package com.cg.ars.service;

import java.sql.Date;
import java.text.ParseException;

import org.junit.Assert;
import org.junit.Test;

import com.cg.ars.dto.Flight;
import com.cg.ars.exception.FlightException;
import com.cg.ars.ui.ARSClient;

public class FlightServiceTest
{
	private FlightService fser;
	
	public FlightServiceTest()
	{
		fser = new FlightServiceImpl();
	}
	
	@Test(expected=FlightException.class)
	public void testAddFlight() throws FlightException
	{
		fser.addFlight(null);
	}
	
	@Test(expected=FlightException.class)
	public void testModifyFlight() throws FlightException
	{
		fser.modifyFlight(null);
	}
	
	@Test(expected=FlightException.class)
	public void testDeleteFlight() throws FlightException
	{
		fser.deleteFlight(null);
	}
	
	@Test
	public void testGetAllFlights()
	{
		try {
			Assert.assertNotNull(fser.getAllFlights());
		} catch (FlightException e) {
			Assert.assertEquals("No Flight Records Found", e.getMessage());
		}
	}
	
	@Test
	public void testvalidateFlightNo1() throws FlightException
	{
		Assert.assertEquals(true, fser.validateFlightNo("PUN1002"));
	}
	
	@Test(expected=FlightException.class)
	public void testvalidateFlightNo2() throws FlightException
	{
		Assert.assertEquals(false, fser.validateFlightNo("pune10000678"));
	}
	
	@Test
	public void testvalidateAirline1() throws FlightException
	{
		Assert.assertEquals(true, fser.validateAirline("Pune Airlines"));

	}
	
	@Test(expected=FlightException.class)
	public void testvalidateAirline2() throws FlightException
	{
		Assert.assertEquals(false, fser.validateAirline("pune1234"));

	}
	
	@Test
	public void testvalidateCity1() throws FlightException
	{
		Assert.assertEquals(true, fser.validateCity("Pune"));

	}
	
	@Test(expected=FlightException.class)
	public void testvalidateCity2() throws FlightException
	{
		Assert.assertEquals(false, fser.validateCity("pu123"));

	}
	
	@Test
	public void testvalidateDate1() throws FlightException, ParseException
	{
		Assert.assertEquals(true, fser.validateDate(new Date(ARSClient.DATE_FORMAT.parse("10-10-3000").getTime())));
	}
	
	@Test(expected=FlightException.class)
	public void testvalidateDate2() throws FlightException, ParseException
	{
		Assert.assertEquals(true, fser.validateDate(new Date(ARSClient.DATE_FORMAT.parse("10-10-2000").getTime())));
	}
	
	@Test(expected=ParseException.class)
	public void testvalidateDate3() throws FlightException, ParseException
	{
		Assert.assertEquals(true, fser.validateDate(new Date(ARSClient.DATE_FORMAT.parse("10/10/3000").getTime())));
	}
	
	@Test
	public void testValidateSeats1() throws FlightException
	{
		Assert.assertEquals(true, fser.validateSeats(200));
	}
	
	@Test(expected=FlightException.class)
	public void testValidateSeats2() throws FlightException
	{
		Assert.assertEquals(false, fser.validateSeats(-1));
	}
	
	@Test
	public void testGetFare() throws FlightException 
	{
		try {
			Object object = fser.getFlight("PUN1234");
			
			Assert.assertTrue(object instanceof Flight);
		}
		catch (Exception e) {
			Assert.assertTrue(e.getMessage().startsWith("Flight not found with [flightNo="));
		}
	}
}
