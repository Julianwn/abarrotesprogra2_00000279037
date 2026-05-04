/*
 * Generador de códigos consecutivos con prefijo.
 */
package objetosservicio;

//** @author Julian Daniel Ramirez Garcia

public class GeneradorCodigosConsecutivos extends GeneradorCodigos {
    
    /**
     * Valor actual del consecutivo utilizado para generar los códigos.
     */
    private int consecutivo;
    
    /**
     * Constructor que inicializa con un prefijo específico y el consecutivo en 1.
     *
     * @param prefijo Prefijo que tendrán todos los códigos generados.
     */
    public GeneradorCodigosConsecutivos(String prefijo) {
        super(prefijo);
        this.consecutivo = 1;
    }
    
    /**
     * Constructor que inizializa con un prefijo especifico y un consecutivo inicial.
     * 
     * @param prefijo Prefijo que tendrán todos los codigos generados.
     * @param consecutivo Consecutivo inicial numerico.
     */
    public GeneradorCodigosConsecutivos(String prefijo, int consecutivo) {
        super(prefijo);
        this.consecutivo = consecutivo;
    }
    
    /**
     * Verifica si un código cumple con el formato válido esperado:
     *     PREFIJO-N
     *     donde N es un número entero positivo.
     *
     * @param codigo Código a validar.
     * @return true si el código cumple con el formato; false en caso contrario.
     */
    @Override
    public boolean tieneFormatoValido(String codigo) {
        return codigo.matches(getPrefijo() + "-\\d+");
    }
    
    /**
     * Genera un nuevo código consecutivo.
     *
     * @return Código generado con el formato PREFIJO-N.
     */
    @Override
    public String generarCodigo() {
        return this.getPrefijo() + "-" + this.incrementarConsecutivo();
    }
    
    /**
     * Devuelve el numero del consecutivo y lo incrementa en uno.
     *
     * @return Valor consecutivo antes del incremento.
     */
    private int incrementarConsecutivo() {
         return this.consecutivo++;
    }
}
