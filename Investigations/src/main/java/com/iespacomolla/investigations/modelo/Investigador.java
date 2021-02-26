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
public class Investigador {

    private long investigador_id;
    private String nombre;
    private String titulo;
    private Double salario;
    private Proyecto proyecto;
    private List<Campo> campos = new ArrayList<>();

    public Investigador() {
    }

    public Investigador(long investigador_id, String nombre, String titulo, Double salario, 
            Proyecto proyecto, List<Campo> campos) {
        this.investigador_id = investigador_id;
        this.nombre = nombre;
        this.titulo = titulo;
        this.salario = salario;
        this.proyecto = proyecto;
        this.campos = campos;
    }

    public long getInvestigador_id() {
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
    
    
}
