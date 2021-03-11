
package com.iespacomolla.investigacion.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Proyecto")
public class Proyecto {
    
    @Id
    @Column(name="proyecto_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long proyecto_id;
    
    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "capital")
    private Double capital;
    
    @Column(name = "fecha_inicio")
    private LocalDate fecha_inicio;
    
    @ManyToMany(mappedBy="proyectos") //No estaría mal indicar: si entidades.size = 0 -> delete this.Proyecto
    private List<Entidad> entidades = new ArrayList<>();
    
    @OneToMany(mappedBy="proyecto", cascade=CascadeType.REFRESH) 
    private List<Investigador> investigadores = new ArrayList<>();
    
    
    @ManyToOne()
    @JoinColumn(name="campo_id")
    private Campo campo;
    
    @Column(name = "finalizado")
    private boolean finalizado;
    
    @Column(name = "coste")
    private Double coste;
    
    @Column(name = "fecha_fin")
    private LocalDate fecha_fin;
    
    public Proyecto() {
    }

    public Proyecto(String nombre, Double capital, LocalDate fecha_inicio, Campo campo,
            List<Entidad> entidades, List<Investigador> investigadores, boolean finalizado,
            Double coste, LocalDate fecha_fin) {

        this.nombre = nombre;
        this.capital = capital;
        this.fecha_inicio = fecha_inicio;
        this.campo = campo;
        this.entidades = entidades;
        this.investigadores = investigadores;
        this.finalizado = finalizado;
        this.coste = coste;
        this.fecha_fin = fecha_fin;
    }

    public Long getProyecto_id() {
        return proyecto_id;
    }

    public String getNombre() {
        return nombre;
    }

    public Double getCapital() {
        return capital;
    }

    public LocalDate getFecha_inicio() {
        return fecha_inicio;
    }

    public List<Entidad> getEntidades() {
        return entidades;
    }

    public List<Investigador> getInvestigadores() {
        return investigadores;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public Double getCoste() {
        return coste;
    }

    public LocalDate getFecha_fin() {
        return fecha_fin;
    }

    public Campo getCampo() {
        return campo;
    }

    public void setProyecto_id(long proyecto_id) {
        this.proyecto_id = proyecto_id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCapital(Double capital) {
        this.capital = capital;
    }

    public void setFecha_inicio(LocalDate fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public void setEntidades(List<Entidad> entidades) {
        this.entidades = entidades;
    }

    public void setInvestigadores(List<Investigador> investigadores) {
        this.investigadores = investigadores;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }

    public void setCoste(Double coste) {   
            this.coste = coste;
    }

    public void setFecha_fin(LocalDate fecha_fin) {
            this.fecha_fin = fecha_fin;
    }

    public void setCampo(Campo campo) {
        this.campo = campo;
    }
    
  @Override
    public String toString(){
        return proyecto_id + ", " + nombre;
    }
}
