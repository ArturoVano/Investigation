
package com.iespacomolla.investigacion.vista.investigador;

import javax.swing.ComboBoxModel;
import com.iespacomolla.investigacion.modelo.Proyecto;
import javax.swing.DefaultComboBoxModel;

public class DetalleInvestigadorPanel extends javax.swing.JPanel {

    private boolean editable;
    //Se le darán los nombres de proyectos disponibles:
    //private ComboBoxModel model; 
    
    public DetalleInvestigadorPanel() {
        initComponents();
        
        
    }

    public boolean isEditable(){
        return editable;
           
    }
    
    public void setEditable(boolean editable){
        this.editable = editable;
        txTitulo.setEditable(editable);
        txNombre.setEditable(editable);
        txSalario.setEditable(editable);
        cbProyecto.setEditable(editable);
    }
    
    public void setModelo(ComboBoxModel modelo){
        cbProyecto.setModel(modelo);
    }
    public void setTxNombreText(String t) throws NullPointerException{
        txNombre.setText(t);
    }
    
    public void setTxTitulo(String t)throws NullPointerException{
        txTitulo.setText(t);
    }
    
    public void setTxSalario(String s) throws NullPointerException{
        
        txSalario.setText(s);
        
    }
    
    public void setComboBoxModel(DefaultComboBoxModel cb){
        cbProyecto.setModel(cb);
    }
    
    public void setCbProyectoItem(String t)throws NullPointerException{
        cbProyecto.setSelectedItem(t);
    }
    
    public String getTxNombreText() {
        return txNombre.getText();
    }
    
    public String getTxTituloText(){
        return txTitulo.getText();
    }
    
    public String getTxSalarioText(){
        /*Double salario = 0.0;
        try{
            salario = Double.parseDouble(txSalario.getText());
        }catch(NumberFormatException ex){
            System.out.println(ex);  //TEMPORAL
        }*/
        String salario = txSalario.getText();
        return salario;
    }
    
    public String getCbSeleccion(){
        String proyecto_id = "";
        
        if (cbProyecto.getSelectedItem() != null){
            Proyecto proy = (Proyecto) cbProyecto.getSelectedItem();
            proyecto_id = proy.getProyecto_id().toString();
        }
        return proyecto_id;
    }
    
    
    public void habilitacion(boolean b){
        
        txNombre.setEnabled(b);
        txTitulo.setEnabled(b);
        txSalario.setEnabled(b);
        cbProyecto.setEnabled(b);
        
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel10 = new javax.swing.JLabel();
        txNombre = new javax.swing.JTextField();
        txTitulo = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txSalario = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cbProyecto = new javax.swing.JComboBox<>();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel10.setText("nombre:");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 50, -1, -1));
        add(txNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(183, 52, 163, -1));
        add(txTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(183, 108, 163, -1));

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel9.setText("titulo:");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 106, -1, -1));

        txSalario.setActionCommand("<Not Set>");
        add(txSalario, new org.netbeans.lib.awtextra.AbsoluteConstraints(183, 152, 163, -1));

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel11.setText("salario:");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 150, -1, -1));

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel12.setText("proyecto:");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 194, -1, -1));

        add(cbProyecto, new org.netbeans.lib.awtextra.AbsoluteConstraints(183, 195, 163, -1));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<Proyecto> cbProyecto;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField txNombre;
    private javax.swing.JTextField txSalario;
    private javax.swing.JTextField txTitulo;
    // End of variables declaration//GEN-END:variables
}
