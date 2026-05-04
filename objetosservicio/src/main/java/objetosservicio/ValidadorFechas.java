/*
 * Clase de servicio que proporciona métodos para validar fechas y horarios.
 */
package objetosservicio;

//** @author Julian Daniel Ramirez Garcia

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ValidadorFechas {
    
    /**
     * Determina si una fecha es posterior a la fecha actual del sistema.
     *
     * @param fecha Fecha a evaluar.
     * @return true si la fecha es futura respecto a la fecha actual; false en caso contrario.
     */
    public static boolean esFechaFutura(LocalDate fecha) {
        return fecha.isAfter(LocalDate.now());
    }
    
    /**
     * Determina si una fecha es anterior a la fecha actual del sistema.
     *
     * @param fecha Fecha a evaluar.
     * @return true si la fecha es pasada respecto a la fecha actual; false en caso contrario.
     */
    public static boolean esFechaPasada(LocalDate fecha) {
        return fecha.isBefore(LocalDate.now());
    }
    
    /**
     * Determina si una persona es mayor de edad (18 años o más) con base en su fecha de nacimiento.
     *
     * @param fecha Fecha de nacimiento.
     * @return true si la persona tiene 18 años o más; false en caso contrario.
     */
    public static boolean esMayorEdad(LocalDate fecha) {
        return LocalDate.now().isAfter(fecha.plusYears(18));
    }
    
    /**
     * Verifica si una fecha se encuentra dentro de un rango determinado.
     *
     * @param fecha Fecha a evaluar.
     * @param desde Fecha inicial del rango.
     * @param hasta Fecha final del rango.
     * @return true si la fecha está dentro del rango; false en caso contrario.
     */
    public static boolean estaDentroRango(LocalDate fecha, LocalDate desde, LocalDate hasta) {
        return (fecha.isAfter(desde) || fecha.isEqual(desde)) &&
               (fecha.isBefore(hasta) || fecha.isEqual(hasta));
    }
    
    /**
     * Determina si una fecha corresponde a un fin de semana.
     *
     * @param fecha Fecha a evaluar.
     * @return true si la fecha es sábado o domingo; false en caso contrario.
     */
    public static boolean esFinSemana(LocalDate fecha) {
        return fecha.getDayOfWeek().equals(DayOfWeek.SATURDAY) || fecha.getDayOfWeek().equals(DayOfWeek.SUNDAY);
    }
    
    /**
     * Determina si una fecha y hora se encuentran dentro de un horario laboral.
     * Reglas:
     *     Lunes a viernes: de 08:00 a 18:00
     *     Sábados: de 08:00 a 13:00
     *     Domingos: no laborables
     *
     * @param fecha Fecha y hora a evaluar.
     * @return true si corresponde a horario laboral; false en caso contrario.
     */
    public static boolean esHorarioLaboral(LocalDateTime fecha) {
        DayOfWeek dia = fecha.getDayOfWeek();
        LocalTime hora = fecha.toLocalTime();

        if (dia == DayOfWeek.SUNDAY) return false;

        LocalTime inicio = LocalTime.of(8, 0);
        LocalTime cierre = (dia == DayOfWeek.SATURDAY) ? LocalTime.of(13, 0) : LocalTime.of(18, 0);

        return !hora.isBefore(inicio) && hora.isBefore(cierre);
    }
}
