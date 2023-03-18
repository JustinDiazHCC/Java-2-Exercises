
/**
 * GizmoMain.java
 * Driver class for assignment: _17-Project-Inventory
 *
 * @version 1.0
 * @author Justin Diaz
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GizmoMain {
    public static void main(String[] args) {

        // create list of Gizmo objects
        List<Gizmo> gizmos = new ArrayList<Gizmo>();
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
            System.out.printf("Please choose an option:\n" +
                    "1. Add a new entry\n" +
                    "2. Remove an entry specified by the user\n" +
                    "3. Sort the list by year\n" +
                    "4. Sort the list by price\n" +
                    "5. Sort the list by quantity\n" +
                    "6. Calculate and print the total value of the inventory\n" +
                    "7. Print inventory\n" +
                    "8. Quit\n" +
                    "");
            choice = input.nextInt();
            input.nextLine();


        } while (choice != QUIT); // quit if user enters 8 to quit

        // close input stream
        input.close();

    } // end method main
} // end class GizmoMain
