package fadebatch.date;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.ListIterator;

public class RandomTimeHolder extends TimeHolder {
	private static final long serialVersionUID = -3936054572504046506L;
	private static final LinkedList<Integer> FIELDS = new LinkedList<Integer>(Arrays.asList(1, 2, 5, 11, 12, 13, 14));
	private Date cutover;

	public RandomTimeHolder(int offset, int field, Date cutover) {
		super(offset, field);
		this.cutover = cutover;
	}

	@Override
	protected Date cleanEndTimeIfNeccesary(Date time, int field) {
		Calendar calendar = getMaximumByField(time, field);
		calendar.add(Calendar.MILLISECOND, 1);
		if (onCutover(time, field))
			return offsetTime(calendar.getTime(), -1);
		return calendar.getTime();
	}

	@Override
	protected Date cleanStartTimeIfNeccesary(Date time, int field) {
		Calendar calendar = getMinimumFields(time, field);
		if (onCutover(time, field))
			return offsetTime(calendar.getTime(), -1);
		return calendar.getTime();
	}

	public boolean onCutover(Date time, int field) {
		Date cutover = getMinimumFields(time, field).getTime();
		return cutover.equals(time);
	}

	public Calendar getMinimumFields(Date time, int field) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		ListIterator<Integer> iterator = FIELDS.listIterator(FIELDS.lastIndexOf(field));
		// field本身不重置
		if (iterator.hasNext())
			iterator.next();
		while (iterator.hasNext()) {
			int subfield = iterator.next();
			cal.set(subfield, getCalendar(cutover).get(subfield));
		}
		return cal;
	}

	public Calendar getMaximumByField(Date time, int field) {
		Calendar cal = getMinimumFields(time, field);
		cal.add(Calendar.MILLISECOND, -1);
		cal.add(field, 1);
		return cal;
	}
}
