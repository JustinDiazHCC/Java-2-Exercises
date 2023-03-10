/**
 * WordCompare.java
 * Prompts user for two words, and prints the entry that comes first alphabetically
 *
 * @version 1.0
 * @author Justin Diaz
 */

import java.util.Scanner;

public class WordCompare {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String firstEntry, firstWord;
        String secondEntry, secondWord;
        boolean keepGoing = false;

        do {
            // prompt first word entry
            System.out.println("Please enter a word");
            firstEntry = input.nextLine();

            firstWord = firstEntry.trim();

            // prompt second word entry
            System.out.println("Please enter another word");
            secondEntry = input.nextLine();
            secondWord = secondEntry.trim();

            // compare words alphabetically; print which comes first
            if (firstWord.compareToIgnoreCase(secondWord) < 0) {
                System.out.printf("%s comes before %s%n", firstWord, secondWord);
            } else if (firstWord.compareToIgnoreCase(secondWord) > 0) {
                System.out.printf("%s comes before %s%n", secondWord, firstWord);
            } else {
                System.out.printf("%s and %s are the same word.%n", firstWord, secondWord);
            }

            // ask if user would like to compare more words
            boolean validEntry = false;
            do {
                System.out.println("Would you like to compare more words? (Yes/No)");
                String choice = input.nextLine();
                if (choice.equalsIgnoreCase("yes") || choice.equalsIgnoreCase("Y")) {
                    validEntry = true;
                    keepGoing = true;
                } else if (choice.equalsIgnoreCase("no") || choice.equalsIgnoreCase("N")) {
                    validEntry = true;
                    keepGoing = false;
                } else System.out.println("Please enter yes/no.");
            } while (!validEntry);
        } while (keepGoing);

        // close input stream
        input.close();

        // goodbye message
        System.out.println("Goodbye.");
    }
} // end class WordCompare