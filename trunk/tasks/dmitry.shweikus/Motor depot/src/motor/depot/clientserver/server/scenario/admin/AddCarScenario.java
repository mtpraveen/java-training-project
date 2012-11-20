/**
 * 
 */
package motor.depot.clientserver.server.scenario.admin;

import motor.depot.clientserver.server.ClientThread;
import motor.depot.clientserver.server.scenario.AbstractScenario;
import motor.depot.model.Car;
import motor.depot.model.MotorDepot;

/**
 * @author dima
 *
 */
public class AddCarScenario extends AbstractScenario
{

	/**
	 * @param thread
	 */
	public AddCarScenario(ClientThread thread) {
		super(thread);
	}

	@Override
	public void run()
	{
		str("Model:");
		String model = readString(); 
		str("Number:");
		String number = readString(); 
		str("State:");
		String state = readString(); 
		str("Description:");
		String description = readString();
		Car car = MotorDepot.instance().addCar(model, number, state, description);
		str("Car created");
		waitForInput();
	}

}
