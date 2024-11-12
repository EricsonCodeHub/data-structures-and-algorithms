import java.util.Scanner;
import java.io.*;
import java.util.StringTokenizer;
import myExceptions.InvalidEvent;;
import java.util.ArrayList;

/**
    This class simulates the functionality of a greenhouse.  It monitors the 
	temperature inside the greenhouse and simulates opening windows to cool it
	down if the temperature gets too high.  It reads a file containing events
	that are injected into the greenhouse process.  These events include opening
	and closing windows, starting and stopping fans, activating an alarm bell 
	when the temperature gets too high, and turning lights on and off.
 */
public class Greenhouse implements Runnable
{
	private int runState;			// stores one of the run states
	public final int running = 1;	// one of the possible runStates
	public final int restart = 2;	// one of the possible runStates
	public final int terminate = 3;	// one of the possible runStates
	
	private ArrayList <Event> eventList;	// container for the event objects
	private RandomAccessFile file;			// used for opening the 
                                            // greenhouse_plan.txt file
	
	// the following two variables are declared here instead of in the run method
	// because they need to be saved if the greenhouse thread is restarted

	private static long badDataPointer = -1;// used for saving the location of 
                                            // the file pointer when a greenhouse
                                           	// reset is processed

	private long filePointer;	// location of current file pointer
	
	// Exception class for the exception thrown when invalid data is read from
	// the greenhouse_plan.txt file
	private InvalidEvent iEvent = new InvalidEvent("bad data");
	
	Thread thrd;	// thread reference

	/** Constructor for the greenhouse object.
	    Initializes the greenhouse.
		Starts the greenhouse thread.
	 */
	public Greenhouse()
	{
		runState = running;
		initializeGreenhouse();
		thrd = new Thread(this, "Greenhouse Thread creation");
		thrd.start();
	}
	
	/** The getRunState method returns one of three values:
	    running, restart, terminate.
	    @return returns the value of runState
	 */
	public int getRunState()
	{
		return runState;
	}
	
	/** The initializeGreenhouse method initializes the greenhouse
	    by creating the ArrayList object and creating event objects
		and adding them to the ArrayList.
	 */
	private void initializeGreenhouse()
	{
		eventList = new ArrayList <Event>();
		eventList.add(new Bell());
		eventList.add(new Thermostat());
		eventList.add(new Light());
		eventList.add(new Water());
		eventList.add(new Fan());
		eventList.add(new Window());
		eventList.add(new Door());
	}
	
