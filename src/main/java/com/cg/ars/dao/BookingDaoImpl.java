package com.cg.ars.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

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
		entityManager.getTransaction().begin();
		
		Booking booking = entityManager.find(Booking.class, bookingId);
		
		entityManager.getTransaction().commit();
		
		return booking;
	}

	@Override
	public void updateBooking(Booking booking)
	{
		entityManager.getTransaction().begin();
		
		entityManager.merge(booking);
		
		entityManager.getTransaction().commit();
	}

	@Override
	public void bookTicket(Booking booking)
	{
		entityManager.getTransaction().begin();
		
		entityManager.persist(booking);
		
		entityManager.getTransaction().commit();
	}

	@Override
	public void cancelBooking(String bookingId)
	{
		entityManager.getTransaction().begin();
		
		Booking booking = this.getBooking(bookingId);
		
		entityManager.remove(booking);
		
		entityManager.getTransaction().commit();
	}
	
	@Override
	public int getBookingId() throws BookingException
	{
		entityManager.getTransaction().begin();
		
		Query query = entityManager.createNativeQuery("SELECT BOOKING_SEQUENCE.NEXTVAL FROM DUAL");
		
		int bookingId =  (int) query.getSingleResult();
		
		entityManager.getTransaction().commit();
		
		return bookingId;
	}
}
