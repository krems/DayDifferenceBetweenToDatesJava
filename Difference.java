import java.util.*;
import java.util.concurrent.TimeUnit;

public class Difference {
    /**
     * Calculates the number of FULL days between to dates
     * @param startDate must be before endDate
     * @param endDate must be after startDate
     * @return number of day between startDate and endDate
     */
    public static int daysBetween(Calendar startDate, Calendar endDate) {
        long start = startDate.getTimeInMillis();
        long end = endDate.getTimeInMillis();
        // It's only approximation due to several bugs (@see java.util.Date) and different precision in Calendar chosen
        // by user (ex. day is time-quantum).
        int presumedDays = (int) TimeUnit.DAYS.toMillis(end - start);
        startDate.add(Calendar.DAY_OF_MONTH, presumedDays);
        // if we still didn't reach endDate try it with the step of one day
        while (startDate.before(endDate)) {
            startDate.add(Calendar.DAY_OF_MONTH, 1);
            ++presumedDays;
        }
        // if we crossed endDate then we must go back, because the boundary day haven't completed yet
        while (startDate.after(endDate)) {
            startDate.add(Calendar.DAY_OF_MONTH, -1);
            --presumedDays;
        }
        return presumedDays;
    }
}
