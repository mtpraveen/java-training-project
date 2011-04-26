package xml.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import xml.model.Flower;

public class CustomHandler extends DefaultHandler {

	private String elementText;
	private Flower flower;
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

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if (qName.equalsIgnoreCase("flower")){
			flower = new Flower();
			
			//flower attributes
			for (int i = 0; i < attributes.getLength(); i++) {
				String attrName = attributes.getLocalName(i);
				String attrText = attributes.getValue(i);
				//is flower name?
				if (attrName.equalsIgnoreCase("name")) {
					flower.setName(attrText);
				//is flower soil?
				} else if (attrName.equalsIgnoreCase("soil")) {
					flower.setSoil(attrText);
				//is flower origin?
				} else if (attrName.equalsIgnoreCase("origin")) {
					flower.setOrigin(attrText);
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (qName.equalsIgnoreCase("flower")) {
			System.out.println(flower);
		//is stem colour visual parameter?
		} else if (qName.equalsIgnoreCase("stem_colour")){
			flower.setStemColour(elementText);
		//is leafs colour visual parameter?
		} else if (qName.equalsIgnoreCase("leafs_colour")){
			flower.setLeafsColour(elementText);
		//is average size visual parameter?
		} else if (qName.equalsIgnoreCase("ave_size")){
			flower.setAveSize(Double.parseDouble(elementText));
		//is temperature parameter?
		} else if (qName.equalsIgnoreCase("temperature")) {
			flower.setTemperature(Integer.parseInt(elementText));
		//is lighting growing tips parameter?
		} else if (qName.equalsIgnoreCase("lighting")) {
			flower.setLighting(elementText);
		//is watering growing tips parameter?
		} else if (qName.equalsIgnoreCase("watering")) {
			flower.setWatering(elementText);
		} else if (qName.equalsIgnoreCase("multiplying")){
			flower.setMultiplying(elementText);
		}
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
	 */
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		elementText = new String(ch, start, length);
	}

}