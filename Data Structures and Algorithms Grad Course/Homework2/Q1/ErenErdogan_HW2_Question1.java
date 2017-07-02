// Eren Erdogan
// Data Structures and Algorithms
// Homework 2
// Question 1

import java.util.Scanner;

public class ErenErdogan_HW2_Question1 {

    public static void main(String[] args) {

        boolean cont = true;

        while(cont) {

            Scanner inpt = new Scanner(System.in);

            System.out.print("Please enter the prefix you'd like to test\n>>> ");

            String s = inpt.next();

            int n = findTheEndOfPrefix(s, 0);

            if (n == -1)
                System.out.println("\nThe expression " + s + " is not a valid prefix expression at all!");

            else if (n == s.length() - 1)
                System.out.println("\nThe expression " + s + " is a fully valid prefix expression! It's legal end is it's last term at index " + n);

            else {

                System.out.println("\nThe expression " + s + " is partially valid and it's legal end is at index " + n);
            }

            System.out.println("\nDo you want to do another test? Enter 'y' for yes and 'n' for no");

            String c = inpt.next();

            if(c.toUpperCase().equals("N")) {
                System.out.println("Program exiting. Good Bye!");
                cont = false;
            }



        }

    }


    // Begin endPre method
    public static int findTheEndOfPrefix(String s, int pos){

        // Initialize variable to be used for first end
        int firstEnd;

        // Check to make sure position not out of bounds
        if( pos >= s.length() )
            return -1;

        // Check to make sure the string passed in is not empty
        if (s.length() == 0)
            return -1;

        // Base Case
        // If current position is a letter
        // return position
        if( Character.isLetter(s.charAt(pos)) )
            return pos;

        // If not a letter, check if an operator
        else if( (s.charAt(pos) == '+') || (s.charAt(pos) == '-') || (s.charAt(pos) == '*') || (s.charAt(pos) == '/') ) {
            // current char is a operator
            firstEnd = findTheEndOfPrefix(s, pos + 1);

            if (firstEnd > -1)
                return findTheEndOfPrefix(s, firstEnd + 1);

            else
                return -1;
        }

        // Not an operator or letter
        else
            return -1;

    }

}
