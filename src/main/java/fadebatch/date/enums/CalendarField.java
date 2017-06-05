package fadebatch.date.enums;

import java.util.Calendar;

public enum CalendarField {
	year(Calendar.YEAR) {
		@Override
		String getPattern(boolean standard) {
			return "yyyy";
		}

	},
	month(Calendar.MONTH) {
		@Override
		String getPattern(boolean standard) {
			return standard ? "-MM" : "MM";
		}
	},
	date(Calendar.DATE) {
		@Override
		String getPattern(boolean standard) {
			return standard ? "-dd" : "dd";
		}
	},
	hour(Calendar.HOUR) {
		@Override
		String getPattern(boolean standard) {
			return standard ? " HH" : "HH";
		}
	},
	minute(Calendar.MINUTE) {
		@Override
		String getPattern(boolean standard) {
			return standard ? ":mm" : "mm";
		}
	},
	second(Calendar.SECOND) {
		@Override
		String getPattern(boolean standard) {
			return standard ? ":ss" : "ss";
		}
	},
	milli(Calendar.MILLISECOND) {
		@Override
		String getPattern(boolean standard) {
			return standard ? ".SS" : "SS";
		}
	};

	private int value;
	private static final CalendarField[] FIELDS = new CalendarField[] { year, month, date, hour, minute, second,
	        milli };

	private CalendarField(int value) {
		this.value = value;
	}

	abstract String getPattern(boolean standard);

	public static String getPatternString(int field, boolean standard) {
		StringBuilder sb = new StringBuilder();
		for (CalendarField calendarField : FIELDS) {
			if (calendarField.value <= field)
				sb.append(calendarField.getPattern(standard));
		}
		return sb.toString();
	}

	@Override
	public String toString() {
		return this.name() + " value:" + value;
	}
}
