

package com.iespacomolla.investigacion.vista.investigador;

import com.iespacomolla.investigacion.modelo.Proyecto;
import java.util.List;
import javax.swing.DefaultComboBoxModel;


public class proyectosComboModel extends DefaultComboBoxModel<Proyecto>{

    private List<Proyecto> datos;

    
    public proyectosComboModel() {
    }
    
    public proyectosComboModel(List<Proyecto> datos){
        this.datos = datos;
    }
    
    public void update(List<Proyecto> proyectos){
        datos = proyectos;
        removeAllElements();
        datos.forEach(p -> {
            addElement(p);
        });
    }
}
