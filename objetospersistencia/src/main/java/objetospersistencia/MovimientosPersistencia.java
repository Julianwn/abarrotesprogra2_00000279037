/* 
 * 
 */
package objetospersistencia;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import objetosdominio.Movimiento;
import exceptions.PersistenciaException;

//** @author Julian Daniel Ramirez Garcia

public class MovimientosPersistencia {

    private final List<Movimiento> compras = new ArrayList<>();
    private final List<Movimiento> ventas = new ArrayList<>();

    public void registrarCompra(Movimiento movimiento) throws PersistenciaException {
        validarMovimiento(movimiento);

        movimiento.setProcesado(false);
        compras.add(movimiento);
    }

    public void registrarVenta(Movimiento movimiento) throws PersistenciaException {
        validarMovimiento(movimiento);

        movimiento.setProcesado(false);
        ventas.add(movimiento);
    }

    private void validarMovimiento(Movimiento m) throws PersistenciaException {
        LocalDate hoy = LocalDate.now();

        if (m.getFecha().isAfter(hoy)) {
            throw new PersistenciaException("Fecha futura inválida");
        }

        if (m.getFecha().getMonth() != hoy.getMonth()) {
            throw new PersistenciaException("Debe ser del mes actual");
        }
    }

    public List<Movimiento> consultarCompras() {
        return compras;
    }

    public List<Movimiento> consultarVentas() {
        return ventas;
    }

    public List<Movimiento> consultarComprasPorPeriodo(LocalDate inicio, LocalDate fin) {
        return filtrarPorPeriodo(compras, inicio, fin);
    }

    public List<Movimiento> consultarVentasPorPeriodo(LocalDate inicio, LocalDate fin) {
        return filtrarPorPeriodo(ventas, inicio, fin);
    }

    private List<Movimiento> filtrarPorPeriodo(List<Movimiento> lista, LocalDate inicio, LocalDate fin) {
        List<Movimiento> resultado = new ArrayList<>();

        for (Movimiento m : lista) {
            if ((m.getFecha().isEqual(inicio) || m.getFecha().isAfter(inicio)) &&
                (m.getFecha().isEqual(fin) || m.getFecha().isBefore(fin))) {
                resultado.add(m);
            }
        }

        return resultado;
    }
}