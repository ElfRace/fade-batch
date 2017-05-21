package fadebatch.date;

import java.util.Date;

/**
 * 实时日切点类，支持其它时间单位【年月日时分秒毫秒】<br>
 * 时间=结束时间=开始时间+1（指定时间单位上+1）
 */
public class RealTimeHolder extends TimeHolder {
	private static final long serialVersionUID = -4799920076962300475L;

	public RealTimeHolder(int offset, int field) {
		super(offset, field);
	}

	@Override
	protected Date cleanStartTimeIfNeccesary(Date time, int field) {
		Date startTime = super.cleanStartTimeIfNeccesary(time, field);
		return offsetTime(startTime, -1);
	}

}
