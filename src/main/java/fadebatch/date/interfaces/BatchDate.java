package fadebatch.date.interfaces;

import java.util.Date;

public interface BatchDate extends ExclusiveTime, TimeSetter {

	Date startTime();

	Date endTime();
}
