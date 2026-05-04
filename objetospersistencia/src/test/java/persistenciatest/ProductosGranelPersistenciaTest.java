package persistenciatest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import objetospersistencia.ProductosGranelPersistencia;
import objetosdominio.Producto;
import objetosdominio.ProductoGranel;
import exceptions.PersistenciaException;

public class ProductosGranelPersistenciaTest {

    @Test
    void agregarProductoCorrecto() throws Exception {
        ProductosGranelPersistencia repo = new ProductosGranelPersistencia();

        Producto base = new Producto("EM001", "Leche", 'G', "L");
        ProductoGranel pg = new ProductoGranel(base, 10f);

        repo.agregarProducto(pg);

        assertNotNull(repo.buscarProducto("GR001"));
    }

    @Test
    void cantidadInvalidaNoSeAgrega() {
        ProductosGranelPersistencia repo = new ProductosGranelPersistencia();

        Producto base = new Producto("EM001", "Leche", 'G', "L");
        ProductoGranel pg = new ProductoGranel(base, 0.0f);

        assertThrows(PersistenciaException.class, () -> {
            repo.agregarProducto(pg);
        });
    }

    @Test
    void productoDuplicado() throws Exception {
        ProductosGranelPersistencia repo = new ProductosGranelPersistencia();

        Producto base = new Producto("EM001", "Leche", 'G', "L");

        repo.agregarProducto(new ProductoGranel(base, 10f));

        assertThrows(PersistenciaException.class, () -> {
            repo.agregarProducto(new ProductoGranel(base, 5f));
        });
    }

    @Test
    void actualizarProductoCorrecto() throws Exception {
        ProductosGranelPersistencia repo = new ProductosGranelPersistencia();

        Producto base = new Producto("EM001", "Leche", 'G', "L");

        repo.agregarProducto(new ProductoGranel(base, 10f));
        repo.actualizarProducto(new ProductoGranel(base, 20f));

        assertEquals(20f, repo.buscarProducto("GR001").getCantidad());
    }

    @Test
    void actualizarProductoCantidadInvalida() throws Exception {
        ProductosGranelPersistencia repo = new ProductosGranelPersistencia();

        Producto base = new Producto("EM001", "Leche", 'G', "L");

        repo.agregarProducto(new ProductoGranel(base, 10f));

        ProductoGranel pg = new ProductoGranel(base, 0.0f);

        assertThrows(PersistenciaException.class, () -> {
            repo.actualizarProducto(pg);
        });
    }

    @Test
    void eliminarProductoCorrecto() throws Exception {
        ProductosGranelPersistencia repo = new ProductosGranelPersistencia();

        Producto base = new Producto("EM001", "Leche", 'G', "L");

        repo.agregarProducto(new ProductoGranel(base, 10f));
        repo.eliminarProducto("GR001");

        assertNull(repo.buscarProducto("GR001"));
    }

    @Test
    void eliminarProductoNoExiste() {
        ProductosGranelPersistencia repo = new ProductosGranelPersistencia();

        assertThrows(PersistenciaException.class, () -> {
            repo.eliminarProducto("GR001");
        });
    }

    @Test
    void consultarInventario() throws Exception {
        ProductosGranelPersistencia repo = new ProductosGranelPersistencia();

        Producto base1 = new Producto("EM001", "Leche", 'G', "L");
        Producto base2 = new Producto("GR002", "Arroz", 'E', "KG");

        repo.agregarProducto(new ProductoGranel(base1, 10f));
        repo.agregarProducto(new ProductoGranel(base2, 5f));

        assertEquals(2, repo.consultarInventario().size());
    }
}