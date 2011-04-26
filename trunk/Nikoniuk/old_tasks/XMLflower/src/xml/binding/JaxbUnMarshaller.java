package xml.binding;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import xml.entity.generated.Flowers;
import xml.entity.generated.Flowers.Flower;

public class JaxbUnMarshaller {

	/**
	 * @param args
	 */
	public static void unmarshal() {
		try {
			File xmlDocument = new File(JaxbConstants.OUTPUT_XML_FILE_PATH);

			// Create a JAXBContext object
			JAXBContext jaxbContext = JAXBContext
					.newInstance(JaxbConstants.PACKAGE_NAME);
			// Create an Unmarshaller object
			Unmarshaller unMarshaller = jaxbContext.createUnmarshaller();
			// Unmarshal an XML document to a root object

			Flowers flowersContainer = (Flowers) unMarshaller.unmarshal(xmlDocument);

			// Output the element and attribute values in XML document
			List<Flower> flowers = flowersContainer.getFlower();
			System.out.println("\nFlowers:");
			for (int i = 0; i < flowers.size(); i++) {
				Flower flower = flowers.get(i);

				System.out.println("\nName: " + flower.getName());
				System.out.println("Soil: " + flower.getSoil());
				System.out.println("Origin: " + flower.getOrigin());
			}
		} catch (JAXBException e) {
			System.err.println(e);
		}
	}

}
