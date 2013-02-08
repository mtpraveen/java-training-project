package com.epam.logic.model;

public class Position {
	
	// Fields names for table 'positions'.
	public static final String POSITION_ID = "position_id";
	public static final String POSITION	= "position";
	
	private int positionId;
	private String position;
	
	public Position() {}
	
	public Position(int positionId, String position) {
		super();
		this.positionId = positionId;
		this.position = position;
	}
	
	public int getPositionId() {
		return positionId;
	}
	
	public void setPositionId(int positionId) {
		this.positionId = positionId;
	}
	
	public String getPosition() {
		return position;
	}
	
	public void setPosition(String position) {
		this.position = position;
	}
	
}
