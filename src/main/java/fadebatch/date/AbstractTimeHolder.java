package fadebatch.date;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 提供通用Time操作
 */
public abstract class AbstractTimeHolder extends ThreadSafeTimeholder {
	private static final long serialVersionUID = 2915275521110735922L;

	public Date startTime() {
		Date time = getMainTime();
		Date startTime = findStartTime(time);
		return offsetTime(startTime);
	}

	public Date endTime() {
		Date time = getMainTime();
		Date endTime = findEndTime(time);
		return offsetTime(endTime);
	}

	public Date getTime() {
		Date time = getMainTime();
		return offsetTime(time);
	}

	public String getTime(String pattern) {
		return new SimpleDateFormat(pattern).format(getTime());
	}

	public void setTime(Date date) {
		updateMainTime(date);
	}

	protected abstract Date offsetTime(Date time);

	protected abstract Date findEndTime(Date time);

	protected abstract Date findStartTime(Date time);
}
