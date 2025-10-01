
import java.util.ArrayList;
import java.util.List;

// Composite
public class Department implements Staff {
    private final List<Staff> staffMembers = new ArrayList<>();

    @Override
    public void displayInfo() {
        for (Staff s : staffMembers) {
            s.displayInfo();
        }
    }

    public void addStaff(Staff s) {
        staffMembers.add(s);
    }

    public void removeStaff(Staff s) {
        staffMembers.remove(s);
    }
}
