/**
 * GizmoMain.java
 * Driver class for assignment: _17-Project-Inventory
 *
 * @version 1.0
 * @author Justin Diaz
 */

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class GizmoMain {
    public static void main(String[] args) {

        // create list of Gizmo objects
        List<Gizmo> gizmos = new ArrayList<>();
        gizmos.add(new Gizmo("Waxbill", 618001, 2015, 3, 499.99));
        gizmos.add(new Gizmo("Heron", 618002, 2016, 13, 299.99));
        gizmos.add(new Gizmo("Crane", 618003, 2017, 195, 49.99));
        gizmos.add(new Gizmo("Duck", 618004, 2018, 295, 99.99));
        gizmos.add(new Gizmo("Wallaby", 618005, 2019, 973, 149.99));
        gizmos.add(new Gizmo("Egret", 618006, 2020, 495, 199.99));

        // menu options
        final int NEW_ENTRY = 1;
        final int REMOVE_ENTRY = 2;
        final int SORT_BY_YEAR = 3;
        final int SORT_BY_PRICE = 4;
        final int SORT_BY_QUANTITY = 5;
        final int PRINT_TOTAL_VALUE = 6;
        final int PRINT_INVENTORY = 7;
        final int QUIT = 8;

        Scanner input = new Scanner(System.in); // Scanner object
        int choice = 0; // track user choice

        // menu dialog loop
        do {
            System.out.print("""
                    Please choose an option:
                    1. Add a new entry
                    2. Remove an entry
                    3. Sort the list by year
                    4. Sort the list by price
                    5. Sort the list by quantity
                    6. Calculate and print the total value of the inventory
                    7. Print inventory
                    8. Quit
                    """);

            // user entry validation loop
            boolean validMenuEntry;
            do {
                try {
                    choice = input.nextInt();
                    validMenuEntry = true;
                    // validate number entry in range
                    if (choice < 1 || choice > 8) {
                        System.err.println("Invalid entry. Please enter a number from 1-8.");
                        validMenuEntry = false;
                    }
                } catch (InputMismatchException e) {
                    // validate correct input type
                    System.err.println("Invalid entry. Please enter a number from 1-8.");
                    validMenuEntry = false;
                } finally {
                    input.nextLine();
                }
            } while (!validMenuEntry);

            switch (choice) {
                case NEW_ENTRY:
                    boolean validNewEntry;
                    do {
                        try {
                            System.out.println("Enter product name:");
                            String newName = input.nextLine();

                            System.out.println("Enter product number:");
                            int newNumber = input.nextInt();
                            input.nextLine(); // clear keyboard buffer

                            System.out.println("Enter year:");
                            int newYear = input.nextInt();
                            input.nextLine(); // clear keyboard buffer

                            System.out.println("Enter quantity:");
                            int newQuantity = input.nextInt();
                            input.nextLine(); // clear keyboard buffer

                            System.out.println("Enter price:");
                            double newPrice = input.nextDouble();
                            input.nextLine(); // clear keyboard buffer

                            validNewEntry = true;

                            // add new Gizmo object to gizmos list
                            gizmos.add(new Gizmo(newName, newNumber, newYear, newQuantity, newPrice));

                        } catch (InputMismatchException e) {
                            // validate correct input type
                            System.err.println("Invalid entry. Please try again.");
                            validNewEntry = false;
                        }
                    } while (!validNewEntry);
                    break;
                case REMOVE_ENTRY:
                    boolean validRemoveEntry;
                    int removeEntry;
                    do {
                        try {
                            System.out.println("Current entries:");
                            for (int i = 0; i < gizmos.size(); i++) {
                                System.out.printf(i + ": " + gizmos.get(i) + "\n");
                            }
                            System.out.println("Enter the index of the entry you would like to remove:");
                            removeEntry = input.nextInt();
                            input.nextLine();
                            gizmos.remove(removeEntry);
                            validRemoveEntry = true;
                        } catch (InputMismatchException e) {
                            // validate correct input type
                            // TODO prevent infinite loop
                            System.err.println("Invalid entry. Please try again.");
                            validRemoveEntry = false;
                        } catch (IndexOutOfBoundsException e) {
                            // validate correct index
                            System.err.println("Invalid selection. Please try again.");
                            validRemoveEntry = false;
                        }
                    } while (!validRemoveEntry);
                    break;
                case SORT_BY_YEAR:
                    break;
                case SORT_BY_PRICE:
                    break;
                case SORT_BY_QUANTITY:
                    break;
                case PRINT_TOTAL_VALUE:
                    break;
                case PRINT_INVENTORY:
                    System.out.printf("%-10s%-10s%-8s%-10s%-8s\n", "Name", "Number", "Year", "Quantity", "Price");
                    for (Gizmo gizmo : gizmos) {
                        System.out.printf("%-10s%-10d%-8d%-10d%-8.2f\n", gizmo.getName(), gizmo.getNumber(),
                                gizmo.getYear(), gizmo.getQuantity(), gizmo.getPrice());
                    }
                    break;
                case QUIT:
                    break;
            }
        } while (choice != QUIT); // quit if user enters 8 to quit

        // close input stream
        input.close();

    } // end method main
} // end class GizmoMain
