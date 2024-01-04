# Inventory Management System
The Inventory Management System (IMS) is a robust software solution designed to streamline and optimize the processes involved in managing inventory, sales, and reporting. This project is hosted on GitHub to provide version control, collaboration, and transparency throughout its development.	

The primary purpose of the Inventory Management System is to offer businesses a comprehensive tool for efficiently handling their inventory-related operations. This includes tracking stock levels, managing products and sales and ensuring data accuracy.
## Table of Content
1. [USER](#user)
   - 1.1 [Field](#field)
   - 1.2 [Constructor](#constructor)
   - 1.3 [Method](#method)
   
2. [Product](#product)
   - 2.1 [Field](#field)
   - 2.2 [Methods](#methods)
   - 
3. [OOP-Concept](#oop-concept)
   - 3.1 [Class and Object](#class-and-object)
   - 3.2 [Inheritance](#inheritance)
   - 3.3 [Polymorphism](#polymorphism)
   - 3.4 [Encapsulation](#encapsulation)
   - 3.5 [Abstraction](#abstraction)
   - 3.6 [Exception Handling](#exception-handling)
   - 3.7 [File I/O](#file-I/O)
   - 3.8 [Lamda Expression](#lamda-expression)
   - 3.9 [Static Method](#static-methods)

## USER
  The User class is an abstract base class that represents users within the system. It provides a blueprint for both regular users and administrators.
  ### 1.1 Field
  * username, password, and name: Basic user information.
  * currentUser: Reference to the currently logged-in user.
  * users: List of all users.
  * admins: List of administrator users.
    
  ### 1.2 Constructor
  *username: The username of the user being created.
  *password: The password of the user.
  *name: The name of the user.
  *string: A parameter that is not used within the constructor or its parent class.
  
  ### 1.3 Methods
  * getRole(): An abstract method that must be implemented by subclasses to return the role of the user.
  * removeUser(): An abstract method to be implemented by subclasses for removing a user.
  * getUsername(), getName(): Getter methods for the username and name, respectively.
  * setUsername(), setPassword(), setName(): Setter methods for updating user information.
  * getCurrentUser(), setCurrentUser(), clearCurrentUser(): Methods for managing the currently logged-in user.
    

  
## Product
### 2.1 Field
* name: the name of the product. It is of type String.
* price: the price of the product. It is of type double.
* stock: the quantity of the product in stock. It is of type int.
### 2.2 Methods
### a. accessor methods
* getName(): Returns the name of the product.
* getPrice(): Returns the price of the product.
* getStock(): Returns the current stock quantity of the products,\.
### b. Overriding Methods
* toString(): Provides a formatted string representation of the product, including name, price, and stock.
* equals(Object obj): Compares two products for equality based on their names.
* hashCode(): Generates a hash code for the product based on its name.


## Object Oriented Concepts
### 1. Class and Objects
* Super Class User. Subclass: BaseUser and Admin
* Super Class Products. Subclass: SaleItem
* Inventory Management System
  
### 2. Inheritance
* Overloading Methods
* Overriding Methods
  
### 3. Polymorphism
### 4. Encapsulation
### 5. Abstraction
### 6. Exception Handling
### 7. File I/O
### 8. Lamda Expression
### 9. Static Methods




     

