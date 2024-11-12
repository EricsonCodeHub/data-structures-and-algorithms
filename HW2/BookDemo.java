/**
   A driver that demonstrates the class PileOfBooks.
   
   @author Frank M. Carrano
   @version 5.0
 */
public class BookDemo
{
	public static void main(String[] args) 
	{
		System.out.println("Create an empty pile of books: ");
		PileInterface<String> myPile = new PileOfBooks<>();
		System.out.println("isEmpty() returns " + myPile.isEmpty() + "\n");

		System.out.println("Add to pile.\n");
		myPile.add("And Then There Were None");
		myPile.add("The Hobbit");
		myPile.add("The Lord of the Rings");
		myPile.add("The Da Vinci Code");
		myPile.add("The Catcher in the Rye");

		System.out.println("isEmpty() returns " + myPile.isEmpty() + "\n");

		System.out.println("Testing peek and pop:\n");
		while (!myPile.isEmpty())
		{
			String top = myPile.getTopBook();
			System.out.println(top + " is at the top of the pile.");

			top = myPile.remove();
			System.out.println(top + " is removed from the pile.\n");
		} // end while

		System.out.println("The pile should be empty: ");
		System.out.println("isEmpty() returns " + myPile.isEmpty() + "\n\n");

		System.out.println("Add to the pile.");
		myPile.add("Anne of Green Gables");
		myPile.add("The Purpose Driven Life");
		myPile.add("The Girl with the Dragon Tattoo");

		System.out.println("\nTesting clear:\n");
		myPile.clear();

		System.out.print("The pile should be empty: ");
		System.out.println("isEmpty() returns " + myPile.isEmpty() + "\n\n");

		System.out.println("myPile.getTopBook() returns " +  myPile.getTopBook());
		System.out.println("myPile.remove()  returns " +  myPile.remove() + "\n");
		System.out.println("\nDone.");
	}  // end main
}  // end Project4

/*
 Create an empty pile of books:
 isEmpty() returns true
 
 Add to pile.
 
 isEmpty() returns false
 
 Testing peek and pop:
 
 The Catcher in the Rye is at the top of the pile.
 The Catcher in the Rye is removed from the pile.
 
 The Da Vinci Code is at the top of the pile.
 The Da Vinci Code is removed from the pile.
 
 The Lord of the Rings is at the top of the pile.
 The Lord of the Rings is removed from the pile.
 
 The Hobbit is at the top of the pile.
 The Hobbit is removed from the pile.
 
 And Then There Were None is at the top of the pile.
 And Then There Were None is removed from the pile.
 
 The pile should be empty:
 isEmpty() returns true
 
 
 Add to the pile.
 
 Testing clear:
 
 The pile should be empty: isEmpty() returns true
 
 
 myPile.getTopBook() returns null
 myPile.remove()  returns null
 
 
 Done.

 */
