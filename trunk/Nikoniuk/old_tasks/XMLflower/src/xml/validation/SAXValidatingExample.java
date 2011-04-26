package xml.validation;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXValidatingExample {
	private static class Validator extends DefaultHandler {
		private boolean validationError = false;
		private SAXParseException saxParseException = null;

		public boolean isValidationError() {
			return validationError;
		}

		public SAXParseException getSaxParseException() {
			return saxParseException;
		}

		public void error(SAXParseException exception) throws SAXException {
			validationError = true;
			saxParseException = exception;
		}

		public void fatalError(SAXParseException exception) throws SAXException {
			validationError = true;
			saxParseException = exception;
		}

		public void warning(SAXParseException exception) throws SAXException {
		}
	}

	public static void validate(File xmlFile,File xsdFile) {
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
			Validator handler = new Validator();
			
			// output validation errors
			parser.parse(xmlFile, handler);
			if (handler.isValidationError())
				System.out.println("XML Document has Error:"
						+ handler.validationError + " "
						+ handler.getSaxParseException().getMessage());
			else
				System.out.println("XML Document is valid");
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		validate(new File("flowers.xml"),new File("flowers.xsd"));

	}

}
