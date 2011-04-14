/**
 * 
 */
package n2.machine.src.org.javatrain.bstu;

/**
 * @author epam0009
 *
 */
public interface IEngine {
void onClutch ();       	// on Clutch (вкл.сцепление)
void offClutch (); 			// off Clutch (выкл.сцепление) 
void onTransmission(); 		// on Transmission(передачу)
void offTransmission();		// off Transmission (передачу)
void endingTheWheels();		// ending The Wheels(остановить колёса)
int numberOfFuelSupply();			// fuel supply
int numberOfWheelRevolutions();     //numberOfWheelRevolutions
void increaseTransmission();		//increaseTransmission
void decreaseTransmission();
void createEngineSpeed();				//testSpeed	
}
