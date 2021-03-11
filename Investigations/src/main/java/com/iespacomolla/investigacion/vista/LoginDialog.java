package com.iespacomolla.investigacion.vista;

import java.awt.Dimension;
import java.awt.Toolkit;

public class LoginDialog extends javax.swing.JDialog {
    
    //Varaible para saber si se ha clicado en contectar o en cancelar
    private boolean aceptado;
    
    public LoginDialog() {
        super((javax.swing.JFrame)null, true);
        initComponents();
        
        Toolkit miPantalla = Toolkit.getDefaultToolkit();
        Dimension TamPantalla = miPantalla.getScreenSize();
        int alturaPant = TamPantalla.height;
        int anchuraPant = TamPantalla.width;
        this.setLocation(anchuraPant/4,alturaPant/5);
        //LoginScreen.setSize(1002,725);
    }
    
    public String getHost(){
        return tbHost.getText();
    }
    public String getUsuario(){
        return tbUsuario.getText();
    }
    public String getContra(){
        return tbContra.getText();
    }
  

    public boolean isAceptado() {
        return aceptado;
    }

    public void setAceptado(boolean aceptado) {
        this.aceptado = aceptado;
    }
    
    //Si esta activado, usamos JDBC, si no: Hibernate.
    public int getDecision(){
        return decision.getSelectedIndex();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel11 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tbUsuario = new javax.swing.JTextField();
        tbHost = new javax.swing.JTextField();
        tbContra = new javax.swing.JTextField();
        cerrarLog = new javax.swing.JButton();
        conLog = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        decision = new javax.swing.JComboBox<>();
        infoConn = new javax.swing.JLabel();

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel11.setText("MySQL");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("CONEXIÓN A LA BASE DE DATOS");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setText("Host:");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setText("Usuario:");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setText("Contraseña:");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setText("investigation");

        cerrarLog.setText("CANCELAR");
        cerrarLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrarLogActionPerformed(evt);
            }
        });

        conLog.setText("CONECTAR");
        conLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                conLogActionPerformed(evt);
            }
        });

        jLabel9.setText("Que método usar");

        decision.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "JDBC", "Hibernate", "ObjectDB", "MongoDB" }));
        decision.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decisionActionPerformed(evt);
            }
        });

        infoConn.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        infoConn.setText("MySQL");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tbContra, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tbUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tbHost, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(179, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(cerrarLog, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(106, 106, 106)
                        .addComponent(conLog, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(140, 140, 140))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(decision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel9)
                                        .addComponent(jLabel5))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(13, 13, 13)
                                        .addComponent(infoConn)))
                                .addGap(113, 113, 113)))
                        .addGap(126, 126, 126))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addGap(34, 34, 34)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(decision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(infoConn)
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tbHost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tbUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tbContra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cerrarLog, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(conLog, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void conLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_conLogActionPerformed
        //Señalizamos que se ha clicado en confirmar y salimos de la ventada de Loggin:
        aceptado = true;
        dispose();
    }//GEN-LAST:event_conLogActionPerformed

    private void cerrarLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrarLogActionPerformed
        //Señalizamos que se ha clicado en cancelar y salimos de la ventada de Loggin:
        aceptado = false;
        dispose();
    }//GEN-LAST:event_cerrarLogActionPerformed

    private void decisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decisionActionPerformed
        String metodo = decision.getSelectedItem().toString();
        infoConn.setText(metodo);
        
        switch(metodo){
            case "JDBC":        
                tbHost.setEnabled(true);
                tbUsuario.setEnabled(true);
                tbContra.setEnabled(true);
                break;
            case "Hibernate":
                tbHost.setEnabled(false);
                tbUsuario.setEnabled(false);
                tbContra.setEnabled(false);
                break;
            case "ObjectDB":
                tbHost.setEnabled(false);
                tbUsuario.setEnabled(false);
                tbContra.setEnabled(false);
                break;
            case "MongoDB":
                tbHost.setEnabled(true);
                tbUsuario.setEnabled(false);
                tbContra.setEnabled(false);
                break;
            default:
                break;
        }
    }//GEN-LAST:event_decisionActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cerrarLog;
    private javax.swing.JButton conLog;
    private javax.swing.JComboBox<String> decision;
    private javax.swing.JLabel infoConn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField tbContra;
    private javax.swing.JTextField tbHost;
    private javax.swing.JTextField tbUsuario;
    // End of variables declaration//GEN-END:variables
}
