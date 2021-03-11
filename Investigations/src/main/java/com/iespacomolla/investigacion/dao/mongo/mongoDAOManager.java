
package com.iespacomolla.investigacion.dao.mongo;

import com.iespacomolla.investigacion.dao.CampoDAO;
import com.iespacomolla.investigacion.dao.DAOManager;
import com.iespacomolla.investigacion.dao.EntidadDAO;
import com.iespacomolla.investigacion.dao.InvestigadorDAO;
import com.iespacomolla.investigacion.dao.ProyectoDAO;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoSocketException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.net.UnknownHostException;
import java.util.List;
import org.bson.Document;


public class mongoDAOManager implements DAOManager{
    
    private CampoDAO campo = null;
    private EntidadDAO entidad = null;
    private InvestigadorDAO investigador = null;
    private ProyectoDAO proyecto = null;
    
    private MongoClient client;
    private MongoDatabase db;
    
    public mongoDAOManager(String host, String user, String pass) throws Exception {
        
        //client = new MongoClient(new MongoClientURI("mongodb://"+ user + ":" + pass +"@" + host + ":27017"));
        client = new MongoClient(new MongoClientURI("mongodb://" + host + ":27017"));
        db = client.getDatabase("investigacion");
        
        //Comprobar si no hay problemas para acceder al Servidor y si nunca se ha ejecutado este programa
        // programa, se creará la coleccion necesaria para auto-generar los IDs
        try{
            MongoCollection<Document> collection = db.getCollection("counters");
            
            if (collection.countDocuments() < 4){

                List<Document> counters = List.of(
                    new Document().append("_id", "campoid").append("seq", 0),
                    new Document().append("_id", "entidadid").append("seq", 0),
                    new Document().append("_id", "investigadorid").append("seq", 0),
                    new Document().append("_id", "proyectoid").append("seq", 0)
                );
                collection.insertMany(counters);
            }
        }
        catch(MongoSocketException ex){
            throw new Exception(ex.getMessage() + " | " + ex.getCause());
        }
      
    }
    
    
    
    
    @Override
    public CampoDAO getCampoDAO() {
        if (campo == null){
            campo = new mongoCampoDAO(db);
        }
        return campo;
    }

    @Override
    public EntidadDAO getEntidadDAO() {
        if (entidad == null){
            entidad = new mongoEntidadDAO(db);
        }
        return entidad;
    }
    
    @Override
    public InvestigadorDAO getInvestigadorDAO() {
        if (investigador == null){
            investigador = new mongoInvestigadorDAO(db);
        }
        return investigador;
    }

    @Override
    public ProyectoDAO getProyectoDAO() {
        if (proyecto == null){
            proyecto = new mongoProyectoDAO(db);
        }
        return proyecto;
    }


    public MongoDatabase getConnection() {
        return db;
    }

    public void close() {
        db = null;
        client.close();
        client = null;
    }
}
