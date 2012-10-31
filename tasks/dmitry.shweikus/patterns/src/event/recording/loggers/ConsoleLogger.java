/**
 * 
 */
package event.recording.loggers;

/**
 * @author dima
 *
 */
public class ConsoleLogger extends AbstractEventLogger
{

	/* (non-Javadoc)
	 * @see event.recording.loggers.AbstractEventLogger#Write(java.lang.String)
	 */
	@Override
	protected void Write(String messageDescription)
	{
		System.out.println(messageDescription);
	}

}
