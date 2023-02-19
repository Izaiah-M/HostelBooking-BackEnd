CREATE DATABASE hostel_management_system
    DEFAULT CHARACTER SET = 'utf8mb4';

USE hostel_management_system;

CREATE TABLE hostels (
    hostel_id INT PRIMARY KEY AUTO_INCREMENT,
    hostel_name VARCHAR(30),
    hostel_location VARCHAR(30),
    phone INT,
    num_rooms INT,
    hostel_status TEXT,
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
  manager_id INT PRIMARY KEY,
  manager_name VARCHAR(20),
  manager_email VARCHAR(20),
  hostel_id INT
);

CREATE TABLE residents (
  resident_id int PRIMARY KEY,
  resident_name VARCHAR(20),
  resident_email VARCHAR(20),
  room_id INT,
  hostel_id INT,
  start_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  end_date DATE DEFAULT DATE_ADD(CURRENT_DATE, INTERVAL 14 WEEK)
);

CREATE TABLE hostels_managers (
  mgr_id INT
);

ALTER TABLE hostels ADD FOREIGN KEY (mgr_id) REFERENCES hostels_managers (mgr_id);

ALTER TABLE rooms ADD FOREIGN KEY (hostel_id) REFERENCES hostels (hostel_id);

ALTER TABLE reservations ADD FOREIGN KEY (room_id) REFERENCES rooms (room_id);

ALTER TABLE reservations ADD FOREIGN KEY (resident_id) REFERENCES residents (resident_id);

ALTER TABLE managers ADD FOREIGN KEY (hostel_id) REFERENCES hostels (hostel_id);

ALTER TABLE residents ADD FOREIGN KEY (room_id) REFERENCES rooms (room_id);

ALTER TABLE residents ADD FOREIGN KEY (hostel_id) REFERENCES hostels (hostel_id);

ALTER TABLE hostels_managers ADD FOREIGN KEY (mgr_id) REFERENCES managers (manager_id);