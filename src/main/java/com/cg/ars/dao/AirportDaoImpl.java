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
	public List<Airport> getAllAirports()
	{
		Query query = entityManager.createNamedQuery("getAllAirports");
		
		@SuppressWarnings("unchecked")
		List<Airport> airports = query.getResultList();
		
		return airports;
	}
}
