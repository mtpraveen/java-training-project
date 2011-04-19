/**
 * 
 */
package autobase.server.db;

import java.io.IOException;

/**
 * @author Alexander Nikoniuk
 *<p>Represents dataset operations </p>
 */
public interface IDataSet {
	public void loadData() throws IOException;
	public void saveData() throws IOException;
	public void createSchema() throws IOException;

}
