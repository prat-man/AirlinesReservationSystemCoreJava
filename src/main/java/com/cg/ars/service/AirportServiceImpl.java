package com.cg.ars.service;

import java.util.List;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.cg.ars.dao.AirportDao;
import com.cg.ars.dao.AirportDaoImpl;
import com.cg.ars.dto.Airport;
import com.cg.ars.exception.AirportException;

public class AirportServiceImpl implements AirportService 
{
	private AirportDao adao;
	
	private Logger logger;
	
	public AirportServiceImpl()
	{
		adao = new AirportDaoImpl();
		
		logger = Logger.getLogger(this.getClass());
	}
	
	/**
	 * Add Airport
	 */
	@Override
	public void addAirport(Airport airport) throws AirportException 
	{
		try {
			this.validateAbbreviation(airport.getAbbreviation());
			this.validateName(airport.getAirportName());
			this.validateLocation(airport.getLocation());
			
			if (adao.getAirport(airport.getAbbreviation()) != null) {
				throw new AirportException("Airport with [airportId=" + airport.getAbbreviation() + "] already present");
			}
			
			adao.addAirport(airport);
			
			logger.info("Airport Information Added [airportId=" + airport.getAbbreviation() + "]");
		}
		catch (Exception e) {
			logger.error("Airport Addition Failed\n" + e.getMessage());
			throw new AirportException("Airport Addition Failed\n" + e.getMessage());
		}
	}
	
	/**
	 * Get Airport Details by ID
	 * @return Airport instance
	 */
	@Override
	public Airport getAirport(String airportId) throws AirportException 
	{
		try {
			this.validateAbbreviation(airportId);
			Airport airport = adao.getAirport(airportId);
			
			if (airport == null) {
				throw new NullPointerException("Airport Record with [airportId=" + airportId + "] not found");
			}
			else {
				logger.info("Airport Record Retrieved [airportId=" + airportId + "]");
				return airport;
			}
		}
		catch (Exception exc) {
			logger.error("Airport Record Retrieval Failed [airportId=" + airportId + "]\n" + exc.getMessage());
			throw new AirportException("Airport Record Retrieval Failed [airportId=" + airportId + "]");
		}
	}
	
	/**
	 * Get List of All Airports
	 * @return List of All Airports
	 */
	@Override
	public List<Airport> getAllAirports() throws AirportException
	{
		try {
			List<Airport> list = adao.getAllAirports();
			
			if (list == null || list.isEmpty()) {
				throw new NullPointerException("Airport List Empty");
			}
			else {
				logger.info("Information for all Airports Retrieved.");
				
				return list;
			}
		}
		catch (Exception exc) {
			logger.error("No Airport Records Found\n" + exc.getMessage());
			throw new AirportException("No Airport Records Found");
		}
	}
	
	/**
	 * Delete Airport Information by airportId
	 */
	public void deleteAirport(String airportId) throws AirportException 
	{
		try {
			this.validateAbbreviation(airportId);
			adao.deleteAirport(airportId);
			logger.info("Airport Record Deleted [airportId=" + airportId + "]");
		}
		catch (Exception exc) {
			logger.error("Airport Record Deletion Failed [airportId=" + airportId + "]\n" +exc.getMessage());
			throw new AirportException("Airport Record Deletion Failed [airportId=" + airportId + "]");
		}
	}

	/**
	 * Validate airport name by pattern each word must start with UPPERCASE followed by lowercase alphabets
	 * @return boolean; true if valid, otherwise false
	 */
	@Override
	public boolean validateName(String name) throws AirportException 
	{
		String pattern = "([A-Z][a-z]+ )*[A-Z][a-z]+";
		
		if (Pattern.matches(pattern, name)) {
			return true;
		}
		else {
			logger.error("Invalid Airport Name [name=" + name + "]");
			throw new AirportException("Invalid Airport Name [name=" + name + "]\nEach word must start with UPPERCASE followed by lowercase alphabets");
		}
	}

	/**
	 * Validate abbreviation by pattern 3 to 4 UPPERCASE alphabets
	 * @return boolean; true if valid, otherwise false
	 */
	@Override
	public boolean validateAbbreviation(String abbreviation) throws AirportException
	{
		String pattern = "[A-Z]{3,4}";
		
		if (Pattern.matches(pattern, abbreviation)) {
			return true;
		}
		else {
			logger.error("Invalid Airport Abbreviation [abbreviation=" + abbreviation + "]");
			throw new AirportException("Invalid Airport Abbreviation [abbreviation=" + abbreviation + "]\nMust be 3 to 4 UPPERCASE alphabets");
		}
	}

	/**
	 * Validate airport location by pattern each word must start with UPPERCASE followed by lowercase alphabets
	 * @return boolean; true if valid, otherwise false
	 */
	@Override
	public boolean validateLocation(String location) throws AirportException 
	{
		String pattern = "([A-Z][a-z]+ )*[A-Z][a-z]+";
		
		if (Pattern.matches(pattern, location)) {
			return true;
		}
		else {
			logger.error("Invalid Airport Location [location=" + location +  "]");
			throw new AirportException("Invalid Airport Location [location=" + location + "]\nEach word must start with UPPERCASE followed by lowercase characters ");
		}
	}
}
