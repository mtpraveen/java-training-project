/**
 * 
 */
package motor.depot.clientserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.log4j.Logger;

/**
 * @author dima
 * this command download file from server 
 */
public class DownloadFileClientCommandImpl extends AbstractClientServerCommandImpl
{
	byte[] data = new byte[0];
	String filePath = "";
	private final static Logger LOGGER = Logger.getLogger(DownloadFileClientCommandImpl.class);

	public DownloadFileClientCommandImpl(ClientServerCommand command) {
		super(command);
	}

	/**
	 * @return the data
	 */
	public byte[] getData()
	{
		return data;
	}

	/**
	 * @param data  the data to set
	 */
	public void setData(byte[] data)
	{
		this.data = data;
	}

	/**
	 * @return the filePath
	 */
	public String getFilePath()
	{
		return filePath;
	}

	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath)
	{
		this.filePath = filePath;
	}

	@Override
	public void processDataOnClient(DataInputStream fromServer, DataOutputStream toServer)
	{
		String filePath;
		boolean success = false;
		try
		{
			LOGGER.debug("Start receiving file");
			filePath = fromServer.readUTF();
			LOGGER.debug("Filepath : " + filePath);
			File file = new File(filePath);
			FileOutputStream outputStream = new FileOutputStream(file);
			int size = fromServer.readInt();
			LOGGER.debug("size : " + size);
			if(size>0)
			{
				byte[] buf = new byte[size];
				fromServer.read(buf);
				outputStream.write(buf);
			}
			LOGGER.debug("receiving done");
			outputStream.close();
			success = true;
			toServer.writeInt(1);
		} catch (IOException e)
		{
		}
		try
		{
			if (!success)
				toServer.writeInt(0);
		} catch (IOException e)
		{
		}
	}

	@Override
	public void sendToClient(DataOutputStream toClient) throws IOException
	{
		super.sendToClient(toClient);
		try
		{
			LOGGER.debug("Sending file to cleint");
			LOGGER.debug("Filepath = " + filePath);
			toClient.writeUTF(filePath);
			LOGGER.debug("data length = " + data.length);
			toClient.writeInt(data.length);
			if (data.length > 0)
			{
				LOGGER.debug("sending data");
				toClient.write(data, 0, data.length);
				LOGGER.debug("Data successfully send");
			}
		} catch (IOException e)
		{
		}
	}

	public boolean readAnswer(DataInputStream fromClient)
	{
		try
		{
			return fromClient.readInt() != 0;
		} catch (IOException e)
		{
		}
		return false;
	}
}
