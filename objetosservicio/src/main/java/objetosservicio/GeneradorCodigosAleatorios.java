/*
 * Genera códigos aleatorios con prefijo y formato fijo sin repeticion.
 */
package objetosservicio;

//** @author Julian Daniel Ramirez Garcia

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GeneradorCodigosAleatorios extends GeneradorCodigos {

    /**
     * Cantidad de valores aún disponibles para generar códigos únicos.
     */
    private int disponibles;
    /**
     * Valor mínimo que se suma al número generado.
     */
    private final int MIN;
    /**
     * Cantidad de dígitos que tendrá la parte numérica del código.
     */
    private final int TAMANIO;
    /**
     * Generador de números aleatorios.
     */
    private final Random RAND;
    /**
     * Estructura que simula el intercambio de valores para evitar repeticiones.
     */
    private final Map <Integer, Integer> MAPA;
    
    /**
     * Constructor que inicializa el generador con un prefijo y rango específico.
     *
     * @param prefijo Prefijo que tendrán los códigos generados.
     * @param min Numero minimo donde inician los codigos generados.
     * @param disponibles Cantidad de numeros generables entre el numero minimo y el maximo.
     */
    public GeneradorCodigosAleatorios(String prefijo, int min, int disponibles) {
        super(prefijo);
        this.MIN = min;
        this.disponibles = disponibles;
        this.TAMANIO = String.valueOf(this.disponibles).length();
        this.RAND = new Random();
        this.MAPA = new HashMap<>();
    }

    /**
     * Verifica si un código cumple con el formato válido esperado:
     *     PREFIJO-XXXX
     *     donde XXXX es una secuencia numérica con longitud fija.
     *
     * @param codigo Código a validar.
     * @return true si el código cumple con el formato; false en caso contrario.
     */
    @Override
    public boolean tieneFormatoValido(String codigo) {
        return codigo.matches(getPrefijo() + "-\\d{" + this.TAMANIO +"}");
    }
    
    /**
     * Genera un nuevo código aleatorio único.
     *
     * @return Código generado con el formato PREFIJO-XXXX.
     */
    @Override
    public String generarCodigo() {
        return this.getPrefijo() + "-" + this.obtenerAleatorio();
    }
    
    /**
    * Obtiene un número aleatorio sin repetición dentro del rango disponible.
    * Utiliza un mecanismo de intercambio mediante un {@code Map} para simular
    * una selección sin reemplazo, evitando generar el mismo número más de una vez.
    *
    * @return Cadena numérica formateada con ceros a la izquierda según el tamaño requerido.
    * @throws IllegalStateException si no quedan valores disponibles para generar códigos.
    */
    private String obtenerAleatorio() throws IllegalStateException {
        if (disponibles <= 0) throw new IllegalStateException("No hay códigos disponibles");
        
        int indiceRand = this.RAND.nextInt(this.disponibles);
        int valorRand = this.MAPA.getOrDefault(indiceRand, indiceRand);
        int ultimoNum = this.MAPA.getOrDefault(this.disponibles -1, this.disponibles -1);
        
        this.MAPA.put(indiceRand, ultimoNum);
        this.disponibles--;
        
        return String.format("%0" + this.TAMANIO + "d", valorRand + this.MIN);
    }
}
