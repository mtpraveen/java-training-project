package com.epam.training.model.application;

import java.util.Date;

import com.epam.training.model.room.ClassApartments;

public class Application {
	private int numberSeats;
	private ClassApartments classApartments;
	private Date arrivalDate;
	private Date evictionDate;	
	
	public Application(int numberSeats, ClassApartments classApartments, Date arrivalDate, Date evictionDate) {
		this.numberSeats = numberSeats;
		this.classApartments = classApartments;
		this.arrivalDate = arrivalDate;
		this.evictionDate = evictionDate;
	}


	public int getNumberSeats() {
		return numberSeats;
	}


	public void setNumberSeats(int numberSeats) {
		this.numberSeats = numberSeats;
	}


	public ClassApartments getClassApartments() {
		return classApartments;
	}


	public void setClassApartments(ClassApartments classApartments) {
		this.classApartments = classApartments;
	}


	public Date getArrivalDate() {
		return arrivalDate;
	}


	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}


	public Date getEvictionDate() {
		return evictionDate;
	}


	public void setEvictionDate(Date evictionDate) {
		this.evictionDate = evictionDate;
	}	
}
