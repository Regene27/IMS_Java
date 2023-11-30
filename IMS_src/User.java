package IMS_src;

import java.util.ArrayList;
import java.util.List;

public abstract class User {
    protected String username;
    protected String password;
    protected String name;

    protected static List<User> users = new ArrayList<>();
    protected static List<Admin> admins = new ArrayList<>();

    public User(String username, String password, String name, String string) {
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public abstract String getName();

    public abstract String getRole();
}

// protected static boolean isAdmin() {
// return currentUser != null && "admin".equals(currentUser.getRole());
// }
// }

// public class CurrentUser implements User {
// private static User currentUser;

// public static User getCurrentUser() {
// return currentUser;
// }

// public static void setCurrentUser(User user) {
// currentUser = user;
// }

// public static void clearCurrentUser() {
// currentUser = null;
// }
// }