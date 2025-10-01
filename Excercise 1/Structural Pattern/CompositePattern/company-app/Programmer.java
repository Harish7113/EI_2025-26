
// Leaf
public class Programmer implements Staff {
    private final String fullName;
    private final long id;
    private final String role;

    public Programmer(long id, String fullName, String role) {
        this.id = id;
        this.fullName = fullName;
        this.role = role;
    }

    @Override
    public void displayInfo() {
        System.out.println("[Programmer] " + id + " - " + fullName + " (" + role + ")");
    }
}
