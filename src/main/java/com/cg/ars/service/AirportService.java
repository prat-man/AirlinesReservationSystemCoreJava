package com.cg.ars.service;

import java.util.List;

import com.cg.ars.dto.Airport;

public interface AirportService 
{
	public void addAirport(Airport airport);
	
	public List<Airport> getAllAirport();
}
