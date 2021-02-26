
package com.iespacomolla.investigacion.dao.mysql.jdbc;

import com.iespacomolla.investigacion.dao.CampoDAO;
import com.iespacomolla.investigacion.dao.DAOException;
import com.iespacomolla.investigations.modelo.Campo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class mysqlCampoJdbcDAO implements CampoDAO{
    
    // Consultas básicas CRUD:
    final String INSERT = "INSERT INTO Campo (nombre, ramal) VALUES (?, ?)";
    final String UPDATE = "UPDATE Campo SET nombre = ?, ramal = ? WHERE campo_id = ?";
    final String DELETE = "DELETE FROM Campo WHERE campo_id = ? ";
    final String GETONE = "SELECT * FROM Campo WHERE campo_id = ?";
    final String GETALL = "SELECT * FROM Campo";  
    private Connection con;
    private ResultSet rs;
    
    public mysqlCampoJdbcDAO(Connection con) {
        this.con = con;
    }
    
    
    @Override
    public void insertar(Campo o) throws DAOException{
        
        PreparedStatement pStat = null;
        try{
            pStat = con.prepareStatement(INSERT);
            pStat.setString(1, o.getRamal());
            pStat.setString(2, o.getNombre());
            
            if (!pStat.execute())
                throw new DAOException("Puede que el objeto de inserción haya sido persistido en la BD.");
            //Obtengo el id que me autogeneró el INSERT para el objeto:
            rs = pStat.getGeneratedKeys();
            if (rs.next())
                o.setCampo_id(rs.getLong(1));
            else
                throw new DAOException("Fallo al asignar ID a este Campo.");
             
        }
        catch (SQLException ex){
            throw new DAOException("Error en SQL INSERT ", ex);
        }
        finally{
            if (rs != null){
                try{
                    rs.close();
                }catch(SQLException ex){
                    throw new DAOException("Error en SQL INSERT ", ex);
                }
            }
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
    public void modificar(Campo o) throws DAOException{
        PreparedStatement pStat = null;
        try{
            pStat = con.prepareStatement(UPDATE);
            pStat.setString(2, o.getNombre());
            pStat.setString(3, o.getRamal());
            
            if (!pStat.execute())
                throw new DAOException("Puede que el objeto de inserción haya sido persistido en la BD.");
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
    public void eliminar(Campo o) throws DAOException{
        PreparedStatement pStat = null;
        try{
            pStat = con.prepareStatement(DELETE);
            pStat.setLong(1, o.getCampo_id());
            
            if (!pStat.execute())
                throw new DAOException("Puede que el objeto de inserción haya sido persistido en la BD.");
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
    
    //Transforma los valores de ResultSet en un objeto:
    private Campo conversion() throws SQLException{
        
        long id = rs.getLong("campo_id");
        String nombre = rs.getString("nombre");
        String ramal = rs.getString("ramal");
        
        Campo c = new Campo(id, nombre, ramal);
        return c;
    }
    
    
    @Override
    public Campo obtener(Long id) throws DAOException{
        PreparedStatement pStat = null;
        Campo c = null;
        rs = null;
        try{
            pStat = con.prepareStatement(GETONE);
            pStat.setLong(1, id);
            rs = pStat.executeQuery();
            
            if (rs.next())
                c = conversion();
            else{
                throw new DAOException("No se ha encontrado el registro de la tabla Campo");
            }     
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
            if (rs != null){
                try{
                    rs.close();
                }catch(SQLException ex){
                    new DAOException("Error en GETONE SQL",ex);
                }
            }
        }
        return c;
    }

    @Override
    public List<Campo> obtenerTodos() throws DAOException{   
        PreparedStatement pStat = null;
        List<Campo> campos = new ArrayList();
        rs = null;
        try{
            pStat = con.prepareStatement(GETALL);
            rs = pStat.executeQuery();
            
            while (rs.next())
                campos.add(conversion());    
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
            if (rs != null){
                try{
                    rs.close();
                }catch(SQLException ex){
                    new DAOException("Error en GETONE SQL",ex);
                }
            }
        }
        return campos;
    }
    
}
