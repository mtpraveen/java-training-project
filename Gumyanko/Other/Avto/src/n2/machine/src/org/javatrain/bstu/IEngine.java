/**
 * 
 */
package n2.machine.src.org.javatrain.bstu;

/**
 * @author epam0009
 *
 */
public interface IEngine {
void onClutch ();       	// on Clutch (���.���������)
void offClutch (); 			// off Clutch (����.���������) 
void onTransmission(); 		// on Transmission(��������)
void offTransmission();		// off Transmission (��������)
void endingTheWheels();		// ending The Wheels(���������� �����)
int numberOfFuelSupply();			// fuel supply
int numberOfWheelRevolutions();     //numberOfWheelRevolutions
void increaseTransmission();		//increaseTransmission
void decreaseTransmission();
void createEngineSpeed();				//testSpeed	
}
