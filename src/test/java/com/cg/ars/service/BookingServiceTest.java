package com.cg.ars.service;

import org.junit.Assert;
import org.junit.Test;

import com.cg.ars.dto.Booking;
import com.cg.ars.exception.BookingException;

public class BookingServiceTest
{
	private static BookingService bser = new BookingServiceImpl();
	
	@Test(expected=BookingException.class)
	public void bookTicketTest() throws BookingException
	{
		bser.bookTicket(null);
	}
	
	@Test(expected=BookingException.class)
	public void viewBookDetailsTest1() throws BookingException
	{
		bser.getBooking(null);
	}
			
	@Test(expected=BookingException.class)
	public void viewBookDetailsTest2() throws BookingException
	{
		bser.getBooking("fsdgfs");
	}
	
	@Test(expected=BookingException.class)
	public void updateBookingDetailsTest1() throws BookingException
	{
		bser.updateBooking(null);
	}
	
	@Test(expected=BookingException.class)
	public void updateBookingDetailsTest2() throws BookingException
	{
		Booking booking = new Booking();
		
		bser.updateBooking(booking);
	}
	
	@Test(expected=BookingException.class)
	public void cancelBookingTest1() throws BookingException
	{
		bser.cancelBooking(null);
	}
	
	@Test(expected=BookingException.class)
	public void cancelBookingTest2() throws BookingException
	{
		bser.cancelBooking("KOL12345");
	}
	
	@Test
	public void validateBookingIdTest1() throws BookingException
	{
		Assert.assertEquals(true, bser.validateBookingId("KOL12345"));
	}
		
    @Test(expected=BookingException.class)
	public void validateBookingIdTest2() throws BookingException
	{
		Assert.assertEquals(false, bser.validateBookingId("kol124"));
	}
    
	@Test
	public void validateCreditCardInfoTest1() throws BookingException
	{
		Assert.assertEquals(true, bser.validateCreditCardInfo("5678946756372345"));
	}
			
	@Test(expected=BookingException.class)
	public void validateCreditCardInfoTest2() throws BookingException
	{
		Assert.assertEquals(false, bser.validateCreditCardInfo("acgd4563667677"));
	}
	
	@Test
	public void validateSrcCityTest1() throws BookingException
	{
		Assert.assertEquals(true, bser.validateCity("Pune"));
	}
					
	@Test(expected=BookingException.class)
	public void validateSrcCityTest2() throws BookingException
	{
		Assert.assertEquals(false, bser.validateCity("kolkata"));
	}
	
	@Test
	public void validateDestCityTest1() throws BookingException
	{
		Assert.assertEquals(true, bser.validateCity("Pune"));
	}
							
	@Test(expected=BookingException.class)
	public void validateDestCityTest2() throws BookingException
	{
		Assert.assertEquals(false, bser.validateCity("delhi"));
	}
}
