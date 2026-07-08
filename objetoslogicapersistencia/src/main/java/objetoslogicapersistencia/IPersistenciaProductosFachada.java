/* 
 * 
 */
package objetoslogicapersistencia;

import objetospersistencia.PersistenciaException;
import objetosdominio.Producto;
import objetosdominio.ProductoEmpacado;
import objetosdominio.ProductoGranel;
import objetosdominio.TipoUnidad;

//** @author Julian Daniel Ramirez Garcia

public interface IPersistenciaProductosFachada {
    
    // consultas ---------------------------------------------------------------
    Producto[] verCatalogo();
    Producto consultarProductoCatalogo(String clave) throws PersistenciaException;
    
    ProductoEmpacado[] verInventarioEmpacado();
    ProductoEmpacado consultarProductoEmpacado(String clave) throws PersistenciaException;
    
    ProductoGranel[] verInventarioGranel();
    ProductoGranel consultarProductoGranel(String clave) throws PersistenciaException;
    
    // registros ---------------------------------------------------------------
    void agregarProductoEmpacado(String nombre, int cantidad) throws PersistenciaException;
    
    void agregarProductoGranel(String nombre, TipoUnidad unidad, float cantidad) throws PersistenciaException;
    
    // actualizaciones ---------------------------------------------------------
    void actualizarProductoEmpacado(String clave, String nombre, int cantidad) throws PersistenciaException;
    
    void actualizarProductoGranel(String clave, String nombre, TipoUnidad unidad, float cantidad) throws PersistenciaException;
    
    // eliminaciones -----------------------------------------------------------
    void eliminarProductoEmpacado(String clave) throws PersistenciaException;
    
    void eliminarProductoGranel(String clave) throws PersistenciaException;
    
}
