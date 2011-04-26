package xml.writing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import xml.entity.generated.Flowers;
import xml.entity.generated.Flowers.Flower;
import xml.entity.generated.Flowers.Flower.GrowingTips;
import xml.entity.generated.Flowers.Flower.VisualParameters;

public class ModelGenerator {
	private static Flowers flowers = new Flowers();
	private static NodeList flowerNodes;
	private static File file = new File("flowers.xml");
	private static String expression = "/flowers/flower[growing_tips/lighting='photophilous']";
	static {
				
		XPathFactory factory = XPathFactory.newInstance();
		XPath xPath = factory.newXPath();

		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document document = builder.parse(file);
			
			flowerNodes = (NodeList) xPath.evaluate(expression, document,
														XPathConstants.NODESET);

			// for each flowerNode in flowerNodes...
			for (int i = 0; i < flowerNodes.getLength(); i++) {
				Node flowerNode = flowerNodes.item(i);
				
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
						VisualParameters visualParameters = new VisualParameters();
						NodeList visualParamsNodes = flowerParamsNodes.item(j).getChildNodes();
						for (int k = 0; k < visualParamsNodes.getLength(); k++) {
							Node visualParamNode = visualParamsNodes.item(k);
							String text = visualParamNode.getTextContent();
							//is stem colour visual parameter?
							if (visualParamNode.getNodeName().equalsIgnoreCase("stem_colour")){
								visualParameters.setStemColour(text);
							//is leafs colour visual parameter?
							} else if (visualParamNode.getNodeName().equalsIgnoreCase("leafs_colour")){
								visualParameters.setLeafsColour(text);
							//is average size visual parameter?
							} else if (visualParamNode.getNodeName().equalsIgnoreCase("ave_size")){
								visualParameters.setAveSize(Double.parseDouble(text));
							}
						}

						flower.setVisualParameters(visualParameters);
					//is growing tips top level node?
					} else if (flowerParamNode.getNodeName().equalsIgnoreCase("growing_tips")) {
						GrowingTips growingTips = new GrowingTips();
						NodeList growingTipsNodes = flowerParamsNodes.item(j).getChildNodes();
						for (int k = 0; k < growingTipsNodes.getLength(); k++) {
							Node growingTipNode = growingTipsNodes.item(k);
							String text = growingTipNode.getTextContent();
							//is temperature growing tips parameter?
							if (growingTipNode.getNodeName().equalsIgnoreCase("temperature")) {
								growingTips.setTemperature(Integer.parseInt(text));
							//is lighting growing tips parameter?
							} else if (growingTipNode.getNodeName().equalsIgnoreCase("lighting")) {
								growingTips.setLighting(text);
							//is watering growing tips parameter?
							} else if (growingTipNode.getNodeName().equalsIgnoreCase("watering")) {
								growingTips.setWatering(Integer.parseInt(text));
							}
						}
						flower.setGrowingTips(growingTips);
					} else if (flowerParamNode.getNodeName().equalsIgnoreCase("multiplying")){
							flower.setMultiplying(flowerParamNode.getTextContent());
					}	
				}	
				flowers.getFlower().add(flower);
			}
		} catch (XPathExpressionException e) {
			System.err.println(e);
		} catch (FileNotFoundException e) {
			System.err.println(e);
		} catch (ParserConfigurationException e) {
			System.err.println(e);
		} catch (SAXException e) {
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e);
		}	
	}

	public static Flowers getFlowers() {
		return flowers;
	}
	
	public static NodeList getFlowerNodes() {
		return flowerNodes;
	}
}
