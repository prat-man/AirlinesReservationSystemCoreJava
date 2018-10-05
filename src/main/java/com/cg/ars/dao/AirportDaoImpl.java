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
		try {
			entityManager.getTransaction().begin();
			
			entityManager.persist(airport);
			
			entityManager.getTransaction().commit();
		}
		catch (Exception exc) {
			exc.printStackTrace();
			
			entityManager.getTransaction().rollback();
			
			throw exc;
		}
	}
	
	@Override
	public Airport getAirport(String airportId)
	{
		try {
			entityManager.getTransaction().begin();
			
			Airport airport = entityManager.find(Airport.class, airportId);
			
			entityManager.getTransaction().commit();
			
			return airport;
		}
		catch (Exception exc) {
			entityManager.getTransaction().rollback();
			
			throw exc;
		}
	}

	@Override
	public List<Airport> getAllAirports()
	{
		try {
			entityManager.getTransaction().begin();
			
			Query query = entityManager.createNamedQuery("getAllAirports");
			
			@SuppressWarnings("unchecked")
			List<Airport> airports = query.getResultList();
			
			entityManager.getTransaction().commit();
			
			return airports;
		}
		catch (Exception exc) {
			entityManager.getTransaction().rollback();
			
			throw exc;
		}
	}
	
	@Override
	public void deleteAirport(String airportId)
	{
		try {
			entityManager.getTransaction().begin();
			
			Airport airport = entityManager.find(Airport.class, airportId);
			
			entityManager.remove(airport);
			
			entityManager.getTransaction().commit();
		}
		catch (Exception exc) {
			entityManager.getTransaction().rollback();
			
			throw exc;
		}
	}
}
