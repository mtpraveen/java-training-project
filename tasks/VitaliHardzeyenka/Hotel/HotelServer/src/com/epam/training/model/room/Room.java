package com.epam.training.model.room;

public class Room {
	private int numberRoom;
	private int numberSeats;
	private int dayPrice;
	private ClassApartments classApartments;
	
	public Room(int numberRoom, int numberSeats, ClassApartments classApartments, int dayPrice) {
		this.numberRoom = numberRoom;
		this.setNumberSeats(numberSeats);
		this.setClassApartments(classApartments);
		this.setDayPrice(dayPrice);
	}
	
	
	public int getNumberRoom() {
		return numberRoom;
	}
	public void setNumberRoom(int numberRoom) {
		this.numberRoom = numberRoom;
	}


	public int getNumberSeats() {
		return numberSeats;
	}


	public void setNumberSeats(int numberSeats) {
		this.numberSeats = numberSeats;
	}


	public int getDayPrice() {
		return dayPrice;
	}


	public void setDayPrice(int dayPrice) {
		this.dayPrice = dayPrice;
	}


	public ClassApartments getClassApartments() {
		return classApartments;
	}


	public void setClassApartments(ClassApartments classApartments) {
		this.classApartments = classApartments;
	}
}
