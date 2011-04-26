package xml.dom;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import xml.model.Flower;

public class flowerDOM {
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
	
	public static void parse(File xmlFile, File xsdFile) {
		try {
			//create a factory
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			
			//configure the factory
			factory.setNamespaceAware(true);
			factory.setValidating(true);
			factory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaLanguage", "http://www.w3.org/2001/XMLSchema");
			factory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaSource", xsdFile);
			
			//parse and validate
			DocumentBuilder builder = factory.newDocumentBuilder();
			ErrorHandlerImpl handler = new ErrorHandlerImpl();
			builder.setErrorHandler(handler);
			
			Document document = builder.parse(xmlFile);
			
			// output validation errors
			if (handler.isValidationError()) {
				System.out.println("XML Document has Error:"
						+ handler.getSaxParseException().getMessage());
				System.exit(1);
			} else
				System.out.println("XML Document is valid");

			Element root = document.getDocumentElement();
			
			NodeList flowersNodes = root.getElementsByTagName("flower");
			// for each flowerNode in flowerNodes...
			for (int i = 0; i < flowersNodes.getLength(); i++) {
				Node flowerNode = flowersNodes.item(i);
				
				Flower flower = new Flower();
				
				// getting flower attributes
				if (flowerNode.hasAttributes()) {
					NamedNodeMap attrs = flowerNode.getAttributes();
					for (int j = 0; j < attrs.getLength(); j++) {
						String attrName = attrs.item(j).getNodeName();
						String attrText = attrs.item(j).getTextContent();
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
				
				// for each top level node in flowerNode
				NodeList flowerParamsNodes = flowerNode.getChildNodes();
				for (int j = 0; j < flowerParamsNodes.getLength(); j++) {
					//is visual parameters top level node?
					Node flowerParamNode = flowerParamsNodes.item(j);

					if (flowerParamNode.getNodeName().equalsIgnoreCase("visual_parameters")) {
						NodeList visualParamsNodes = flowerParamsNodes.item(j).getChildNodes();
						for (int k = 0; k < visualParamsNodes.getLength(); k++) {
							Node visualParamNode = visualParamsNodes.item(k);
							String text = visualParamNode.getTextContent();
							//is stem colour visual parameter?
							if (visualParamNode.getNodeName().equalsIgnoreCase("stem_colour")){
								flower.setStemColour(text);
							//is leafs colour visual parameter?
							} else if (visualParamNode.getNodeName().equalsIgnoreCase("leafs_colour")){
								flower.setLeafsColour(text);
							//is average size visual parameter?
							} else if (visualParamNode.getNodeName().equalsIgnoreCase("ave_size")){
								flower.setAveSize(Double.parseDouble(text));
							}
						}
					//is growing tips top level node?
					} else if (flowerParamNode.getNodeName().equalsIgnoreCase("growing_tips")) {
						NodeList growingTipsNodes = flowerParamsNodes.item(j).getChildNodes();
						for (int k = 0; k < growingTipsNodes.getLength(); k++) {
							Node growingTipNode = growingTipsNodes.item(k);
							String text = growingTipNode.getTextContent();
							//is temperature growing tips parameter?
							if (growingTipNode.getNodeName().equalsIgnoreCase("temperature")) {
								flower.setTemperature(Integer.parseInt(text));
							//is lighting growing tips parameter?
							} else if (growingTipNode.getNodeName().equalsIgnoreCase("lighting")) {
								flower.setLighting(text);
							//is watering growing tips parameter?
							} else if (growingTipNode.getNodeName().equalsIgnoreCase("watering")) {
								flower.setWatering(text);
							}
						}
					} else if (flowerParamNode.getNodeName().equalsIgnoreCase("multiplying")){
							flower.setMultiplying(flowerParamNode.getTextContent());
					}	
				}		
				System.out.println(flower);
			}
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
