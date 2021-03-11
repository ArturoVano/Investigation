
package com.iespacomolla.investigacion.controlador;


import com.iespacomolla.investigacion.dao.DAOException;
import com.iespacomolla.investigacion.dao.DAOManager;
import com.iespacomolla.investigacion.dao.mongo.mongoDAOManager;
import com.iespacomolla.investigacion.dao.mysql.hbn.mysqlDAOManagerHbn;
import com.iespacomolla.investigacion.dao.mysql.jdbc.mysqlDAOManagerJdbc;
import com.iespacomolla.investigacion.dao.objectdb.DAOManagerODB;
import com.iespacomolla.investigacion.modelo.Campo;
import com.iespacomolla.investigacion.modelo.Entidad;
import com.iespacomolla.investigacion.modelo.Investigador;
import com.iespacomolla.investigacion.modelo.Proyecto;
import com.iespacomolla.investigacion.vista.MenuPrincipal;
import com.iespacomolla.investigacion.vista.Tablas;
import com.iespacomolla.investigacion.vista.campo.TablaCamposFrame;
import com.iespacomolla.investigacion.vista.entidad.TablaEntidadesFrame;
import com.iespacomolla.investigacion.vista.investigador.TablaInvestigadoresFrame;
import com.iespacomolla.investigacion.vista.proyecto.TablaProyectoFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.System.exit;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;


public class Controlador implements ActionListener{

    private DAOManager maestro;
    private MenuPrincipal menu;
    private Tablas tablas;
    
    private JButton btnAbreCampo;
    private JButton btnAbreEntidad;
    private JButton btnAbreInvestigador;
    private JButton btnAbreProyecto;
    
