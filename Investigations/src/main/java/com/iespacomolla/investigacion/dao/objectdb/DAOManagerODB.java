

package com.iespacomolla.investigacion.dao.objectdb;

import com.iespacomolla.investigacion.dao.CampoDAO;

import com.iespacomolla.investigacion.dao.DAOManager;
import com.iespacomolla.investigacion.dao.EntidadDAO;
import com.iespacomolla.investigacion.dao.InvestigadorDAO;
import com.iespacomolla.investigacion.dao.ProyectoDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class DAOManagerODB implements DAOManager{
    
    private CampoDAO campo = null;
    private EntidadDAO entidad = null;
    private InvestigadorDAO investigador = null;
    private ProyectoDAO proyecto = null;
    
    private EntityManagerFactory emf;

    public DAOManagerODB() {
        if (emf == null) 
            emf = Persistence.createEntityManagerFactory("objectdb:db/nba.odb");
        
    }
    
    
    @Override
    public CampoDAO getCampoDAO() {
        if (campo == null){
            campo = new CampoODBDAO(emf);
        }
        return campo;
    }

    @Override
    public EntidadDAO getEntidadDAO() {
        if (entidad == null){
            entidad = new EntidadODBDAO(emf);
        }
        return entidad;
    }

    @Override
    public InvestigadorDAO getInvestigadorDAO() {
        if (investigador == null){
            investigador = new InvestigadorODBDAO(emf);
        }
        return investigador;
    }

    @Override
    public ProyectoDAO getProyectoDAO() {
        if (proyecto == null){
            proyecto = new ProyectoODBDAO(emf);
        }
        return proyecto;
    }
    
    
    public EntityManagerFactory getEntityManager() {
        return emf;
    }

    // Cierra recursos
    public void closeConnection() {
        emf.close();
    }
    
    /*public static void main(String[] args) throws DAOException{
        DAOManagerODB maestro = new DAOManagerODB();
        
        System.out.println(" CAMPOS ");
        Campo c1 = new Campo("Computacion", "Ingenieria");c1.setCampo_id(1);
        Campo c2 = new Campo("Algoritmos", "Matematicas");c2.setCampo_id(2);
        //maestro.getCampoDAO().insertar(c1);
        //maestro.getCampoDAO().insertar(c2);
        List<Campo> campos = maestro.getCampoDAO().obtenerTodos();
        for (Campo c : campos){  
            System.out.println("ID: "+c.getCampo_id()+ " NOMBRE: " + c.getNombre());
        }
        
        System.out.println(" Entidad ");
        Entidad e1 = new Entidad("Paco Molla", "Petrer, Alicante", null); e1.setEntidad_id(1);
        Entidad e2 = new Entidad("Everis", "Madrid", null);e2.setEntidad_id(2);
        maestro.getEntidadDAO().insertar(e1);
        maestro.getEntidadDAO().insertar(e2);
        List <Entidad> entidades = maestro.getEntidadDAO().obtenerTodos();
        for (Entidad e : entidades){
            System.out.println("ID: "+e.getEntidad_id()+ " NOMBRE: " + e.getNombre());
        }
        
        System.out.println(" Proyecto "); List<Entidad> es1 = new ArrayList<>(); es1.add(e1);
        
        Proyecto p1 = new Proyecto("Flujo de hilos", 30000.0, LocalDate.parse("2021-01-01"), null, es1, null, false, null, null);p1.setProyecto_id(1);
        es1.set(0, e2);
        Proyecto p2 = new Proyecto("Algoritmo de hilos", 40000.0, LocalDate.parse("2021-02-01"), null, es1, null, false, null, null);p2.setProyecto_id(2);
        es1.set(0, e1);
        Proyecto p3 = new Proyecto("Data Access Object", 10000.0, LocalDate.parse("2021-01-01"), null, es1, null, true, 9000.0, LocalDate.parse("2021-02-24"));p3.setProyecto_id(3);
        //maestro.getProyectoDAO().insertar(p1); 
        //maestro.getProyectoDAO().insertar(p2);
        //maestro.getProyectoDAO().insertar(p3);
        List <Proyecto> proyectos = maestro.getProyectoDAO().obtenerTodos();
        for (Proyecto e : proyectos){
            System.out.println("ID: "+e.getProyecto_id()+ " NOMBRE: " + e.getNombre());
        }
        
        System.out.println(" Investigador ");
        List<Campo> cs1 = new ArrayList<>(); cs1.add(c2);
        Investigador i1 = new Investigador("Paco", "Matematico", 1500.0, p1, cs1);i1.setInvestigador_id(1);
        cs1.set(0, c1);
        Investigador i2 = new Investigador("Arturo", "Ingeniero informatico", 1200.0, p3,cs1);i2.setInvestigador_id(2);
        cs1.set(0, c2);
        Investigador i3 = new Investigador("DaviJuan", "Fisico", 1400.0, p1,cs1);i3.setInvestigador_id(3);
        //maestro.getInvestigadorDAO().insertar(i1);
        //maestro.getInvestigadorDAO().insertar(i2);
        //maestro.getInvestigadorDAO().insertar(i3);
        maestro.getInvestigadorDAO().modificar(i3);
        maestro.getInvestigadorDAO().modificar(i2);
        maestro.getInvestigadorDAO().modificar(i3);
        List <Investigador> investigadores = maestro.getInvestigadorDAO().obtenerTodos();
        for (Investigador i : investigadores){
            
            System.out.println("ID: "+i.getInvestigador_id()+ " NOMBRE: " + i.getNombre());
        }
    }*/
}
