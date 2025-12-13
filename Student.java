public class Student {
    private int id;
    private String name;
    private double cpi;
    private int backlogs;

    // Constructor
    public Student(int id, String name, double cpi, int backlogs) {
        this.id = id;
        this.name = name;
        this.cpi = cpi;
        this.backlogs = backlogs;
    }

    // toString() lets us print the object easily
    @Override
    public String toString() {
        return String.format("ID: %d | Name: %-15s | CPI: %.2f | Backlogs: %d", id, name, cpi, backlogs);
    }
}