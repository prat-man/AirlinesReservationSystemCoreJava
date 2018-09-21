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
	public List<Flight> getFlights(Date date,String destination);
}
