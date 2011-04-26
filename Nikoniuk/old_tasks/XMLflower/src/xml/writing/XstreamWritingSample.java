package xml.writing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import xml.entity.generated.Flowers;
import xml.entity.generated.Flowers.Flower;
import xml.entity.generated.Flowers.Flower.GrowingTips;
import xml.entity.generated.Flowers.Flower.VisualParameters;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class XstreamWritingSample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		writeToXml(ModelGenerator.getFlowers());

	}

	public static void writeToXml(Flowers flowersContainer) {
		final String fileName = "output/xstream-output.xml";
		try {
			XStream xstream = new XStream(new DomDriver());
			xstream.alias("flowers", Flowers.class);
			xstream.alias("flower", Flower.class);
			xstream.alias("visual_parameters", VisualParameters.class);
			xstream.alias("growing_tips", GrowingTips.class);
			// serialize
			File file = new File(fileName);
			FileOutputStream fos = new FileOutputStream(file);
			xstream.toXML(flowersContainer, fos);
			
			System.out.println("Data is written to " + fileName);
			
			// deserialize
			List<Flower> flowers = ((Flowers)xstream.fromXML(new FileInputStream(file))).getFlower();
			System.out.println("Data read from " + fileName + ": ");
			
			for (Flower flower: flowers) {
				System.out.println(new Formatter().format("%s - soil: %s, origin: %s...", flower.getName(), flower.getSoil(), flower.getOrigin()));
			}
		} catch (FileNotFoundException e) {
			System.err.println(e);
		}
	}

	
}
