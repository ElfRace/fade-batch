package util;

public interface Task<P> {
	/**
	 * 轮询时需要执行的操作
	 * 
	 * @return 返回是否需要再次轮询<br>
	 *         true:需要<br>
	 *         false:不需要
	 */
	boolean task(P param);

	/**
	 * 当最后一次轮询仍然失败时执行的回调方法
	 */
	void failureCallBack();
}
