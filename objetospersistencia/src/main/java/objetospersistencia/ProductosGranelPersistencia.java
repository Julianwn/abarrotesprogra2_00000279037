/* 
 * 
 */
package objetospersistencia;

import java.util.ArrayList;
import java.util.List;
import objetosdominio.ProductoGranel;
import exceptions.PersistenciaException;

//** @author Julian Daniel Ramirez Garcia

public class ProductosGranelPersistencia {

    private final List<ProductoGranel> inventario = new ArrayList<>();

    public void agregarProducto(ProductoGranel producto) throws PersistenciaException {
        if (producto.getCantidad() <= 0.01f) {
            throw new PersistenciaException("Cantidad inválida");
        }

        if (buscarProducto(producto.getClave()) != null) {
            throw new PersistenciaException("Producto ya existe en inventario");
        }

        inventario.add(producto);
    }

    public ProductoGranel buscarProducto(String clave) {
        for (ProductoGranel p : inventario) {
            if (p.getClave().equals(clave)) {
                return p;
            }
        }
        return null;
    }

    public void actualizarProducto(ProductoGranel producto) throws PersistenciaException {
        if (producto.getCantidad() <= 0) {
            throw new PersistenciaException("Cantidad inválida");
        }

        ProductoGranel existente = buscarProducto(producto.getClave());

        if (existente == null) {
            throw new PersistenciaException("Producto no existe");
        }

        inventario.remove(existente);
        inventario.add(producto);
    }

    public void eliminarProducto(String clave) throws PersistenciaException {
        ProductoGranel p = buscarProducto(clave);

        if (p == null) {
            throw new PersistenciaException("No existe en inventario");
        }

        inventario.remove(p);
    }

    public List<ProductoGranel> consultarInventario() {
        return inventario;
    }
}