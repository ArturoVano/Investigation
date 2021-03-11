

package com.iespacomolla.investigacion.dao.objectdb;

import com.iespacomolla.investigacion.dao.DAOException;
import com.iespacomolla.investigacion.dao.ProyectoDAO;
import com.iespacomolla.investigacion.modelo.Proyecto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;


public class ProyectoODBDAO implements ProyectoDAO{
    
    //le faltan parámetros para el objeto java completo
    final String UPDATE = "UPDATE Proyecto SET nombre=?, fecha_inicio=?, capital=?, finalizado=?, coste=?, fecha_fin=?"
            + " WHERE proyecto_id = ?";
    
    private EntityManager em;
    private EntityManagerFactory emf;
    
    public ProyectoODBDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    
    @Override
    public void insertar(Proyecto o) throws DAOException {
        
        em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(o);
            em.getTransaction().commit();
        }
        catch(Exception ex){
            throw new DAOException("Fallo"
                    + " al insertar Proyecto con ObjectDB ", ex);
        }
        finally{
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            if (em.isOpen())
                em.close();
        }
    }

    @Override
    public void modificar(Proyecto o) throws DAOException {
        
        em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(em.contains(o)? o : em.merge(o));
            em.getTransaction().commit();
        }
        catch(Exception ex){
            throw new DAOException("Fallo al modificar el Proyecto con ObjectDB", ex);
        }
        finally{
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            if (em.isOpen())
                em.close();
        }
    }

    @Override
    public void eliminar(Proyecto o) throws DAOException {
        
        em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.remove(em.contains(o)? o : em.merge(o));  
            em.getTransaction().commit();
        }
        catch(Exception ex){
            throw new DAOException("Fallo al eliminar el Proyecto con ObjectDB", ex);
        }
        finally{
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            if (em.isOpen())
                em.close();
        }
    }

    @Override
    public Proyecto obtener(Long id, int level) throws DAOException {
        
        em = emf.createEntityManager();
        Proyecto e = null;
        try{
            e = em.find(Proyecto.class, id);
            return e;
        }
        catch(Exception ex){
            throw new DAOException("Fallo al obtener el Proyectos con ObjectDB", ex);
        }
        finally{
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            if (em.isOpen())
                em.close();
        }
    }

    @Override
    public List<Proyecto> obtenerTodos() throws DAOException {
        
        em = emf.createEntityManager();
        List<Proyecto> proyectos = null;
        try{
            TypedQuery<Proyecto> query = em.createQuery("SELECT p FROM Proyecto p", Proyecto.class);
            proyectos = query.getResultList();
            return proyectos;
        }
        catch(Exception ex){
            throw new DAOException("Fallo al obtenerTodos las Proyectos con ObjectDB", ex);
        }
        finally{
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            if (em.isOpen())
                em.close();
        } 
    }

}
