package fadebatch.date;

import java.util.Calendar;
import java.util.Date;

/**
 * 最基本的时间类，没有日切的概念<br>
 * 开始时间=结束时间=时间
 */
public class TimeHolder extends AbstractTimeHolder {
	private static final long serialVersionUID = -2390552692591980127L;
	private final int offset;
	private final int field;

	public TimeHolder(int offset, int field) {
		super();
		this.offset = offset;
		this.field = field;
	}

	@Override
	protected final Date findStartTime(Date time) {
		return cleanStartTimeIfNeccesary(time, field);
	}

	@Override
	protected final Date findEndTime(Date time) {
		return cleanEndTimeIfNeccesary(time, field);
	}

	protected Date cleanEndTimeIfNeccesary(Date time, int field) {
		return time;
	}

	protected Date cleanStartTimeIfNeccesary(Date time, int field) {
		return time;
	}

	@Override
	protected Date offsetTime(Date time) {
		return offsetTime(time, offset);
	}

	protected final Date offsetTime(Date time, int offset) {
		Calendar calendar = getCalendar(time);
		calendar.add(field, offset);
		return calendar.getTime();
	}

	public static Calendar getCalendar(Date time) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(time);
		return calendar;
	}

	Date changeTime(Date time, int amount) {
		Calendar cal = getCalendar(time);
		cal.add(field, amount);
		return cal.getTime();
	}
}
