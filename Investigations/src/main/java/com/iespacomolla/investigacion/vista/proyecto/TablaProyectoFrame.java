
package com.iespacomolla.investigacion.vista.proyecto;

import com.iespacomolla.investigacion.modelo.Campo;
import com.iespacomolla.investigacion.modelo.Entidad;
import com.iespacomolla.investigacion.modelo.Investigador;
import com.iespacomolla.investigacion.modelo.Proyecto;
import com.iespacomolla.investigacion.vista.Tablas;
import com.iespacomolla.investigacion.vista.campo.CampoTableModel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;


public class TablaProyectoFrame extends javax.swing.JFrame implements Tablas<Proyecto>{

    Map<String, String> nDatos;
    
   boolean nuevoRegistro;
   
   private ProyectoTableModel modeloTabla;
   
   private List<Proyecto> proyectos;
   
   private camposComboModel campoModel;
   
   
    public TablaProyectoFrame(JFrame padre, List<Proyecto> proyectos) {
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
        
        habilitaInput(false);
        
        this.proyectos = proyectos;
        modeloTabla = new ProyectoTableModel(proyectos);
        tabla.setModel(modeloTabla);
        
        campoModel = new camposComboModel();   
    }

    //El constructor lo llamará cuando haga cambios con los datos:
    @Override
    public void updateTableModel(List<Proyecto> p) {
        
        proyectos = p;
        modeloTabla.updateModel(proyectos);
        modeloTabla.fireTableDataChanged();
    }
    
    public Map<String, String> getDatosDetalle(){
        
        //Sea para insertar o para modificar, completamos el Diccionario:
        nDatos = new HashMap<String, String>();
        
        if (!nuevoRegistro){ //Si ya existe, le paso su id;
            String proyecto_id = tabla.getValueAt(tabla.getSelectedRow(), 0).toString();
            nDatos.put("proyecto_id", proyecto_id);
        }
        //Le meto los demás datos:    
        nDatos.put("nombre", txNombre.getText());
        try{ 
            nDatos.put("fecha_inicio", txFechaInicio.getText());
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this, "fecha inicio"+  txFechaInicio.getText() + 
                    " incorrecta, ejemplo: 2007-12-03");
            throw ex; //Para terminar con el método
        }
        
        nDatos.put("capital", txCapital.getText()); 
        
        String campo = cbCampo.getSelectedItem().toString();
        String[] properties = campo.split(",");
        String campo_id = properties[0];
        nDatos.put("campo_id", campo_id);
        
        nDatos.put("finalizado", String.valueOf(cbFinalizado.isSelected()));
        nDatos.put("fecha_fin", txFechaFin.getText());
        nDatos.put("coste", txCoste.getText());
        
