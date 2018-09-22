package com.cg.ars.service;

import java.util.List;
import java.util.regex.Pattern;

import com.cg.ars.dao.AirportDao;
import com.cg.ars.dao.AirportDaoImpl;
import com.cg.ars.dto.Airport;
import com.cg.ars.exception.AirportException;

public class AirportServiceImpl implements AirportService 
{
	private AirportDao adao;
	
	public AirportServiceImpl() 
	{
		adao = new AirportDaoImpl();
	}

	public void addAirport(Airport airport) 
	{
		adao.addAirport(airport);
	}

	public List<Airport> getAllAirports() 
	{
		return adao.getAllAirport();
	}

	public boolean validateName(String name) throws AirportException 
	{
		String pattern = "([A-Z][a-z]+ )*[A-Z][a-z]+";
		
		if (Pattern.matches(pattern, name)) {
			return true;
		}
		else {
			throw new AirportException("Invalid Airport Name");
		}
	}

	public boolean validateAbbreviation(String abbreviation) throws AirportException 
	{
		String pattern = "[A-Z]{3,4}";
		
		if (Pattern.matches(pattern, abbreviation)) {
			return true;
		}
		else {
			throw new AirportException("Invalid Airport Abbreviation");
		}
	}

	public boolean validateLocation(String location) throws AirportException 
	{
		String pattern = "([A-Z][a-z]+ )*[A-Z][a-z]+";
		
		if (Pattern.matches(pattern, location)) {
			return true;
		}
		else {
			throw new AirportException("Invalid Airport Location");
		}
	}
}
