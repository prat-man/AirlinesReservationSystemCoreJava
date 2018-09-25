package com.cg.ars.service;

import java.sql.Date;
import java.util.List;

import com.cg.ars.dto.Flight;
import com.cg.ars.exception.FlightException;

public interface FlightService 
{
	public void addFlight(Flight flight);
	
	public Flight modifyFlight(Flight flight);
	
	public void deleteFlight(Flight flight);
	
	public List<Flight> getAllFlights();
	
	public List<Flight> getFlights(Date date, String depCity, String arrCity);
	
	public boolean validateFlightNo(String flightNo) throws FlightException;
	
	public boolean validateAirline(String airline) throws FlightException;
	
	public boolean validateCity(String city) throws FlightException;
	
	public boolean validateDate(Date date) throws FlightException;
		
	public boolean validateSeats(Integer seats) throws FlightException;
}
