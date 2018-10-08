package com.cg.ars.dao;

import java.util.List;

import com.cg.ars.dto.Booking;
import com.cg.ars.exception.BookingException;

public interface BookingDao
{
	public void bookTicket(Booking booking) throws BookingException;
	
	public Booking getBooking(String bookingId);
	
	public List<Booking> getBookingsForUser(String username);
	
	public void updateBooking(Booking booking);
	
	public void cancelBooking(String bookingId);
	
	public int getBookingId() throws BookingException;
}
