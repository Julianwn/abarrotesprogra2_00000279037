package persistenciatest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import objetospersistencia.ProductosPersistencia;
import objetosdominio.Producto;
import exceptions.PersistenciaException;

public class ProductosPersistenciaTest {

    @Test
    void agregarProductoCorrecto() throws Exception {
        ProductosPersistencia repo = new ProductosPersistencia();

        Producto p = new Producto("AT001", "Arroz", 'E', "KG");

        repo.agregarProducto(p);

        assertNotNull(repo.buscarProducto("AT001"));
    }

    @Test
    void agregarProductoDuplicado() throws Exception {
        ProductosPersistencia repo = new ProductosPersistencia();

        Producto p = new Producto("AT001", "Arroz", 'E', "KG");

        repo.agregarProducto(p);

        assertThrows(PersistenciaException.class, () -> {
            repo.agregarProducto(p);
        });
    }

    @Test
    void claveInvalida() {
        ProductosPersistencia repo = new ProductosPersistencia();

        Producto p = new Producto("A1001", "Arroz", 'E', "KG");

        assertThrows(PersistenciaException.class, () -> {
            repo.agregarProducto(p);
        });
    }

    @Test
    void unidadInvalida() {
        ProductosPersistencia repo = new ProductosPersistencia();

        Producto p = new Producto("AT001", "Arroz", 'E', "LT");

        assertThrows(PersistenciaException.class, () -> {
            repo.agregarProducto(p);
        });
    }

    @Test
    void tipoInvalido() {
        ProductosPersistencia repo = new ProductosPersistencia();

        Producto p = new Producto("AT001", "Arroz", 'X', "KG");

        assertThrows(PersistenciaException.class, () -> {
            repo.agregarProducto(p);
        });
    }

    @Test
    void eliminarProducto() throws Exception {
        ProductosPersistencia repo = new ProductosPersistencia();

        Producto p = new Producto("AT001", "Arroz", 'E', "KG");

        repo.agregarProducto(p);
        repo.eliminarProducto("AT001");

        assertNull(repo.buscarProducto("AT001"));
    }

    @Test
    void eliminarProductoNoExiste() {
        ProductosPersistencia repo = new ProductosPersistencia();

        assertThrows(PersistenciaException.class, () -> {
            repo.eliminarProducto("AT001");
        });
    }

    @Test
    void actualizarProducto() throws Exception {
        ProductosPersistencia repo = new ProductosPersistencia();

        Producto p1 = new Producto("AT001", "Arroz", 'E', "KG");
        Producto p2 = new Producto("AT001", "Arroz Integral", 'E', "KG");

        repo.agregarProducto(p1);
        repo.actualizarProducto(p2);

        assertEquals("Arroz Integral", repo.buscarProducto("AT001").getNombre());
    }

    @Test
    void consultarConFiltros() throws Exception {
        ProductosPersistencia repo = new ProductosPersistencia();

        repo.agregarProducto(new Producto("AT001", "Arroz", 'E', "KG"));
        repo.agregarProducto(new Producto("LT001", "Leche", 'G', "L"));

        assertEquals(1, repo.consultarProductos("E", null).size());
        assertEquals(1, repo.consultarProductos(null, "L").size());
        assertEquals(2, repo.consultarProductos(null, null).size());
    }
}