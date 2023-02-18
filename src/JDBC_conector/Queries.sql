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