// imports needed for printing to file
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
//imports needed for data storage
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Queue;
//imports needed to run simulation get user input
import java.util.Random;
import java.util.Scanner;

/**
 * This class simulates the operations of an airport, including managing landing and takeoff queues,
 * tracking airplane movements, and displaying simulation results.
*/
public class Airport
{
    //keeps track of time during simulation
    private static int currentTime = 0;

    // bellow are all user input variabels
    private int timeNeededLanding;
    private int timeNeededTakeoff;
    private double probOfTakeoff;
    private double probOfLanding;
    private int maxTimeLandingQueue;
    private int totalSimulationTime;

    //runway objest
    private Runway runway;

    // queue of planes waiting to land
    private Queue<Airplane> landingQueue = new LinkedList<>();
    // queue of planes waiting to take off
    private Queue<Airplane> takeoffQueue = new LinkedList<>();
    // array of all crashed planes
    private ArrayList<Airplane> arrayCrashed = new ArrayList<>();
    // array of all landed planes
    private ArrayList<Airplane> arraylanded = new ArrayList<>();
    // array of all the planes that took off
    private ArrayList<Airplane> arraytookoff = new ArrayList<>();
    // array used to keep track of in air tine 
    private ArrayList<Airplane> inAirTimeArray = new ArrayList<>();

    // debug file
    private static File file;


    /**
     * Main method to run the airport simulation
    */
    public static void main(String[] args) throws IOException
    {
        //creates debug file
        file = new File("debugOutput.txt");
        file.createNewFile();
        //creates airport object
        Airport airport = new Airport();
        //gets all user inputs needed for simulation
        airport.getInputs();
        // creates new runway
        airport.runway = new Runway();

        //loops until total simulation time is reached
        while (currentTime < airport.totalSimulationTime)
        {
            //calls method to simulate the airport
            airport.simulateAll();
        }

        //displayed the results of the simulation
        airport.displayResults();

    }


    /**
     * Writes a string to the debug output file
     * 
     * @param string The string to be written
    */
    private static void deBugprint(String string) 
    {
        try
        {
            //creates file writer
            FileWriter fw = new FileWriter(file, true);
            //writes string to new line
            fw.write(string + "\n");
            //closes file writer
            fw.close();
        }
        catch(IOException e)
        {
            //prints error if error occurs
            System.out.println("print error");
        }
    }

    
    /**
     * Simulates all actions in the airport for the current time
    */
    private void simulateAll()
    {
        //creates random object
        Random rand = new Random();
        //assigns random value to takeOffRand
        double takeOffRand = rand.nextDouble();
        //assigns random value to landingRand
        double landingRand = rand.nextDouble();


        // prints to the debug file
        deBugprint("minutes: " + Integer.toString(currentTime) + ",  landing prob: "+ 
        Double.toString(landingRand) );
        // prints to the debug file
        deBugprint("minutes: " + Integer.toString(currentTime) + ",  take off prob: "+ 
        Double.toString(takeOffRand) );
        

        // if takeOffRand < probOfTakeoff a plane gets added to the takeoff queue
        if( takeOffRand < probOfTakeoff)
        {
            takeoffQueue.add(new Airplane(currentTime));
            deBugprint("plane added to take off queue");
        }
        // if landingRand < probOfLanding a plane gets added to the landingQueue
        if(landingRand < probOfLanding)
        {
            landingQueue.add(new Airplane(currentTime));
            deBugprint("plane added to landing queue");
        }
        
        // if the runway is not clear do the if body
        if(!runway.isRunwayClear(currentTime))
        {
            //prints to debug file
            deBugprint("minutes: " + Integer.toString(currentTime) + ", runway is not clear");
        }
        // the first else if checks to see if planes are in the landing queue
        else if(!landingQueue.isEmpty())
        {
            // prints to debug file
            deBugprint("landing queue is not empty");
           
            /*
                if crashed planes should stay in the queue till the get to the front of the queue
                if(plane is crashed)
                {
                    add to cashed array and in air time
                }
                else()
                {
                    add to landed array
                }
            */


            // this for loop removes crashed planes from landing queue until there is a plan that has crashed or queue is empty
            // this loop makes sure there aren't any crashed planes still in the queue at the end of the simulation
            for (Airplane temp = landingQueue.peek(); temp != null &&  maxTimeLandingQueue < (currentTime - temp.getArrivalTime()); )
            {
                // sets land time for temp plane 
                temp.setLandTime(currentTime);
                // adds plane to crash array
                arrayCrashed.add(temp);
                //prints to debug file 
                deBugprint("minutes: " + Integer.toString(currentTime) + " plane " + temp +  " has crahed");
                //printd to debug file
                deBugprint("plane "+ temp + " has crashed and spent " + temp.getTimeInLandingQueue() + " minutes in the landing queue");
                // remove from landingQueue
                landingQueue.remove();
                //prints to debug file
                deBugprint("landing is dequeued");
                //peeks next plane in landing queue
                temp = landingQueue.peek();

            }
            
            //checks to see if there is a plane in the ques after crashed planes have been removed
            if(!landingQueue.isEmpty()) 
            {
                // removed plane for queue
                Airplane temp = landingQueue.remove();
                //prints to debug file
                deBugprint("landing is dequeued");
                // sets planes land time
                temp.setLandTime(currentTime);
                //adds plane to arraylanded
                arraylanded.add(temp);
                //prints to debug file
                deBugprint("minutes: " + Integer.toString(currentTime) + " plane " + temp +  " has lannded");
                //prints to debug file
                deBugprint("plane "+ temp + " spent " + temp.getTimeInLandingQueue() + "minutes in the landing queue");
                //adds plan to array to calculate in air time wait time 
                inAirTimeArray.add(temp); 
                //sets runway clear time
                runway.setClearTime(currentTime + timeNeededLanding);
            }
            
        }
        // checks to see if the take off queue is empty
        else if(!takeoffQueue.isEmpty())
        {
            //sets temp to the last plane in queue
            Airplane temp = takeoffQueue.peek();
            //sets temp take off time
            temp.setTakeOffTime(currentTime);
            //adds temp to the took off array
            arraytookoff.add(temp);
            //print to debug file
            deBugprint("minutes: " + Integer.toString(currentTime) + " plane " + temp +  " taken off");
            //print to debug file
            deBugprint("plane "+ temp + " spent " + temp.getTimeInTakeoffQueue() + "minutes in the takeoff queue");
            //removed plane from  takeoffQueue
            takeoffQueue.remove();
            // prints to debug file
            deBugprint("takeoff is dequeued");
            //sets runway clear time
            runway.setClearTime(currentTime + timeNeededTakeoff);
        }
        else;
        //increments currentTime
        currentTime++;
    }

