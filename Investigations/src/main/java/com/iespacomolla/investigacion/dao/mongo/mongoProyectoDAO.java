

package com.iespacomolla.investigacion.dao.mongo;

import com.iespacomolla.investigacion.dao.DAOException;
import com.iespacomolla.investigacion.dao.ProyectoDAO;
import com.iespacomolla.investigacion.modelo.Campo;
import com.iespacomolla.investigacion.modelo.Entidad;
import com.iespacomolla.investigacion.modelo.Investigador;
import com.iespacomolla.investigacion.modelo.Proyecto;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;


public class mongoProyectoDAO implements ProyectoDAO{

    private MongoDatabase db;
    private MongoCollection<Document> collection;

    public mongoProyectoDAO(MongoDatabase db) {
        this.db = db;
        collection = db.getCollection("Proyecto");
    }
    
    
    @Override
    public void insertar(Proyecto o) throws DAOException {
        
        try{
            Document document = new Document();
            document.put("_id", getNextSequence("proyectoid"));
            document.put("nombre", o.getNombre());
            document.put("capital", o.getCapital());
            document.put("fecha_inicio", o.getFecha_inicio().toString());
            if (o.getCampo() != null)
                document.put("campo", o.getCampo().getCampo_id().doubleValue());
            else
                document.put("campo", 0);
            
                document.put("finalizado", o.isFinalizado());
                document.put("coste", o.getCoste());
            if (o.getFecha_fin() != null)
                document.put("fecha_fin", o.getFecha_fin().toString());
            
            ArrayList<Double> id_entidades = new ArrayList<>();
            for (Entidad e : o.getEntidades()){
                id_entidades.add(e.getEntidad_id().doubleValue());
                
            }
            document.put("entidades", id_entidades);
            
            ArrayList<Double> id_investigadores = new ArrayList<>();
            for (Investigador i : o.getInvestigadores()){
                id_investigadores.add(i.getInvestigador_id().doubleValue());
                
            }
            document.put("investigadores", id_investigadores);
            
            collection.insertOne(document);
            try{
                o.setProyecto_id(document.getDouble("_id").longValue());
            }
            catch(Exception ex){
                throw new DAOException("Error al obtener _id del Investigador con Mongo", ex);
            }
        }
        catch(Exception ex){
            throw new DAOException("Error al insertar Investigador con Mongo", ex);
        }
    }

    @Override
    public void modificar(Proyecto o) throws DAOException {
        try {
            
            ArrayList<Double> id_entidades = new ArrayList<>();
            for (Entidad e : o.getEntidades()){
                id_entidades.add(e.getEntidad_id().doubleValue());
                
            }
            
            ArrayList<Double> id_investigadores = new ArrayList<>();
            for (Investigador i : o.getInvestigadores()){
                id_investigadores.add(i.getInvestigador_id().doubleValue());
                
            }   
            
            Double campo_id = 0.0;
            if (o.getCampo() != null){
                campo_id = o.getCampo().getCampo_id().doubleValue(); 
            }
            String fecha_fin = "";
            if (o.getFecha_fin() != null){
                fecha_fin = o.getFecha_fin().toString();
            }
            
            List<Bson> update = List.of(
                 
                Updates.set("nombre", o.getNombre()),
                Updates.set("capital", o.getCapital()),
                Updates.set("fecha_inicio", o.getFecha_inicio().toString()),
                Updates.set("finalizado", o.isFinalizado()),
                Updates.set("coste", o.getCoste()),
                Updates.set("entidades", id_entidades),
                Updates.set("investigadores", id_investigadores),
                Updates.set("campo", campo_id),
                Updates.set("fecha_fin", fecha_fin) 
            );
            
            //El Documento cuyo id sea el de este objeto, modificas sus campos por estos otros: 
            collection.updateOne(Filters.eq("_id", o.getProyecto_id()), update);
        } catch (Exception ex) {
            throw new DAOException("Error al modificar Proyecto con MongoDB", ex);
        }
    }

