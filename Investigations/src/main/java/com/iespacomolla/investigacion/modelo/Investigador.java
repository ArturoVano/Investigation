
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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Investigador")
public class Investigador {
    
    @Id
    @Column(name = "investigador_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long investigador_id;
    
    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "titulo")
    private String titulo;
    
    @Column(name="salario")
    private Double salario;
    
    @ManyToOne(cascade={/*CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,*/CascadeType.REFRESH})
    @JoinColumn(name="proyecto_id")
    private Proyecto proyecto;
    
    @ManyToMany()
    @JoinTable(name="investigador_campo", joinColumns={@JoinColumn(name="investigador_id")}, 
            inverseJoinColumns={@JoinColumn(name="campo_id")})
    private List<Campo> campos = new ArrayList<>();

    
    public Investigador() {
    }

    public Investigador(String nombre, String titulo, Double salario, 
            Proyecto proyecto, List<Campo> campos) {

        this.nombre = nombre;
        this.titulo = titulo;
        this.salario = salario;
        this.proyecto = proyecto;
        this.campos = campos;
    }

    public Long getInvestigador_id() {
        return investigador_id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTitulo() {
        return titulo;
    }

    public Double getSalario() {
        return salario;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public List<Campo> getCampos() {
        return campos;
    }

    public void setInvestigador_id(long investigador_id) {
        this.investigador_id = investigador_id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public void setCampos(List<Campo> campos) {
        this.campos = campos;
    }
    
    @Override
    public String toString(){
        return investigador_id + ", " + nombre;
    }
}
