/*
 * Clase de utilidad que proporciona métodos para validar cadenas de texto.
 */
package objetosservicio;

//** @author Julian Daniel Ramirez Garcia

public class ValidadorStrings {
    
    /**
    * Verifica si la longitud del texto supera un tamaño máximo permitido.
    *
    * @param texto Cadena de texto a evaluar.
    * @param maximo Número máximo de caracteres permitidos.
    * @return true si el texto excede el tamaño máximo; false en caso contrario
    *         o si el texto es nulo.
    */
    public static boolean superaTamanioMaximo(String texto, int maximo) {
        if (estaVacio(texto)) return false;
        return texto.length() > maximo;
    }
    
    /**
     * Verifica si la longitud del texto es menor al tamaño mínimo requerido.
     *
     * @param texto Cadena de texto a evaluar.
     * @param minimo Número mínimo de caracteres requeridos.
     * @return true si el texto no cumple con la longitud mínima; false en caso contrario
     *         o si el texto es nulo.
     */
    public static boolean careceTamanioMinimo(String texto, int minimo) {
        if (estaVacio(texto)) return false;
        return texto.length() < minimo;
    }
    
    /**
     * Verifica si una cadena de texto es nula, está vacía o contiene solo espacios en blanco.
     *
     * @param texto Cadena de texto a validar.
     * @return true si el texto es nulo, vacío o solo contiene espacios; false en caso contrario.
     */
    public static boolean estaVacio(String texto) {
        return (texto == null) || (texto.isBlank());
    }
    
    /**
     * Determina si una cadena contiene únicamente caracteres numéricos (0-9).
     *
     * @param texto Cadena de texto a validar.
     * @return true si el texto contiene solo dígitos; false si contiene otros caracteres o está vacío.
     */
    public static boolean contieneSoloNumeros(String texto) {
        if (estaVacio(texto)) return false;
        
        for (char letra : texto.toCharArray()) {
            if (!Character.isDigit(letra)) return false;
        }
        return true;
    }
    
    /**
     * Determina si una cadena contiene únicamente letras y espacios en blanco.
     *
     * @param texto Cadena de texto a validar.
     * @return true si el texto contiene solo letras o espacios; false en caso contrario.
     */
    public static boolean contieneSoloLetras(String texto) {
        if (estaVacio(texto)) return false;
        
        for (char letra : texto.toCharArray()) {
            if (!Character.isLetter(letra) && !Character.isWhitespace(letra)) return false;
        }
        return true;
    }
    
    /**
     * Verifica si un carácter pertenece al conjunto de caracteres especiales permitidos.
     *
     * @param c Carácter a evaluar.
     * @return true si es un carácter especial permitido; false en caso contrario.
     */
    private static boolean esCaracterEspecial(char c) {
    return c == '$' || c == '%' || c == '-' ||
           c == '_' || c == '#' || c == '@';
    }
    
    /**
     * Valida si una contraseña cumple con los criterios establecidos:
     *     Longitud entre 8 y 12 caracteres
     *     Al menos una letra minúscula
     *     Al menos una letra mayúscula
     *     Al menos un dígito
     *     Al menos un carácter especial permitido
     *
     * @param texto Contraseña a validar.
     * @return true si la contraseña cumple todos los requisitos; false en caso contrario.
     */
    public static boolean esPasswordValido(String texto) {
        if (estaVacio(texto) || texto.length() < 8 || texto.length() > 12) return false;
        
        boolean tieneMinuscula = false, tieneMayuscula = false, tieneNumero = false, tieneEspecial = false;
        
        for (char letra : texto.toCharArray()) {
            if (Character.isLowerCase(letra)) {
                tieneMinuscula = true;
            } else if (Character.isUpperCase(letra)) {
                tieneMayuscula = true;
            } else if (Character.isDigit(letra)) {
                tieneNumero = true;
            } else if (esCaracterEspecial(letra)) {
                tieneEspecial = true;
            } else {
                // carácter no permitido
                return false;
            }
        }
        return tieneMinuscula && tieneMayuscula && tieneNumero && tieneEspecial;
    }
    
    /**
    * Obtiene la cantidad de días correspondientes a un mes específico.
    * Considera meses de 30 y 31 días, y asigna 28 días a febrero,
    * ignorando los años bisiestos.
    *
    * @param mes Número del mes (1-12).
    * @return Cantidad de días del mes indicado. Si el mes no es válido, retorna 0.
    */
    private static int getDiasDelMes(int mes) {
        switch (mes) {
            case 1, 3, 5, 7, 8, 10, 12 -> {
                return 31;
            }
            case 4, 6, 9, 11 -> {
                return 30;
            }
            case 2 ->  {
                return 28;
            }
        }
        return 0;
    }
    
    /**
     * Valida si una cadena representa una fecha válida en formato "dd/MM/yyyy".
     * La validación incluye:
     *     Longitud exacta de 10 caracteres
     *     Separadores '/' en posiciones correctas
     *     Contenido numérico en día, mes y año
     *     Mes en rango de 1 a 12
     *     Día válido según el mes (sin considerar años bisiestos)
     *
     * @param texto Cadena de texto que representa la fecha a validar.
     * @return true si la fecha es válida; false en caso contrario.
     */
    public static boolean esFechaValida(String texto) {
        if (estaVacio(texto) || texto.length() != 10) return false;

        if (texto.charAt(2) != '/' || texto.charAt(5) != '/') return false;

        for (int i = 0; i < texto.length(); i++) {
            if (i == 2 || i == 5) continue;
            
            char c = texto.charAt(i);
            if (c < '0' || c > '9') return false;
        }
        
        int dia = Integer.parseInt(texto.substring(0, 2));
        int mes = Integer.parseInt(texto.substring(3, 5));

        if (mes < 1 || mes > 12) return false;
        return !(dia < 1 || dia > getDiasDelMes(mes));
    }
    
    /**
     * Determina si una cadena es un palíndromo.
     * Un palíndromo es un texto que se lee igual de izquierda a derecha que de derecha a izquierda,
     * ignorando espacios y diferencias entre mayúsculas y minúsculas.
     *
     * @param texto Cadena de texto a evaluar.
     * @return true si el texto es un palíndromo; false en caso contrario.
     */
    public static boolean esPalindromo(String texto) {
        if (estaVacio(texto)) return false;
        
        String textoLimpio = "";
        for (char c : texto.toCharArray()) {
            if (Character.isWhitespace(c)) continue;
            textoLimpio += Character.toLowerCase(c);
        }
        
        for (int i = 0; i < textoLimpio.length()/2; i++) {
            char izq = textoLimpio.charAt(i);
            char der = textoLimpio.charAt(textoLimpio.length() - 1 - i);
            
            if (izq != der) return false;
        }
        
        return true;
    }
}
