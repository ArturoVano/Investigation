
package com.iespacomolla.investigacion.dao.mongo;

import com.iespacomolla.investigacion.dao.CampoDAO;
import com.iespacomolla.investigacion.dao.DAOException;
import com.iespacomolla.investigacion.modelo.Campo;
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


public class mongoCampoDAO implements CampoDAO{
    
    private MongoDatabase db;
    private MongoCollection<Document> collection;

    public mongoCampoDAO(MongoDatabase db) {
        this.db = db;     
        collection = db.getCollection("Campo");
    }
    
    
    @Override
    public void insertar(Campo o) throws DAOException {
        try {
            Document document = new Document();
            document.put("_id", getNextSequence("campoid"));
            document.put("nombre", o.getNombre());
            document.put("ramal", o.getRamal());
            collection.insertOne(document);
            //Obtengo el id que me autogeneró mongo:
            try{
                o.setCampo_id(document.getDouble("_id").longValue());
            }
            catch(Exception ex){
                throw new DAOException("Fallo al asignar ID a este Campo con Mongo", ex);
            }
            
        } catch (Exception ex) {
            throw new DAOException("Error al insertar Campo con MongoDB", ex);
        }
    }

    @Override
    public void modificar(Campo o) throws DAOException {
        
        try {
            List<Bson> update = List.of(
                Updates.set("nombre", o.getNombre()),
                Updates.set("ramal", o.getRamal())
            );
            //El Documento cuyo id sea el de este objeto, modificas sus campos por estos otros: 
            collection.updateOne(Filters.eq("_id", o.getCampo_id()), update);
        } catch (Exception ex) {
            throw new DAOException("Error al modificar Campo con MongoDB", ex);
        }
    }

    @Override
    public void eliminar(Campo o) throws DAOException {
        try {
            DeleteResult result = collection.deleteOne(Filters.eq("_id", o.getCampo_id()));
            if (result.getDeletedCount() != 1)
                throw new DAOException("Puede que el documento Campo no se haya eliminado");
            
        } catch (Exception ex) {
            throw new DAOException("Error al eliminar Campo con MongoDB", ex);
        }
    }
    
    public Campo conversion(Document d){
        
        //String idConv = d.getString("_id");
        //Long id = Long.parseLong("_id");
        Long id = d.getDouble("_id").longValue();
        String nombre = d.getString("nombre");
        String ramal = d.getString("ramal");
        Campo c = new Campo(nombre, ramal);
        c.setCampo_id(id);
        return c;
        
    }
    
    @Override
    public Campo obtener(Long id, int level) throws DAOException {
        
        Campo campo = null;
        try {
            Document document = collection.find(Filters.eq("_id", id)).first();
            if (document != null) {
                campo = conversion(document);
            }
        } catch (Exception ex) {
            throw new DAOException("Error al obtener Campo con MongoDB", ex);
        }
        return campo;
    }

    @Override
    public List<Campo> obtenerTodos() throws DAOException {
        
        List<Campo> campos = new ArrayList<>();
        try {
            MongoCursor<Document> cursor = collection.find().iterator();
            while (cursor.hasNext())
                campos.add(conversion(cursor.next()));
            
        } catch (Exception ex) {
            throw new DAOException("Error al obtenerTodos los Campos con MongoDB", ex);
        }
        return campos;
    }

    //Función para autogenerar _id incremental y númerico:
    /*public Object getNextSequence(String name) throws Exception{
        
        DBCollection oldCollection = (DBCollection) db.getCollection("counters");

        BasicDBObject find = new BasicDBObject();
        find.put("_id", name);
        
        BasicDBObject update = new BasicDBObject();
        update.put("$inc", new Document("seq", 1));
         
        DBObject obj =  oldCollection.findAndModify(find, update);
        return obj.get("seq");
       //Se debe tener una coleccion counters que rastree la última secuencia usada.  
    }*/
    
    private Object getNextSequence(String name) throws Exception{
        
        MongoCollection contadores = db.getCollection("counters");

        Document find = new Document();
        find.put("_id", name);
        
        Document update = new Document();
        update.put("$inc", new Document("seq", 1));
         
        Document obj = (Document) contadores.findOneAndUpdate(find, update); 
        return obj.get("seq");
       //Se debe tener una coleccion counters que rastree la última secuencia usada.  
    }
}
