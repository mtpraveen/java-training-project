package samples.xml.stax;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;

public class StAXExample {
	public static void main(String[] args) {
		File file = new File("Comp.xml");
		parseWithCursor(file);
		//parseWithIterator(file);
	}

	private static void parseWithIterator(File file) {
		try {
			XMLEventReader reader = XMLInputFactory.newInstance()
					.createXMLEventReader(new FileInputStream(file));
			while (reader.hasNext()) {
				XMLEvent event = reader.nextEvent();
				if (event.isStartElement()) {
					System.out.println("Start element "
							+ event.asStartElement().getName());
				} else if (event.isCharacters()) {
					System.out.println("Text " + event.asCharacters());
				}

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void parseWithCursor(File file) {
		try {
			XMLStreamReader reader = XMLInputFactory.newInstance()
					.createXMLStreamReader(new FileInputStream(file));
			while (reader.hasNext()) {
				int event = reader.next();
				if (event == XMLStreamConstants.START_ELEMENT) {
					System.out
							.println("START_ELEMENT " + reader.getLocalName());
					System.out.println("Attributes: ");
					for (int i = 0; i < reader.getAttributeCount(); i++) {
						System.out.println(reader.getAttributeName(i) + "="
								+ reader.getAttributeValue(i));
					}

				} else if (event == XMLStreamConstants.CHARACTERS) {
					System.out.println("Text: " + reader.getText());
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
