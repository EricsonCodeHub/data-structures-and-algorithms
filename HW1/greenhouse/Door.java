
public class Door extends Event implements Runnable
{
	private final String eventName = "Door";
	Thread thrd;
	
	public Door()
	{
		setStartTime(-1);
		setStopTime(-1);
		setPriority(-1);
		setIsActive(false);
		setTestTime(-1);
		setFailedTime(-1);
		setThreadStartTime(-1);
		setThreadExpired(false);
	}
	public String getEventName()
	{
		return eventName;
	}
	
	public void startThread(long startTime)
	{
		thrd = new Thread(this, "Door Thread creation");
		if (getStopTime() != -1)
			setThreadStartTime(startTime);
		thrd.start();
	}
	public void run()
	{
		setIsActive(true);
		System.out.println("Door thread has started at time " + System.nanoTime() / 1000000);
		while (getIsActive() == true)
		{
			if ( ( ( (System.nanoTime() / 1000000) >= 
			         (getThreadStartTime() + getStopTime()) ) &&
				   (getStopTime() != -1) ) ||
				 ( (getFailedTime() != -1) &&
				   ( (System.nanoTime() / 1000000) >= (getThreadStartTime() + getFailedTime()) ) ) )
			{
				setIsActive(false);
				setThreadExpired(true);
			}
		}
		System.out.println("Door thread has terminated at time " + System.nanoTime() / 1000000);
	}
}