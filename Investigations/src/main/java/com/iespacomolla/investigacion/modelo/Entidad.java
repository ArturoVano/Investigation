
package com.iespacomolla.investigacion.modelo;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name="Entidad")
public class Entidad {
    
    @Id
    @Column (name="entidad_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long entidad_id;
    
    @Column (name="nombre")
    private String nombre;
    
    @Column (name="ubicacion")
    private String ubicacion;
    
    @ManyToMany(cascade=CascadeType.REFRESH)
    @JoinTable(name="entidad_proyecto", joinColumns={@JoinColumn(name="entidad_id")}, 
            inverseJoinColumns={@JoinColumn(name="proyecto_id")})
    private List<Proyecto> proyectos = new ArrayList<>();
    
    
    public Entidad(){}

    public Entidad(String nombre, String ubicacion, List<Proyecto>proyectos) {
        
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.proyectos = proyectos;
    }

    public Long getEntidad_id() {
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
    
    @Override
    public String toString(){
        return entidad_id + ", " + nombre+ ", " + ubicacion; 
    }
}
