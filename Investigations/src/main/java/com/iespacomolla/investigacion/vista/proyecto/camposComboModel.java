
package com.iespacomolla.investigacion.vista.proyecto;

import com.iespacomolla.investigacion.modelo.Campo;
import java.util.List;
import javax.swing.DefaultComboBoxModel;


public class camposComboModel extends DefaultComboBoxModel<Campo>{
    
    private List<Campo> datos;
    
    public camposComboModel() {
    }
    
    public camposComboModel(List<Campo> c) {
        datos = c;
    }
    
    
    public List<Campo> getCampos(){
        return datos;
    }
    
    public void update(List<Campo> campos){
        datos = campos;
        removeAllElements();
        for (Campo c : datos){
            addElement(c);
        }
    }


}
