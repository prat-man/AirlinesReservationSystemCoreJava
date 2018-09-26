package com.cg.ars.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.cg.ars.dto.User;
import com.cg.ars.exception.UserException;
import com.cg.ars.service.UserService;
import com.cg.ars.service.UserServiceImpl;

public class ARSClient
{
	static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args)
	{
		FlightAnimation fa = new FlightAnimation();
		fa.startAnimation();
		
		LogoAnimation la = new LogoAnimation();
		la.startAnimation();
		
		UserService userService=new UserServiceImpl();
		try 
		{
			System.out.println("========================Welcome to Airline Reservation System=========================");
			System.out.println("========================Login User=====================");
			System.out.println();
			System.out.println("Username:");
			String username=BR.readLine();
			System.out.println("Password:");
			String password=BR.readLine();
			boolean flag=userService.verifyUser(username, password);
			if(flag==true) 
			{
				User user=userService.getUser(username);
				String role=user.getRole();
				if(role.equals("Admin")) 
				{
					
				}
				else if(role.equals("Executive"))
				{
					
				}
				else
				{
					System.out.println("*******Welcome User!!Choose your option*******");
					System.out.println("1:Book a Ticket\n2:View Booking Details\n3:Update Booking Details\n4:Cancel Booking");
					System.out.println("Enter option:");
					int choice=Integer.parseInt(BR.readLine());
					switch(choice)
					{
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
