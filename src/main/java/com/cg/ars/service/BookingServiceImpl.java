package com.cg.ars.service;

import java.util.Arrays;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.cg.ars.dao.BookingDao;
import com.cg.ars.dao.BookingDaoImpl;
import com.cg.ars.dto.Booking;
import com.cg.ars.dto.Flight;
import com.cg.ars.exception.BookingException;

public class BookingServiceImpl implements BookingService 
{
	private BookingDao bdao;
	private FlightService fser;
	
	private Logger logger;
	
	public BookingServiceImpl() 
	{
		bdao = new BookingDaoImpl();
		fser = new FlightServiceImpl();
		
		logger = Logger.getLogger(this.getClass());
	}

	/**
	 * Book Ticket
	 * @param Booking Instance
	 */
	@Override
	public void bookTicket(Booking booking) throws BookingException 
	{
		try {
			Flight flight = fser.getFlight(booking.getFlightNo());
			
			this.validateCity(booking.getDestCity());
			this.validateCity(booking.getSrcCity());
			this.validateClassType(booking.getClassType());
			this.validateCreditCardInfo(booking.getCreditCardInfo());
			this.validateEmail(booking.getCustEmail());
			this.validateNoOfPassengers(flight, booking.getClassType(), booking.getNoOfPassengers());
			
			Integer remainingSeats;
			
			switch (booking.getClassType())
			{
				case Flight.FIRST:
								remainingSeats = flight.getFirstSeats() - booking.getNoOfPassengers();
								
								if (booking.getNoOfPassengers() > 1) {
									booking.setSeatNumber("F" + (remainingSeats + 1) + " - " + "F" + flight.getFirstSeats());
								}
								else {
									booking.setSeatNumber("F" + flight.getFirstSeats());
								}
								
								flight.setFirstSeats(remainingSeats);
								break;
				
				case Flight.BUSINESS:
								remainingSeats = flight.getBussSeats() - booking.getNoOfPassengers();
								
								if (booking.getNoOfPassengers() > 1) {
									booking.setSeatNumber("B" + (remainingSeats + 1) + " - " + "B" + flight.getBussSeats());
								}
								else {
									booking.setSeatNumber("B" + flight.getBussSeats());
								}
								
								flight.setFirstSeats(remainingSeats);
								break;
					
				default:
								logger.error("Invalid Class Type [classType=" + booking.getClassType() + "]");
								throw new BookingException("Invalid Class Type [classType=" + booking.getClassType() + "]");
			}
			
			bdao.bookTicket(booking);
		}
		catch (Exception exc) {
			logger.error(exc.getMessage());
			throw new BookingException(exc.getMessage());
		}
	}

	/**
	 * Get Booking Details by Id
	 * @return Booking Instance
	 */
	@Override
	public Booking getBooking(String bookingId) throws BookingException 
	{
		try {
			this.validateBookingId(bookingId);
			
			Booking booking = bdao.getBooking(bookingId);
			
			if (booking == null) {
				logger.error("Booking Record with [bookingId=" + bookingId + "] not found");
				throw new NullPointerException("Booking Record with [bookingId=" + bookingId + "] not found");
			}
			else 
			{
				logger.info("Booking details fetched by booking ID [Booking ID =" + booking.getBookingId() + "]");
				return booking;
			}
		}
		catch (Exception exc) {
			logger.error(exc.getMessage());
			throw new BookingException(exc.getMessage());
		}
	}

	/**
	 * Update Booking Details
	 */
	@Override
	public void updateBooking(Booking booking) throws BookingException 
	{
		try {
			this.validateCity(booking.getDestCity());
			this.validateCity(booking.getSrcCity());
			this.validateClassType(booking.getClassType());
			this.validateCreditCardInfo(booking.getCreditCardInfo());
			this.validateEmail(booking.getCustEmail());
			
			Flight flight = fser.getFlight(booking.getFlightNo());
			
			this.validateNoOfPassengers(flight, booking.getClassType(), booking.getNoOfPassengers());
      
			bdao.updateBooking(booking);
      
			logger.info("Updating booking details with booking ID [Booking ID =" + booking.getBookingId() + "]");
		}
		catch (Exception exc) {
			logger.error(exc.getMessage());
			throw new BookingException(exc.getMessage());
		}
	}

	/**
	 * Cancel Booking
	 */
	@Override
	public void cancelBooking(String bookingId) throws BookingException 
	{
		try {
			logger.info("Cancelling booking with [Booking ID=" + bookingId + "]");
			bdao.cancelBooking(bookingId);
		}
		catch (Exception exc) {
			logger.error(exc.getMessage());
			throw new BookingException(exc.getMessage());
		}
	}
	
