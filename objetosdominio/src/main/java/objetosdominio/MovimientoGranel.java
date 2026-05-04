/*
 * Clase MovimientoGranel, subclase de Movimiento
 */
package objetosdominio;

/**
 * Representa un movimiento asociado a un producto a granel.
 * 
 * @author Julian Daniel Ramirez Garcia
 */
public class MovimientoGranel extends Movimiento {

    private ProductoGranel producto;

    /**
     * Constructor por defecto
     */
    public MovimientoGranel() {
        super();
        this.producto = null;
    }

    /**
     * Constructor con parámetros
     */
    public MovimientoGranel(String fecha, boolean procesado, ProductoGranel producto) {
        super(fecha, procesado);
        setProducto(producto);
    }

    public ProductoGranel getProducto() {
        return producto;
    }

    public void setProducto(ProductoGranel producto) {

        if (producto != null) {
            this.producto = producto;
        } else {
            System.out.println("Error Producto granel invalido...");
        }
    }

    @Override
    public String toString() {
        return "MovimientoGranel("
                + "clave: " + getClave()
                + ", fecha: " + getFecha()
                + ", procesado: " + isProcesado()
                + ", producto: " + producto
                + ")";
    }
}