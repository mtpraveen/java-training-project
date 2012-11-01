package com.epam.training.factoryMethod;

import java.util.ArrayList;
import java.util.Iterator;

import com.epam.training.observer.BaseObserver;

public class TransmissionFactory {
	
	private enum Transmissions {AUTOMATIC, MANUAL}
	
	public static AbstractTransmission getTransmission(String transmissionType) {
		Transmissions transmission = Transmissions.valueOf(transmissionType.toUpperCase());
		
		switch (transmission) {
			case AUTOMATIC: return AutomaticTransmission.getInstance();
			case MANUAL: return ManualTransmission.getInstance();
			default: throw new EnumConstantNotPresentException(Transmissions.class, transmission.name());			
		}		
	}	
	
}
