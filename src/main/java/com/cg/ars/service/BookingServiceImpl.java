package com.cg.ars.service;

import java.util.Arrays;
import java.util.regex.Pattern;

import com.cg.ars.dao.BookingDao;
import com.cg.ars.dao.BookingDaoImpl;
import com.cg.ars.dto.Booking;
import com.cg.ars.dto.Flight;
import com.cg.ars.exception.BookingException;

public class BookingServiceImpl implements BookingService 
{
	private BookingDao bdao;
	
	public BookingServiceImpl() 
	{
		bdao = new BookingDaoImpl();
	}

	public void bookTicket(Booking booking) 
	{
		bdao.bookTicket(booking);
	}

	public Booking viewBookDetails(String bookingId) 
	{
		return bdao.viewBookDetails(bookingId);
	}

	public Booking updateBookingDetails(Booking booking) 
	{
		return bdao.updateBookingDetails(booking);
	}

	public void cancelBooking(Booking booking) 
	{
		bdao.cancelBooking(booking);
	}

	public boolean validateBookingId(String bookingId) throws BookingException 
	{
		String pattern = "[A-Z]{3,4}[0-9]{4,6}";
		
		if (Pattern.matches(pattern, bookingId)) {
			return true;
		}
		else {
			throw new BookingException("Invalid Booking ID");
		}
	}

	public boolean validateEmail(String email) throws BookingException 
	{
		String pattern = "(^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$)";
		
		if (Pattern.matches(pattern, email)) {
			return true;
		}
		else {
			throw new BookingException("Invalid Email ID");
		}
	}

	public boolean validateNoOfPassengers(Flight flight, String classType, Integer passengers) throws BookingException 
	{
		int availableSeats;
		
		// get number of seats available by class type
		switch (classType) {
			case "First":
				availableSeats = flight.getFirstSeats();
				break;
			
			case "Business":
				availableSeats = flight.getBussSeats();
				break;
				
			default:
				availableSeats = 0;
				break;
		}
		
		// if number of passengers are one or more
		// and number of passengers are less than number of seats available
		if (passengers > 0 && passengers <= availableSeats) {
			return true;
		}
		else {
			throw new BookingException("Invalid number of passengers");
		}
	}

	public boolean validateClassType(String classType) throws BookingException 
	{
		String[] classes = {"First", "Business"};
		
		if (Arrays.asList(classes).contains(classType)) {
			return true;
		}
		else {
			throw new BookingException("Invalid Class Type");
		}
	}

	public boolean validateCreditCardInfo(String creditCardInfo) throws BookingException 
	{
		String pattern = "[0-9]{16}";
		
		if (Pattern.matches(pattern, creditCardInfo)) {
			return true;
		}
		else {
			throw new BookingException("Invalid Credit Card Information");
		}
	}

	public boolean validateCity(String city) throws BookingException 
	{
		String pattern = "([A-Z][a-z]+ )*[A-Z][a-z]+";
		
		if (Pattern.matches(pattern, city)) {
			return true;
		}
		else {
			throw new BookingException("Invalid City Name");
		}
	}
}
