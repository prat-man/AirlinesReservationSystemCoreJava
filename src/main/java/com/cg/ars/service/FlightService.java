package com.cg.ars.service;

import java.sql.Date;
import java.util.List;

import com.cg.ars.dto.Flight;

public interface FlightService 
{
	public void addFlight(Flight flight);
	
	public Flight modifyFlight(Flight flight);
	
	public void deleteFlight(Flight flight);
	
	public List<Flight> getAllFlights();
	
	public List<Flight> getFlights(Date date, String depCity, String arrCity);
	
	public boolean validateFlightNo(String flightNo);
	
	public boolean validateAirline(String airline);
	
	public boolean validateDepCity(String depCity);
	
	public boolean validateArrCity(String arrCity);
	
	public boolean validateDepDate(Date depDate);
	
	public boolean validateArrDate(Date arrDate);
	
	public boolean validateFirstSeats(Integer firstSeats);
	
	public boolean validateBussSeats(Integer bussSeats);
}
