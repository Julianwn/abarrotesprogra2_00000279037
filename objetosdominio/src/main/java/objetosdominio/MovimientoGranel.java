/* 
 * 
 */
package objetosdominio;

import java.time.LocalDate;

//** @author Julian Daniel Ramirez Garcia

public class MovimientoGranel extends Movimiento<ProductoGranel> {
    
    private float cantidad;
    
    public MovimientoGranel(String clave, ProductoGranel producto, float cantidad) {
        super(clave, producto);
        if (validarCantidad(cantidad)) this.cantidad = cantidad;
    }
    
    public MovimientoGranel(String clave, ProductoGranel producto, LocalDate fecha, float cantidad) {
        super(clave, producto, fecha);
        if (validarCantidad(cantidad)) this.cantidad = cantidad;
    }
    
    private boolean validarCantidad(float cantidad) {
        if (cantidad <= -0.01 || cantidad >= 0.01) {
            return true;
        } else {
            throw new IllegalArgumentException("Can1tidad de movimiento demasiado pequeña (<-0.01 o >0.01)");
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
