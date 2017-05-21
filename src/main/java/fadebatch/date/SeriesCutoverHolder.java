package fadebatch.date;

import java.util.Date;

import fadebatch.date.enums.HolderType;

public class SeriesCutoverHolder extends AbstractTimeHolder {
	private static final long serialVersionUID = -875438836595201717L;
	private final int amount;
	private TimeHolder holder;
	private final HolderType type;

	public SeriesCutoverHolder(int amount, TimeHolder holder) {
		this(amount, holder, HolderType.endMainTime);
	}

	public SeriesCutoverHolder(int amount, TimeHolder holder, HolderType type) {
		super();
		this.amount = amount;
		this.holder = holder;
		this.type = type;
	}

	@Override
	protected Date offsetTime(Date time) {
		return holder.offsetTime(time);
	}

	@Override
	protected Date findEndTime(Date time) {
		Date holderEndTime = holder.findEndTime(time);
		return holder.changeTime(holderEndTime, type.getEndOffset(amount));
	}

	@Override
	protected Date findStartTime(Date time) {
		Date holderStartTime = holder.findStartTime(time);
		return holder.changeTime(holderStartTime, type.getStartOffset(amount));
	}

	@Override
	public Date startTime() {
		return holder.startTime();
	}

	@Override
	public Date endTime() {
		return holder.endTime();
	}

	@Override
	public Date getTime() {
		return holder.getTime();
	}

	@Override
	public String getTime(String pattern) {
		return holder.getTime(pattern);
	}

	@Override
	public void setTime(Date date) {
		holder.setTime(date);
	}
}
