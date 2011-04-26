package samples.xml.dom;

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
import org.xml.sax.SAXException;

import samples.xml.model.device;
import samples.xml.model.device;

public class DOMSample {
	public static void parse(File xmlFile) {
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			Document document = builder.parse(xmlFile);

			Element root = document.getDocumentElement();
			System.out.println("Root is " + root.getTagName());
			if (root.hasAttributes()) {
				System.out.println("Attributes:");
				NamedNodeMap attrs = root.getAttributes();
				for (int i = 0; i < attrs.getLength(); i++) {
					System.out.println(attrs.item(i));
				}
			}
			printElements(root);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param root
	 */
	private static void printElements(Element root) {
		device device=null;
		boolean isDevice = root.getNodeName().equalsIgnoreCase("device");
		if (isDevice){
			device = new device();
		}
		if (root.hasChildNodes()) {
			NodeList deviceNodes = root.getChildNodes();
			for (int j = 0; j < deviceNodes.getLength(); j++) {
				Node deviceNode = deviceNodes.item(j);
				if (deviceNode.getNodeType() == Node.ELEMENT_NODE) {
					String text = deviceNode.getTextContent();
					if (isDevice){
						System.out
						.println("Found element:" + deviceNode.getNodeName()+" "+text);
						if (deviceNode.getNodeName().equalsIgnoreCase("name")){
							device.setName(text);
						}else if (deviceNode.getNodeName().equalsIgnoreCase("origin")){
							device.setOrigin(text);
						}else if (deviceNode.getNodeName().equalsIgnoreCase("price")){
							device.setPrice(text);
						}//else if (studentNode.getNodeName().equalsIgnoreCase("mark")){
						//	device.setMark(Double.parseDouble(text));
						//}
					}
					printElements((Element) deviceNode);
				}
			}
		}
		if (device!=null){
			System.out.println(device);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		parse(new File("Comp.xml"));

	}

}
