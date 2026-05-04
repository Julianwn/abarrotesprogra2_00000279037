package persistenciatest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import objetospersistencia.MovimientosPersistencia;
import objetosdominio.Movimiento;
import exceptions.PersistenciaException;

public class MovimientosPersistenciaTest {

    private String hoy() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    private String fechaValida() {
        return LocalDate.now().minusDays(1)
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    private String fechaFutura() {
        return LocalDate.now().plusDays(1)
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    private String fueraDeMes() {
        return LocalDate.now().minusMonths(1)
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    @Test
    void registrarCompraCorrecta() throws Exception {
        MovimientosPersistencia repo = new MovimientosPersistencia();

        Movimiento m = new Movimiento(fechaValida(), true);

        repo.registrarCompra(m);

        assertEquals(1, repo.consultarCompras().size());
        assertFalse(repo.consultarCompras().get(0).isProcesado());
    }

    @Test
    void registrarVentaCorrecta() throws Exception {
        MovimientosPersistencia repo = new MovimientosPersistencia();

        Movimiento m = new Movimiento(fechaValida(), true);

        repo.registrarVenta(m);

        assertEquals(1, repo.consultarVentas().size());
        assertFalse(repo.consultarVentas().get(0).isProcesado());
    }

    @Test
    void fechaFuturaNoPermitida() {
        MovimientosPersistencia repo = new MovimientosPersistencia();

        Movimiento m = new Movimiento(fechaFutura(), true);

        assertThrows(PersistenciaException.class, () -> {
            repo.registrarCompra(m);
        });
    }

    @Test
    void fechaFueraDeMesNoPermitida() {
        MovimientosPersistencia repo = new MovimientosPersistencia();

        Movimiento m = new Movimiento(fueraDeMes(), true);

        assertThrows(PersistenciaException.class, () -> {
            repo.registrarCompra(m);
        });
    }

    @Test
    void consultarComprasPorPeriodo() throws Exception {
        MovimientosPersistencia repo = new MovimientosPersistencia();

        Movimiento m = new Movimiento(fechaValida(), true);

        repo.registrarCompra(m);

        assertEquals(1, repo.consultarComprasPorPeriodo(
                LocalDate.now().minusDays(2),
                LocalDate.now()
        ).size());
    }

    @Test
    void consultarVentasPorPeriodo() throws Exception {
        MovimientosPersistencia repo = new MovimientosPersistencia();

        Movimiento m = new Movimiento(fechaValida(), true);

        repo.registrarVenta(m);

        assertEquals(1, repo.consultarVentasPorPeriodo(
                LocalDate.now().minusDays(2),
                LocalDate.now()
        ).size());
    }
}