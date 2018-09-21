WRITE ORACLE CREDENTIALS AND COMMANDS HERE
HANDLED BY SAJAL BAIN

DATABASE USERID: lab1btrg30@ORCL11G
DATABASE PASSWORD: lab1boracle 

Users Table:
CREATE TABLE users
(
	username varchar2(20), password varchar2(20),
	role varchar2(10),mobileno number(10),
	PRIMARY KEY (username)
);

AirPort Table:
CREATE TABLE airport
(
	airportname varchar2(20),abbreviation varchar2(5),
	location varchar2(40), PRIMARY KEY(abbreviation)
);

Flight Information Table:
CREATE TABLE flightinformation
(
	flightno varchar2(5),airline varchar2(10),depcity varchar2(10),
	arrcity varchar2(10),depdate date,arrdate date,deptime number(8),
	arrtime number(8),firstseats number(6),firstseatfare number(8,2),
	bussseats number(6),bussseatsfare number(6,2),
	primary key(flightno)
);

Booking Information Table:
CREATE TABLE bookinginformation 
(
	booking_id varchar2(5),cust_email varchar2(30),
	no_of_passengers number(3),class_type varchar2(10),
	total_fare number(6,2),seat_number number(3),
	creditcard_info varchar2(16),src_city varchar2(10),
	dest_city varchar2(10),primary key(booking_id)
);
