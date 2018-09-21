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
}
