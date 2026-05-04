/* 
 * 
 */
package objetospersistencia;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import objetosdominio.MovimientoGranel;
import exceptions.PersistenciaException;

//** @author Julian Daniel Ramirez Garcia

public class MovimientosGranelPersistencia {

    private final List<MovimientoGranel> movimientos = new ArrayList<>();

    public void registrarCompra(MovimientoGranel movimiento) throws PersistenciaException {
        validarMovimiento(movimiento);

        for (MovimientoGranel m : movimientos) {
            if (m.getProducto().getClave().equals(movimiento.getProducto().getClave())
                && m.getFecha().isEqual(movimiento.getFecha())) {
                throw new PersistenciaException("Movimiento duplicado en el día");
            }
        }

        movimiento.setProcesado(false);
        movimientos.add(movimiento);
    }

    public void registrarVenta(MovimientoGranel movimiento) throws PersistenciaException {
        validarMovimiento(movimiento);

        movimiento.setProcesado(false);
        movimientos.add(movimiento);
    }

    private void validarMovimiento(MovimientoGranel m) throws PersistenciaException {
        LocalDate hoy = LocalDate.now();
        
        if (m == null) {
            throw new PersistenciaException("Movimiento null");
        }
        if (m.getFecha().isAfter(hoy)) {
            throw new PersistenciaException("Fecha futura inválida");
        }

        if (m.getFecha().getMonth() != hoy.getMonth()) {
            throw new PersistenciaException("Debe ser del mes actual");
        }
    }

    public List<MovimientoGranel> consultarCompras() {
        return movimientos;
    }

    public List<MovimientoGranel> consultarPorPeriodo(LocalDate inicio, LocalDate fin) {
        List<MovimientoGranel> resultado = new ArrayList<>();

        for (MovimientoGranel m : movimientos) {
            if ((m.getFecha().isEqual(inicio) || m.getFecha().isAfter(inicio)) &&
                (m.getFecha().isEqual(fin) || m.getFecha().isBefore(fin))) {
                resultado.add(m);
            }
        }

        return resultado;
    }
}