package InventoryManagementSystem.IMS_src;

import java.util.ArrayList;
import java.util.List;

public class User {
    protected String username;
    protected String password;
    protected String name;
    protected String role;

    protected static List<User> users = new ArrayList<>();

    public User(String username, String password, String name, String role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.role = role;
        users.add(this); // Add the user to the list when created
    }

    public User(String username, String password, String name) {
        this(username, password, name, "base_user");
    }

    public String getName() {
        return name;
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
            System.out.println((i + 1) + ". Username: " + user.username + " -- role: " + user.role);
        }
    }

    // protected static boolean isAdmin() {
    // return currentUser != null && "admin".equals(currentUser.getRole());
    // }
    // }

    public class CurrentUser {
        private static User currentUser;

        public static User getCurrentUser() {
            return currentUser;
        }

        public static void setCurrentUser(User user) {
            currentUser = user;
        }

        public static void clearCurrentUser() {
            currentUser = null;
        }
    }
}
