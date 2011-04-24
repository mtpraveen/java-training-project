package edu.XML.xpath;

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
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XpathSample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		find(new File("candy.xml"));
	}

	private static void find(File file) {
		XPathFactory factory = XPathFactory.newInstance();
		XPath xPath = factory.newXPath();

		try {
			// compile first
			XPathExpression xPathExpression = xPath
					.compile("/candies/candy/name");
			InputSource inputSource = new InputSource(new FileInputStream(file));
			// evaluate
			String name = xPathExpression.evaluate(inputSource);
			System.out.println("Name: " + name);

			// evaluate directly
			inputSource = new InputSource(new FileInputStream(file));
			// obtain nodes set
			DocumentBuilder builder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			Document document = builder.parse(file);
			String expression = "//title";
			NodeList nodes = (NodeList) xPath.evaluate(expression, document,
					XPathConstants.NODESET);
			System.out.println("Titles:");
			for (int i = 0; i < nodes.getLength(); i++) {
				Element element = (Element) nodes.item(i);
				System.out.println(element.getFirstChild().getNodeValue());
			}
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

}