	/** The run method is executed when the greenhouse thread is started.
	    It contains two loops, one for reading in the items in the 
		greenhouse_plan.txt file and saving the data to the event objects in the 
        ArrayList. Thesecond loop continuously loops through the ArrayList,
		starting and stopping the event threads at the designated times.
	 */
	public void run()
	{
		// The Scanner is used to read the users response when an
		// exception is thrown as a result of reading invalid data
		// in the greenhouse_plan.txt file
		Scanner keyboard = new Scanner(System.in);
		
		String line = null;		// stores each line of data read from the
								// greenhouse_plan.txt file
		
		System.out.println("Greenhouse thread has started");
		
		// try/catch construct is needed because an IOException can result 
		// if the file can not be read, and an InvalidEvent can result if
		// invalid data is read from the file.
		try
		{
			// create the object for reading from the file.
			file = new RandomAccessFile("greenhouse_plan.txt", "r");
			
			// If this is a restart, and we have bad data in the file,
			// and this line has the bad data, 
			// this should skip over it
			if ((badDataPointer != -1) && 
                (badDataPointer == file.getFilePointer()))

				line = file.readLine();
			
			// read the first line in the file
			line = file.readLine();
			
			// This loop reads the greenhouse_plan.txt file,
			// and calls the appropriate method to process each line in
			// the file.
			System.out.println(
                   "****** Start reading the greenhouse_plan.txt file ******");
			while (line != null)
			{
				// the tokenizer uses the equal sign and comma to separate
				// the items in each line into tokens
				StringTokenizer strT = new StringTokenizer(line, "=,");
				
				// the first token in each line is one of 
				// event, priority, test, or failed.  Anything else is 
				// invalid data and will result in an InvalidEvent exception.
				String type = strT.nextToken();
				
				switch (type)
				{
					case "event":
						filePointer = file.getFilePointer();
						processEvent(strT);
						break;
					case "priority":
						filePointer = file.getFilePointer();
						processPriority(strT);
						break;
					case "test":
						filePointer = file.getFilePointer();
						processTest(strT);
						break;
					case "failed":
						filePointer = file.getFilePointer();
						processFailed(strT);
						break;
					default:
						throw iEvent;
				}

				// If this is a restart, and we have bad data in the file,
				// and this line has the bad data, 
				// this should skip over it
				if ((badDataPointer != -1) && 
                    (badDataPointer == file.getFilePointer()))

					line = file.readLine();
				
				line = file.readLine();
			}
			file.close();
		}
		catch (InvalidEvent e)
		{
			// When invalid event is found in the file, ask the user if 
			// a restart of the greenhouse is desired.
			//
			// If the answer is yes, set runState to restart which will
			// result in the current greenhouse thread terminating and a
			// new one starting.  Because filePointer has been saved each
			// time a line was read from the file, when the greenhouse 
			// thread is restarted, it will skip over the line with the bad data
			//
			// If the answer is no, runState is set to terminate which
			// will result in the current greenhouse thread terminating.
			System.out.println("An invalid item was read from the file.");
			System.out.println("Do you want to reset the greenhouse (y/n)?  ");
			String response = keyboard.nextLine();
			
			// this returns the value of the filePointer to the beginning of the
			// line of bad data.
			badDataPointer = filePointer - line.length() -2;
			
			if (response.toLowerCase().charAt(0) == 'n')
			{
				resetEvents();
				runState = terminate;
			}
			else if (response.toLowerCase().charAt(0) == 'y')
			{
				resetEvents();
				runState = restart;
			}
		}
		catch (IOException e)
		{
			// this exception is thrown if the greenhouse_plan.txt file 
            // could not be read.
			System.out.println("Could not open file.  Program terminating");
			System.exit(1);
		}
		System.out.println("****** File read complete ******");
		
		// At this point the greenhouse_plan.txt file has been read and the 
		// ArrayList of event objects is ready to be processed.  So the start 
        // time is recorded which will be used as the reference point
		// for starting and stopping the event threads.
		long startTime = System.nanoTime() / 1000000;
		System.out.println("start time: " + startTime);
		
		boolean threadsExecuting = true;	// flag used to determine when to 
											// terminate the greenhouse thread
		long stopTime = 0;	// a time that will contain the ending time of the
							// last event thread that terminates

		// loop through the ArrayList of event objects and start and stop the 
		// event threads at the appropriate times
		do
		{
			int executingThreadCount = 0;
			
			for (int i = 0; i < eventList.size(); i++)
			{
				// set a flag based on the current time being greater than the event
				// start time, assuming there is an event start time.
				boolean threadAfterStartTime = 
                        (((System.nanoTime() / 1000000) >= 
                         (eventList.get(i).getStartTime() + startTime)) &&
                         (eventList.get(i).getStartTime() != -1));
												
				// set a flag to determing if this event thread is currently active
				boolean isThreadActive = eventList.get(i).getIsActive() == true;
				
				// set a flag to indicate if the event stop time has been reached.
				// this is set to true to indcate that either there is not stop 
				// time, or the the stop time has not been reached yet.
				boolean threadBeforeEndTime = true;
				
				// set the event stop time flag to false if we are past the 
				// thread stop time.
				if ((eventList.get(i).getStopTime() != -1) &&
				    ((System.nanoTime() / 1000000) >
					 (eventList.get(i).getStopTime() + startTime)))
				{
					threadBeforeEndTime = false;
				}
				
				// if it is time to start an event thread, based on the event
				// start time, start the thread.
				if (threadAfterStartTime && threadBeforeEndTime && 
				    !isThreadActive && !eventList.get(i).getThreadExpired())
				{
					// start the event thread
					eventList.get(i).startThread(startTime);
					
					// this sleep is used to give the thread a chance to get 
					// started and change the isThreadActive flag to true so a 
					// second instance of this objects thread can not be started 
					// while this thread is executing.
					try
					{
						Thread.sleep(100);
					}
					catch (InterruptedException e)
					{
						// do not do anything if this exception is thrown
					}
				}

				// run test
				if ((eventList.get(i).getTestTime() != -1) &&
				    ((System.nanoTime() / 1000000) >
					 (eventList.get(i).getTestTime() + startTime)) &&
					!eventList.get(i).getThreadExpired())
				{
					executeTest(i);
					
					// this sleep is used to give the thread a chance to get started
					// and change the isThreadActive flag to true so a second instance
					// of this objects thread can not be started while this thread is
					// executing.
					try
					{
						Thread.sleep(100);
					}
					catch (InterruptedException e)
					{
						// do not do anything if this exception is thrown
					}
				}
				
				// process fail
				if ((eventList.get(i).getFailedTime() != -1) &&
				    ((System.nanoTime() / 1000000) >= 
					 (eventList.get(i).getFailedTime() + startTime)))
				{
					Bell alarm = new Bell();
					alarm.startThread(5);
					eventList.get(i).setFailedTime(-1);
				}
				
				// need to determine what the stop time of the last thread
				// to terminate is so that the greenhouse thread can be
				// terminated when there are no longer any executing
				// event threads.
				if (eventList.get(i).getStopTime() > stopTime)
				{
					stopTime = eventList.get(i).getStopTime();
				}
				
				// increment executingThreadCount if there are any event threads
				// currently executing
				if (eventList.get(i).getIsActive() == true)
					executingThreadCount++;
			}

			// if here, we are past the ending time of the event thread that 
			// terminated last, set the threadsExecuting to false so that the
			// this loop will terminate - thus ending the greenhouse thread.
			if (((System.nanoTime() / 1000000) > (startTime + stopTime)) &&
			     (executingThreadCount == 0))
			{
				threadsExecuting = false;
			}
		} while(threadsExecuting);
		System.out.println("Exiting Greenhouse thread " + 
                            System.nanoTime() / 1000000);
		runState = terminate;
	}
	
