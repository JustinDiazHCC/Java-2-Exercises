/**
 * FormatContactList.java
 * Imports a list of contacts from a .txt file,
 * Outputting them into a formatted .txt file
 *
 * @author Justin Diaz
 * @version 1.0
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Formatter;
import java.util.Scanner;

public class FormatContactList {
    public static void main(String[] args) {
        // input file
        String inputFileName = "_15-Format-Contact-List/input-files/contacts.txt";
        Path inputFile = Paths.get(inputFileName);

        // output file
        Path outputFile = Paths.get("_15-Format-Contact-List/output-files/formatted-contacts.txt");

        // Scanner object to read input file
        try (Scanner inputFileScanner = new Scanner(inputFile)) {
            // Formatter object to write output file
            try (Formatter formatter = new Formatter(outputFile.toFile())) {

                // iterate through contacts list entries, assigning each field to a variable
                while (inputFileScanner.hasNextLine()) {
                    String entry = inputFileScanner.nextLine();
                    String[] fields = entry.split(",");

                    String firstName = fields[1];
                    String lastName = fields[2];
                    String email = fields[3];

                    // print formatted entry to output file
                    formatter.format("%s, %s <%s>\n", lastName, firstName, email);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    } // end method main
} // end class FormatContactList
