/* 
 * 
 */
package exceptions;

//** @author Julian Daniel Ramirez Garcia

public class PersistenciaException extends Exception {
    
    public PersistenciaException() {
        super("Se rompio la persistencia");
    }
    
    public PersistenciaException(String mensaje) {
        super(mensaje);
    }
}
