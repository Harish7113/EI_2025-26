

// Leaf
public class TeamLead implements Staff {
    private final String fullName;
    private final long id;
    private final String role;

    public TeamLead(long id, String fullName, String role) {
        this.id = id;
        this.fullName = fullName;
        this.role = role;
    }

    @Override
    public void displayInfo() {
        System.out.println("[TeamLead] " + id + " - " + fullName + " (" + role + ")");
    }
}
