/**
 * 
 */
package motor.depot.exceptions;

import motor.depot.model.Trip;

/**
 * @author dima
 *
 */
public class CannotChangeAssignedDriverException extends RuntimeException
{
	Trip trip;

	/**
	 * @param trip
	 */
	public CannotChangeAssignedDriverException(Trip trip) {
		super();
		this.trip = trip;
	}

	/**
	 * @return the trip
	 */
	public Trip getTrip()
	{
		return trip;
	}
	/* (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage()
	{
		return "Cannot change assignet driver for trip : " + trip.toString();
	}
}
