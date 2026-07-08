/* 
 * 
 */
package objetospersistencia;

import java.util.ArrayList;
import java.util.List;
import objetosdominio.Producto;
import objetosdominio.TipoProducto;
import objetosdominio.TipoUnidad;

//** @author Julian Daniel Ramirez Garcia

public class ProductosPersistencia<T extends Producto> {

    private final List<T> catalogo;
    
    public ProductosPersistencia() {
        catalogo = new ArrayList<>();
    }

    //** metodos de consulta unica ---------------------------------------------
    public T consultarProducto(String clave) throws PersistenciaException {
        for (T producto : catalogo) {
            if (producto.getClave().equals(clave)) {
                return producto;
            }
        }
        throw new PersistenciaException("La clave no coincide con ningun producto");
    }
    
    //** metodos de consulta a toda la lista -----------------------------------
    public T[] consultarProductos(T[] a) {
        return this.catalogo.toArray(a);
    }
    
    public Object[] consultarProductos() {
        return this.catalogo.toArray();
    }
    
    //** metodos para consulta filtrada ----------------------------------------
    public T[] consultarProductos(TipoProducto tipo, TipoUnidad unidad) {
        List<T> productos = new ArrayList<>();
        
        for (T producto : catalogo) {
            if (tipo == producto.getTipo() && producto.getUnidad().equals(unidad)) {
                productos.add(producto);
            }
        }
        return (T[]) productos.toArray(Producto[]::new);
    }
    
    //** metodos para registro unico -------------------------------------------
    public void agregarProducto(T producto) throws PersistenciaException {
        catalogo.add(producto);
    }
    
    /** metodos de actualizacion unica -----------------------------------------
    public void actualizarProducto(String clave, T producto) throws PersistenciaException {
        consultarProducto(clave).actualizar(producto);
    }
     */
    
    //** metodos de eliminacion unica ------------------------------------------
    public void eliminarProducto(T producto) throws PersistenciaException {
        catalogo.remove(producto);
    }
    
    //** metodos de apoyo ------------------------------------------------------
    private boolean buscarProducto(String clave) throws PersistenciaException {
        for (T producto : catalogo) {
            if (producto.getClave().equals(clave)) return true;
        }
        return false;
    }
    
}
