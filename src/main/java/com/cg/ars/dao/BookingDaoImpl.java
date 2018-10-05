package com.cg.ars.dao;

import java.math.BigDecimal;

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
		try {
			entityManager.getTransaction().begin();
			
			Booking booking = entityManager.find(Booking.class, bookingId);
			
			entityManager.getTransaction().commit();
			
			return booking;
		}
		catch (Exception exc) {
			entityManager.getTransaction().rollback();
			
			throw exc;
		}
	}

	@Override
	public void updateBooking(Booking booking)
	{
		try {
			entityManager.getTransaction().begin();
			
			entityManager.merge(booking);
			
			entityManager.getTransaction().commit();
		}
		catch (Exception exc) {
			entityManager.getTransaction().rollback();
			
			throw exc;
		}
	}

	@Override
	public void bookTicket(Booking booking)
	{
		try {
			entityManager.getTransaction().begin();
			
			entityManager.persist(booking);
			
			entityManager.getTransaction().commit();
		}
		catch (Exception exc) {
			entityManager.getTransaction().rollback();
			
			throw exc;
		}
	}

	@Override
	public void cancelBooking(String bookingId)
	{
		try {
			entityManager.getTransaction().begin();
			
			Booking booking = this.getBooking(bookingId);
			
			entityManager.remove(booking);
			
			entityManager.getTransaction().commit();
		}
		catch (Exception exc) {
			entityManager.getTransaction().rollback();
			
			throw exc;
		}
	}
	
	@Override
	public int getBookingId() throws BookingException
	{
		try {
			entityManager.getTransaction().begin();
			
			Query query = entityManager.createNativeQuery("SELECT BOOKING_SEQUENCE.NEXTVAL FROM DUAL");
			
			int bookingId =  ((BigDecimal) query.getSingleResult()).intValue();
			
			entityManager.getTransaction().commit();
			
			return bookingId;
		}
		catch (Exception exc) {
			entityManager.getTransaction().rollback();
			
			throw exc;
		}
	}
}
