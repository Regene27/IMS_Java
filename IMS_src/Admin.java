package InventoryManagementSystem.IMS_src;

public class Admin extends User {
    private String access;

    public Admin(String username, String password, String name, String role, String access) {
        super(username, password, name, role);
        this.access = access;
    }
}
