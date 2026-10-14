import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class BillingSystem {
    private static List<Resource> resources = new ArrayList<Resource>();
    private static List<UsageRecord> allUsages = new ArrayList<UsageRecord>();
    private static int nextUsageId = 1;
    private static Scanner scanner = new Scanner(System.in);
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static void main(String[] args) {
        addSampleData();
        boolean running = true;

        // the menu making the start, stop, availability, and billing actions.
        while (running) {
            System.out.println("\n=== Usage and Billing System ===");
            System.out.println("1. View resources");
            System.out.println("2. Start usage");
            System.out.println("3. Stop usage");
            System.out.println("4. View usage history and bills");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1": viewResources(); break;
                case "2": startUsage(); break;
                case "3": stopUsage(); break;
                case "4": viewUsageHistory(); break;
                case "5": running = false; break;
                default: System.out.println("Please enter a number from 1 to 5.");
            }
        }
        System.out.println("Application closed.");
    }

 
    private static void addSampleData() {
        Service meetingRoomService = new Service(1, "Meeting Room", 30.0, 10.0);
        Service workstationService = new Service(2, "Workstation", 20.0, 8.0);
        resources.add(new Resource(1, "Meeting Room A", 2, meetingRoomService));
        resources.add(new Resource(2, "Workstation 1", 1, workstationService));
    }

    // Showingg capacity and active usage count so the user can see which resources have free slots.
    private static void viewResources() {
        System.out.println("\nAvailable resources:");
        for (Resource resource : resources) {
            int active = resource.getActiveUsages().size();
            System.out.println(resource.getId() + ". " + resource.getName()
                    + " | Capacity: " + resource.getCapacity()
                    + " | In use: " + active
                    + " | Free slots: " + (resource.getCapacity() - active)
                    + " | Service: " + resource.getService().getName());
        }
    }

    // Starts a session only if the resource exists and has an available slot otherwise it rejects.
    private static void startUsage() {
        viewResources();
        System.out.print("Enter resource ID: ");
        int resourceId = readInt();
        Resource resource = findResource(resourceId);

        if (resource == null) {
            System.out.println("Resource not found.");
            return;
        }
        if (!resource.hasAvailableSlot()) {
            System.out.println("Resource is full. Usage request rejected.");
            return;
        }

        System.out.print("Enter user name: ");
        String userName = scanner.nextLine().trim();
        if (userName.isEmpty()) {
            System.out.println("User name cannot be empty.");
            return;
        }

        UsageRecord usage = new UsageRecord(nextUsageId++, userName, resource, LocalDateTime.now());
        resource.addUsage(usage);
        allUsages.add(usage);
        System.out.println("Usage started at " + usage.getStartTime().format(formatter));
        System.out.println("Usage record ID: " + usage.getId());
    }

    // Stoping th active session, calculating  the bill, and releasesing the resource slot.
    private static void stopUsage() {
        List<UsageRecord> active = getActiveUsages();
        if (active.isEmpty()) {
            System.out.println("There are no active usages.");
            return;
        }

        System.out.println("\nActive usages:");
        for (UsageRecord usage : active) {
            System.out.println("Record ID: " + usage.getId()
                    + " | User: " + usage.getUserName()
                    + " | Resource: " + usage.getResource().getName()
                    + " | Started: " + usage.getStartTime().format(formatter));
        }

        System.out.print("Enter usage record ID to stop: ");
        int usageId = readInt();
        UsageRecord usage = findActiveUsage(usageId);
        if (usage == null) {
            System.out.println("Active usage record not found.");
            return;
        }

        usage.stop(LocalDateTime.now());
        double bill = usage.calculateBill();
        usage.getResource().removeUsage(usage);

        System.out.println("Usage stopped at " + usage.getEndTime().format(formatter));
        System.out.println("Billable hours: " + usage.getBillableHours());
        System.out.printf("Final bill: Rs. %.2f%n", bill);
    }
 
    private static void viewUsageHistory() {
        if (allUsages.isEmpty()) {
            System.out.println("No usage records yet.");
            return;
        }

        System.out.println("\nUsage history:");
        for (UsageRecord usage : allUsages) {
            System.out.print("ID: " + usage.getId()
                    + " | User: " + usage.getUserName()
                    + " | Resource: " + usage.getResource().getName()
                    + " | Start: " + usage.getStartTime().format(formatter));

            if (usage.getEndTime() == null) {
                System.out.println(" | Status: ACTIVE");
            } else {
                System.out.println(" | End: " + usage.getEndTime().format(formatter)
                        + " | Hours: " + usage.getBillableHours()
                        + " | Bill: Rs. " + String.format("%.2f", usage.getBill()));
            }
        }
    }
 
    private static int readInt() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
 
    private static Resource findResource(int id) {
        for (Resource resource : resources) {
            if (resource.getId() == id) {
                return resource;
            }
        }
        return null;
    }

 
    private static List<UsageRecord> getActiveUsages() {
        List<UsageRecord> active = new ArrayList<UsageRecord>();
        for (UsageRecord usage : allUsages) {
            if (usage.getEndTime() == null) {
                active.add(usage);
            }
        }
        return active;
    }

 
    private static UsageRecord findActiveUsage(int id) {
        for (UsageRecord usage : allUsages) {
            if (usage.getId() == id && usage.getEndTime() == null) {
                return usage;
            }
        }
        return null;
    }
}
