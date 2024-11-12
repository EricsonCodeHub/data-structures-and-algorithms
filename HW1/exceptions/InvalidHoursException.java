/**
   InvalidHoursExceptions are thrown by Payroll
   objects when an invalid number of hours is given
   for the Payroll Class Exceptions programming challenge.
*/

public class InvalidHoursException extends Exception
{
   /**
      No-arg constructor
   */
      
   public InvalidHoursException()
   {
      super("Invalid number of hours");
   }

   /**
      This constructor reports the number of 
      hours that caused the exception to be
      thrown.
      @param hours The invalid number of hours.
   */
   
   public InvalidHoursException(double hours)
   {
      super("Invalid number of hours: " + hours);
   }
}