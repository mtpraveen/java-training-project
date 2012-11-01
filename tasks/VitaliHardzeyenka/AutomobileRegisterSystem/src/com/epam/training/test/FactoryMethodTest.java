package com.epam.training.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.epam.training.factoryMethod.AbstractTransmission;
import com.epam.training.factoryMethod.TransmissionFactory;

public class FactoryMethodTest {

	@Test
	public void testTransmissionFactory() {
		TransmissionFactory transmissionFactory  = new TransmissionFactory();
		AbstractTransmission manualTransmission = transmissionFactory.getTransmission("manual");
		AbstractTransmission autoTransmission = transmissionFactory.getTransmission("automatic");
		
		manualTransmission.increaseVelocity();
		manualTransmission.decreaseVelocity();
		
		autoTransmission.increaseVelocity();
		autoTransmission.decreaseVelocity();
	}
}