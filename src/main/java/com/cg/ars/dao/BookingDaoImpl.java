package com.cg.ars.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.cg.ars.dto.Booking;
import com.cg.ars.exception.BookingException;
import com.cg.ars.util.JPAUtil;

public class BookingDaoImpl implements BookingDao
{
	private EntityManager entityManager;
	
	private Logger logger;

	public BookingDaoImpl()
	{
		entityManager = JPAUtil.getEntityManager();
		
		logger = Logger.getLogger(this.getClass());
		
		PropertyConfigurator.configure("log4j.properties");
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
	public int getBookingId() throws BookingException
	{
		Query query = entityManager.createNativeQuery("SELECT BOOKING_SEQUENCE.NEXTVAL FROM DUAL");
		return (int) query.getSingleResult();
	}
}
