public class Airplane {

    private int arrivalTime;
    private int takeoffTime;
    private int landTime;

    private int timeInLandingQueue;
    private int timeInTakeoffQueue;
    

    public Airplane() 
    {
        arrivalTime = Airport.getCurrentTime();
        System.out.println("plane made");
    }

    public void setTakeOffTime()
    {
        takeoffTime = Airport.getCurrentTime();
        timeInTakeoffQueue = takeoffTime - arrivalTime;
    }

    public void setLandTime()
    {
        landTime = Airport.getCurrentTime();
        timeInLandingQueue = landTime - arrivalTime;
    }

    public int getTimeInLandingQueue()
    {
        return timeInLandingQueue;
    }

    public int getTimeInTakeoffQueue()
    {
        return timeInTakeoffQueue;
    }

    public int getArrivalTime()
    {
        return arrivalTime;
    }


    
}
