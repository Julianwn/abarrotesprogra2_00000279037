/* 
 * 
 */
package objetosdominio;

import java.time.LocalDate;

//** @author Julian Daniel Ramirez Garcia

public class MovimientoEmpacado extends Movimiento<ProductoEmpacado> {
    
    private int cantidad;
    
    public MovimientoEmpacado(String clave, ProductoEmpacado producto, int cantidad) {
        super(clave, producto);
        if (validarCantidad(cantidad)) this.cantidad = cantidad;
    }
    
    public MovimientoEmpacado(String clave, ProductoEmpacado producto, LocalDate fecha, int cantidad) {
        super(clave, producto, fecha);
        if (validarCantidad(cantidad)) this.cantidad = cantidad;
    }
    
    private boolean validarCantidad(int cantidad) {
        if (cantidad <= -1 || cantidad >= 1) {
            return true;
        } else {
            throw new IllegalArgumentException("Can1tidad de movimiento demasiado pequeña (<-1 o >1)");
        }
    }
    
    public float getCantidad() {
        return cantidad;
    }
    
    @Override
    public String toString() {
        return "Movimiento["
                + "Clave: " + getClave()
                + ", " + getProducto().toString()
                + ", Cantidad: " + cantidad
                + ", Fecha: " + getFecha().toString()
                + "]";
    }
}
