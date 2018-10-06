package com.cg.ars.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.cg.ars.dao.FlightDao;
import com.cg.ars.dao.FlightDaoImpl;
import com.cg.ars.dto.Flight;
import com.cg.ars.exception.FlightException;

public class FlightServiceImpl implements FlightService 
{
	private FlightDao fdao;
	private AirportService aser;
	
	private Logger logger;
	
	public FlightServiceImpl() 
	{
		fdao = new FlightDaoImpl();
		
		aser = new AirportServiceImpl();
		
		logger = Logger.getLogger(this.getClass());
	}

	/**
	 * Add Flight
	 */
	@Override
	public void addFlight(Flight flight) throws FlightException 
	{
		try {
			flight.setDepCity(aser.getAirport(flight.getDepAirport()).getLocation());
			
			flight.setArrCity(aser.getAirport(flight.getArrAirport()).getLocation());
			
			this.validateAirline(flight.getAirline());
			this.validateCity(flight.getArrCity());
			this.validateCity(flight.getDepCity());
			this.validateDate(flight.getArrDate());
			this.validateDate(flight.getDepDate());
			this.validateFlightNo(flight.getFlightNo());
			this.validateSeats(flight.getFirstSeats());
			this.validateSeats(flight.getBussSeats());
			
			fdao.addFlight(flight);
	      
			logger.info("Flight Record Added [flightNo=" + flight.getFlightNo() + "]");
		}
		catch (Exception exc) {
			// Dirty hack to reuse method for validating while adding flight
			if (exc.getMessage().equals("Invalid Date. You must book at least ONE day before")) {
				throw new FlightException("Invalid Date. You must add flights at least ONE day before");
			}
			
			throw new FlightException(exc.getMessage());
		}
	}

	/**
	 * Modify Flights
	 */
	@Override
	public Flight modifyFlight(Flight flight) throws FlightException
	{
		try {
			flight.setArrCity(aser.getAirport(flight.getArrAirport()).getLocation());
			
			flight.setDepCity(aser.getAirport(flight.getDepAirport()).getLocation());
			
			this.validateCity(flight.getArrCity());
			this.validateAirline(flight.getAirline());
			this.validateCity(flight.getDepCity());
			this.validateDate(flight.getArrDate());
			this.validateDate(flight.getDepDate());
			this.validateFlightNo(flight.getFlightNo());
			this.validateSeats(flight.getFirstSeats());
			this.validateSeats(flight.getBussSeats());
	
			flight = fdao.modifyFlight(flight);
	
			logger.info("Flight Record Modified [flightNo=" + flight.getFlightNo() + "]");
	
			return flight;
		}
		catch (Exception exc) {
			throw new FlightException(exc.getMessage());
		}
	}

	/**
	 * Delete Flight
	 */
	@Override
	public void deleteFlight(String flightNo) throws FlightException 
	{
		try {
			this.validateFlightNo(flightNo);
			
			fdao.deleteFlight(flightNo);
			
			logger.info("Flight Record Deleted [flightNo=" + flightNo +"]");
		}
		catch (Exception exc) {
			logger.error("Flight Record Deletion Failed [flightNo=" + flightNo + "]\n" + exc.getMessage());
			throw new FlightException("Flight Record Deletion Failed [flightNo=" + flightNo + "]");
		}
	}

	/**
	 * Get List of All Flights
	 * @return List of All Flights
	 */
	@Override
	public List<Flight> getAllFlights() throws FlightException 
	{
		try {
			List<Flight> list = fdao.getAllFlights();
			
			if (list == null || list.isEmpty()) {
				throw new NullPointerException("Flight List Empty");
			}
			else {
				logger.info("Flight Record Fetched");
				
				return list;
			}
		}
		catch (Exception exc) {
			logger.error("No Flight Records Found\n" + exc.getMessage());
			throw new FlightException("No Flight Records Found");
		}
	}

	/**
	 * Get List of All Flights by date, departure and arrival city
	 * @return List of All Flights by date, departure and arrival city
	 */
	@Override
	public List<Flight> getFlights(Date date, String depCity, String arrCity) throws FlightException
	{
		this.validateCity(depCity);
		this.validateCity(arrCity);
		this.validateDate(date);
		
		try {
			List<Flight> list = fdao.getFlights(date, depCity, arrCity);
			
			if (list == null || list.isEmpty()) {
				throw new NullPointerException("Flight List Empty");
			}
			else {
				logger.info("Flight Records Fetched for [depCity=" + depCity + ", arrCity=" + arrCity + ", date=" + date + "]");
				
				return list;
			}
		}
		catch (Exception exc) {
			logger.error("No Flight Records Found\n" + exc.getMessage());
			throw new FlightException("No Flight Records Found");
		}
	}

