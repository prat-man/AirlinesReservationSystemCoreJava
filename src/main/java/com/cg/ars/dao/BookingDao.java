package com.cg.ars.dao;

import com.cg.ars.dto.Booking;

public interface BookingDao
{
	public void bookTicket(Booking booking);
	
	public Booking viewBookDetails(String bookingId);
	
	public Booking updateBookingDetails(Booking booking);
	
	public void cancelBooking(Booking booking);
}
