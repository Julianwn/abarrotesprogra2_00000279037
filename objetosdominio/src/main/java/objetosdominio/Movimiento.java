/*
 * Clase Movimiento, superclase para MovimientoEmpacado y MovimientoGranel
 */
package objetosdominio;

import java.time.LocalDate;
import objetosservicio.objetosvalidador.ValidadorStrings;

/**
 * Representa un movimiento dentro del sistema.
 * Sirve como superclase para los movimientos de productos empacados y a granel.
 * 
 * @author Julian Daniel Ramirez Garcia
 * @param <T>
 */
public class Movimiento<T extends Producto> {

    private String clave;
    private T producto;
    private LocalDate fecha;

    /**
     * Constructor con producto.
     * 
     * @param clave
     * @param producto
     */
    public Movimiento(String clave, T producto) {
        if (validarProducto(producto)) this.producto = producto;
        if (validarClave(clave)) this.clave = clave;
        this.fecha = LocalDate.now();
    }
    
    /**
     * Constructor con todos los parametros.
     * @param clave
     * @param fecha
     * @param producto 
     */
    public Movimiento(String clave, T producto, LocalDate fecha) {
        if (validarProducto(producto)) this.producto = producto;
        if (validarClave(clave)) this.clave = clave;
        if (validarFecha(fecha)) this.fecha = fecha;
    }
    
    private boolean validarClave(String clave) {
        if (ValidadorStrings.estaVacio(clave)) throw new NullPointerException("Clave invalida (null)");
        String tipo = producto.getTipo().toString();
        
        if (clave.substring(4, 6).equals(tipo)) {
            if (clave.matches("(COM|VEN)-" + tipo + "-[0-9]+")) {
                return true;
            } else {
                throw new IllegalArgumentException("Formato de clave invalido (COM-" + tipo + "-0");
            }
        }
        throw new IllegalArgumentException("La clave del movimiento no correspondo con el tipo de producto");
    }
    
    public String getClave() {
        return clave;
    }
    
    private boolean validarProducto(T producto) {
        if (producto != null) {
            return true;
        } else {
            throw new NullPointerException("Producto invalida (null)");
        }
    }
    
    public T getProducto() {
        return (T) producto.copiar();
    }
    
    private boolean validarFecha(LocalDate fecha) {
        if (fecha != null) {
            return true;
        } else {
            throw new NullPointerException("Fecha invalida (null)");
        }
    }
    
    public LocalDate getFecha() {
        return fecha;
    }
    
    @Override
    public String toString() {
        return "Movimiento["
                + "Clave: " + clave
                + ", " + producto.toString()
                + ", Fecha: " + fecha.toString()
                + "]";
    }
}
