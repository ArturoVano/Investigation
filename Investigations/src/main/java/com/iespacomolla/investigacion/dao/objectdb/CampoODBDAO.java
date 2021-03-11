

package com.iespacomolla.investigacion.dao.objectdb;

import com.iespacomolla.investigacion.dao.CampoDAO;
import com.iespacomolla.investigacion.dao.DAOException;
import com.iespacomolla.investigacion.modelo.Campo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;


public class CampoODBDAO implements CampoDAO{
    
    final String UPDATE = "UPDATE Campo SET nombre = ?, ramal = ? WHERE campo_id = ?";
        
    private EntityManagerFactory emf;
    private EntityManager em;

    public CampoODBDAO(EntityManagerFactory emf) {
        this.emf = emf;
        
    }
    
    
    @Override
    public void insertar(Campo o) throws DAOException {
        
        em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(o);
            em.getTransaction().commit();
        }
        catch(Exception ex){
            throw new DAOException("Fallo"
                    + " al insertar Campo con ObjectDB ", ex);
        }
        finally{
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            if (em.isOpen())
                em.close();
        }
    }

    @Override
    public void modificar(Campo o) throws DAOException {
        
        
        try{
            em = emf.createEntityManager();
            em.getTransaction().begin();
            //Si no está administrada por em, hacer que sea administrada por em
            em.persist(em.contains(o)? o : em.merge(o));
            em.flush();
            em.getTransaction().commit();
        }
        catch(Exception ex){
            throw new DAOException("Fallo al modificar el Campo con ObjectDB", ex);
        }
        finally{
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            if (em.isOpen())
                em.close();
        }
    }

    @Override
    public void eliminar(Campo o) throws DAOException {
        
        em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            //Si no está administrada por em, hacer que sea administrada por em
            em.remove(em.contains(o)? o : em.merge(o));   
            em.getTransaction().commit();
        }
        catch(Exception ex){
            throw new DAOException("Fallo al eliminar el Campo con ObjectDB", ex);
        }
        finally{
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            if (em.isOpen())
                em.close();
        }
    }

    @Override
    public Campo obtener(Long id, int level) throws DAOException {
        
        em = emf.createEntityManager();
        Campo c = null;
        try{
            c = em.find(Campo.class, id);
            return c;
        }
        catch(Exception ex){
            throw new DAOException("Fallo al obtener el Campo con ObjectDB", ex);
        }
        finally{
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            if (em.isOpen())
                em.close();
        }
    }

    @Override
    public List<Campo> obtenerTodos() throws DAOException {
        
        em = emf.createEntityManager();
        List<Campo> campos = null;
        try{
            TypedQuery<Campo> query = em.createQuery("SELECT c FROM Campo c", Campo.class);
            campos = query.getResultList();
            return campos;
        }
        catch(Exception ex){
            throw new DAOException("Fallo al obtenerTodos los Campos con ObjectDB", ex);
        }
        finally{
            if (em.isOpen())
                em.close();
            
        }
    }

}
