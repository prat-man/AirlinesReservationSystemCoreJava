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
			this.validateAirline(flight.getAirline());
			this.validateCity(flight.getArrCity());
			this.validateCity(flight.getDepCity());
			this.validateDate(flight.getArrDate());
			this.validateDate(flight.getDepDate());
			this.validateFlightNo(flight.getFlightNo());
			this.validateSeats(flight.getFirstSeats());
			this.validateSeats(flight.getBussSeats());
			
			fdao.addFlight(flight);
      
      logger.info("Flight Record Added [flightNo=" + flight.getFlightNo() + "]");
	}

	@Override
	public Flight modifyFlight(Flight flight) 
	{	
			this.validateCity(flight.getArrCity());
			this.validateAirline(flight.getAirline());
			this.validateCity(flight.getDepCity());
			this.validateDate(flight.getArrDate());
			this.validateDate(flight.getDepDate());
			this.validateFlightNo(flight.getFlightNo());
			this.validateSeats(flight.getFirstSeats());
			this.validateSeats(flight.getBussSeats());
      
      flight = fdao.modifyFlight(flight);
      
			logger.info("Flight Record Modified [flightNo=" + flight.getFlightNo() + "]");
      
      return flight;
	}

	@Override
	public void deleteFlight(String flightNo) throws FlightException 
	{
		try {
			this.validateFlightNo(flightNo);
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
			this.validateCity(depCity);
			this.validateCity(arrCity);
			this.validateDate(date);
      
      List<Flight> list = fdao.getFlights(date, depCity, arrCity);
      
		  logger.info("Flight Records Fetched between " + depCity + " and " + arrCity + " on " + date );
      
		  return list;
	}

	@Override
	public boolean validateFlightNo(String flightNo) throws FlightException
	{
		String pattern = "[A-Z]{3,4}[0-9]{4,6}";
		
		if (Pattern.matches(pattern, flightNo)) {
			return true;
		}
		else {
			throw new FlightException("Invalid Flight Number [flightNo=" + flightNo + "]\n Flight Number Should start with UPPERCASE and ends with number");
		}
	}

	@Override
	public boolean validateAirline(String airline) throws FlightException
	{
		String pattern = "([A-Z][a-z]+ )*[A-Z][a-z]+";
		
		if (Pattern.matches(pattern, airline)) {
			return true;
		}
		else {
			throw new FlightException("Invalid Airline Name[Airline=" + airline + "]\nAirline name should start with UPPERCASE");
		}
	}
	
	@Override
	public boolean validateCity(String city) throws FlightException 
	{
		String pattern = "([A-Z][a-z]+ )*[A-Z][a-z]+";
	
		if (Pattern.matches(pattern, city)) {
			return true;
		}
		else {
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
		logger.info("Occupancy between cities [depCity=" + depCity + ", arrCity=" + arrCity + "]");
		return oc;
	}

	@Override
	public Flight getFlight(String flightNo) throws FlightException
	{
		Flight flight = fdao.getFlight(flightNo);
		
		if(flight == null) {
			logger.error("Flight not found with [flightNo=" + flightNo + "]");
			throw new FlightException("Flight not found with [flightNo=" + flightNo + "]");
		}
		
		return flight;
	}

	@Override
	public Double getFare(String flightNo, String classType) throws FlightException
	{
		Flight flight = fdao.getFlight(flightNo);
		
		if (flight == null) {
			logger.error("Flight Not Found with [flightNo=" + flightNo + "]");
			throw new FlightException("Flight Not Found with [flightNo=" + flightNo + "]");
		}

		double fare;
		
		switch (classType) {
			case Flight.FIRST:
				fare = flight.getFirstSeatsFare();
				break;
			
			case Flight.BUSINESS:
				fare = flight.getBussSeatsFare();
				break;
				
			default:
				fare = -1;
				break;
		}
		
		if (fare < 0) {
			logger.error("Invalid Class Type [classType=" + classType + "]");
			throw new FlightException("Invalid Class Type [classType=" + classType + "]");
		}
		
		return fare;
	}
}
