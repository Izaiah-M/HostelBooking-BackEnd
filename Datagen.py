import mysql.connector
import random

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



def generate_random_letters():
    letters = list('abcdefghijklmnopqrstuvwxyz')
    random_letters = []
    
    while len(random_letters) < 3:
        letter = random.choice(letters)
        if letter not in random_letters:
            random_letters.append(letter)
    
    return ''.join(random_letters)

def generate_random_passwords():
    passwords = list('123456789abcdefghijklmnopqrstuvwxyz')
    random_password = []
    
    while len(random_password) < 8:
        letter = random.choice(passwords)
        if letter not in random_password:
            random_password.append(letter)
    
    return ''.join(random_password)

def generate_random_email():
    email = list('abcdefghijklmnopqrstuvwxyz')
    random_email = []
    
    while len(random_email) < 6:
        letter = random.choice(email)
        if letter not in random_email:
            random_email.append(letter)
    
    return ''.join(random_email) + "@mail.com"


# Inserting the data into the database.
def datagen():
    for i in range(0, 10):
        name = generate_random_letters()
        password = generate_random_passwords()
        email = generate_random_email()

        mycursor.execute("INSERT INTO managers (manager_name, manager_email, manager_password) VALUES (?, ?, ?);", name, email, password)

datagen()