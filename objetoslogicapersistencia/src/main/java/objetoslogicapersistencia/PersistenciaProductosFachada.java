/* 
 * 
 */
package objetoslogicapersistencia;

//** @author Julian Daniel Ramirez Garcia

import objetoslogicapersistencia.IPersistenciaProductosFachada;
import objetospersistencia.PersistenciaException;
import objetospersistencia.ProductosPersistencia;
import objetosdominio.Producto;
import objetosdominio.ProductoEmpacado;
import objetosdominio.ProductoGranel;
import objetosdominio.TipoProducto;
import objetosdominio.TipoUnidad;
import objetosservicio.objetosgenerador.GeneradorCodigosConsecutivos;


public class PersistenciaProductosFachada implements IPersistenciaProductosFachada {
    
    private final ProductosPersistencia<Producto> catalogo;
    
    private final ProductosPersistencia<ProductoEmpacado> inventarioEmpacado;
    private final GeneradorCodigosConsecutivos genClavesEmpacado;
    private final ProductosPersistencia<ProductoGranel> inventarioGranel;
    private final GeneradorCodigosConsecutivos genClavesGranel;
    
    public PersistenciaProductosFachada() {
        catalogo = new ProductosPersistencia<>();
        
        inventarioEmpacado = new ProductosPersistencia<>();
        genClavesEmpacado = new GeneradorCodigosConsecutivos("EM");
        inventarioGranel = new ProductosPersistencia<>();
        genClavesGranel = new GeneradorCodigosConsecutivos("GR");
    }
    
    //** implementacion de consultas -------------------------------------------
    
    @Override
    public Producto[] verCatalogo() {
        return catalogo.consultarProductos(new Producto[0]);
    }
    
    @Override
    public Producto consultarProductoCatalogo(String clave) throws PersistenciaException {
        return catalogo.consultarProducto(clave);
    }
    
    @Override
    public ProductoEmpacado[] verInventarioEmpacado() {
        return inventarioEmpacado.consultarProductos(new ProductoEmpacado[0]);
    }
    
    @Override
    public ProductoEmpacado consultarProductoEmpacado(String clave) throws PersistenciaException {
        return inventarioEmpacado.consultarProducto(clave);
    }
    
    @Override
    public ProductoGranel[] verInventarioGranel() {
        return inventarioGranel.consultarProductos(new ProductoGranel[0]);
    }
    
    @Override
    public ProductoGranel consultarProductoGranel(String clave) throws PersistenciaException {
        return inventarioGranel.consultarProducto(clave);
    }
    
    //** implementacion de registros -------------------------------------------
    @Override
    public void agregarProductoEmpacado(String nombre, int cantidad) throws PersistenciaException {
        String clave = genClavesEmpacado.generarCodigo();
        catalogo.agregarProducto(new Producto(clave, nombre, TipoProducto.EM, TipoUnidad.PZ));
        inventarioEmpacado.agregarProducto(new ProductoEmpacado(clave, nombre, cantidad));
        System.out.println("\n" + inventarioEmpacado.consultarProducto(clave).toString());
    }
    
    @Override
    public void agregarProductoGranel(String nombre, TipoUnidad unidad, float cantidad) throws PersistenciaException {
        String clave = genClavesGranel.generarCodigo();
        catalogo.agregarProducto(new Producto(clave, nombre, TipoProducto.GR, unidad));
        inventarioGranel.agregarProducto(new ProductoGranel(clave, nombre, unidad, cantidad));
        System.out.println("\n" + inventarioGranel.consultarProducto(clave).toString());
    }
    
    //** implementacion de actualizaciones -------------------------------------
    @Override
    public void actualizarProductoEmpacado(String clave, String nombre, int cantidad) throws PersistenciaException {
        catalogo.actualizarProducto(clave, new Producto(clave, nombre, TipoProducto.EM, TipoUnidad.PZ));
        inventarioEmpacado.actualizarProducto(clave, new ProductoEmpacado(clave, nombre, cantidad));
        System.out.println("\n" + inventarioEmpacado.consultarProducto(clave).toString());
    }
    
    @Override
    public void actualizarProductoGranel(String clave, String nombre, TipoUnidad unidad, float cantidad) throws PersistenciaException {
        catalogo.actualizarProducto(clave, new Producto(clave, nombre, TipoProducto.GR, unidad));
        inventarioGranel.actualizarProducto(clave, new ProductoGranel(clave, nombre, unidad, cantidad));
        System.out.println("\n" + inventarioGranel.consultarProducto(clave).toString());
    }
    
    //** implementacion de eliminaciones ---------------------------------------
    @Override
    public void eliminarProductoEmpacado(String clave) throws PersistenciaException {
        inventarioEmpacado.eliminarProducto(clave);
        catalogo.eliminarProducto(clave);
    }
    
    @Override
    public void eliminarProductoGranel(String clave) throws PersistenciaException {
        inventarioGranel.eliminarProducto(clave);
        catalogo.eliminarProducto(clave);
    }
    
    //** metodos de apoyo ------------------------------------------------------
    public String getPrefijoGranel() {
        return genClavesGranel.getPrefijo();
    }
}
