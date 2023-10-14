package server.domain;
public class Car{
    private String plate;
    private String renavan;
    private String name;
    private String brand;
    private int year;
    private Driver driver;

    public Car(String plate, String renavan, String name, String brand, int year, Driver driver) {
        this.plate = plate;
        this.renavan = renavan;
        this.name = name;
        this.brand = brand;
        this.year = year;
        this.driver = driver;
    }

    public Car(String plate, String renavan, String name, String brand, int year, String driverName, String driverCPF) {
        this.plate = plate;
        this.renavan = renavan;
        this.name = name;
        this.brand = brand;
        this.year = year;
        this.driver = new Driver(driverName, driverCPF);
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getRenavan() {
        return renavan;
    }

    public void setRenavan(String renavan) {
        this.renavan = renavan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    @Override
    public String toString() {
        return  plate +
                "," + renavan +
                "," + name +
                "," + brand +
                "," + year +
                "," + driver +
                '}';
    }
}
