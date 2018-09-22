package com.cg.ars.service;

import java.util.List;

import com.cg.ars.dto.Airport;
import com.cg.ars.exception.AirportException;

public interface AirportService 
{
	public void addAirport(Airport airport);
	
	public List<Airport> getAllAirports();
	
	public boolean validateName(String name) throws AirportException;
	
	public boolean validateAbbreviation(String abbreviation) throws AirportException;
	
	public boolean validateLocation(String location) throws AirportException;
}
