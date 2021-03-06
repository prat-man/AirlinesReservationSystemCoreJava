package com.cg.ars.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.cg.ars.dto.Booking;
import com.cg.ars.dto.Flight;
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
	public List<Booking> getBookingsForUser(String username)
	{
		try {
			entityManager.getTransaction().begin();
			
			Query query = entityManager.createNamedQuery("getBookingsForUser")
							.setParameter("username", username);
			
			@SuppressWarnings("unchecked")
			List<Booking> bookings = query.getResultList();
			
			entityManager.getTransaction().commit();
			
			return bookings;
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
			
			Flight flight = entityManager.find(Flight.class, booking.getFlightNo());
			
			Integer remainingSeats;
			
			switch (booking.getClassType())
			{
				case Flight.FIRST:
								remainingSeats = flight.getFirstSeats() - booking.getNoOfPassengers();
								
								if (booking.getNoOfPassengers() > 1) {
									booking.setSeatNumber("F" + (remainingSeats + 1) + " - " + "F" + flight.getFirstSeats());
								}
								else {
									booking.setSeatNumber("F" + flight.getFirstSeats());
								}
								
								flight.setFirstSeats(remainingSeats);
								break;
				
				case Flight.BUSINESS:
								remainingSeats = flight.getBussSeats() - booking.getNoOfPassengers();
								
								if (booking.getNoOfPassengers() > 1) {
									booking.setSeatNumber("B" + (remainingSeats + 1) + " - " + "B" + flight.getBussSeats());
								}
								else {
									booking.setSeatNumber("B" + flight.getBussSeats());
								}
								
								flight.setBussSeats(remainingSeats);
								break;
					
				default:
								throw new RuntimeException("Invalid Class Type [classType=" + booking.getClassType() + "]");
			}
			
			entityManager.merge(flight);
			
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
			
			Booking booking = entityManager.find(Booking.class, bookingId);
			
			Flight flight = entityManager.find(Flight.class, booking.getFlightNo());
			
			Integer remainingSeats;
			
			switch (booking.getClassType())
			{
				case Flight.FIRST:
								remainingSeats = flight.getFirstSeats() + booking.getNoOfPassengers();
								flight.setFirstSeats(remainingSeats);
								break;
				
				case Flight.BUSINESS:
								remainingSeats = flight.getBussSeats() + booking.getNoOfPassengers();
								flight.setBussSeats(remainingSeats);
								break;
					
				default:
								throw new RuntimeException("Invalid Class Type [classType=" + booking.getClassType() + "]");
			}
			
			entityManager.merge(flight);
			
			entityManager.remove(booking);
			
			entityManager.getTransaction().commit();
		}
		catch (Exception exc) {
			entityManager.getTransaction().rollback();
			
			throw exc;
		}
	}
	
	@Override
	public int getBookingId()
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
