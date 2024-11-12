import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;

public class Runway
{
    private Queue<Airplane> landingQueue = new LinkedList<>();
    private Queue<Airplane> takeoffQueue = new LinkedList<>();
    private ArrayList<Airplane> arrayCrashed = new ArrayList<>();
    private ArrayList<Airplane> arraylanded = new ArrayList<>();
    private ArrayList<Airplane> arraytookoff = new ArrayList<>();
    private ArrayList<Airplane> inAirTimeArray = new ArrayList<>();

    private int maxLandingQueueTime;
    private int landingTime;
    private int TakeoffTime;

    private boolean isClear =  true;
    private int clearTimeAt = Airport.getCurrentTime();
 

    public Runway(int maxLandingQueueTime, int landingTime, int TakeoffTime)
    {
        this.maxLandingQueueTime = maxLandingQueueTime;
        this.landingTime  = landingTime;
        this.TakeoffTime  =  TakeoffTime;
    }

    public void addLandingQueue(Airplane plane)
    {
        landingQueue.add(plane);
        System.out.println("plane added to landing Q");
    }

    public void addTakeoffQueue( Airplane plane)
    {
        takeoffQueue.add(plane);
         System.out.println("plane added to takeoff Q");
    }

    public boolean isRunwayClear()
    {
        if (clearTimeAt < Airport.getCurrentTime())
        {
             System.out.println("runway not clear");
            return true;
        }
        else
        {
            System.out.println("runwayclear");
            return false;  
        }
    }

    public void simulateRunway()
    {
        if(!isRunwayClear());
        else if(!landingQueue.isEmpty())
        {

            for (Airplane temp = landingQueue.peek(); temp != null && maxLandingQueueTime < (Airport.getCurrentTime() - temp.getArrivalTime()); )
            {
                temp.setLandTime();
                arrayCrashed.add(temp);
                inAirTimeArray.add(temp);
                System.out.println("plane added to crashed array");
                landingQueue.remove();
                temp = landingQueue.peek();

            }
            if(!landingQueue.isEmpty())
            {
                Airplane temp = landingQueue.remove();
                temp.setLandTime();
                arraylanded.add(temp);
                inAirTimeArray.add(temp);
                System.out.println("plane added to landed array");

                clearTimeAt = Airport.getCurrentTime() + landingTime;
            }

            
        }
        else if(!takeoffQueue.isEmpty())
        {
            Airplane temp = takeoffQueue.peek();
            temp.setTakeOffTime();
            arraytookoff.add(temp);
            System.out.println("plane added to takeoff array");
            takeoffQueue.remove();

            clearTimeAt = Airport.getCurrentTime() + TakeoffTime;
        }
        else;


    }

    public ArrayList<Airplane> getCrashedArray()
    {
        return arrayCrashed;
    }

    public ArrayList<Airplane> getLandedArray()
    {
        return arraylanded;
    }

    public ArrayList<Airplane> getTookoff()
    {
        return arraytookoff;
    }

    public ArrayList<Airplane> getInAirTimeArray()
    {
        return inAirTimeArray;
    }

}
