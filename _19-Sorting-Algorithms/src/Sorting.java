import java.util.*;

/**
 * Sorting.java
 * Creates a random list of numbers and uses different algorithms to sort the data
 *
 * @version 1.0
 * @author Justin Diaz
 */
public class Sorting {
    public static void main(String[] args) {
        // initialize array of size 1000
        Integer[] numbersArray = new Integer[1000];

        // assign a growing number to each index of the array
        for(int i = 0; i < numbersArray.length; i++) {
            numbersArray[i] = i + 1;
        }

        // print every index of the array to a new line
        for(int number : numbersArray) {
            System.out.println(number);
        }

        // empty line for output whitespace
        System.out.println();

        List<Integer> shuffledNumbers = new LinkedList<Integer>();
        for (int number : shuffledNumbers) {
            shuffledNumbers.add(number + 1);
        }
        for(int number : shuffledNumbers) {
            System.out.println(number);
        }


        // shuffle contents of numbers array
        shuffledNumbers = Arrays.asList(numbersArray);
        Collections.shuffle(shuffledNumbers);
        shuffledNumbers.toArray();

        // print every index of shuffled array to a new line
        for (int number : shuffledNumbers) {
            System.out.println(number);
        }






    } // end method main
} // end class Sorting