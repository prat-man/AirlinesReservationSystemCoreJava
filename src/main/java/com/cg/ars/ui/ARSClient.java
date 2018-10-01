package com.cg.ars.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.SimpleTimeZone;

import com.cg.ars.dto.Flight;
import com.cg.ars.dto.User;
import com.cg.ars.exception.FlightException;
import com.cg.ars.exception.UserException;
import com.cg.ars.service.FlightService;
import com.cg.ars.service.FlightServiceImpl;
import com.cg.ars.service.UserService;
import com.cg.ars.service.UserServiceImpl;

public class ARSClient
{
	static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
	
	static final UserService U_SER = new UserServiceImpl();
	static final FlightService F_SER = new FlightServiceImpl();
	
	public static void main(String[] args)
	{
		FlightAnimation fa = new FlightAnimation();
		fa.startAnimation();
		
		LogoAnimation la = new LogoAnimation();
		la.startAnimation();
		
		
		try 
		{
			System.out.println("========================Welcome to Airline Reservation System=========================");
			System.out.println("========================Login User=====================");
			System.out.println();
			System.out.println("Username:");
			String username=BR.readLine();
			System.out.println("Password:");
			String password=BR.readLine();
			
			boolean flag = U_SER.verifyUser(username, password);
			
			if(flag==true) {
				User user = U_SER.getUser(username);
				
				String role=user.getRole();
				
				if(role.equals("Admin")) {
					
				}
				else if(role.equals("Executive")) {
					System.out.println("=========================Welcome Executive===========================");
					System.out.println("1. View Flight Details for a particular period");
					System.out.println("2. View Flight Occupancy Details from one region to another region");
					System.out.println("3. Exit");
					System.out.print("Enter Your Choice: ");
					
					int choice=Integer.parseInt(BR.readLine());
					switch(choice)
					{
					case 1:
						viewDetailsBasedOnDate();
						break;
						
					case 2:
						viewDetailsBasedOnRegion();
						break;
						
					default:
						return;
					}
				}
				else {
					System.out.println("*******Welcome User!!Choose your option*******");
					System.out.println("1:Book a Ticket\n2:View Booking Details\n3:Update Booking Details\n4:Cancel Booking");
					System.out.println("Enter option:");
					int choice=Integer.parseInt(BR.readLine());
					
					switch(choice) {
						case 1: bookTicket(); 
								break;
								
						case 2: viewBooking();
								break;
								
						case 3: updateBooking();
								break;
								
						case 4: cancelBooking();
								break;
								
						default: System.out.println("*****THANK YOU*****");
								
						return;
					}
					
				}
			}
		
		}
		
		catch (IOException | UserException e) {
			
			e.printStackTrace();
		}
		
	}

	private static void viewDetailsBasedOnDate() 
	{
		List<Flight> flightsByDate;
		String flightNo = null;
		System.out.println("=====================================================================");
		System.out.println("Enter the flight number for which the details have to be entered");
		
		try {
			flightNo = BR.readLine();
		} catch (IOException e)
		{
			System.out.println("Invalid Flight Number");
		}
		System.out.println("=====================================================================");
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm");
		
	    Date startDate = null;
	    Date endDate = null;
	    
		try {
			System.out.println("Enter the start date of the flight");
			startDate = new Date(dateFormat.parse(BR.readLine()).getTime());
			
			System.out.println("Enter the end date of the flight");
			endDate = new Date(timeFormat.parse(BR.readLine()).getTime());
			} 
		
		catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			System.out.println("enter the correct date according to the format dd-mm-yyyy");
		}
		
	      
		flightsByDate = F_SER.getOccupancy(flightNo, startDate, endDate);
		
		System.out.println("Flight Details are : ");
		for(Flight f: flightsByDate)
		{
			System.out.printf("%s%s%s%s%s%s%s%s%d%lf%d%lf",
								f.getFlightNo(),
								f.getAirline(),
								f.getArrCity(),
								f.getDepCity(),
								timeFormat.format(f.getArrTime()),
								timeFormat.format(f.getDepTime()),
								dateFormat.format(f.getArrDate()),
								dateFormat.format(f.getDepDate()),
								f.getFirstSeats(),
								f.getFirstSeatsFare(),
								f.getBussSeats(),
								f.getBussSeatsFare());
		}
		
	}

	private static void viewDetailsBasedOnRegion() {
		// TODO Auto-generated method stub
		List<Flight> flightsByRegion;
		
	      
		flightsByDate = F_SER.getOccupancy(depCity, arrCity)
		
		System.out.println("Flight Details are : ");
		for(Flight f: flightsByDate)
		{
			System.out.printf("%s%s%s%s%s%s%s%s%d%lf%d%lf",
								f.getFlightNo(),
								f.getAirline(),
								f.getArrCity(),
								f.getDepCity(),
								timeFormat.format(f.getArrTime()),
								timeFormat.format(f.getDepTime()),
								dateFormat.format(f.getArrDate()),
								dateFormat.format(f.getDepDate()),
								f.getFirstSeats(),
								f.getFirstSeatsFare(),
								f.getBussSeats(),
								f.getBussSeatsFare());
		}
	}

	private static void bookTicket() 
	{
		// TODO: Implement book ticket functionality in client
	}

	private static void viewBooking() 
	{
		// TODO: Implement view booking functionality in client
	}

	private static void updateBooking() 
	{
		// TODO: Implement update booking functionality in client
	}

	private static void cancelBooking() 
	{
		// TODO: Implement cancel booking functionality in client
	}

	
}
