package fadebatch.date;

import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import fadebatch.date.NaturalCutoverHolder;
import fadebatch.date.RealTimeHolder;
import fadebatch.date.TimeHolder;

public class TimeHolderTest {

	@Test
	public void testTimeHolder() {
		TimeHolder holder = new TimeHolder(0, Calendar.DATE);
		Assert.assertEquals(holder.startTime(), holder.endTime());
	}

	@Test
	public void testTimeHolder2() throws InterruptedException {
		TimeHolder holder = new TimeHolder(0, Calendar.DATE);
		Date startTime = holder.startTime();
		Date endTime = holder.endTime();
		holder.clean();
		Thread.sleep(1);
		Date startTime2 = holder.startTime();
		Date endTime2 = holder.endTime();
		Assert.assertNotSame(startTime2, startTime);
		Assert.assertNotSame(endTime, endTime2);
	}

	@Test
	public void testTimeHolder3() {
		TimeHolder holder = new TimeHolder(-1, Calendar.DATE);
		Assert.assertEquals(holder.startTime(), holder.getTime());
	}

	@Test
	public void testNaturalTimeHolder1() {
		NaturalCutoverHolder holder = new NaturalCutoverHolder(0, Calendar.DATE);
		Date startTime = holder.startTime();
		Date endTime = holder.endTime();
		Assert.assertNotSame(startTime, endTime);
		Calendar cal = TimeHolder.getCalendar(endTime);
		cal.add(Calendar.DATE, -1);
		Assert.assertEquals(cal.getTime(), startTime);
		Assert.assertEquals(cal.get(Calendar.DATE), Calendar.getInstance().get(Calendar.DATE));
	}

	@Test
	public void testNaturalTimeHolder2() {
		NaturalCutoverHolder holder = new NaturalCutoverHolder(-1, Calendar.YEAR);
		Date startTime = holder.startTime();
		Date endTime = holder.endTime();
		Calendar startCal = TimeHolder.getCalendar(startTime);
		Calendar endCal = TimeHolder.getCalendar(endTime);
		Assert.assertEquals(startCal.get(Calendar.MONTH), 0);
		Assert.assertEquals(startCal.get(Calendar.DATE), 1);
		Assert.assertEquals(startCal.get(Calendar.HOUR), 0);
		Assert.assertEquals(startCal.get(Calendar.MINUTE), 0);
		Assert.assertEquals(startCal.get(Calendar.SECOND), 0);
		Assert.assertEquals(startCal.get(Calendar.MILLISECOND), 0);
		Assert.assertEquals(endCal.get(Calendar.MONTH), 0);
		Assert.assertEquals(endCal.get(Calendar.DATE), 1);
		Assert.assertEquals(endCal.get(Calendar.HOUR), 0);
		Assert.assertEquals(endCal.get(Calendar.MINUTE), 0);
		Assert.assertEquals(endCal.get(Calendar.SECOND), 0);
		Assert.assertEquals(endCal.get(Calendar.MILLISECOND), 0);
	}

	@Test
	public void testRealTimeHolder1() {
		RealTimeHolder holder = new RealTimeHolder(0, Calendar.DATE);
		Date startTime = holder.startTime();
		Date endTime = holder.endTime();
		Assert.assertNotSame(startTime, endTime);
		Calendar cal = TimeHolder.getCalendar(endTime);
		cal.add(Calendar.DATE, -1);
		Assert.assertEquals(cal.getTime(), startTime);
		Assert.assertEquals(TimeHolder.getCalendar(endTime).get(Calendar.DATE),
		        Calendar.getInstance().get(Calendar.DATE));
	}

	@Test
	public void testGetTime() {
		TimeHolder holder = new BatchCutoverHolder(-1, Calendar.MILLISECOND);
		String time = holder.getTime(true);
		System.out.println(time);
	}
}
