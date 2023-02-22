import mysql.connector
import random

# Connect to the database
mydb = mysql.connector.connect(
  host="localhost",
  user="root",
  password="",
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

def generate_random_types():
    types = ["Double", "Single", "Double self contained", "Single self contained"]

    chosen = random.choice(types)

    return chosen

def generate_random_status():
    status = ["Booked", "Vacant", "Under maintenance"]

    chosen = random.choice(status)

    return chosen


# Inserting the data into the database.

def datagen():
    for i in range(0, 40):
        room_type = generate_random_types()
        room_status = generate_random_status()
        # price = generate_random_price()

        mycursor.execute("INSERT INTO rooms (hostel_id, room_type, room_status) VALUES (9, %s, %s);", (room_type, room_status))

        # For generating rooms.
        # mycursor.execute("INSERT INTO rooms (hostel_id, room_type, room_status) VALUES (9, %s, %s);", (room_type, room_status))
        # mycursor.execute("INSERT INTO rooms (hostel_id, room_type, room_status) VALUES (10, %s, 'Booked');", (room_type,))
        mydb.commit()

# datagen()
# Querying info from the database
def getting_rooms_singles():
    mycursor.execute("SELECT room_id FROM rooms WHERE hostel_id = 10 AND room_status = 'Booked' AND room_type LIKE '%Single%'")
    rows = [tup[0] for tup in mycursor.fetchall()]
    
    return rows
# print(getting_rooms_singles())

def getting_rooms_doubles():
    mycursor.execute("SELECT room_id FROM rooms WHERE hostel_id = 10 AND room_status = 'Booked' AND room_type LIKE '%Double%'")
    rows = [tup[0] for tup in mycursor.fetchall()]
    
    return rows

# generating rooms
def generate_room_double(assigned_rooms):
    doubles = getting_rooms_doubles()
    available_doubles = list(set(doubles) - set(assigned_rooms))

    if not available_doubles:
        # if no unassigned room is found, return None
        return None
    
    # select a random unassigned room
    choiceDouble = random.choice(available_doubles)
    assigned_rooms.append(choiceDouble)
    return choiceDouble


def generate_residents_doubles(num_residents):
    residents = []
    assigned_rooms = []

    for i in range(num_residents):
        name = generate_random_letters()
        email = generate_random_email()
        password = generate_random_passwords()
        
        if i % 2 == 0:
            # generate a new room for every other resident
            room_id = generate_room_double(assigned_rooms)
        else:
            # use the same room ID as the previous resident
            room_id = assigned_rooms[-1]

        residents.append((name, email, password, room_id, 10))
        assigned_rooms.append(room_id)

    return residents




# print(generate_residents_doubles(9))

# Generating residents for single rooms
def generate_residents_singles(num_residents):
    residents = []
    assigned_rooms = getting_rooms_singles()
    random.shuffle(assigned_rooms)

    if len(assigned_rooms) < num_residents:
        # if there are not enough rooms, return an empty list
        return []

    for i in range(num_residents):
        name = generate_random_letters()
        email = generate_random_email()
        password = generate_random_passwords()
        room_id = assigned_rooms[i]

        residents.append((name, email, password, room_id, 10))

    return residents



# print(generate_residents_singles(8))


# Inserting the data into the database
def into_residents():
    residents = generate_residents_doubles(18)
    query = "INSERT INTO residents (resident_name, resident_email, resident_password, room_id, hostel_id) VALUES (%s, %s, %s, %s, %s);"
    mycursor.executemany(query, residents)
    mydb.commit()

# into_residents()


