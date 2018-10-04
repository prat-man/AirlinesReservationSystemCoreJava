package com.cg.ars.service;

import java.util.Arrays;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.cg.ars.dao.BookingDao;
import com.cg.ars.dao.BookingDaoImpl;
import com.cg.ars.dao.FlightDao;
import com.cg.ars.dao.FlightDaoImpl;
import com.cg.ars.dto.Booking;
import com.cg.ars.dto.Flight;
import com.cg.ars.exception.BookingException;

public class BookingServiceImpl implements BookingService 
{
	private BookingDao bdao;
	private FlightDao fdao;
	
	private Logger logger;
	
	public BookingServiceImpl() 
	{
		bdao = new BookingDaoImpl();
		fdao = new FlightDaoImpl();
		
		logger = Logger.getLogger(this.getClass());
		
		PropertyConfigurator.configure("log4j.properties");
	}

	@Override
	public void bookTicket(Booking booking) throws BookingException 
	{
		try {
			Flight flight = fdao.getFlight(booking.getFlightNo());
			
			this.validateNoOfPassengers(flight, booking.getClassType(), booking.getNoOfPassengers());
			
			Integer remainingSeats;
			
			switch (booking.getClassType())
			{
				case Flight.FIRST:
								remainingSeats = flight.getFirstSeats() - booking.getNoOfPassengers();
								
								if (booking.getNoOfPassengers() > 1) {
									booking.setSeatNumber("F" + remainingSeats + " - " + "F" + flight.getFirstSeats());
								}
								else {
									booking.setSeatNumber("F" + flight.getFirstSeats());
								}
								
								flight.setFirstSeats(remainingSeats);
								break;
				
				case Flight.BUSINESS:
								remainingSeats = flight.getBussSeats() - booking.getNoOfPassengers();
								
								if (booking.getNoOfPassengers() > 1) {
									booking.setSeatNumber("B" + remainingSeats + " - " + "B" + flight.getBussSeats());
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

	@Override
	public Booking getBooking(String bookingId) throws BookingException 
	{
		try {
			Booking booking = bdao.getBooking(bookingId);
			
			if (booking == null) {
				logger.error("Booking Record with Booking ID=" + bookingId + " not found");
				throw new NullPointerException("Booking Record with Booking ID=" + bookingId + " not found");
			}
			else 
			{
				logger.info("Booking details fetched by id="+ bookingId);
				return booking;
			}
		}
		catch (Exception exc) {
			logger.error(exc.getMessage());
			throw new BookingException(exc.getMessage());
		}
	}

	@Override
	public void updateBooking(Booking booking) throws BookingException 
	{
		try {
			logger.info("Updating booking details");
			bdao.updateBooking(booking);
		}
		catch (Exception exc) {
			logger.error(exc.getMessage());
			throw new BookingException(exc.getMessage());
		}
	}

	@Override
	public void cancelBooking(String bookingId) throws BookingException 
	{
		try {
			logger.info("Cancelling booking");
			bdao.cancelBooking(bookingId);
		}
		catch (Exception exc) {
			logger.error(exc.getMessage());
			throw new BookingException(exc.getMessage());
		}
	}
	
	@Override
	public String generateBookingId(String flightNo) throws BookingException
	{
		Flight flight = fdao.getFlight(flightNo);
		
		String prefix = flight.getAirline().substring(0,3);
		
		logger.info("Booking ID generated!");
		return prefix + bdao.getBookingId();
	}

	@Override
	public boolean validateBookingId(String bookingId) throws BookingException 
	{
		String pattern = "[A-Z]{3,4}[0-9]{4,6}";
		
		if (Pattern.matches(pattern, bookingId)) {
			logger.info("Booking ID valid!");
			return true;
		}
		else {
			logger.error("Invalid Booking ID");
			throw new BookingException("Invalid Booking ID");
		}
	}

	@Override
	public boolean validateEmail(String email) throws BookingException 
	{
		String pattern = "(^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$)";
		
		if (Pattern.matches(pattern, email)) {
			logger.info("Email ID valid!");
			return true;
		}
		else {
			logger.error("Invalid Email ID");
			throw new BookingException("Invalid Email ID");
		}
	}

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
			logger.info("Number of passengers valid!");
			return true;
		}
		else {
			logger.error("Invalid number of passengers!"
							+"\n"+"Should be between 0 and "+availableSeats);
			throw new BookingException("Invalid number of passengers!"
										+"\n"+"Should be between 0 and "+availableSeats);
		}
	}

	@Override
	public boolean validateClassType(String classType) throws BookingException 
	{
		String[] classes = Flight.getClassTypes();
		
		if (Arrays.asList(classes).contains(classType)) {
			logger.info("Class Type valid!");
			return true;
		}
		else {
			logger.error("Invalid Class Type"
							+"\n"+"Should be 'FIRST' or 'BUSINESS'");
			throw new BookingException("Invalid Class Type"
										+"\n"+"Should be 'FIRST' or 'BUSINESS'");
		}
	}

	@Override
	public boolean validateCreditCardInfo(String creditCardInfo) throws BookingException 
	{
		String pattern = "[0-9]{16}";
		
		if (Pattern.matches(pattern, creditCardInfo)) {
			logger.info("Credit card number valid!");
			return true;
		}
		else {
			logger.error("Invalid Credit Card Information"
							+"Must be 16 digits");
			throw new BookingException("Invalid Credit Card Information"
										+"Must be 16 digits");
		}
	}

	@Override
	public boolean validateCity(String city) throws BookingException 
	{
		String pattern = "([A-Z][a-z]+ )*[A-Z][a-z]+";
		
		if (Pattern.matches(pattern, city)) {
			logger.info("City name valid!");
			return true;
		}
		else {
			logger.error("Invalid City Name");
			throw new BookingException("Invalid City Name");
		}
	}
}
