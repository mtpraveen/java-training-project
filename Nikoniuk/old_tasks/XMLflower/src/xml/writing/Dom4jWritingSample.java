package xml.writing;

import java.io.FileWriter;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.dom.DOMDocument;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.w3c.dom.NodeList;

import xml.entity.generated.Flowers;
import xml.entity.generated.Flowers.Flower;
import xml.entity.generated.Flowers.Flower.GrowingTips;
import xml.entity.generated.Flowers.Flower.VisualParameters;

public class Dom4jWritingSample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		writeToXml(ModelGenerator.getFlowers());
	}

	public static void writeToXml(Flowers flowers) {
		Document document = buildDocument(flowers);
		try {
			// write to a file output.xml
			String fileName = "output/dom4j-output.xml";
			XMLWriter writer = new XMLWriter(new FileWriter(fileName));
			writer.write(document);
			writer.close();

			System.out.println("Data is written to "+fileName);
			
			// Pretty print the document to System.out
			System.out.println("Print data to system.out (Pretty print)...");
			OutputFormat format = OutputFormat.createPrettyPrint();
			writer = new XMLWriter(System.out, format);
			writer.write(document);

			// Compact format to System.out
			System.out.println("Print data to system.out (Compact format)...");
			format = OutputFormat.createCompactFormat();
			writer = new XMLWriter(System.out, format);
			writer.write(document);
			
		} catch (IOException e) {
			System.err.println(e);
		}

	}

	private static Document buildDocument(Flowers flowers) {
		DocumentFactory factory = DocumentFactory.getInstance();
		Document document = factory.createDocument();
	
		Element root = document.addElement("flowers");		
		
		for (Flower flower: flowers.getFlower()) {
			Element flowerElement = factory.createElement("flower");
			flowerElement.add(factory.createAttribute(flowerElement, "name", flower.getName()));
			flowerElement.add(factory.createAttribute(flowerElement, "soil", flower.getSoil()));
			flowerElement.add(factory.createAttribute(flowerElement, "origin", flower.getOrigin()));
			
			VisualParameters visualParameters = flower.getVisualParameters();
			
			Element visualParametersElement = factory.createElement("visual_parameters");
			visualParametersElement.add(factory.createElement("stem_colour").addText(visualParameters.getStemColour()));
			visualParametersElement.add(factory.createElement("leafs_colour").addText(visualParameters.getLeafsColour()));
			visualParametersElement.add(factory.createElement("ave_size").addText(Double.toString(visualParameters.getAveSize())));
			flowerElement.add(visualParametersElement);
			
			GrowingTips growingTips = flower.getGrowingTips();
			
			Element growingTipsElement = factory.createElement("growing_tips");
			growingTipsElement.add(factory.createElement("lighting").addText(growingTips.getLighting()));
			growingTipsElement.add(factory.createElement("temperature").addText(Integer.toString(growingTips.getTemperature())));
			growingTipsElement.add(factory.createElement("watering").addText(Integer.toString(growingTips.getWatering())));
			flowerElement.add(growingTipsElement);
			
			growingTipsElement.add(factory.createElement("multiplying").addText(flower.getMultiplying()));

			root.add(flowerElement);
		}

		return document;
	}

}
