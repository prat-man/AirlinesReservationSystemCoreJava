package com.cg.ars.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.cg.ars.dao.FlightDao;
import com.cg.ars.dao.FlightDaoImpl;
import com.cg.ars.dto.Flight;
import com.cg.ars.exception.FlightException;

public class FlightServiceImpl implements FlightService 
{
	private FlightDao fdao;
	
	private Logger logger;
	
	public FlightServiceImpl() 
	{
		fdao = new FlightDaoImpl();
		
		logger = Logger.getLogger(this.getClass());
	}

	/**
	 * Add Flight
	 */
	@Override
	public void addFlight(Flight flight) 
	{
		logger.info("Adding Flight");
		fdao.addFlight(flight);
	}

	/**
	 * Modify Flights
	 */
	@Override
	public Flight modifyFlight(Flight flight) 
	{
		logger.info("Modifying Flights");
		return fdao.modifyFlight(flight);
	}

	/**
	 * Delete Flight
	 */
	@Override
	public void deleteFlight(String flightNo) throws FlightException 
	{
		try {
			fdao.deleteFlight(flightNo);
			logger.info("Flight Deleted");
			
		}
		catch (Exception exc) {
			
			logger.error(exc.getMessage());
			throw new FlightException(exc.getMessage());
		}
	}

	/**
	 * Get List of All Flights
	 * @return List of All Flights
	 */
	@Override
	public List<Flight> getAllFlights() 
	{
		logger.info("Fetching Flights");
		return fdao.getAllFlights();
	}

	/**
	 * Get List of All Flights by date, departure and arrival city
	 * @return List of All Flights by date, departure and arrival city
	 */
	@Override
	public List<Flight> getFlights(Date date, String depCity, String arrCity)
	{
		logger.info("Listing Flight");
		return fdao.getFlights(date, depCity, arrCity);
	}

	/**
	 * Validate Booking ID by pattern 3 to 4 UPPERCASE alphabets followed by 4 to 6 digits
	 * @return boolean; true if valid, otherwise false
	 */
	@Override
	public boolean validateFlightNo(String flightNo) throws FlightException
	{
		String pattern = "[A-Z]{3,4}[0-9]{4,6}";
		
		if (Pattern.matches(pattern, flightNo)) {
			logger.info("Valid Flight Number");
			return true;
		}
		else {
			logger.error("Invalid Flight Number");
			throw new FlightException("Invalid Flight Number");
		}
	}

	/**
	 * Validate City by pattern each word must start with UPPERCASE followed by lowercase alphabets
	 * @return boolean; true if valid, otherwise false
	 */
	@Override
	public boolean validateAirline(String airline) throws FlightException
	{
		String pattern = "([A-Z][a-z]+ )*[A-Z][a-z]+";
		
		if (Pattern.matches(pattern, airline)) {
			logger.info("Valid Airline Name");
			return true;
		}
		else {
			logger.error("Invalid Airline Name");
			throw new FlightException("Invalid Airline Name");
		}
	}

	/**
	 * Validate City by pattern each word must start with UPPERCASE followed by lowercase alphabets
	 * @return boolean; true if valid, otherwise false
	 */
	@Override
	public boolean validateCity(String city) throws FlightException 
	{
		String pattern = "([A-Z][a-z]+ )*[A-Z][a-z]+";
	
		if (Pattern.matches(pattern, city)) {
			logger.info("Valid City");
			return true;
		}
		else {
			logger.error("Invalid City");
			throw new FlightException("Invalid City");
		}
	}
	
	/**
	 * Validates Date 
	 * @return boolean; true if valid, otherwise false
	 */
	@Override
	public boolean validateDate(Date date) throws FlightException
	{
		LocalDate today = LocalDate.now();
		LocalDate other = date.toLocalDate();
		
		if (today.compareTo(other) > 0) {
			return true;
		}
		else {
			logger.error("Invalid Date. You must book at least ONE day before");
			throw new FlightException("Invalid Date. You must book at least ONE day before");
		}
	}

	/**
	 * Validate that Seats must be greater than 0
	 * @return boolean; true if valid, otherwise false 
	 */
	@Override
	public boolean validateSeats(Integer seats) throws FlightException 
	{
		if (seats > 0) {
			return true;
		}
		else {
			logger.error("Invalid Number of Seats. Must be greater than ZERO");
			throw new FlightException("Invalid Number of Seats. Must be greater than ZERO");
		}
	}

	/**
	 * Get Occupancy Details by Flight Number
	 * @return occupancy 
	 */
	@Override
	public Double getOccupancy(String flightNo) {
		logger.info("Fetched Occupancy");
		return fdao.getOccupancy(flightNo);
	}

	/**
	 * Get Occupancy Details between cities
	 * @return occupancy 
	 */
	@Override
	public Double getOccupancy(String depCity, String arrCity) {
		logger.info("Occupancy between Cities");
		return fdao.getOccupancy(depCity, arrCity);
	}

	/**
	 * Get Flight number 
	 * @return Flight Instance
	 */
	@Override
	public Flight getFlight(String flightNo) throws FlightException
	{
		Flight flight = fdao.getFlight(flightNo);
		
		if(flight==null) {
			logger.error("Flight not found with ID="+flightNo);
			throw new FlightException("Flight not found with ID="+flightNo);
		}
		
		return flight;
	}

	/**
	 * Get Flight Fare by Flight Number and Class Type
	 * @return Flight Fare
	 */
	@Override
	public Double getFare(String flightNo, String classType) throws FlightException
	{
		Flight flight = fdao.getFlight(flightNo);
		
		if (flight == null) {
			logger.error("Flight Not Found with ID=" + flightNo);
			throw new FlightException("Flight Not Found with ID=" + flightNo);
		}

		double fare = fdao.getFare(flight, classType);
		
		if (fare < 0) {
			logger.error("Invalid Class Type " + classType);
			throw new FlightException("Invalid Class Type " + classType);
		}
		
		return fare;
	}
}
