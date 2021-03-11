
package com.iespacomolla.investigacion.vista.campo;

import com.iespacomolla.investigacion.modelo.Campo;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;


public class CampoTableModel extends AbstractTableModel{
    
    
    private List<Campo> datos = new ArrayList<>();
    
    
    public CampoTableModel(List<Campo> datos){
        this.datos = datos;

    }
    
    public void updateModel(List<Campo> campos){
        datos = campos;
    }
    
    @Override
    public String getColumnName(int column){
        
        return switch (column) {
            case 0 -> "campo_id";
            case 1 -> "nombre";
            case 2 -> "ramal";
            default -> "[]";
        };
    }
    
    @Override
    public int getRowCount() {
        return datos.size();
    }

    @Override
    public int getColumnCount() {
        return Campo.class.getDeclaredFields().length;
        
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        Campo preguntado = datos.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return preguntado.getCampo_id();
            case 1:
                return preguntado.getNombre();
            case 2:
                return preguntado.getRamal();
            default:
                return "";
        }
    }

}
