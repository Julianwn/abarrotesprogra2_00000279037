/* 
 * 
 */
package objetosservicio.objetosvalidador;

//** @author Julian Daniel Ramirez Garcia

public class ValidadorClaves {
    
    public static boolean esClaveConsecutivaMovimiento(String clave, String prefijo) {
        return clave.matches(prefijo + "-[0-9]+");
    }
    
    public static boolean esClaveConsecutivoProducto(String clave, String prefijo) {
        return clave.matches(prefijo + "-[0-9]+");
    }
}