	/**
	 * Generate Booking ID
	 * @return String; Generated Booking ID 
	 */
	@Override
	public String generateBookingId(String flightNo) throws BookingException
	{
		try {
			Flight flight = fser.getFlight(flightNo);
			
			String airline = flight.getAirline();
			
			String prefix = airline.substring(0, ((airline.length() < 4) ? airline.length() : 4)).toUpperCase();
			
			logger.info("Booking ID generated for [Flight No=" + flightNo + "]");
			
			return prefix + bdao.getBookingId();
		}
		catch (Exception e) {
			logger.error(e.getMessage());
			throw new BookingException(e.getMessage());
		}
	}

	/**
	 * Validate Booking ID by pattern 3 to 4 UPPERCASE alphabets followed by 4 to 6 digits
	 * @return boolean; true if valid, otherwise false
	 */
	@Override
	public boolean validateBookingId(String bookingId) throws BookingException 
	{
		String pattern = "[A-Z]{3,4}[0-9]{4,6}";
		
		if (Pattern.matches(pattern, bookingId)) {
			logger.info("Booking ID valid![Booking ID=" + bookingId + "]");
			return true;
		}
		else {
			logger.error("Invalid Booking ID [Booking ID=" + bookingId + "]");
			throw new BookingException("Invalid Booking ID [Booking ID=" + bookingId + "]");
		}
	}

	/**
	 * Validates Email Id
	 * @return boolean; true if valid, otherwise false
	 */
	@Override
	public boolean validateEmail(String email) throws BookingException 
	{
		String pattern = "(^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$)";
		
		if (Pattern.matches(pattern, email)) {
			logger.info("Email ID valid! [Email =" + email + "]");
			return true;
		}
		else {
			logger.error("Invalid Email ID [Email =" + email + "]");
			throw new BookingException("Invalid Email ID [Email =" + email + "]");
		}
	}

	/**
	 * Validates that available seats are greater than or equal to number of passengers
	 * @return boolean; true if valid, otherwise false
	 */
	@Override
	public boolean validateNoOfPassengers(Flight flight, String classType, Integer passengers) throws BookingException 
	{
		int availableSeats;
		
		// get number of seats available by class type
		switch (classType) {
			case Flight.FIRST:
				availableSeats = flight.getFirstSeats();
				break;
			
			case Flight.BUSINESS:
				availableSeats = flight.getBussSeats();
				break;
				
			default:
				availableSeats = 0;
				break;
		}
		
		// if number of passengers are one or more
		// and number of passengers are less than number of seats available
		if (passengers > 0 && passengers <= availableSeats) {
			logger.info("Number of passengers valid! [NoOfPassengers =" + passengers + "]");
			return true;
		}
		else {
			logger.error("Invalid number of passengers [NoOfPassengers =" + passengers + "]" +
							"\nFormat"+"Should be between 0 and "+availableSeats);
			throw new BookingException("Invalid number of passengers [NoOfPassengers =" + passengers + "]"
						+ "\nFormat" + "Should be between 0 and "+availableSeats);
		}
	}

	/**
	 * Validates Class Type which should be First or Business
	 * @return boolean; true if valid, otherwise false
	 */
	@Override
	public boolean validateClassType(String classType) throws BookingException 
	{
		String[] classes = Flight.getClassTypes();
		
		if (Arrays.asList(classes).contains(classType)) {
			logger.info("Class Type valid! [Class Type=" + classType + "]");
			return true;
		}
		else {
			logger.error("Invalid Class Type [Class Type=" + classType + "]" +
							"\nFormat:"+"Should be " + Flight.FIRST + " or "+ Flight.BUSINESS);
			throw new BookingException("Invalid Class Type [Class Type=" + classType + "]" +
							"\nFormat:"+"Should be " + Flight.FIRST + " or "+ Flight.BUSINESS);
		}
	}

	/**
	 * Validate Credit Card Information by pattern card number should be of 16 digits
	 * @return boolean; true if valid, otherwise false
	 */
	@Override
	public boolean validateCreditCardInfo(String creditCardInfo) throws BookingException 
	{
		String pattern = "[0-9]{16}";
		
		if (Pattern.matches(pattern, creditCardInfo)) {
			logger.info("Credit card number valid!");
			return true;
		}
		else {
			logger.error("Invalid Credit Card Information [Info Entered =" + creditCardInfo + "]"
							+"\nFormat: Must be 16 digits");
			throw new BookingException("Invalid Credit Card Information [Info Entered =" + creditCardInfo + "]"
							+"\nFormat: Must be 16 digits");
		}
	}

	/**
	 * Validate City by pattern each word must start with UPPERCASE followed by lowercase alphabets
	 * @return boolean; true if valid, otherwise false
	 */
	@Override
	public boolean validateCity(String city) throws BookingException 
	{
		String pattern = "([A-Z][a-z]+ )*[A-Z][a-z]+";
		
		if (Pattern.matches(pattern, city)) {
			logger.info("City name valid![City =" + city + "]");
			return true;
		}
		else {
			logger.error("Invalid City Name [City =" + city +"]\nFormat: Should start with UPPERCASE followed by lowercase");
			throw new BookingException("Invalid City Name [City =" + city +"]\nFormat: Should start with UPPERCASE followed by lowercase");
		}
	}
}
