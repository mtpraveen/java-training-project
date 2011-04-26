package samples.xml.xpath;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XpathSample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		find(new File("Comp.xml"));
	}

	private static void find(File file) {
		XPathFactory factory = XPathFactory.newInstance();
		XPath xPath = factory.newXPath();

		try {
			// compile first
			XPathExpression xPathExpression = xPath
					.compile("/Devices/Device[Origin='China']/Type/Energy");
			InputSource inputSource = new InputSource(new FileInputStream(file));
			// evaluate
			String title = xPathExpression.evaluate(inputSource);
			System.out.println("Energy: " + title);

			
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		//} catch (ParserConfigurationException e) {
	//		// TODO Auto-generated catch block
	//		e.printStackTrace();
		//} catch (SAXException e) {
		//	// TODO Auto-generated catch block
	//		e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
