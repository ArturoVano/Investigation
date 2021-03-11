
package com.iespacomolla.investigacion.vista.entidad;

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
import javax.swing.table.AbstractTableModel;

//Esta clase será llamada por MainMenu
public class TablaEntidadesFrame extends javax.swing.JFrame implements Tablas<Entidad>{
    
    Map<String, String> nDatos;
    boolean nuevoRegistro;
    private EntidadTableModel modeloTabla;
    private List<Entidad> entidades;
    //private DetalleEntidadPanel detalle;
    
    public TablaEntidadesFrame(JFrame padre, List<Entidad> entidades) {
        initComponents();
        tabla.getTableHeader().setReorderingAllowed(false);
        setLocationRelativeTo(padre);
        //Quiero que por defecto solo se pueda usar el btnCrear:
        btnEditar.setEnabled(false);
        btnBorrar.setEnabled(false);
        btnGuardar.setEnabled(false);
        btnCancelar.setEnabled(false);
        
        //Acción de seleccion de la tabla y comprobación, para permitir nuevas opciones 
        this.tabla.getSelectionModel().addListSelectionListener(e -> {
            boolean seleccionValida = (tabla.getSelectedRow() != -1);
            btnEditar.setEnabled(seleccionValida);
            btnBorrar.setEnabled(seleccionValida);
            
        });
        //Descomentar si el detalle actual desaparece
        
             
        txNombre.setEnabled(false);
        txUbicacion.setEnabled(false);
        
        this.entidades = entidades;
        modeloTabla = new EntidadTableModel(entidades);
        tabla.setModel(modeloTabla);
    }

    //El constructor lo llamará cuando haga cambios con los datos:
    @Override
    public void updateTableModel(List<Entidad> e) {
        
        entidades = e;
        modeloTabla.updateModel(entidades);
        modeloTabla.fireTableDataChanged();
    }
    
    @Override
    public Map<String, String> getDatosDetalle() {
        
        nDatos = new HashMap<String, String>();
        if (!nuevoRegistro){
            String entidad_id = tabla.getValueAt(tabla.getSelectedRow(), 0).toString();
            nDatos.put("entidad_id", entidad_id);
        }
        //Le meto los demás datos:
        nDatos.put("nombre", txNombre.getText());
        nDatos.put("ubicacion", txUbicacion.getText());
        
        return nDatos;
    }
    
    @Override
    public Map<String, String> getFilaBorrar(){
        
        String entidad_id = tabla.getValueAt(tabla.getSelectedRow(), 0).toString();
        nDatos.put("entidad_id", entidad_id);       
        nDatos.put("nombre", tabla.getValueAt(tabla.getSelectedRow(), 1).toString());
        nDatos.put("ubicacion", tabla.getValueAt(tabla.getSelectedRow(), 2).toString());
        
        return nDatos;
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        btnCrear = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        txUbicacion = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txNombre = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "entidad_id", "nombre", "ubicacion"
            }
        ));
        jScrollPane1.setViewportView(tabla);

        btnCrear.setText("Nuevo");
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });

        btnBorrar.setText("Borrar");
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cacelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel9.setText("Ubicacion:");

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel10.setText("nombre:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(btnBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(90, 90, 90)
                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txUbicacion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txNombre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(59, 59, 59))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(56, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed

        txNombre.setText(null);
        txUbicacion.setText(null);
        txNombre.setEnabled(false);
        txUbicacion.setEnabled(false);
        btnGuardar.setEnabled(false);
        btnCancelar.setEnabled(false);
        tabla.clearSelection();
        
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        //Mostrar al usuario la info. actual:
        String nom = (String) tabla.getValueAt(tabla.getSelectedRow(), 1);
        String ubi = (String) tabla.getValueAt(tabla.getSelectedRow(), 2);
        txNombre.setText(nom);
        txUbicacion.setText(ubi);
        // Y damos la opción de guardar o cancelar la modificación:
        btnGuardar.setEnabled(true);
        btnCancelar.setEnabled(true);
        txNombre.setEnabled(true);
        txUbicacion.setEnabled(true);
        
        nuevoRegistro = false;
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
        
        txNombre.setText(null);
        txUbicacion.setText(null);
        
        txNombre.setEnabled(true);
        txUbicacion.setEnabled(true);
        btnGuardar.setEnabled(true);
        btnCancelar.setEnabled(true);
        
        nuevoRegistro = true;
    }//GEN-LAST:event_btnCrearActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        
        btnGuardar.setEnabled(false);
        btnCancelar.setEnabled(false);
        txNombre.setEnabled(true);
        txUbicacion.setEnabled(true);
        txNombre.setText("");
        txUbicacion.setText("");
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        
        btnBorrar.setEnabled(false);
        btnEditar.setEnabled(false);
        tabla.clearSelection();
    }//GEN-LAST:event_btnBorrarActionPerformed



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
    private javax.swing.JTextField txUbicacion;
    // End of variables declaration//GEN-END:variables

    @Override
    public JButton getBtnCrear() {
        return btnCrear;
    }

    @Override
    public JButton getBtnBorrar() {
        return btnBorrar;
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
    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    //TablaEntidades no usará ninguno de estos métodos heredados:

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
