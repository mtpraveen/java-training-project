/**
 * 
 */
package motor.depot.clientserver.server.scenario.admin;

import java.io.IOException;

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
	public void run() throws IOException
	{
		str(thread.getString("AddCarScenario.Model")); //$NON-NLS-1$
		String model = readString(); 
		str(thread.getString("AddCarScenario.Number")); //$NON-NLS-1$
		String number = readString(); 
		str(thread.getString("AddCarScenario.State")); //$NON-NLS-1$
		String state = readString(); 
		str(thread.getString("AddCarScenario.Description")); //$NON-NLS-1$
		String description = readString();
		Car car = MotorDepot.getInstance().addCar(model, number, state, description);
		str(thread.getString("AddCarScenario.Car_created")); //$NON-NLS-1$
		waitForInput();
	}

}
