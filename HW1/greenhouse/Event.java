
/** This is the parent class that all of the event classes
    inherit from.
 */
public class Event 
{
	private int startTime;			// event start time (start time of the event thread)
	private int stopTime;			// event stop time (stop time of the event thread)
	private int priority;			// event priority
	private boolean isActive;		// flag indicating whether or not the event thread is executing
	private int testTime;			// the time an event object is tested
	private int failedTime;			// the time that an executing event thread is to fail
	private long threadStartTime;	// the actual start time of the tread
	private boolean threadExpired;	// indicates that this thread has finished executing 
	
	public void setStartTime(int time)
	{
		startTime = time;
	}
	public int getStartTime()
	{
		return startTime;
	}
	public void setStopTime(int time)
	{
		stopTime = time;
	}
	public int getStopTime()
	{
		return stopTime;
	}
	public void setPriority(int priority)
	{
		this.priority = priority;
	}
	public int getPriority()
	{
		return priority;
	}
	public void setIsActive(boolean on)
	{
		isActive = on;
	}
	public boolean getIsActive()
	{
		return isActive;
	}
	public void setTestTime(int t)
	{
		testTime = t;
	}
	public int getTestTime()
	{
		return testTime;
	}
	public void setFailedTime(int f)
	{
		failedTime = f;
	}
	public int getFailedTime()
	{
		return failedTime;
	}
	public void setThreadStartTime(long t)
	{
		threadStartTime = t;
	}
	public long getThreadStartTime()
	{
		return threadStartTime;
	}
	public void setThreadExpired(boolean t)
	{
		threadExpired = t;
	}
	public boolean getThreadExpired()
	{
		return threadExpired;
	}
	
	// this method is a dummy method that is here because 
	// it is needed (and overwritten) in the inherited classes
	public String getEventName()
	{
		return null;
	}
	
	// this method is a dummy method that is here because 
	// it is needed (and overwritten) in the inherited classes
	public void startThread(long t)
	{
	
	}
	
	// this method is a dummy method that is here because 
	// it is needed (and overwritten) in the inherited classes
	public void startThread(int t)
	{
	
	}
}