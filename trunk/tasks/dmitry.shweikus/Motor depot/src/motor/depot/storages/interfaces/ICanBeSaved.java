/**
 * 
 */
package motor.depot.storages.interfaces;

import java.io.Serializable;

import motor.depot.model.MotorDepot;

/**
 * @author dima
 *
 */
public interface ICanBeSaved extends Serializable
{
	String getClassId();
	int getId();
	void setId(int id);
	ICanBeSaved newInstance();
	ITableRowProvider getRowProvider();
	long getSerialVersionUID();
}
