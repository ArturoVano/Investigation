
package com.iespacomolla.investigacion.vista.investigador;

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


public class TablaInvestigadoresFrame extends javax.swing.JFrame implements Tablas<Investigador>{
    
    Map<String, String> nDatos;
    boolean nuevoRegistro;
    private InvestigadorTableModel modeloTabla;
    private proyectosComboModel comboModel;
    private List<Investigador> investigadores;
    
    public TablaInvestigadoresFrame(JFrame padre, List<Investigador> investigadores) {
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
        
        this.investigadores = investigadores;
        modeloTabla = new InvestigadorTableModel(investigadores);
        tabla.setModel(modeloTabla);
        
        comboModel = new proyectosComboModel();
    }
    
    @Override
    public void updateTableModel(List<Investigador> i) {
        
        investigadores = i;
        modeloTabla.updateModel(investigadores);
        modeloTabla.fireTableDataChanged();
    }
    
    @Override
    public Map<String, String> getFilaBorrar() {
        
        String investigador_id = tabla.getValueAt(tabla.getSelectedRow(), 0).toString();
        nDatos.put("investigador_id", investigador_id);       
        nDatos.put("nombre", tabla.getValueAt(tabla.getSelectedRow(), 1).toString());
        nDatos.put("titulo", tabla.getValueAt(tabla.getSelectedRow(), 2).toString());
        if (tabla.getValueAt(tabla.getSelectedRow(), 3) != null)
            nDatos.put("salario", tabla.getValueAt(tabla.getSelectedRow(), 3).toString());
        else
            nDatos.put("salario","");
        nDatos.put("proyecto", tabla.getValueAt(tabla.getSelectedRow(), 4).toString());
        
        return nDatos;
    }
    
    @Override
    public Map<String, String> getDatosDetalle(){
        
        nDatos = new HashMap<String, String>();
        if (!nuevoRegistro){ //Si ya existe, le añado su id;
            String investigador_id = tabla.getValueAt(tabla.getSelectedRow(), 0).toString();
            nDatos.put("investigador_id", investigador_id);
        }
        nDatos.put("nombre", txNombre.getText());
        nDatos.put("titulo", txTitulo.getText());
        nDatos.put("salario", txSalario.getText());
        
        String proyecto_id = "";
        if (cbProyecto.getSelectedItem() != null){
            Proyecto proy = (Proyecto) cbProyecto.getSelectedItem();
            proyecto_id = proy.getProyecto_id().toString();
        }
        nDatos.put("proyecto_id", proyecto_id);
        return nDatos;
    }
    
    //El controlador lo usará si quiere que se pueda elegir el proyecto de este investigador:
    @Override
    public void proyectoComboModel(List<Proyecto> p){
        comboModel.update(p);
        cbProyecto.setModel(comboModel);
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
        txTitulo = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txSalario = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cbProyecto = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "investigador_id", "nombre", "titulo", "salario", "proyecto_id"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabla);

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel10.setText("nombre:");

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel9.setText("titulo:");

        txSalario.setActionCommand("<Not Set>");

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel11.setText("salario:");

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel12.setText("proyecto:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
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
                            .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(413, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(46, 46, 46)
                                .addComponent(txNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(70, 70, 70)
                                .addComponent(txTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(56, 56, 56)
                                .addComponent(txSalario, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(36, 36, 36)
                                .addComponent(cbProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(41, 41, 41))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(txNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(txTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(txSalario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(cbProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(120, 120, 120))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed

        txNombre.setText(null);
        txTitulo.setText(null); 
        txSalario.setText(null);
        cbProyecto.setSelectedIndex(0);
        habilitaInput(false);
        
        btnGuardar.setEnabled(false);
        btnCancelar.setEnabled(false);
        tabla.clearSelection();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        String nom =  tabla.getValueAt(tabla.getSelectedRow(), 1).toString();
        String titulo = tabla.getValueAt(tabla.getSelectedRow(), 2).toString();
        String salario = "";
        if (tabla.getValueAt(tabla.getSelectedRow(), 3) != null)
            salario = tabla.getValueAt(tabla.getSelectedRow(), 3).toString();
        String proyecto = tabla.getValueAt(tabla.getSelectedRow(), 4).toString();
        
        txNombre.setText(nom);
        txTitulo.setText(titulo); 
        txSalario.setText(salario);
        cbProyecto.setSelectedItem(proyecto);
        // Y damos la opción de guardar o cancelar la modificación:
        btnGuardar.setEnabled(true);
        btnCancelar.setEnabled(true);
        habilitaInput(true);
        
        nuevoRegistro = false;
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
        
        txNombre.setText(null);
        txTitulo.setText(null); 
        txSalario.setText(null);
        cbProyecto.setSelectedIndex(0);
        habilitaInput(true);
        btnGuardar.setEnabled(true);
        btnCancelar.setEnabled(true);
        
        nuevoRegistro = true;
    }//GEN-LAST:event_btnCrearActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        
        btnGuardar.setEnabled(false);
        btnCancelar.setEnabled(false);
        habilitaInput(false);
        
        txNombre.setText(null);
        txTitulo.setText(null); 
        txSalario.setText(null);
        //cbProyecto.setSelectedIndex(0);
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        btnBorrar.setEnabled(false);
        btnEditar.setEnabled(false);
        
        tabla.clearSelection();
    }//GEN-LAST:event_btnBorrarActionPerformed

    private void habilitaInput(boolean b){
        
        txNombre.setEnabled(b);
        txTitulo.setEnabled(b);
        txSalario.setEnabled(b);
        cbProyecto.setEnabled(b);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCrear;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<Proyecto> cbProyecto;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txNombre;
    private javax.swing.JTextField txSalario;
    private javax.swing.JTextField txTitulo;
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
