package samples.xml.validation;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class JaxpValidationSample {
	private static class ErrorHandlerImpl extends DefaultHandler {
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

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		validate(new File("Comp.xml"), "entrant.xsd");
	
	}

	private static void validate(File file, String string) {
		try {
			// create a factory
			SchemaFactory factory = SchemaFactory
					.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			//create a schema
			Schema schema = factory.newSchema(new File("entrant.xsd"));
			
			//validate
			Validator validator = schema.newValidator();
			ErrorHandlerImpl handler = new ErrorHandlerImpl();
			validator.setErrorHandler(handler);
			StreamSource streamSource = new StreamSource(file);
			validator.validate(streamSource);
			
			// output validation errors
			if (handler.isValidationError())
				System.out.println("XML Document has Error:"
						+ handler.validationError + " "
						+ handler.getSaxParseException().getMessage());
			else
				System.out.println("XML Document is valid");
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
