
package com.iespacomolla.investigacion.vista.investigador;

import com.iespacomolla.investigacion.modelo.Investigador;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;


class InvestigadorTableModel extends AbstractTableModel{

    
    private List<Investigador> datos = new ArrayList<>();

    public InvestigadorTableModel(List<Investigador> datos) {
        this.datos = datos;
    }
    
    public void updateModel(List<Investigador> investigadores) {
        
        datos = investigadores;
    }
    
    @Override
    public String getColumnName(int column){
        
        return switch (column) {
            case 0 -> "investigador_id";
            case 1 -> "nombre";
            case 2 -> "titulo";
            case 3 -> "salario";
            case 4 -> "proyecto_id";
            case 5 -> "proyecto_name";
            case 6 -> "campos";
            default -> "[]";
        };
    }
    
    @Override
    public int getRowCount() {
        return datos.size();
    }

    @Override
    public int getColumnCount() {
        return Investigador.class.getDeclaredFields().length + 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        Investigador preguntado = datos.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return preguntado.getInvestigador_id();
            case 1:
                return preguntado.getNombre();
            case 2:
                return preguntado.getTitulo();
            case 3:
                return preguntado.getSalario();
            case 4:
                if (preguntado.getProyecto() != null)
                    return preguntado.getProyecto().getProyecto_id();
                else
                    return "[]";
            case 5:
                if (preguntado.getProyecto() != null)
                    return preguntado.getProyecto().getNombre();
                else
                    return "[]";
            case 6:
                return "[infoxicación]";
            default:
                return "";
        }
    }

}
