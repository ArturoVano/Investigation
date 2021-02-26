/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iespacomolla.investigations.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Entidad {

    private long entidad_id;
    private String nombre;
    private String ubicacion;
    private List<Proyecto> proyectos = new ArrayList<>();
    
    public Entidad(){}

    public Entidad(long entidad_id, String nombre, String ubicacion, List<Proyecto>proyectos) {
        
        this.entidad_id = entidad_id;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.proyectos = proyectos;
    }

    public long getEntidad_id() {
        return entidad_id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public List<Proyecto> getProyectos() {
        return proyectos;
    }

    public void setEntidad_id(long entidad_id) {
        this.entidad_id = entidad_id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setProyectos(List<Proyecto> proyectos) {
        this.proyectos = proyectos;
    }
    
    
}
