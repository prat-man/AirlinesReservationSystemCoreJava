package com.cg.ars.dao;

import java.sql.Date;
import java.util.List;

import com.cg.ars.dto.Flight;

public interface FlightDao
{
	public void addFlight(Flight flight);
	
	public Flight modifyFlight(Flight flight);
	
	public void deleteFlight(Flight flight);
	
	public List<Flight> getAllFlights();
	
	public List<Flight> getFlights(Date date, String depCity, String arrCity);
	
	public Flight getFlight(String flightNo);
	
	public Double getOccupancy(String flightNo);
	
	public Double getOccupancy(String depCity, String arrCity);
	
	public Double getFare(Flight flight, String classType);
}
