// Main class
public class Example {
    public static void main(String[] args) {
        Car myCar = new Car("Toyota", 2023);
        myCar.displayDetails();
    }
}

// Second class in the same file
class Car {
    private String brand;
    private int year;

    // Constructor
    public Car(String brand, int year) {
        this.brand = brand;
        this.year = year;
    }

    // Method to display car details
    public void displayDetails() {
        System.out.println("Brand: " + brand + ", Year: " + year);
    }
}
