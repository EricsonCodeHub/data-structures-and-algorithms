import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

public class Airport
{
    //timer
    private static int currentTime = 0;

    //inputs
    private int timeNeededLanding;
    private int timeNeededTakeoff;
    private double probOfTakeoff;
    private double probOfLanding;
    private int maxTimeLandingQueue;
    private int totalSimulationTime;
    private Runway runway;

    //data storage
    private Queue<Airplane> landingQueue = new LinkedList<>();
    private Queue<Airplane> takeoffQueue = new LinkedList<>();
    private ArrayList<Airplane> arrayCrashed = new ArrayList<>();
    private ArrayList<Airplane> arraylanded = new ArrayList<>();
    private ArrayList<Airplane> arraytookoff = new ArrayList<>();
    private ArrayList<Airplane> inAirTimeArray = new ArrayList<>();

    private static File file;

    public static void main(String[] args) throws IOException
    {
        file = new File("debugOutput.txt");
        file.createNewFile();
        Airport airport = new Airport();
        airport.getInputs();
        airport.runway = new Runway();

        while (currentTime < airport.totalSimulationTime)
        {
            airport.simulateAll();
        }

        airport.displayResults(airport);

    }

    private static void deBugprint(String string) 
    {
        try
        {
            FileWriter fw = new FileWriter(file, true);
            fw.write(string + "\n");
            fw.close();
        }
        catch(IOException e)
        {

            System.out.println("print error");
        }
    }

    

    public void simulateAll()
    {
        Random rand = new Random();
        double takeOffRand = rand.nextDouble();
        double landingRand = rand.nextDouble();


        // prints to file
        deBugprint("minutes: " + Integer.toString(currentTime) + ",  landing prob: "+ 
        Double.toString(landingRand) );

        deBugprint("minutes: " + Integer.toString(currentTime) + ",  take off prob: "+ 
        Double.toString(takeOffRand) );
        


        if( takeOffRand < probOfTakeoff)
        {
            takeoffQueue.add(new Airplane(currentTime));
        }
        if(landingRand < probOfLanding)
        {
            landingQueue.add(new Airplane(currentTime));
        }
        
        if(!runway.isRunwayClear(currentTime))
        {
            deBugprint("minutes: " + Integer.toString(currentTime) + ", runway is not clear");
        }
        else if(!landingQueue.isEmpty())
        {
            deBugprint("landing queue is not empty");
            // remove loop to fix you time in que problem -----------------------------------------------------------------------------------------------------
            /*
                fix like this
                if(plane is crashed)
                {
                    add to cashed array and inair time
                }
                else()
                {
                    add to landed array
                }
            */
            for (Airplane temp = landingQueue.peek(); temp != null &&  maxTimeLandingQueue < (currentTime - temp.getArrivalTime()); )
            {
                temp.setLandTime(currentTime);
                arrayCrashed.add(temp);
                deBugprint("minutes: " + Integer.toString(currentTime) + " plane " + temp +  " has crahed");

                inAirTimeArray.add(temp);
                
                landingQueue.remove();
                deBugprint("landing is dequeued");

                temp = landingQueue.peek();

            }
            if(!landingQueue.isEmpty())
            {
                Airplane temp = landingQueue.remove();
                deBugprint("landing is dequeued");

                temp.setLandTime(currentTime);
                arraylanded.add(temp);
                deBugprint("minutes: " + Integer.toString(currentTime) + " plane " + temp +  " has lannded");

                inAirTimeArray.add(temp); 
                

                runway.setClearTime(currentTime + timeNeededLanding);
            }
            
        }
        else if(!takeoffQueue.isEmpty())
        {
            Airplane temp = takeoffQueue.peek();
            temp.setTakeOffTime(currentTime);
            arraytookoff.add(temp);
            deBugprint("minutes: " + Integer.toString(currentTime) + " plane " + temp +  " taken off");
            takeoffQueue.remove();
            deBugprint("takeoff is dequeued");

            runway.setClearTime(currentTime + timeNeededTakeoff);
        }
        else;
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

    private void displayResults(Airport airport)
    {
        System.out.println("The number of planes that took off in the simulated time");
        System.out.println(arraytookoff.size());

        System.out.println("the number of planes that landed in the simulated time;");
        System.out.println(arraylanded.size());

        System.out.println("the number of planes that crashed because they ran out of fuel before they could land");
        System.out.println(arrayCrashed.size());

        int avgTakeOffTime = 0;
        int takeOffArraySize = arraytookoff.size();
        Airplane temp;
        while( !arraytookoff.isEmpty() )
        {
            temp = arraytookoff.remove(0);
            avgTakeOffTime = avgTakeOffTime + temp.getTimeInTakeoffQueue();
        }

        System.out.println("the average time that a plane spent in the takeoff queue");
        avgTakeOffTime = (avgTakeOffTime / takeOffArraySize);
        System.out.println(avgTakeOffTime);


        int avgTimeLandQueue = 0;
        int sizeInAirTimeArray = inAirTimeArray.size();
        while( !inAirTimeArray.isEmpty() )
        {
            temp = inAirTimeArray.remove(0);
            avgTimeLandQueue = avgTimeLandQueue + temp.getTimeInLandingQueue();
        }
        System.out.println(" the average time that a plane spent in the landing queue");   //only planes thta land success
        avgTimeLandQueue = (avgTimeLandQueue / sizeInAirTimeArray);
        System.out.println(avgTimeLandQueue);
        //outputs stop
    }



}

