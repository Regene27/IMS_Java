package InventoryManagementSystem.IMS_src;

import java.util.Scanner;

public class UserAuth {

    public UserAuth() {

        Scanner scanner = new Scanner(System.in);

        Admin admin = new Admin("admin", "12345678", "admin", "admin");
        User user = new User("user", "12345678", "User");

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (username.equals(admin.username) && password.equals(admin.password)) {
            System.out.println("Welcome, " + admin.getName() + "!");
            CurrentUser.setCurrentUser(admin);
        } else if (username.equals(user.username) && password.equals(user.password)) {
            System.out.println("Welcome, " + user.getName() + "!");
            CurrentUser.setCurrentUser(user);
        } else {
            System.out.println("Invalid username or password.");
            System.exit(0);
        }

        scanner.close();
    }
}
