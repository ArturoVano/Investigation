
package com.iespacomolla.investigacion.dao.mysql.hbn;

import com.iespacomolla.investigacion.dao.DAOException;
import com.iespacomolla.investigacion.dao.EntidadDAO;
import com.iespacomolla.investigacion.modelo.Entidad;
import java.util.List;
import org.hibernate.JDBCException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class mysqlEntidadHbnDAO implements EntidadDAO{
    
    private SessionFactory sf;
    private Session session;

    public mysqlEntidadHbnDAO(SessionFactory sf) {
        this.sf = sf; 
    }
    
    
    @Override
    public void insertar(Entidad o) throws DAOException {
        session = sf.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.save(o);
            tx.commit();
        }
        catch(JDBCException ex){
            throw new DAOException("Error en INSERT Hbn de Entidad", ex);
        }finally {
            if (tx.isActive())
                tx.rollback();
            if (session.isOpen())
                session.close();
        }
    }

    @Override
    public void modificar(Entidad o) throws DAOException {
        session = sf.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.update(o);
            tx.commit();
            
        }catch(JDBCException ex){
            throw new DAOException("Error en Hbn UPDATE de Entidad", ex);
        }finally {
            if (tx.isActive())
                tx.rollback();
            if (session.isOpen())
                session.close();
        }
    }

    @Override
    public void eliminar(Entidad o) throws DAOException {
        session = sf.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.delete(o);
            tx.commit();
            
        }catch(JDBCException ex){
            throw new DAOException("Error en Hbn DELETE", ex);
        }finally {
            if (tx.isActive())
                tx.rollback();
            if (session.isOpen())
                session.close();
        }
    }

    @Override
    public Entidad obtener(Long id, int level) throws DAOException {
        session = sf.openSession();
        try{
            Entidad e = session.get(Entidad.class, id);
            return e;
        }
        catch (Exception ex){
            throw new DAOException("Error en obtener Campo con Hibernate", ex);
        }
        finally{
            if (session.isOpen())
                session.close();
        }
    }

    @Override
    public List<Entidad> obtenerTodos() throws DAOException {
        session = sf.openSession();
        try{
            List<Entidad> campos = (List<Entidad>) session.createQuery("FROM Entidad").list();
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
