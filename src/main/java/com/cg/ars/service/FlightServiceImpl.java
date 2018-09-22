package com.cg.ars.service;

import java.sql.Date;
import java.util.List;

import com.cg.ars.dao.FlightDao;
import com.cg.ars.dao.FlightDaoImpl;
import com.cg.ars.dto.Flight;

public class FlightServiceImpl implements FlightService 
{
	private FlightDao fdao;
	
	public FlightServiceImpl() 
	{
		fdao = new FlightDaoImpl();
	}

	public void addFlight(Flight flight) 
	{
		fdao.addFlight(flight);
	}

	public Flight modifyFlight(Flight flight) 
	{
		return fdao.modifyFlight(flight);
	}

	public void deleteFlight(Flight flight) 
	{
		fdao.deleteFlight(flight);
	}

	public List<Flight> getAllFlights() 
	{
		return fdao.getAllFlights();
	}

	public List<Flight> getFlights(Date date, String depCity, String arrCity)
	{
		return fdao.getFlights(date, depCity, arrCity);
	}

	public boolean validateFlightNo(String flightNo) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean validateAirline(String airline) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean validateDepCity(String depCity) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean validateArrCity(String arrCity) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean validateDepDate(Date depDate) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean validateArrDate(Date arrDate) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean validateFirstSeats(Integer firstSeats) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean validateBussSeats(Integer bussSeats) {
		// TODO Auto-generated method stub
		return false;
	}
}
