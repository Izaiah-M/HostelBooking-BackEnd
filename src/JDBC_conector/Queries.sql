CREATE DATABASE hostel_management_system
    DEFAULT CHARACTER SET = 'utf8mb4';

USE hostel_management_system;

CREATE TABLE hostels (
    hostel_id INT PRIMARY KEY AUTO_INCREMENT,
    hostel_name VARCHAR(30),
    hostel_location VARCHAR(30),
    phone INT,
    num_rooms INT,
    hostel_status VARCHAR(30),
    mgr_id INT
);

CREATE TABLE rooms (
  room_id INT PRIMARY KEY AUTO_INCREMENT,
  hostel_id INT,
  room_type VARCHAR(40),
  price INT,
  room_status VARCHAR(40)
);

CREATE TABLE reservations (
  reservation_id INT PRIMARY KEY AUTO_INCREMENT,
  room_id INT NOT NULL,
  resident_id INT NOT NULL,
  resident_name VARCHAR(20) NOT NULL,
  resident_email VARCHAR(20) NOT NULL,
  start_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  end_date DATE
);

CREATE TABLE managers (
  manager_id INT PRIMARY KEY AUTO_INCREMENT,
  manager_name VARCHAR(20),
  manager_email VARCHAR(20),
  manager_password VARCHAR(30),
  hostel_id INT
);

CREATE TABLE residents (
  resident_id int PRIMARY KEY AUTO_INCREMENT,
  resident_name VARCHAR(20),
  resident_email VARCHAR(20),
  resident_password VARCHAR(30),
  room_id INT,
  hostel_id INT,
  start_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  end_date DATE DEFAULT DATE_ADD(CURRENT_DATE, INTERVAL 14 WEEK)
);

CREATE TABLE hostels_managers (
  mgr_id INT UNIQUE
);



ALTER TABLE hostels ADD FOREIGN KEY (mgr_id) REFERENCES hostels_managers (mgr_id);

ALTER TABLE rooms ADD FOREIGN KEY (hostel_id) REFERENCES hostels (hostel_id);

ALTER TABLE reservations ADD FOREIGN KEY (room_id) REFERENCES rooms (room_id);

ALTER TABLE reservations ADD FOREIGN KEY (resident_id) REFERENCES residents (resident_id);

ALTER TABLE managers ADD FOREIGN KEY (hostel_id) REFERENCES hostels (hostel_id);

ALTER TABLE residents ADD FOREIGN KEY (room_id) REFERENCES rooms (room_id);

ALTER TABLE residents ADD FOREIGN KEY (hostel_id) REFERENCES hostels (hostel_id);

ALTER TABLE hostels_managers ADD FOREIGN KEY (mgr_id) REFERENCES managers (manager_id);

SELECT * FROM managers;

DELETE FROM hostels;

-- To reset the PRIMARY key AUTO_INCREMENT
ALTER TABLE residents AUTO_INCREMENT = 1;

INSERT INTO hostels_managers 
VALUES
(1), (2), (3), (4), (5), (6), (7), (8), (9), (10);

INSERT INTO hostels
(hostel_name, hostel_location, phone, num_rooms, hostel_status, mgr_id)
VALUES
("David's Ark", "Buguju", 0757106452, 50, "Vacancies", 1),
("Sky hostel", "Buguju", 0727176602, 60, "Vacancies", 2),
("Dandeyy Girls", "Kataba", 0778495752, 50, "Vacancies", 3),
("HighField Hostel", "Buguju", 0800288838, 70, "Vacancies", 4),
("Chronos Hostel", "Wandegeya", 0800445622, 55, "Vacancies", 5),
("Carlton Hostel", "Buguju", 0788927374, 30, "Fully-booked", 6),
("St. Micheal Hostels", "Buguju", 0712425366, 50, "Vacancies", 7),
("New Boys Hostel", "Wandegeya", 0772990101, 30, "Fully-booked", 8),
("David's Ark", "Bishop", 0703445772, 74, "Vacancies", 9),
("Premium Hostel", "Buguju", 0743566278, 40, "Fully-booked", 10);

UPDATE managers
SET hostel_id = 10
WHERE manager_id = 10;

-- DELETE FROM rooms;

SELECT * FROM rooms;

UPDATE hostels
SET hostel_name = "Freshers Banquet"
WHERE hostel_id = 9;

SELECT room_id, room_type, room_status FROM rooms
WHERE room_status = "Booked" AND hostel_id = 1;

-- 2....21, 60

SELECT * FROM residents;

DELETE FROM residents;

SELECT COUNT(*) FROM rooms
WHERE room_status = "Booked" AND hostel_id = 1 AND room_type LIKE "%Double%";