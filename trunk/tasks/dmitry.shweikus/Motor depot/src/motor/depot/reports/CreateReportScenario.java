/**
 * 
 */
package motor.depot.reports;

import java.util.ArrayList;

import motor.depot.clientserver.ClientServerCommand;
import motor.depot.clientserver.DownloadFileClientCommandImpl;
import motor.depot.clientserver.server.ClientThread;
import motor.depot.clientserver.server.scenario.AbstractScenario;
import motor.depot.clientserver.server.scenario.MenuScenario;
import motor.depot.listclasses.ListWithIds;
import motor.depot.model.MotorDepot;
import motor.depot.storages.interfaces.ICanBeSaved;

/**
 * @author dima
 * 
 */
public class CreateReportScenario extends AbstractScenario
{

	/**
	 * @param thread
	 */
	public CreateReportScenario(ClientThread thread) {
		super(thread);
	}

	@Override
	public void run()
	{
		ArrayList<ListWithIds<? extends ICanBeSaved>> lists = MotorDepot.getInstance()
				.getAllListsWithIds();
		MenuScenario tables = new MenuScenario(thread);
		tables.setCaption("Select kind:");
		tables.setRepeatable(false);
		for (ListWithIds<? extends ICanBeSaved> listWithIds : lists)
		{
			tables.addMenuItem(listWithIds.getPrototype().getClassId(), null);
		}
		tables.run();
		if (tables.getSelectedItem() < 0)
			return;
		ListWithIds<? extends ICanBeSaved> list = lists.get(tables.getSelectedItem());
		MenuScenario scenario = new MenuScenario(thread);
		scenario.setRepeatable(false);
		scenario.setCaption("Select report format:");
		scenario.addMenuItem("XML", null);
		scenario.addMenuItem("CSV", null);
		scenario.run();
		IReportCreator reportCreator;
		switch (scenario.getSelectedItem()) {
		case 0:
			reportCreator = new XmlReportCreator();
			break;
		case 1:
			reportCreator = new CsvReportCreator();
			break;
		default:
			return;
		}
		str("Enter filename");
		String filePath = readString();
		DownloadFileClientCommandImpl cmd = (DownloadFileClientCommandImpl) ClientServerCommand.DOWNLOAD_FILE.getImpl();
		cmd.setData(reportCreator.build(list.getTableProvider(), list.getPrototype().getClassId()));
		cmd.setFilePath(filePath);
		cmd.sendToClient(writer);
		if(cmd.readAnswer(reader))
			str("Report created");
		else
			str("Error creating report");
		waitForInput();
	}

}
