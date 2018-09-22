package com.cg.ars.service;

import java.util.List;

import org.junit.Test;

import com.cg.ars.exception.AirportException;

import junit.framework.Assert;

public class AirportServiceTest
{
	private AirportService aser;
	
	public AirportServiceTest() {
		aser = new AirportServiceImpl();
	}
	
	
	//AddAirport Test case goes here
	
	@Test
	public void getAllAirportsTest()
	{
		Object obj = aser.getAllAirports();
		
		if (obj == null) {
			Assert.assertEquals(null, obj);
		}
		else {
			Assert.assertTrue(obj instanceof List);
		}
	}
	
	//NameValidate Test Cases
	@Test
	public void validateNameTest1()
	{
		Assert.assertEquals(true, aser.validateName("Indira Gandhi International Airport"));
	}
	
	@Test(expected=AirportException.class)
	public void validateNameTest2()
	{
		Assert.assertEquals(false, aser.validateName("pune"));
	}
	
	
	//Abbreviation Test Cases
	@Test
	public void validateAbbreviationTest1() 
	{
		Assert.assertEquals(true, aser.validateName("KOL"));
	}
	
	@Test(expected=AirportException.class)
	public void validateAbbreviationTest2() 
	{
		Assert.assertEquals(false, aser.validateName("kolkata"));
	}
	
	

	//AirportLocation Test Cases
	@Test
	public void validateLocationTest1()
	{
		Assert.assertEquals(true, aser.validateName("Pune"));
	}
	
	@Test(expected=AirportException.class)
	public void validateLocationTest2()
	{
		Assert.assertEquals(false, aser.validateName("pune"));
	}
	
	
	
}
