package xml.stax;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.XMLEvent;

import xml.model.Flower;

public class flowerStAX {
	public static void main(String[] args) {
		File file = new File("flowers.xml");
		System.out.println("=====parse with curson=====");
		parseWithCursor(file);
		System.out.println("=====parse with iterator=====");		
		parseWithIterator(file);
	}

	private static void parseWithIterator(File file) {
		try {
			XMLEventReader reader = XMLInputFactory.newInstance()
					.createXMLEventReader(new FileInputStream(file));
			
			Flower flower = null;		
			String elementName = null;
			String elementText = null;
			
			while (reader.hasNext()) {
				XMLEvent event = reader.nextEvent();
				
				if (event.isEndElement()) {
					elementName = event.asEndElement().getName().toString();
					if (elementName.equalsIgnoreCase("flower")) {
						System.out.println(flower);
					//is stem colour visual parameter?
					} else if (elementName.equalsIgnoreCase("stem_colour")){
						flower.setStemColour(elementText);
					//is leafs colour visual parameter?
					} else if (elementName.equalsIgnoreCase("leafs_colour")){
						flower.setLeafsColour(elementText);
					//is average size visual parameter?
					} else if (elementName.equalsIgnoreCase("ave_size")){
						flower.setAveSize(Double.parseDouble(elementText));
					} else if (elementName.equalsIgnoreCase("temperature")) {
						flower.setTemperature(Integer.parseInt(elementText));
					//is lighting growing tips parameter?
					} else if (elementName.equalsIgnoreCase("lighting")) {
						flower.setLighting(elementText);
					//is watering growing tips parameter?
					} else if (elementName.equalsIgnoreCase("watering")) {
						flower.setWatering(elementText);
					} else if (elementName.equalsIgnoreCase("multiplying")){
						flower.setMultiplying(elementText);
					}
				} else if (event.isStartElement()) {
					elementName = event.asStartElement().getName().toString();
					elementText = null;
					
					//new flower element?
					if (elementName.equalsIgnoreCase("flower")){
						flower = new Flower();
						
						//flower attributes
						Iterator<Attribute> attributes = event.asStartElement().getAttributes();
						while (attributes.hasNext()) {
							Attribute attr = attributes.next();
							String attrName = attr.getName().toString();
							String attrText = attr.getValue();
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
				} else if (event.isCharacters()) {
					elementText = event.asCharacters().toString();
				}	
				
			}
		} catch (FileNotFoundException e) {
			System.err.println(e);
		} catch (XMLStreamException e) {
			System.err.println(e);
		} catch (FactoryConfigurationError e) {
			System.err.println(e);
		}

	}

	private static void parseWithCursor(File file) {
		try {
			XMLStreamReader reader = XMLInputFactory.newInstance()
					.createXMLStreamReader(new FileInputStream(file));
			
			Flower flower = null;		
			String elementName = null;
			String elementText = null;
			
			while (reader.hasNext()) {
				int event = reader.next();
				switch (event) {
				case XMLStreamConstants.END_ELEMENT:	
					elementName = reader.getLocalName();
					if (elementName.equalsIgnoreCase("flower")) {
						System.out.println(flower);
					//is colour visual parameter?
					} else if (elementName.equalsIgnoreCase("stem_colour")){
						flower.setStemColour(elementText);
					//is leafs colour visual parameter?
					} else if (elementName.equalsIgnoreCase("leafs_colour")){
						flower.setLeafsColour(elementText);
					//is average size visual parameter?
					} else if (elementName.equalsIgnoreCase("ave_size")){
						flower.setAveSize(Double.parseDouble(elementText));
					} else if (elementName.equalsIgnoreCase("temperature")) {
						flower.setTemperature(Integer.parseInt(elementText));
					//is lighting growing tips parameter?
					} else if (elementName.equalsIgnoreCase("lighting")) {
						flower.setLighting(elementText);
					//is watering growing tips parameter?
					} else if (elementName.equalsIgnoreCase("watering")) {
						flower.setWatering(elementText);
					} else if (elementName.equalsIgnoreCase("multiplying")){
						flower.setMultiplying(elementText);
					}
					break;
					
				case XMLStreamConstants.START_ELEMENT:
					elementName = reader.getLocalName();
					elementText = null;
					
					//new flower element?
					if (elementName.equalsIgnoreCase("flower")){
						flower = new Flower();
						
						//flower attributes
						for (int i = 0; i < reader.getAttributeCount(); i++) {
							String attrName = reader.getAttributeName(i).toString();
							String attrText = reader.getAttributeValue(i);
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
					break;
					
				case XMLStreamConstants.CHARACTERS:
					elementText = reader.getText();
					break;
				}
			}
		} catch (FileNotFoundException e) {
			System.err.println(e);
		} catch (XMLStreamException e) {
			System.err.println(e);
		} catch (FactoryConfigurationError e) {
			System.err.println(e);
		}

	}
}
