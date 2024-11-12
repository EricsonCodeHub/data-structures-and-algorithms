/**
   A class that represents a student that can be cloned.
   
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 4.0
*/
public class Student implements Cloneable
{
	private Name   fullName;
	private String id;      // Identification number

	public Student()
	{
		fullName = new Name();
		id = "";
	} // end default constructor

	public Student(Name studentName, String studentId)
	{
		fullName = studentName;
		id = studentId;
	} // end constructor

	public void setStudent(Name studentName, String studentId)
	{
		setName(studentName); // Or fullName = studentName;
		setId(studentId);     // Or id = studentId;
	} // end setStudent

	public void setName(Name studentName)
	{
		fullName = studentName;
	} // end setName

	public Name getName()
	{
		return fullName;
	} // end getName

	public void setId(String studentId)
	{
		id = studentId;
	} // end setId

	public String getId()
	{
		return id;
	} // end getId

	public String toString()
	{
		return id + " " + fullName.toString();
	} // end toString

	public boolean equals(Object other)
	{
      boolean result;

      if ((other == null) || (getClass() != other.getClass()))
         result = false;
      else
      {
         Student otherStudent = (Student)other;
         result = fullName.equals(otherStudent.fullName) && 
                  id.equals(otherStudent.id);
      } // end if

      return result;
	} // end equals

	public Object clone()
	{
      Student theCopy = null;

      try
      {
         theCopy = (Student)super.clone(); // Object can throw an exception
      }
      catch (CloneNotSupportedException e)
      {
         throw new Error(e.toString());
      }

      theCopy.fullName = (Name)fullName.clone();

      return theCopy;
	} // end clone
}  // end Student