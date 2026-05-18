import java.util.HashMap;
import java.util.Map;
import java.io.*;

public class InventoryService {
    // RAM Storage
    private Map<String, BookableResource> resources = new HashMap<>();

    // ADDRESSS on Hard Drive
    private final String FILE_NAME = "inventory.txt";

    public void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (BookableResource item : resources.values()) {
                writer.println(item.getId() + "," + item.getName() + "," + item.isAvailable());
            }
            System.out.println("System Log: Data saved to hard drive.");
        } catch (IOException e) {
            System.out.println("ERROR saving: " + e.getMessage());
        }
    }

    public void registerResource(BookableResource resource, User user) {
        if (user.role != null && user.role.equalsIgnoreCase("ADMIN")) {
        // 1. Check if ID exists (to prevent the duplicates we saw earlier)
        if (resources.containsKey(resource.getId())) {
            System.out.println("Error: ID " + resource.getId() + " already exists!");
        } else {
            resources.put(resource.getId(), resource);
            saveToFile();
            System.out.println("Success! Item registered and saved to disk.");
        }
    } else {
        // This is the message you keep seeing!
        System.out.println("Security Alert: Unauthorized access attempt.");
        // Debug line to tell us EXACTLY what is wrong
        System.out.println("System saw role: [" + user.role + "]"); 
    }
    }
    public void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists())
            return;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // 1. Split the line by the comma
                String[] parts = line.split(",");
                if (parts.length < 3)
                    continue;
                String id = parts[0];
                String name = parts[1];
                boolean available = Boolean.parseBoolean(parts[2]);
                BookableResource resource = new BookableResource(id, name);
                resource.setAvailable(available);
                resources.put(id, resource);
            }
            System.out.println("System Log: Data loaded from hard drive.");
        } catch (IOException e) {
            System.out.println("ERROR loading: " + e.getMessage());
        }
    }
    // 1. Fixes the "listResources" error
    public void listResources() {
        if (resources.isEmpty()) {
            System.out.println("The inventory is empty.");
        } else {
            for (BookableResource item : resources.values()) {
                String status = item.isAvailable() ? "Available" : "Booked";
            System.out.println(item.getId() + ": " + item.getName() + " [" + status + "]");
            }
        }
    }

    // 2. Fixes the "bookResource" error
    public void bookResource(String id) {
        if (resources.containsKey(id)) {
            resources.get(id).setAvailable(false);
            saveToFile(); // Important: Save the booking to the file!
            System.out.println("Asset " + id + " has been booked.");
        } else {
            System.out.println("Asset ID not found.");
        }
    }

    // 3. Fixes the "releaseResource" error
    public void releaseResource(String id) {
        if (resources.containsKey(id)) {
            resources.get(id).setAvailable(true);
            saveToFile(); // Important: Save the release to the file!
            System.out.println("Asset " + id + " is now available.");
        } else {
            System.out.println("Asset ID not found.");
        }
    }
}
