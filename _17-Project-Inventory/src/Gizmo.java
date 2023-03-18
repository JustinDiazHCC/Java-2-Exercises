/**
 * Gizmojava
 * Contains instance variables for assignment: _17-Project-Inventory
 *
 * @version 1.0
 * @author Justin Diaz
 */
public class Gizmo implements Comparable<Gizmo>{

    // instance variables
    protected String name;
    protected int number;
    protected int year;
    protected int quantity;
    protected double price;

    // full argument constructor
    public Gizmo(String name, int number, int year, int quantity, double price) {
        this.name = name;
        this.number = number;
        this.year = year;
        this.quantity = quantity;
        this.price = price;
    }
    // zero argument constructor
    public Gizmo() {
        this.name = "";
        this.number = 0;
        this.year = 0;
        this.quantity = 0;
        this.price = 0.0;
    }

    // getter and setter methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // toString method

    @Override
    public String toString() {
        return "Gizmo{" +
                "name='" + name + '\'' +
                ", number=" + number +
                ", year=" + year +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }

    @Override
    public int compareTo(Gizmo other) {
        Integer year = this.getYear();
        return year.compareTo(other.getYear());
    }
} // end class Gizmo