
import java.util.ArrayList;
/**
 * The bookPile class manages a pile of Types
 * 
*/
public class PileOfBooks<T> implements PileInterface<T> 
{
    private ArrayList<T> bookPile = new ArrayList<T>();
	
	/**
	 * Constructor makes an instance of the class,
	 * the bookPile is empty.
	*/
    public PileOfBooks()
    {
        //Constructor body is empty
    }

    /**
     * adds a new type to the Pile 
     * 
     * @param element the type to be added to the Pile
    */
    public void add(T element)
    {
        //adds element to the bookPile
        bookPile.add(element);
    }

    /**
     * removes a type from the Pile
     * 
     * @return T the type t that was removed from the Pile 
    */
    public T remove()
    {
		if(!bookPile.isEmpty())
		{   
            //temp is set to the last index of the bookPile
			T temp = bookPile.get(bookPile.size() -1);
            //the last type of the bookPile is removed
			bookPile.remove(bookPile.size() -1);
            //temp is returned
			return temp;
		}
		else
		{
            //returns null if bookPile is empty
			return null;
		}
    }

    /**
     * getTopBook gets the type from the top of the Pile
     * 
     * @return T the top type from the list
    */
    public T getTopBook()
    {
		if(!bookPile.isEmpty())
		{
            //returns type at last index of bookPile
			return bookPile.get(bookPile.size() -1);
		}
		else
		{
            //returns null if bookPile is empty
			return null;
		}

    }

    /**
     * isEmpty will return true if the Pile is empty
     * 
     * @return True if the list is empty
    */
    public boolean isEmpty()
    {
        //returns true if book pile is empty 
        return bookPile.isEmpty();
    }

    /**
     * clear clears the Pile
     * 
    */
    public void clear()
    {
        //clears  bookPile arrayList
        bookPile.clear();
    }

}