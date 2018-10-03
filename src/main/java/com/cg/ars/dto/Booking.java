package com.cg.ars.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="BOOKINGS")

@NamedQueries({
	@NamedQuery(name="getSumOfPassengersByFlightNo", query="SELECT SUM(b.noOfPassengers) FROM Booking b WHERE b.flightNo=:flightNo"),
	@NamedQuery(name="getSumOfPassengersByRoute", query="SELECT SUM(b.noOfPassengers) FROM Booking b WHERE b.srcCity=:depCity AND b.destCity=:arrCity")
})
public class Booking 
{
	@Id
	@Column(name="BOOKING_ID")
	private String bookingId;
	
	@Column(name="FLIGHT_NO")
	private String flightNo;
	
	@Column(name="CUST_EMAIL")
	private String custEmail;
	
	@Column(name="NO_OF_PASSENGERS")
	private Integer noOfPassengers;
	
	@Column(name="CLASS_TYPE")
	private String classType;
	
	@Column(name="TOTAL_FARE")
	private Double totalFare;
	
	@Column(name="SEAT_NUMBER")
	private Integer seatNumber;
	
	@Column(name="CREDITCARD_INFO")
	private String creditCardInfo;
	
	@Column(name="SRC_CITY")
	private String srcCity;
	
	@Column(name="DEST_CITY")
	private String destCity;

	/**
	 * Default No-Argument Constructor
	 */
	public Booking() {
		super();
	}
	
	/**
	 * Parameterized Constructor
	 * @param bookingId
	 * @param flightNo
	 * @param custEmail
	 * @param noOfPassengers
	 * @param classType
	 * @param totalFare
	 * @param seatNumber
	 * @param creditCardInfo
	 * @param srcCity
	 * @param destCity
	 */
	public Booking(String bookingId, String flightNo, String custEmail, Integer noOfPassengers, String classType, Double totalFare,
			Integer seatNumber, String creditCardInfo, String srcCity, String destCity) {
		super();
		this.bookingId = bookingId;
		this.flightNo = flightNo;
		this.custEmail = custEmail;
		this.noOfPassengers = noOfPassengers;
		this.classType = classType;
		this.totalFare = totalFare;
		this.seatNumber = seatNumber;
		this.creditCardInfo = creditCardInfo;
		this.srcCity = srcCity;
		this.destCity = destCity;
	}

	/*
	 * Getters and Setters
	 */
	public String getBookingId() {
		return bookingId;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}

	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightId(String flightNo) {
		this.flightNo = flightNo;
	}

	public String getCustEmail() {
		return custEmail;
	}

	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}

	public Integer getNoOfPassengers() {
		return noOfPassengers;
	}

	public void setNoOfPassengers(Integer noOfPassengers) {
		this.noOfPassengers = noOfPassengers;
	}

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	public Double getTotalFare() {
		return totalFare;
	}

	public void setTotalFare(Double totalFare) {
		this.totalFare = totalFare;
	}

	public Integer getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(Integer seatNumber) {
		this.seatNumber = seatNumber;
	}

	public String getCreditCardInfo() {
		return creditCardInfo;
	}

	public void setCreditCardInfo(String creditCardInfo) {
		this.creditCardInfo = creditCardInfo;
	}

	public String getSrcCity() {
		return srcCity;
	}

	public void setSrcCity(String srcCity) {
		this.srcCity = srcCity;
	}

	public String getDestCity() {
		return destCity;
	}

	public void setDestCity(String destCity) {
		this.destCity = destCity;
	}

	/**
	 * Override toString() method
	 * @return String representing an instance of this class
	 */
	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", custEmail=" + custEmail + ", noOfPassengers=" + noOfPassengers
				+ ", classType=" + classType + ", totalFare=" + totalFare + ", seatNumber=" + seatNumber
				+ ", creditCardInfo=" + creditCardInfo + ", srcCity=" + srcCity + ", destCity=" + destCity + "]";
	}
}