	/** The processEvent method determines which event type to process
	    and calls the appropriate method to save the event data into the 
		ArrayList. This method throws an InvalidEvent exception if an invalid 
		event type is read from the greenhouse_plan.txt file.
		@param strT a StringTokenizer object reference to the object that 
		            contains the tokens from a line of data read from the
					greenhouse_plan.txt tile.
	 */
	private void processEvent(StringTokenizer strT) throws InvalidEvent
	{
		String eventType = strT.nextToken();
		switch (eventType)
		{
			case "Bell":
				System.out.println("Bell event");
				setEvent("Bell", strT);
				break;
			case "Thermostat":
				System.out.println("Thermostat event");
				setEvent("Thermostat", strT);
				break;
			case "Light":
				System.out.println("Light event");
				setEvent("Light" , strT);
				break;
			case "Water":
				System.out.println("Water event");
				setEvent("Water" , strT);
				break;
			case "Fan":
				System.out.println("Fan event");
				setEvent("Fan" , strT);
				break;
			case "Window":
				System.out.println("Window event");
				setEvent("Window" , strT);
				break;
			case "Door":
				System.out.println("Door event");
				setEvent("Door" , strT);
				break;
			default:
				throw iEvent;
		}
	}
	
	/** The processPriority method will set the priority of the specified event.
	    This method throws an InvalidEvent exception if an invalid event type is  
		read from the greenhouse_plan.txt file.
		@param strT a StringTokenizer object reference to the object that 
		            contains the tokens from a line of data read from the
					greenhouse_plan.txt tile.
	 */
	private void processPriority(StringTokenizer strT) throws InvalidEvent
	{
		String eventType = strT.nextToken();
		String priority = strT.nextToken();
		switch (eventType)
		{
			case "Bell":
				System.out.println("Bell priority");
				setPriority("Bell", priority);
				break;
			case "Thermostat":
				System.out.println("Thermostat priority");
				setPriority("Thermostat", priority);
				break;
			case "Light":
				System.out.println("Light priority");
				setPriority("Light", priority);
				break;
			case "Water":
				System.out.println("Water priority");
				setPriority("Water", priority);
				break;
			case "Fan":
				System.out.println("Fan priority");
				setPriority("Fan", priority);
				break;
			case "Window":
				System.out.println("Window priority");
				setPriority("Window", priority);
				break;
			case "Door":
				System.out.println("Door priority");
				setPriority("Door", priority);
				break;
			case "*":
				System.out.println("* priority");
				setPriority("*", priority);
				break;
			default:
				throw iEvent;
		}
		
	}
	
