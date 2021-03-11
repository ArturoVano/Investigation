
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
