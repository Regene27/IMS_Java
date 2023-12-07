package IMS_src;

import java.util.ArrayList;
import java.util.List;

public class Admin extends User {
    protected String role;

    public String getName() {
        return name;
    }

    public Admin(String username, String password, String name) {
        super(username, password, name, "admin");
        admins.add(this);
    }

    public String getRole() {
        return "admin";
    }

    public String getUsername() {
        return super.username;
    }

    public void editUserInfo(String username, String password, String name, String role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.role = role;
    }

    public static void removeUser() {

        // if (user.getRole().equals("base_user")) {
        // System.out.println("You cannot remove users.");
        // return;
        // }

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
            BaseUser.remove(user);
            System.out.println("User removed successfully");
        }
    }
}
