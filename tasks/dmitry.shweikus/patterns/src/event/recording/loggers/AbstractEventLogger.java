/**
 * 
 */
package event.recording.loggers;

import java.util.Date;

import event.recording.system.Message;

/**
 * @author dima
 *
 */
public abstract class AbstractEventLogger
{
	protected abstract void Write(String messageDescription);
	public void Event(String description)
	{
		WriteMessage("EVENT", description);
	}
	public void Error(String description)
	{
		WriteMessage("ERROR", description);
	}
	public void UnknownMessage(Message message)
	{
		WriteMessage("UNKNOWNMESSAGE", message.getKind() + " : " + message.getExtraData());
	}
	protected void WriteMessage(String category, String description)
	{
		Write((new Date()) + " : " + category + " : " + description);
	}
	/**
	 * @param string
	 */
	public void CustomMessage(String message)
	{
		WriteMessage("DESCRIPTION", message);
	}
}
