package persistenciatest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import objetospersistencia.MovimientosGranelPersistencia;
import objetosdominio.MovimientoGranel;
import objetosdominio.Producto;
import objetosdominio.ProductoGranel;
import exceptions.PersistenciaException;

public class MovimientosGranelPersistenciaTest {

    @Test
    void compraCorrecta() throws Exception {
        MovimientosGranelPersistencia repo = new MovimientosGranelPersistencia();

        ProductoGranel p = new ProductoGranel(new Producto("AT001", "Arroz", 'G', "KR"), 1.5f);
        MovimientoGranel m = new MovimientoGranel("04/05/2026", true, p);

        repo.registrarCompra(m);

        assertEquals(1, repo.consultarCompras().size());
    }

    @Test
    void noDuplicadoMismoDia() throws Exception {
        MovimientosGranelPersistencia repo = new MovimientosGranelPersistencia();

        ProductoGranel p = new ProductoGranel(new Producto("AT001", "Arroz", 'G', "KR"), 1.5f);

        repo.registrarCompra(new MovimientoGranel("04/05/2026", true, p));

        assertThrows(PersistenciaException.class, () -> {
            repo.registrarCompra(new MovimientoGranel("04/05/2026", true, p));
        });
    }

    @Test
    void ventaCorrecta() throws Exception {
        MovimientosGranelPersistencia repo = new MovimientosGranelPersistencia();

        ProductoGranel p = new ProductoGranel(new Producto("AT001", "Arroz", 'G', "KR"), 1.5f);
        MovimientoGranel m = new MovimientoGranel("04/05/2026", true, p);

        repo.registrarVenta(m);

        assertEquals(1, repo.consultarCompras().size());
    }

    @Test
    void consultarPeriodo() throws Exception {
        MovimientosGranelPersistencia repo = new MovimientosGranelPersistencia();

        ProductoGranel p = new ProductoGranel(new Producto("AT001", "Arroz", 'G', "KR"), 1.5f);

        repo.registrarVenta(new MovimientoGranel("04/05/2026", true, p));

        assertEquals(1, repo.consultarPorPeriodo(
                LocalDate.now().minusDays(1),
                LocalDate.now().plusDays(1)
        ).size());
    }
}