    /**
     * Reads inputs from the user
    */
    private void getInputs() 
    {
        //creates scanner object
        Scanner keyboard = new Scanner(System.in);

        // gets users input for timeNeededLanding
        System.out.println("Enter time needed for landing:");
        timeNeededLanding = keyboard.nextInt();

        // gets users input for  timeNeededTakeoff
        System.out.println("Enter time needed for takeoff:");
        timeNeededTakeoff = keyboard.nextInt();

        // gets users input for probOfTakeoff
        System.out.println("Enter probability of takeoff (between 0 and 1):");
        probOfTakeoff = keyboard.nextDouble();

        // gets users input for probOfLanding
        System.out.println("Enter probability of landing (between 0 and 1):");
        probOfLanding = keyboard.nextDouble();

        // gets users input for maxTimeLandingQueue
        System.out.println("Enter maximum time for landing queue:");
        maxTimeLandingQueue = keyboard.nextInt();

        // gets users input for totalSimulationTime
        System.out.println("Enter total simulation time:");
        totalSimulationTime = keyboard.nextInt();
    }

    /**
     * Displays simulation results
    */
    private void displayResults()
    {
        //variables to display results
        int avgTakeOffTime; 
        int takeOffArraySize;
        int avgTimeLandQueue;
        int sizeInAirTimeArray;
        //used to hold current airplane
        Airplane temp;

        //prints the amount of planes that took off 
        System.out.println("The number of planes that took off in the simulated time");
        System.out.println(arraytookoff.size());

        //prints the amount of planes that landed
        System.out.println("the number of planes that landed in the simulated time;");
        System.out.println(arraylanded.size());

        //prints the amount of planes that crashed
        System.out.println("the number of planes that crashed because they ran out of fuel before they could land");
        System.out.println(arrayCrashed.size());

        
        
        /*
            the loop below adds all remaining plans waiting to land to take off to the
            array took off to the calculate the average wait time
        */
        

        avgTakeOffTime = 0;
        takeOffArraySize = arraytookoff.size();
        /*
            The while loop is used to accumulate time planes
            spent in the take off array
        */
        while( !arraytookoff.isEmpty() )
        {
            temp = arraytookoff.remove(0);
            avgTakeOffTime = avgTakeOffTime + temp.getTimeInTakeoffQueue();

        }

        
        System.out.println("the average time that a plane spent in the takeoff queue"); //-- dont include planes left in takeoff queue per jim
        try
        {
            avgTakeOffTime = (avgTakeOffTime / takeOffArraySize);
            System.out.println(avgTakeOffTime);
        }
        catch (ArithmeticException e)
        {
            /*
                it is very unlikely this code will be executed,
                it could execute when the simulation time is very low
                and the chance of a plane taking off is low


                this is for divide by zero error
            */
            System.out.println("0 planes took off during the simulation " ); 
        }
        


        avgTimeLandQueue = 0;
        sizeInAirTimeArray = inAirTimeArray.size();
        // loop used to accumulate all time landed planes spent in the air
        while( !inAirTimeArray.isEmpty() )
        {
            temp = inAirTimeArray.remove(0);
            avgTimeLandQueue = avgTimeLandQueue + temp.getTimeInLandingQueue();
        }
        System.out.println(" the average time that a plane spent in the landing queue");
        try
        {
            avgTimeLandQueue = (avgTimeLandQueue / sizeInAirTimeArray);
            System.out.println(avgTimeLandQueue);
        }
        catch (ArithmeticException e)
        {
            /*
                it is very unlikely this code will executed,
                it could execute if the runtime is very low and the
                chance of a plane landing is very low


                this is for divide by zero error
            */
            System.out.println("0 planes landed safely during the simulation" );
        }
    }
}

