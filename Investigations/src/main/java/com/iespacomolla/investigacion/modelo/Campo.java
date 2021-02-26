/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iespacomolla.investigacion.modelo;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Campo {

    private long campo_id;
    private String nombre;
    private String ramal;

    public Campo() {
    }

    public Campo(long campo_id, String nombre, String ramal) {
        this.campo_id = campo_id;
        this.nombre = nombre;
        this.ramal = ramal;
    }

    public long getCampo_id() {
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
    
    
}
