
package com.iespacomolla.investigacion.dao.mysql.hbn;

import com.iespacomolla.investigacion.dao.CampoDAO;
import com.iespacomolla.investigacion.dao.DAOException;
import com.iespacomolla.investigacion.dao.DAOManager;
import com.iespacomolla.investigacion.dao.EntidadDAO;
import com.iespacomolla.investigacion.dao.InvestigadorDAO;
import com.iespacomolla.investigacion.dao.ProyectoDAO;
import com.iespacomolla.investigacion.modelo.Campo;
import com.iespacomolla.investigacion.modelo.Entidad;
import com.iespacomolla.investigacion.modelo.Investigador;
import com.iespacomolla.investigacion.modelo.Proyecto;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * 
  Implementación de la clase maestra de DAO´s para MySQL con Hibernate
 */
public class mysqlDAOManagerHbn implements DAOManager{
    
    private CampoDAO campo = null;
    private EntidadDAO entidad = null;
    private InvestigadorDAO investigador = null;
    private ProyectoDAO proyecto = null;
    
    private SessionFactory sf;
    private Session session;

    public mysqlDAOManagerHbn() throws DAOException{
        
        sf = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Campo.class).
                addAnnotatedClass(Entidad.class).addAnnotatedClass(Investigador.class).
                addAnnotatedClass(Proyecto.class).buildSessionFactory();
        
        
        if (sf == null)
            throw new DAOException("La cadena de conexión de Hibernate ha fallado");
    }
    
    
    @Override
    public CampoDAO getCampoDAO() { 
        if (campo == null){
            campo = new mysqlCampoHbnDAO(sf);
        }
        return campo;
    }

    @Override
    public EntidadDAO getEntidadDAO() {
        if (entidad == null){
            entidad = new mysqlEntidadHbnDAO(sf);
        }
        return entidad;
    }

    @Override
    public InvestigadorDAO getInvestigadorDAO() {
        if (investigador == null){
            investigador = new mysqlInvestigadorHbnDAO(sf);
        }
        return investigador;
    }

    @Override
    public ProyectoDAO getProyectoDAO() {
        if (proyecto == null){
            proyecto = new mysqlProyectoHbnDAO(sf);
        }
        return proyecto;
    }
    
 
    public SessionFactory getSessionFactory() {
        return sf;
    }
    
    public void closeConnection() {
        sf.close();
    }
    
    
}
