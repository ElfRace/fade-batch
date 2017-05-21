package fadebatch.date.interfaces;

import java.util.Date;

public interface IteratableTime {
	Date next();

	void reset();
	
	boolean isEndTime();
	
	boolean isStartTime();
}
