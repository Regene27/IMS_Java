package InventoryManagementSystem.IMS_src;

import java.util.ArrayList;
import java.util.List;

public class Admin extends User {

    protected static List<Admin> admins = new ArrayList<>();

    public String getName() {
        return name;
    }

    public Admin(String username, String password, String name, String role) {
        super(username, password, name, "admin");
        admins.add(this);
    }

    public void editUserInfo(String username, String password, String name, String role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.role = role;
    }

    public static void removeUser() {
        // if (!isAdmin()) {
        // System.out.println("You do not have permission to remove a user.");
        // return;
        // }

        User.displayUsers();
        System.out.print("Enter the username of the user you want to remove: ");

        String username = System.console().readLine();
        User user = User.findUser(username);
        if (user == null) {
            System.out.println("User not found");
            return;
        } else if ("admin".equals(user.getRole())) {
            System.out.println("You cannot remove an admin");
            return;
        } else {
            User.remove(user);
            System.out.println("User removed successfully");
        }
    }
}
