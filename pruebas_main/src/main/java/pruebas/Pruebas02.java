/* 
 * 
 */
package pruebas;

//** @author Julian Daniel Ramirez Garcia

import objetosservicio.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Pruebas02 {

    public static void main(String[] args) {
        
        
        // Instancias para codigoConsecutivo y codigoAleatorio
        GeneradorCodigosConsecutivos generadorConsecutivo = new GeneradorCodigosConsecutivos("CON");
        GeneradorCodigosAleatorios generadorAleatorio = new GeneradorCodigosAleatorios("ALE", 1, 9999);
        
        // codigos consecutivos generados
        final String codigoConsecutivo1 = generadorConsecutivo.generarCodigo();
        final String codigoConsecutivo2 = generadorConsecutivo.generarCodigo();
        
        // codigos aleatorios generados
        final String codigoAleatorio1 = generadorAleatorio.generarCodigo();
        final String codigoAleatorio2 = generadorAleatorio.generarCodigo();
        
        // comparar codigos consecutivos
        System.out.println("Los codigos consecutivos: " + codigoConsecutivo1 + " y " + codigoConsecutivo2 + " son: " + ((codigoConsecutivo1.equals(codigoConsecutivo2)) ? "iguales" : "diferentes"));
        // comparar codigos aleatorios
        System.out.println("Los codigos aleatorios: " + codigoAleatorio1 + " y " + codigoAleatorio2 + " son: " + ((codigoAleatorio1.equals(codigoAleatorio2)) ? "iguales" : "diferentes"));
        
        // evaluacion de codigo consecutivo
        System.out.println("El codigo consecutivo CON-999 tiene formato valido: " + generadorConsecutivo.tieneFormatoValido("CON9999"));
        // evaluacion de codigo aleatorio
        System.out.println("El codigo aleatorio ABC-1 tiene formato valido: " + generadorAleatorio.tieneFormatoValido("ABC-1"));
        
        // crear objetos localDate
        LocalDateTime hoy = LocalDateTime.now();
        LocalDate finAnio = LocalDate.of(hoy.plusYears(1).getYear(), 12, 31);
        LocalDate maniana = hoy.toLocalDate().plusDays(1);
        LocalDate fechaNacimiento = LocalDate.of(2000, 2, 12);
        // fecha actual mas un año
        // System.out.println(finAnio);
        
        // despliega las cuatro fechas con formato dd/mm/aaaa
        System.out.println();
        
        // evaluacion de fechas localDate
        System.out.println("maniana es fecha futura: " + ValidadorFechas.esFechaFutura(maniana));
        System.out.println("hoy es fecha pasada: " + ValidadorFechas.esFechaPasada(hoy.toLocalDate()));
        System.out.println("fechaNacimiento es de un mayor de edad: " + ValidadorFechas.esMayorEdad(fechaNacimiento));
        System.out.println("maniana esta entre hoy y fin de año: " + ValidadorFechas.estaDentroRango(maniana, hoy.toLocalDate(), finAnio));
        System.out.println("hoy es fin de semana: " + ValidadorFechas.esFinSemana(hoy.toLocalDate()));
        System.out.println("hoy esta en horario laboral: " + ValidadorFechas.esHorarioLaboral(hoy));
        
        // crea objetos fecha con Strings
        // LocalDate fecha1 = LocalDate.parse("10/20/2020",);
    }
}
