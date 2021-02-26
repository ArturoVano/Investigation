
package com.iespacomolla.investigacion.controlador;

import com.iespacomolla.investigacion.dao.CampoDAO;
import com.iespacomolla.investigacion.dao.DAOManager;
import com.iespacomolla.investigacion.dao.EntidadDAO;
import com.iespacomolla.investigacion.dao.InvestigadorDAO;
import com.iespacomolla.investigacion.dao.ProyectoDAO;
import com.iespacomolla.investigacion.dao.mysql.mysqlDAOManagerJdbc;
import com.iespacomolla.investigacion.vista.MenuPrincipal;
import com.iespacomolla.investigacion.vista.campo.TablaCamposFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.naming.Context;
import javax.swing.JButton;
import javax.swing.JFrame;


public class Controlador implements ActionListener{

    private DAOManager maestro;
    private MenuPrincipal menu;
    private Context contexto;
    
    private JButton btnAbreCampo;
    private JButton btnAbreEntidad;
    private JButton btnAbreInvestigador;
    private JButton btnAbreProyecto;
    
    private JButton btnCrear;
    private JButton btnEditar;
    private JButton btnGuardar;
    private JButton btnCancelar;
    private JButton btnBorrar;
    
    public Controlador(String h, String u, String p) {
        
        try{
            maestro = new mysqlDAOManagerJdbc(h, u, p, "investigation");
        }
        catch(SQLException ex){
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
        
        menu = new MenuPrincipal();
        menu.setVisible(true);
        btnAbreCampo = menu.getAbreTablaCampo();
        btnAbreEntidad = menu.getAbreTablaEntidad();
        btnAbreInvestigador = menu.getAbreTablaInvestigador();
        btnAbreProyecto = menu.getAbreTablaProyecto();
        
        btnAbreCampo.addActionListener(this);
        btnAbreEntidad.addActionListener(this);
        btnAbreInvestigador.addActionListener(this);
        btnAbreProyecto.addActionListener(this);
        
        btnCrear = menu.getBtnCrear();
        btnEditar = menu.getBtnEditar();
        btnGuardar = menu.getBtnGuardar();
        btnCancelar = menu.getBtnCancelar();
        btnBorrar = menu.getBtnCancelar();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == btnAbreCampo){
            //reasigno los botónes, pues ahora serán los de TablaCamposFrame:
            asignaBotonesCRUD();
        }
        else if (e.getSource() == btnAbreEntidad){
            //reasigno los botónes, pues ahora serán los de TablaEntidadesFrame:
            asignaBotonesCRUD();
        }
        else if (e.getSource() == btnAbreInvestigador){
            //reasigno los botónes, pues ahora serán los de TablaInvestigadoresFrame:
            asignaBotonesCRUD();
        }
        else if (e.getSource() == btnAbreProyecto){
            //reasigno los botónes, pues ahora serán los de TablaProyectosFrame:
            asignaBotonesCRUD();
        }
    }
    
    public void asignaBotonesCRUD(){
        
        btnCrear = menu.getBtnCrear();
        btnEditar = menu.getBtnEditar();
        btnGuardar = menu.getBtnGuardar();
        btnCancelar = menu.getBtnCancelar();
        btnBorrar = menu.getBtnCancelar();
        
        btnCrear.addActionListener(this);
        btnEditar.addActionListener(this);
        btnGuardar.addActionListener(this);
        btnCancelar.addActionListener(this);
        btnBorrar.addActionListener(this);
    }
    
  
}
