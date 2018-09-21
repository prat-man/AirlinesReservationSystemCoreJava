package com.cg.ars.dao;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.cg.ars.dto.Airport;
import com.cg.ars.dto.Booking;
import com.cg.ars.dto.Flight;

public class FlightDaoImpl implements FlightDao {

	private EntityManager entityManager;

	public FlightDaoImpl() {
		//entityManager = JPAUtil.getEntityManager();
	}
	public void addFlight(Flight flight) {
		entityManager.getTransaction().begin();
		entityManager.persist(flight);
		entityManager.getTransaction().commit();
	}

	public Flight modifyFlight(Flight flight) {
		entityManager.getTransaction().begin();
		Flight fl=entityManager.merge(flight);
		entityManager.getTransaction().commit();
		return fl;
	}

	public void deleteFlight(Flight flight) {
		entityManager.getTransaction().begin();
		entityManager.remove(flight);
		entityManager.getTransaction().begin();
	}

	public List<Flight> getAllFlights() {
		Query query = entityManager.createNamedQuery("getAllFlights");
		List<Flight> flights = query.getResultList();
		return flights; 
	}

	public List<Flight> getFlights(Date date, String destination) {
		Query query = entityManager.createNamedQuery("getFlights");
		List<Flight> flights = query.getResultList();
		return flights; 
	}
	
}
