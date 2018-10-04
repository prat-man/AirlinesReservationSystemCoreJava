package com.cg.ars.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.cg.ars.dto.Airport;
import com.cg.ars.util.JPAUtil;

public class AirportDaoImpl implements AirportDao
{
	private EntityManager entityManager;

	public AirportDaoImpl()
	{
		entityManager = JPAUtil.getEntityManager();
	}

	@Override
	public void addAirport(Airport airport)
	{
		entityManager.getTransaction().begin();
		
		entityManager.persist(airport);
		
		entityManager.getTransaction().commit();
	}
	
	@Override
	public Airport getAirport(String airportId)
	{
		entityManager.getTransaction().begin();
		
		Airport airport = entityManager.find(Airport.class, airportId);
		
		entityManager.remove(airport);
		
		entityManager.getTransaction().commit();
		
		return airport;
	}

	@Override
	public List<Airport> getAllAirports()
	{
		entityManager.getTransaction().begin();
		
		Query query = entityManager.createNamedQuery("getAllAirports");
		
		@SuppressWarnings("unchecked")
		List<Airport> airports = query.getResultList();
		
		entityManager.getTransaction().commit();
		
		return airports;
	}
	
	@Override
	public void deleteAirport(String airportId)
	{
		entityManager.getTransaction().begin();
		
		Airport airport = this.getAirport(airportId);
		
		entityManager.remove(airport);
		
		entityManager.getTransaction().commit();
	}
}
