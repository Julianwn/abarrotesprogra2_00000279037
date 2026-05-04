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
public class ProductoGranel extends Producto {

    private float cantidad;

    /**
     * Constructor por defecto.
     */
    public ProductoGranel() {
        super();
        this.cantidad = 0f;
    }

    /**
     * Constructor que recibe un producto base y la cantidad.
     */
    public ProductoGranel(Producto p, float cantidad) {
        super(iniciarClaveGR(p.getClave()), p.getNombre(), p.getTipo(), p.getUnidad());
        setCantidad(cantidad);
    }

    /**
     * Constructor que recibe un producto base.
     */
    public ProductoGranel(Producto p) {
        super(iniciarClaveGR(p.getClave()), p.getNombre(), p.getTipo(), p.getUnidad());
        this.cantidad = 0f;
    }

    /**
     * Genera la clave GR a partir de otra clave.
     */
    public static String iniciarClaveGR(String clave) {
        return "GR" + clave.substring(2);
    }

    /**
     * Sobrescribe la validación de clave para productos a granel.
     */
    @Override
    public boolean validarClave(String clave) {
        return clave != null && !clave.equals("") && clave.matches("GR[0-9]{3}");
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {

        if (cantidad > 0.01f) {
            this.cantidad = cantidad;
        } else {
            System.out.println("Error Cantidad insuficiente...");
        }
    }

    @Override
    public String toString() {
        return "ProductoGranel("
                + "clave: " + clave
                + ", nombre: " + nombre
                + ", tipo: " + tipo
                + ", unidad: " + unidad
                + ", cantidad: " + cantidad
                + ")";
    }
}