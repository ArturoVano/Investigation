
package com.iespacomolla.investigacion.dao.mysql.hbn;

import com.iespacomolla.investigacion.dao.DAOException;
import com.iespacomolla.investigacion.dao.ProyectoDAO;
import com.iespacomolla.investigacion.modelo.Proyecto;
import java.util.List;
import org.hibernate.JDBCException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class mysqlProyectoHbnDAO implements ProyectoDAO{
    
    private SessionFactory sf;
    private Session session;

    public mysqlProyectoHbnDAO(SessionFactory sf) {
        this.sf = sf;          
    }
    
    @Override
    public void insertar(Proyecto o) throws DAOException {
        
        session = sf.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.save(o);
            tx.commit();
        }
        catch(JDBCException ex){
            throw new DAOException("Error en INSERT Hbn de Proyecto", ex);
        }finally {
            if (tx.isActive())
                tx.rollback();
            if (session.isOpen())
                session.close();
        }
    }

    @Override
    public void modificar(Proyecto o) throws DAOException {
        session = sf.openSession();
        //Transaction tx = null;
        try{
            session.getTransaction().begin();
            session.update(o);
            session.getTransaction().commit();
            
        }catch(Exception ex){
            throw new DAOException("Error en Hbn UPDATE de Proyecto", ex);
        }finally {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            if (session.isOpen())
                session.close();
        }
    }

    @Override
    public void eliminar(Proyecto o) throws DAOException {
        session = sf.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.delete(o);
            tx.commit();
            
        }catch(JDBCException ex){
            throw new DAOException("Error en Hbn DELETE de Proyecto", ex);
        }finally {
            if (tx.isActive())
                tx.rollback(); 
            if (session.isOpen())
                session.close();
        }
    }

    @Override
    public Proyecto obtener(Long id, int level) throws DAOException {
        
        session = sf.openSession();
        try{
            Proyecto p = session.get(Proyecto.class, id);
            return p;
        }catch (Exception ex){
            throw new DAOException("Error en obtener Entidad con Hibernate", ex);
        }
        finally{
            if (session.isOpen())
                session.close();
        }
    }

    @Override
    public List<Proyecto> obtenerTodos() throws DAOException {
        session = sf.openSession();
        try{
            session = sf.openSession();
            List<Proyecto> campos = (List<Proyecto>) session.createQuery("FROM Proyecto").list();
            return campos;
        }catch(Exception ex){
            throw new DAOException("Error en obtenerTodos de Proyecto con Hibernate", ex);
        }
        finally{
            if (session.isOpen())
                session.close();
        }
        
    }

}
