Inventory Management System
“KIN Sosetha & LINE Regene”

Source Code:
Features Concept:

Features of the Inventory Management System:

User Authentication: (Available)

- File containing authorized users information
- Checking authorization by comparing inputted information with file information
- If authorized, user is let into system; otherwise, quit program

Product: (Available)

- Add new product
- Edit product information (name, quantity, price)
- Remove product

Receipt/Invoice: (Available)

- Datetime issued
- Amounts of each product sold + price
- Total price

Report:

- Date issued
- All receipt_ids in a set amount of time (weekly, biweekly, monthly, quarterly, semester, yearly)
- Total Price

Project Report Sample:

Introduction:

- Our project is about an Inventory Management System with the purpose of tracking product inventory and generating and keeping track of all transactions made.

Detail:

- The functions of the system:
- User authentication
- View products
- Add product
- Edit product information (name, price, quantity, etc)
- Delete product
- Add product inventory/stocks
- Generate invoices/receipts
- Generate reports based on time (day, month, year, etc)
- View product categories

Implementation:

Class

- Field: what is it used for?
- User: user_id, username, passwords, name.
- Product: product_id, name, description, price, quantity
- Receipt: reciepe_id, user_id, prod_id, prod_quan, prod_price, total price.
- Report: report_id, reciepe_id, total_price, date.

Method: What does it do?

- User: getName, getUsername, getPassword, setName, setUsername, setPassword
- Product: getName, getPrice, getQuantity, setName, setPrice, setQuantity, addProduct, removeProduct
- Receipt: setProductQuantity, getProduct_ID, getUser_ID, getProductPrice, getTotalPrice
- Report: getReceiptID, getDate, getTotalPrice
