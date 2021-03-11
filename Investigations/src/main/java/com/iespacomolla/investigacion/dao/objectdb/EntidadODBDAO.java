

package com.iespacomolla.investigacion.dao.objectdb;

import com.iespacomolla.investigacion.dao.DAOException;
import com.iespacomolla.investigacion.dao.EntidadDAO;
import com.iespacomolla.investigacion.modelo.Entidad;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;


public class EntidadODBDAO implements EntidadDAO{
    
    final String UPDATE = "UPDATE Entidad SET nombre=? ubicacion=? WHERE entidad_id = ?";
    
    private EntityManagerFactory emf;
    private EntityManager em;

    public EntidadODBDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    
    @Override
    public void insertar(Entidad o) throws DAOException {
        
        em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(o);
            em.getTransaction().commit();
        }
        catch(Exception ex){
            throw new DAOException("Fallo"
                    + " al insertar Entidad con ObjectDB ", ex);
        }
        finally{
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            if (em.isOpen())
                em.close();
        }
    }

    @Override
    public void modificar(Entidad o) throws DAOException {
        
        em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(em.contains(o)? o : em.merge(o));
            /*Query query = em.createQuery(UPDATE);
            query.setParameter(0, o.getNombre());
            query.setParameter(1, o.getUbicacion());
            query.setParameter(2, o.getEntidad_id());*/
            em.getTransaction().commit();
        }
        catch(Exception ex){
            throw new DAOException("Fallo al modificar Entidad con ObjectDB", ex);
        }
        finally{
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            if (em.isOpen())
                em.close();
        }
    }

    @Override
    public void eliminar(Entidad o) throws DAOException {
        
        em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.remove(em.contains(o)? o : em.merge(o));  
            em.getTransaction().commit();
        }
        catch(Exception ex){
            throw new DAOException("Fallo al eliminar el Entidad con ObjectDB", ex);
        }
        finally{
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            if (em.isOpen())
                em.close();
        }
    }

    @Override
    public Entidad obtener(Long id, int level) throws DAOException {
        
        em = emf.createEntityManager();
        Entidad e = null;
        try{
            e = em.find(Entidad.class, id);
            return e;
        }
        catch(Exception ex){
            throw new DAOException("Fallo al modificar el Entidad con ObjectDB", ex);
        }
        finally{
            if (em.isOpen())
                em.close();
        }
    }

    @Override
    public List<Entidad> obtenerTodos() throws DAOException {
        
        em = emf.createEntityManager();
        List<Entidad> entidades = null;
        try{
            TypedQuery<Entidad> query = em.createQuery("SELECT e FROM Entidad e", Entidad.class);
            entidades = query.getResultList();
            return entidades;
        }
        catch(Exception ex){
            throw new DAOException("Fallo al obtenerTodos las Entidades con ObjectDB", ex);
        }
        finally{
            if (em.isOpen())
                em.close();
        }
    }

}
