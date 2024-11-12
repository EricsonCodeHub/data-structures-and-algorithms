/**
 * This class represents an exception that is thrown when an invalid date is entered
 */
public class BadVisitDateException extends Exception
{
    /**
     * Constructs a new BadVisitDateExceptione.
     *
     * @param e The error message that describes the reason for the exception.
     */
    public BadVisitDateException(String e)
    {
        // Calls the constructor of the super class Exception
        super(e);
    }
}