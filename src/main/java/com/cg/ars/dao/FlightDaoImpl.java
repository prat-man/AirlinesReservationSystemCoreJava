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
			
			Long sumOfPassengers = entityManager.createNamedQuery("getSumOfPassengersByFlightNo", Long.class)
													.setParameter("flightNo", flightNo)
													.getSingleResult();
			
			Flight flight = entityManager.find(Flight.class, flightNo);
			
			Long seatCount = Long.valueOf(flight.getFirstSeats()) + Long.valueOf(flight.getBussSeats());
			
			entityManager.getTransaction().commit();
							
			return Double.valueOf(sumOfPassengers) / Double.valueOf(seatCount);
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
			
			Long sumOfPassengers = entityManager.createNamedQuery("getSumOfPassengersByRoute", Long.class)
													.setParameter("depCity", depCity)
													.setParameter("arrCity", arrCity)
													.getSingleResult();
	
	
			Long seatCount = entityManager.createNamedQuery("getSeatCount", Long.class)
													.setParameter("depCity", depCity)
													.setParameter("arrCity", arrCity)
													.getSingleResult();
			
			entityManager.getTransaction().commit();
													
			return Double.valueOf(sumOfPassengers) / Double.valueOf(seatCount);
		}
		catch (Exception exc) {
			entityManager.getTransaction().rollback();
			
			throw exc;
		}
	}
}
