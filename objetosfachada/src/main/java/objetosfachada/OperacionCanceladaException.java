/* 
 * 
 */
package objetosfachada;

//** @author Julian Daniel Ramirez Garcia

public class OperacionCanceladaException extends Exception {

    public OperacionCanceladaException() {
        super("Operacion Cancelada...");
    }
    
    public OperacionCanceladaException(String msg) {
        super(msg);
    }
}
