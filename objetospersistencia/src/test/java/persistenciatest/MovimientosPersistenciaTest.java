package persistenciatest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import objetospersistencia.MovimientosPersistencia;
import objetosdominio.Movimiento;
import objetosdominio.Producto;
import exceptions.PersistenciaException;

public class MovimientosPersistenciaTest {

    @Test
    void registrarCompraCorrecta() throws Exception {
        MovimientosPersistencia repo = new MovimientosPersistencia();

        Producto p = new Producto("AT001", "Arroz", 'E', "KG");
        Movimiento m = new Movimiento("04/05/2026", true);

        repo.registrarCompra(m);

        assertEquals(1, repo.consultarCompras().size());
    }

    @Test
    void fechaFutura() {
        MovimientosPersistencia repo = new MovimientosPersistencia();

        Producto p = new Producto("AT001", "Arroz", 'E', "KG");
        Movimiento m = new Movimiento("05/05/2026", true);

        assertThrows(PersistenciaException.class, () -> {
            repo.registrarCompra(m);
        });
    }

    @Test
    void fueraDeMes() {
        MovimientosPersistencia repo = new MovimientosPersistencia();

        Producto p = new Producto("AT001", "Arroz", 'E', "KG");
        Movimiento m = new Movimiento("04/06/2026", true);

        assertThrows(PersistenciaException.class, () -> {
            repo.registrarCompra(m);
        });
    }

    @Test
    void consultarPorPeriodo() throws Exception {
        MovimientosPersistencia repo = new MovimientosPersistencia();

        Producto p = new Producto("AT001", "Arroz", 'E', "KG");

        repo.registrarCompra(new Movimiento("03/05/2026", true));

        assertEquals(1, repo.consultarComprasPorPeriodo(
                LocalDate.now().minusDays(1),
                LocalDate.now().plusDays(1)
        ).size());
    }
}