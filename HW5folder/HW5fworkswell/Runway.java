/**
 * This class represents a Runway, which manages the landing queue and clear status
*/
public class Runway
{
    // isClear represents if the runway is clear for use or not
    private boolean isClear;
    // clearTimeAt represents the time are which the runway will be ready for use
    private int clearTimeAt;
 
    /**
     * Runway is the class constructor
    */
    public Runway()
    {
        isClear =  true;
        clearTimeAt = 0;
    }

    /**
     * Sets the time at which the runway will be clear for use
     * 
     * @param time The time at which the runway will be clear
    */
    public void setClearTime(int time)
    {
        clearTimeAt = time;
    }

    /**
     * Checks if the runway is clear for use at the given current time
     * 
     * @param currentTime The current time
     * @return True if the runway is clear; false otherwise
    */
    public boolean isRunwayClear(int currentTime)
    {
        if (clearTimeAt < currentTime)
        {
            return true;
        }
        else
        {
            return false;  
        }
    }
}
