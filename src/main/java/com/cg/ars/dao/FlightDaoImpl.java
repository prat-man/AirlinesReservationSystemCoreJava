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
	
	public void addFlight(Flight flight)
	{
		entityManager.getTransaction().begin();
		
		entityManager.persist(flight);
		
		entityManager.getTransaction().commit();
	}

	public Flight modifyFlight(Flight flight)
	{
		entityManager.getTransaction().begin();
		
		Flight retFlight = entityManager.merge(flight);
		
		entityManager.getTransaction().commit();
		
		return retFlight;
	}

	public void deleteFlight(Flight flight)
	{
		entityManager.getTransaction().begin();
		
		entityManager.remove(flight);
		
		entityManager.getTransaction().begin();
	}

	public List<Flight> getAllFlights()
	{
		Query query = entityManager.createNamedQuery("getAllFlights");
		
		@SuppressWarnings("unchecked")
		List<Flight> flights = query.getResultList();
		
		return flights;
	}

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
}
