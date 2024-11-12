/**    A driver that demonstrates the class Student and the interface StudentInterface.      @author Frank M. Carrano   @author Timothy M. Henry   @version 4.0*/public class Driver{	public static void main(String[] args) 	{		System.out.println("Test Student");		StudentInterface bob = new Student();		bob.setStudent(new Name("Bob", "Bean"), "555-55");		System.out.println(bob);				Student friend = new Student(new Name("Kris", "Smythe"), "111-22");		System.out.println(friend);			StudentInterface kyle = new Student();		kyle.setName(new Name("Kyle", "Skye"));		kyle.setId("345-01");		System.out.println(kyle);		System.out.println(kyle.getId() + " " + kyle.getName());      		System.out.println("\nDone!");	} // end main} // end Driver/* Test Student 555-55 Bob Bean 111-22 Kris Smythe 345-01 Kyle Skye 345-01 Kyle Skye  Done!*/