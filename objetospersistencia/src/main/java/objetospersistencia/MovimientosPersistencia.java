/* 
 * 
 */
package objetospersistencia;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import objetosdominio.Movimiento;

//** @author Julian Daniel Ramirez Garcia

public class MovimientosPersistencia<T extends Movimiento> {

    private final List<T> compras;
    private final List<T> ventas;

    public MovimientosPersistencia() {
        compras = new ArrayList<>();
        ventas = new ArrayList<>();
    }
    
    //** metodos de consulta unica ---------------------------------------------
    public T consultarCompra(String clave) throws PersistenciaException {
        for (T compra : compras) {
            if (compra.getClave().equals(clave)) return compra;
        }
        throw new PersistenciaException("La clave no coincide con ninguna compra");
    }

    public T consultarVenta(String clave) throws PersistenciaException {
        for (T venta : ventas) {
            if (venta.getClave().equals(clave)) return venta;
        }
        throw new PersistenciaException("La clave no coincide con ninguna venta");
    }
    
    //** metodos de consulta a toda la lista -----------------------------------
    public T[] consultarCompras(T[] a) {
        return compras.toArray(a);
    }

    public T[] consultarVentas(T[] a) {
        return ventas.toArray(a);
    }
    
    /*
     * METODOS EN DESUSO PARA CONSULTAR POR PERIODO ----------------------------
    public T[] consultarComprasPorPeriodo(LocalDate inicio, LocalDate fin) {
        return filtrarPorPeriodo(consultarCompras((T[]) new Movimiento[0]), inicio, fin);
    }

    public T[] consultarVentasPorPeriodo(LocalDate inicio, LocalDate fin) {
        return filtrarPorPeriodo(consultarCompras((T[]) new Movimiento[0]), inicio, fin);
    }
     */
    
    //** metodos de registro ---------------------------------------------------
    public void registrarCompra(T movimiento) throws PersistenciaException {
        if (validarFecha(movimiento.getFecha())) {
            compras.add(movimiento);
            System.out.println(movimiento.toString());
        }
    }
    
    public void registrarVenta(T movimiento) throws PersistenciaException {
        if (validarFecha(movimiento.getFecha())) {
            ventas.add(movimiento);
            System.out.println(movimiento.toString());
        }
    }

    //** metodos de eliminacion ------------------------------------------------
    public void eliminarCompra(String clave) throws PersistenciaException {
        compras.remove(consultarCompra(clave));
    }
    
    public void eliminarVenta(String clave) throws PersistenciaException {
        ventas.remove(consultarVenta(clave));
    }
    
    //** metodos de apoyo ------------------------------------------------------
    private boolean validarFecha(LocalDate fecha) throws PersistenciaException {
        LocalDate hoy = LocalDate.now();

        if (fecha == null) {
            throw new PersistenciaException("Fecha inválida");
        }
        if (fecha.isAfter(hoy)) {
            throw new PersistenciaException("Fecha futura inválida");
        }
        if (fecha.getMonth() != hoy.getMonth()) {
            throw new PersistenciaException("Debe ser del mes actual");
        }
        return true;
    }

    /*
     * METODO EN DESUSO PARA FILTRAR POR FECHAS --------------------------------
    private T[] filtrarPorPeriodo(T[] lista, LocalDate inicio, LocalDate fin) {
        List<T> resultado = new ArrayList<>();

        for (T m : lista) {
            if ((m.getFecha().isEqual(inicio) || m.getFecha().isAfter(inicio)) &&
                (m.getFecha().isEqual(fin) || m.getFecha().isBefore(fin))) {
                resultado.add(m);
            }
        }
        return (T[]) resultado.toArray(Movimiento[]::new);
    }
     */
}
