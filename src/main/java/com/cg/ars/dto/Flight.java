package com.cg.ars.dto;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="FLIGHTINFORMATION")

@NamedQueries({
	@NamedQuery(name="getFlights", query="SELECT f FROM Flight f WHERE f.depDate = :depDate AND f.depCity = :depCity AND f.arrCity = :arrCity"),
	@NamedQuery(name="getAllFlights", query="SELECT a FROM Airport a")
})

public class Flight
{
	@Id
	@Column(name="FLIGHTNO")
	private String flightNo;
	
	@Column(name="AIRLINE")
	private String airline;
	
	@Column(name="DEP_CITY")
	private String depCity;
	
	@Column(name="ARR_CITY")
	private String arrCity;
	
	@Column(name="DEP_DATE")
	private Date depDate;
	
	@Column(name="ARR_DATE")
	private Date arrDate;
	
	@Column(name="DEP_TIME")
	private Time depTime;
	
	@Column(name="ARR_TIME")
	private Time arrTime;
	
	@Column(name="FIRSTSEATS")
	private Integer firstSeats;
	
	@Column(name="FIRSTSEATFARE")
	private Double firstSeatsFare;
	
	@Column(name="BUSSSEATS")
	private Integer bussSeats;
	
	@Column(name="BUSSSEATSFARE")
	private Double bussSeatsFare;

	/**
	 * Default No-Argument Constructor
	 */
	public Flight() {
		super();
	}

	/*
	 * Getters and Setters
	 */
	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public String getDepCity() {
		return depCity;
	}

	public void setDepCity(String depCity) {
		this.depCity = depCity;
	}

	public String getArrCity() {
		return arrCity;
	}

	public void setArrCity(String arrCity) {
		this.arrCity = arrCity;
	}

	public Date getDepDate() {
		return depDate;
	}

	public void setDepDate(Date depDate) {
		this.depDate = depDate;
	}

	public Date getArrDate() {
		return arrDate;
	}

	public void setArrDate(Date arrDate) {
		this.arrDate = arrDate;
	}

	public Time getDepTime() {
		return depTime;
	}

	public void setDepTime(Time depTime) {
		this.depTime = depTime;
	}

	public Time getArrTime() {
		return arrTime;
	}

	public void setArrTime(Time arrTime) {
		this.arrTime = arrTime;
	}

	public Integer getFirstSeats() {
		return firstSeats;
	}

	public void setFirstSeats(Integer firstSeats) {
		this.firstSeats = firstSeats;
	}

	public Double getFirstSeatsFare() {
		return firstSeatsFare;
	}

	public void setFirstSeatsFare(Double firstSeatsFare) {
		this.firstSeatsFare = firstSeatsFare;
	}

	public Integer getBussSeats() {
		return bussSeats;
	}

	public void setBussSeats(Integer bussSeats) {
		this.bussSeats = bussSeats;
	}

	public Double getBussSeatsFare() {
		return bussSeatsFare;
	}

	public void setBussSeatsFare(Double bussSeatsFare) {
		this.bussSeatsFare = bussSeatsFare;
	}
	
	/**
	 * Override toString() method
	 * @return String representing an instance of this class
	 */
	@Override
	public String toString() {
		return "Flight [flightNo=" + flightNo + ", airline=" + airline + ", depCity=" + depCity + ", arrCity=" + arrCity
				+ ", depTime=" + depTime + ", arrTime=" + arrTime + ", firstSeats=" + firstSeats + ", firstSeatsFare="
				+ firstSeatsFare + ", bussSeats=" + bussSeats + ", bussSeatsFare=" + bussSeatsFare + "]";
	}
}
