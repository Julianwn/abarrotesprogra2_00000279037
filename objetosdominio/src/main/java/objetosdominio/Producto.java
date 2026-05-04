/*
 * Clase Producto, superclase para ProductoEmpacado y ProductoGranel
 */
package objetosdominio;

import java.util.Objects;

/**
 * Representa un producto genérico dentro del sistema de abarrotes.
 * Esta clase sirve como superclase para los diferentes tipos de productos.
 * 
 * @author Julian Daniel Ramirez Garcia
 */
public class Producto {

    protected String clave, nombre;
    protected char tipo;
    protected String unidad;

    /**
     * Constructor por defecto.
     * Inicializa todos los atributos con valores vacíos.
     */
    public Producto() {
        this.clave = "";
        this.nombre = "";
        this.tipo = '\0';
        this.unidad = "";
    }

    /**
     * Constructor completo.
     * Utiliza siempre los métodos de validación.
     */
    public Producto(String clave, String nombre, char tipo, String unidad) {
        setClave(clave);
        setNombre(nombre);
        setTipo(tipo);
        setUnidad(unidad);
    }

    /**
     * Constructor que recibe solo la clave.
     */
    public Producto(String clave) {

        if (validarClave(clave)) {
            this.clave = clave;
        } else {
            System.out.println("Error Clave invalida...");
            this.clave = "";
        }

        this.nombre = "";
        this.tipo = '\0';
        this.unidad = "";
    }

    /**
     * Constructor copia.
     */
    public Producto(Producto p) {
        this.clave = p.clave;
        this.nombre = p.nombre;
        this.tipo = p.tipo;
        this.unidad = p.unidad;
    }

    public String getClave() {
        return clave;
    }

    /**
     * Asigna la clave del producto.
     * Este método es final para evitar romper la integridad del objeto.
     */
    public final void setClave(String clave) {

        if (validarClave(clave)) {
            this.clave = clave;
        } else {
            System.out.println("Error Clave invalida...");
        }
    }

    /**
     * Valida la clave del producto.
     * Formato permitido: GR000 o EM000
     */
    public boolean validarClave(String clave) {
        return clave != null && !clave.equals("") && clave.matches("(GR|EM)[0-9]{3}");
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {

        if (validarNombre(nombre)) {
            this.nombre = nombre;
        } else {
            System.out.println("Error Nombre vacio...");
        }
    }

    public boolean validarNombre(String nombre) {
        return nombre != null && !nombre.equals("");
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {

        if (validarTipo(tipo)) {
            this.tipo = tipo;
        } else {
            System.out.println("Error Tipo invalido...");
        }
    }

    public boolean validarTipo(char tipo) {
        return tipo != '\0' && String.valueOf(tipo).matches("[EG]");
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {

        if (validarUnidad(unidad)) {
            this.unidad = unidad;
        } else {
            System.out.println("Error Unidad invalida...");
        }
    }

    public boolean validarUnidad(String unidad) {
        return unidad != null && !unidad.equals("") && unidad.matches("(KG|L|g|PZ)");
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.clave);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        final Producto p2 = (Producto) obj;

        return Objects.equals(this.clave, p2.clave);
    }

    @Override
    public String toString() {
        return "Producto("
                + "clave: " + clave
                + ", nombre: " + nombre
                + ", tipo: " + tipo
                + ", unidad: " + unidad
                + ")";
    }
}