	/** The processTest method will determine which event type is to be tested.
	    This method throws an InvalidEvent exception if an invalid event type is  
		read from the greenhouse_plan.txt file.
		@param strT a StringTokenizer object reference to the object that 
		            contains the tokens from a line of data read from the
					greenhouse_plan.txt tile.
	 */
	private void processTest(StringTokenizer strT) throws InvalidEvent
	{
		String eventType = strT.nextToken();
		String testTime = strT.nextToken();
		switch (eventType)
		{
			case "Bell":
				System.out.println("Bell test");
				setTestTime("Bell", testTime);
				break;
			case "Thermostat":
				System.out.println("Thermostat test");
				setTestTime("Thermostat", testTime);
				break;
			case "Light":
				System.out.println("Light test");
				setTestTime("Light", testTime);
				break;
			case "Water":
				System.out.println("Water test");
				setTestTime("Water", testTime);
				break;
			case "Fan":
				System.out.println("Fan test");
				setTestTime("Fan", testTime);
				break;
			case "Window":
				System.out.println("Window test");
				setTestTime("Window", testTime);
				break;
			case "Door":
				System.out.println("Door test");
				setTestTime("Door", testTime);
				break;
			default:
				throw iEvent;
		}
	}
	
	/** The processFailed method will determine which event type is failed.
	    This method throws an InvalidEvent exception if an invalid event type   
		is read from the greenhouse_plan.txt file.
		@param strT a StringTokenizer object reference to the object that 
		            contains the tokens from a line of data read from the
					greenhouse_plan.txt tile.
	 */
	private void processFailed(StringTokenizer strT) throws InvalidEvent
	{
		String eventType = strT.nextToken();
		String failTime = strT.nextToken();
		switch (eventType)
		{
			case "Bell":
				System.out.println("Bell failed");
				setFailed("Bell", failTime);
				break;
			case "Thermostat":
				System.out.println("Thermostat failed");
				setFailed("Thermostat", failTime);
				break;
			case "Light":
				System.out.println("Light failed");
				setFailed("Light" , failTime);
				break;
			case "Water":
				System.out.println("Water failed");
				setFailed("Water" , failTime);
				break;
			case "Fan":
				System.out.println("Fan failed");
				setFailed("Fan" , failTime);
				break;
			case "Window":
				System.out.println("Window failed");
				setFailed("Window" , failTime);
				break;
			case "Door":
				System.out.println("Door failed");
				setFailed("Door" , failTime);
				break;
			default:
				throw iEvent;
		}
	}
	
	/** The setEvent method stores the event start and stop times into the 
	    appropriate event object in the ArrayList.
		@param eventName a String contains the event type
		@param strT a StringTokenizer object reference to the object that 
		            contains the tokens from a line of data read from the
					greenhouse_plan.txt tile.
	 */
	private void setEvent(String eventName, StringTokenizer strT)
	{
		int index;				// loop index
		boolean found = false;	// flag to indicate if the event was found
		
		for(index = 0; index < eventList.size(); index++)
		{
			// This event type object exists in the ArrayList, but has not
			// been initialized with data from the greenhouse_plan.txt file
			if ((eventList.get(index).getEventName().equals(eventName)) &&
			    (eventList.get(index).getStartTime() == -1))
			{
				// save the start time
				eventList.get(index).setStartTime(Integer.parseInt(strT.nextToken()));
				System.out.println(eventList.get(index).getEventName() + 
								   " start time is: " +
								   eventList.get(index).getStartTime());
								   
				// get the stop time
				String eventLength = strT.nextToken();
				
				// no stop time
				if (eventLength.equals("*"))
					eventList.get(index).setStopTime(-1);
				else
					// save the stop time
					eventList.get(index).setStopTime(Integer.parseInt(eventLength)  +
													 eventList.get(index).getStartTime());
				found = true;
				System.out.println(eventList.get(index).getEventName() + 
								   " event stop time is: " +
								   eventList.get(index).getStopTime());
				break;
			}
			
		}

		// this event type already has an object in the ArrayList that 
		// has been initialized with data from the greenhouse_plan.txt file.
		// Thus a new object, of this event type must be added to the 
		// ArrayList.
		if ((index >= eventList.size()) && (found == false))
		{
			if (eventName.equals("Bell"))
				addEvent(new Bell(), strT);
			else if (eventName.equals("Thermostat"))
				addEvent(new Thermostat(), strT);
			else if (eventName.equals("Light"))
				addEvent(new Light(), strT);
			else if (eventName.equals("Water"))
				addEvent(new Water(), strT);
			else if (eventName.equals("Fan"))
				addEvent(new Fan(), strT);
			else if (eventName.equals("Window"))
				addEvent(new Window(), strT);
			else if (eventName.equals("Door"))
				addEvent(new Door(), strT); 
		}
	}
	