    @Override
    public void eliminar(Proyecto o) throws DAOException {
        try{
            DeleteResult result = collection.deleteOne(Filters.eq("_id", o.getProyecto_id()));
            if (result.getDeletedCount() != 1)
                throw new DAOException("Puede que el documento Proyecto no se haya eliminado");
        }
        catch(Exception ex){
            throw new DAOException("Error al eliminar Proyecto con Mongo", ex);
        }
    }

    public Proyecto conversion(Document d, int level) throws DAOException{
        
        Long id = d.getDouble("_id").longValue();
        
        String nombre = d.getString("nombre");
        Double capital = d.getDouble("capital");
        String date1 = d.getString("fecha_inicio");
        LocalDate fi = LocalDate.parse(date1);
        
        List<Entidad> entidades = new ArrayList<>();
        if (level < 1 && d.get("entidades") != null){
            Long entidad_id;
            ArrayList<Double> fK = (ArrayList<Double>) d.get("entidades");
            mongoEntidadDAO eDAO = new mongoEntidadDAO(db);
            for (Double de : fK){
                entidad_id = de.longValue();
                try{
                    entidades.add(eDAO.obtener(entidad_id, 1));
                }
                catch(DAOException ex){
                    throw new DAOException("Error al obtener el Entidad con ID: "+ de
                    + " de este Proyecto");
                }
            }
        }
        List<Investigador> investigadores = new ArrayList<>(); 
        if (level < 1 && d.get("investigadores") != null){
            Long investigador_id;
            ArrayList<Double> fK = (ArrayList<Double>) d.get("investigadores");
            mongoInvestigadorDAO iDAO = new mongoInvestigadorDAO(db);
            for (Double di : fK){
                investigador_id = di.longValue();
                try{
                    investigadores.add(iDAO.obtener(investigador_id, 1));
                }
                catch(DAOException ex){
                    throw new DAOException("Error al obtener el Investigador con ID: "+ di
                    + " de este Proyecto");
                }
            }
        }
        Campo campo = null;
        if (d.containsKey("campo")){
            //Mongo tiene los números como Double
            Double auxId = d.getDouble("campo");
            Long campoId = auxId.longValue();
            try{
                mongoCampoDAO cDAO = new mongoCampoDAO(db);
                campo = cDAO.obtener(campoId, 1);
            }catch(DAOException ex){
                throw new DAOException("Fallo al obtener el Campo del proyecto: "+ nombre, ex);
            }
        }
        boolean finalizado = false;
        if (d.containsKey("finalizado"))        
            finalizado = d.getBoolean("finalizado");
        Double coste = 0.0;
        if (d.containsKey("coste"))
            coste = d.getDouble("coste");
        
        LocalDate ff = null;
        if (d.containsKey("fecha_fin")){
            String date2 = d.getString("fecha_fin");
            ff = LocalDate.parse(date2);
        }
        
        Proyecto p = new Proyecto(nombre, capital, fi, campo, entidades,
                investigadores, finalizado, coste, ff);
        p.setProyecto_id(id);
        return p;
    }
    
    @Override
    public Proyecto obtener(Long id, int level) throws DAOException {
        Proyecto proyecto = null;
        try {
            Document document = collection.find(Filters.eq("_id", id)).first();
            if (document != null) {
                proyecto = conversion(document, level);
            }
        } catch (Exception ex) {
            throw new DAOException("Error al obtener Proyecto con MongoDB", ex);
        }
        return proyecto;
    }

    @Override
    public List<Proyecto> obtenerTodos() throws DAOException {
        
        List<Proyecto> proyectos = new ArrayList<>();
        try {
            MongoCursor<Document> cursor = collection.find().iterator();
            while (cursor.hasNext())
                proyectos.add(conversion(cursor.next(),0));
            
        } catch (Exception ex) {
            throw new DAOException( ex);
        }
        return proyectos;
    }
    
    
    //Función para autogenerar _id incremental y númerico:
    private Object getNextSequence(String name) throws Exception{
        
        MongoCollection contadores = db.getCollection("counters");

        Document find = new Document();
        find.put("_id", name);
        
        Document update = new Document();
        update.put("$inc", new Document("seq", 1));
         
        Document obj = (Document) contadores.findOneAndUpdate(find, update); 
        return obj.get("seq");   
    }

}
