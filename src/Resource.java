 
import java.util.ArrayList;
import java.util.List;

public class Resource {
    private int id;
    private String name;
    private int capacity;
    private Service service;
    private List<UsageRecord> activeUsages;

    public Resource(int id, String name, int capacity, Service service) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.service = service;
        this.activeUsages = new ArrayList<UsageRecord>();
    }

     
    public boolean hasAvailableSlot() {
        return activeUsages.size() < capacity;
    }

  
    public void addUsage(UsageRecord usage) {
        activeUsages.add(usage);
    }

    
    public void removeUsage(UsageRecord usage) {
        activeUsages.remove(usage);
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getCapacity() { return capacity; }
    public Service getService() { return service; }
    public List<UsageRecord> getActiveUsages() { return activeUsages; }
}
