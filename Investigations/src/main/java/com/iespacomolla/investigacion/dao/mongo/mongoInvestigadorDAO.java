
package com.iespacomolla.investigacion.dao.mongo;

import com.iespacomolla.investigacion.dao.DAOException;
import com.iespacomolla.investigacion.dao.InvestigadorDAO;
import com.iespacomolla.investigacion.modelo.Campo;
import com.iespacomolla.investigacion.modelo.Investigador;
import com.iespacomolla.investigacion.modelo.Proyecto;
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


public class mongoInvestigadorDAO implements InvestigadorDAO{
    
    private MongoDatabase db;
    private MongoCollection<Document> collection;

    public mongoInvestigadorDAO(MongoDatabase db) {
        this.db = db;
        collection = db.getCollection("Investigador");
    }
    
    
    @Override
    public void insertar(Investigador o) throws DAOException {
        try{
            Document document = new Document();
            document.put("_id", getNextSequence("investigadorid"));
            if (o.getProyecto() != null){
                document.put("proyecto_id", o.getProyecto().getProyecto_id().doubleValue());
            }
            else 
                document.put("proyecto_id", 0);
            document.put("nombre", o.getNombre());
            document.put("titulo", o.getTitulo());
            document.put("salario", o.getSalario());
            
            //Insertar un array con las referencias al ID de los Campo asociados:
            ArrayList<Double> id_campos = new ArrayList<>();
            for (Campo c : o.getCampos()){
                id_campos.add(c.getCampo_id().doubleValue());
                
            }
            document.put("campos", id_campos);
            
            collection.insertOne(document);
            try{
                o.setInvestigador_id(document.getDouble("_id").longValue());
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
    public void modificar(Investigador o) throws DAOException {
        try {
            ArrayList<Double> id_campos = new ArrayList<>();
            
            for (Campo c : o.getCampos()){
                id_campos.add(c.getCampo_id().doubleValue());
            }
            Double proyecto_id = 0.0;
            
            if (o.getProyecto() != null){
                proyecto_id = o.getProyecto().getProyecto_id().doubleValue();
            }
            List<Bson> update = List.of(   
                Updates.set("nombre", o.getNombre()),
                Updates.set("titulo", o.getTitulo()),
                Updates.set("salario", o.getSalario()),
                Updates.set("campos", id_campos),
                Updates.set("proyectos", proyecto_id)
            );
              
            //El Documento cuyo id sea el de este objeto, modificas sus campos por estos otros: 
            collection.updateOne(Filters.eq("_id", o.getInvestigador_id()), update);
        } catch (Exception ex) {
            throw new DAOException("Error al modificar Investigador con MongoDB", ex);
        }
    }

    @Override
    public void eliminar(Investigador o) throws DAOException {
        try{
            DeleteResult result = collection.deleteOne(Filters.eq("_id", o.getInvestigador_id()));
            if (result.getDeletedCount() != 1)
                throw new DAOException("Puede que el documento Investigador no se haya eliminado");
        }
        catch(Exception ex){
            throw new DAOException("Error al eliminar Investigador con Mongo", ex);
        }
    }
    
    private Investigador conversion(Document d, int level) throws DAOException{
        
        Long id = d.getDouble("_id").longValue();
        String nombre = d.getString("nombre");
        Proyecto p = null;
        if (d.containsKey("proyecto_id")){
            Double auxId = d.getDouble("proyecto_id");
            Long proyecto_id = auxId.longValue();
            try{
                mongoProyectoDAO pDAO = new mongoProyectoDAO(db);
                p = pDAO.obtener(proyecto_id, 0); 
            }catch(DAOException ex){
                throw new DAOException("Fallo al obtener el proyecto del Investigador: "+ nombre, ex);
            }
        }
            
        String titulo = d.getString("titulo");
        Double salario = d.getDouble("salario");
        
        List<Campo> campos = new ArrayList<>();
        if (level < 1 && d.get("campos") != null){
            
            List<Double> camposId = (ArrayList<Double>) d.get("campos");
            Long campo_id;
            mongoCampoDAO cDAO = new mongoCampoDAO(db);
            
            for (Double dc : camposId){
                campo_id = dc.longValue();
                try{
                    campos.add(cDAO.obtener(campo_id, 1));
                }catch(DAOException ex){
                    throw new DAOException("Error al obtener el campo con ID: "+ dc
                    + " de este Investigador");
                } 
            }
                       
        }
        
        Investigador i = new Investigador(nombre, titulo, salario, p, campos);
        i.setInvestigador_id(id);
        return i;
    }
    
    @Override
    public Investigador obtener(Long id, int level) throws DAOException {
        
        Investigador investigador = null;
        try {
            Document document = collection.find(Filters.eq("_id", id)).first();
            if (document != null) {
                investigador = conversion(document, level);
            }
        } catch (DAOException ex) {
            throw new DAOException("Error al obtener Investigador con MongoDB", ex);
        }
        return investigador;
    }

    @Override
    public List<Investigador> obtenerTodos() throws DAOException {
        
        List<Investigador> investigador = new ArrayList<>();
        try {
            MongoCursor<Document> cursor = collection.find().iterator();
            while (cursor.hasNext())
                investigador.add(conversion(cursor.next(), 0));
            
        } catch (Exception ex) {
            throw new DAOException("Error al obtenerTodos Investigador con MongoDB", ex);
        }
        return investigador;
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