	/**
	 * Validate Booking ID by pattern 3 to 4 UPPERCASE alphabets followed by 4 to 6 digits
	 * @return boolean; true if valid, otherwise false
	 */
	@Override
	public boolean validateFlightNo(String flightNo) throws FlightException
	{
		String pattern = "[A-Z]{3,4}[0-9]{4,6}";
		
		if (Pattern.matches(pattern, flightNo)) {
			return true;
		}
		else {
			throw new FlightException("Invalid Flight Number [flightNo=" + flightNo + "]\n Flight Number Should start with UPPERCASE and ends with number");
		}
	}

	/**
	 * Validate City by pattern each word must start with UPPERCASE followed by lowercase alphabets
	 * @return boolean; true if valid, otherwise false
	 */
	@Override
	public boolean validateAirline(String airline) throws FlightException
	{
		String pattern = "([A-Z][a-z]+ )*[A-Z][a-z]+";
		
		if (Pattern.matches(pattern, airline)) {
			return true;
		}
		else {
			throw new FlightException("Invalid Airline Name[Airline=" + airline + "]\nAirline name should start with UPPERCASE");
		}
	}

	/**
	 * Validate City by pattern each word must start with UPPERCASE followed by lowercase alphabets
	 * @return boolean; true if valid, otherwise false
	 */
	@Override
	public boolean validateCity(String city) throws FlightException 
	{
		String pattern = "([A-Z][a-z]+ )*[A-Z][a-z]+";
	
		if (Pattern.matches(pattern, city)) {
			return true;
		}
		else {
			throw new FlightException("Invalid City [city=" + city + "]\n");
		}
	}
	
	/**
	 * Validates Date 
	 * @return boolean; true if valid, otherwise false
	 */
	@Override
	public boolean validateDate(Date date) throws FlightException
	{
		LocalDate today = LocalDate.now();
		LocalDate other = date.toLocalDate();
		
		if (today.compareTo(other) < 0) {
			return true;
		}
		else {
			throw new FlightException("Invalid Date. You must book at least ONE day before");
		}
	}

	/**
	 * Validate that Seats must be greater than 0
	 * @return boolean; true if valid, otherwise false 
	 */
	@Override
	public boolean validateSeats(Integer seats) throws FlightException 
	{
		if (seats > 0) {
			return true;
		}
		else {
			throw new FlightException("Invalid Number of Seats. Must be greater than ZERO");
		}
	}

	/**
	 * Get Occupancy Details by Flight Number
	 * @return occupancy 
	 * @throws FlightException 
	 */
	@Override
	public Double getOccupancy(String flightNo) throws FlightException
	{
		try {
			Double oc = fdao.getOccupancy(flightNo);
			
			logger.info("Fetched Occupancy for [flightNo=" + flightNo + "]");
			
			return oc;
		}
		catch (Exception e) {
			throw new FlightException(e.getMessage());
		}
	}

	/**
	 * Get Occupancy Details between cities
	 * @return occupancy 
	 * @throws FlightException 
	 */
	@Override
	public Double getOccupancy(String depCity, String arrCity) throws FlightException
	{
		try {
			Double oc = fdao.getOccupancy(depCity, arrCity);
			
			logger.info("Fetched Occupancy between cities [depCity=" + depCity + ", arrCity=" + arrCity + "]");
			
			return oc;
		}
		catch (Exception e) {
			throw new FlightException(e.getMessage());
		}
	}

	/**
	 * Get Flight Instance by Flight Number 
	 * @return Flight Instance
	 */
	@Override
	public Flight getFlight(String flightNo) throws FlightException
	{
		Flight flight = fdao.getFlight(flightNo);
		
		if (flight == null) {
			logger.error("Flight not found with [flightNo=" + flightNo + "]");
			throw new FlightException("Flight not found with [flightNo=" + flightNo + "]");
		}
		
		return flight;
	}

	/**
	 * Get Flight Fare by Flight Number and Class Type
	 * @return Flight Fare
	 */
	@Override
	public Double getFare(String flightNo, String classType) throws FlightException
	{
		Flight flight = fdao.getFlight(flightNo);
		
		if (flight == null) {
			logger.error("Flight Not Found with [flightNo=" + flightNo + "]");
			throw new FlightException("Flight Not Found with [flightNo=" + flightNo + "]");
		}

		double fare;
		
		switch (classType) {
			case Flight.FIRST:
				fare = flight.getFirstSeatsFare();
				break;
			
			case Flight.BUSINESS:
				fare = flight.getBussSeatsFare();
				break;
				
			default:
				fare = -1;
				break;
		}
		
		if (fare < 0) {
			logger.error("Invalid Class Type [classType=" + classType + "]");
			throw new FlightException("Invalid Class Type [classType=" + classType + "]");
		}
		
		return fare;
	}
}
