package IMS_src;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserAuth {

    public static List<User> getUserList() {
        List<User> userList = new ArrayList<>();

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
                }

                User user = new BaseUser(username, password, name);
                userList.add(user);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return userList;
    }

    public static List<Admin> getAdminList() {
        List<Admin> adminList = new ArrayList<>();

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
                }

                Admin admin = new Admin(username, password, name);
                adminList.add(admin);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return adminList;
    }

    public UserAuth() {
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
                break;
            }
        }

        if (!found) {
            for (Admin admin : adminList) {
                if (username.equals(admin.username) && password.equals(admin.password)) {
                    System.out.println("Welcome, " + admin.getName() + "!");
                    found = true;
                    break;
                }
            }
        }

        if (!found) {
            System.out.println("Invalid username or password.");
            System.exit(0);
        }

        scanner.close();
    }
}
