package com.cg.ars.service;

import java.util.List;

import com.cg.ars.dao.AirportDao;
import com.cg.ars.dao.AirportDaoImpl;
import com.cg.ars.dto.Airport;

public class AirportServiceImpl implements AirportService 
{
	private AirportDao adao;
	
	public AirportServiceImpl() 
	{
		adao = new AirportDaoImpl();
	}

	public void addAirport(Airport airport) 
	{
		adao.addAirport(airport);
	}

	public List<Airport> getAllAirport() 
	{
		return adao.getAllAirport();
	}	
}
