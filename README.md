## Restaurant Pandan Wangi

**Description**

This application is to simplify service and data management at a restaurant called Pandan Wangi.

The following features are available:

**Feature API**
- Menu
  - Get All - displays all Menu list
  - Get By ID - display information Menu based on Menu ID
  - Get All By Active - displays all Menu list based on activation
  - Get All By Name - displays all Menu list based on name
  - Get All By Category - displays all Menu list based on category
  - Create New - create a new Menu
  - Update Data - update Menu data based on Menu ID
  - Patch Status Active - update Menu activation status based on Menu ID
- Customer
  - Get All - displays all Customer list
  - Get By ID - display information Customer based on Customer ID
  - Create New - create a new Customer
  - Update Data - update Customer data based on Customer ID
  - Patch Status Member - update Customer member status based on Customer ID
- Table
  - Get All - display all Table list
  - Get By ID - display information Table based on Table ID
  - Get All By Active - displays all Table list based on activation
  - Get All By InUsed - displays all Table list based on occupied
  - Create New - create a new Table
  - Patch Status Active - update Table activation status based on Table ID
  - Patch Status InUsed - update Table InUsed status based on Table ID
- Employee
  - Get All - displays all Employee list
  - Get By ID - display information Employee based on Employee ID
  - Get All By Active - displays all Employee list based on activation
  - Create New - create a new Employee
  - Update Data - update Employee data based on Employee ID
  - Patch Status Active - update Employee activation status based on Employee ID
- Order
  - Get All - displays all Order list
  - Get By ID - display information Order based on Order ID
  - Create New - create a new Order
  - Delete - delete Order and all DetailOrder list based on Order ID
- Detail Order
  - Get All - displays all DetailOrder list
  - Get By ID - display information DetailOrder based on DetailOrder ID
  - Get All By ID Order - displays all DetailOrder list based on Order ID
  - Create New - create a new DetailOrder
  - Patch Status Order - update DetailOrder status order based on DetailOrder ID
  - Delete - delete DetailOrder based on DetailOrder ID