package org.bsu.entrant.training;
public class Mark {
	
	private int valueMark;

	public int getValueMark() {
		return valueMark;
	}

	public int setValueMark() {
		this.valueMark = ((int) (1 + Math.random() * 10));
		return valueMark;
	}

    


}
