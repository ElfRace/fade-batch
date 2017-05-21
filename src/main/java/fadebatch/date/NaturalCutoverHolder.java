package fadebatch.date;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * 以自然日为日切点的类，支持其它时间单位【年月日时分秒毫秒】<br>
 * 当mainTime等于cutover点时，取上一个cutover点，与realTimeHolder保持行为一致
 */
public class NaturalCutoverHolder extends TimeHolder {
	private static final long serialVersionUID = -6524673864762456183L;
	private static final LinkedList<Integer> FIELDS = new LinkedList<Integer>(Arrays.asList(1, 2, 5, 11, 12, 13, 14));

	public NaturalCutoverHolder(int offset, int field) {
		super(offset, field);
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
		Calendar calendar = getMinimumByField(time, field);
		if (onCutover(time, field))
			return offsetTime(calendar.getTime(), -1);
		return calendar.getTime();
	}

	public boolean onCutover(Date time, int field) {
		Date cutover = getMinimumByField(time, field).getTime();
		return cutover.equals(time);
	}

	public static Calendar getMinimumByField(Date time, int field) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		ListIterator<Integer> iterator = FIELDS.listIterator(FIELDS.lastIndexOf(field));
		// field本身不重置
		if (iterator.hasNext())
			iterator.next();
		while (iterator.hasNext()) {
			int subfield = iterator.next();
			cal.set(subfield, cal.getActualMinimum(subfield));
		}
		return cal;
	}

	public static Calendar getMaximumByField(Date time, int field) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		ListIterator<Integer> iterator = FIELDS.listIterator(FIELDS.lastIndexOf(field));
		// field本身不重置
		if (iterator.hasNext())
			iterator.next();
		while (iterator.hasNext()) {
			int subfield = iterator.next();
			cal.set(subfield, cal.getActualMaximum(subfield));
		}
		return cal;
	}
}
