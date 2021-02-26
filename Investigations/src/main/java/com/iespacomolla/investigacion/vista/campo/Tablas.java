/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iespacomolla.investigacion.vista.campo;

import javax.swing.JButton;

/**
 *
 * Interfaz que implementarán mis 4 Frames donde el usuario realiza las operaciones CRUD,
 *  que usará el Menú principal para mayor modularidad 
 */
public interface Tablas {
    
    public JButton getBtnCrear();
    
    public JButton getBtnBorrar();
        
    public JButton getBtnEditar();
    
    public JButton getBtnGuardar();
            
    public JButton getBtnCancelar();
    
    public void setVisible(boolean b);
}
