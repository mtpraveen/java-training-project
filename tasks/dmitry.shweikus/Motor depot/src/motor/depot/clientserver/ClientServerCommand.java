/**
 * 
 */
package motor.depot.clientserver;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.DataOutputStream;


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
	},
	DOWNLOAD_FILE {
		@Override
		public AbstractClientServerCommandImpl getImpl()
		{
			return new DownloadFileClientCommandImpl(this);
		}
	}
	;
	public abstract AbstractClientServerCommandImpl getImpl();
	public static void sendText(DataOutputStream writer, String textToClient)
	{
		ShowTextCommandImpl impl = new ShowTextCommandImpl(SHOW_TEXT);
		try
		{
			impl.sendString(writer, textToClient);
		} catch (IOException e)
		{
		}
	}
}
