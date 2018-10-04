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
		/*FlightAnimation fa = new FlightAnimation();
		fa.startAnimation();
		
		LogoAnimation la = new LogoAnimation();
		la.startAnimation();*/
		
		try
		{
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
				
				int menu = Integer.parseInt(BR.readLine());
				
				if (menu == 2) {
					clearScreen();
					
					System.out.println("===============================================================================");
					System.out.println("==================== Welcome to Airline Reservation System ====================");
					System.out.println("===============================================================================");
					System.out.println("================================== Register ===================================");
					System.out.println("===============================================================================");
					System.out.println();
					
					registerUser();
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
				String username = BR.readLine();
				
				System.out.print("Password: ");
				String password = getPassword();
				
				if(U_SER.verifyUser(username, password)) 
				{
					User user = U_SER.getUser(username);
					
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
							System.out.println("10. Logout");
							System.out.print("Enter Your Choice: ");
							
							int choice = Integer.parseInt(BR.readLine());
							
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
										viewBooking();
										break;
								
								case 10:
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
							
							System.out.println("1. View flight occupancy by flight");
							System.out.println("2. View flight occupancy by route");
							System.out.println("3. Logout");
							System.out.print("Enter Your Choice: ");
							
							int choice = Integer.parseInt(BR.readLine());
							
							switch (choice)
							{
								case 1:
										viewFlightOccupancy();
										break;
									
								case 2:
										viewFlightOccupancyByRoute();
										break;
								
								case 3:
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
							System.out.println("5. Logout");
							System.out.print("Enter Your Choice: ");
							
							int choice = Integer.parseInt(BR.readLine());
							
							switch (choice)
							{
								case 1:
										bookTicket(); 
										break;
										
								case 2:
										viewBooking();
										break;
										
								case 3:
										changeEmailId();
										break;
										
								case 4:
										cancelBooking();
										break;
									
								case 5:
								default:
										break inner;
							}
							
							holdScreen();
						}
					}
				}
			}
		}
		catch (IOException | UserException e) {
			e.printStackTrace();
		}
	}

	private static void registerUser() 
	{
		User user = new User();
		
		user.setRole(User.USER);
		
		try {
			System.out.print("Username: ");
			user.setUsername(BR.readLine());
			
			System.out.print("Password: ");
			user.setPassword(getPassword());
			
			System.out.print("Mobile No.: ");
			user.setMobileNo(BR.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			U_SER.addUser(user);
			
			System.out.println("Registration Successful");
		} catch (UserException e) {
			e.printStackTrace();
		}
	}

	private static void addUser()
	{
		System.out.println("User Type");
		System.out.println("1. Admin");
		System.out.println("2. Executive");
		System.out.println("3. Back");
		System.out.print("Enter Your Choice: ");
		
		int choice = 0;
		
		try {
			choice = Integer.parseInt(BR.readLine());
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
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
					
			case 3:
			default:
					return;
		}
		
		try {
			System.out.print("Username: ");
			user.setUsername(BR.readLine());
			
			System.out.print("Password: ");
			user.setPassword(getPassword());
			
			System.out.print("Mobile No.: ");
			user.setMobileNo(BR.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			U_SER.addUser(user);
			
			System.out.println("Added User Successfully");
		} catch (UserException e) {
			e.printStackTrace();
		}
	}

	private static void viewFlights()
	{
		List<Flight> flightList = F_SER.getAllFlights();
		
		System.out.printf("\n\n%-20s %-40s %-40s %-40s %-12s %-12s %-12s %-12s %15s %15s %15s %15s\n",
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
							"Business Fare");
							
		for (Flight f : flightList)
		{
			System.out.printf("%-20s %-40s %-40s %-40s %-12s %-12s %-12s %-12s %15d %15.2f %15d %15.2f\n",
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
					f.getBussSeatsFare());
		}
	}

	private static void addFlight()
	{
		try {
			Flight flight = new Flight();
			
			System.out.print("Flight Number: ");
			flight.setFlightNo(BR.readLine());
			
			System.out.print("Airline Name: ");
			flight.setAirline(BR.readLine());
			
			System.out.print("Departure City: ");
			flight.setDepCity(BR.readLine());
			
			System.out.print("Arrival City: ");
			flight.setArrCity(BR.readLine());
			
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
			
			System.out.print("Airport Abbreviation: ");
			flight.setAirport(BR.readLine());
			
			F_SER.addFlight(flight);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	private static void modifyFlight()
	{
		try {
			System.out.println("Flight Number: ");
			
			Flight flight = F_SER.getFlight(BR.readLine());
			
			System.out.printf("%s%s%s%s%s%s%s%s%d%lf%d%lf",
								flight.getFlightNo(),
								flight.getAirline(),
								flight.getArrCity(),
								flight.getDepCity(),
								TIME_FORMAT.format(flight.getArrTime()),
								TIME_FORMAT.format(flight.getDepTime()),
								DATE_FORMAT.format(flight.getArrDate()),
								DATE_FORMAT.format(flight.getDepDate()),
								flight.getFirstSeats(),
								flight.getFirstSeatsFare(),
								flight.getBussSeats(),
								flight.getBussSeatsFare(),
								flight.getBussSeatsFare());
			
			System.out.print("Flight Number: ");
			flight.setFlightNo(BR.readLine());
			
			System.out.print("Airline Name: ");
			flight.setAirline(BR.readLine());
			
			System.out.print("Departure City: ");
			flight.setDepCity(BR.readLine());
			
			System.out.print("Arrival City: ");
			flight.setArrCity(BR.readLine());
			
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
			
			System.out.print("Airport Abbreviation: ");
			flight.setAirport(BR.readLine());
			
			F_SER.modifyFlight(flight);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void deleteFlight() 
	{
		try {
			System.out.print("Flight Number: ");
			
			Flight flight =  F_SER.getFlight(BR.readLine());
			
			System.out.printf("%s%s%s%s%s%s%s%s%d%lf%d%lf",
					flight.getFlightNo(),
					flight.getAirline(),
					flight.getArrCity(),
					flight.getDepCity(),
					TIME_FORMAT.format(flight.getArrTime()),
					TIME_FORMAT.format(flight.getDepTime()),
					DATE_FORMAT.format(flight.getArrDate()),
					DATE_FORMAT.format(flight.getDepDate()),
					flight.getFirstSeats(),
					flight.getFirstSeatsFare(),
					flight.getBussSeats(),
					flight.getBussSeatsFare());
			
			F_SER.deleteFlight(flight.getFlightNo());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void viewFlightsByDate() 
	{
		try 
		{
			System.out.print("Travel Date (dd-MM-yyyy): ");
			Date date = new Date(DATE_FORMAT.parse(BR.readLine()).getTime());
			
			System.out.print("Departure City: ");
			String depCity = BR.readLine();
			
			System.out.print("Arrival City: ");
			String arrCity = BR.readLine();
			
			List<Flight> flights = F_SER.getFlights(date, depCity, arrCity);
			
			System.out.printf("\n\n%-20s %-40s %-40s %-40s %-12s %-12s %-12s %-12s %15s %15s %15s %15s\n",
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
					"Airport");
			
			for (Flight f : flights)
			{
				System.out.printf("%-20s %-40s %-40s %-40s %-12s %-12s %-12s %-12s %15d %15.2f %15d %15.2f\n",
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
						f.getBussSeatsFare());
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void addAirport() 
	{
		try 
		{
			Airport airport = new Airport();
			
			System.out.print("Airport Name: ");
			airport.setAirportName(BR.readLine());
			
			System.out.print("Abbreviation: ");
			airport.setAbbreviation(BR.readLine());
			
			System.out.print("Location: ");
			airport.setLocation(BR.readLine());
			
			A_SER.addAirport(airport);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void viewAirports() 
	{
		List<Airport> Airports =  A_SER.getAllAirports();
		for (Airport a : Airports)
		{
			System.out.printf("%s%s%s%s%s%s%s%s%d%lf%d%lf",
					a.getAirportName(),
					a.getAbbreviation(),
					a.getLocation());
		}
	}
  
	private static void viewFlightOccupancy() 
	{
		System.out.print("Flight number: ");
		
		String flightNo = null;
		
		try {
			flightNo = BR.readLine();
		}
		catch (IOException e) {
			System.err.println("Invalid Flight Number");
		}
		
		double occupancy = F_SER.getOccupancy(flightNo);
		
		System.out.println("\nFlight Occupancy: " + occupancy);
	}

	private static void viewFlightOccupancyByRoute()
	{
		String depCity = null;
		String arrCity = null;
	      
		try {
			System.out.print("Departure City: ");
			depCity = BR.readLine();
			
			System.out.print("Arrival City: ");
			arrCity = BR.readLine();
		} 
		catch (IOException e) {
			System.err.println("Invalid City Name");
			System.exit(0);
		}
		
		double occupancy = F_SER.getOccupancy(depCity, arrCity);
		
		System.out.println("\nFlight Occupancy: " + occupancy);
	}

	private static void bookTicket()
	{
		try 
		{
			System.out.print("Travel Date (dd-MM-yyyy): ");
			Date date = new Date(DATE_FORMAT.parse(BR.readLine()).getTime());
        
			System.out.print("City From: ");
			String depCity = BR.readLine();
        
			System.out.print("City To: ");
			String arrCity = BR.readLine();
      
			List<Flight> flightList = F_SER.getFlights(date, depCity, arrCity);
      
			for(Flight flight : flightList) 
			{
				System.out.printf("%s%s%s%s%s%s%s%s%d%lf%d%lf",
						flight.getFlightNo(),
						flight.getAirline(),
						flight.getArrCity(),
						flight.getDepCity(),
						TIME_FORMAT.format(flight.getArrTime()),
						TIME_FORMAT.format(flight.getDepTime()),
						DATE_FORMAT.format(flight.getArrDate()),
						DATE_FORMAT.format(flight.getDepDate()),
						flight.getFirstSeats(),
						flight.getFirstSeatsFare(),
						flight.getBussSeats(),
						flight.getBussSeatsFare());
			}
			
			Booking booking = new Booking();
			
			System.out.print("Flight Number:");
			String flightNo = BR.readLine();
			booking.setFlightNo(flightNo);
			
			booking.setBookingId(B_SER.generateBookingId(flightNo));
			
			Flight flight = F_SER.getFlight(flightNo);
			
			if (flight == null) {
				System.err.println("Invalid Flight Number [flightNo=" + flightNo + "]");
			}
			
			System.out.print("Email ID: ");
			booking.setCustEmail(BR.readLine());
			
			System.out.print("Number of Seats Required: ");
			int noOfPassengers = Integer.parseInt(BR.readLine());
			booking.setNoOfPassengers(noOfPassengers);
			
			System.out.print("Class Type:");
			String classType = BR.readLine();
			booking.setClassType(classType);
			
			double totalFare = noOfPassengers * F_SER.getFare(flightNo, classType);
			booking.setTotalFare(totalFare);
			
			System.out.print("Credit Card Number: ");
			booking.setCreditCardInfo(BR.readLine());
			
			booking.setSrcCity(flight.getDepCity());
		
			booking.setDestCity(flight.getArrCity());
			
			B_SER.bookTicket(booking);
			
			System.out.println("\nBooking Successful\n");
			
			System.out.println("Booking ID: " + booking.getBookingId());
			
			System.out.println("Seat Number: " + booking.getSeatNumber());
		}
		catch(IOException | ParseException | FlightException | BookingException e) {
			System.err.println(e.getMessage());
		}
	}

	private static void viewBooking() 
	{
		try {
			System.out.print("Booking ID:");
			
			Booking booking = B_SER.getBooking(BR.readLine());
			
			System.out.printf("%s%s%s%s%s%s%d%d%lf",
					booking.getBookingId(),
					booking.getFlightNo(),
					booking.getCustEmail(),
					booking.getSrcCity(),
					booking.getDestCity(),
					booking.getClassType(),
					booking.getNoOfPassengers(),
					booking.getSeatNumber(),
					booking.getTotalFare());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void changeEmailId() 
	{
		try 
		{
			System.out.println("Booking ID: ");
			String bookingId = BR.readLine();
			
			Booking booking = B_SER.getBooking(bookingId);
			
			System.out.print("Email ID: ");
			booking.setCustEmail(BR.readLine());
			
			B_SER.updateBooking(booking);
			
			System.out.println("Email ID changed successfully");
		} 
		catch (IOException | BookingException e) {
			e.printStackTrace();
		}
	}

	private static void cancelBooking() 
	{
		try
		{
			System.out.print("Booking ID: ");
			String bookingId = BR.readLine();
			
			if (B_SER.validateBookingId(bookingId)) {
				B_SER.cancelBooking(bookingId);
				System.out.println("Booking with [bookingId=" + bookingId + "] cancelled successfully");
			}
		} 
		catch (IOException | BookingException e) {
			e.printStackTrace();
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
				System.err.println(e.getMessage());
			}
		}
		
		return password;
	}
	
	public static void clearScreen()
	{
		// try to clear console
		try {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch (InterruptedException | IOException exc) {
			exc.printStackTrace();
		}
	}
	
	public static void holdScreen()
	{
		System.out.print("\n\nPress [ENTER] to continue ...");
		
		try {
			BR.readLine();
		} catch (IOException exc) {
			exc.printStackTrace();
		}
	}
}
