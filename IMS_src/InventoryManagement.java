package IMS_src;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class InventoryManagement {
    private static final String FILE_PATH = "inventory.txt";
    private static final String REPORT_FILE_PATH = "sale_order_report.txt";
    private static List<Product> products;
    private static List<String> removedProductsReport;
    private static double totalPrice;

    public InventoryManagement() {
        products = new ArrayList<>();
        loadInventoryData();
        removedProductsReport = new ArrayList<>();
        totalPrice = 0.0;
    }

    // Exceptions
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

    // Exceptions
    public static void saveInventoryData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Product product : products) {
                writer.write(product.getName() + "," + product.getPrice() + "," + product.getQuantity() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void displayProducts() {
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

    public static void generateSaleOrder() {
        Scanner scanner = new Scanner(System.in);

        List<SaleItem> saleItems = new ArrayList<>();

        displayProducts();

        boolean continueSale = true;

        while (continueSale) {
            System.out.print("Enter the index of the product to sell (or -1 to finish): ");
            int index = scanner.nextInt();

            if (index == -1) {
                continueSale = false;
            } else if (index >= 1 && index <= products.size()) {
                Product product = products.get(index - 1);
                System.out.println("Selected Product: " + product);

                System.out.print("Enter the amount to sell: ");
                int amountToSell = scanner.nextInt();

                if (amountToSell >= 0) {
                    SaleItem saleItem = new SaleItem(product.getName(), product.getPrice(), amountToSell);
                    saleItems.add(saleItem);
                    System.out.println(amountToSell + " " + product.getName() + " added.");
                    System.out.println("=".repeat(100));
                } else {
                    System.out.println("Invalid amount. Amount to add should be greater than or equal to 0.");
                    System.out.println("=".repeat(100));
                }
            } else {
                System.out.println("Invalid product index.");
            }
        }

        // Lambda Expression
        saleItems.forEach(item -> System.out
                .println(item.getAmount() + " " + item.getName() + " - $" + item.getPrice() + " each" + " - Total: $"
                        + item.getTotalPrice()));

        System.out.print("\nDo you want to confirm the sale? (yes/no): ");
        String confirm = scanner.next().toLowerCase();

        if (confirm.equals("yes")) {
            for (SaleItem item : saleItems) {
                Product product = findProductByName(item.getName());
                if (product != null) {
                    product.setQuantity(product.getQuantity() - item.getAmount());
                }
            }

            saveInventoryData();

            displaySaleReport(saleItems);

            saveReportToFile(saleItems);

        } else {
            System.out.println("Sale canceled. Inventory remains unchanged.");
        }
    }

    private static Product findProductByName(String productName) {
        for (Product product : products) {
            if (product.getName().equals(productName)) {
                return product;
            }
        }
        return null;
    }

    private static void displaySaleReport(List<SaleItem> saleItems) {
        System.out.println("\nSale Report:");
        for (SaleItem item : saleItems) {
            System.out.println(
                    item.getAmount() + " " + item.getName() + " - $" + item.getPrice() + " each" + " - Total: $"
                            + item.getTotalPrice());
        }
    }

    private static void saveReportToFile(List<SaleItem> saleItems) {
        String folder = "receipts/";
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = folder + "SaleReport_" + timeStamp + ".txt";

        // Exceptions
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("Sale Report:\n");
            for (SaleItem item : saleItems) {
                writer.write(
                        item.getAmount() + " " + item.getName() + " - $" + item.getPrice() + " each" + " - Total: $"
                                + item.getTotalPrice() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error saving sale report to file: " + e.getMessage());
        }
    }

    public static void addRemovedProductToReport(String productName, int amountRemoved, double price) {
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
    }

    // Exceptions
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

    public static void displayMenu() {
        System.out.println("=".repeat(100));
        System.out.println("Sale Order Management System");
        System.out.println("1. List Products");
        System.out.println("2. Generate Sale Order");
        System.out.println("3. Add Stock");
        System.out.println("4. View Removed Products Report");
        System.out.println("5. Edit User Info");
        System.out.println("6. Exit\n");
        if (User.getCurrentUser().getRole().equals("admin")) {
            System.out.println("7. Add Product");
            System.out.println("8. Remove Product");
            System.out.println("9. Remove User\n");
        }
        System.out.print("Enter your choice: ");
    }

    public static void main(String[] args) {

        InventoryManagement system = new InventoryManagement();

        new UserLogin();
        int choice = 0;

        Scanner scanner = new Scanner(System.in);

        do {
            displayMenu();

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();

                scanner.nextLine();

                switch (choice) {
                    case 1:
                        clearConsole();
                        displayProducts();
                        System.out.println("=".repeat(100));
                        break;
                    case 2:
                        clearConsole();
                        generateSaleOrder();
                        System.out.println("=".repeat(100));
                        break;
                    case 7:
                        if (User.getCurrentUser().getRole().equals("admin")) {
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
                        }
                    case 8:
                        if (User.getCurrentUser().getRole().equals("admin")) {
                            clearConsole();
                            system.removeProduct();
                            System.out.println("=".repeat(100));
                            break;
                        }
                    case 3:
                        clearConsole();
                        system.addStock();
                        System.out.println("=".repeat(100));
                        break;
                    case 4:
                        clearConsole();
                        system.displayRemovedProductsReport();
                        System.out.println("=".repeat(100));
                        break;
                    case 5:
                        clearConsole();
                        UserLogin.editCurrentUserInfo();
                    case 9:
                        if (User.getCurrentUser().getRole().equals("admin")) {
                            clearConsole();
                            Admin.removeUser();
                            System.out.println("=".repeat(100));
                            break;
                        }
                    case 6:
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
