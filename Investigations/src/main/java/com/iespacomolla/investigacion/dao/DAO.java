/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iespacomolla.investigacion.dao;

import java.util.List;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public interface DAO<T, I> {
    
    void insertar(T o) throws DAOException;
    
    void modificar(T o) throws DAOException;
    
    void eliminar(T o) throws DAOException;
    
    T obtener(I id) throws DAOException;
    
    List <T> obtenerTodos() throws DAOException;
}
