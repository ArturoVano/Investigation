

package com.iespacomolla.investigacion.dao.mysql.hbn;

import com.iespacomolla.investigacion.dao.DAOException;
import com.iespacomolla.investigacion.dao.InvestigadorDAO;
import com.iespacomolla.investigacion.modelo.Investigador;
import java.util.List;
import org.hibernate.JDBCException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class mysqlInvestigadorHbnDAO implements InvestigadorDAO{
   
    private SessionFactory sf;
    private Session session;

    public mysqlInvestigadorHbnDAO(SessionFactory sf) {
        this.sf = sf;
    }
    
    
    @Override
    public void insertar(Investigador o) throws DAOException {
        session = sf.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.save(o);
            tx.commit();
            session.clear();
        }
        catch(JDBCException ex){
            throw new DAOException("Error en INSERT Hbn de Investigador", ex);
        }finally {
            if (tx.isActive())
                tx.rollback();
            if (session.isOpen())
                session.close();
        }
    }

    @Override
    public void modificar(Investigador o) throws DAOException {
        session = sf.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.update(o);
            tx.commit();
            session.clear();
        }catch(JDBCException ex){
            throw new DAOException("Error en Hbn UPDATE de Investigador", ex);
        }finally {
            if (tx.isActive())
                tx.rollback();
            if (session.isOpen())
                session.close();
        }
    }

    @Override
    public void eliminar(Investigador o) throws DAOException {
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
            if (session.isOpen())
                session.close();
        }
    }

    @Override
    public Investigador obtener(Long id, int level) throws DAOException {
        session = sf.openSession();
        try{
            Investigador i = session.get(Investigador.class, id);
            session.clear();
            return i;
        }catch (Exception ex){
            throw new DAOException("Error en obtener Investigador con Hibernate", ex);
        }finally{
            if (session.isOpen())
                session.close();
        }
        
    }

    @Override
    public List<Investigador> obtenerTodos() throws DAOException {
        
        session = sf.openSession();
        try{
           List<Investigador> campos = (List<Investigador>) session.createQuery("FROM Investigador").list();
            session.clear();
            return campos; 
        }catch (Exception ex){
            throw new DAOException("Error en obtenerTodos de Entidad con Hibernate", ex);
        }
        finally{
            if (session.isOpen())
                session.close();
        }
    }

}