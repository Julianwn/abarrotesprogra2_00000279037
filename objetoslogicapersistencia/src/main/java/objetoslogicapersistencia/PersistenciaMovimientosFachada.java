/* 
 * 
 */
package objetoslogicapersistencia;

import objetoslogicapersistencia.IPersistenciaMovimientosFachada;
import objetospersistencia.PersistenciaException;
import objetospersistencia.MovimientosPersistencia;
import objetosdominio.MovimientoEmpacado;
import objetosdominio.MovimientoGranel;
import objetosdominio.ProductoEmpacado;
import objetosdominio.ProductoGranel;
import objetosservicio.objetosgenerador.GeneradorCodigosConsecutivos;

//** @author Julian Daniel Ramirez Garcia

public class PersistenciaMovimientosFachada implements IPersistenciaMovimientosFachada {
    
    private final MovimientosPersistencia<MovimientoEmpacado> movimientosEmpacados;
    private final GeneradorCodigosConsecutivos genClavesComprasEmpacado;
    private final GeneradorCodigosConsecutivos genClavesVentasEmpacado;
    
    private final MovimientosPersistencia<MovimientoGranel> movimientosGranel;
    private final GeneradorCodigosConsecutivos genClavesComprasGranel;
    private final GeneradorCodigosConsecutivos genClavesVentasGranel;
    
    public PersistenciaMovimientosFachada() {
        movimientosEmpacados = new MovimientosPersistencia<>();
        genClavesComprasEmpacado = new GeneradorCodigosConsecutivos("COM-EM");
        genClavesVentasEmpacado = new GeneradorCodigosConsecutivos("VEN-EM");
        
        movimientosGranel = new MovimientosPersistencia<>();
        genClavesComprasGranel = new GeneradorCodigosConsecutivos("COM-GR");
        genClavesVentasGranel = new GeneradorCodigosConsecutivos("VEN-GR");
    }
    
    //** implementacion de consultas -------------------------------------------
    @Override
    public MovimientoEmpacado[] verComprasEmpacado() {
        return movimientosEmpacados.consultarCompras(new MovimientoEmpacado[0]);
    }
    
    @Override
    public MovimientoEmpacado obtenerCompraEmpacado(String clave) throws PersistenciaException {
        return movimientosEmpacados.consultarCompra(clave);
    }
    
    @Override
    public MovimientoEmpacado[] verVentasEmpacado() {
        return movimientosEmpacados.consultarVentas(new MovimientoEmpacado[0]);
    }
    
    @Override
    public MovimientoEmpacado obtenerVentaEmpacado(String clave) throws PersistenciaException {
        return movimientosEmpacados.consultarVenta(clave);
    }
    
    @Override
    public MovimientoGranel[] verComprasGranel() {
        return movimientosGranel.consultarCompras(new MovimientoGranel[0]);
    }
    
    @Override
    public MovimientoGranel obtenerCompraGranel(String clave) throws PersistenciaException {
        return movimientosGranel.consultarCompra(clave);
    }
    
    @Override
    public MovimientoGranel[] verVentasGranel() {
        return movimientosGranel.consultarVentas(new MovimientoGranel[0]);
    }
    
    @Override
    public MovimientoGranel obtenerVentaGranel(String clave) throws PersistenciaException {
        return movimientosGranel.consultarVenta(clave);
    }
    
    //** implementacion de registros -------------------------------------------
    @Override
    public void agregarCompraEmpacado(ProductoEmpacado producto, int cantidad) throws PersistenciaException {
        movimientosEmpacados.registrarCompra(new MovimientoEmpacado(genClavesComprasEmpacado.generarCodigo(), producto, cantidad));
    }
    
    @Override
    public void agregarVentaEmpacado(ProductoEmpacado producto, int cantidad) throws PersistenciaException {
        movimientosEmpacados.registrarVenta(new MovimientoEmpacado(genClavesVentasEmpacado.generarCodigo(), producto, cantidad));
    }
    
    @Override
    public void agregarCompraGranel(ProductoGranel producto, float cantidad) throws PersistenciaException {
        movimientosGranel.registrarCompra(new MovimientoGranel(genClavesComprasGranel.generarCodigo(), producto, cantidad));
    }
    
    @Override
    public void agregarVentaGranel(ProductoGranel producto, float cantidad) throws PersistenciaException {
        movimientosGranel.registrarVenta(new MovimientoGranel(genClavesVentasGranel.generarCodigo(), producto, cantidad));
    }
    
    
    //** metodos de apoyo ------------------------------------------------------
    public String getPrefijoCompraEmpacado() {
        return genClavesComprasEmpacado.getPrefijo();
    }
    
    public String getPrefijoVentaEmpacado() {
        return genClavesVentasEmpacado.getPrefijo();
    }
    
    public String getPrefijoComprasGranel() {
        return genClavesComprasGranel.getPrefijo();
    }
    
    public String getPrefijoVentasGranel() {
        return genClavesVentasGranel.getPrefijo();
    }
}
