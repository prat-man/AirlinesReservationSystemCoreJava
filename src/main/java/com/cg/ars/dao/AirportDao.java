package com.cg.ars.dao;

import java.util.List;

import com.cg.ars.dto.Airport;

public interface AirportDao
{
	public void addAirport(Airport airport);
	
	public List<Airport> getAllAirport();
}
