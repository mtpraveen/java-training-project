/**
 * 
 */
package transports;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
//import java.util.logging.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * @author Мара
 *
 */
public class Application {
	private static final Logger LOGGER = Logger.getLogger(Application.class);

	public static void main(String[] args) {
		BasicConfigurator.configure(); // Add a ConsoleAppender that uses PatternLayout using the PatternLayout#TTCC_CONVERSION_PATTERN and prints to System.out to the root category.
		PropertyConfigurator.configure("log4j.properties"); //Read configuration options from properties.
		try {
			TransportSystem.main(args);
			} catch (RuntimeException e) {
			LOGGER.error("Could not find the file log4j.properties!",e);
		}finally{
			System.out.println("Done.");
		}
		


	}

}