        return nDatos;
    }
    
    @Override
    public Map<String, String> getFilaBorrar(){
        
        String proyecto_id = tabla.getValueAt(tabla.getSelectedRow(), 0).toString();
        nDatos.put("proyecto_id", proyecto_id);  
        nDatos.put("nombre", tabla.getValueAt(tabla.getSelectedRow(), 1).toString());
        nDatos.put("capital", tabla.getValueAt(tabla.getSelectedRow(), 2).toString());
        nDatos.put("fecha_inicio", tabla.getValueAt(tabla.getSelectedRow(), 3).toString());
        nDatos.put("finalizado", tabla.getValueAt(tabla.getSelectedRow(), 6).toString());
        nDatos.put("coste", tabla.getValueAt(tabla.getSelectedRow(), 7).toString());
        String campo_id = tabla.getValueAt(tabla.getSelectedRow(), 9).toString();
        nDatos.put("campo_id", campo_id);
        if (tabla.getValueAt(tabla.getSelectedRow(), 10) != null )
            nDatos.put("fecha_fin", tabla.getValueAt(tabla.getSelectedRow(), 10).toString());
        
        return nDatos;
    }
    
    @Override
    public void campoComboModel(List<Campo> c) {
        campoModel.update(c);
        cbCampo.setModel(campoModel);
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnEditar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnCrear = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txNombre = new javax.swing.JTextField();
        txCapital = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txFechaInicio = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        cbCampo = new javax.swing.JComboBox<>();
        cbFinalizado = new javax.swing.JCheckBox();
        jLabel13 = new javax.swing.JLabel();
        txFechaFin = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txCoste = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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

        tabla.setModel(tabla.getModel());
        jScrollPane1.setViewportView(tabla);

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel9.setText("capital:");

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel10.setText("nombre:");

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel11.setText("fecha_inicio:");

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel12.setText("campo:");

        cbFinalizado.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        cbFinalizado.setText("Finalizado");

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel13.setText("fecha_fin:");

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel14.setText("coste:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 715, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(63, 63, 63)
                                .addComponent(txNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(26, 26, 26)
                                .addComponent(txFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jLabel9)
                                .addGap(63, 63, 63)
                                .addComponent(txCapital, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(72, 72, 72)
                                .addComponent(cbCampo, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(143, 143, 143)
                                .addComponent(cbFinalizado))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(56, 56, 56)
                                .addComponent(txFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(83, 83, 83)
                                .addComponent(txCoste, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                            .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(txNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(txFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(txCapital, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(cbCampo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(cbFinalizado)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(txFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(txCoste, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(60, 60, 60))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        //Mostrar al usuario la info. actual:
        String nom = tabla.getValueAt(tabla.getSelectedRow(), 1).toString();
        String capital = tabla.getValueAt(tabla.getSelectedRow(), 2).toString();
        String fecha_ini = tabla.getValueAt(tabla.getSelectedRow(), 3).toString();
        boolean finalizado = Boolean.valueOf(tabla.getValueAt(tabla.getSelectedRow(), 6).toString());
        String coste = tabla.getValueAt(tabla.getSelectedRow(), 7).toString();
        String campo_nom = tabla.getValueAt(tabla.getSelectedRow(), 8).toString();
        String fecha_fin = "";
        if (tabla.getValueAt(tabla.getSelectedRow(), 10) != null )
            fecha_fin = tabla.getValueAt(tabla.getSelectedRow(), 10).toString();
        
        txNombre.setText(nom);
        txFechaInicio.setText(fecha_ini);
        txCapital.setText(capital);
        cbCampo.setSelectedItem(campo_nom);
        cbFinalizado.setSelected(finalizado);
        txFechaFin.setText(fecha_fin);
        txCoste.setText(coste);
        
        // Y damos la opción de guardar o cancelar la modificación:
        btnGuardar.setEnabled(true);
        btnCancelar.setEnabled(true);
        habilitaInput(true);
        
        nuevoRegistro = false;
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed

        txNombre.setText("");
        txFechaInicio.setText(null);
        txCapital.setText(null);
        cbCampo.setSelectedIndex(0);
        cbFinalizado.setSelected(false);
        txFechaFin.setText("");
        txCoste.setText("");
        habilitaInput(false); 
        btnGuardar.setEnabled(false);
        btnCancelar.setEnabled(false);
        tabla.clearSelection();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
        habilitaInput(true);      
        btnGuardar.setEnabled(true);
        btnCancelar.setEnabled(true);
        
        txNombre.setText("");
        txFechaInicio.setText(null);
        txCapital.setText(null);
        cbCampo.setSelectedIndex(0);
        cbFinalizado.setSelected(false);
        txFechaFin.setText("");
        txCoste.setText("");
        
        nuevoRegistro = true;
    }//GEN-LAST:event_btnCrearActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        
        btnGuardar.setEnabled(false);
        btnCancelar.setEnabled(false);
        habilitaInput(false);
        
        txNombre.setText(null);
        txFechaInicio.setText(null);
        txCapital.setText(null);
        cbCampo.setSelectedIndex(0);
        cbFinalizado.setSelected(false);
        txFechaFin.setText(null);
        txCoste.setText(null);
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        
        btnBorrar.setEnabled(false);
        btnEditar.setEnabled(false);
        
        tabla.clearSelection();
    }//GEN-LAST:event_btnBorrarActionPerformed

    public void habilitaInput(boolean editable){
        
        txNombre.setEditable(editable);
        txFechaInicio.setEditable(editable);
        txCapital.setEditable(editable);
        cbCampo.setEditable(editable);
        cbFinalizado.setEnabled(editable);
        txFechaFin.setEditable(editable);
        txCoste.setEditable(editable);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCrear;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<Campo> cbCampo;
    private javax.swing.JCheckBox cbFinalizado;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txCapital;
    private javax.swing.JTextField txCoste;
    private javax.swing.JTextField txFechaFin;
    private javax.swing.JTextField txFechaInicio;
    private javax.swing.JTextField txNombre;
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

    

}
