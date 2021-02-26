

package com.iespacomolla.investigacion;

import com.iespacomolla.investigacion.controlador.Controlador;
import com.iespacomolla.investigacion.vista.LoginDialog;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Main {
    
    public static void main(String[] args){
        
        LoginDialog dialog = new LoginDialog();
        dialog.setVisible(true);
        
        if (dialog.isAceptado()){
            
            String host = dialog.getMysqlHost();
            String usuario = dialog.getMysqlUsuario();
            String contra = dialog.getMysqlContra();
            Controlador control = new Controlador(host, usuario, contra);
            
        }
    }
}
