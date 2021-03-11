
package com.iespacomolla.investigacion.vista;

import com.iespacomolla.investigacion.vista.campo.TablaCamposFrame;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;


public class MenuPrincipal extends javax.swing.JFrame {
    
    public MenuPrincipal() {
        initComponents();
        
        Toolkit miPantalla = Toolkit.getDefaultToolkit();
        Dimension TamPantalla = miPantalla.getScreenSize();
        int alturaPant = TamPantalla.height;
        int anchuraPant = TamPantalla.width;
        this.setLocation(anchuraPant/3,alturaPant/4);
        
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

        abreTablaCampo.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        abreTablaCampo.setText("Campo");

        abreTablaProyecto.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        abreTablaProyecto.setText("Proyecto");

        abreTablaInvestigador.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        abreTablaInvestigador.setText("Investigador");

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
    



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton abreTablaCampo;
    private javax.swing.JButton abreTablaEntidad;
    private javax.swing.JButton abreTablaInvestigador;
    private javax.swing.JButton abreTablaProyecto;
    // End of variables declaration//GEN-END:variables
}
