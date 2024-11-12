/**
 * The PileInterface interface is an outline for the PileOfBooks class
*/
public interface PileInterface<T>
{

    /**
     * adds a new type to the Pile 
     * 
     * @param element the type to be added to the Pile
    */
    public void add(T element);

    /**
     * removes a type from the Pile
     * 
     * @return T the type t that was removed from the Pile 
    */
    public T remove();

    /**
     * getTopBook gets the type from the top of the Pile
     * 
     * @return T the top type from the list
    */
    public T getTopBook();

    /**
     * isEmpty will return true if the Pile is empty
     * 
     * @return True if the list is empty
    */ 
    public boolean isEmpty();

    /**
     * clear clears the Pile
     * 
    */
    public void clear();
}