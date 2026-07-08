/*
 * Clase ProductoGranel, subclase de Producto
 */
package objetosdominio;

/**
 * Representa un producto vendido a granel.
 * Hereda de la clase Producto y agrega la cantidad en unidades de peso o volumen.
 * 
 * @author Julian Daniel Ramirez Garcia
 */
public class ProductoGranel extends Producto<ProductoGranel> {

    private float cantidad;
    
    /**
     * Constructor que recibe todos sus atributos.
     * @param clave
     * @param nombre
     * @param unidad
     * @param cantidad
     */
    public ProductoGranel(String clave, String nombre, TipoUnidad unidad, float cantidad) {
        super(clave, nombre, TipoProducto.GR, validarUnidad(unidad));
        setCantidad(cantidad);
    }
    
    /**
     * Constructor copia que recibe un producto granel base.
     */
    private ProductoGranel(ProductoGranel producto) {
        super(producto.getClave(), producto.getNombre(), producto.getTipo(), producto.getUnidad());
        this.cantidad = producto.getCantidad();
    }
    
    private static TipoUnidad validarUnidad(TipoUnidad unidad) {
        if (unidad != TipoUnidad.PZ) {
            return unidad;
        } else {
            throw new IllegalArgumentException("Tipo unidad invalido para producto granel (KG, g, L)");
        }
    }
    
    public float getCantidad() {
        return cantidad;
    }

    public final void setCantidad(float cantidad) {
        if (cantidad >= 0.01f) {
            switch (getUnidad()) {
                case KG -> {
                    if (cantidad <= 1500f) {
                        this.cantidad = cantidad;
                    } else {
                        throw new IllegalArgumentException("Cantidad excedente (1,500.00kg)");
                    }
                }
                case g -> {
                    if (cantidad <= 1500000f) {
                        this.cantidad = cantidad;
                    } else {
                        throw new IllegalArgumentException("Cantidad excedente (1,500,000.00g");
                    }
                }
                case L -> {
                    if (cantidad <= 3000f) {
                        this.cantidad = cantidad;
                    } else {
                        throw new IllegalArgumentException("Cantidad excedente (3,000.00L");
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("Cantidad insuficiente (<0.01)");
        }
    }
    
    public void agregarCantidad(float cantidad) {
        setCantidad(this.cantidad + cantidad);
    }
    
    public void restarCantidad(float cantidad) {
        setCantidad(this.cantidad - cantidad);
    }

    @Override
    public void actualizar(ProductoGranel producto) {
        setNombre(producto.getNombre());
        setUnidad(producto.getUnidad());
        setCantidad(producto.getCantidad());
    }
    
    @Override
    public ProductoGranel copiar() {
        return new ProductoGranel(this);
    }
    
    @Override
    public String toString() {
        return "ProductoGranel["
                + "Clave: " + getClave()
                + ", Nombre: " + getNombre()
                + ", Tipo: " + getTipo().toString()
                + ", Unidad: " + getUnidad().toString()
                + ", Cantidad: " + cantidad
                + "]";
    }
}