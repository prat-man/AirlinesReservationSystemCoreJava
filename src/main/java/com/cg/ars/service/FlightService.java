package com.cg.ars.service;

import java.sql.Date;
import java.util.List;

import com.cg.ars.dto.Flight;
import com.cg.ars.exception.FlightException;

public interface FlightService 
{
	public void addFlight(Flight flight) throws FlightException;
	
	public Flight modifyFlight(Flight flight) throws FlightException;
	
	public void deleteFlight(String flightNo) throws FlightException;
	
	public Flight getFlight(String flightNo) throws FlightException;
	
	public List<Flight> getAllFlights() throws FlightException;
	
	public List<Flight> getFlights(Date date, String depCity, String arrCity) throws FlightException;
	
	public Double getFare(String flightNo, String classType) throws FlightException;
	
	public Double getOccupancy(String flightNo) throws FlightException;
	
	public Double getOccupancy(String depCity, String arrCity) throws FlightException;
	
	public boolean validateFlightNo(String flightNo) throws FlightException;
	
	public boolean validateAirline(String airline) throws FlightException;
	
	public boolean validateCity(String city) throws FlightException;
	
	public boolean validateDate(Date date) throws FlightException;
		
	public boolean validateSeats(Integer seats) throws FlightException;
}
