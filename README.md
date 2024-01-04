# Inventory Management System

The Inventory Management System (IMS) is a robust software solution designed to streamline and optimize the processes involved in managing inventory, sales, and reporting. This project is hosted on GitHub to provide version control, collaboration, and transparency throughout its development.	

The primary purpose of the Inventory Management System is to offer businesses a comprehensive tool for efficiently handling their inventory-related operations. This includes tracking stock levels, managing products and sales and ensuring data accuracy.


## Table of Content

1. [USER](#user)
   - 1.1 [Field](#11-field)
   - 1.2 [Constructor](#12-constructor)
   - 1.3 [Method](#13-method)
   
2. [Product](#product)
   - 2.1 [Field](#21-field)
   - 2.2 [Constructor](#22-constructor)
   - 2.3 [Methods](#23-methods)
     
3. [OOP-Concept](#object-oriented-concept)
   - 3.1 [Class and Object](#31-class-and-object)
   - 3.2 [Inheritance](#32-inheritance)
   - 3.3 [Polymorphism](#33-polymorphism)
   - 3.4 [Encapsulation](#34-encapsulation)
   - 3.5 [Abstraction](#35-abstraction)
   - 3.6 [Exception Handling](#36-exception-handling)
   - 3.7 [File I/O](#37-file-I/O)
   - 3.8 [Lamda Expression](#38-lamda-expression)
   - 3.9 [Static Method](#39static-methods)

## USER
  The User class is an abstract base class that represents users within the system. It provides a blueprint for both regular users and administrators.
  
  ### 1.1 Field
  
 ```java
    protected String username;
    protected String password;
    protected String name;
    protected String role;

    protected static User currentUser;

    protected static List<User> users = new ArrayList<>();
    protected static List<Admin> admins = new ArrayList<>();
```

  ### 1.2 Constructor
  
```java
   public User(String username, String password, String name, String role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.role = role;
    }
```

```java
   public BaseUser(String username, String password, String name, String role) {
        super(username, password, name, "base_user");
        users.add(this);
   }
```

```java
    public Admin(String username, String password, String name, String role) {
        super(username, password, name, "admin");
        admins.add(this);
    }
```
  
  ### 1.3 Methods
  ```java
    public abstract String getRole();

    protected abstract void removeUser();

    public String getUsername() {
        return this.username;
    }

    public String getName() {
        return this.name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public static void clearCurrentUser() {
        currentUser = null;
    }
   ```

  
## Product

The product class is a superclass used to store all types of products in the inventory, with a subclass of SaleItem to represent each item in the sale order/cart.

### 2.1 Field

```java
    private String name;
    protected double price;
    private int stock;
```

### 2.2 Constructor

```java
    public Product(String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
```

```java
    public SaleItem(String name, double price, int quantity) {
        super(name, price);
        this.quantity = quantity;
    }
```

### 2.3 Methods

```java
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return name + " - Price: $" + price + " - Stock: " + stock;
    }
```

```java
    public double getTotalPrice() {
        return quantity * getPrice();
    }

    public int getAmount() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + getName() + '\'' +
                ", price=" + getPrice() +
                ", quantity=" + quantity +
                ", total price=" + getTotalPrice() + "}";
    }
```

## Object Oriented Concepts

### 1. Class and Objects
- #### SuperClass: User
  - ##### Subclass: BaseUser and Admin
- #### SuperClass: Product
  - ##### Subclass: SaleItem
- #### Inventory Management System
  
### 2. Inheritance

- #### Overloading Methods

```java
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
```
  
- #### Overriding Methods

##### User Class
     
 ```java
    protected abstract void removeUser();
```

##### Admin Class

```java
    @Override
    public void removeUser() {
        BaseUser.displayUsers();
        System.out.print("Enter the username of the user you want to remove: ");

        String username = System.console().readLine();
        User user = BaseUser.findUser(username);
        if (user == null) {
            System.out.println("User not found");
            return;
        } else if ("admin".equals(user.getRole())) {
            System.out.println("You cannot remove an admin");
            return;
        } else {
            BaseUser.removeUser(user);
            System.out.println("User removed successfully");
        }
    }
```

##### BaseUser Class

```java
    @Override
    public void removeUser() {
        users.remove(this);
        TextFileUtils.removeUserInfoFromFile("authorized_users.txt", this.getUsername());
        System.out.println("User removed successfully");
    }
```
  
### 3. Polymorphism

```java
    public String toString() {
        return "Username: " + this.username + "\nRole: " + this.role + "\nName: " + this.name;
    }
```

```java
    public String toString() {
        return name + " - Price: $" + price + " - Stock: " + stock;
    }
```

```java
    public String toString() {
        return "Product{" +
                "name='" + getName() + '\'' +
                ", price=" + getPrice() +
                ", quantity=" + quantity +
                ", total price=" + getTotalPrice() + "}";
    }
```

### 4. Encapsulation

```java
    private String name;
    protected double price;
    private int stock;
```

 ```java
    protected String username;
    protected String password;
    protected String name;
    protected String role;
```

### 5. Abstraction

```java
    public abstract class User {
       public abstract String getRole();
   
       protected abstract void removeUser();
    }
```

### 6. Exception Handling

#### Error Handling for when Sale Order amount is larger than Inventory Stock

```java
       try {
            // Lambda Expression
            saleItems.forEach(item -> System.out
                    .println(
                            item.getAmount() + " " + item.getName() + " - $" + item.getPrice() + " each" + " - Total: $"
                                    + item.getTotalPrice()));

            System.out.print("\nDo you want to confirm the sale? (yes/no): ");
            String confirm = scanner.next().toLowerCase();

            if (confirm.equals("yes")) {
                for (SaleItem item : saleItems) {

                    Product product = findProductByName(item.getName());
                    if (product != null) {
                        if (product.getStock() < item.getAmount()) {
                            throw new InsufficientStockException("Not enough stock for " + item.getName());
                        }
                        product.setStock(product.getStock() - item.getAmount());
                    }

                }

                saveInventoryData();

                displaySaleReport(saleItems);

                saveReportToFile(saleItems);

            } else {
                System.out.println("Sale canceled. Inventory remains unchanged.");
            }
        } catch (InsufficientStockException e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }
```

#### IOException Handling for when Sale Order reciept/report not saved
```java
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
```

### 7. File I/O

- #### authorized_users.txt
  text file containing all information of all users, including admins and base_users.

- #### inventory.txt
  text file containing all informations of all current products in the inventory, such as name, price and stock amount.

- #### receipts/SaleOrder_(time_issued).txt
  In receipts folder, contains each sale order receipt/report with the datetime when issued.
  
### 8. Lamda Expression and Inner Class

```java
    saleItems.forEach(item -> System.out.println(item.getAmount() + " " + item.getName() + " - $" + item.getPrice()
             + " each" + " - Total: $"+ item.getTotalPrice()));
```

```java
    public class InventoryManagementSystem {
       public static void generateSaleOrder() {
              class InsufficientStockException extends RuntimeException {
                  public InsufficientStockException(String message) {
                      super(message);
                  }
              }
       }
    }
```

### 9. Static Methods

```java
    public static void saveInventoryData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Product product : products) {
                writer.write(product.getName() + "," + product.getPrice() + "," + product.getStock() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
```




     

