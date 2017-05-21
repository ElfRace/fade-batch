package fadebatch.date;

import java.io.Serializable;
import java.util.Calendar;

import fadebatch.date.interfaces.BatchDate;
import fadebatch.date.interfaces.Cleanable;

/**
 * 保证线程安全的类，其它实现dater的必要属性都应当是final的
 */
public abstract class ThreadSafeTimeholder implements BatchDate, Cleanable, Serializable {
	private static final long serialVersionUID = 7420539985168297927L;

	private ThreadLocal<FloorMilliDate> mainTime = new ThreadLocal<FloorMilliDate>() {
		@Override
		protected FloorMilliDate initialValue() {
			return new FloorMilliDate();
		}
	};

	protected java.util.Date getMainTime() {
		return (java.util.Date) mainTime.get().clone();
	}

	protected void updateMainTime(java.util.Date time) {
		mainTime.set(new FloorMilliDate(time));
	}

	public void clean() {
		mainTime.remove();
	}

	private class FloorMilliDate extends java.util.Date {
		private static final long serialVersionUID = 6418536292035949078L;

		public FloorMilliDate() {
			super();
			resetMilliSecond(this);
		}

		public FloorMilliDate(java.util.Date date) {
			super();
			resetMilliSecond(date);
		}

		private void resetMilliSecond(java.util.Date date) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.set(Calendar.MILLISECOND, 0);
			this.setTime(cal.getTimeInMillis());
		}
	}
}
