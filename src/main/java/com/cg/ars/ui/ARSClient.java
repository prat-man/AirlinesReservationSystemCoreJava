package com.cg.ars.ui;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.cg.ars.dto.Airport;
import com.cg.ars.dto.Booking;
import com.cg.ars.dto.Flight;
import com.cg.ars.dto.User;
import com.cg.ars.exception.AirportException;
import com.cg.ars.exception.BookingException;
import com.cg.ars.exception.FlightException;
import com.cg.ars.exception.UserException;
import com.cg.ars.service.AirportService;
import com.cg.ars.service.AirportServiceImpl;
import com.cg.ars.service.BookingService;
import com.cg.ars.service.BookingServiceImpl;
import com.cg.ars.service.FlightService;
import com.cg.ars.service.FlightServiceImpl;
import com.cg.ars.service.UserService;
import com.cg.ars.service.UserServiceImpl;

public class ARSClient
{
	public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));

	public static final UserService    U_SER = new UserServiceImpl();
	public static final FlightService  F_SER = new FlightServiceImpl();
	public static final BookingService B_SER = new BookingServiceImpl();
	public static final AirportService A_SER = new AirportServiceImpl();
	
	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
	public static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("hh:mm");
	
	public static void main(String[] args)
	{
		LogoAnimation la = new LogoAnimation();
		la.startAnimation();
		
		FlightAnimation fa = new FlightAnimation();
		fa.startAnimation();
		
		while (true) {
			clearScreen();
			
			System.out.println("===============================================================================");
			System.out.println("==================== Welcome to Airline Reservation System ====================");
			System.out.println("===============================================================================");
			System.out.println();
			
			System.out.println("1. Login");
			System.out.println("2. Register");
			System.out.println("3. Exit");
			System.out.print("Enter Your Choice: ");
			
			int menu;
			
			try {
				menu = Integer.parseInt(BR.readLine());
			} catch (NumberFormatException | IOException e) {
				System.err.println("\nInvalid Choice");
				holdScreen();
				continue;
			}
			
			if (menu == 2) {
				clearScreen();
				
				System.out.println("===============================================================================");
				System.out.println("==================== Welcome to Airline Reservation System ====================");
				System.out.println("===============================================================================");
				System.out.println("================================== Register ===================================");
				System.out.println("===============================================================================");
				System.out.println();
				
				if (!registerUser()) {
					holdScreen();
					continue;
				}
				
				holdScreen();
			}
			else if (menu != 1) {
				System.out.println();
				System.out.println("===============================================================================");
				System.out.println("================================== Thank You ==================================");
				System.out.println("===============================================================================");
				System.out.println();
				
				return;
			}
			
			clearScreen();
			
			System.out.println("===============================================================================");
			System.out.println("==================== Welcome to Airline Reservation System ====================");
			System.out.println("===============================================================================");
			System.out.println("==================================== Login ====================================");
			System.out.println("===============================================================================");
			System.out.println();
			
			System.out.print("Username: ");
			String username;
			try {
				username = BR.readLine();
			} catch (IOException e) {
				System.err.println("\nInvalid Username");
				holdScreen();
				continue;
			}
			
			System.out.print("Password: ");
			String password = getPassword();
			
			boolean isValidUser;
			try {
				isValidUser = U_SER.verifyUser(username, password);
			} catch (UserException e) {
				System.err.println("\n" + e.getMessage());
				holdScreen();
				continue;
			}
			
			if(isValidUser) 
			{
				User user;
				try {
					user = U_SER.getUser(username);
				} catch (UserException e) {
					System.err.println("\n" + e.getMessage());
					holdScreen();
					continue;
				}
				
				String role = user.getRole();
				
				if(role.equals("Admin")) 
				{
					inner: while(true) {
						clearScreen();
						
						System.out.println("===============================================================================");
						System.out.println("==================== Welcome to Airline Reservation System ====================");
						System.out.println("===============================================================================");
						System.out.println("================================ Admin Console ================================");
						System.out.println("===============================================================================");
						System.out.println();
						
						System.out.println("1. Add User");
						System.out.println("2. Add Flight");
						System.out.println("3. Modify Flight");
						System.out.println("4. Delete Flight");
						System.out.println("5. View Flights");
						System.out.println("6. View Flights by Date");
						System.out.println("7. Add Airport");
						System.out.println("8. View Airports");
						System.out.println("9. View Booking Details");
						System.out.println("10. Change Password");
						System.out.println("11. Logout");
						System.out.print("Enter Your Choice: ");
						
						int choice;
						try {
							choice = Integer.parseInt(BR.readLine());
						} catch (NumberFormatException | IOException e) {
							System.err.println("\nInvalid Choice");
							holdScreen();
							continue;
						}
						
						switch (choice)
						{
							case 1:
									addUser();
									break;
									
							case 2:
									addFlight();
									break;
								
							case 3:
									modifyFlight();
									break;
							
							case 4:
									deleteFlight();
									break;
								
							case 5:
									viewFlights();
									break;
								
							case 6:
									viewFlightsByDate();
									break;
								
							case 7:
									addAirport();
									break;
							
							case 8:
									viewAirports();
									break;
								
							case 9:
									viewBooking(username);
									break;
									
							case 10:
									changePassword(username);
							
							case 11:
							default:
									break inner;
						}
						
						holdScreen();
					}
				}
				else if(role.equals("Executive"))
				{
					inner: while(true) {
						clearScreen();
						
						System.out.println("===============================================================================");
						System.out.println("==================== Welcome to Airline Reservation System ====================");
						System.out.println("===============================================================================");
						System.out.println("============================== Executive Console ==============================");
						System.out.println("===============================================================================");
						System.out.println();
						
						System.out.println("1. View Flights");
						System.out.println("2. View Flights by Date");
						System.out.println("3. View Airports");
						System.out.println("4. View Booking Details");
						System.out.println("5. View Flight Occupancy by Flight");
						System.out.println("6. View Flight Occupancy by Route");
						System.out.println("7. Change Password");
						System.out.println("8. Logout");
						System.out.print("Enter Your Choice: ");
						
						int choice;
						try {
							choice = Integer.parseInt(BR.readLine());
						} catch (NumberFormatException | IOException e) {
							System.err.println("\nInvalid Choice");
							holdScreen();
							continue;
						}
						
						switch (choice)
						{
							case 1:
									viewFlights();
									break;
							
							case 2:
									viewFlightsByDate();
									break;
							
							case 3:
									viewAirports();
									break;
							
							case 4:
									viewBooking(username);
									break;
							
							case 5:
									viewFlightOccupancy();
									break;
								
							case 6:
									viewFlightOccupancyByRoute();
									break;
							
							case 7:
									changePassword(username);
									break;
							
							case 8:
							default:
									break inner;
						}
						
						holdScreen();
					}
				}
				else {
					inner: while(true) {
						clearScreen();
						
						System.out.println("===============================================================================");
						System.out.println("==================== Welcome to Airline Reservation System ====================");
						System.out.println("===============================================================================");
						System.out.println("================================= User Console ================================");
						System.out.println("===============================================================================");
						System.out.println();
						
						System.out.println("1. Book a Ticket");
						System.out.println("2. View Booking Details");
						System.out.println("3. Change Email ID");
						System.out.println("4. Cancel Booking");
						System.out.println("5. Change Password");
						System.out.println("6. Logout");
						System.out.print("Enter Your Choice: ");
						
						int choice;
						try {
							choice = Integer.parseInt(BR.readLine());
						} catch (NumberFormatException | IOException e) {
							System.err.println("\nInvalid Choice");
							holdScreen();
							continue;
						}
						
						switch (choice)
						{
							case 1:
									bookTicket(username); 
									break;
									
							case 2:
									viewBooking(username);
									break;
									
							case 3:
									changeEmailId(username);
									break;
									
							case 4:
									cancelBooking(username);
									break;
									
							case 5:
									changePassword(username);
									break;
								
							case 6:
							default:
									break inner;
						}
						
						holdScreen();
					}
				}
			}
		}
	}
	
	private static boolean registerUser() 
	{
		User user = new User();
		
		user.setRole(User.USER);
		
		try {
			System.out.print("Username: ");
			user.setUsername(BR.readLine());
			
			System.out.print("Password: ");
			user.setPassword(getPassword());
			
			System.out.print("Email ID: ");
			user.setEmail(BR.readLine());
			
			System.out.print("Mobile No.: ");
			user.setMobileNo(BR.readLine());
		} catch (IOException e) {
			System.err.println("\nInvalid Input");
			System.err.println(e.getMessage());
			
			return false;
		}
		
		try {
			U_SER.addUser(user);
			
			System.out.println("\nRegistration Successful");
			
			return true;
		} catch (UserException e) {
			System.err.println("\n" + e.getMessage());
			
			return false;
		}
	}

	private static void addUser()
	{
		System.out.println("\nUser Type");
		System.out.println("1. Admin");
		System.out.println("2. Executive");
		System.out.print("Enter Your Choice: ");
		
		int choice = 0;
		
		try {
			choice = Integer.parseInt(BR.readLine());
		} catch (NumberFormatException | IOException e) {
			System.err.println("\nInvalid Choice");
			System.err.println(e.getMessage());
			return;
		}
		
		User user = new User();
		
		switch (choice)
		{
			case 1:
					user.setRole(User.ADMIN);			
					break;
					
			case 2:
					user.setRole(User.EXECUTIVE);
					break;
					
			default:
					System.out.println("\nInvalid Choice");
					return;
		}
		
		try {
			System.out.print("\nUsername: ");
			user.setUsername(BR.readLine());
			
			System.out.print("Password: ");
			user.setPassword(getPassword());
			
			System.out.print("Email ID: ");
			user.setEmail(BR.readLine());
			
			System.out.print("Mobile No.: ");
			user.setMobileNo(BR.readLine());
		} catch (IOException e) {
			System.err.println("\nInvalid Input");
			System.err.println(e.getMessage());
		}
		
		try {
			U_SER.addUser(user);
			
			System.out.println("\nAdded User Successfully");
		} catch (UserException e) {
			System.err.println("\n" + e.getMessage());
		}
	}
	
	private static void changePassword(String username)
	{
		String oldPass, newPass, confPass;
		
		System.out.print("\nEnter old password: ");
		oldPass = getPassword();
		
		System.out.print("Enter new password: ");
		newPass = getPassword();
		
		System.out.print("Enter new password again: ");
		confPass = getPassword();
		
		if (!newPass.equals(confPass)) {
			System.err.println("\nThe passwords do not match");
			return;
		}
		
		try {
			U_SER.changePassword(username, oldPass, newPass);
			
			System.out.println("\nPassword Changed Successfully");
		}
		catch (UserException e) {
			System.err.println("\n" + e.getMessage());
		}
	}

	private static void viewFlights()
	{
		try {
			List<Flight> flightList = F_SER.getAllFlights();
			
			String header = String.format("%-20s | %-40s | %-40s | %-40s | %-12s | %-12s | %-12s | %-12s | %15s | %15s | %15s | %15s | %-12s | %-12s",
								"Flight No",
								"Airline",
								"Departure City",
								"Arrival City",
								"Dep Date",
								"Dep Time",
								"Arr Date",
								"Arr Time",
								"First Seats",
								"First Fare",
								"Business Seats",
								"Business Fare",
								"Dep Airport",
								"Arr Airport");
			
			System.out.println("\n\n" + header);
			
			System.out.println(String.format("%" + header.length() + "s", "").replace(' ', '-'));
								
			for (Flight f : flightList)
			{
				System.out.printf("%-20s | %-40s | %-40s | %-40s | %-12s | %-12s | %-12s | %-12s | %15d | %15.2f | %15d | %15.2f | %-12s | %-12s\n",
						f.getFlightNo(),
						f.getAirline(),
						f.getDepCity(),
						f.getArrCity(),
						DATE_FORMAT.format(f.getDepDate()),
						TIME_FORMAT.format(f.getDepTime()),
						DATE_FORMAT.format(f.getArrDate()),
						TIME_FORMAT.format(f.getArrTime()),
						f.getFirstSeats(),
						f.getFirstSeatsFare(),
						f.getBussSeats(),
						f.getBussSeatsFare(),
						f.getDepAirport(),
						f.getArrAirport());
				
			}
		}
		catch (FlightException e) {
			System.err.println("\n" + e.getMessage());
		}
	}

	private static void addFlight()
	{
		Flight flight = new Flight();
		
		try {
			System.out.print("\nFlight Number: ");
			flight.setFlightNo(BR.readLine());
			
			System.out.print("Airline Name: ");
			flight.setAirline(BR.readLine());
			
			System.out.print("Departure Date: ");
			Date dateDep = new Date(DATE_FORMAT.parse(BR.readLine()).getTime());
			flight.setDepDate(dateDep);
			
			System.out.print("Departure Time: ");
			Time timeDep = new Time(TIME_FORMAT.parse(BR.readLine()).getTime());
			flight.setDepTime(timeDep);
			
			System.out.print("Arrival Date: ");
			Date dateArr = new Date(DATE_FORMAT.parse(BR.readLine()).getTime());
			flight.setArrDate(dateArr);
			
			System.out.print("Arrival Time: ");
			Time timeArr = new Time(TIME_FORMAT.parse(BR.readLine()).getTime());
			flight.setArrTime(timeArr);
			
			System.out.print("First Seats: ");
			flight.setFirstSeats(Integer.parseInt(BR.readLine()));
			
			System.out.print("First Seats Fare: ");
			flight.setFirstSeatsFare(Double.parseDouble(BR.readLine()));
			
			System.out.print("Business Seats: ");
			flight.setBussSeats(Integer.parseInt(BR.readLine()));
			
			System.out.print("Business Seats Fare: ");
			flight.setBussSeatsFare(Double.parseDouble(BR.readLine()));
			
			System.out.print("Departure Airport: ");
			flight.setDepAirport(BR.readLine());
			
			System.out.print("Arrival Airport: ");
			flight.setArrAirport(BR.readLine());
		}
		catch(IOException | ParseException e) {
			System.err.println("\nInvalid Input");
			System.err.println(e.getMessage());
			return;
		}
		
		try {
			F_SER.addFlight(flight);
			
			System.out.println("\nFlight Added Succesfully");
		} catch (FlightException e) {
			System.err.println("\n" + e.getMessage());
		}
	}

	private static void modifyFlight()
	{
		Flight flight;
		
		String flightNo;
		
		try {
			System.out.print("\nFlight Number: ");
			flightNo = BR.readLine();
		} catch (IOException e) {
			System.err.println("\nInvalid Input");
			System.err.println(e.getMessage());
			return;
		}
		
		try {
			flight = F_SER.getFlight(flightNo);
		} catch (FlightException e) {
			System.err.println("\n" + e.getMessage());
			return;
		}
		
		try {
			System.out.println("\nFlight No.: " + flight.getFlightNo());
			System.out.println("Airline: " + flight.getAirline());
			System.out.println("Departure City: " + flight.getDepCity());
			System.out.println("Arrival City: " + flight.getArrCity());
			System.out.println("Departure Date: " +  DATE_FORMAT.format(flight.getDepDate()));
			System.out.println("Departure Time: " + TIME_FORMAT.format(flight.getDepTime()));
			System.out.println("Arrival Date: " + DATE_FORMAT.format(flight.getArrDate()));
			System.out.println("Arrival Time: " + TIME_FORMAT.format(flight.getArrTime()));
			System.out.println("First Seats: " + flight.getFirstSeats());
			System.out.println("First Seats Fare: " + flight.getFirstSeatsFare());
			System.out.println("Business Seats: " + flight.getBussSeats());
			System.out.println("Business Fare: " + flight.getBussSeatsFare());
			System.out.println("Departure Airport: " + flight.getDepAirport());
			System.out.println("Arrival Airport: " + flight.getArrAirport());
			
			System.out.print("\n\nAirline Name: ");
			flight.setAirline(BR.readLine());
			
			System.out.print("Departure Date: ");
			Date dateDep = new Date(DATE_FORMAT.parse(BR.readLine()).getTime());
			flight.setDepDate(dateDep);
			
			System.out.print("Departure Time: ");
			Time timeDep = new Time(TIME_FORMAT.parse(BR.readLine()).getTime());
			flight.setDepTime(timeDep);
			
			System.out.print("Arrival Date: ");
			Date dateArr = new Date(DATE_FORMAT.parse(BR.readLine()).getTime());
			flight.setArrDate(dateArr);
			
			System.out.print("Arrival Time: ");
			Time timeArr = new Time(TIME_FORMAT.parse(BR.readLine()).getTime());
			flight.setArrTime(timeArr);
			
			System.out.print("First Seats: ");
			flight.setFirstSeats(Integer.parseInt(BR.readLine()));
			
			System.out.print("First Seats Fare: ");
			flight.setFirstSeatsFare(Double.parseDouble(BR.readLine()));
			
			System.out.print("Business Seats: ");
			flight.setBussSeats(Integer.parseInt(BR.readLine()));
			
			System.out.print("Business Seats Fare: ");
			flight.setBussSeatsFare(Double.parseDouble(BR.readLine()));
			
			System.out.print("Departure Airport: ");
			flight.setDepAirport(BR.readLine());
			
			System.out.print("Arrival Airport: ");
			flight.setArrAirport(BR.readLine());
		}
		catch (IOException | ParseException e) {
			System.err.println("\nInvalid Input");
			System.err.println(e.getMessage());
			return;
		}
		
		try {
			F_SER.modifyFlight(flight);
			
			System.out.println("\nFlight Modified Successfully");
		} catch (FlightException e) {
			System.err.println("\n" + e.getMessage());
		}
	}

	private static void deleteFlight() 
	{
		try {
			System.out.print("\nFlight Number: ");
			
			Flight flight =  F_SER.getFlight(BR.readLine());
			
			System.out.println("Flight No.: " + flight.getFlightNo());
			System.out.println("Airline: " + flight.getAirline());
			System.out.println("Departure City: " + flight.getDepCity());
			System.out.println("Arrival City: " + flight.getArrCity());
			System.out.println("Departure Date: " +  DATE_FORMAT.format(flight.getDepDate()));
			System.out.println("Departure Time: " + TIME_FORMAT.format(flight.getDepTime()));
			System.out.println("Arrival Date: " + DATE_FORMAT.format(flight.getArrDate()));
			System.out.println("Arrival Time: " + TIME_FORMAT.format(flight.getArrTime()));
			System.out.println("First Seats: " + flight.getFirstSeats());
			System.out.println("First Seats Fare: " + flight.getFirstSeatsFare());
			System.out.println("Business Seats: " + flight.getBussSeats());
			System.out.println("Business Fare: " + flight.getBussSeatsFare());
			System.out.println("Departure Airport: " + flight.getDepAirport());
			System.out.println("Arrival Airport: " + flight.getArrAirport());
			
			System.out.print("\nAre you sure (Y/N)? ");
			char ch = Character.toUpperCase(BR.readLine().charAt(0));
			
			if (ch == 'Y') {
				F_SER.deleteFlight(flight.getFlightNo());
				
				System.out.println("\nFlight Deleted Successfully");
			}
			else {
				System.out.println("\nUser Cancelled Operation");
			}
		}
		catch (Exception e) {
			System.err.println("\nCould not delete flight");
			System.err.println(e.getMessage());
		}
	}

	private static void viewFlightsByDate() 
	{
		Date date;
		String depCity, arrCity;
		
		try {
			System.out.print("\nTravel Date (dd-MM-yyyy): ");
			date = new Date(DATE_FORMAT.parse(BR.readLine()).getTime());
			
			System.out.print("Departure City: ");
			depCity = BR.readLine();
			
			System.out.print("Arrival City: ");
			arrCity = BR.readLine();
		} catch (ParseException | IOException e) {
			System.err.println("\nInvalid Input");
			System.err.println(e.getMessage());
			return;
		}
		
		List<Flight> flights;
		
		try {
			flights = F_SER.getFlights(date, depCity, arrCity);
		} catch (FlightException e) {
			System.err.println("\n" + e.getMessage());
			return;
		}
		
		String header = String.format("%-20s | %-40s | %-40s | %-40s | %-12s | %-12s | %-12s | %-12s | %15s | %15s | %15s | %15s | %-12s | %-12s",
				"Flight No",
				"Airline",
				"Departure City",
				"Arrival City",
				"Dep Date",
				"Dep Time",
				"Arr Date",
				"Arr Time",
				"First Seats",
				"First Fare",
				"Business Seats",
				"Business Fare",
				"Dep Airport",
				"Arr Airport");
		
		System.out.println("\n\n" + header);
		
		System.out.println(String.format("%" + header.length() + "s", "").replace(' ', '-'));
		
		for (Flight f : flights)
		{
			System.out.printf("%-20s | %-40s | %-40s | %-40s | %-12s | %-12s | %-12s | %-12s | %15d | %15.2f | %15d | %15.2f | %-12s | %-12s\n",
					f.getFlightNo(),
					f.getAirline(),
					f.getDepCity(),
					f.getArrCity(),
					DATE_FORMAT.format(f.getDepDate()),
					TIME_FORMAT.format(f.getDepTime()),
					DATE_FORMAT.format(f.getArrDate()),
					TIME_FORMAT.format(f.getArrTime()),
					f.getFirstSeats(),
					f.getFirstSeatsFare(),
					f.getBussSeats(),
					f.getBussSeatsFare(),
					f.getDepAirport(),
					f.getArrAirport());
		}
	}

	private static void addAirport() 
	{
		Airport airport = new Airport();
		
		try 
		{
			System.out.print("\nAirport Name: ");
			airport.setAirportName(BR.readLine());
			
			System.out.print("Abbreviation: ");
			airport.setAbbreviation(BR.readLine());
			
			System.out.print("Location: ");
			airport.setLocation(BR.readLine());
		}
		catch (IOException e) {
			System.err.println("\nInvalid Input");
			System.err.println(e.getMessage());
			return;
		}
		
		try {
			A_SER.addAirport(airport);
			
			System.out.println("\nAirport Added Successfully");
		} catch (AirportException e) {
			System.err.println("\n" + e.getMessage());
		}
	}

	private static void viewAirports() 
	{
		List<Airport> airports =  A_SER.getAllAirports();
		
		if (airports == null || airports.isEmpty()) {
			System.err.println("\nNo Airports Found");
			return;
		}
		
		String header = String.format("%-40s | %-15s | %-40s",
				"Airport Name",
				"Abbreviation",
				"Location");
		
		System.out.println("\n\n" + header);
		
		System.out.println(String.format("%" + header.length() + "s", "").replace(' ', '-'));
		
		for (Airport a : airports)
		{
			System.out.printf("%-40s | %-15s | %-40s\n",
					a.getAirportName(),
					a.getAbbreviation(),
					a.getLocation());
		}
	}
  
	private static void viewFlightOccupancy() 
	{
		System.out.print("\nFlight number: ");
		
		String flightNo = null;
		
		try {
			flightNo = BR.readLine();
		}
		catch (IOException e) {
			System.err.println("Invalid Flight Number");
		}
		
		try {
			double occupancy = F_SER.getOccupancy(flightNo) * 100;
			
			System.out.println("\nFlight Occupancy: " + String.format("%.2f", occupancy) + " %");
		} catch (FlightException e) {
			System.err.println("\n" + e.getMessage());
		}
	}

	private static void viewFlightOccupancyByRoute()
	{
		String depCity = null;
		String arrCity = null;
	      
		try {
			System.out.print("\nDeparture City: ");
			depCity = BR.readLine();
			
			System.out.print("Arrival City: ");
			arrCity = BR.readLine();
		} 
		catch (IOException e) {
			System.err.println("Invalid City Name");
			return;
		}
		
		try {
			double occupancy = F_SER.getOccupancy(depCity, arrCity) * 100;
			
			System.out.println("\nFlight Occupancy: " + String.format("%.2f", occupancy) + " %");
		} catch (FlightException
				e) {
			System.err.println("\n" + e.getMessage());
		}
	}

	private static void bookTicket(String username)
	{
		Date date;
		String depCity, arrCity;
		
		try 
		{
			System.out.print("\nTravel Date (dd-MM-yyyy): ");
			date = new Date(DATE_FORMAT.parse(BR.readLine()).getTime());
        
			System.out.print("City From: ");
			depCity = BR.readLine();
        
			System.out.print("City To: ");
			arrCity = BR.readLine();
		}
		catch (IOException | ParseException e) {
			System.err.println("\nInvalid Input");
			System.err.println(e.getMessage());
			return;
		}
      
		List<Flight> flightList;
		
		try {
			flightList = F_SER.getFlights(date, depCity, arrCity);
		} catch (FlightException e) {
			System.err.println("\n" + e.getMessage());
			return;
		}
		
		String header = String.format("%-20s | %-40s | %-12s | %-40s | %-12s | %-40s | %-12s | %-12s | %-12s | %-12s | %15s | %15s | %15s | %15s",
				"Flight No",
				"Airline",
				"Dep Airport",
				"Dep City",
				"Arr Airport",
				"Arr City",
				"Dep Date",
				"Dep Time",
				"Arr Date",
				"Arr Time",
				"First Seats",
				"First Fare",
				"Business Seats",
				"Business Fare");
		
		System.out.println("\n\n" + header);
		
		System.out.println(String.format("%" + header.length() + "s", "").replace(' ', '-'));
  
		for(Flight flight : flightList) 
		{
			System.out.printf("%-20s | %-40s | %-12s | %-40s | %-12s | %-40s | %-12s | %-12s | %-12s | %-12s | %15d | %15.2f | %15d | %15.2f\n",
					flight.getFlightNo(),
					flight.getAirline(),
					flight.getDepAirport(),
					flight.getDepCity(),
					flight.getArrAirport(),
					flight.getArrCity(),
					DATE_FORMAT.format(flight.getDepDate()),
					TIME_FORMAT.format(flight.getDepTime()),
					DATE_FORMAT.format(flight.getArrDate()),
					TIME_FORMAT.format(flight.getArrTime()),
					flight.getFirstSeats(),
					flight.getFirstSeatsFare(),
					flight.getBussSeats(),
					flight.getBussSeatsFare());
		}
		
		Booking booking = new Booking();
		
		booking.setUsername(username);
		
		try {
			System.out.print("\n\nFlight Number: ");
			String flightNo = BR.readLine();
			booking.setFlightNo(flightNo);
		}
		catch (IOException e) {
			System.err.println("\nInvalid Input");
			System.err.println(e.getMessage());
			return;
		}
		
		Flight flight;
		
		try {
			flight = F_SER.getFlight(booking.getFlightNo());
		} catch (FlightException e) {
			System.out.println("\n" + e.getMessage());
			return;
		}
		
		try {
			System.out.print("Number of Seats Required: ");
			booking.setNoOfPassengers(Integer.parseInt(BR.readLine()));
			
			System.out.print("Class Type: ");
			booking.setClassType(BR.readLine());
			
			System.out.print("Credit Card Number: ");
			booking.setCreditCardInfo(BR.readLine());
		}
		catch (IOException e) {
			System.err.println("\nInvalid Input");
			System.err.println(e.getMessage());
			return;
		}
		
		double totalFare;
		
		try {
			totalFare = booking.getNoOfPassengers() * F_SER.getFare(booking.getFlightNo(), booking.getClassType());
			booking.setTotalFare(totalFare);
		} catch (FlightException e) {
			System.err.println("\n" + e.getMessage());
			return;
		}
		
		booking.setSrcCity(flight.getDepCity());
		
		booking.setDestCity(flight.getArrCity());
		
		try {
			booking.setBookingId(B_SER.generateBookingId(flight.getFlightNo()));
			
			B_SER.bookTicket(booking);
		} catch (BookingException e) {
			System.err.println("\n" + e.getMessage());
			return;
		}
		
		System.out.println("\nBooking Successful\n");
		
		System.out.println("Booking ID: " + booking.getBookingId());
		
		System.out.println("Seat Number: " + booking.getSeatNumber());
	}

	private static void viewBooking(String username) 
	{
		String bookingId;
		
		try {
			System.out.print("\nBooking ID: ");
			
			bookingId = BR.readLine();
		}
		catch (IOException e) {
			System.err.println("\nInvalid Input");
			System.err.println(e.getMessage());
			return;
		}
			
		Booking booking;
		Flight flight;
		
		try {
			booking = B_SER.getBooking(bookingId);
			flight = F_SER.getFlight(booking.getFlightNo());
		} catch (BookingException | FlightException e) {
			System.err.println("\n" + e.getMessage());
			return;
		}
		
		if (!booking.getUsername().equals(username)) {
			System.err.println("\nAccess Denied");
			return;
		}
		
		System.out.println("\nFlight No: " + booking.getFlightNo());
		System.out.println("Source City: " + booking.getSrcCity());
		System.out.println("Destination City: " + booking.getDestCity());
		System.out.println("Source Airport: " + flight.getDepAirport());
		System.out.println("Destination Airport: " + flight.getArrAirport());
		System.out.println("Class Type: " + booking.getClassType());
		System.out.println("No. of passengers: " + booking.getNoOfPassengers());
		System.out.println("Seat Number: " + booking.getSeatNumber());
		System.out.println("Total Fare: " + booking.getTotalFare());
	}

	private static void changeEmailId(String username) 
	{
		String password, email;
		
		try {
			System.out.print("\nEnter password: ");
			password = getPassword();
			
			System.out.print("Enter new Email ID: ");
			email = BR.readLine();
		}
		catch (IOException e) {
			System.out.println("\nInvalid Input");
			System.out.println(e.getMessage());
			return;
		}
		
		try {
			U_SER.changeEmail(username, password, email);
			
			System.out.println("\nEmail ID Changed Successfully");
		}
		catch (UserException e) {
			System.err.println("\n" + e.getMessage());
		}
	}

	private static void cancelBooking(String username) 
	{
		String bookingId;
		
		try {
			System.out.print("\nBooking ID: ");
			bookingId = BR.readLine();
		} 
		catch (IOException e) {
			System.err.println("\nInvalid Input");
			System.err.println(e.getMessage());
			return;
		}
		
		Booking booking;
		
		try {
			booking = B_SER.getBooking(bookingId);
		} catch (BookingException e) {
			System.err.println("\n" + e.getMessage());
			return;
		}
		
		if (!booking.getUsername().equals(username)) {
			System.err.println("\nAccess Denied");
			return;
		}
		
		try {
			if (B_SER.validateBookingId(bookingId)) {
				B_SER.cancelBooking(bookingId);
				
				System.out.println("\nBooking Cancelled Successfully");
			}
		} catch (BookingException e) {
			e.printStackTrace();
			System.err.println("\n" + e.getMessage());
		}
	}
	
	private static String getPassword()
	{
		String password = null;
		
		Console console = System.console();
		
		if (console != null) {
			password = String.valueOf(console.readPassword());
		}
		else {
			try {
				password = BR.readLine();
			} catch (IOException e) {
				System.err.println("\n" + e.getMessage());
			}
		}
		
		return password;
	}
	
	public static void clearScreen()
	{
		String os = System.getProperty("os.name").toLowerCase();
		
		try {
			if (os.indexOf("windows") >= 0) {
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			}
			else if (os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0 || os.indexOf("aix") >= 0 ||	// unix & linux
					 os.indexOf("mac") >= 0 ||														// mac osx
					 os.indexOf("sunos") >= 0) {													// solaris
				Runtime.getRuntime().exec("clear");
			}
		} catch (IOException | InterruptedException e) {
			// Could not clear screen
			// Do nothing
		}
	}
	
	public static void holdScreen()
	{
		System.out.print("\n\nPress [ENTER] to continue ...");
		
		try {
			BR.readLine();
		} catch (IOException e) {
			// Some invalid input was entered by the user
			// Do nothing
		}
	}
}
