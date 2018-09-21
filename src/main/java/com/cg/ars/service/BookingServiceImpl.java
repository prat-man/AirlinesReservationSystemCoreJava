package com.cg.ars.service;

import com.cg.ars.dao.BookingDao;
import com.cg.ars.dao.BookingDaoImpl;
import com.cg.ars.dto.Booking;

public class BookingServiceImpl implements BookingService 
{
	BookingDao bdao;
	
	public BookingServiceImpl() 
	{
		super();
		bdao=new BookingDaoImpl();
	}

	public void bookTicket(Booking booking) 
	{
		bdao.bookTicket(booking);
	}

	public Booking viewBookDetails(String bookingId) 
	{
		Booking booking=bdao.viewBookDetails(bookingId);
		return booking;
	}

	public Booking updateBookingDetails(Booking booking) 
	{
		Booking updatebooking=bdao.updateBookingDetails(booking);
		return updatebooking;
	}

	public void cancelBooking(Booking booking) 
	{
		bdao.cancelBooking(booking);
	}
}
