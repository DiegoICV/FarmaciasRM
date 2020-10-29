package farmacias.de.turno.config;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Para evaluar las horas
 */
@Component
public class DateUtils {

    /**
     * format 24hre ex. 12:12 , 17:15
     */

    private static String HOUR_FORMAT = "HH:mm";

    public DateUtils() {
    }

    /**
     * Se encarga de retornar la hora en el sistema
     * @return hora del sistema
     */
    public  String getCurrentHour() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdfHour = new SimpleDateFormat(HOUR_FORMAT);
        String hour = sdfHour.format(cal.getTime());
        return hour;
    }

    /**
     *
     * @param calendar
     * @return hora del sistema
     */
    public  String getCurrentHour(Calendar calendar) {
        SimpleDateFormat sdfHour = new SimpleDateFormat(HOUR_FORMAT);
        String hour = sdfHour.format(calendar.getTime());
        return hour;
    }

    /**
     *
     * @param target hora que se quiere comparar
     * @param start rango inicial
     * @param end rango final
     * @return validacion de si es la hora esta en rango
     */
    public  boolean isHourInInterval(String target, String start, String end) {
       if(end.compareTo(start)>=0){
           return ((target.compareTo(start) >= 0)
                   && (target.compareTo(end) <= 0));
       }else{
        return target.compareTo(start)>=0;
       }

    }

    /**
     * se encarga de evaluar de extraer la hora del sistema y evaluar
     * @param start hora inicial
     * @param end hora final
     * @return verdadero si esta en rango falso de lo contrario
     */
    public  boolean isNowInInterval(String start, String end) {
        return this.isHourInInterval
                (this.getCurrentHour(), start, end);
    }

}