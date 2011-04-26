package xml.sax;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class flowerSAX {
	
	public static void parse(File xmlFile, File xsdFile) {
		try {
			//create a factory
			SAXParserFactory factory = SAXParserFactory.newInstance();
			
			//configure the factory
			factory.setFeature("http://xml.org/sax/features/validation",true);
			factory.setFeature("http://apache.org/xml/features/validation/schema", true);
			factory.setFeature("http://apache.org/xml/features/validation/schema-full-checking",
					true);
			
			//parse and validate
			SAXParser parser = factory.newSAXParser();
			parser.setProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage",
			"http://www.w3.org/2001/XMLSchema");
			parser.setProperty("http://java.sun.com/xml/jaxp/properties/schemaSource",
			xsdFile);
			CustomHandler handler = new CustomHandler();
			
			// output validation errors
			parser.parse(xmlFile, handler);
			if (handler.isValidationError())
				System.out.println("XML Document has Error:"
						+ handler.getSaxParseException().getMessage());
			else
				System.out.println("XML Document is valid");

		} catch (ParserConfigurationException e) {
			System.err.println(e);
		} catch (SAXException e) {
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		parse(new File("flowers.xml"), new File("flowers.xsd"));
	}

}
