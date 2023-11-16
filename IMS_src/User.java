package InventoryManagementSystem.IMS_src;

public class User {
    protected String username;
    protected String password;
    protected String name;
    protected String role;

    public User(String username, String password, String name, String role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.role = role;
    }
}
