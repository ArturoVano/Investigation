
package com.iespacomolla.investigacion.dao;

import java.util.List;


public interface DAO<T, I> {
    
    void insertar(T o) throws DAOException;
    
    void modificar(T o) throws DAOException;
    
    void eliminar(T o) throws DAOException;
    
    T obtener(I id, int level) throws DAOException;
    
    List <T> obtenerTodos() throws DAOException;
}
