
public class Bell extends Event implements Runnable
{
	private final String eventName = "Bell";
	private int numRings; 
	Thread thrd;
	
	public Bell()
	{
		setStartTime(-1);
		setStopTime(-1);
		setPriority(-1);
		setIsActive(false);
		setTestTime(-1);
		setFailedTime(-1);
		setThreadStartTime(-1);
		setThreadExpired(false);
		numRings = -1;
	}
	public String getEventName()
	{
		return eventName;
	}
	
	public void startThread(long startTime)
	{
		thrd = new Thread(this, "Bell Thread creation");
		if (getStopTime() != -1)
			setThreadStartTime(startTime);
		thrd.start();
	}
	public void startThread(int num)
	{
		numRings = num;
		thrd = new Thread(this, "Bell Thread creation");
		thrd.start();
	}
	public void run()
	{
		if (numRings != -1)
		{
			System.out.println("Bell ring thread has started at time " + System.nanoTime() / 1000000);
			for (int i = 0; i < numRings; i++)
				System.out.println("Bell test - ring");
			System.out.println("Bell test thread has terminated at time " + System.nanoTime() / 1000000);
			numRings = -1;
			setThreadExpired(true);
			setTestTime(-1);
		}
		else
		{
			setIsActive(true);
			System.out.println("Bell ring has started at time " + System.nanoTime() / 1000000);
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
			System.out.println("Bell thread has terminated at time " + System.nanoTime() / 1000000);
		}
	}
}