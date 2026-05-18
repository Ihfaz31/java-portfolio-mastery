import java.util.Scanner;

public class Main {
    /**
     * @param args
     */
    public static void main(String[] args) {
        // 1. Initialize the Service (The Engine)
        InventoryService inventory = new InventoryService();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        System.out.println("Enter Username: ");
        String name = scanner.nextLine();

        System.out.println("Enter Role (ADMIN/USER): ");
        String role = scanner.nextLine();

        // Create a user object based on input
        User currentUser = new User(name, role);
        // Use the user object (avoid unused variable) — display logged in user
        System.out.println("Logged in: " + currentUser.getUsername());

        // 2. Pre-register some data so we have something to test
        inventory.registerResource(new BookableResource("LAP-01", "MacBook Pro"), currentUser);
        inventory.registerResource(new BookableResource("LAP-02", "Dell XPS"), currentUser);
        inventory.registerResource(new BookableResource("LAP-03", "HP Spectre"), currentUser);
        inventory.registerResource(new BookableResource("LAP-04", "HP Victus"), currentUser);

        // ADMIN Register
        System.out.println("\n--- RMIT ASSET MANAGER ---");

        while (running) {
            System.out.println("\nMENU: [1]List  [2]Book  [3]Release  [4]Exit [5]Register (ADMIN only)");
            System.out.print("Action: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    inventory.listAllResources();
                    break;
                case "2":
                    System.out.print("Enter ID to Book: ");
                    inventory.bookResource(scanner.nextLine());
                    break;
                case "3":
                    System.out.print("Enter ID to Release: ");
                    inventory.releaseResource(scanner.nextLine());
                    break;
                case "4":
                    running = false;
                    System.out.println("System exiting... Goodbye!");
                    break;
                case "5":
                    System.out.print("Enter New Resource ID: ");
                    String newId = scanner.nextLine();
                    System.out.print("Enter New Asset Name: ");
                    String newName = scanner.nextLine();
        
        // This is the important part: passing the NEW asset and the CURRENT user
        inventory.registerResource(new BookableResource(newId, newName), currentUser);
        break;
                default:
                    System.out.println("Invalid selection. Try 1-4.");
            }
        }
        scanner.close();
    }
}