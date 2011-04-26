package xml.writing;

import java.io.FileOutputStream;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

import xml.entity.generated.Flowers;
import xml.entity.generated.Flowers.Flower;
import xml.entity.generated.Flowers.Flower.GrowingTips;
import xml.entity.generated.Flowers.Flower.VisualParameters;

public class JdomWritingSample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		writeToXml(ModelGenerator.getFlowers());

	}

	public static void writeToXml(Flowers flowers) {
		
		Element root = new Element("flowers");
		for (Flower flower: flowers.getFlower()) {
			Element flowerElement = new Element("flower");
			flowerElement.setAttribute("name", flower.getName());
			flowerElement.setAttribute("soil", flower.getSoil());
			flowerElement.setAttribute("origin", flower.getOrigin());
			
			VisualParameters visualParameters = flower.getVisualParameters();
			
			Element visualParametersElement = new Element("visual_parameters");
			visualParametersElement.addContent(new Element("stem_colour").addContent(visualParameters.getStemColour()));
			visualParametersElement.addContent(new Element("leafs_colour").addContent(visualParameters.getLeafsColour()));
			visualParametersElement.addContent(new Element("ave_size").addContent(Double.toString(visualParameters.getAveSize())));
			flowerElement.addContent(visualParametersElement);
			
			GrowingTips growingTips = flower.getGrowingTips();
			
			Element growingTipsElement = new Element("growing_tips");
			growingTipsElement.addContent(new Element("lighting").addContent(growingTips.getLighting()));
			growingTipsElement.addContent(new Element("temperature").addContent(Integer.toString(growingTips.getTemperature())));
			growingTipsElement.addContent(new Element("watering").addContent(Integer.toString(growingTips.getWatering())));
			flowerElement.addContent(growingTipsElement);
			
			growingTipsElement.addContent(new Element("multiplying").addContent(flower.getMultiplying()));

			root.addContent(flowerElement);
		}
		
		Document document = new Document(root);
		XMLOutputter out = new XMLOutputter();
		try {
			String fileName = "output/jdom-output.xml";
			FileOutputStream fos = new FileOutputStream(fileName);
			out.output(document, fos);
			
			System.out.println("Data is written to "+fileName);
		} catch (Exception e) {
			System.out.println("Exception while outputting JDOM:");
			e.printStackTrace();
		}

	}

}
