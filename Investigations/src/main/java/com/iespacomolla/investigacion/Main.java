

package com.iespacomolla.investigacion;

import com.iespacomolla.investigacion.controlador.Controlador;
import com.iespacomolla.investigacion.vista.LoginDialog;


public class Main {
    
    final static private int JDBC = 0;
    final static private int HIBERNATE = 1; 
    final static private int OBJECTDB = 2; 
    final static private int MONGO = 3; 
           
    public static void main(String[] args){
        
        LoginDialog dialog = new LoginDialog();
        dialog.setVisible(true);
        
        if (dialog.isAceptado()){
            
            String host = dialog.getHost();
            String usuario = dialog.getUsuario();
            String contra = dialog.getContra();
            
            if (dialog.getDecision() == 0){
                
                Controlador controlJDBC = new Controlador(host, usuario, contra, JDBC);
            }
            else if (dialog.getDecision() == 1){
                Controlador controlHbn = new Controlador("", "", "", HIBERNATE);
            }
            else if (dialog.getDecision() == 2){
                Controlador controlODB = new Controlador("", "", "", OBJECTDB);
            }
            else if (dialog.getDecision() == 3){
                Controlador controlJDBC = new Controlador(host, usuario, contra, MONGO);
            }       

            
        }
    }
}
