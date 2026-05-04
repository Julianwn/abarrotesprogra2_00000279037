package persistenciatest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import objetospersistencia.MovimientosGranelPersistencia;
import objetosdominio.Producto;
import objetosdominio.ProductoGranel;
import objetosdominio.MovimientoGranel;
import exceptions.PersistenciaException;

public class MovimientosGranelPersistenciaTest {

    private String hoy() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    private String ayer() {
        return LocalDate.now().minusDays(1)
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    private String futuro() {
        return LocalDate.now().plusDays(1)
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    private String fueraDeMes() {
        return LocalDate.now().minusMonths(1)
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    private ProductoGranel crearProductoGranel() {
        Producto base = new Producto("EM001", "Leche", 'G', "L");
        return new ProductoGranel(base, 10f);
    }

    @Test
    void registrarCompraCorrecta() throws Exception {
        MovimientosGranelPersistencia repo = new MovimientosGranelPersistencia();

        MovimientoGranel m = new MovimientoGranel(ayer(), true, crearProductoGranel());

        repo.registrarCompra(m);

        assertEquals(1, repo.consultarCompras().size());
        assertFalse(repo.consultarCompras().get(0).isProcesado());
    }

    @Test
    void registrarVentaCorrecta() throws Exception {
        MovimientosGranelPersistencia repo = new MovimientosGranelPersistencia();

        MovimientoGranel m = new MovimientoGranel(ayer(), true, crearProductoGranel());

        repo.registrarVenta(m);

        assertEquals(1, repo.consultarCompras().size()); // misma lista en tu implementación
        assertFalse(repo.consultarCompras().get(0).isProcesado());
    }

    @Test
    void noPermitirDuplicadoMismoProductoMismoDia() throws Exception {
        MovimientosGranelPersistencia repo = new MovimientosGranelPersistencia();

        ProductoGranel pg = crearProductoGranel();

        repo.registrarCompra(new MovimientoGranel(hoy(), true, pg));

        assertThrows(PersistenciaException.class, () -> {
            repo.registrarCompra(new MovimientoGranel(hoy(), true, pg));
        });
    }

    @Test
    void fechaFuturaNoPermitida() {
        MovimientosGranelPersistencia repo = new MovimientosGranelPersistencia();

        MovimientoGranel m = new MovimientoGranel(futuro(), true, crearProductoGranel());

        assertThrows(PersistenciaException.class, () -> {
            repo.registrarCompra(m);
        });
    }

    @Test
    void fueraDeMesNoPermitido() {
        MovimientosGranelPersistencia repo = new MovimientosGranelPersistencia();

        MovimientoGranel m = new MovimientoGranel(fueraDeMes(), true, crearProductoGranel());

        assertThrows(PersistenciaException.class, () -> {
            repo.registrarCompra(m);
        });
    }

    @Test
    void consultarPorPeriodo() throws Exception {
        MovimientosGranelPersistencia repo = new MovimientosGranelPersistencia();

        repo.registrarVenta(new MovimientoGranel(ayer(), true, crearProductoGranel()));

        assertEquals(1, repo.consultarPorPeriodo(
                LocalDate.now().minusDays(2),
                LocalDate.now()
        ).size());
    }
}