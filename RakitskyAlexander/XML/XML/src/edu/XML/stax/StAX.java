package edu.XML.stax;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.XML.Less.Candy;
import edu.XML.Less.Ingredients;
import edu.XML.Less.TypeCandies;
import edu.XML.Less.ValueCandies;

public class StAX {
	public static void main(String[] args) {
		File file = new File("Candy.xml");
		parseWithCursor(file);
		//parseWithIterator(file);
	}

	private static void parseWithIterator(File file) {
		try {
			System.out.println("WHITH ITERATOR!!!");
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
			System.out.println("WHITH CURSOR!!!");
			//Candy candy=new Candy();
			XMLStreamReader reader = XMLInputFactory.newInstance()
					.createXMLStreamReader(new FileInputStream(file));
			while (reader.hasNext()) {
				int event = reader.next();
				if (event == XMLStreamConstants.START_ELEMENT) {
					String nameReader = reader.getLocalName();
					System.out
							.println("START_ELEMENT " + nameReader);
					boolean isCandy=nameReader.equalsIgnoreCase("candy");
					if (isCandy)
					{
						Candy candy=new Candy();
						
					}
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
