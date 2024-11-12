/**
    InvalidHourlyRateExceptions are thrown by Payroll
    objects when an invalid hourly pay rate is given 
    for the Payroll Class Exceptions programming challenge.
*/

public class InvalidHourlyRateException extends Exception
{
    /**
        No-arg constructor
    */
        
    public InvalidHourlyRateException()
    {
        super("Invalid hourly pay rate");
    }

    /**
        This constructor reports the pay rate that
        caused the exception to be thrown.
        @param rate The invalid pay rate.
    */
    
    public InvalidHourlyRateException(double rate)
    {
        super("Invalid hourly pay rate: " + rate);
    }
}