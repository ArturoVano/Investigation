

package com.iespacomolla.investigacion.dao.objectdb;

import com.iespacomolla.investigacion.dao.DAOException;
import com.iespacomolla.investigacion.dao.InvestigadorDAO;
import com.iespacomolla.investigacion.modelo.Investigador;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;



public class InvestigadorODBDAO implements InvestigadorDAO{
    
    final String UPDATE = "UPDATE Investigador SET proyecto_id = ?, nombre =?, titulo=?, salario =? WHERE investigador_id = ?";
    
    private EntityManager em;
    private EntityManagerFactory emf;

    public InvestigadorODBDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    
    @Override
    public void insertar(Investigador o) throws DAOException {
        
        em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(o);
            em.flush();
            em.getTransaction().commit();
        }
        catch(Exception ex){
            throw new DAOException("Fallo"
                    + " al insertar Investigador con ObjectDB ", ex);
        }
        finally{
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            if (em.isOpen())
                em.close();
        }
    }

    @Override
    public void modificar(Investigador o) throws DAOException {
        
        em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            /*Query query = em.createQuery(UPDATE);
            query.setParameter(0, o.getProyecto().getProyecto_id());
            query.setParameter(1, o.getNombre());
            query.setParameter(2, o.getTitulo());
            query.setParameter(3, o.getSalario());
            query.setParameter(4, o.getInvestigador_id());*/
            em.persist(em.contains(o)? o : em.merge(o));
            em.getTransaction().commit();
        }
        catch(Exception ex){
            throw new DAOException("Fallo al modificar el Investigador con ObjectDB", ex);
        }
        finally{
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            if (em.isOpen())
                em.close();
        }
    }

    @Override
    public void eliminar(Investigador o) throws DAOException {
        
        em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.remove(em.contains(o)? o : em.merge(o));  
            em.getTransaction().commit();
        }
        catch(Exception ex){
            throw new DAOException("Fallo al eliminar el Investigador con ObjectDB", ex);
        }
        finally{
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            if (em.isOpen())
                em.close();
        }
    }

    @Override
    public Investigador obtener(Long id, int level) throws DAOException {
        
        em = emf.createEntityManager();
        Investigador e = null;
        try{
            e = em.find(Investigador.class, id);        
            return e;
        }
        catch(Exception ex){
            throw new DAOException("Fallo al obtener el Investigador con ObjectDB", ex);
        }
        finally{
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            if (em.isOpen())
                em.close();
        }
    }

    @Override
    public List<Investigador> obtenerTodos() throws DAOException {
        
        em = emf.createEntityManager();
        List<Investigador> investigadores = null;
        try{
            TypedQuery<Investigador> query = em.createQuery("SELECT i FROM Investigador i", Investigador.class);
            investigadores = query.getResultList();
            return investigadores;
        }
        catch(Exception ex){
            throw new DAOException("Fallo al obtenerTodos las Investigadores con ObjectDB", ex);
        }
        finally{
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            if (em.isOpen())
                em.close();
        }
    }

}
