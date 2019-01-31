package bot.calendar.demo.avantica.calendar_bot.commons;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by juanjose on 3/19/18.
 */

public class Utils {

    static public String formatDateTime(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(dateFormat.format(date));
    }


}
