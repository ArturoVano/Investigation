/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iespacomolla.investigacion.dao;

/**
 *
 * CLASE DAO MAESTRA, que devuelve cada DAO particular, me permite acceder a cada DAO de mi app.
 */
public interface DAOManager {
    
    CampoDAO getCampoDAO();
    
    EntidadDAO getEntidadDAO();
    
    InvestigadorDAO getInvestigadorDAO();
    
    ProyectoDAO getProyectoDAO();
}
