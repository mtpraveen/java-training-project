package edu.XML.vallidation;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class DOMValidatingExample {
	private static class ErrorHandlerImpl implements ErrorHandler {
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

	public static void validate(File xmlFile, String schemaUrl) {
		try {
			//create a factory
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			
			//configure the factory
			factory.setNamespaceAware(true);
			factory.setValidating(true);
			factory.setAttribute(
					"http://java.sun.com/xml/jaxp/properties/schemaLanguage",
			"http://www.w3.org/2001/XMLSchema");
			factory.setAttribute(
					"http://java.sun.com/xml/jaxp/properties/schemaSource",
					schemaUrl);
			//parse and validate
			DocumentBuilder builder = factory.newDocumentBuilder();
			ErrorHandlerImpl handler = new ErrorHandlerImpl();
			builder.setErrorHandler(handler);
			builder.parse(xmlFile);
			
			// output validation errors
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
		validate(new File("candy.xml"),"candy.xsd");

	}

}
