/*
 * Clase Producto, superclase para ProductoEmpacado y ProductoGranel
 */
package objetosdominio;

import java.util.Objects;
import objetosservicio.objetosvalidador.ValidadorStrings;

/**
 * Representa un producto genérico dentro del sistema de abarrotes.
 * Esta clase sirve como superclase para los diferentes tipos de productos.
 * 
 * @author Julian Daniel Ramirez Garcia
 * @param <T>
 */
public class Producto<T extends Producto> {

    private String clave;
    private String nombre;
    private TipoProducto tipo;
    private TipoUnidad unidad;

    /**
     * Constructor completo.
     * Utiliza siempre los métodos de validación.
     * @param clave
     * @param nombre
     * @param tipo
     * @param unidad
     */
    public Producto(String clave, String nombre, TipoProducto tipo, TipoUnidad unidad) {
        this.tipo = tipo;
        if (validarClave(clave)) this.clave = clave;
        if (!ValidadorStrings.estaVacio(nombre)) {
            this.nombre = nombre;
        } else {
            throw new NullPointerException("Nombre vacio");
        }
        if (validarUnidad(unidad)) this.unidad = unidad;
    }
    
    /**
     * Constructor copia.
     */
    private Producto(Producto producto) {
        this.clave = producto.clave;
        this.nombre = producto.nombre;
        this.tipo = producto.tipo;
        this.unidad = producto.unidad;
    }

    /**
     * Valida la clave del producto.
     * Formato permitido: GR0 o EM0
     */
    private boolean validarClave(String clave) {
        if (ValidadorStrings.estaVacio(clave)) throw new NullPointerException("Clave invalida (null)");
        if (clave.startsWith(tipo.toString())) {
            if (clave.matches(tipo.toString() + "-[0-9]+")) {
                return true;
            } else {
                throw new IllegalArgumentException("Formato de clave invalido (EM-0 o GR-0)");
            }
        } else {
            throw new IllegalArgumentException("La clave del producto no corresponde con el tipo de producto");
        }
    }
    
    public String getClave() {
        return clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (!ValidadorStrings.estaVacio(nombre)) {
            this.nombre = nombre;
        } else {
            throw new NullPointerException("Nombre vacio");
        }
        
    }

    public TipoProducto getTipo() {
        return tipo;
    }

    public void setTipo(TipoProducto tipo) {
        this.tipo = tipo;
    }

    private boolean validarUnidad(TipoUnidad unidad) {
        switch (tipo) {
            case EM -> {
                if (unidad == TipoUnidad.PZ) {
                    return true;
                } else {
                    throw new IllegalArgumentException("Tipo de unidad invalido para producto " + tipo.toString() + " (PZ)");
                }
            }
            case GR -> {
                if (unidad != TipoUnidad.PZ) {
                    return true;
                } else {
                    throw new IllegalArgumentException("Tipo de unidad invalido para producto " + tipo.toString() + " (KG, g, L)");
                }
            }
        }
        return false;
    }
    
    public TipoUnidad getUnidad() {
        return unidad;
    }

    public void setUnidad(TipoUnidad unidad) {
        if (validarUnidad(unidad)) this.unidad = unidad;
    }

    public void actualizar(T producto) {
        setNombre(producto.getNombre());
        setUnidad(producto.getUnidad());
    }
    
    public Producto copiar() {
        return new Producto(this);
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
        return "Producto["
                + "Clave: " + clave
                + ", Nombre: " + nombre
                + ", Tipo: " + tipo.toString()
                + ", Unidad: " + unidad.toString()
                + "]";
    }
}
