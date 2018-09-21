package com.cg.ars.service;

import java.util.List;

import com.cg.ars.dao.AirportDao;
import com.cg.ars.dao.AirportDaoImpl;
import com.cg.ars.dto.Airport;

public class AirportServiceImpl implements AirportService 
{
	AirportDao adao;
	
	public AirportServiceImpl() 
	{
		super();
		adao=new AirportDaoImpl();
	}

	public void addAirport(Airport airport) 
	{
		adao.addAirport(airport);
	}

	public List<Airport> getAllAirport() 
	{
		List<Airport> aList=adao.getAllAirport();
		return aList;
	}	
}
