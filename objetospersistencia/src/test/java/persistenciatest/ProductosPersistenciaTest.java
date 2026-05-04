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

        Producto p = new Producto("GR001", "Arroz", 'E', "KG");

        repo.agregarProducto(p);

        assertNotNull(repo.buscarProducto("GR001"));
    }

    @Test
    void agregarProductoDuplicado() throws Exception {
        ProductosPersistencia repo = new ProductosPersistencia();

        Producto p = new Producto("GR001", "Arroz", 'E', "KG");

        repo.agregarProducto(p);

        assertThrows(PersistenciaException.class, () -> {
            repo.agregarProducto(new Producto("GR001", "Otro", 'E', "KG"));
        });
    }

    @Test
    void claveInvalida() {
        ProductosPersistencia repo = new ProductosPersistencia();

        Producto p = new Producto("XX001", "Arroz", 'E', "KG");

        assertThrows(PersistenciaException.class, () -> {
            repo.agregarProducto(p);
        });
    }

    @Test
    void nombreInvalido() {
        ProductosPersistencia repo = new ProductosPersistencia();

        Producto p = new Producto("GR001", "", 'E', "KG");

        assertThrows(PersistenciaException.class, () -> {
            repo.agregarProducto(p);
        });
    }

    @Test
    void tipoInvalido() {
        ProductosPersistencia repo = new ProductosPersistencia();

        Producto p = new Producto("GR001", "Arroz", 'X', "KG");

        assertThrows(PersistenciaException.class, () -> {
            repo.agregarProducto(p);
        });
    }

    @Test
    void eliminarProductoCorrecto() throws Exception {
        ProductosPersistencia repo = new ProductosPersistencia();

        Producto p = new Producto("GR001", "Arroz", 'E', "KG");

        repo.agregarProducto(p);
        repo.eliminarProducto("GR001");

        assertNull(repo.buscarProducto("GR001"));
    }

    @Test
    void eliminarProductoNoExiste() {
        ProductosPersistencia repo = new ProductosPersistencia();

        assertThrows(PersistenciaException.class, () -> {
            repo.eliminarProducto("GR001");
        });
    }

    @Test
    void actualizarProductoCorrecto() throws Exception {
        ProductosPersistencia repo = new ProductosPersistencia();

        Producto original = new Producto("GR001", "Arroz", 'E', "KG");
        Producto actualizado = new Producto("GR001", "Arroz Integral", 'E', "KG");

        repo.agregarProducto(original);
        repo.actualizarProducto(actualizado);

        assertEquals("Arroz Integral", repo.buscarProducto("GR001").getNombre());
    }

    @Test
    void actualizarProductoNoExiste() {
        ProductosPersistencia repo = new ProductosPersistencia();

        Producto p = new Producto("GR001", "Arroz", 'E', "KG");

        assertThrows(PersistenciaException.class, () -> {
            repo.actualizarProducto(p);
        });
    }

    @Test
    void consultarSinFiltros() throws Exception {
        ProductosPersistencia repo = new ProductosPersistencia();

        repo.agregarProducto(new Producto("GR001", "Arroz", 'E', "KG"));
        repo.agregarProducto(new Producto("EM001", "Leche", 'G', "L"));

        assertEquals(2, repo.consultarProductos(null, null).size());
    }

    @Test
    void consultarPorTipo() throws Exception {
        ProductosPersistencia repo = new ProductosPersistencia();

        repo.agregarProducto(new Producto("GR001", "Arroz", 'E', "KG"));
        repo.agregarProducto(new Producto("EM001", "Leche", 'G', "L"));

        assertEquals(1, repo.consultarProductos("E", null).size());
    }

    @Test
    void consultarPorUnidad() throws Exception {
        ProductosPersistencia repo = new ProductosPersistencia();

        repo.agregarProducto(new Producto("GR001", "Arroz", 'E', "KG"));
        repo.agregarProducto(new Producto("EM001", "Leche", 'G', "L"));

        assertEquals(1, repo.consultarProductos(null, "L").size());
    }
}