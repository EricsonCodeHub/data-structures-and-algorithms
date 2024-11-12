/**
   This program demonstrates a solution to the
   Payroll Class Exceptions programming challenge.
*/

public class AAA
{
   public static void main(String[] args)
   {
      Payroll worker;      // Reference variable
            
      // Create a Payroll object with an empty string for the name.
      try
      {
         worker = new Payroll("", 123);
      }
      catch (Exception e)
      {
         System.out.println("Error - " + e.getMessage());
      }

      // Create a Payroll object with an invalid ID number.
      try
      {
         worker = new Payroll("Bill Jenkins", -99);
      }
      catch (Exception e)
      {
         System.out.println("Error - " + e.getMessage());
      }
      
      // Try to set an invalid hourly pay rate.
      try
      {
         worker = new Payroll("Bill Jenkins", 123);
         worker.setPayRate(150.0);
      }
      catch(InvalidHourlyRateException e)
      {
         System.out.println("Error - " + e.getMessage());
      }
      catch (Exception e)
      {
            System.out.println("Error - " + e.getMessage());
      }
      
      // Try to set an invalid number of hours worked.
      try
      {
         worker = new Payroll("Bill Jenkins", 123);
         worker.setHoursWorked(-10);
      }
      catch(InvalidHoursException e)
      {
            System.out.println("Error - " + e.getMessage());
      }
      catch (Exception e)
      {
            System.out.println("Error - " + e.getMessage());
      }

   }
}

