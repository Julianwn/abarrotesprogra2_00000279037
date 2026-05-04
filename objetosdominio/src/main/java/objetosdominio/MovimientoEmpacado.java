/*
 * Clase MovimientoEmpacado, subclase de Movimiento
 */
package objetosdominio;

/**
 * Representa un movimiento asociado a un producto empacado.
 * 
 * @author Julian Daniel Ramirez Garcia
 */
public class MovimientoEmpacado extends Movimiento {

    private ProductoEmpacado producto;

    /**
     * Constructor por defecto
     */
    public MovimientoEmpacado() {
        super();
        this.producto = null;
    }

    /**
     * Constructor con parámetros
     */
    public MovimientoEmpacado(String fecha, boolean procesado, ProductoEmpacado producto) {
        super(fecha, procesado);
        setProducto(producto);
    }

    public ProductoEmpacado getProducto() {
        return producto;
    }

    public void setProducto(ProductoEmpacado producto) {

        if (producto != null) {
            this.producto = producto;
        } else {
            System.out.println("Error Producto empacado invalido...");
        }
    }

    @Override
    public String toString() {
        return "MovimientoEmpacado("
                + "clave: " + getClave()
                + ", fecha: " + getFecha()
                + ", procesado: " + isProcesado()
                + ", producto: " + producto
                + ")";
    }
}