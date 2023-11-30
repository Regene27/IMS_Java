package IMS_src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class UserRegister {

    public static void main(String[] args) {
        String username;
        String password;
        String name;
        String role;

        Scanner sc = new Scanner(System.in);

        String filePath = "authorized_users.txt";

        boolean user = true;

        do {

            System.out.print("Input Username: ");
            username = sc.nextLine();

            if (isUsernameExists(username, filePath)) {
                System.out.println("Username already exists. Please choose a different username.");
                continue;
            }

            System.out.print("Input Password: ");
            password = sc.nextLine();

            System.out.print("Input Name: ");
            name = sc.nextLine();

            role = "base_user";

            String WriteContent = username + ":" + password + ":" + name + ":" + role;
            System.out.println("Your username is " + username);
            System.out.println("Your password is " + password);
            user = false;

            try {
                FileWriter fileWriter = new FileWriter(filePath, true);
                fileWriter.write("\n" + WriteContent);
                fileWriter.close();
                System.out.println("User is registered successfully!");
            } catch (IOException e) {
                System.err.println("Error writing to the file: " + e.getMessage());
            }
        } while (user == true);

        sc.close();
    }

    private static boolean isUsernameExists(String username, String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] user = line.split(":");
                if (user.length > 0 && user[0].equals(username)) {
                    reader.close();
                    return true;
                }
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
        return false;
    }
}
