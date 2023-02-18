# from cs50 import SQL
# import random

# db = SQL("sqlite:///CakeShop.db")


# for i in range(0,50):
#     price = random.randrange(50000,1000000,100000)
#     type = random.choice(types)
#     flavour = random.choice(Flavours)
#     icing = random.choice(icings)
    
#     db.execute("INSERT INTO Cakes(Type,Flavour,DateMade,Icing,Cost) VALUES(?,?,?,?,?);",type,flavour,date,icing,price)

import mysql.connector

# Connect to the database
mydb = mysql.connector.connect(
  host="localhost",
  user="root",
  password="#Admin123",
  database="hostel_management_system"
)

# Check if connection was successful
if (mydb):
    print("Connection Successful")
else:
    print("Connection Unsuccessful")

# Perform database operations
mycursor = mydb.cursor()

# Execute a query
mycursor.execute("SELECT * FROM hostel_table")

# Fetch the results
results = mycursor.fetchall()

# Display the results
for result in results:
    print(result)


mgr_name = ["Fruit Cake","Forest Cake","Sponge Cake","Basic Vanilla Cake"]
mgr_contact = ["orange","marble","chocolate","vanilla"]
icings = ["fondant","Whipped Cream","Butter Cream"]
date = "6/12/2022"