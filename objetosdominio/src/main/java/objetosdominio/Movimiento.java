/*
 * Clase Movimiento, superclase para MovimientoEmpacado y MovimientoGranel
 */
package objetosdominio;

/**
 * Representa un movimiento dentro del sistema.
 * Sirve como superclase para los movimientos de productos empacados y a granel.
 * 
 * @author Julian Daniel Ramirez Garcia
 */
public class Movimiento {

    private String clave, fecha;
    private boolean procesado;

    private static int contador = 0;

    /**
     * Constructor por defecto.
     */
    public Movimiento() {
        this.clave = "";
        this.fecha = "";
        this.procesado = false;
    }

    /**
     * Constructor con parámetros.
     */
    public Movimiento(String fecha, boolean procesado) {
        this.clave = genClave();
        setFecha(fecha);
        setProcesado(procesado);
    }
    
    /**
     * Constructor que recibe solo la clave.
     */
    public Movimiento(String clave) {
        this.clave = clave;
        this.fecha = "";
        this.procesado = false;
    }

    /**
     * Genera automáticamente la clave del movimiento.
     */
    public String genClave() {

        String numClave = String.format("%03d", ++contador);
        return "MV" + numClave;
    }

    public String getClave() {
        return clave;
    }
    
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {

        if (validarFecha(fecha)) {
            this.fecha = fecha;
        } else {
            System.out.println("Error Fecha invalida...");
        }
    }

    /**
     * Valida el formato de fecha: dd/mm/aaaa o dd-mm-aaaa
     */
    public boolean validarFecha(String fecha) {

        return fecha != null &&
               !fecha.equals("") &&
               fecha.matches("\\d{2}[-/]\\d{2}[-/]\\d{4}");
    }

    public boolean isProcesado() {
        return procesado;
    }

    public void setProcesado(boolean procesado) {
        this.procesado = procesado;
    }

    public static int getContador() {
        return contador;
    }

    @Override
    public String toString() {
        return "Movimiento(" +
               "clave=" + clave +
               ", fecha=" + fecha +
               ", procesado=" + procesado +
               ")";
    }
}