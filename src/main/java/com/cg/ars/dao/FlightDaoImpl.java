package com.cg.ars.dao;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.cg.ars.dto.Booking;
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
		entityManager.getTransaction().begin();
		
		entityManager.persist(flight);
		
		entityManager.getTransaction().commit();
	}

	@Override
	public Flight modifyFlight(Flight flight)
	{
		entityManager.getTransaction().begin();
		
		Flight retFlight = entityManager.merge(flight);
		
		entityManager.getTransaction().commit();
		
		return retFlight;
	}

	@Override
	public void deleteFlight(Flight flight)
	{
		entityManager.getTransaction().begin();
		
		entityManager.remove(flight);
		
		entityManager.getTransaction().begin();
	}

	@Override
	public List<Flight> getAllFlights()
	{
		Query query = entityManager.createNamedQuery("getAllFlights");
		
		@SuppressWarnings("unchecked")
		List<Flight> flights = query.getResultList();
		
		return flights;
	}

	@Override
	public List<Flight> getFlights(Date date, String depCity, String arrCity)
	{
		Query query = entityManager.createNamedQuery("getFlights")
						.setParameter("depDate", date)
						.setParameter("depCity", depCity)
						.setParameter("arrCity", arrCity);
		
		@SuppressWarnings("unchecked")
		List<Flight> flights = query.getResultList();
		
		return flights;
	}

	@Override
	public Flight getFlight(String flightNo)
	{
		Flight flight = entityManager.find(Flight.class, flightNo);
		return flight;
	}

	@Override
	public Double getFare(Flight flight, String classType)
	{
		double fare;
		
		switch (classType) {
			case "First":
				fare = flight.getFirstSeatsFare();
				break;
			
			case "Business":
				fare = flight.getBussSeatsFare();
				break;
				
			default:
				fare = -1;
				break;
		}
		
		return fare;
	}
	
	@Override
	public Double getOccupancy(String flightNo)
	{
		Double sumOfPassengers = entityManager.createNamedQuery("Booking.getSumOfPassengersByFlightNo", Double.class)
												.setParameter("flightNo", flightNo)
												.getSingleResult();
		
		Flight flight = this.getFlight(flightNo);
		
		Double seatCount = flight.getFirstSeatsFare() + flight.getBussSeats();
						
		return sumOfPassengers / seatCount;
	}

	@Override
	public Double getOccupancy(String depCity, String arrCity)
	{
		Double sumOfPassengers = entityManager.createNamedQuery("Booking.getSumOfPassengersByRoute", Double.class)
												.setParameter("depCity", depCity)
												.setParameter("arrCity", arrCity)
												.getSingleResult();


		Double seatCount = entityManager.createNamedQuery("Flight.getSeatCount", Double.class)
												.setParameter("depCity", depCity)
												.setParameter("arrCity", arrCity)
												.getSingleResult();
												
		return sumOfPassengers / seatCount;
	}
}
