import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

public class Airport {
    //timer
    public static int currentTime = 0;

    //inputs
    public int timeNeededLanding;
    public int timeNeededTakeoff;
    public double probOfTakeoff;
    public double probOfLanding;
    public int maxTimeLandingQueue;
    public int totalSimulationTime;
    public Runway runway;

    public static void main(String[] args) 
    {
        Airport airport = new Airport();
        airport.getInputs();
        airport.runway = new Runway(airport.maxTimeLandingQueue, airport.timeNeededLanding, airport.timeNeededTakeoff);

        while (Airport.currentTime < airport.totalSimulationTime)
        {
            airport.simulateAll();

        }


        //outputts
        System.out.println("The number of planes that took off in the simulated time");
        System.out.println(airport.runway.getTookoff().size());

        System.out.println("the number of planes that landed in the simulated time;");
        System.out.println(airport.runway.getLandedArray().size());

        System.out.println("the number of planes that crashed because they ran out of fuel before they could land");
        System.out.println(airport.runway.getCrashedArray().size());

        int avgTakeOffTime = 0;
        int takeOffArraySize = airport.runway.getTookoff().size();
        Airplane temp;
        while( !airport.runway.getTookoff().isEmpty() )
        {
            temp = airport.runway.getTookoff().remove(0);
            avgTakeOffTime = avgTakeOffTime + temp.getTimeInTakeoffQueue();
        }

        System.out.println("the average time that a plane spent in the takeoff queue");
        avgTakeOffTime = (avgTakeOffTime / takeOffArraySize);
        System.out.println(avgTakeOffTime);


        int avgTimeLandQueue = 0;
        int sizeInAirTimeArray = airport.runway.getInAirTimeArray().size();
        while( !airport.runway.getInAirTimeArray().isEmpty() )
        {
            temp = airport.runway.getInAirTimeArray().remove(0);
            avgTimeLandQueue = avgTimeLandQueue + temp.getTimeInLandingQueue();
        }
        System.out.println(" the average time that a plane spent in the landing queue");
        avgTimeLandQueue = (avgTimeLandQueue / sizeInAirTimeArray);
        System.out.println(avgTimeLandQueue);
        //outputs stop
    }

    public void simulateAll()
    {
        Random rand = new Random();

        if(rand.nextDouble() < probOfTakeoff)
        {
            runway.addTakeoffQueue(new Airplane());
        }
        if(rand.nextDouble() < probOfLanding)
        {
            runway.addLandingQueue(new Airplane());
        }
        
        runway.simulateRunway();
        currentTime++;
    }

    public void getInputs() 
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter time needed for landing:");
        timeNeededLanding = keyboard.nextInt();

        System.out.println("Enter time needed for takeoff:");
        timeNeededTakeoff = keyboard.nextInt();

        System.out.println("Enter probability of takeoff (between 0 and 1):");
        probOfTakeoff = keyboard.nextDouble();

        System.out.println("Enter probability of landing (between 0 and 1):");
        probOfLanding = keyboard.nextDouble();

        System.out.println("Enter maximum time for landing queue:");
        maxTimeLandingQueue = keyboard.nextInt();

        System.out.println("Enter total simulation time:");
        totalSimulationTime = keyboard.nextInt();
    }

    public static int getCurrentTime() 
    {
        return currentTime;
    }


}

