
package com.iespacomolla.investigacion.dao.mysql;

import com.iespacomolla.investigacion.dao.CampoDAO;
import com.iespacomolla.investigacion.dao.DAOManager;
import com.iespacomolla.investigacion.dao.EntidadDAO;
import com.iespacomolla.investigacion.dao.InvestigadorDAO;
import com.iespacomolla.investigacion.dao.ProyectoDAO;
import com.iespacomolla.investigacion.dao.mysql.jdbc.mysqlCampoJdbcDAO;
import com.iespacomolla.investigacion.dao.mysql.jdbc.mysqlEntidadJdbcDAO;
import com.iespacomolla.investigacion.dao.mysql.jdbc.mysqlInvestigadorJdbcDAO;
import com.iespacomolla.investigacion.dao.mysql.jdbc.mysqlProyectoJdbcDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
  Implementación de la clase maestra de DAO´s para MySQL con JDBC
 */
public class mysqlDAOManagerJdbc implements DAOManager{
    

    private Connection conn;
        
    private CampoDAO campo = null;
    private EntidadDAO entidad = null;
    private InvestigadorDAO investigador = null;
    private ProyectoDAO proyecto = null;
    
    public mysqlDAOManagerJdbc(String host, String user, String password, String db) throws SQLException{
        
        conn = DriverManager.getConnection("jdbc:mariadb://"+ host+ ":3306/"+ db, user, password);
               
    }

    @Override
    public CampoDAO getCampoDAO() {
        if (campo == null){
            campo = new mysqlCampoJdbcDAO(conn);
        }
        return campo;
    }

    @Override
    public EntidadDAO getEntidadDAO() {
        if (entidad == null){
            entidad = new mysqlEntidadJdbcDAO(conn);
        }
        return entidad;
    }

    @Override
    public InvestigadorDAO getInvestigadorDAO() {
        if (investigador == null){
            investigador = new mysqlInvestigadorJdbcDAO(conn);
        }
        return investigador;
    }

    @Override
    public ProyectoDAO getProyectoDAO() {
        if (proyecto == null){
            proyecto = new mysqlProyectoJdbcDAO(conn);
        }
        return proyecto;
    }
    
     /*public static void main(String[] args) throws SQLException{
        mysqlDAOManagerJdbc maestro = new mysqlDAOManagerJdbc();
        List<Campo> campos = maestro.getCampoDAO().obtenerTodos();
        System.out.println(campos);
    }*/
}


