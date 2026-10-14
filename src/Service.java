 
public class Service {
    private int id;
    private String name;
    private double firstHourCost;
    private double additionalHourCost;

    public Service(int id, String name, double firstHourCost, double additionalHourCost) {
        this.id = id;
        this.name = name;
        this.firstHourCost = firstHourCost;
        this.additionalHourCost = additionalHourCost;
    }

   
    public int getId() { return id; }
    public String getName() { return name; }
    public double getFirstHourCost() { return firstHourCost; }
    public double getAdditionalHourCost() { return additionalHourCost; }
}
