/**
   InvalidNameExceptions are thrown by Payroll
   objects when an invalid name is given for 
   the Payroll Class Exceptions programming 
   challenge.
*/

public class InvalidNameException extends Exception
{
   /**
      No-arg constructor
   */
      
   public InvalidNameException()
   {
      super("Invalid name");
   }
}