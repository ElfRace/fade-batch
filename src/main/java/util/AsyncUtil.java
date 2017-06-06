package util;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class AsyncUtil {
	private static final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(10);

	public static <P> void doTask(Task<P> task, P param, int interval, int times) {
		doTask(new PeriodicTask<P>(task, param, interval, times));
	}

	private static void doTask(PeriodicTask<?> task) {
		executor.schedule(task, task.interval, TimeUnit.SECONDS);
	}

	public synchronized static void setPoolSize(int size) {
		executor.setCorePoolSize(size);
	}

	private static class PeriodicTask<P> implements Runnable {
		private Task<P> task;
		private int interval;
		private int times;
		private P param;

		public PeriodicTask(Task<P> task, P param, int interval, int times) {
			super();
			this.task = task;
			this.param=param;
			this.interval = interval;
			this.times = times;
		}

		public void run() {
			times--;
			boolean needToRunAgain = task.task(param);
			if (needToRunAgain) {
				if (times > 0)
					AsyncUtil.doTask(this);
				else
					task.failureCallBack();
			}
		}

	}
}
