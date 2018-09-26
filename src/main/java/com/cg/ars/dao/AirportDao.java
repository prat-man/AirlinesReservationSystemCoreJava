package com.cg.ars.dao;

import java.util.List;

import com.cg.ars.dto.Airport;

public interface AirportDao
{
	public void addAirport(Airport airport);
	
	public Airport getAirport(String airportId);
	
	public List<Airport> getAllAirports();
	
	public void deleteAirport(String airportId);
}
