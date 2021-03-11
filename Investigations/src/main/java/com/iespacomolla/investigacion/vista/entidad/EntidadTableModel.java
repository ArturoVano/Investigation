
package com.iespacomolla.investigacion.vista.entidad;

import com.iespacomolla.investigacion.modelo.Entidad;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;


public class EntidadTableModel extends AbstractTableModel {
    
    private List<Entidad> datos = new ArrayList<>();
    
    public EntidadTableModel(List<Entidad> datos) {
        this.datos = datos;
    }
    
    public void updateModel(List<Entidad> entidades) {
        datos = entidades;
    }
    
    @Override
    public String getColumnName(int column){
        
        return switch (column) {
            case 0 -> "entidad_id";
            case 1 -> "nombre";
            case 2 -> "ubicacion";
            case 3 -> "proyectos";
            default -> "[]";
        };
    }
    
    @Override
    public int getRowCount() {
        return datos.size();
    }

    @Override
    public int getColumnCount() {
        return Entidad.class.getDeclaredFields().length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        Entidad preguntado = datos.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return preguntado.getEntidad_id();
            case 1:
                return preguntado.getNombre();
            case 2:
                return preguntado.getUbicacion();
            case 3:
                return "[infoxicación]";
            default:
                return "";
        }
    }
    
    
}
