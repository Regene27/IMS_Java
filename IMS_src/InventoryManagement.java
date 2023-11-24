package InventoryManagementSystem.IMS_src;

import java.io.*;
import java.util.*;

public class InventoryManagement {
    private static final String FILE_PATH = "sale_order.txt";
    private static final String REPORT_FILE_PATH = "sale_order_report.txt";
    private List<Product> products;
    private List<String> removedProductsReport;
    private double totalPrice;

    public InventoryManagement() {
        products = new ArrayList<>();
        loadInventoryData();
        removedProductsReport = new ArrayList<>();
        totalPrice = 0.0;
    }

    public void loadInventoryData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String name = parts[0];
                    double price = Double.parseDouble(parts[1]);
                    int quantity = Integer.parseInt(parts[2]);
                    products.add(new Product(name, price, quantity));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveInventoryData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Product product : products) {
                writer.write(product.getName() + "," + product.getPrice() + "," + product.getQuantity() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void displayProducts() {
        if (products.size() == 0) {
            System.out.println("No product listed.");
        } else {
            for (int i = 0; i < products.size(); i++) {
                System.out.println((i + 1) + ". " + products.get(i));
            }
        }
    }

    public void addProduct(String name, double price, int quantity) {
        products.add(new Product(name, price, quantity));
        saveInventoryData();
    }

    public void displayRemovedProductsReport() {
        if (removedProductsReport.isEmpty()) {
            System.out.println("No products have been removed.");
        } else {
            System.out.println("Sold Products Report:");
            for (String reportEntry : removedProductsReport) {
                System.out.println(reportEntry);
            }
            System.out.println("Total Price of all Products Sold: $" + totalPrice);
            saveReportToFile();
        }
    }

    public void addRemovedProductToReport(String productName, int amountRemoved, double price) {
        double removedTotalPrice = amountRemoved * price;
        removedProductsReport
                .add(productName + " - Amount Sold: " + amountRemoved + " - Total Price: $" + removedTotalPrice);
        totalPrice += removedTotalPrice;
    }

    public void removeProduct() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select a product to remove:");
        displayProducts();
        System.out.print("Enter the index of the product to remove: ");
        int index = scanner.nextInt() - 1;

        if (index >= 0 && index < products.size()) {
            Product product = products.get(index);
            System.out.println("Selected Product: " + product);
            System.out.print("Confirm to remove product(y): ");
            String confirmation = scanner.next();

            if (confirmation.equals("y")) {
                products.remove(index);
                saveInventoryData();
                System.out.println(product.getName() + " removed.");
            }
        } else {
            System.out.println("Invalid product index.");
        }
        scanner.close();
    }

    public void addStock() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select a product to add stock:");
        displayProducts();
        System.out.print("Enter the index of the product to add stock: ");
        int index = scanner.nextInt() - 1;

        if (index >= 0 && index < products.size()) {
            Product product = products.get(index);
            System.out.println("Selected Product: " + product);
            System.out.print("Enter the amount to add: ");
            int amountToAdd = scanner.nextInt();

            if (amountToAdd >= 0) {
                product.setQuantity(product.getQuantity() + amountToAdd);
                saveInventoryData();
                System.out.println(amountToAdd + " " + product.getName() + " added to stock.");
                scanner.nextLine();
            } else {
                System.out.println("Invalid amount. Amount to add should be greater than or equal to 0.");
            }
        } else {
            System.out.println("Invalid product index.");
        }
        scanner.close();
    }

    public void saveReportToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(REPORT_FILE_PATH))) {
            writer.write("Removed Products Report:\n");
            for (String reportEntry : removedProductsReport) {
                writer.write(reportEntry + "\n");
            }
            writer.write("Total Price of all Products Sold: $" + totalPrice);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void clearConsole() {
        try {
            new ProcessBuilder("clear").inheritIO().start().waitFor();
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    private static User getCurrentUser() {
        return CurrentUser.getCurrentUser();
    }

    private static void setCurrentUser(User user) {
        CurrentUser.setCurrentUser(user);
    }

    private static void clearCurrentUser() {
        CurrentUser.clearCurrentUser();
    }

    public static void displayMenu() {
        System.out.println("=".repeat(100));
        System.out.println("Sale Order Management System");
        System.out.println("1. List Products");
        System.out.println("2. Add Product");
        System.out.println("3. Remove Product");
        System.out.println("4. Add Stock");
        System.out.println("5. View Removed Products Report");
        System.out.println("6. Remove User");
        System.out.println("7. Exit");
        System.out.print("Enter your choice: ");
    }

    public static void main(String[] args) {

        User user1 = new User("user1", "12345678", "User");
        User user2 = new User("user2", "12345678", "User");
        User admin = new User("admin", "12345678", "admin", "admin");

        new UserAuth();
        int choice = 0;

        InventoryManagement system = new InventoryManagement();
        Scanner scanner = new Scanner(System.in);

        do {
            displayMenu();

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();

                scanner.nextLine();

                switch (choice) {
                    case 1:
                        clearConsole();
                        system.displayProducts();
                        System.out.println("=".repeat(100));
                        break;
                    case 2:
                        clearConsole();
                        System.out.print("Enter product name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter product price: ");
                        double price = scanner.nextDouble();
                        System.out.print("Enter product quantity: ");
                        int quantity = scanner.nextInt();
                        system.addProduct(name, price, quantity);
                        System.out.println("=".repeat(100));
                        break;
                    case 3:
                        clearConsole();
                        system.removeProduct();
                        System.out.println("=".repeat(100));
                        break;
                    case 4:
                        clearConsole();
                        system.addStock();
                        System.out.println("=".repeat(100));
                        break;
                    case 5:
                        clearConsole();
                        system.displayRemovedProductsReport();
                        System.out.println("=".repeat(100));
                        break;
                    case 6:
                        clearConsole();
                        Admin.removeUser();
                        System.out.println("=".repeat(100));
                        break;
                    case 7:
                        clearConsole();
                        System.out.println("Exiting the Sale Order Management System.");
                        System.out.println("=".repeat(100));
                        scanner.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                        System.out.println("=".repeat(100));
                        break;
                }
            } else {
                System.out.println("Invalid choice. Please enter a valid option.");
                System.out.println("=".repeat(100));
            }

        } while (choice >= 1 && choice <= 7);

        scanner.close();
    }
}
