/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iespacomolla.investigacion.dao.mysql.jdbc;

import com.iespacomolla.investigacion.dao.DAOException;
import com.iespacomolla.investigacion.dao.ProyectoDAO;
import com.iespacomolla.investigations.modelo.Campo;
import com.iespacomolla.investigations.modelo.Entidad;
import com.iespacomolla.investigations.modelo.Investigador;
import com.iespacomolla.investigations.modelo.Proyecto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class mysqlProyectoJdbcDAO implements ProyectoDAO{
    
    // Consultas básicas CRUD:
    final String INSERT = "INSERT INTO Proyecto (campo_id nombre, fecha_inicio, capital, finalizado, coste,fecha_fin)"
            + "VALUES (?, ?, ?, ?, ?, ?)";
    final String UPDATE = "UPDATE Proyecto SET nombre=?, fecha_inicio=?, capital=?, finalizado=?, coste=?, fecha_fin=?"
            + " WHERE proyecto_id = ?";
    final String DELETE = "DELETE FROM Proyecto WHERE proyecto_id = ? ";
    final String GETONE = "SELECT * FROM Proyecto WHERE proyecto_id = ?";
    final String GETALL = "SELECT * FROM Proyecto";
    final String GETENTIDADES = "SELECT e.*\n" +
                                "FROM Proyecto p\n" +
                            "LEFT OUTER JOIN entidad_proyecto e_p\n" +
                                "ON p.proyecto_id = e_p.proyecto_id\n" +
                            "LEFT OUTER JOIN entidad AS e\n" +
                                "ON e_p.entidad_id = e.entidad_id\n" +
                            "WHERE e_p.proyecto_id = ?;";
    final String GETPROYECTOS = "SELECT i.investigador_id\n" +
                            "    FROM Proyecto p \n" +
                            "RIGHT JOIN Investigador i \n" +
                            "    ON i.proyecto_id = p.proyecto_id \n" +
                            "WHERE i.proyecto_id = ?;";
    
    private Connection con;
    private ResultSet rs;
    private PreparedStatement pStat;
    
    public mysqlProyectoJdbcDAO(Connection con) {
        this.con = con;
    }
    
    @Override
    public void insertar(Proyecto o) throws DAOException{
        PreparedStatement pStat = null;
        try{
            pStat = con.prepareStatement(INSERT);
            pStat.setLong(1, o.getCampo().getCampo_id());
            pStat.setString(2, o.getNombre());
            pStat.setString(3, o.getFecha_inicio().toString());
            pStat.setDouble(4, o.getCapital());
            pStat.setBoolean(5, o.isFinalizado());
            pStat.setDouble(6, o.getCoste());
            pStat.setString(7, o.getFecha_fin().toString());
            
            if (!pStat.execute())
                throw new DAOException("Puede que el objeto de inserción haya sido persistido en la BD.");
            //Obtengo el id que me autogeneró el INSERT para el objeto:
            rs = pStat.getGeneratedKeys();
            if (rs.next())
                o.setProyecto_id(rs.getLong(1));
            else
                throw new DAOException("Fallo al asignar ID a este Campo.");
             
        }
        catch (SQLException ex){
            throw new DAOException("Error en SQL INSERT ", ex);
        }
        finally{
            cerrarBasura();
        }
    }

    @Override
    public void modificar(Proyecto o) throws DAOException{
        pStat = null;
        try{
            pStat = con.prepareStatement(UPDATE);
            pStat.setString(1, o.getNombre());
            pStat.setDate(2, java.sql.Date.valueOf(o.getFecha_inicio()));
            pStat.setDouble(3, o.getCapital());
            pStat.setBoolean(4, o.isFinalizado());
            pStat.setDouble(5, o.getCoste());
            pStat.setDate(5, java.sql.Date.valueOf(o.getFecha_fin()));
            pStat.setLong(6, o.getProyecto_id());
            
            if (!pStat.execute())
                throw new DAOException("Puede que no se haya modificado la fila en la BD.");
        }
        catch (SQLException ex){
            throw new DAOException("Error en SQL INSERT ", ex);
        }
        finally{
            if (pStat != null){
                try{
                   pStat.close();
                }catch (SQLException ex){
                    throw new DAOException("Error en SQL INSERT", ex);
                }
            }
        }
    }

    @Override
    public void eliminar(Proyecto o) throws DAOException{
        PreparedStatement pStat = null;
        try{
            pStat = con.prepareStatement(DELETE);
            pStat.setLong(1, o.getProyecto_id());
            
            if (!pStat.execute())
                throw new DAOException("Puede que el objeto no haya sido eliminado de la BD.");
        }
        catch (SQLException ex){
            throw new DAOException("Error en SQL DELETE ", ex);
        }
        finally{
            if (pStat != null){
                try{
                   pStat.close();
                }catch (SQLException ex){
                    throw new DAOException("Error en SQL DELETE", ex);
                }
            }
        }
    }
    
    
    //Transforma los valores de ResultSet en un objeto:
    public Proyecto conversion() throws DAOException{
        try{
            long proyecto_id = rs.getLong("proyecto_id");
            String nombre = rs.getString("nombre");
            LocalDate fecha_inicio = rs.getDate("fecha_inicio").toLocalDate();
            Double capital = rs.getDouble("capital");
            
            mysqlCampoJdbcDAO cDAO = new mysqlCampoJdbcDAO(con);
            Campo c = cDAO.obtener(rs.getLong("campo_id"));
            
            Boolean finalizado = rs.getBoolean("finalizado");
            Double coste = null;
            LocalDate fecha_fin = null;
            if (finalizado){
                coste = rs.getDouble("coste");
                fecha_fin = rs.getDate("fecha_fin").toLocalDate();
            }
            List<Entidad> entidades = new ArrayList();
            pStat = null; 
            rs = null; //Ya no necesito el ResultSet.
            //Bloque try para conseguir las Entidades de este Proyecto:
            try{
                mysqlEntidadJdbcDAO eDAO = new mysqlEntidadJdbcDAO(con);
                pStat = con.prepareStatement(GETENTIDADES);
                pStat.setLong(1, proyecto_id);
                rs = pStat.executeQuery();
                
                while (rs.next()){
                    entidades.add(eDAO.obtener(rs.getLong("entidad_id")));
                }
            }catch (SQLException ex){
                throw new DAOException("Error al obtener las entidades que financian este proyecto. ",ex);
            }
            List<Investigador> investigadores = new ArrayList();
            //Bloque try para conseguir los Investigadores de este Proyecto
            try{
               mysqlInvestigadorJdbcDAO iDAO = new mysqlInvestigadorJdbcDAO(con);
               pStat = con.prepareStatement(GETPROYECTOS);
                pStat.setLong(1, proyecto_id);
                rs = pStat.executeQuery();
                
                while (rs.next()){
                    investigadores.add(iDAO.obtener(rs.getLong("investigador_id")));
                }
            }
            catch(SQLException ex){
                throw new DAOException("Error al obtener los investigadores que trabajan en este proyecto. ",ex);
            }
            Proyecto p = new Proyecto(nombre, capital, fecha_inicio, c, entidades, investigadores,
                    finalizado, coste, fecha_inicio);
            return p;
            
        }catch(SQLException ex){
            throw new DAOException("Error al obtener datos de la Entidad",ex);
        }  
    }
    
    
    @Override
    public Proyecto obtener(Long id) throws DAOException{
        pStat = null;
        Proyecto p = null;
        rs = null;
        try{
            pStat = con.prepareStatement(GETONE);
            pStat.setLong(1, id);
            rs = pStat.executeQuery();
            
            if (rs.next())
                p = conversion();
            else{
                throw new DAOException("No se ha encontrado el registro de la tabla Proyecto");
            }     
        }
        catch (SQLException ex){
            throw new DAOException("Error en SQL GETONE ", ex);
        }
        finally{
            cerrarBasura();
        }
        return p;
    }

    @Override
    public List<Proyecto> obtenerTodos() throws DAOException{
        pStat = null;
        List<Proyecto> proyectos = new ArrayList();
        rs = null;
        try{
            pStat = con.prepareStatement(GETALL);
            rs = pStat.executeQuery();
            
            while (rs.next())                
                proyectos.add(conversion());  
            
        }
        catch (SQLException ex){
            throw new DAOException("Error en SQL GETALL ", ex);
        }
        finally{   
            cerrarBasura();
        }
        return proyectos;
    }

    
    //Comprueba y cierra recursos
    private void cerrarBasura() throws DAOException{
        
        if (pStat != null){
                try{
                   pStat.close();
                }catch (SQLException ex){
                    throw new DAOException("Error al cerrar PreparedStatement. ", ex);
                }
            }
            if (rs != null){
                try{
                    rs.close();
                }catch(SQLException ex){
                    new DAOException("Error al cerrar ResultSet. ",ex);
                }
            }
    }
}
