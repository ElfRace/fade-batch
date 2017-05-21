package fadebatch.date;

import java.util.Calendar;
import java.util.Date;

/**
 * 如果以小时或更小的时间单位分割，则与NaturalCutoverHolder行为一致
 */
public class BatchCutoverHolder extends NaturalCutoverHolder {
	private static final long serialVersionUID = -4333126571797182817L;
	private final boolean needConvertToBatchCutover;

	public BatchCutoverHolder(int offset, int field) {
		super(offset, field);
		this.needConvertToBatchCutover = field < Calendar.AM_PM;
	}

	@Override
	protected Date cleanEndTimeIfNeccesary(Date time, int field) {
		Date endTime = super.cleanEndTimeIfNeccesary(time, field);
		endTime = convertToBatchCutoverIfNeccessary(endTime);
		return endTime;
	}

	@Override
	protected Date cleanStartTimeIfNeccesary(Date time, int field) {
		Date startTime = super.cleanStartTimeIfNeccesary(time, field);
		startTime = convertToBatchCutoverIfNeccessary(startTime);
		return startTime;
	}

	private Date convertToBatchCutoverIfNeccessary(Date startTime) {
		if (needConvertToBatchCutover) {
			Calendar cal = getCalendar(startTime);
			cal.set(Calendar.HOUR_OF_DAY, 23);
			startTime = cal.getTime();
		}
		return startTime;
	}
}
