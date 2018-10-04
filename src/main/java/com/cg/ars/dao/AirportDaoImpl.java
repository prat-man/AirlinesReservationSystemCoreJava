package com.cg.ars.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.cg.ars.dto.Airport;
import com.cg.ars.util.JPAUtil;

public class AirportDaoImpl implements AirportDao
{
	private EntityManager entityManager;
	
	private Logger logger;

	public AirportDaoImpl()
	{
		entityManager = JPAUtil.getEntityManager();
		
		logger = Logger.getLogger(this.getClass());
		
		PropertyConfigurator.configure("log4j.properties");
	}

	@Override
	public void addAirport(Airport airport)
	{
		entityManager.persist(airport);
	}
	
	@Override
	public Airport getAirport(String airportId)
	{
		Airport airport = entityManager.find(Airport.class, airportId);
		
		entityManager.remove(airport);
		
		return airport;
	}

	@Override
	public List<Airport> getAllAirports()
	{
		Query query = entityManager.createNamedQuery("getAllAirports");
		
		@SuppressWarnings("unchecked")
		List<Airport> airports = query.getResultList();
		
		return airports;
	}
	
	@Override
	public void deleteAirport(String airportId)
	{
		Airport airport = this.getAirport(airportId);
		
		entityManager.remove(airport);
	}
}
