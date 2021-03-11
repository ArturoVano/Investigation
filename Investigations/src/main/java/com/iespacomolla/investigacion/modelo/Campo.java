
package com.iespacomolla.investigacion.modelo;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="Campo")
public class Campo {
    
    @Id
    @Column(name = "campo_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long campo_id;
    
    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "ramal")
    private String ramal;
    

    public Campo() {
    }

    public Campo(String nombre, String ramal) {
        
        this.nombre = nombre;
        this.ramal = ramal;
    }

    public Long getCampo_id() {
        return campo_id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRamal() {
        return ramal;
    }

    public void setCampo_id(long campo_id) {
        this.campo_id = campo_id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setRamal(String ramal) {
        this.ramal = ramal;
    }
    
    @Override
    public String toString(){
        return campo_id + ", " + nombre;
    }
}
