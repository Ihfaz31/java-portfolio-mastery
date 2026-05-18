import java.util.HashMap;
import java.util.Map;

public class InventoryService {
    private Map<String, BookableResource> resources = new HashMap<>();

    public void registerResource(BookableResource resource) {
        resources.put(resource.getId(), resource);
        System.out.println("System Log: Registered " + resource.getName() + " (ID: " + resource.getId() + ")");
    }
    public void bookResource(String id) {
        BookableResource item = resources.get(id);
        if (item != null && item.isAvailable()) {
            item.setAvailable(false);
            System.out.println("SUCCCESS: " + item.getName() + " has been booked.");
        } else {
            System.out.println("FAILED: Item [ " + id + " ] is not booked or not found");
        }
    }
    public void releaseResource(String id) {
        BookableResource item = resources.get(id);
        if (item !=null) {
            item.setAvailable(true);
            System.out.println("RELEASED: " + item.getName() + " is now free again.");
        }
    }
    public void listAllResources() {
        System.out.println("\n--- Current Inventory ---");
        for (BookableResource item : resources.values()) {
            String status = item.isAvailable() ? "FREE" : "[BOOKED]";
            System.out.println(status + " " + item.getId() + " | Name: " + item.getName());
        }
    }
    public void registerResource(BookableResource resource, User user) {
        if (user.isAdmin()) {
            resources.put(resource.getId(), resource);
            System.out.println("Success! Admin added the item.");
    } else {
            // Block and log the attempt
            System.out.println("FAILED: Only admins can add items to the inventory.");
        }
    }
}

