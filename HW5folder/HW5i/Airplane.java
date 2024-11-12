/**
 * The Airport class represents an airplane, the class stores and set 
 * data necessary for simulating an airport.
*/
public class Airplane {

    // the time the Airplane gets created an added to a queue
    private int arrivalTime;
    // the time the Airplane takes off
    private int takeoffTime;
    // the time the Airplane lands
    private int landTime;
    //time the plane spends in the landing queue
    private int timeInLandingQueue;
    //time the plane spends in the takeoff queue
    private int timeInTakeoffQueue;
    
    /**
     * Airplane constructor sets the arrival time of the Airplane
     * 
     * @param currentTime The current time when the Airplane is created
    */
    public Airplane(int CurrentTime) 
    {
        arrivalTime = CurrentTime;
    }

    /**
     * Sets the takeoff time of the Airplane and calculates the time spent in the takeoff queue
     * 
     * @param currentTime The current time when the Airplane takes off.
    */
    public void setTakeOffTime(int currentTime)
    {
        takeoffTime = currentTime;
        timeInTakeoffQueue = takeoffTime - arrivalTime;
    }

    /**
     * Sets the landing time of the Airplane and calculates the time spent in the landing queue.
     * 
     * @param currentTime The current time when the Airplane lands.
    */
    public void setLandTime(int currentTime)
    {
        landTime = currentTime;
        timeInLandingQueue = landTime - arrivalTime;
    }

    /**
     * Returns the time spent by the Airplane in the landing queue
     * 
     * @return The time spent in the landing queue
    */
    public int getTimeInLandingQueue()
    {
        return timeInLandingQueue;
    }

    /**
     * Returns the time spent by the Airplane in the takeoff queue
     * 
     * @return The time spent in the takeoff queue
    */
    public int getTimeInTakeoffQueue()
    {
        return timeInTakeoffQueue;
    }

    /**
     * Returns the arrival time of the Airplane
     * 
     * @return The arrival time of the Airplane
    */
    public int getArrivalTime()
    {
        return arrivalTime;
    }


    
}
