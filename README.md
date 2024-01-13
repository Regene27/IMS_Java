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
     
3. [OOP-Concept](#object-oriented-concepts)
   - 3.1 [Class and Object](#1-class-and-objects)
   - 3.2 [Inheritance](#2-inheritance)
   - 3.3 [Polymorphism](#3-polymorphism)
   - 3.4 [Encapsulation](#4-encapsulation)
   - 3.5 [Abstraction](#5-abstraction)
   - 3.6 [Exception Handling](#6-exception-handling)
   - 3.7 [File I/O](#7-file-I/O)
   - 3.8 [Lamda Expression](#8-lamda-expression)
   - 3.9 [Static Method](#9static-methods)

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
Inheritance is used to create a hierarchy of classes. In this Inventory management system, there are two superclasses:
* User extended to subclasses which are BaseUser and Admin. We use inheritance in this case because both Admin and BaseUser store the informations as in Users but they also have their own attributes. Therefore, we want to specified its own attribute in subclasses. It is also useful for code struture and reducability. 
* Product that extended to SaleItem as its subclass. We use inheritance because we want to avoid long line of code and only focus on the specified information in SaleItem.

- #### Overloading Methods
Method overloading allows you to define multiple methods with the same name but different parameter types.
In this case, by overloading the removeUser method, the code becomes more readable and self-explanatory. The parameter-less users.remove(this) is used when we want to remove the current user. While, User.users.remove(user) is used to remove some specific users.

```java
    
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

##### Admin Class
removeUser method is part of a subclass that extends another class named BaseUser. By using @Override, the code is indicating that this method is intended to override a method with the same signature in the superclass. If there is no such method in the superclass, or if the signature doesn't match, the compiler will raise an error.

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
The @Override annotation is used to declare and enforce that the removeUser method in the current class is intended to override a method in a superclass or implement a method from an interface. This practice helps improve code quality, maintainability, and reduces the likelihood of accidental errors.

```java
    @Override
    public void removeUser() {
        users.remove(this);
        TextFileUtils.removeUserInfoFromFile("authorized_users.txt", this.getUsername());
        System.out.println("User removed successfully");
    }
```
  
### 3. Polymorphism

#### Casting

* We create admin and base_user as normal user first, then cast it into each role that we want the user to have. 

```java
   User admin = new Admin(username, password, name);
   adminList.add((Admin) admin);
```

* We need to cast the user into Admin to add the user into Admin list because since we have two array lists of users, which is normal users list(base_user) and admin list.

### 4. Encapsulation
* In Product, we use private and protected as the encapsulation. the use of "private"and "protected" access modifiers in Product class exemplifies encapsulation by controlling the visibility and access to the internal state of the class. This ensures that the class's internal details are hidden, providing a clean and well-defined interface for interacting with objects of the Product class.

```java
    private String name;
    protected double price;
    private int stock;
```

* In User class, all the attributes (username, password, name) are declared as protected, making them accessible to both the class itself and its subclasses. The use of protected allows subclasses (like Admin class) to inherit and access these attributes. The protected modifier provides a level of encapsulation, allowing subclasses to work with these variables while still controlling access from classes outside the package. Also, This promotes a modular and organized design, making it easier to understand and maintain the code.


 ```java
    protected String username;
    protected String password;
    protected String name;
    protected String role;
```

* In Product class, we have methods that are set to public, because we want to access it from other classes. We want to access the name, price, and stocks of each product from everywhere.

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
```

### 5. Abstraction
Abstract class is a class that cannot be instantiated on its own; it is meant to be subclassed by concrete (non-abstract) classes.

In this case, Abstraction is about defining a common interface and hiding the implementation details. The User class provides a blueprint for creating various types of users (which can be concrete subclasses), and it enforces that each subclass must implement certain methods. This helps in creating a hierarchy of classes that share common characteristics while allowing for specific implementations in each subclass.

The getRole() and removeUser() methods are declared as abstract methods in the User class. Abstract methods are methods that are declared without an implementation in the abstract class and must be implemented by any concrete subclass.

```java
    public abstract class User {
    }
```

```java
    public abstract String getRole();
   
    protected abstract void removeUser();
```

### 6. Exception Handling

#### Error Handling for when Sale Order amount is larger than Inventory Stock
In this context, exception handling is used to manage a potential InsufficientStockException.
The try block contains the code that may potentially throw an exception. It involves processing sale items, checking stock availability, and updating the inventory. Inside the try block, if the stock for a particular product is insufficient (product.getStock() < item.getAmount()), an InsufficientStockException is thrown. This is done using the throw new InsufficientStockException("Not enough stock for " + item.getName()); statement. The catch block immediately follows the try block and specifies how to handle the exception if it occurs. In this case, it catches an InsufficientStockException. If an InsufficientStockException is caught, the catch block executes, printing an error message (System.out.println("Error: " + e.getMessage());). Additionally, the return; statement indicates that the method should exit after handling the exception.


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

In this case, exception handling is used to manage potential IOExceptions that might occur during file operations.

Inside the try block, a BufferedWriter is created to write data to a file (new BufferedWriter(new FileWriter(fileName))). The code then writes the sale report data to the file within the same try block. If an IOException occurs during the file-writing process, the catch block is executed. The catch block prints the stack trace (e.printStackTrace()) and displays an error message (System.err.println("Error saving sale report to file: " + e.getMessage());).

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
- #### Lamda Expression
Lambda expressions are used in conjunction with the forEach method to iterate over a collection of SaleItem objects (saleItems) and specify the behavior for each element, resulting in more readable and expressive code.

The lambda expression is used within the forEach method to iterate over each element (item) in the saleItems collection. The lambda body contains a code block ({ ... }) that defines the actions to be performed for each SaleItem. The code block prints details such as the quantity, name, price per unit, and total price for each SaleItem to the console.

```java
    saleItems.forEach(item -> System.out.println(item.getAmount() + " " + item.getName() + " - $" + item.getPrice()
             + " each" + " - Total: $"+ item.getTotalPrice()));
```
- #### Inner Class
In this case, inner class is used to allows for better encapsulation of the exception and ties it closely to the logic of the generateSaleOrder method. It can enhance code organization and readability while limiting the visibility of the exception to the specific context where it is relevant.  

In Inventory Management System, an instance of InsufficientStockException can be created when an insufficient stock condition is detected during the sale order generation process.
Inner classes have access to the members of the enclosing class. If needed, InsufficientStockException can access methods or fields of the InventoryManagementSystem class. This allows InsufficientStockException to access private fields or methods of InventoryManagementSystem if required.

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

Static method allows for the encapsulation of utility functionality related to saving inventory data. It provides a convenient way to perform operations without requiring the instantiation of an object.

In this case, since the method is static, it can be called using the class name, without creating an instance of the class. Also, it performs file I/O operations to save the inventory data. It uses a try-with-resources statement to automatically close the BufferedWriter when the try block is exited.


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




     

