/**
 * JobApplicants.java
 * Read job applicant information and generate personalized response emails
 *
 * @version 1.0
 * @author Justin Diaz
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Formatter;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JobApplicants {
    public static void main(String[] args) {
        // input file
        String inputFileName = "_15-Job-Applicants/input-files/applicants.csv";
        Path inputFile = Paths.get(inputFileName);

        //output file
        Path outputFile = Paths.get("_15-Job-Applicants/output-files/emails.txt");

        // Scanner object to read input file
        try (Scanner inputFileScanner = new Scanner(inputFile)) {
            // Formatter object to write output file
            try (Formatter formatter = new Formatter(outputFile.toFile())) {
                // initialize a variable for each field per record
                String id;
                String title;
                String firstName;
                String lastName;
                String email;
                String position;
                String status;

                // StringBuilder object to write output file text
                StringBuilder str = new StringBuilder();

                // iterate through each record of input file
                while (inputFileScanner.hasNextLine()) {
                    String entry = inputFileScanner.nextLine();
                    String[] fields = entry.split(",");

                    // iterate through fields array, strip leading and trailing whitespace
                    for (int i = 0; i < fields.length; i++) {
                        fields[i] = fields[i].strip();
                    }

                    // assign each field to its corresponding variable
                    id = fields[0];
                    title = fields[1];
                    firstName = fields[2];
                    lastName = fields[3];
                    email = fields[4];
                    position = fields[5];
                    status = fields[6];

                    if (!email.contains("@")) {
                        // print error message if email field does not contain @ symbol
                        System.err.printf("The following email address is invalid: \"<%s>\". Missing @ symbol.%n", email);
                    } else if (!firstName.chars().allMatch(Character::isLetter)) {
                        // print error message if firstName field contains any character other than uppercase or lowercase letters
                        System.err.printf("Invalid character in first name: \"%s\"%n", firstName);
                    } else if (!lastName.chars().allMatch(Character::isLetter)) {
                        // print error message if lastName field contains any character other than uppercase or lowercase letters
                        System.err.printf("Invalid character in last name: \"%s\"%n", lastName);
                    } else {
                        // write personalized email based on hiring status
                        String personalizedEmail;
                        switch (status) {
                            case "Hire":
                                // address email to full name if title is omitted
                                if (title.equals("")) {
                                    personalizedEmail = "--------------------------------------------------------------------------------\n" +
                                            "To: " + email + "\n" +
                                            "From: humanresources@gizmogroupllc.com\n" +
                                            "Subject: Your Gizmo Group Application\n" +
                                            "--------------------------------------------------------------------------------\n" +
                                            "Dear " + firstName + " " + lastName + ",\n" +
                                            "\n" +
                                            "After careful consideration of your application and interview, we are pleased\n" +
                                            "to inform you that you have been hired by Gizmo Group for the position of\n" + position +
                                            ".\n\n" +
                                            "Please reply soon to accept your position and begin working with us. We look\n" +
                                            "forward to seeing you soon!\n" +
                                            "\n" +
                                            "Cheers,\n" +
                                            "The Gizmo Group\n" +
                                            "--------------------------------------------------------------------------------";
                                } else {
                                    // abbreviate the title of Honorable if present
                                    if (title.equals("Honorable")) {
                                        title = "Hon";
                                    }
                                    // address email to title + last name
                                    personalizedEmail = "--------------------------------------------------------------------------------\n" +
                                            "To: " + email + "\n" +
                                            "From: humanresources@gizmogroupllc.com\n" +
                                            "Subject: Your Gizmo Group Application\n" +
                                            "--------------------------------------------------------------------------------\n" +
                                            "Dear " + title + ". " + lastName + ",\n" +
                                            "\n" +
                                            "After careful consideration of your application and interview, we are pleased\n" +
                                            "to inform you that you have been hired by Gizmo Group for the position of\n" + position +
                                            ".\n\n" +
                                            "Please reply soon to accept your position and begin working with us. We look\n" +
                                            "forward to seeing you soon!\n" +
                                            "\n" +
                                            "Cheers,\n" +
                                            "The Gizmo Group\n" +
                                            "--------------------------------------------------------------------------------";
                                }
                                // append email to StringBuilder object
                                str.append(personalizedEmail + "\n");
                                break;
                            case "Reject":
                                // address email to full name if title is omitted
                                if (title.equals("")) {
                                    personalizedEmail = "--------------------------------------------------------------------------------\n" +
                                            "To: " + email + "\n" +
                                            "From: humanresources@gizmogroupllc.com\n" +
                                            "Subject: Your Gizmo Group Application\n" +
                                            "--------------------------------------------------------------------------------\n" +
                                            "Dear " + firstName + " " + lastName + ",\n" +
                                            "\n" +
                                            "After careful consideration of your application and interview, we regret \n" +
                                            "to inform you that you have been rejected by Gizmo Group for the position of\n" + position +
                                            ".\n\n" +
                                            "We will be keeping your information on file in case circumstances change. Thank\n" +
                                            "you for your interest in working with us.\n" +
                                            "\n" +
                                            "Sincerely,\n" +
                                            "The Gizmo Group\n" +
                                            "--------------------------------------------------------------------------------";
                                } else {
                                    // abbreviate the title of Honorable if present
                                    if (title.equals("Honorable")) {
                                        title = "Hon";
                                    }
                                    // address email to title + last name
                                    personalizedEmail = "--------------------------------------------------------------------------------\n" +
                                            "To: " + email + "\n" +
                                            "From: humanresources@gizmogroupllc.com\n" +
                                            "Subject: Your Gizmo Group Application\n" +
                                            "--------------------------------------------------------------------------------\n" +
                                            "Dear " + title + ". " + lastName + ",\n" +
                                            "\n" +
                                            "After careful consideration of your application and interview, we regret \n" +
                                            "to inform you that you have been rejected by Gizmo Group for the position of\n" + position +
                                            ".\n\n" +
                                            "We will be keeping your information on file in case circumstances change. Thank\n" +
                                            "you for your interest in working with us.\n" +
                                            "\n" +
                                            "Sincerely,\n" +
                                            "The Gizmo Group\n" +
                                            "--------------------------------------------------------------------------------";
                                }
                                // append email to StringBuilder object
                                str.append(personalizedEmail + "\n");
                                break;
                            case "Interview":
                                // address email to full name if title is omitted
                                if (title.equals("")) {
                                    personalizedEmail = "--------------------------------------------------------------------------------\n" +
                                            "To: " + email + "\n" +
                                            "From: humanresources@gizmogroupllc.com\n" +
                                            "Subject: Your Gizmo Group Application\n" +
                                            "--------------------------------------------------------------------------------\n" +
                                            "Dear " + firstName + " " + lastName + ",\n" +
                                            "\n" +
                                            "After careful consideration of your application, we would like to request that \n" +
                                            "you schedule an in-person interview in our main office for the position of\n" + position +
                                            ".\n\n" +
                                            "Please reply to this email for more information on the office location and\n" +
                                            "what to expect at your interview. We look forward to seeing you soon!\n" +
                                            "\n" +
                                            "Kind regards,\n" +
                                            "The Gizmo Group\n" +
                                            "--------------------------------------------------------------------------------";
                                } else {
                                    // abbreviate the title of Honorable if present
                                    if (title.equals("Honorable")) {
                                        title = "Hon";
                                    }
                                    // address email to title + last name
                                    personalizedEmail = "--------------------------------------------------------------------------------\n" +
                                            "To: " + email + "\n" +
                                            "From: humanresources@gizmogroupllc.com\n" +
                                            "Subject: Your Gizmo Group Application\n" +
                                            "--------------------------------------------------------------------------------\n" +
                                            "Dear " + title + ". " + lastName + ",\n" +
                                            "\n" +
                                            "After careful consideration of your application, we would like to request that \n" +
                                            "you schedule an in-person interview in our main office for the position of\n" + position +
                                            ".\n\n" +
                                            "Please reply to this email for more information on the office location and\n" +
                                            "what to expect at your interview. We look forward to seeing you soon!\n" +
                                            "\n" +
                                            "Kind regards,\n" +
                                            "The Gizmo Group\n" +
                                            "--------------------------------------------------------------------------------";
                                }
                                // append email to StringBuilder object
                                str.append(personalizedEmail + "\n");
                                break;
                        }
                    }
                }
                // format entire StringBuilder object to output file
                formatter.format(str.toString());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    } // end method main
} // end class JobApplicants