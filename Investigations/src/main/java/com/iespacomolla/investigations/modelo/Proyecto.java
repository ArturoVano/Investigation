
package com.iespacomolla.investigations.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Proyecto {

    private Long proyecto_id;
    private String nombre;
    private Double capital;
    private LocalDate fecha_inicio;
    private List<Entidad> entidades = new ArrayList<>();
    private List<Investigador> investigadores = new ArrayList<>();
    private Campo campo;
    private boolean finalizado;
    private Double coste;
    private LocalDate fecha_fin;
    
    public Proyecto() {
    }

    public Proyecto(String nombre, Double capital, LocalDate fecha_inicio, Campo campo,List<Entidad> e
            , List<Investigador> i,boolean finalizado, Double coste, LocalDate fecha_fin) {
        
        this.nombre = nombre;
        this.capital = capital;
        this.fecha_inicio = fecha_inicio;
        this.campo = campo;
        entidades = e;
        investigadores = i;
        this.finalizado = finalizado;
        
        if (finalizado){
            this.coste = coste;
            this.fecha_fin = fecha_fin;
        }
        else{
            System.out.println("El estudio no está finalizado");
        }  
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

    public void setProyecto_id(Long proyecto_id) {
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
        if (finalizado)
            this.coste = coste;
        else{
            System.out.println("El proyecto no ha finalizado. No puede poner fecha de término");
        }
    }

    public void setFecha_fin(LocalDate fecha_fin) {
        if (finalizado)
            this.fecha_fin = fecha_fin;
        else{
            System.out.println("El proyecto no ha finalizado. No puede poner fecha de término");
        }
    }

    public void setCampo(Campo campo) {
        this.campo = campo;
    }
    
  
}
