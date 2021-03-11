

package com.iespacomolla.investigacion.dao.mongo;

import com.iespacomolla.investigacion.dao.DAOException;
import com.iespacomolla.investigacion.dao.EntidadDAO;
import com.iespacomolla.investigacion.modelo.Entidad;
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
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;


public class mongoEntidadDAO implements EntidadDAO{
    
    private MongoDatabase db;
    private MongoCollection<Document> collection;

    public mongoEntidadDAO(MongoDatabase db) {
        this.db = db;
        collection = db.getCollection("Entidad");
    }
    
    
    @Override
    public void insertar(Entidad o) throws DAOException {
        
        try{

            Document document = new Document();
            document.put("_id", getNextSequence("entidadid"));
            document.put("nombre", o.getNombre());
            document.put("ubicacion", o.getUbicacion());
            
            //Solo tendrá id y nombre de los proyectos financiados por esta Entidad
            ArrayList<Double> id_proyectos = new ArrayList<>();
            for (Proyecto p : o.getProyectos()){
                id_proyectos.add(p.getProyecto_id().doubleValue());
                
            }
            document.put("proyectos", id_proyectos);
            
            collection.insertOne(document);
            
            //Obtengo el id que me autogeneró mongo:
            try{
                o.setEntidad_id(document.getDouble("_id").longValue());
            }
            catch(Exception ex){
                throw new DAOException("Fallo al asignar ID a esta Entidad con Mongo", ex);
            }
        }
        catch(Exception ex){
            throw new DAOException("Fallo al insertar una Entidad con Mongo", ex);
        }
    }

    @Override
    public void modificar(Entidad o) throws DAOException {
        
        try {
            
            ArrayList<Double> id_proyectos = new ArrayList<>();
            
            for (Proyecto p : o.getProyectos()){
                id_proyectos.add(p.getProyecto_id().doubleValue());
            }
            
            List<Bson> update = List.of(
                Updates.set("nombre", o.getNombre()),
                Updates.set("ubicacion", o.getUbicacion()),
                Updates.set("proyectos", id_proyectos)
            );
            //El Documento cuyo id sea el de este objeto, modificas sus campos por estos otros: 
            collection.updateOne(Filters.eq("_id", o.getEntidad_id()), update);
        } catch (Exception ex) {
            throw new DAOException("Error al modificar Entidad con MongoDB", ex);
        }
    }

    @Override
    public void eliminar(Entidad o) throws DAOException {
        
        try {
            DeleteResult result = collection.deleteOne(Filters.eq("_id", o.getEntidad_id()));
            if (result.getDeletedCount() != 1)
                throw new DAOException("Puede que el documento Entidad no se haya eliminado");
            
        } catch (Exception ex) {
            throw new DAOException("Error al eliminar Entidad con MongoDB", ex);
        }
    }
    
    
    private Entidad conversion(Document d, int level) throws DAOException{
        
        Long id = d.getDouble("_id").longValue();
        String nombre = d.getString("nombre");
        String ubicacion = d.getString("ubicacion");
        
        //Código para obtener los proyectos:
        List<Proyecto> proyectos = new ArrayList<>();
        if (level < 1 && d.get("proyectos") != null){
            
            Long proyecto_id;
            ArrayList<Double> fK = (ArrayList<Double>) d.get("proyectos");
            //Document embedDoc = d.get("proyectos", Document.class);
            mongoProyectoDAO pDAO = new mongoProyectoDAO(db);
            
            for (int i = 0; i < fK.size(); i++){
                proyecto_id = fK.get(i).longValue();
                try{
                    proyectos.add(pDAO.obtener(proyecto_id, 1));
                }catch(DAOException ex){
                    throw new DAOException("Error al obtener el proyecto con ID: "+ proyecto_id
                    + " de esta Entidad");
                }
            }
        }
        
        Entidad e = new Entidad(nombre, ubicacion, proyectos);
        e.setEntidad_id(id);
        return e;
    }
    
    @Override
    public Entidad obtener(Long id, int level) throws DAOException {
        
        Entidad entidad = null;
        try {
            Document document = collection.find(Filters.eq("_id", id)).first();
            if (document != null) {
                entidad = conversion(document, level);
            }
        } catch (Exception ex) {
            throw new DAOException("Error al obtener Entidad con MongoDB", ex);
        }
        return entidad;
    }

    @Override
    public List<Entidad> obtenerTodos() throws DAOException {
        
        List<Entidad> entidades = new ArrayList<>();
        try {
            MongoCursor<Document> cursor = collection.find().iterator();
            while (cursor.hasNext())
                entidades.add(conversion(cursor.next(), 0));
            
        } catch (Exception ex) {
            throw new DAOException("Error al obtener Todas las Entidades con MongoDB", ex);
        }
        return entidades;
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
