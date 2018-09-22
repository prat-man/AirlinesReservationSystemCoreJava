package com.cg.ars.service;

import com.cg.ars.dto.Booking;

public interface BookingService 
{
	public void bookTicket(Booking booking);
	
	public Booking viewBookDetails(String bookingId);
	
	public Booking updateBookingDetails(Booking booking);
	
	public void cancelBooking(Booking booking);
	
	public boolean validateBookingId(String bookingId);
	
	public boolean validateEmail(String email);
	
	public boolean validateNoOfPassengers(Integer passengers);
	
	public boolean validateClassType(String classType);
	
	public boolean validateCreditCardInfo(String creditCardInfo);
	
	public boolean validateSrcCity(String srcCity);
	
	public boolean validateDestCity(String destCity);
	
}
