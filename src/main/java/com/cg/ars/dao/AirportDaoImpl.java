package com.cg.ars.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import com.cg.ars.dto.Airport;

public class AirportDaoImpl implements AirportDao {

	private EntityManager entityManager;

	public AirportDaoImpl() {
		//entityManager = JPAUtil.getEntityManager();
	}

	public void addAirport(Airport airport) {
		entityManager.getTransaction().begin();
		entityManager.persist(airport);
		entityManager.getTransaction().commit();
	}

	public List<Airport> getAllAirport() {
		String jplq="SELECT a FROM Airport a";
		TypedQuery<Airport> query=entityManager.createQuery(jplq,Airport.class);
		return query.getResultList();
	}
	
}
