package com.cg.ars.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="AIRPORTS")

@NamedQueries({
	@NamedQuery(name="getAllAirports", query="SELECT a FROM Airport a")
})

public class Airport
{
	@Column(name="AIRPORTNAME")
	private String airportName;
	
	@Id
	@Column(name="ABBREVIATION")
	private String abbreviation;
	
	@Column(name="LOCATION")
	private String location;

	/**
	 * Default No-Argument Constructor
	 */
	public Airport() {
		super();
	}
	
	/*
	 * Getters and Setters
	 */
	public String getAirportName() {
		return airportName;
	}

	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	/**
	 * Override toString() method
	 * @return String representing an instance of this class
	 */
	@Override
	public String toString() {
		return "Airport [airportName=" + airportName + ", abbreviation=" + abbreviation + ", location=" + location
				+ "]";
	}
}
