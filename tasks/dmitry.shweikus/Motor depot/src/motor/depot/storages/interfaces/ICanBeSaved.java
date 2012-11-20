/**
 * 
 */
package motor.depot.storages.interfaces;

import motor.depot.model.MotorDepot;

/**
 * @author dima
 *
 */
public interface ICanBeSaved
{
	AbstractItemStateSaver getSaver(AbstractStorage saverCreator);
	void loadPrimitives(AbstractItemStateLoader stateGetter);
	void loadObjects(MotorDepot motorDepot, AbstractItemStateLoader getter);
	String getClassId();
	int getId();
	void setId(int id);
	ICanBeSaved newInstance();
	ITableRowProvider getRowProvider();
}
