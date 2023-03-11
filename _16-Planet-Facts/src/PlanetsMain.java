package planets;

import java.util.*;

/**
 * Prints facts about planets - practice using Collections methods
 */
public class PlanetsMain {

    public static void main(String[] args) {

        // Source https://nssdc.gsfc.nasa.gov/planetary/factsheet/
        List<Planet> planets = new ArrayList<>(9);
        planets.add(new Planet("Mercury", 0.33, 58));
        planets.add(new Planet("Venus", 4.87, 108));
        planets.add(new Planet("Earth", 5.97, 150));
        planets.add(new Planet("Mars", .642, 228));
        planets.add(new Planet("Jupiter", 1898, 779));
        planets.add(new Planet("Saturn", 568, 1434));
        planets.add(new Planet("Uranus", 86.8, 2873));
        planets.add(new Planet("Neptune", 102, 4495));
        planets.add(new Planet("Pluto", 0.0146, 5906));

        System.out.println("Original order:");
        printPlanets(planets);

        // Randomize the order of the planets
        Collections.shuffle(planets);

        System.out.println("Planets in a random order:");
        printPlanets(planets);

        // Remove pluto
        Iterator itr = planets.iterator();
        Planet element;
        while (itr.hasNext()) {
            element = (Planet) itr.next();
            if (element.getName().equals("Pluto")) {
                itr.remove();
            }
        }

        System.out.println("Removed Pluto:");
        printPlanets(planets);

        // Sort by distance from sun using Natural Ordering (Hint: Implement Planet.compareTo)
        Collections.sort(planets);
        System.out.println("Sorted by distance from sun:");
        printPlanets(planets);

        // Sort alphabetically (Hint: you need a Comparator class)
        Collections.sort(planets, new Comparator<Planet>() {
            @Override
            public int compare(Planet o1, Planet o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        System.out.println("Sorted alphabetically:");
        printPlanets(planets);

        // Sort by mass (Hint: you need a Comparator class)
        Collections.sort(planets, new Comparator<Planet>() {
            @Override
            public int compare(Planet o1, Planet o2) {
                double diff = o1.getMass() - o2.getMass();
                if (diff < 0) {
                    return -1;
                } else if (diff == 0) {
                    return 0;
                } else { // diff must be positive if neither negative nor 0
                    return 1;
                }
            }
        });

        System.out.println("Sorted by mass:");
        printPlanets(planets);

        // Add each planet in your list to a Map (name => Planet)
        Map<String, Planet> planetMap = new HashMap<>();
        // Hint - use an enhanced for loop
        for (Planet planet : planets) {
            planetMap.put(planet.getName(), planet);
        }

        System.out.println("Earth:" + planetMap.get("Earth"));
        // Retrieve and print Mars
        System.out.println("Mars: " + planetMap.get("Mars"));

    }

    /**
     * Print the planet list in a table
     */
    private static void printPlanets(List<Planet> planets) {

        System.out.println("------------------------------------------------------------");
        System.out.printf("%-10s%-15s%-10s%n%n", "Planet", "Mass", "Distance");

        for (Planet planet : planets) {
            System.out.printf("%-10s%-15.3f%-10d%n", planet.getName(),
                    planet.getMass(), planet.getDistance());
        }

        System.out.println("------------------------------------------------------------\n\n");
    }

}
