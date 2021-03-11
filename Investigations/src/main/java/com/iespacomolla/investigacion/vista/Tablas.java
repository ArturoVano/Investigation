
package com.iespacomolla.investigacion.vista;

import com.iespacomolla.investigacion.modelo.Campo;
import com.iespacomolla.investigacion.modelo.Entidad;
import com.iespacomolla.investigacion.modelo.Investigador;
import com.iespacomolla.investigacion.modelo.Proyecto;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

/**
 *
 * Interfaz que implementarán mis 4 Frames donde el usuario realiza las operaciones CRUD.
 *  
 * 
 * @param <T>
 */
public interface Tablas<T>
{
    
    public void updateTableModel(List <T> t);
    
    public JButton getBtnCrear();
    
    public JButton getBtnBorrar();
        
    public JButton getBtnEditar();
    
    public JButton getBtnGuardar();
            
    public JButton getBtnCancelar();
    
    public void setVisible(boolean b);

    public Map<String, String> getDatosDetalle();
    
    public Map<String, String> getFilaBorrar();
    
    public void proyectoComboModel(List<Proyecto> p);
    
    public void entidadComboModel(List<Entidad> e);
    
    public void investigadorComboModel(List<Investigador> i);
    
    public void campoComboModel(List<Campo> c);

}
