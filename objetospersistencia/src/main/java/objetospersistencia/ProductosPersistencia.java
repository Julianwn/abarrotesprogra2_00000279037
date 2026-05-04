/* 
 * 
 */
package objetospersistencia;

import java.util.ArrayList;
import java.util.List;
import objetosdominio.Producto;
import exceptions.PersistenciaException;

//** @author Julian Daniel Ramirez Garcia

public class ProductosPersistencia {

    private final List<Producto> listaProductos = new ArrayList<>();

    public void agregarProducto(Producto producto) throws PersistenciaException {
        if (producto == null) {
            throw new PersistenciaException("Producto nulo");
        }

        if (producto.getClave() == null || !producto.getClave().matches("(GR|EM)\\d{3}")) {
            throw new PersistenciaException("Clave inválida");
        }

        if (buscarProducto(producto.getClave()) != null) {
            throw new PersistenciaException("Producto duplicado");
        }

        if (producto.getNombre() == null || producto.getNombre().isEmpty()) {
            throw new PersistenciaException("Nombre requerido");
        }

        if (!producto.getUnidad().matches("KG|L|PZ")) {
            throw new PersistenciaException("Unidad inválida");
        }
        
        if (producto.getTipo() != 'E' && producto.getTipo() != 'G') {
            throw new PersistenciaException("Tipo inválido");
        }

        listaProductos.add(producto);
    }

    public Producto buscarProducto(String clave) {
        for (Producto p : listaProductos) {
            if (p.getClave().equals(clave)) {
                return p;
            }
        }
        return null;
    }

    public void actualizarProducto(Producto producto) throws PersistenciaException {
        Producto existente = buscarProducto(producto.getClave());

        if (existente == null) {
            throw new PersistenciaException("Producto no existe");
        }

        eliminarProducto(producto.getClave());
        agregarProducto(producto);
    }

    public void eliminarProducto(String clave) throws PersistenciaException {
        Producto p = buscarProducto(clave);

        if (p == null) {
            throw new PersistenciaException("Producto no encontrado");
        }

        listaProductos.remove(p);
    }

    public List<Producto> consultarProductos(String tipo, String unidad) {
        List<Producto> resultado = new ArrayList<>();

        for (Producto p : listaProductos) {
            boolean coincide = true;

            if (tipo != null && !String.valueOf(p.getTipo()).equals(tipo)) {
                coincide = false;
            }

            if (unidad != null && !p.getUnidad().equals(unidad)) {
                coincide = false;
            }

            if (coincide) {
                resultado.add(p);
            }
        }

        return resultado;
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }
}