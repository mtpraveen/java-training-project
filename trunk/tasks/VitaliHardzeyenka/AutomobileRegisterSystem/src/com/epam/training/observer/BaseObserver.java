package com.epam.training.observer;

import com.epam.training.carEquipment.Car;
import com.epam.training.carEquipment.Equipmment;
import com.epam.training.factoryMethod.AbstractTransmission;
import com.epam.training.factoryMethod.TransmissionFactory;

public abstract class BaseObserver {
	public abstract Object valueChanged(Car observed);
	public abstract Object valueChanged(Equipmment observed);
	public abstract Object valueChanged(AbstractTransmission observed);
}
