package com.cg.ars.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

import com.cg.ars.dao.FlightDao;
import com.cg.ars.dao.FlightDaoImpl;
import com.cg.ars.dto.Flight;
import com.cg.ars.exception.FlightException;

public class FlightServiceImpl implements FlightService 
{
	private FlightDao fdao;
	
	public FlightServiceImpl() 
	{
		fdao = new FlightDaoImpl();
	}

	@Override
	public void addFlight(Flight flight) 
	{
		fdao.addFlight(flight);
	}

	@Override
	public Flight modifyFlight(Flight flight) 
	{
		return fdao.modifyFlight(flight);
	}

	@Override
	public void deleteFlight(String flightNo) throws FlightException 
	{
		try {
			fdao.deleteFlight(flightNo);
		}
		catch (Exception exc) {
			throw new FlightException(exc.getMessage());
		}
	}

	@Override
	public List<Flight> getAllFlights() 
	{
		return fdao.getAllFlights();
	}

	@Override
	public List<Flight> getFlights(Date date, String depCity, String arrCity)
	{
		return fdao.getFlights(date, depCity, arrCity);
	}

	@Override
	public boolean validateFlightNo(String flightNo) throws FlightException
	{
		String pattern = "[A-Z]{3,4}[0-9]{4,6}";
		
		if (Pattern.matches(pattern, flightNo)) {
			return true;
		}
		else {
			throw new FlightException("Invalid Flight Number");
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
			throw new FlightException("Invalid Airline Name");
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
			throw new FlightException("Invalid City");
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
		return fdao.getOccupancy(flightNo);
	}

	@Override
	public Double getOccupancy(String depCity, String arrCity) {
		return fdao.getOccupancy(depCity, arrCity);
	}

	@Override
	public Flight getFlight(String flightNo) throws FlightException
	{
		Flight flight = fdao.getFlight(flightNo);
		
		if(flight==null) {
			throw new FlightException("Flight not found with ID="+flightNo);
		}
		
		return flight;
	}

	@Override
	public Double getFare(String flightNo, String classType) throws FlightException
	{
		Flight flight = fdao.getFlight(flightNo);
		
		if (flight == null) {
			throw new FlightException("Flight Not Found with ID=" + flightNo);
		}

		double fare = fdao.getFare(flight, classType);
		
		if (fare < 0) {
			throw new FlightException("Invalid Class Type " + classType);
		}
		
		return fare;
	}
}