    private JButton btnGuardar;
    private JButton btnBorrar;
    
    
    public Controlador(String h, String u, String p, int d) {
        
        if (d == 0){ //Si se ha elegido JDBC
            try{
            maestro = new mysqlDAOManagerJdbc(h, u, p, "investigation");
            }
            catch(SQLException ex){
                ex.printStackTrace();
                JOptionPane.showMessageDialog(menu,"Error al instanciar Manager de Mysql con JDBC: " 
                        + ex.getMessage());
                exit(0);
            }
        }
        else if (d == 1){ //Si se ha elegido Hibernate
            try{
                maestro = new mysqlDAOManagerHbn();
            }
            catch(DAOException ex){
                JOptionPane.showMessageDialog(menu,"Error al Manager de Hibernate: " 
                        + ex.getMessage());
                exit(0);
            }
        }
        else if (d == 2){ //Si se ha elegido ObjectDB
            maestro = new DAOManagerODB();
            
            
        }
        else if (d == 3){ //Si se ha elegido MongoDB
            try {
                maestro = new mongoDAOManager(h, u, p);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(menu,"Error al instanciar Manager de MongoDB: " 
                        + ex.getMessage());
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                exit(0);
            }
        }
        
        menu = new MenuPrincipal();
        menu.setVisible(true);
        
        btnAbreCampo = menu.getAbreTablaCampo();
        btnAbreEntidad = menu.getAbreTablaEntidad();
        btnAbreInvestigador = menu.getAbreTablaInvestigador();
        btnAbreProyecto = menu.getAbreTablaProyecto();
        
        btnAbreCampo.addActionListener(this);
        btnAbreEntidad.addActionListener(this);
        btnAbreInvestigador.addActionListener(this);
        btnAbreProyecto.addActionListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == btnAbreCampo){
                   
            try{
                List<Campo> campos = maestro.getCampoDAO().obtenerTodos();
                tablas = new TablaCamposFrame(menu, campos);
                tablas.setVisible(true);
                //reasigno los botones, pues ahora serán los de TablaCamposFrame:
                asignaBotonesCRUD();
            }
            catch(DAOException ex){
                JOptionPane.showMessageDialog(menu,"Error al cargar tabla Campo y sus datos: " 
                        + ex.getMessage());
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
        else if (e.getSource() == btnAbreEntidad){
            
            try{
                List<Entidad> entidades = maestro.getEntidadDAO().obtenerTodos();
                tablas = new TablaEntidadesFrame(menu, entidades);
                tablas.setVisible(true);
                //reasigno los botones, pues ahora serán los de TablaEntidadFrame:
                asignaBotonesCRUD();

            }
            catch(DAOException ex){
                JOptionPane.showMessageDialog(menu,"Error al cargar  tabla Entidad y sus datos: " 
                        + ex.getMessage());
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        else if (e.getSource() == btnAbreInvestigador){
            
            try{
                List<Investigador> investigadores = maestro.getInvestigadorDAO().obtenerTodos();
                tablas = new TablaInvestigadoresFrame(menu, investigadores);
                tablas.setVisible(true);
                //reasigno los botones, pues ahora serán los de TablaInvestigadorFrame:
                asignaBotonesCRUD();
            }
            catch(DAOException ex){
                JOptionPane.showMessageDialog(menu,"Error al cargar tabla Investigador y sus datos: " 
                        + ex.getMessage());
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
            try{
                List<Proyecto> proyecsForResearcher = maestro.getProyectoDAO().obtenerTodos();
                tablas.proyectoComboModel(proyecsForResearcher);
            }
            catch(DAOException ex){
                JOptionPane.showMessageDialog(menu,"Error al cargar proyectos para los Investigadores.");
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        else if (e.getSource() == btnAbreProyecto){


            
            try{
                List<Proyecto> proyectos = maestro.getProyectoDAO().obtenerTodos();
                tablas = new TablaProyectoFrame(menu, proyectos);
                tablas.setVisible(true);
                //reasigno los botones, pues ahora serán los de TablaProyectoFrame:
                asignaBotonesCRUD();
            }
            catch(DAOException ex){
                JOptionPane.showMessageDialog(menu,"Error al cargar tabla Proyecto y sus datos: " 
                        + ex.getMessage());
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
            try{
                List<Campo> campForProyect = maestro.getCampoDAO().obtenerTodos();
                tablas.campoComboModel(campForProyect);
            }
            catch(DAOException ex){
                JOptionPane.showMessageDialog(menu,"Error al cargar campos para los proyectos.");
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        else if (e.getSource() == btnGuardar){
            
            
            if (tablas.getDatosDetalle() != null){
                //Tenemos que recibir de las vistas los datos que quieren que guardemos:
                Map<String,String> nDatos = tablas.getDatosDetalle();

                if (tablas.getClass().equals(TablaCamposFrame.class)){
                    //Si no contiene un id, no existe y lo creamos
                    if (!nDatos.containsKey("campo_id")){ 
                        
                        //Crear objeto Campo e insertarlo
                        Campo c = new Campo(nDatos.get("nombre"), nDatos.get("ramal"));
                        try {
                            maestro.getCampoDAO().insertar(c);
                            JOptionPane.showMessageDialog(menu,"Campo " + c.getNombre() + " con ID: " + c.getCampo_id() +
                        " ha sido insertado con éxito.");
                            
                        } catch (DAOException ex) {
                            JOptionPane.showMessageDialog(menu,"Error al insertar objeto Campo.");
                            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        
                    }
                    //Si contiene un id, ya existe y lo modificamos:
                    else if (nDatos.containsKey("campo_id")){
                        
                        Campo c = new Campo(nDatos.get("nombre"), nDatos.get("ramal"));
                        try{
                            c.setCampo_id(Long.parseLong(nDatos.get("campo_id")));
                        }
                        catch(NumberFormatException ex){
                            
                            JOptionPane.showMessageDialog(menu,"Error con el ID del campo a modificar.");
                        }
                        try {
                            maestro.getCampoDAO().modificar(c);
                            JOptionPane.showMessageDialog(menu,"Campo " + c.getNombre() + " con ID: " + c.getCampo_id() +
                                " ha sido modificado con éxito.");
                            
                        } catch (DAOException ex) {
                            
                            JOptionPane.showMessageDialog(menu, ex.getMessage());  
                            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        
                    }
                    //Lo haya insertado o modificado, refrescar el TableModel y re-enviarlo a la vista:

                    try {
                        
                        List<Campo> campos = maestro.getCampoDAO().obtenerTodos();
                        tablas.updateTableModel(campos);

                    } catch (DAOException ex) {
                        Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(menu,"Error al actualizar el CampoTableModel. "
                        + ex.getMessage());  
                    }    
                        
                }
                else if (tablas.getClass().equals(TablaEntidadesFrame.class)){
                    
                    List<Proyecto> proyectos = new ArrayList<>(); 
                    
                    Entidad ent = new Entidad(nDatos.get("nombre"), nDatos.get("ubicacion"), proyectos);
                    
                    if (!nDatos.containsKey("entidad_id")){
                        try {
                            maestro.getEntidadDAO().insertar(ent);
                            JOptionPane.showMessageDialog(menu,"La entidad " + ent.getNombre() + " con ID: "+ 
                                ent.getEntidad_id() + " ha sido insertada con éxito");
                        
                        } catch (DAOException ex) {
                            JOptionPane.showMessageDialog(menu,"Error al insertar Entidad: " + ex.getMessage());
                            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    else if (nDatos.containsKey("entidad_id")){
                        
                        try{
                            ent.setEntidad_id(Long.parseLong(nDatos.get("entidad_id")));
                        }
                        catch(NumberFormatException ex){
                            JOptionPane.showMessageDialog(menu,"Error con el ID de Entidad a modificar.");
                        }
                        try {
                            maestro.getEntidadDAO().modificar(ent);
                            JOptionPane.showMessageDialog(menu,"Entidad " + ent.getNombre() + " con ID: " + ent.getEntidad_id() +
                                " ha sido modificado con éxito.");  
                        } catch (DAOException ex) {
                            JOptionPane.showMessageDialog(menu, ex.getMessage());  
                            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } 
                    //Lo haya insertado o modificado, refrescar el TableModel y re-enviarlo a la vista:
                    try {
                        List<Entidad> entidades = maestro.getEntidadDAO().obtenerTodos();
                        tablas.updateTableModel(entidades);

                    } catch (DAOException ex) {
                        Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(menu,"Error al actualizar el EntidadTableModel. "
                        + ex.getMessage());  
                    } 
                    
                }
                else if (tablas.getClass().equals(TablaInvestigadoresFrame.class)){
                    
                    
                    Double salario = null;
                    Proyecto ProyectOfResearcher = null;
                    List<Campo> campos = new ArrayList<>();
                    try{
                        salario = Double.parseDouble(nDatos.get("salario"));                     
                    }catch(NumberFormatException ex){
                        JOptionPane.showMessageDialog(menu,"Error al parsear salario del Investigador.");
                        Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try{
                        Long idProyecto = Long.parseLong(nDatos.get("proyecto_id"));
                        ProyectOfResearcher = maestro.getProyectoDAO().obtener(idProyecto, 0);
                    }
                    catch(NumberFormatException ex){/*tendra proyecto a null*/ }
                    catch(DAOException ex){
                        JOptionPane.showMessageDialog(menu,"Error obtener proyecto del Investigador " + ex.getMessage());
                        Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    Investigador i = new Investigador(nDatos.get("nombre"), nDatos.get("titulo"), salario,
                        ProyectOfResearcher, campos);
                    //No contenía ID, por lo que el usuario quiere insertarlo.
                    if (!nDatos.containsKey("investigador_id")){
                        
                        try{
                            maestro.getInvestigadorDAO().insertar(i);
                            JOptionPane.showMessageDialog(menu,"El investigador " + i.getNombre() + " con ID: "+ 
                                i.getInvestigador_id() + " ha sido insertado con éxito");
                            
                        }catch(DAOException ex){
                            JOptionPane.showMessageDialog(menu,"Error al insertar Investigador: " + ex.getMessage());
                            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    //Ya contiene un ID, por tanto existe y quieren modificarlo.
                    else{
                        
                        try{
                            i.setInvestigador_id(Long.parseLong(nDatos.get("investigador_id")));
                        }
                        catch(NumberFormatException ex){
                            JOptionPane.showMessageDialog(menu,"Error con el ID de Investigador a modificar.");
                            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            maestro.getInvestigadorDAO().modificar(i);
                            JOptionPane.showMessageDialog(menu,"Investigador " + i.getNombre() + " con ID: " + i.getInvestigador_id() +
                                " ha sido modificado con éxito.");  
                        } catch (DAOException ex) {
                            JOptionPane.showMessageDialog(menu, ex.getMessage());  
                            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    //Lo haya insertado o modificado, refrescar el TableModel y re-enviarlo a la vista:
                    try {
                        List<Investigador> investigadores = maestro.getInvestigadorDAO().obtenerTodos();
                        tablas.updateTableModel(investigadores);

                    } catch (DAOException ex) {
                        Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(menu,"Error al actualizar el InvestigadorTableModel. "
                        + ex.getMessage());  
                    }
                }
                else if (tablas.getClass().equals(TablaProyectoFrame.class)){
                    
                    String nom = nDatos.get("nombre");
                    
                    Double capital = 0.0;
                    try{
                        capital = Double.parseDouble(nDatos.get("capital"));
                    }catch(Exception ex){}
                    
                    LocalDate fecha_inicio = null;
                    try{
                        fecha_inicio = LocalDate.parse(nDatos.get("fecha_inicio"));
                    }
                    catch(DateTimeParseException ex){ //Cojo la fecha actual
                        fecha_inicio = LocalDate.now();
                    }
                    
                    long idCampo = Long.parseLong(nDatos.get("campo_id"));
                    Campo campoProyect = null;
                    try {
                        campoProyect = maestro.getCampoDAO().obtener(idCampo, 0);
                    } catch (DAOException ex) {
                        Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(menu,"No se ha podido obtener el campo del Proyecto. ");
                    }
                    List<Entidad> entidades = new ArrayList<>();
                    List<Investigador> investigadores = new ArrayList<>();
                    Boolean finalizado = Boolean.parseBoolean(nDatos.get("finalizado"));
                    
                    Double coste = 0.0;
                    try{
                        coste = Double.parseDouble(nDatos.get("coste"));
                    }catch(Exception ex){}
                    
                    LocalDate fecha_fin = null;
                    try{
                        fecha_fin = LocalDate.parse(nDatos.get("fecha_fin"));
                    }catch(Exception ex){ fecha_fin = LocalDate.now(); }

                    Proyecto p = new Proyecto(nom, capital, fecha_inicio, campoProyect, entidades, investigadores,
                    finalizado, coste, fecha_fin);
                    
                    if (!nDatos.containsKey("proyecto_id")){
                        
                        try{
                            maestro.getProyectoDAO().insertar(p);
                            JOptionPane.showMessageDialog(menu,"El Proyecto " + p.getNombre() + " con ID: " + p.getProyecto_id()
                                + " ha sido insertado con éxito");
                        }
                        catch(DAOException ex){
                            JOptionPane.showMessageDialog(menu,"Fallo al insertar el Proyecto " + p.getNombre()+ ". ", 
                                    ex.getMessage(), JOptionPane.ERROR_MESSAGE);
                            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    else{
                        try{
                            p.setProyecto_id(Long.parseLong(nDatos.get("proyecto_id")));
                        }
                        catch(NumberFormatException ex){
                            JOptionPane.showMessageDialog(menu,"Error con el ID de Proyecto a modificar.");
                        }
                        try {
                            maestro.getProyectoDAO().modificar(p);
                            JOptionPane.showMessageDialog(menu,"Proyecto " + p.getNombre() + " con ID: " + p.getProyecto_id() +
                                " ha sido modificado con éxito.");  
                        } catch (DAOException ex) {
                            JOptionPane.showMessageDialog(menu, ex.getMessage());  
                            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    //Lo haya insertado o modificado, refrescar el TableModel y re-enviarlo a la vista:

                    try {
                        List<Proyecto> proyectos = maestro.getProyectoDAO().obtenerTodos();
                        tablas.updateTableModel(proyectos);

                    } catch (DAOException ex) {
                        Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(menu,"Error al actualizar el ProyectoTableModel. "
                        + ex.getMessage());  
                    }
                }

            }
            else
                JOptionPane.showMessageDialog(menu,"El Frame no ha enviado los datos correctamente ");
        }
        else if (e.getSource() == btnBorrar){
            
            Map<String,String> datos = tablas.getDatosDetalle();
            
            if (tablas.getClass().equals(TablaCamposFrame.class)){
                datos = tablas.getFilaBorrar();
                
                long campo_id = Long.parseLong(datos.get("campo_id"));
                String nombre = datos.get("nombre");
                String ramal = datos.get("ramal");
                
                Campo c = new Campo (nombre, ramal);
                c.setCampo_id(campo_id);
                
                try {
                    maestro.getCampoDAO().eliminar(c);
                    JOptionPane.showMessageDialog(menu,"Campo " + nombre + " de ID: " + campo_id +
                            " ha sido eliminado con éxito.");
                } catch (DAOException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(menu,"Error al eliminar campo: " + nombre + 
                            " con ID: " + campo_id + " =" + ex.getMessage());
                }
                //Refescar la tabla:
                try {
                        
                        List<Campo> campos = maestro.getCampoDAO().obtenerTodos();
                        tablas.updateTableModel(campos);

                    } catch (DAOException ex) {
                        Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(menu,"Error al actualizar el CampoTableModel. "
                        + ex.getMessage());  
                    }  
            }
            else if (tablas.getClass().equals(TablaEntidadesFrame.class)){
                
                datos = tablas.getFilaBorrar();
                long entidad_id = Long.parseLong(datos.get("entidad_id"));
                String nombre = datos.get("nombre");
                String ubicacion = datos.get("ubicacion");
                List<Proyecto> proyectos = new ArrayList<>();
                
                Entidad ent = new Entidad(nombre, ubicacion, proyectos);
                ent.setEntidad_id(entidad_id);
                
                try{
                    maestro.getEntidadDAO().eliminar(ent);
                    JOptionPane.showMessageDialog(menu,"Entidad " + nombre + " de ID: " + entidad_id +
                            " ha sido eliminada con éxito.");
                }
                catch(DAOException ex){
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(menu,"Error al eliminar Entidad: " + nombre + 
                            " con ID: " + entidad_id + " =" + ex.getMessage());
                }
                //Refrescar la tabla:
                try {
                    List<Entidad> entidades = maestro.getEntidadDAO().obtenerTodos();
                    tablas.updateTableModel(entidades);

                } catch (DAOException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(menu,"Error al actualizar el EntidadTableModel. "
                    + ex.getMessage());  
                }
            }
            else if (tablas.getClass().equals(TablaInvestigadoresFrame.class)){
                
                datos = tablas.getFilaBorrar();
                long investigador_id = Long.parseLong(datos.get("investigador_id"));
                String nombre = datos.get("nombre");
                String titulo = datos.get("titulo");
                Double salario = 0.0;
                try{
                    salario = Double.parseDouble(datos.get("salario"));
                }catch(Exception ex){}
                
                long idProyecto = 0;
                try{
                    idProyecto = Long.parseLong(datos.get("proyecto_id"));
                }
                catch(NumberFormatException ex){}
                
                Proyecto pDeInv = null;
                try{
                    if (idProyecto > 0)
                        pDeInv = maestro.getProyectoDAO().obtener(idProyecto, 0);
                }catch (DAOException ex){}
                List<Campo> campos = new ArrayList<>();
                
                Investigador i = new Investigador(nombre, titulo, salario, pDeInv, campos);
                i.setInvestigador_id(investigador_id);
                
                try{
                    maestro.getInvestigadorDAO().eliminar(i);
                    JOptionPane.showMessageDialog(menu,"Investigador: " + nombre + " con ID: " + 
                            investigador_id + " ha sido eliminado con éxito.");
                    
                }catch (DAOException ex){
                    
                    JOptionPane.showMessageDialog(menu,"Error al eliminar Investigador: " + nombre + 
                            " con ID: " + investigador_id, ex.getMessage(), JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
                //Refescar la tabla:
                try {
                    List<Investigador> investigadores = maestro.getInvestigadorDAO().obtenerTodos();
                    tablas.updateTableModel(investigadores);

                } catch (DAOException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(menu,"Error al actualizar el InvestigadorTableModel. "
                    , ex.getMessage(), JOptionPane.ERROR_MESSAGE);  
                }
            }
            else if (tablas.getClass().equals(TablaProyectoFrame.class)){
                
                datos = tablas.getFilaBorrar();
                long proyecto_id = Long.parseLong(datos.get("proyecto_id"));
                String nombre = datos.get("nombre");
                Double capital = 0.0;
                try{
                    capital = Double.parseDouble(datos.get("capital"));
                }
                catch(Exception ex){}
                
                LocalDate fecha_inicio = LocalDate.parse(datos.get("fecha_inicio"));
                List<Entidad> entidades = new ArrayList<>();
                List<Investigador> investigadores = new ArrayList<>();
                long idCampo = Long.parseLong(datos.get("campo_id"));
                Campo cDeProy = null;
                try{
                    if (idCampo > 0)
                        cDeProy = maestro.getCampoDAO().obtener(idCampo, 0);
                }catch (DAOException ex){}
                Boolean finalizado = Boolean.parseBoolean(datos.get("finalizado"));
                Double coste = Double.parseDouble(datos.get("coste"));
                LocalDate fecha_fin = null;
                try{
                    fecha_fin = LocalDate.parse(datos.get("fecha_fin"));
                }catch(Exception ex){  }
                
                
                Proyecto p = new Proyecto(nombre, capital, fecha_inicio, cDeProy, entidades,
                investigadores, finalizado, coste, fecha_fin);
                p.setProyecto_id(proyecto_id);
                
                try{
                    maestro.getProyectoDAO().eliminar(p);
                    JOptionPane.showMessageDialog(menu,"Proyecto: " + nombre + " con ID: " + 
                            proyecto_id + " ha sido eliminado con éxito.");
                }
                catch(DAOException ex){
                    JOptionPane.showMessageDialog(menu,"Error al eliminar Proyecto: " + nombre + 
                            " con ID: " + proyecto_id + ". ", ex.getMessage(), JOptionPane.ERROR_MESSAGE);
                }
                //Refescar la tabla:
                try {
                    
                    List<Proyecto> proyectos = maestro.getProyectoDAO().obtenerTodos();
                    tablas.updateTableModel(proyectos);
                    
                } catch (DAOException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(menu,"Error al actualizar el ProyectoTableModel. "
                    + ex.getMessage());  
                }
            }
            
        }    }
    
    public void asignaBotonesCRUD(){
          
        btnGuardar = tablas.getBtnGuardar();
        btnBorrar = tablas.getBtnBorrar();      
        
        btnGuardar.addActionListener(this);
        btnBorrar.addActionListener(this);
    }
    
  
}
