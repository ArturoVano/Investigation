
package com.iespacomolla.investigacion.vista.proyecto;

import java.time.LocalDate;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class DetalleProyectoPanel extends javax.swing.JPanel{

    public boolean editable;
    
    public DetalleProyectoPanel() {
        initComponents();
    }

    public boolean isEditable(){
        
        return editable;
    }
    
    public void setEditable(boolean editable){
        
        this.editable = editable;
        txNombre.setEditable(editable);
        txCapital.setEditable(editable);
        cbCampo.setEditable(editable);
        cbFinalizado.setEnabled(editable);
        txFechaFin.setEditable(cbFinalizado.isSelected());
        txCoste.setEditable(cbFinalizado.isSelected());
    }
    
    public void setComboBoxModel(DefaultComboBoxModel cb){
        cbCampo.setModel(cb);
    }
    
    public void setTxNombreText(String t) {
        txNombre.setText(t);
    }
    public void setTxFechaInicio(String t){
        txFechaInicio.setText(t);
    }
    public void setTxCoste(String t){
        txCoste.setText(t);
    }
    public void setTxCapital(String t){
        txCapital.setText(t);
    }
    public void setCbCampoItem(String t){
        cbCampo.setSelectedItem(t);
    }
    public void setCbFinalizado(boolean b){
        cbFinalizado.setSelected(b);
    }
    public void setTxFechaFin(String t){
        txFechaFin.setText(t);
    }
    
    
    public String getTxNombreText(){
        return txNombre.getText();
    }
    public Double getTxCapital(){
        Double capital = 0.0;
        try{
            capital = Double.parseDouble(txCapital.getText());
        }catch(NumberFormatException ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,"Error, el TableModel de Entidad no ha sido cargado.");
        }
        return capital;
    }

    public String getTxFechaInicio() {
        return  txFechaInicio.getText();
    }
    
    public boolean getcbFinalizado(){
        return cbFinalizado.isSelected();
    }
    
    public String getTxFechaFin(){
        return txFechaFin.getText();
    }
    
    public Double getTxCoste(){
        Double coste = 0.0;
        try{
            coste = Double.parseDouble(txCoste.getText());
        }catch(NumberFormatException ex){
            System.out.println(ex);  //TEMPORAL
        }
        return coste;
    }
    public String getCbCampo(){
        
        String campo = cbCampo.getSelectedItem().toString();
        String[] properties = campo.split(",");
        String campo_id = properties[0];
        return campo_id;
    }
    //Los controles solo podrán ser utilizados cuando desde el padre se autorice:
    public void habilitacion(boolean b){
        
        txNombre.setEnabled(b);
        txFechaInicio.setEnabled(b);
        txCapital.setEnabled(b);
        cbCampo.setEnabled(b);
        cbFinalizado.setEnabled(b);
        txFechaFin.setEnabled(b);
        txCoste.setEnabled(b);
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel9.setText("capital:");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(56, 138, -1, -1));

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel10.setText("nombre:");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 50, -1, -1));
        add(txNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(183, 52, 163, -1));
        add(txCapital, new org.netbeans.lib.awtextra.AbsoluteConstraints(183, 140, 163, -1));

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel11.setText("fecha_inicio:");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 94, -1, -1));
        add(txFechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(183, 96, 163, -1));

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel12.setText("campo:");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 191, -1, -1));

        add(cbCampo, new org.netbeans.lib.awtextra.AbsoluteConstraints(183, 192, 163, -1));

        cbFinalizado.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        cbFinalizado.setText("Finalizado");
        add(cbFinalizado, new org.netbeans.lib.awtextra.AbsoluteConstraints(188, 236, -1, -1));

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel13.setText("fecha_fin:");
        add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 278, -1, -1));
        add(txFechaFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(188, 280, 163, -1));

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel14.setText("coste:");
        add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 334, -1, -1));
        add(txCoste, new org.netbeans.lib.awtextra.AbsoluteConstraints(183, 336, 163, -1));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbCampo;
    private javax.swing.JCheckBox cbFinalizado;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField txCapital;
    private javax.swing.JTextField txCoste;
    private javax.swing.JTextField txFechaFin;
    private javax.swing.JTextField txFechaInicio;
    private javax.swing.JTextField txNombre;
    // End of variables declaration//GEN-END:variables
}
