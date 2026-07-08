/* 
 * 
 */
package objetoslogicapersistencia;

import objetospersistencia.PersistenciaException;
import objetosdominio.MovimientoEmpacado;
import objetosdominio.MovimientoGranel;
import objetosdominio.ProductoEmpacado;
import objetosdominio.ProductoGranel;

//** @author Julian Daniel Ramirez Garcia

public interface IPersistenciaMovimientosFachada {
    
    // consultas ---------------------------------------------------------------
    MovimientoEmpacado[] verComprasEmpacado();
    MovimientoEmpacado obtenerCompraEmpacado(String clave) throws PersistenciaException;
    MovimientoEmpacado[] verVentasEmpacado();
    MovimientoEmpacado obtenerVentaEmpacado(String calve) throws PersistenciaException;
    
    MovimientoGranel[] verComprasGranel();
    MovimientoGranel obtenerCompraGranel(String clave) throws PersistenciaException;
    MovimientoGranel[] verVentasGranel();
    MovimientoGranel obtenerVentaGranel(String clave) throws PersistenciaException;
    
    // registros ---------------------------------------------------------------
    void agregarCompraEmpacado(ProductoEmpacado producto, int cantidad) throws PersistenciaException;
    void agregarVentaEmpacado(ProductoEmpacado producto, int cantidad) throws PersistenciaException;
    
    void agregarCompraGranel(ProductoGranel producto, float cantidad) throws PersistenciaException;
    void agregarVentaGranel(ProductoGranel producto, float cantidad) throws PersistenciaException;

}
