
package com.iespacomolla.investigacion.vista;

import com.iespacomolla.investigacion.vista.campo.TablaCamposFrame;
import com.iespacomolla.investigacion.vista.campo.Tablas;
import com.iespacomolla.investigacion.vista.entidad.TablaEntidadesFrame;
import javax.swing.JButton;
import javax.swing.JFrame;


public class MenuPrincipal extends javax.swing.JFrame {

    private Tablas tablas;
    
    public MenuPrincipal() {
        initComponents();
        //Por defecto, para que no mareen nos constructores al asignar JButtons:
        tablas = new TablaCamposFrame(this);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        abreTablaEntidad = new javax.swing.JButton();
        abreTablaCampo = new javax.swing.JButton();
        abreTablaProyecto = new javax.swing.JButton();
        abreTablaInvestigador = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        abreTablaEntidad.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        abreTablaEntidad.setText("Entidad");
        abreTablaEntidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abreTablaEntidadActionPerformed(evt);
            }
        });

        abreTablaCampo.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        abreTablaCampo.setText("Campo");
        abreTablaCampo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abreTablaCampoActionPerformed(evt);
            }
        });

        abreTablaProyecto.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        abreTablaProyecto.setText("Proyecto");
        abreTablaProyecto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abreTablaProyectoActionPerformed(evt);
            }
        });

        abreTablaInvestigador.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        abreTablaInvestigador.setText("Investigador");
        abreTablaInvestigador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abreTablaInvestigadorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(abreTablaInvestigador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(abreTablaCampo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(abreTablaEntidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(abreTablaProyecto, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE))
                .addGap(41, 41, 41))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(85, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(abreTablaEntidad, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(abreTablaCampo, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(79, 79, 79)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(abreTablaProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(abreTablaInvestigador, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Cuando clic en los botones de este Frame, abrimos los respectivos Frames pasandole esta clase como padre:
    private void abreTablaCampoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abreTablaCampoActionPerformed
        
        tablas = new TablaCamposFrame(this);
        tablas.setVisible(true);
    }//GEN-LAST:event_abreTablaCampoActionPerformed

    private void abreTablaEntidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abreTablaEntidadActionPerformed
       
        tablas = new TablaEntidadesFrame(this);
        tablas.setVisible(true);
    }//GEN-LAST:event_abreTablaEntidadActionPerformed

    private void abreTablaInvestigadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abreTablaInvestigadorActionPerformed
        
        tablas = new TablaCamposFrame(this);
        tablas.setVisible(true);
    }//GEN-LAST:event_abreTablaInvestigadorActionPerformed

    private void abreTablaProyectoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abreTablaProyectoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_abreTablaProyectoActionPerformed

    
    public JButton getAbreTablaCampo() {
        return abreTablaCampo;
    }

    public JButton getAbreTablaEntidad() {
        return abreTablaEntidad;
    }

    public JButton getAbreTablaInvestigador() {
        return abreTablaInvestigador;
    }

    public JButton getAbreTablaProyecto() {
        return abreTablaProyecto;
    }
    
    //MÉTODOS para que el Controlador tenga cualquier botón de cualquier TablaFrame que se hayan habierto;
    public JButton getBtnBorrar(){
        
        return tablas.getBtnCrear();
    }
    public JButton getBtnCancelar(){
        
        return tablas.getBtnCancelar();
    }
    public JButton getBtnCrear(){
        
        return tablas.getBtnCrear();
    }
    public JButton getBtnEditar(){
        
        return tablas.getBtnEditar();
    }
    public JButton getBtnGuardar(){
        
        return tablas.getBtnGuardar();
    }
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton abreTablaCampo;
    private javax.swing.JButton abreTablaEntidad;
    private javax.swing.JButton abreTablaInvestigador;
    private javax.swing.JButton abreTablaProyecto;
    // End of variables declaration//GEN-END:variables
}
