
package com.iespacomolla.investigacion.dao;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class DAOException extends Exception{

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOException(Throwable cause) {
        super(cause);
    }

    
}
