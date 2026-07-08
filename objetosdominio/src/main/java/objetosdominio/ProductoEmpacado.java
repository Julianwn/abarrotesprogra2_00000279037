/*
 * Clase ProductoEmpacado, subclase de Producto
 */
package objetosdominio;

/**
 * Representa un producto empacado.
 * Hereda de la clase Producto y agrega la cantidad de unidades.
 * 
 * @author Julian Daniel Ramirez Garcia
 */
public class ProductoEmpacado extends Producto<ProductoEmpacado> {

    private int cantidad;

    /**
     * Constructor que recibe todos sus atributos.
     * @param clave
     * @param nombre
     * @param cantidad
     */
    public ProductoEmpacado(String clave, String nombre, int cantidad) {
        super(clave, nombre, TipoProducto.EM, TipoUnidad.PZ);
        setCantidad(cantidad);
    }
    
    /**
     * Constructor copia que recibe un producto empacado base.
     */
    private ProductoEmpacado(ProductoEmpacado producto) {
        super(producto.getClave(), producto.getNombre(), producto.getTipo(), producto.getUnidad());
        this.cantidad = producto.getCantidad();
    }

    public int getCantidad() {
        return cantidad;
    }

    public final void setCantidad(int cantidad) {
        if (cantidad >= 1) {
            if (cantidad <= 5000) {
                this.cantidad = cantidad;
            } else {
                throw new IllegalArgumentException("Cantidad excedente (>5000)");
            }
        } else {
            throw new IllegalArgumentException("Cantidad insuficiente (<1)");
        }
    }
    
    public void agregarCantidad(int cantidad) {
        setCantidad(this.cantidad + cantidad);
    }
    
    public void restarCantidad(int cantidad) {
        setCantidad(this.cantidad - cantidad);
    }
    
    @Override
    public void actualizar(ProductoEmpacado producto) {
        setNombre(producto.getNombre());
        setCantidad(producto.getCantidad());
    }
    
    @Override
    public ProductoEmpacado copiar() {
        return new ProductoEmpacado(this);
    }

    @Override
    public String toString() {
        return "ProductoEmpacado["
                + "Clave: " + getClave()
                + ", Nombre: " + getNombre()
                + ", Tipo: " + getTipo().toString()
                + ", Unidad: " + getUnidad().toString()
                + ", Cantidad: " + cantidad
                + "]";
    }
}