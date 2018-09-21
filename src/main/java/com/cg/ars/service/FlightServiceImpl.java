package com.cg.ars.service;

import java.sql.Date;
import java.util.List;

import com.cg.ars.dao.FlightDao;
import com.cg.ars.dao.FlightDaoImpl;
import com.cg.ars.dto.Airport;
import com.cg.ars.dto.Flight;

public class FlightServiceImpl implements FlightService 
{
	FlightDao fdao;
	
	
	public FlightServiceImpl() 
	{
		super();
		fdao=new FlightDaoImpl();
	}

	public void addFlight(Flight flight) 
	{
		fdao.addFlight(flight);
		
	}

	public Flight modifyFlight(Flight flight) 
	{
		fdao.modifyFlight(flight);
		return null;
	}

	public void deleteFlight(Flight flight) 
	{
		fdao.deleteFlight(flight);
		
	}

	public List<Flight> getAllFlights() 
	{
		List<Flight> fList=fdao.getAllFlights();
		return fList;
	}

	public List<Flight> getFlights(Date date, String destination) 
	{
		List<Flight> fList=fdao.getFlights(date, destination);
		return fList;
	}
}
