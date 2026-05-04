/*
 * Clase abstracta que define la estructura base para la generación y validación de códigos.
 */
package objetosservicio;

//** @author Julian Daniel Ramirez Garcia

public abstract class GeneradorCodigos {
    
    /**
     * Prefijo que identifica el tipo de código generado.
     * Es inmutable una vez asignado.
     */
    private final String PREFIJO;
    
    /**
     * Constructor que inicializa el prefijo del generador de códigos.
     *
     * @param prefijo Cadena que se utilizará como prefijo para los códigos generados.
     */
    public GeneradorCodigos(String prefijo) {
        this.PREFIJO = prefijo;
    }
    
    /**
     * Obtiene el prefijo asociado a este generador de códigos.
     *
     * @return El prefijo definido para los códigos.
     */
    public String getPrefijo() {
        return PREFIJO;
    }
    
    /**
     * Verifica si un código cumple con el formato válido definido por la subclase.
     *
     * @param codigo Código a validar.
     * @return true si el código cumple con el formato esperado; false en caso contrario.
     */
    public abstract boolean tieneFormatoValido(String codigo);
    
    /**
     * Genera un nuevo código siguiendo las reglas definidas por la subclase.
     *
     * @return Cadena que representa un código generado.
     */
    public abstract String generarCodigo();
}
