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

	@Override
	public void addFlight(Flight flight) 
	{
		fdao.addFlight(flight);
		logger.info("Flight Record Added [flightNo=" + flight.getFlightNo() + "]");
	}

	@Override
	public Flight modifyFlight(Flight flight) 
	{
		flight = fdao.modifyFlight(flight);
		
		logger.info("Flight Record Modified [flightNo=" + flight.getFlightNo() + "]");
		
		return flight;
	}

	@Override
	public void deleteFlight(String flightNo) throws FlightException 
	{
		try {
			fdao.deleteFlight(flightNo);
			logger.info("Flight Record Deleted [flightNo=" + flightNo +"]");
			
		}
		catch (Exception exc) {
			logger.error("Flight Record Deletion Failed [flightNo=" + flightNo + "]\n" + exc.getMessage());
			throw new FlightException("Flight Record Deletion Failed [flightNo=" + flightNo + "]");
		}
	}

	@Override
	public List<Flight> getAllFlights() 
	{
		List<Flight> list = fdao.getAllFlights();
		logger.info("Flight Record Fetched");
		return list;
	}

	@Override
	public List<Flight> getFlights(Date date, String depCity, String arrCity)
	{
		List<Flight> list = fdao.getFlights(date, depCity, arrCity);
		logger.info("Flight Record Fetched between " + depCity + " and " + arrCity + " on " + date );
		return list;
	}

	@Override
	public boolean validateFlightNo(String flightNo) throws FlightException
	{
		String pattern = "[A-Z]{3,4}[0-9]{4,6}";
		
		if (Pattern.matches(pattern, flightNo)) {
			logger.info("Valid FlightNo Validated [flightNo=" + flightNo +"]");
			return true;
		}
		else {
			logger.error("Invalid FlightNo [flightNo=" + flightNo + "]");
			throw new FlightException("Invalid Flight Number [flightNo=" + flightNo + "]\n Flight Number Should start with UPPERCASE and ends with number");
		}
	}

	@Override
	public boolean validateAirline(String airline) throws FlightException
	{
		String pattern = "([A-Z][a-z]+ )*[A-Z][a-z]+";
		
		if (Pattern.matches(pattern, airline)) {
			logger.info("Airline Name validated [Airline=" + airline + "]");
			return true;
		}
		else {
			logger.error("Invalid Airline Name [Airline=" + airline + "]\nAirline name should start with UPPERCASE");
			throw new FlightException("Invalid Airline Name[Airline=" + airline + "]\nAirline name should start with UPPERCASE");
		}
	}
	
	@Override
	public boolean validateCity(String city) throws FlightException 
	{
		String pattern = "([A-Z][a-z]+ )*[A-Z][a-z]+";
	
		if (Pattern.matches(pattern, city)) {
			logger.info("Valid City [city=" + city + "]");
			return true;
		}
		else {
			logger.error("Invalid City [city=" + city + "]");
			throw new FlightException("Invalid City [city=" + city + "]\n");
		}
	}
	
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

	@Override
	public Double getOccupancy(String flightNo) {
		Double oc = fdao.getOccupancy(flightNo);
		logger.info("Fetched Occupancy [Occupancy" + oc + "]");
		return oc;
	}

	@Override
	public Double getOccupancy(String depCity, String arrCity) {
		Double oc = fdao.getOccupancy(depCity, arrCity);
		logger.info("Occupancy between Cities [Occupancies=" + oc + "]");
		return oc;
	}

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
