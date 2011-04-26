package xml.xpath;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XpathFlowers {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		find(new File("flowers.xml"));
	}

	private static void find(File file) {
		XPathFactory factory = XPathFactory.newInstance();
		XPath xPath = factory.newXPath();

		try {
			// compile first
			final String[] flowerNames = new String[]{"lilium", "rose", "sunflower"};
			for (String flowerName: flowerNames) {
				XPathExpression xPathExpression = xPath
						.compile("/flowers/flower[@name='" + flowerName + "']/multiplying");
				// evaluate
				String multiplying = xPathExpression.evaluate(new InputSource(
																new FileInputStream(file)));
				System.out.println(flowerName + " multiplies by " + multiplying);
			}

			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document document = builder.parse(file);

			String expression = "/flowers/flower/growing_tips/temperature";
			NodeList nodes = (NodeList) xPath.evaluate(expression, document,
					XPathConstants.NODESET);
			System.out.println("Temperatures:");
			for (int i = 0; i < nodes.getLength(); i++) {
				Node element = nodes.item(i);
				System.out.println(element.getTextContent());
			}
			
			expression = "/flowers/flower[growing_tips/temperature>=25]";
			nodes = (NodeList) xPath.evaluate(expression, document,
					XPathConstants.NODESET);
			System.out.println("Flowers (temperature >=25): ");
			for (int i = 0; i < nodes.getLength(); i++) {
				Element element = (Element) nodes.item(i);
				System.out.println(element.getAttribute("name"));
			}

			expression = "/flowers/flower[@origin='europe']";
			nodes = (NodeList) xPath.evaluate(expression, document,
					XPathConstants.NODESET);
			System.out.println("Flowers from Europe: ");
			for (int i = 0; i < nodes.getLength(); i++) {
				Element element = (Element) nodes.item(i);
				System.out.println(element.getAttribute("name") + "-" + element.getElementsByTagName("temperature").item(0).getTextContent());
			}
			
			expression = "/flowers/descendant-or-self::flower[growing_tips/lighting='photophilous']";
			nodes = (NodeList) xPath.evaluate(expression, document,
					XPathConstants.NODESET);
			System.out.println("Photophilous flowers: ");
			for (int i = 0; i < nodes.getLength(); i++) {
				Element element = (Element) nodes.item(i);
				System.out.println(element.getAttribute("name"));
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

}
