/**
 * 
 */
package motor.depot.clientserver;

import java.io.DataOutputStream;
import java.io.IOException;


/**
 * @author dima
 * implementation of command. Used on client and server 
 */
public enum ClientServerCommand
{
	/**
	 * show text to user
	 */
	SHOW_TEXT
	{
		@Override
		public AbstractClientServerCommandImpl getImpl()
		{
			return new ShowTextCommandImpl(this);
		}
	},
	/**
	 * receive simple string, password or file from client
	 */
	GET_STRING
	{
		@Override
		public AbstractClientServerCommandImpl getImpl()
		{
			return new GetStringCommandImpl(this);
		}
	},
	/**
	 * disconnect client
	 */
	CLOSE_CLIENT
	{
		@Override
		public AbstractClientServerCommandImpl getImpl()
		{
			return new CloseClientCommandImpl(this);
		}
	},
	/**
	 * receive file from server
	 */
	DOWNLOAD_FILE {
		@Override
		public AbstractClientServerCommandImpl getImpl()
		{
			return new DownloadFileClientCommandImpl(this);
		}
	}
	;
	public abstract AbstractClientServerCommandImpl getImpl();
	/**
	 * sends text from server to client
	 * @param writer - 
	 * @param textToClient
	 * @throws IOException 
	 */
	public static void sendText(DataOutputStream writer, String textToClient) throws IOException
	{
		ShowTextCommandImpl impl = new ShowTextCommandImpl(SHOW_TEXT);
		impl.sendString(writer, textToClient);
	}
}
