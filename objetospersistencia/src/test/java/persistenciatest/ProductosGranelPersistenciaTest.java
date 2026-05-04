package persistenciatest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import objetospersistencia.ProductosGranelPersistencia;
import objetosdominio.Producto;
import objetosdominio.ProductoGranel;
import exceptions.PersistenciaException;

public class ProductosGranelPersistenciaTest {

    @Test
    void agregarCorrecto() throws Exception {
        ProductosGranelPersistencia repo = new ProductosGranelPersistencia();

        Producto p = new Producto("AT001", "Arroz", 'E', "KG");
        ProductoGranel pg = new ProductoGranel(p, 10);

        repo.agregarProducto(pg);

        assertNotNull(repo.buscarProducto("AT001"));
    }

    @Test
    void cantidadInvalida() {
        ProductosGranelPersistencia repo = new ProductosGranelPersistencia();

        Producto p = new Producto("AT001", "Arroz", 'E', "KG");
        ProductoGranel pg = new ProductoGranel(p, 0);

        assertThrows(PersistenciaException.class, () -> {
            repo.agregarProducto(pg);
        });
    }

    @Test
    void productoDuplicado() throws Exception {
        ProductosGranelPersistencia repo = new ProductosGranelPersistencia();

        Producto p = new Producto("AT001", "Arroz", 'E', "KG");

        repo.agregarProducto(new ProductoGranel(p, 10));

        assertThrows(PersistenciaException.class, () -> {
            repo.agregarProducto(new ProductoGranel(p, 5));
        });
    }

    @Test
    void actualizarProducto() throws Exception {
        ProductosGranelPersistencia repo = new ProductosGranelPersistencia();

        Producto p = new Producto("AT001", "Arroz", 'E', "KG");

        repo.agregarProducto(new ProductoGranel(p, 10));
        repo.actualizarProducto(new ProductoGranel(p, 20));

        assertEquals(20, repo.buscarProducto("AT001").getCantidad());
    }

    @Test
    void eliminarProducto() throws Exception {
        ProductosGranelPersistencia repo = new ProductosGranelPersistencia();

        Producto p = new Producto("AT001", "Arroz", 'E', "KG");

        repo.agregarProducto(new ProductoGranel(p, 10));
        repo.eliminarProducto("AT001");

        assertNull(repo.buscarProducto("AT001"));
    }
}