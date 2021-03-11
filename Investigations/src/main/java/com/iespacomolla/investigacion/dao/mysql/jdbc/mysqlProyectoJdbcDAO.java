

package com.iespacomolla.investigacion.dao.mysql.jdbc;

import com.iespacomolla.investigacion.dao.DAOException;
import com.iespacomolla.investigacion.dao.ProyectoDAO;
import com.iespacomolla.investigacion.modelo.Campo;
import com.iespacomolla.investigacion.modelo.Entidad;
import com.iespacomolla.investigacion.modelo.Investigador;
import com.iespacomolla.investigacion.modelo.Proyecto;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class mysqlProyectoJdbcDAO implements ProyectoDAO{
    
    // Consultas básicas CRUD:
    final String INSERT = "INSERT INTO Proyecto (campo_id, nombre, fecha_inicio, capital, finalizado, coste,fecha_fin)"
            + "VALUES (?, ?, ?, ?, ?, ?, ?)";
    final String UPDATE = "UPDATE Proyecto SET nombre=?, fecha_inicio=?, capital=?, finalizado=?, coste=?, fecha_fin=?"
            + " WHERE proyecto_id = ?";
    final String DELETE = "DELETE FROM Proyecto WHERE proyecto_id = ? ";
    final String GETONE = "SELECT * FROM Proyecto WHERE proyecto_id = ?";
    final String GETALL = "SELECT * FROM Proyecto";
    final String GETENTIDADES = "SELECT e.entidad_id\n" +
                                "FROM Proyecto p\n" +
                            "LEFT OUTER JOIN entidad_proyecto e_p\n" +
                                "ON p.proyecto_id = e_p.proyecto_id\n" +
                            "LEFT OUTER JOIN Entidad AS e\n" +
                                "ON e_p.entidad_id = e.entidad_id\n" +
                            "WHERE e_p.proyecto_id = ?;";
    final String GETINVESTIGADORES = "SELECT i.investigador_id\n" +
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
            pStat = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            pStat.setLong(1, o.getCampo().getCampo_id());
            pStat.setString(2, o.getNombre());
            pStat.setString(3, o.getFecha_inicio().toString());
            if (o.getCapital() == null)
               pStat.setDouble(4, 0.0);
            else
                pStat.setDouble(4, o.getCapital());
            pStat.setBoolean(5, o.isFinalizado());
            if (o.getCoste() == null)
                pStat.setDouble(6, 0.0);
            else
                pStat.setDouble(6, o.getCoste());
            if (o.getFecha_fin() == null)
                pStat.setDate(7, null);
            else
                pStat.setDate(7, Date.valueOf(o.getFecha_fin()));
            
            if (pStat.executeUpdate() == 0)
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
            if (o.getCapital() == null)
               pStat.setDouble(3, 0.0);
            else
                pStat.setDouble(3, o.getCapital());
            pStat.setBoolean(4, o.isFinalizado());
            if (o.getCoste() == null)
                pStat.setDouble(5, 0.0);
            else
                pStat.setDouble(5, o.getCoste());
            pStat.setDate(6, java.sql.Date.valueOf(o.getFecha_fin()));
            pStat.setLong(7, o.getProyecto_id());
            
            if (pStat.executeUpdate() == 0)
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
            
            if (pStat.executeUpdate() == 0)
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
    public Proyecto conversion(int level) throws DAOException{
        ResultSet rs2 = null;
        try{
            long proyecto_id = rs.getLong("proyecto_id");
            String nombre = rs.getString("nombre");
            LocalDate fecha_inicio = rs.getDate("fecha_inicio").toLocalDate();
            Double capital = rs.getDouble("capital");
            
            mysqlCampoJdbcDAO cDAO = new mysqlCampoJdbcDAO(con);
            //Campo no tiene atributos dependientes, puedo ir siempre en 1er nivel.
            Campo c = cDAO.obtener(rs.getLong("campo_id"), 0);
            
            Boolean finalizado = rs.getBoolean("finalizado");
            Double coste = null;
            LocalDate fecha_fin = null;
            
            coste = rs.getDouble("coste");
            
            if (rs.getDate("fecha_fin") != null){
                fecha_fin = rs.getDate("fecha_fin").toLocalDate();
            }
            List<Entidad> entidades = new ArrayList();
            pStat = null;
            //Bloque try para conseguir las Entidades de este Proyecto:
            try{
                mysqlEntidadJdbcDAO eDAO = new mysqlEntidadJdbcDAO(con);
                pStat = con.prepareStatement(GETENTIDADES);
                pStat.setLong(1, proyecto_id);
                rs2 = pStat.executeQuery();
                
                while (rs2.next()){
                    // Puede dar StackOverflowException, uso level para frenar la recursividad:
                    if (level < 1)
                        entidades.add(eDAO.obtener(rs2.getLong("entidad_id"), 1));
                    //Le he mandado a por las entidades de este proyecto, pero con level 1, para que las clases dependientes me las ponga null.
                    else
                        entidades.add(null);
                }
                // Crear un centinela para parar la recursividad, si carga la entidad que la ha llamado, poner a null su posición en la lista y frenar la recursividad.
            }catch (SQLException ex){
                throw new DAOException("Error al obtener las entidades que financian este proyecto. ",ex);
            }
            List<Investigador> investigadores = new ArrayList();
            //Bloque try para conseguir los Investigadores de este Proyecto
            try{
               mysqlInvestigadorJdbcDAO iDAO = new mysqlInvestigadorJdbcDAO(con);
               pStat = con.prepareStatement(GETINVESTIGADORES);
                pStat.setLong(1, proyecto_id);
                rs2 = pStat.executeQuery();
                
                while (rs2.next()){
                    if (level < 1)
                        investigadores.add(iDAO.obtener(rs2.getLong("investigador_id"),1));
                    else 
                       investigadores.add(null); 
                }
            }
            catch(SQLException ex){
                throw new DAOException("Error al obtener los investigadores que trabajan en este proyecto. ",ex);
            }
            Proyecto p = new Proyecto(nombre, capital, fecha_inicio, c, entidades, investigadores,
                    finalizado, coste, fecha_inicio);
            p.setProyecto_id(proyecto_id);
            return p;
            
        }catch(SQLException ex){
            throw new DAOException("Error al obtener datos de la Entidad",ex);
        }
        finally{
            if (rs2 != null){
                try {
                    rs2.close();
                } catch (SQLException ex) {
                    throw new DAOException("Error al terminar de obtener datos de la Entidad",ex);
                }
            }
        }
    }
    
    
    @Override
    public Proyecto obtener(Long id, int level) throws DAOException{
        pStat = null;
        Proyecto p = null;
        rs = null;
        try{
            pStat = con.prepareStatement(GETONE);
            pStat.setLong(1, id);
            rs = pStat.executeQuery();
            
            if (rs.next())
                p = conversion(level);
            else{
                throw new DAOException("No se ha encontrado el registro de la tabla Proyecto");
            }     
        }
        catch (SQLException ex){
            throw new DAOException("Error en SQL GETONE Proyecto", ex);
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
                // obtenerTodos siempre empieza en level 0
                proyectos.add(conversion(0));  
            
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
