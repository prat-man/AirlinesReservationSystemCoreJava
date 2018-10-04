ORACLE CREDENTIALS
------------------

DATABASE USERID: lab1btrg30@ORCL11G
DATABASE PASSWORD: lab1boracle 



ORACLE QUERIES
--------------

Users Table: 
CREATE TABLE USERS
(
	USERNAME  VARCHAR2(20) PRIMARY KEY,
	PASSWORD  VARCHAR2(100),
	ROLE      VARCHAR2(20),
	MOBILE_NO NUMBER(20)
);

Airports Table:
CREATE TABLE AIRPORTS
(
	AIRPORTNAME  VARCHAR2(20),
	ABBREVIATION VARCHAR2(10) PRIMARY KEY,
	LOCATION     VARCHAR2(40)
);

Flights Information Table:
CREATE TABLE FLIGHTS
(
	FLIGHT_NO     VARCHAR2(20) PRIMARY KEY,
	AIRLINE       VARCHAR2(40),
	DEP_CITY      VARCHAR2(40),
	ARR_CITY      VARCHAR2(40),
	DEP_DATE      DATE,
	ARR_DATE      DATE,
	DEP_TIME      DATE,
	ARR_TIME      DATE,
	FIRSTSEATS    NUMBER(6),
	FIRSTSEATFARE NUMBER(10,2),
	BUSSSEATS     NUMBER(6),
	BUSSSEATSFARE NUMBER(10,2),
	AIRPORT       VARCHAR2(10) REFERENCES AIRPORTS(ABBREVIATION)
);

Bookings Table:
CREATE TABLE BOOKINGS
(
	BOOKING_ID       VARCHAR2(20) PRIMARY KEY,
	FLIGHT_NO        REFERENCES FLIGHTS(FLIGHT_NO),
	CUST_EMAIL       VARCHAR2(40),
	NO_OF_PASSENGERS NUMBER(3),
	CLASS_TYPE       VARCHAR2(10),
	TOTAL_FARE       NUMBER(6,2),
	SEAT_NUMBER      VARCHAR2(20),
	CREDITCARD_INFO  VARCHAR2(20),
	SRC_CITY         VARCHAR2(40),
	DEST_CITY        VARCHAR2(40)
);
