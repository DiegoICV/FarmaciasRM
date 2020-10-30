package farmacias.de.turno.utils;

import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Utility para manipulacion de horas
 */
@Component
public class DateUtils {

	private static String HOUR_FORMAT = "HH:mm";

	public DateUtils() {
	}

	/**
	 * Se encarga de retornar la hora en el sistema usando la libreria Calendar
	 * 
	 * @return hora del sistema
	 */
	public String getCurrentHour() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat stringToDateFormatter = new SimpleDateFormat(HOUR_FORMAT);
		String time = stringToDateFormatter.format(calendar.getTime());
		return time;
	}

	/*
	 * Retorna la hora del sistema en formato string Calendar.getTime
	 */
	public String getCurrentHour(Calendar calendar) {
		SimpleDateFormat stringToDateFormatter = new SimpleDateFormat(HOUR_FORMAT);
		String time = stringToDateFormatter.format(calendar.getTime());
		return time;
	}

	/**
	 * Verifica si la hora target esta dentro del rango [start,end]
	 */
	public boolean isHourInInterval(String target, String start, String end) {
		if (end.compareTo(start) >= 0) {
			return ((target.compareTo(start) >= 0) && (target.compareTo(end) <= 0));
		} else {
			return target.compareTo(start) >= 0;
		}

	}

	/**
	 * Retorna si la hora del sistema esta dentro del rango [start,end]
	 */
	public boolean isNowInInterval(String start, String end) {
		return this.isHourInInterval(this.getCurrentHour(), start, end);
	}

}