/**
 * 
 */
package motor.depot.clientserver;

import java.io.PrintWriter;


/**
 * @author dima
 * 
 */
public enum ClientServerCommand
{
	SHOW_TEXT
	{
		@Override
		public AbstractClientServerCommandImpl getImpl()
		{
			return new ShowTextCommandImpl(this);
		}
	},
	GET_STRING
	{
		@Override
		public AbstractClientServerCommandImpl getImpl()
		{
			return new GetStringCommandImpl(this);
		}
	},
	CLOSE_CLIENT
	{
		@Override
		public AbstractClientServerCommandImpl getImpl()
		{
			return new CloseClientCommandImpl(this);
		}
	};
	public abstract AbstractClientServerCommandImpl getImpl();
	public static void sendText(PrintWriter writer, String textToClient)
	{
		ShowTextCommandImpl impl = new ShowTextCommandImpl(SHOW_TEXT);
		impl.sendString(writer, textToClient);
	}
}
