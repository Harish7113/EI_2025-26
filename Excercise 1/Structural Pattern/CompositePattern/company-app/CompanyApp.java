

// Client
public class CompanyApp {
    public static void main(String[] args) {
        Staff p1 = new Programmer(100, "John", "Senior Developer");
        Staff p2 = new Programmer(101, "Jane", "Junior Developer");

        Staff lead1 = new TeamLead(200, "Alice", "Project Manager");
        Staff lead2 = new TeamLead(201, "Bob", "Product Manager");

        Department devDept = new Department();
        devDept.addStaff(p1);
        devDept.addStaff(p2);

        Department mgmtDept = new Department();
        mgmtDept.addStaff(lead1);
        mgmtDept.addStaff(lead2);

        Department company = new Department();
        company.addStaff(devDept);
        company.addStaff(mgmtDept);

        System.out.println("=== Company Structure ===");
        company.displayInfo();
    }
}
