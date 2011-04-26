package xml.writing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import xml.entity.generated.Flowers;
import xml.entity.generated.Flowers.Flower;
import xml.entity.generated.Flowers.Flower.GrowingTips;
import xml.entity.generated.Flowers.Flower.VisualParameters;

public class StaxWritingSample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		writeToXml(ModelGenerator.getFlowers());

	}

	public static void writeToXml(Flowers flowers) {
		XMLOutputFactory factory = XMLOutputFactory.newInstance();
		XMLStreamWriter writer;
		try {
			String fileName = "output/stax-output.xml";
			writer = factory.createXMLStreamWriter(new FileOutputStream(
					new File(fileName)));
			writer.writeStartElement("flowers");
			for (Flower flower : flowers.getFlower()) {
				writer.writeStartElement("flower");
				
				writer.writeAttribute("name", flower.getName());
				writer.writeAttribute("soil", flower.getSoil());
				writer.writeAttribute("origin", flower.getOrigin());

				writer.writeStartElement("visual_parameters");
				VisualParameters visualParameters = flower.getVisualParameters();
				
				writer.writeStartElement("stem_colour");
				writer.writeCharacters(visualParameters.getStemColour());
				writer.writeEndElement();// stem_colour
				
				writer.writeStartElement("leafs_colour");
				writer.writeCharacters(visualParameters.getLeafsColour());
				writer.writeEndElement();// leafs_colour
				
				writer.writeStartElement("ave_size");
				writer.writeCharacters(Double.toString(visualParameters.getAveSize()));
				writer.writeEndElement();// ave_size
				
				writer.writeEndElement();// visual_parameters

				writer.writeStartElement("growing_tips");
				GrowingTips growingTips = flower.getGrowingTips();
				
				writer.writeStartElement("temperature");
				writer.writeCharacters(Integer.toString(growingTips.getTemperature()));
				writer.writeEndElement();// stem_colour
				
				writer.writeStartElement("lighting");
				writer.writeCharacters(growingTips.getLighting());
				writer.writeEndElement();// leafs_colour
				
				writer.writeStartElement("watering");
				writer.writeCharacters(Integer.toString(growingTips.getWatering()));
				writer.writeEndElement();// ave_size
				
				writer.writeEndElement();// growing_tips

				writer.writeStartElement("multiplying");
				writer.writeCharacters(flower.getMultiplying());
				writer.writeEndElement();// multiplying

				writer.writeEndElement();// flower
			}
			writer.writeEndElement();// flowers

			writer.writeEndDocument();
			writer.close();

			System.out.println("Data is written to " + fileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
