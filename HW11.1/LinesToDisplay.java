import java.util.*;
import java.awt.*;

/**
 * A class that will be used to display the lines of text that are corrected.  Since it may be accessed by user interface threads
 * (paint) and the application thread, make all methods synchronized
 * 
 * @author Charles Hoot
 * @version 4.0
 */

    
public class LinesToDisplay
{
    
    public static final int LINES = 10;     // Display 10 lines
    private ArrayList<Wordlet> lines[];
    private int currentLine;
    

    /**
     * Constructor for objects of class LinesToDisplay
     */

    // add unchecked ignore
    @SuppressWarnings("unchecked")
    public LinesToDisplay()
    {
        //ADD CODE FOR THE CONSTRUCTOR

        // step 8 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        //Arraylist abject is created and the type cast and assigned to lines
        lines = (ArrayList<Wordlet>[]) new ArrayList[LINES + 1];
        //lines index0 gets assigned array list
        lines[0] = new ArrayList<>();
        //current line set to 0
        currentLine = 0;

    }
    
    /**
     * Add a new wordlet to the current line.
     * 
     * @param  w   A wordlet. 
     */ 
    public synchronized void addWordlet(Wordlet w)
    {
        //ADD CODE HERE TO ADD A WORDLET TO THE CURRENT LINE
        // step 9>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        // wordlet gets added to arrraylisy at currentline 
        lines[currentLine].add(w);
    }
    
    
    /**
     * Go to the next line, if the number of lines has exceeded LINES, shift them all
     * up by one
     *
     */
    public synchronized void nextLine()
    {
        //ADD CODE TO HANDLE THE NEXT LINE

        // step 10 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        // checks to see if currentr line is less then 10
        if(currentLine < LINES)
        {
            currentLine++;
        }
        else
        {
            //shifts line index by 1
            for(int i = 0; i < LINES; i++)
            {
                lines[i] = lines[i + 1];
            }
        }

        // creates new line
        lines[currentLine] = new ArrayList<Wordlet>();
    }    
    
    public static final int LINE_HEIGHT = 15;
    
    /**
     * Draw a representation of the lines display at the given location.
     * 
     * @param   g  The graphics context to draw on.   
     * @param   topLeftX  The x position of the left edge of the top of our drawing.
     * @param   topY  The y position of the top.
     * 
     */
    synchronized public void drawOn(Graphics g, int topLeftX, int topY)
    {
        
        // Draw each line
        FontMetrics fm = g.getFontMetrics();
        for(int i=0; i<currentLine; i++)
        {
            Iterator<Wordlet> iter = lines[i].iterator();
            int stringsStartY = topY + LINE_HEIGHT;
            int xPos = topLeftX;
    
            for(int j = 0; j<lines[i].size(); j++)
            {
                Wordlet w = iter.next();
                String toDraw = w.getWord();
                if( w.isSpelledCorrectly() )
                    g.setColor(Color.black);
                else
                    g.setColor(Color.red);
                
                g.drawString(toDraw, xPos, stringsStartY+i*LINE_HEIGHT);
                
                // get the width to add to the next line
                xPos += fm.stringWidth(toDraw);
            }  // end for over wordlets
        }  // end for over lines

    }
    
    /**
     * Provide a string that is composed from all the lines in the display.
     * @return A string concatenating all the strings held by the class.
     */
    public String toString()
    {
    String result = "The lines are:\n";
    for(int i=0; i<currentLine; i++)
        {
            Iterator<Wordlet> iter = lines[i].iterator();
    
            for(int j = 0; j<lines[i].size(); j++)
            {
                Wordlet w =  iter.next();
                result = result + w.getWord();
                
            }  // end for over wordlets
            result = result + "\n";
        }  // end for over lines
    return result;
    }
}
