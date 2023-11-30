package IMS_src;

import java.util.ArrayList;
import java.util.List;

public class BaseUser extends User {
    protected String role;

    // users.add(this); // Add the user to the list when created
    // }

    public BaseUser(String username, String password, String name) {
        super(username, password, name, "base_user");
        users.add(this); // Add the user to the list when created
    }

    public String getName() {
        return super.name;
    }

    public String getRole() {
        return role;
    }

    public static void remove(User user) {
        User.users.remove(user);
    }

    public static User findUser(String username) {
        for (User user : User.users) {
            if (user.username.equals(username)) {
                return user;
            }
        }
        return null;
    }

    public static void displayUsers() {
        for (int i = 0; i < User.users.size(); i++) {
            User user = User.users.get(i);
            if ("admin".equals(user.getRole())) {
                continue;
            }
            System.out.println((i + 1) + ". Username: " + user.username);
        }
    }
}