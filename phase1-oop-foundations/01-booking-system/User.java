public class User {
    public String username;
    public String role; // e.g., "ADMIN", "USER"
    public String getUsername() {
    return this.username;
}

    public User(String username, String userRole) {
        this.username = username;
        this.role = userRole.toUpperCase();
    }
    // this is the gatekeeper check
    public boolean isAdmin() {
        return "ADMIN".equals(this.role);
    }
}
