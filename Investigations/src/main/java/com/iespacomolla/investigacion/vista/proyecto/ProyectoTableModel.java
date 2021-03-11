
package com.iespacomolla.investigacion.vista.proyecto;

import com.iespacomolla.investigacion.modelo.Proyecto;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;


class ProyectoTableModel extends AbstractTableModel{

    
    private List<Proyecto> datos = new ArrayList<>();

    public ProyectoTableModel(List<Proyecto> datos) {
        this.datos = datos;
    }
    
    public void updateModel(List<Proyecto> proyectos) {
        
        datos = proyectos;
    }
    
    @Override
    public String getColumnName(int column){
        
        return switch (column) {
            case 0 -> "investigador_id";
            case 1 -> "nombre";
            case 2 -> "capital";
            case 3 -> "fecha_inicio";
            case 4 -> "entidades";
            case 5 -> "investigadores";
            case 6 -> "finalizado";
            case 7 -> "coste";
            case 8 -> "campo_name";
            case 9 -> "campo_id";
            case 10 -> "fecha_fin";
            default -> "[]";
        };
    }
    
    @Override
    public int getRowCount() {
        return datos.size();
    }

    @Override
    public int getColumnCount() {
        return Proyecto.class.getDeclaredFields().length + 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        Proyecto preguntado = datos.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return preguntado.getProyecto_id();
            case 1:
                return preguntado.getNombre();
            case 2:
                return preguntado.getCapital();
            case 3:
                return preguntado.getFecha_inicio();
            case 4:
                return "[infotoxicación]"; //!Cuidado
            case 5:
                return "[infoxicación ]"; //!Cuidado
            case 6:
                return preguntado.isFinalizado();
            case 7:
                return preguntado.getCoste();
            case 8:
                if (preguntado.getCampo() != null)
                    return preguntado.getCampo().getNombre();
                else
                    return "[]";
            case 9:
                if (preguntado.getCampo() != null)
                    return preguntado.getCampo().getCampo_id();
                else
                    return "[]";
            case 10:
                return preguntado.getFecha_fin();
            default:
                return "";
        }
    }

}
