
/**
 * This class represents a patient's medical record, including details like patient ID, visit date,
 * reason for the visit, and treatment received.
 */
public class PatientRecord
{

    // Fields to store patient data
    private int patientID;
    private int month;
    private int day;
    private int year;
    private String visitReason;
    private String treatment;

    /**
     * Constructs a new PatientRecord object.
     *
     * @param patientID The ID of the patient.
     * @param month the month of the visit.
     * @param day the day of the visit.
     * @param year the year of the visit.
     * @param visitReason The reason for the patient's visit.
     * @param treatment the patients treatment.
     */
    PatientRecord(int patientID, int month, int day, int year, String visitReason, String treatment) throws BadVisitDateException
    {   
        // validfates the the pietents birthday, throws exception if there is invalide data
        if (month < 1 || month > 12)
        {
            throw new BadVisitDateException("Month not in range 1-12");
        }
        if (day < 1 || day > 31)
        {
            throw new BadVisitDateException("Day not in range 1-31");
        }
        if (year <= 1900)
        {
            throw new BadVisitDateException("Year not greater than 1900");
        }

        // assigns the argument to the class fields
        this.patientID = patientID;
        this.month = month;
        this.day = day;
        this.year = year;
        this.visitReason = visitReason;
        this.treatment = treatment;
    }

    // overrides method from object class
    @Override
    /**
     * Generates a hash code value for the PatientRecord based on the patientID and birthdate fieldsS.
     *
     * @return The hash code value for patient birthdata and ID.
     */
    public int hashCode()
    {

        String date = (month + "" + day + year);
        int result = patientID * date.hashCode();

        // insures the result will not be negitive 
        if(result < 0)
        {
            result = result + Integer.MAX_VALUE;
        }

        // returns the result 
        return result;

    }

    // overrides method from object class
    @Override
    /**
     * Returns a string representation of the PatientRecord object.
     *
     * @return A string containing patient ID visit date  reason for visit and treatment information.
     */
    public String toString()
    {
        return ("Patient:" +  patientID + " [" + month + "/" + day + "/" + year + "] Complaint: " + visitReason + " Prescribed: " + treatment);
    }
    
}





