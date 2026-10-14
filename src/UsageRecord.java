import java.time.Duration;
import java.time.LocalDateTime;

 
public class UsageRecord {
    private int id;
    private String userName;
    private Resource resource;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private double bill;

    public UsageRecord(int id, String userName, Resource resource, LocalDateTime startTime) {
        this.id = id;
        this.userName = userName;
        this.resource = resource;
        this.startTime = startTime;
        this.endTime = null;
        this.bill = 0.0;
    }

     
    public void stop(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    
    public long getBillableHours() {
        if (endTime == null) {
            return 0;
        }
        long minutes = Duration.between(startTime, endTime).toMinutes();
        if (minutes <= 0) {
            return 1;
        }
        return (minutes + 59) / 60;
    }

    
    public double calculateBill() {
        long hours = getBillableHours();
        if (hours == 0) {
            bill = 0.0;
        } else {
            bill = resource.getService().getFirstHourCost()
                    + (hours - 1) * resource.getService().getAdditionalHourCost();
        }
        return bill;
    }

    public int getId() { return id; }
    public String getUserName() { return userName; }
    public Resource getResource() { return resource; }
    public LocalDateTime getStartTime() { return startTime; }
    public LocalDateTime getEndTime() { return endTime; }
    public double getBill() { return bill; }
}
