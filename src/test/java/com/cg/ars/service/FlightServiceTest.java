package com.cg.ars.service;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;

import org.junit.Test;

import com.cg.ars.dto.Flight;
import com.cg.ars.exception.FlightException;
import com.cg.ars.ui.ARSClient;

import junit.framework.Assert;

public class FlightServiceTest
{
	private FlightService F_SER;
	
	public FlightServiceTest() {
		F_SER = new FlightServiceImpl();
	}
	
	@Test
	public void testAddFlight() throws ParseException, FlightException {
		
		Flight testFlight = new Flight();
		
		testFlight.setAirline("KINGFISHER");
		testFlight.setArrCity("Pune");
		testFlight.setArrDate(new Date(ARSClient.DATE_FORMAT.parse("10-10-2018").getTime()));
		testFlight.setArrTime(new Time(ARSClient.TIME_FORMAT.parse("08:30").getTime()));
		testFlight.setBussSeats(20);
		testFlight.setBussSeatsFare(10000.00);
		testFlight.setDepCity("Kolkata");
		testFlight.setDepDate(new Date(ARSClient.DATE_FORMAT.parse("09-10-2018").getTime()));
		testFlight.setDepTime(new Time(ARSClient.TIME_FORMAT.parse("10:30").getTime()));
		testFlight.setFirstSeats(10);
		testFlight.setFirstSeatsFare(7000.00);
		testFlight.setFlightNo("PUN1234");
		
		F_SER.addFlight(testFlight);
		
		Assert.assertNotNull(F_SER.getFlight("PUN1234"));
	}
	
	@Test
	public void testModifyFlight() throws ParseException, FlightException{
		
		Flight testFlight = new Flight();
		
		testFlight.setAirline("INDIGO");
		testFlight.setArrCity("Mumbai");
		testFlight.setArrDate(new Date(ARSClient.DATE_FORMAT.parse("10-10-2018").getTime()));
		testFlight.setArrTime(new Time(ARSClient.TIME_FORMAT.parse("08:30").getTime()));
		testFlight.setBussSeats(20);
		testFlight.setBussSeatsFare(10000.00);
		testFlight.setDepCity("Kolkata");
		testFlight.setDepDate(new Date(ARSClient.DATE_FORMAT.parse("09-10-2018").getTime()));
		testFlight.setDepTime(new Time(ARSClient.TIME_FORMAT.parse("10:30").getTime()));
		testFlight.setFirstSeats(10);
		testFlight.setFirstSeatsFare(7000.00);
		testFlight.setFlightNo("KOL1234");
		
		F_SER.addFlight(testFlight);
		
		testFlight.setAirline("GOAIRWAYS");
		testFlight.setDepCity("Delhi");
		testFlight.setArrCity("Bengaluru");
		
		F_SER.modifyFlight(testFlight);
		
		Assert.assertEquals(testFlight, F_SER.getFlight("KOL1234"));
		
	}
	
	@Test
	public void testDeleteFlight() throws ParseException, FlightException {
		
		Flight testFlight = new Flight();
		
		testFlight.setAirline("AIR INDIA");
		testFlight.setArrCity("Surat");
		testFlight.setArrDate(new Date(ARSClient.DATE_FORMAT.parse("10-10-2018").getTime()));
		testFlight.setArrTime(new Time(ARSClient.TIME_FORMAT.parse("08:30").getTime()));
		testFlight.setBussSeats(20);
		testFlight.setBussSeatsFare(10000.00);
		testFlight.setDepCity("Lucknow");
		testFlight.setDepDate(new Date(ARSClient.DATE_FORMAT.parse("09-10-2018").getTime()));
		testFlight.setDepTime(new Time(ARSClient.TIME_FORMAT.parse("10:30").getTime()));
		testFlight.setFirstSeats(10);
		testFlight.setFirstSeatsFare(7000.00);
		testFlight.setFlightNo("SUR4634");
		
		F_SER.addFlight(testFlight);
		
		F_SER.deleteFlight(testFlight.getFlightNo());
		
		Assert.assertNull(F_SER.getFlight(testFlight.getFlightNo()));
	}
	
	@Test
	public void testGetAllFlights() throws FlightException {
		
		Assert.assertNotNull(F_SER.getAllFlights());
	}
	
}
