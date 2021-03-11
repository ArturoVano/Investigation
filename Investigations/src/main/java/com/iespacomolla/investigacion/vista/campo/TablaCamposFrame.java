
package com.iespacomolla.investigacion.vista.campo;

import com.iespacomolla.investigacion.modelo.Campo;
import com.iespacomolla.investigacion.modelo.Entidad;
import com.iespacomolla.investigacion.modelo.Investigador;
import com.iespacomolla.investigacion.modelo.Proyecto;
import com.iespacomolla.investigacion.vista.Tablas;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;

//Esta clase será llamada por MainMenu
public class TablaCamposFrame extends javax.swing.JFrame implements Tablas<Campo>{

   Map<String, String> nDatos;
   boolean nuevoRegistro;
   private CampoTableModel modeloTabla;
   private List<Campo> campos;

   
   //El controlador le dará el padre y la información que debe mostrar.
    public TablaCamposFrame(JFrame padre, List<Campo> campos) {
        initComponents();
        tabla.getTableHeader().setReorderingAllowed(false);
        setLocationRelativeTo(padre);
         //Quiero que por defecto solo se pueda usar el btnCrear_
        btnEditar.setEnabled(false);
        btnBorrar.setEnabled(false);
        btnGuardar.setEnabled(false);
        btnCancelar.setEnabled(false);     
        
        this.tabla.getSelectionModel().addListSelectionListener(e -> {
            boolean seleccionValida = (tabla.getSelectedRow() != -1);
            btnEditar.setEnabled(seleccionValida);
            btnBorrar.setEnabled(seleccionValida);
            
        });
        
       habilitaInput(false);
       this.campos = campos;
       modeloTabla = new CampoTableModel(campos);
       tabla.setModel(modeloTabla);
    }
    
    //El constructor lo llamará cuando haga cambios con los datos:
    @Override
    public void updateTableModel(List<Campo> c) {
        
        campos = c;
        modeloTabla.updateModel(campos);
        modeloTabla.fireTableDataChanged();
    }
    
    
    @Override
    public JButton getBtnBorrar() {
        return btnBorrar;
    }

    @Override
    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    @Override
    public JButton getBtnCrear() {
        return btnCrear;
    }

    @Override
    public JButton getBtnEditar() {
        return btnEditar;
    }

    @Override
    public JButton getBtnGuardar() {
        return btnGuardar;
    }
    
    @Override
    public Map<String, String> getDatosDetalle(){
        
        //Sea para insertar o para modificar, completamos el Diccionario:
        nDatos = new HashMap<String, String>();
        
        if (!nuevoRegistro){ //Si ya existe, le paso su id;
            String campo_id = tabla.getValueAt(tabla.getSelectedRow(), 0).toString();
            nDatos.put("campo_id", campo_id);
        }
        //Le meto los demás datos:    
        nDatos.put("nombre", txNombre.getText());
        nDatos.put("ramal", txRamal.getText());
        
        return nDatos;
    }
    
   @Override
    public Map<String, String> getFilaBorrar(){
        
        String campo_id = tabla.getValueAt(tabla.getSelectedRow(), 0).toString();
        nDatos.put("campo_id", campo_id);       
        nDatos.put("nombre", tabla.getValueAt(tabla.getSelectedRow(), 1).toString());
        nDatos.put("ramal", tabla.getValueAt(tabla.getSelectedRow(), 2).toString());
        
        return nDatos;
    }
    
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnCrear = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        txNombre = new javax.swing.JTextField();
        txRamal = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnCrear.setText("Nuevo");
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });
        getContentPane().add(btnCrear, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 93, 48));

        btnBorrar.setText("Borrar");
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });
        getContentPane().add(btnBorrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 30, 93, 48));

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 30, 93, 48));

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, 93, 48));

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        getContentPane().add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 30, 93, 48));

        jScrollPane1.setViewportView(tabla);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 490, 250));

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel10.setText("nombre:");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 130, -1, -1));
        getContentPane().add(txNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 130, 160, -1));
        getContentPane().add(txRamal, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 180, 160, -1));

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel9.setText("ramal:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 180, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Cuando el botón editar este habilitado, y le de click, los TextBox recivirán los valores editables de la fila seleccionada:
    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        
        //String campo_id = tabla.getValueAt(tabla.getSelectedRow(), 0).toString();
        String nom = (String) tabla.getValueAt(tabla.getSelectedRow(), 1);
        String ramal = (String) tabla.getValueAt(tabla.getSelectedRow(), 2);
        txNombre.setText(nom);
        txRamal.setText(ramal);
        // Y damos la opción de guardar o cancelar la modificación:
        btnGuardar.setEnabled(true);
        btnCancelar.setEnabled(true);
        habilitaInput(true);
        //Indicamos al programa que se va a editar un registro existente:
        nuevoRegistro = false;
        
        nuevoRegistro = false;
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        
        txNombre.setText(null);
        txRamal.setText(null);
        habilitaInput(false);
        btnGuardar.setEnabled(false);
        btnCancelar.setEnabled(false);
        tabla.clearSelection();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
        
        txNombre.setText(null);
        txRamal.setText(null);
        
        habilitaInput(true);
        btnGuardar.setEnabled(true);
        btnCancelar.setEnabled(true);
        //Indicamos al programa que se esta creando un nuevo registro:
        nuevoRegistro = true;
    }//GEN-LAST:event_btnCrearActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        
        
        
        btnGuardar.setEnabled(false);
        btnCancelar.setEnabled(false);
        habilitaInput(false);
        
        txNombre.setText("");
        txRamal.setText("");
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
         
        
        btnBorrar.setEnabled(false);
        btnEditar.setEnabled(false);
        
        tabla.clearSelection();
    }//GEN-LAST:event_btnBorrarActionPerformed

    private void habilitaInput(boolean b){
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCrear;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txNombre;
    private javax.swing.JTextField txRamal;
    // End of variables declaration//GEN-END:variables

 

    @Override
    public void proyectoComboModel(List<Proyecto> p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void entidadComboModel(List<Entidad> e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void investigadorComboModel(List<Investigador> i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void campoComboModel(List<Campo> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

}
