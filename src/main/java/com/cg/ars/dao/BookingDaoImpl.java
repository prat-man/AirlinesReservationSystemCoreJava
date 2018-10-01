package com.cg.ars.dao;

import javax.persistence.EntityManager;

import com.cg.ars.dto.Booking;
import com.cg.ars.exception.BookingException;
import com.cg.ars.util.JPAUtil;

public class BookingDaoImpl implements BookingDao
{
	private EntityManager entityManager;

	public BookingDaoImpl()
	{
		entityManager = JPAUtil.getEntityManager();
	}
	
	@Override
	public Booking getBooking(String bookingId)
	{
		Booking booking = entityManager.find(Booking.class, bookingId);
		
		return booking;
	}

	@Override
	public Booking updateBooking(Booking booking)
	{	
		Booking book = entityManager.merge(booking);
		
		return book;
	}

	@Override
	public void bookTicket(Booking booking)
	{
		entityManager.persist(booking);
	}

	@Override
	public void cancelBooking(String bookingId)
	{
		Booking booking = this.getBooking(bookingId);
		
		entityManager.remove(booking);
	}

	@Override
	public String getBookingId(String flightNo) throws BookingException {
		
		return null;
	}
}
