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
					System.out.println("=========================Welcome Executive===========================");
					System.out.println("1. Login");
					System.out.println("2. View Flight Occupancy Details");
					System.out.println("3. Exit");
					System.out.print("Enter Your Choice: ");
					
					int choice=Integer.parseInt(BR.readLine());
					switch(choice)
					{
					case 1:
						loginExecutive();
						break;
						
					case 2:
						viewFlightOccupancyDetails();
						break;
						
					default:
						return;
					}
				}
				else
				{
					
				}
			}
		
		}
		
		catch (IOException | UserException e) {
			
			e.printStackTrace();
		}
		
		
	}

	private static void loginExecutive() 
	{
		
		
	}

	private static void viewFlightOccupancyDetails() 
	{
		
		
	}
}
