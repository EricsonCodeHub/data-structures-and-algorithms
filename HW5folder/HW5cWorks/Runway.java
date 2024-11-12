/**
 * This class represents a Runway, which manages the landing queue and clear status
*/
public class Runway
{
    // boolean 
    private boolean isClear =  true;
    private int clearTimeAt = 0;
 
    public Runway()
    {
    }

    public void setClearTime(int time)
    {
        clearTimeAt = time;
    }

    public boolean isRunwayClear(int currentTime)
    {
        if (clearTimeAt < currentTime)
        {
            System.out.println("runwayclear");
            return true;
        }
        else
        {
            System.out.println("runway not clear");
            return false;  
        }
    }
}
