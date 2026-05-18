public class BookableResource {

    // 1. Properties
    private String id;
    private String name;
    private boolean isAvailable;

    // 2. Constructor
    public BookableResource(String id, String name) {
        this.id = id;
        this.name = name;
        this.isAvailable = true;
    }

    // 3. Getters and Setters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }
}