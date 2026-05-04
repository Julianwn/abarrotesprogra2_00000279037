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
public class ProductoEmpacado extends Producto {

    private int cantidad;

    /**
     * Constructor por defecto.
     */
    public ProductoEmpacado() {
        super();
        this.cantidad = 0;
    }

    /**
     * Constructor que recibe un producto base y la cantidad.
     */
    public ProductoEmpacado(Producto p, int cantidad) {

        super(iniciarClaveEM(p.getClave()), p.getNombre(), p.getTipo(), p.getUnidad());
        setCantidad(cantidad);
    }

    /**
     * Constructor que recibe un producto base.
     */
    public ProductoEmpacado(Producto p) {

        super(iniciarClaveEM(p.getClave()), p.getNombre(), p.getTipo(), p.getUnidad());
        this.cantidad = 0;
    }

    /**
     * Genera la clave EM a partir de otra clave.
     */
    public static String iniciarClaveEM(String clave) {
        return "EM" + clave.substring(2);
    }

    /**
     * Sobrescribe la validación de clave para productos empacados.
     */
    @Override
    public boolean validarClave(String clave) {

        return clave != null && !clave.equals("") && clave.matches("EM[0-9]{3}");
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {

        if (cantidad >= 1) {
            this.cantidad = cantidad;
        } else {
            System.out.println("Error Cantidad insuficiente...");
        }
    }

    @Override
    public String toString() {
        return "ProductoEmpacado("
                + "clave: " + clave
                + ", nombre: " + nombre
                + ", tipo: " + tipo
                + ", unidad: " + unidad
                + ", cantidad: " + cantidad
                + ")";
    }
}