package com.cg.ars.service;

import com.cg.ars.dto.Booking;

public interface BookingService 
{
	public void bookTicket(Booking booking);
    public Booking viewBookDetails(String bookingId);
    public Booking updateBookingDetails(Booking booking);
    public void cancelBooking(Booking booking);
}
