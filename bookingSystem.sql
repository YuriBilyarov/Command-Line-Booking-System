DROP DATABASE bookingSystem;
CREATE DATABASE bookingSystem;
USE bookingSystem;


CREATE TABLE Client(
	clientId VARCHAR(4),
	clientName VARCHAR(25) NOT NULL,
	PRIMARY KEY (clientId)
);

CREATE TABLE Staff(
	staffId VARCHAR(4),
	staffName VARCHAR(25) NOT NULL,
	specialities VARCHAR(50) NOT NULL,
	PRIMARY KEY (staffId)
);

CREATE TABLE Booking(
	bookingId VARCHAR(4),
	clientId VARCHAR(4) NOT NULL,
	staffId VARCHAR(4) NOT NULL,
	bookingDate DATE NOT NULL,
	bookingTime TIME NOT NULL,
	duration TIME NOT NULL,
	focus VARCHAR(50) NOT NULL,
	PRIMARY KEY (bookingId),
	FOREIGN KEY (clientId) REFERENCES Client(clientId),
	FOREIGN KEY (staffId) REFERENCES Staff(staffId)
);
	
INSERT INTO Client
VALUES ('C001', 'Daniel Cooper'),
 ('C002', 'Josh Jones'),
 ('C003', 'Anabel Smith'),
 ('C004', 'David Wood'),
 ('C005', 'Tim Morris'),
 ('C006', 'Anthony Lee'),
 ('C007', 'Sarah Ford'),
 ('C008', 'George McAfee'),
 ('C009', 'Zack Morris');

INSERT INTO Staff
VALUES ('S001', 'Dan White', 'Muscle Gain'),
 ('S002', 'Holly Ray', 'Weight Loss'),
 ('S003', 'Lee Porter', 'Strenght & Conditioning'),
 ('S004', 'Maria Payne', 'Cardio'),
 ('S005', 'Liam Blackstone', 'Cardio'),
 ('S006', 'Veronica Straw', 'Weight Loss');

INSERT INTO Booking
VALUES ('B001', 'C002', 'S003', '2020-02-19', '17:40:00', '00:45:00', 'Strength & Conditioning'),
 ('B002', 'C008', 'S006', '2020-02-19', '16:30:00', '01:20:00', 'Weight Loss'),
 ('B003', 'C003', 'S004', '2020-02-20', '07:30:00', '02:00:00', 'Cardio'),
 ('B004', 'C002', 'S003', '2020-02-21', '17:40:00', '00:45:00', 'Strength & Conditioning'),
 ('B005', 'C002', 'S001', '2020-02-23', '18:30:00', '01:30:00', 'Muscle Gain'),
 ('B006', 'C005', 'S001', '2020-02-24', '18:30:00', '01:30:00', 'Muscle Gain');
