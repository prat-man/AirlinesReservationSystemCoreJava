package com.cg.ars.dao;

import com.cg.ars.dto.Booking;
import com.cg.ars.exception.BookingException;

public interface BookingDao
{
	public void bookTicket(Booking booking);
	
	public Booking getBooking(String bookingId);
	
	public Booking updateBooking(Booking booking);
	
	public void cancelBooking(String bookingId);
	
	public int getBookingId() throws BookingException;
}
