/**
 * Search.java
 * A program to prompt user file input and allow for the user to search for a word in the file
 *
 * @author Justin Diaz
 * @version 1.1
 */

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Search {
    public static void main(String[] args) {
        // Scanner object
        Scanner input = new Scanner(System.in);

        // create a CustomBinaryTreeSimpleMap for each line
        SimpleMap<Integer, String> lineMap = new CustomBinaryTreeSimpleMap<>();
        /**
         using CustomBinaryTreeSimpleMap
         */
        // create a CustomBinaryTreeSimpleMap for each word
        SimpleMap<String, ArrayList<Integer>> indexMap = new CustomBinaryTreeSimpleMap<>();

        // prompt user file input
        System.out.println("Please enter the name of a file:");
        String inputFileName = input.nextLine();
        Path inputFile = Paths.get(inputFileName);

        // Scanner object to read input file
        try (Scanner inputFileScanner = new Scanner(inputFile)) {

            // index input file
            System.out.printf("%nIndexing %s...%n%n", inputFileName);

            // track line iterations with iterator
            int lineNumber = 0;

            // iterate through each line in input file
            while (inputFileScanner.hasNextLine()) {
                lineNumber++;
                String line = inputFileScanner.nextLine();
                // put each line in lineMap
                lineMap.put(lineNumber, line);

                // trim leading and trailing whitespace
                line = line.trim();
                // remove punctuation
                line = line.replaceAll("[^a-zA-Z0-9\s]", "");
                // to lowercase
                line = line.toLowerCase();
                // create array of each word in line
                String[] words = line.split(" ");

                for (String word : words) {
                    // put each word in indexMap
                    if (indexMap.get(word) == null) {
                        // create new ArrayList of line numbers if one doesn't already exist
                        ArrayList lineNumbers = new ArrayList<String>();
                        lineNumbers.add(lineNumber);
                        indexMap.put(word, lineNumbers);
                    }
                    else {
                        // add line number to existing ArrayList otherwise
                        indexMap.get(word).add(lineNumber);
                        indexMap.put(word, indexMap.get(word));
                    }
                }
            }

            String searchTerm;
            do {
                // prompt search term entry
                System.out.print("Please enter a search term (blank to exit):\n");
                searchTerm = input.nextLine();
                // end iteration if user enters empty string
                if(searchTerm == "")
                    break;
                System.out.print("\n");

                // search term to lowercase
                searchTerm = searchTerm.toLowerCase();

                // search for term in indexMap
                if (indexMap.containsKey(searchTerm)) {
                    for (Integer lineNum : indexMap.get(searchTerm))
                        System.out.printf("%d: %s%n", lineNum, lineMap.get(lineNum));
                    System.out.println();
                } else {
                    // print "not found" message
                    System.out.println("Search term not found in file.\n");
                }
            } while (!searchTerm.equals("")); // iterate as long as user enters a search term
        } catch (NoSuchFileException e) {
            System.err.println("No such file found.");
        } catch (IOException e) {
            System.err.println("File input/output exception occurred.");
        }
    } // end method main
} // end class Search