	/** The setPriority method sets the priority for the event.
	    @param eventName String for the name of the event 
		@param priority String for the priority 
	 */
	private void setPriority(String eventName, String priority)
	{
		// set the priority of all events
		if (eventName.equals("*"))
		{
			for(int index = 0; index < eventList.size(); index++)
			{
				eventList.get(index).setPriority(Integer.parseInt(priority));
				System.out.println(eventList.get(index).getEventName() + 
								   " priority is: " + 
								   eventList.get(index).getPriority());
			}
		}
		// set the priority of the designated event
		for(int index = 0; index < eventList.size(); index++)
		{
			if (eventList.get(index).getEventName().equals(eventName))
			{
				eventList.get(index).setPriority(Integer.parseInt(priority));
				System.out.println(eventList.get(index).getEventName() + 
									" priority is: " +
									eventList.get(index).getPriority());
			}
		}
	}
	
	/** The setTestTime method sets the test time for the event.
	    @param eventName String for the name of the event 
		@param priority String for the test time 
	 */
	private void setTestTime(String eventName, String testTime)
	{
		for(int index = 0; index < eventList.size(); index++)
		{
			if (eventList.get(index).getEventName().equals(eventName))
			{
				eventList.get(index).setTestTime(Integer.parseInt(testTime));
				System.out.println(eventList.get(index).getEventName() + 
									" test time is: " +
									eventList.get(index).getTestTime());
			}
		}
	}
	
	/** The setFailed method sets the fail time for the event.
	    @param eventName String for the name of the event 
		@param priority String for the fail time
	 */
	private void setFailed(String eventName, String failTime)
	{
		for(int index = 0; index < eventList.size(); index++)
		{
			if (eventList.get(index).getEventName().equals(eventName))
			{
				eventList.get(index).setFailedTime(Integer.parseInt(failTime));
				System.out.println(eventList.get(index).getEventName() + 
									" fail time is: " +
									eventList.get(index).getFailedTime());
			}
		}
	}
	
	/** The addEvent method adds an event with its start and stop times 
        into the ArrayList.
		@param obj a reference to an event type object
		@param strT a StringTokenizer object reference to the object that 
		            contains the tokens from a line of data read from the
					greenhouse_plan.txt tile.
	 */
	private void addEvent(Event obj, StringTokenizer strT)
	{
		// save the start time
		obj.setStartTime(Integer.parseInt(strT.nextToken()));
		System.out.println(obj.getEventName() + 
						   " start time is: " +
						   obj.getStartTime());
								   
		// get the stop time
		String eventLength = strT.nextToken();
				
		// no stop time
		if (eventLength.equals("*"))
			obj.setStopTime(-1);
		else
			// save the stop time
			obj.setStopTime(Integer.parseInt(eventLength)  +
											 obj.getStartTime());
		eventList.add(obj);
		System.out.println(obj.getEventName() + 
						   " event stop time is: " +
						   obj.getStopTime());
	}
	
	/** The executeTest method calls the event method that results
	    in the constructor being called that will loop the specified times
		and then terminate.  The other event costructor loops based on elapsed time.
		@param i the number of times the event loop is to loop before terminating 
                 the thread.
	 */
	private void executeTest(int i)
	{
		eventList.get(i).startThread(1);
	}
	
	/** The resetEvent method resets all of the event class fields to 
	     initial values.  This is called in the event of a greenhouse reset.
		 The purpose is to reuse the existing event objects in the ArrayList 
		 instead of adding new ones - refer to the setEvent method.
	 */
	private void resetEvents()
	{
		for (int i = 0; i < eventList.size(); i++)
		{
			eventList.get(i).setStartTime(-1);
			eventList.get(i).setStopTime(-1);
			eventList.get(i).setPriority(-1);
			eventList.get(i).setIsActive(false);
			eventList.get(i).setTestTime(-1);
			eventList.get(i).setFailedTime(-1);
			eventList.get(i).setThreadStartTime(-1);
			eventList.get(i).setThreadExpired(false);

		}
	}
}
