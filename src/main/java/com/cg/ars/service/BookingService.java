package com.cg.ars.service;

import com.cg.ars.dto.Booking;
import com.cg.ars.dto.Flight;
import com.cg.ars.exception.BookingException;

public interface BookingService 
{
	public void bookTicket(Booking booking) throws BookingException;
	
	public Booking getBooking(String bookingId) throws BookingException;
	
	public void updateBooking(Booking booking) throws BookingException;
	
	public void cancelBooking(String bookingId) throws BookingException;
	
	public String generateBookingId(String flightNo) throws BookingException;
	
	public boolean validateBookingId(String bookingId) throws BookingException;
	
	public boolean validateNoOfPassengers(Flight flight, String classType, Integer passengers) throws BookingException;
	
	public boolean validateClassType(String classType) throws BookingException;
	
	public boolean validateCreditCardInfo(String creditCardInfo) throws BookingException;
	
	public boolean validateCity(String city) throws BookingException;
	
}
