package xml.binding;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import xml.entity.generated.Flowers;
import xml.entity.generated.ObjectFactory;


public class JaxbMarshaller {

	/**
	 * @param args
	 */
	public static void marshal() {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(JaxbConstants.PACKAGE_NAME);
			Marshaller marshaller = jaxbContext.createMarshaller();
			
			ObjectFactory factory = new ObjectFactory();
			
			Flowers flowers = factory.createFlowers();
			
			flowers.getFlower().addAll(ModelGenerator.generateFlowers());
			marshaller.setProperty("jaxb.formatted.output",Boolean.TRUE);
			marshaller.marshal(flowers, System.out);
			marshaller.marshal(flowers, new File(JaxbConstants.OUTPUT_XML_FILE_PATH));
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
