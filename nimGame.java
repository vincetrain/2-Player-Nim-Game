
/*
*   Date: 17/03/2021
*   Name: Vincent Tran
*   Teacher: Mr. Ho
*   Description: Program that emulates Nim game, with built in computer to play against.
*
*/

// Import Statements

import java.util.Scanner;
import java.nio.file.attribute.UserPrincipalLookupService;
import java.util.Random;

public class nimGame {

// Global Variables

    // Utility

    public static Scanner userInput = new Scanner(System.in);   // Initializes scanner variable to take user input

    public static Random rand = new Random();   // Initializes a random variable to generate a random number

    // Data

    public static String playerName1 = ""; // Initializes string variable that defines player 1's name. Used in endGame() and main()

    public static String playerName2 = "";  // Initializes string variable that defines player 2's name. Used in endGame() and main()

    public static Integer pile1 = rand.nextInt(10)+1;   // Initializes pile1 int variable to be a random integer between 1 and 10. Used in all methods

    public static Integer pile2 = rand.nextInt(10)+1;   // Initializes pile2 int variable to be a random integer between 1 and 10. Used in all methods
        
    public static Integer pile3 = rand.nextInt(10)+1;   // Initializes pile3 int variable to be a random integer between 1 and 10. Used in all methods

    public static Integer counter = rand.nextInt(2)+1;    // Initializes counter int variable to keep track of turn. Used in main()

    public static String userPile = ""; // Initializes string variable defining the user's picked pile. Used in main() and pileRemove()

/**
 * Prints the current values for each pile.
 * <p>
 * Reduces line count and file size by removing unnecessary dupes of the following lines.
 */

    public static void printPiles() {

                    // Prints a display for counter A 

                    System.out.print("A: ");
                    for (int i = 0; i < pile1; i++) {   // For loop that prints a symbol according to the amount of items in pileA
        
                        System.out.print("*");
                    }
                    System.out.println();   // Prints an empty line to add spacing in-between the displays
                    
                    // Prints a display for counter B
                    
                    System.out.print("B: ");
                    for (int j = 0; j < pile2; j++) {   // For loop that prints a symbol according to the amount of items in pileB
        
                        System.out.print("*");
                    }
                    System.out.println();   // Prints an empty line to add spacing in-between the displays
                    
                    // Prints a display for counter C
                    
                    System.out.print("C: ");
                    for (int k = 0; k < pile3; k++) {   // For loop that prints a symbol according to the amount of items in pileC
        
                        System.out.print("*");
                    }
                    System.out.println();   // Prints an empty line to add spacing in-between the displays

    }

/**
 * Method used for prompting the user for the desired amount they want to take.
 * <p>
 * Method is implemented here because it is the least messiest (and most optimized?) way to implement a re-input.
 * Doesn't require me nesting another while loop and introducing a new variable.
 */

    public static void pileRemove() {

        userInput = new Scanner(System.in); // Calls for userInput to recieve a new input
        System.out.println("How many items do you wish to take from pile " + userPile + "?");   // Prompts the user to input their desired take
        Integer userTake = userInput.nextInt(); // Sets userTake variable to the next integer entered
        
        if (userTake > 0 && userTake <= 10) {   // Checks if desired take amount is within range of 1-10
            if (userTake <= pile1 && userPile.equals("A")) {    // Reads userPile and checks if not empty
                pile1-=userTake;    // Subtracts pile accordingly
            }
            else if (userTake <= pile2 && userPile.equals("B")) {   // Reads userPile and checks if not empty
                pile2-=userTake;    // Subtracts pile accordingly
            }
            else if (userTake <= pile3 && userPile.equals("C")) {   // Reads userPile and checks if not empty
                pile3-=userTake;    // Subtracts pile accordingly
            }
            else {
                System.out.println("Pile " + userPile + " does not have that many. Please try again");  // Tells user there are not enough items in pile
                printPiles();
                pileRemove();   // Re-input by executing method again
            }

        }

        else {
            System.out.println("You have entered an invalid input. Please enter an integer in-between 1 and 10");   // Tells user input is invalid
            printPiles();
            pileRemove(); // Re-input by executing method again
        }

    }

/**
 * Launches the application.
 * 
 * @param args - Application startup arguments
 */

    public static void main(String args[]) {

        System.out.println("Welcome to Nim."); // Welcomes the user to Nim

        // Prompts player 1 to input their name.

        userInput = new Scanner(System.in); // Calling Scanner variable for user input
        System.out.println("Please enter Player 1's name."); // Prints a prompt for user to input 
        playerName1 = userInput.nextLine(); // Sets playerName1 variable to what the user has inputted.

        // Prompts player 2 to input their name.

        userInput = new Scanner(System.in); // Calling Scanner variable for user input
        System.out.println("Please enter Player 2's name."); // Prints a prompt for user to input 
        playerName2 = userInput.nextLine(); // Sets playerName2 variable to what the user has inputted.

        if (playerName1.equals(playerName2)) {  // Prevents duplicate player names
            playerName2 += " (1)";
        }

        // Gameplay while loop
        
        while (pile1+pile2+pile3 >= 2) {   // Loops ONLY if there are 2 items on the playing field
            counter++;  // Adds one to counter to change turn
         
            printPiles();

            // Uses counter and uses modulus operator to determine who's turn it is //

            if (counter%2==1) { // Checks if counter is odd

                userInput = new Scanner(System.in);
                System.out.println(playerName1 + ", which pile do you wish to pick from?"); // Prompts user to input a desired pile
                userPile = userInput.nextLine();

                }

            else {

                userInput = new Scanner(System.in); // Calls scanner to read user input
                System.out.println(playerName2 + ", which pile do you wish to pick from?"); // Prompts user to input a desired pile
                userPile = userInput.nextLine(); // sets userInput to userPile

            }
        
                userPile = userPile.toUpperCase();  // Converts userPile to uppercase to resolve any case issues
        
                    
            if (userPile.equals("A") && pile1 > 0) {    // Checks if pile is not empty
                pileRemove();   // Redirects to new method for removing from pile
            }

            else if (userPile.equals("B") && pile2 > 0) {    // Checks if pile is not empty
                pileRemove();   // Redirects to new method for removing from pile
            }

            else if (userPile.equals("C") && pile3 > 0) {    // Checks if pile is not empty
                pileRemove();   // Redirects to new method for removing from pile
            }
    
            else {
                System.out.println("Pile '" + userPile + "'' does not exist or is empty. Please try again.");
                counter--;  // Removes 1 from counter to restart player turn and trigger re-input
            }

        }

        if (pile1+pile2+pile3 == 0) {   // Prevents bug where user can win by taking the whole last remaining pile
            counter--;
        } 

        if (counter%2==0) { // Checks who's turn was the last turn
            System.out.println(playerName1 + " has taken the final item, therefore they have lost the game.");
            System.out.println("Congratulations, " + playerName2 + "!");
        }

        else {
            System.out.println(playerName2 + " has taken the final item, therefore they have lost the game.");
            System.out.println("Congratulations, " + playerName1 + "!");
        }

    }
}