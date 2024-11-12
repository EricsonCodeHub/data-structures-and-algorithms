/**
   InvalidIDExceptions are thrown by Payroll
   objects when an invalid ID number is given
   for the Payroll Class Exceptions programming 
   challenge.
*/

public class InvalidIDException extends Exception
{
   /**
      No-arg constructor
   */
      
   public InvalidIDException()
   {
      super("Invalid ID number");
   }

   /**
      This constructor reports the ID number
      that caused the exception to be thrown.
      @param idNum The invalid ID number.
   */
      
   public InvalidIDException(int idNum)
   {
      super("Invalid ID number: " + idNum);
   }
}