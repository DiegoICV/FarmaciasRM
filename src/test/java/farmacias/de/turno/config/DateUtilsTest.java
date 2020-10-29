package farmacias.de.turno.config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import farmacias.de.turno.config.DateUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DateUtilsTest {

    private DateUtils dateUtils;

    @BeforeEach
    public void setUp() {
            this.dateUtils = new DateUtils();
    }

    @Test
    public void  validateBeetween01(){
        String now = "00:20";
        String start = "14:00";
        String end   = "15:26";
        assertEquals(dateUtils.isHourInInterval(now,start,end),false);
    }

    @Test
    public void  validateBeetween02(){
        String now = "00:21";
        String start = "00:00";
        String end   = "01:00";
        assertEquals(dateUtils.isHourInInterval(now,start,end),true);
    }

    @Test
    public void  validateBeetween03(){
        String now = "15:00";
        String start = "14:00";
        String end   = "00:00";
        assertEquals(dateUtils.isHourInInterval(now,start,end),true);
    }


    @Test
    public void  validateBeetween04(){
        String now = "01:00";
        String start = "14:00";
        String end   = "00:00";
        assertEquals(dateUtils.isHourInInterval(now,start,end),false);
    }

}
