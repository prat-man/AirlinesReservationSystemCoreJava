package com.cg.ars.dao;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.cg.ars.dto.Flight;
import com.cg.ars.util.JPAUtil;

public class FlightDaoImpl implements FlightDao
{
	private EntityManager entityManager;

	public FlightDaoImpl()
	{
		entityManager = JPAUtil.getEntityManager();
	}
	
	@Override
	public void addFlight(Flight flight)
	{
		try {
			entityManager.getTransaction().begin();
			
			entityManager.persist(flight);
			
			entityManager.getTransaction().commit();
		}
		catch (Exception exc) {
			entityManager.getTransaction().rollback();
			
			throw exc;
		}
	}

	@Override
	public Flight modifyFlight(Flight flight)
	{
		try {
			entityManager.getTransaction().begin();
			
			Flight retFlight = entityManager.merge(flight);
			
			entityManager.getTransaction().commit();
			
			return retFlight;
		}
		catch (Exception exc) {
			entityManager.getTransaction().rollback();
			
			throw exc;
		}
	}

	@Override
	public void deleteFlight(String flightNo)
	{
		try {
			entityManager.getTransaction().begin();
			
			Flight flight = entityManager.find(Flight.class, flightNo);
			
			entityManager.remove(flight);
			
			entityManager.getTransaction().commit();
		}
		catch (Exception exc) {
			entityManager.getTransaction().rollback();
			
			throw exc;
		}
	}

	@Override
	public List<Flight> getAllFlights()
	{
		try {
			entityManager.getTransaction().begin();
			
			Query query = entityManager.createNamedQuery("getAllFlights");
			
			@SuppressWarnings("unchecked")
			List<Flight> flights = query.getResultList();
			
			entityManager.getTransaction().commit();
			
			return flights;
		}
		catch (Exception exc) {
			entityManager.getTransaction().rollback();
			
			throw exc;
		}
	}

	@Override
	public List<Flight> getFlights(Date date, String depCity, String arrCity)
	{
		try {
			entityManager.getTransaction().begin();
			
			Query query = entityManager.createNamedQuery("getFlights")
							.setParameter("depDate", date)
							.setParameter("depCity", depCity)
							.setParameter("arrCity", arrCity);
			
			@SuppressWarnings("unchecked")
			List<Flight> flights = query.getResultList();
			
			entityManager.getTransaction().commit();
			
			return flights;
		}
		catch (Exception exc) {
			entityManager.getTransaction().rollback();
			
			throw exc;
		}
	}

	@Override
	public Flight getFlight(String flightNo)
	{
		try {
			entityManager.getTransaction().begin();
			
			Flight flight = entityManager.find(Flight.class, flightNo);
			
			entityManager.getTransaction().commit();
			
			return flight;
		}
		catch (Exception exc) {
			entityManager.getTransaction().rollback();
			
			throw exc;
		}
	}
	
	@Override
	public Double getOccupancy(String flightNo)
	{
		try {
			entityManager.getTransaction().begin();
			
			Double sumOfPassengers = entityManager.createNamedQuery("Booking.getSumOfPassengersByFlightNo", Double.class)
													.setParameter("flightNo", flightNo)
													.getSingleResult();
			
			Flight flight = this.getFlight(flightNo);
			
			Double seatCount = flight.getFirstSeatsFare() + flight.getBussSeats();
			
			entityManager.getTransaction().commit();
							
			return sumOfPassengers / seatCount;
		}
		catch (Exception exc) {
			entityManager.getTransaction().rollback();
			
			throw exc;
		}
	}

	@Override
	public Double getOccupancy(String depCity, String arrCity)
	{
		try {
			entityManager.getTransaction().begin();
			
			Double sumOfPassengers = entityManager.createNamedQuery("Booking.getSumOfPassengersByRoute", Double.class)
													.setParameter("depCity", depCity)
													.setParameter("arrCity", arrCity)
													.getSingleResult();
	
	
			Double seatCount = entityManager.createNamedQuery("Flight.getSeatCount", Double.class)
													.setParameter("depCity", depCity)
													.setParameter("arrCity", arrCity)
													.getSingleResult();
			
			entityManager.getTransaction().commit();
													
			return sumOfPassengers / seatCount;
		}
		catch (Exception exc) {
			entityManager.getTransaction().rollback();
			
			throw exc;
		}
	}
}
