package com.cg.ars.service;

import java.util.List;

import com.cg.ars.dto.Airport;
import com.cg.ars.exception.AirportException;

public interface AirportService 
{
	public void addAirport(Airport airport) throws AirportException;
	
	public Airport getAirport(String airportId) throws AirportException;
	
	public List<Airport> getAllAirports();
	
	public void deleteAirport(String airportId) throws AirportException;
	
	public boolean validateName(String name) throws AirportException;
	
	public boolean validateAbbreviation(String abbreviation) throws AirportException;
	
	public boolean validateLocation(String location) throws AirportException;
}
