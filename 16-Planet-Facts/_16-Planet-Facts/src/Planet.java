package planets;

/**
 * Represents a planet in the solar system
 */
class Planet implements Comparable<Planet> {

    private final String name;
    private final double mass;
    private final int distance;

    /**
     * Creates a new planet
     *
     * @param name The name of the planet
     * @param mass The mass of the planet in 10^24 kg
     * @param distance The average distance from the sun in 10^6 km
     */
    public Planet(String name, double mass, int distance) {
        this.name = name;
        this.mass = mass;
        this.distance = distance;
    }

    @Override
    public int compareTo(Planet other) {        
        // TODO
        return 0;        
    }

    public String getName() {
        return name;
    }

    public double getMass() {
        return mass;
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return "Planet{" + "name=" + name + ", mass=" + mass + ", distance=" + distance + '}';
    }

}
