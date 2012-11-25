/**
 * 
 */
package motor.depot.clientserver.server.scenario.admin;

import java.util.ArrayList;

import motor.depot.clientserver.ClientServerCommand;
import motor.depot.clientserver.DownloadFileClientCommandImpl;
import motor.depot.clientserver.server.ClientThread;
import motor.depot.clientserver.server.scenario.AbstractScenario;
import motor.depot.clientserver.server.scenario.MenuScenario;
import motor.depot.listclasses.ListWithIds;
import motor.depot.model.MotorDepot;
import motor.depot.reports.CsvReportCreator;
import motor.depot.reports.IReportCreator;
import motor.depot.reports.XmlReportCreator;
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
		tables.setCaption(thread.getString("CreateReportScenario.Select_kind")); //$NON-NLS-1$
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
		scenario.setCaption(thread.getString("CreateReportScenario.Select_report_format")); //$NON-NLS-1$
		scenario.addMenuItem("XML", null); //$NON-NLS-1$
		scenario.addMenuItem("CSV", null); //$NON-NLS-1$
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
		str(thread.getString("CreateReportScenario.Enter_filename")); //$NON-NLS-1$
		String filePath = readString();
		DownloadFileClientCommandImpl cmd = (DownloadFileClientCommandImpl) ClientServerCommand.DOWNLOAD_FILE.getImpl();
		cmd.setData(reportCreator.build(list.getTableProvider(), list.getPrototype().getClassId()));
		cmd.setFilePath(filePath);
		cmd.sendToClient(writer);
		if(cmd.readAnswer(reader))
			str(thread.getString("CreateReportScenario.Report_created")); //$NON-NLS-1$
		else
			str(thread.getString("CreateReportScenario.Error_creating_report")); //$NON-NLS-1$
		waitForInput();
	}

}
