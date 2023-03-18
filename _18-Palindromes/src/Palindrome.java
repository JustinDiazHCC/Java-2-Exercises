/**
 * Palindrome.java
 * Determines whether a string entered by the user is a palindrome
 *
 * @author Justin Diaz
 * @version 1.0
 */

import java.util.Scanner;

public class Palindrome {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in); // Scanner object

        // user entry prompt
        System.out.println("Enter a sentence:");
        if (palindrome(input.nextLine())) { // palindrome method call
            System.out.println("This entry is a palindrome!");
        } else {
            System.out.println("This entry is not a palindrome.");
        }

        input.close(); // close input stream
    } // end method main

    public static boolean palindrome(String entry) {
        // filter out
        String filteredEntry = entry.replaceAll(" ", "");
        filteredEntry.toLowerCase();
        filteredEntry.replaceAll("[^a-z]", "");

        if (filteredEntry.length() == 1 || filteredEntry.length() == 0) {
            // is palindrome if there are only one or zero characters (remaining, or to begin with)
            return true;
        } else if (filteredEntry.charAt(0) == filteredEntry.charAt(filteredEntry.length() - 1)) {
            // recursive call to method if first and last character match
            // calls method again, removing first and last character for next iteration
            return palindrome(filteredEntry.substring(1, filteredEntry.length() - 1));
        } else {
            // if neither of the above conditions is satisfied, the entry is not a palindrome
            return false;
        }

    } // end method palindrome
} // end class Palindrome
