package IMS_src;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserLogin {

    public static List<User> getUserList() {
        List<User> userList = new ArrayList<>();

        // Exceptions
        try {
            File file = new File("authorized_users.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(":");

                String username = parts[0];
                String password = parts[1];
                String name = parts[2];
                String role = parts[3];

                if (!"base_user".equals(role)) {
                    continue;
                } else {
                    BaseUser baseUser = new BaseUser(username, password, name);
                    userList.add(baseUser);
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return userList;
    }

    public static List<Admin> getAdminList() {
        List<Admin> adminList = new ArrayList<>();

        // Exceptions
        try {
            File file = new File("authorized_users.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(":");

                String username = parts[0];
                String password = parts[1];
                String name = parts[2];
                String role = parts[3];

                if (!"admin".equals(role)) {
                    continue;
                } else {
                    Admin admin = new Admin(username, password, name);
                    adminList.add(admin);
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return adminList;
    }

    public UserLogin() {
        Scanner scanner = new Scanner(System.in);

        List<User> userList = getUserList();
        List<Admin> adminList = getAdminList();

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        boolean found = false;

        for (User user : userList) {
            if (username.equals(user.username) && password.equals(user.password)) {
                System.out.println("Welcome, " + user.getName() + "!");
                found = true;
                User.setCurrentUser(user);
                break;
            }
        }

        if (!found) {
            for (Admin admin : adminList) {
                if (username.equals(admin.username) && password.equals(admin.password)) {
                    System.out.println("Welcome, " + admin.getName() + "!");
                    found = true;
                    User.setCurrentUser(admin);
                    break;
                }
            }
        }

        if (!found) {
            System.out.println("Invalid username or password.");
            System.exit(0);
        }
    }

    public static void editCurrentUserInfo() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("New username: ");
        String newUsername = scanner.nextLine();

        System.out.print("New password: ");
        String newPassword = scanner.nextLine();

        System.out.print("New name: ");
        String newName = scanner.nextLine();

        if (User.currentUser.getRole().equals("admin")) {
            System.out.print("Can't edit admin info.");

            scanner.close();
            return;
        } else {
            BaseUser baseUser = (BaseUser) User.currentUser;
            baseUser.setUsername(newUsername);
            baseUser.setPassword(newPassword);
            baseUser.setName(newName);
        }

        try {
            File file = new File("authorized_users.txt");
            List<String> lines = new ArrayList<>();

            Scanner scannerFile = new Scanner(file);
            while (scannerFile.hasNextLine()) {
                String line = scannerFile.nextLine();
                String[] parts = line.split(":");

                if (parts[0].equals(User.currentUser.getUsername())) {
                    line = newUsername + ":" + newPassword + ":" + newName + ":" + parts[3];
                }

                lines.add(line);
            }
            scannerFile.close();

            FileWriter writer = new FileWriter(file);
            for (String line : lines) {
                writer.write(line + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("User info updated successfully.");
    }

    public void deleteCurrentUser() {
        if (User.currentUser.getRole().equals("base_user")) {
            List<User> userList = getUserList();
            userList.remove(User.currentUser);
        } else if (User.currentUser.getRole().equals("admin")) {
            System.out.print("Can't delete admin.");
        }

        System.out.println("User deleted successfully.");
    }

    public static void main(String[] args) {
        new UserLogin();
    }
}
