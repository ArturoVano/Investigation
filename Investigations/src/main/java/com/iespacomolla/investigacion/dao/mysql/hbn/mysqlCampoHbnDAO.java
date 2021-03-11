
package com.iespacomolla.investigacion.dao.mysql.hbn;

import com.iespacomolla.investigacion.dao.CampoDAO;
import com.iespacomolla.investigacion.dao.DAOException;
import com.iespacomolla.investigacion.modelo.Campo;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.JDBCException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class mysqlCampoHbnDAO implements CampoDAO{
    
    private final SessionFactory sf;
    private Session session;
            
    public mysqlCampoHbnDAO(SessionFactory sf) {
        this.sf = sf;
    }
    
    
    @Override
    public void insertar(Campo o) throws DAOException {
        
        session = sf.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.save(o);
            tx.commit();
            session.clear();
        }catch(JDBCException ex){
            throw new DAOException("Error en Hbn INSERT", ex);
        }finally {
            if (tx.isActive())
                tx.rollback();
            if (session.isOpen())
                session.close();
        }
        
    }

    @Override
    public void modificar(Campo o) throws DAOException {
        
        session = sf.openSession();
        //Transaction tx = null;
        try{
            session.getTransaction().begin();
            session.update(o);
            session.getTransaction().commit();
            session.clear();
            
        }catch(Exception ex){
            throw new DAOException("Error en Hbn UPDATE de Campo", ex);
        }finally {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            if (session.isOpen())
                session.close();
        }   
        
    }

    @Override
    public void eliminar(Campo o) throws DAOException {
        
        session = sf.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.delete(o);
            tx.commit();
            session.clear();
        }catch(JDBCException ex){
            throw new DAOException("Error en Hbn DELETE", ex);
        }finally {
            if (tx.isActive())
                tx.rollback();
        }   if (session.isOpen())
                session.close();
    }

    @Override
    public Campo obtener(Long id, int level) throws DAOException {
        
        session = sf.openSession();
        try{
            Campo c = session.get(Campo.class, id);
            session.clear();
            return c;
        }catch (Exception ex){
            throw new DAOException("Error en obtener Campo con Hibernate", ex);
        }
        finally{
            if (session.isOpen())
                session.close();
        }
    }

    @Override
    public List<Campo> obtenerTodos() throws DAOException {
        
        session = sf.openSession();
        try{
            List<Campo> campos = (List<Campo>) session.createQuery("FROM Campo").list();
            return campos;
        }
        catch (Exception ex){
            throw new DAOException("Error en obtenerTodos de Campo con Hibernate", ex);
        }
        finally{
            if (session.isOpen())
                session.close();
        }  
    }

}
