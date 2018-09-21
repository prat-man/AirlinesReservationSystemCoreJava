package com.cg.ars.service;

import com.cg.ars.dao.BookingDao;
import com.cg.ars.dao.BookingDaoImpl;
import com.cg.ars.dto.Booking;

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
}
