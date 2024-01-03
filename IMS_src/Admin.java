package IMS_src;

import java.util.ArrayList;
import java.util.List;

public class Admin extends User {
    protected String role;

    public Admin(String username, String password, String name) {
        super(username, password, name, "admin");
        admins.add(this);
    }

    // Overriding Method
    @Override
    public String getRole() {
        return "admin";
    }

    public void editUserInfo(String username, String password, String name, String role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.role = role;
    }

    @Override
    public void removeUser() {
        BaseUser.displayUsers();
        System.out.print("Enter the username of the user you want to remove: ");

        String username = System.console().readLine();
        User user = BaseUser.findUser(username);
        if (user == null) {
            System.out.println("User not found");
            return;
        } else if ("admin".equals(user.getRole())) {
            System.out.println("You cannot remove an admin");
            return;
        } else {
            BaseUser.removeUser(user);
            System.out.println("User removed successfully");
        }
    }

    public String toString() {
        return "Username: " + this.username + "\nRole: " + this.role + "\nName: " + this.name;
    }
}
