// import statement for Scanner
import java.util.Scanner;
/**
	The PalindromeDemo class determines if the string entered
	by the user is a Palindrome.
*/
public class PalindromeDemo
{
	public static void main(String[] args)
    {
        //Creates new New scanner keyboard
        Scanner keyboard = new Scanner(System.in);
        // used for users input
        String userInput;
        
        // directs user
        System.out.println("This program will determine if the entered"+
							" string is a Palindrome");
        System.out.println("enter the string you want to check for"+
										" Palindrome:");
        //takes users input
        userInput = keyboard.nextLine();

        /*
            checks if the user's input is a PalindromeDemo and displays
            the results of the user.
        */
        if(palindromeLogic(userInput))
        {
            System.out.println("\"" + userInput + "\" is a Palindrome");
        }
        else
        {
            System.out.println("\"" + userInput + "\" is not Palindrome");
        }
    }


    /*
        the Palindrome Logic method returns true if the
        string entered is a Palindrome

        the param is user String input
    */
    private static boolean palindromeLogic(String input)
    {
        //creates linked stack the node data type is Character
        LinkedStack<Character> stack = new LinkedStack<Character>();

        // creates isPalindrome and sets default to true
        boolean isPalindrome = true;

        //input string gets parsed to all lower case
        input = input.toLowerCase();

		/*
			(input.length()/2) if length is an odd number
			that is ok, this in int math
		*/
        for(int i = 0, j = input.length() -1; i < (input.length()/2); i++, j--)
        {
            //pushes char from front of input string
            stack.push(input.charAt(i));
            //pushes char from back of input string
            stack.push(input.charAt(j));
        }

        //loop terminates once stake is empty
        while(! stack.isEmpty())
        {
            /*
                this if statement compares the 
                top two stack nodes data and
                also removes them

                stack.pop() != stack.pop() will
                never throw EmptyStackException()
				because the stack will never have a 
				odd number of nodes
            */
            if( stack.pop() != stack.pop())
            {
                // if the nodes don't match boolean is set to false 
                isPalindrome = false;
            }
        }
		
        //boolean is returned
        return isPalindrome;
    }
}