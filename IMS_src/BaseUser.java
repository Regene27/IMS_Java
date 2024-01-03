package IMS_src;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BaseUser extends User {
    protected String role;

    public BaseUser(String username, String password, String name) {
        super(username, password, name, "base_user");
        users.add(this);
    }

    // Overriding Method
    @Override
    public String getRole() {
        return "base_user";
    }

    @Override
    public void removeUser() {
        users.remove(this);
        TextFileUtils.removeUserInfoFromFile("authorized_users.txt", this.getUsername());
        System.out.println("User removed successfully");
    }

    public static void removeUser(User user) {
        User.users.remove(user);
        TextFileUtils.removeUserInfoFromFile("authorized_users.txt", user.getUsername());
    }

    public class TextFileUtils {
        public static void removeUserInfoFromFile(String filePath, String username) {
            List<String> lines = new ArrayList<>();

            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (!line.contains(username)) {
                        lines.add(line);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                for (String line : lines) {
                    writer.write(line);
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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

    public String toString() {
        return "Username: " + this.username + "\nRole: " + this.role + "\nName: " + this.name;
    }

    public static void remove(User user) {
    }
}