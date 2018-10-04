package com.cg.ars.service;

import org.junit.Assert;
import org.junit.Test;

import com.cg.ars.dto.Booking;
import com.cg.ars.exception.BookingException;

public class BookingServiceTest
{
	private static BookingService bser = new BookingServiceImpl();
	
	@Test
	public void bookTicketTest1() throws BookingException
	{
		Booking booking = new Booking();
		
		booking.setBookingId("ZZZ12345");
		booking.setFlightNo("ZZZ1001");
		booking.setCustEmail("sts@capgemini.com");
		booking.setNoOfPassengers(3);
		booking.setClassType("First");
		booking.setTotalFare(6464.90);
		booking.setSeatNumber("F6");
		booking.setCreditCardInfo("4242698912093456");
		booking.setSrcCity("Kolkata");
		booking.setDestCity("Delhi");

		bser.bookTicket(booking);
		
		Booking bookingRet = bser.getBooking("ZZZ12345");
		
		Assert.assertNotNull(bookingRet);
		
		bser.cancelBooking("ZZZ12345");
	}
	
	@Test(expected=BookingException.class)
	public void bookTicketTest2() throws BookingException
	{
		bser.bookTicket(null);
	}
	
	@Test
	public void viewBookDetailsTest1() throws BookingException
	{
		Booking booking = new Booking();
		
		booking.setBookingId("ZZZ12345");
		booking.setFlightNo("ZZZ1001");
		booking.setCustEmail("sts@capgemini.com");
		booking.setNoOfPassengers(3);
		booking.setClassType("First");
		booking.setTotalFare(6464.90);
		booking.setSeatNumber("F6");
		booking.setCreditCardInfo("4242698912093456");
		booking.setSrcCity("Kolkata");
		booking.setDestCity("Delhi");
		
		bser.bookTicket(booking);
		
		Booking bookingRet = bser.getBooking("ZZZ12345");
		
		Assert.assertNotNull(bookingRet);
		
		bser.cancelBooking("ZZZ12345");
	}
			
	@Test(expected=BookingException.class)
	public void viewBookDetailsTest2() throws BookingException
	{
		bser.getBooking("fsdgfs");
	}
	
	//updateBookingDetailsValidate Test Cases
	@Test
	public void updateBookingDetailsTest1() throws BookingException
	{
		Booking booking = new Booking();
		
		booking.setBookingId("ZZZ12345");
		booking.setFlightNo("ZZZ1001");
		booking.setCustEmail("sts@capgemini.com");
		booking.setNoOfPassengers(3);
		booking.setClassType("First");
		booking.setTotalFare(6464.90);
		booking.setSeatNumber("F6");
		booking.setCreditCardInfo("4242698912093456");
		booking.setSrcCity("Kolkata");
		booking.setDestCity("Delhi");
		
		bser.bookTicket(booking);
		
		booking.setCreditCardInfo("4242698912193456");
		
		bser.updateBooking(booking);
		
		Booking bookingRet = bser.getBooking("ZZZ12345");
		
		Assert.assertEquals("4242698912193456", bookingRet.getCreditCardInfo());
		
		try {
			bser.cancelBooking("ZZZ12345");
		} catch (Exception e) {
			// Do nothing
		}
	}
	
	@Test(expected=BookingException.class)
	public void updateBookingDetailsTest2() throws BookingException
	{
		Booking booking = new Booking();
		
		booking.setBookingId(null);
		booking.setFlightNo(null);
		booking.setCustEmail("sts@capgemini.com");
		booking.setNoOfPassengers(3);
		booking.setClassType("First");
		booking.setTotalFare(6464.90);
		booking.setSeatNumber("F6");
		booking.setCreditCardInfo("4242698912093456");
		booking.setSrcCity("Kolkata");
		booking.setDestCity("Delhi");
		
		bser.updateBooking(booking);
	}
	
	@Test(expected=BookingException.class)
	public void cancelBookingTest1() throws BookingException
	{
		Booking booking = new Booking();
		
		booking.setBookingId("ZZZ12345");
		booking.setFlightNo("ZZZ1001");
		booking.setCustEmail("sts@capgemini.com");
		booking.setNoOfPassengers(3);
		booking.setClassType("First");
		booking.setTotalFare(6464.90);
		booking.setSeatNumber("F6");
		booking.setCreditCardInfo("4242698912093456");
		booking.setSrcCity("Kolkata");
		booking.setDestCity("Delhi");
		
		bser.bookTicket(booking);
		
		bser.cancelBooking("ZZZ12345");
		
		bser.getBooking("ZZZ12345");
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
	public void validateEmailTest1() throws BookingException
	{
		Assert.assertEquals(true, bser.validateEmail("ars@capgemini.com"));
	}
	
	@Test(expected=BookingException.class)
	public void validateEmailTest2() throws BookingException
	{
		Assert.assertEquals(false, bser.validateEmail("ars.@@gmail..com"));
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
