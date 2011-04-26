package xml.transformation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class TransformationSample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		transform(new File("flowers.xml"), new File("flowers.xslt"));

	}

	private static void transform(File xmlFile, File xslFile) {
		TransformerFactory factory = TransformerFactory.newInstance();

		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			Document document = builder.parse(xmlFile);
			DOMSource domSource = new DOMSource(document);


			StreamSource xsltSource = new StreamSource(xslFile);
			StreamResult streamResult = new StreamResult(new FileOutputStream("transformation.html"));
			Transformer transformer = factory.newTransformer(xsltSource);
			transformer.transform(domSource, streamResult);
				// another way to create a transformer is to use a template
				// Templates templates=factory.newTemplates(xsltSource);
				// Transformer transformer=templates.newTransformer();

		} catch (TransformerConfigurationException e) {
			System.err.println(e);
		} catch (ParserConfigurationException e) {
			System.err.println(e);
		} catch (SAXException e) {
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e);
		} catch (TransformerException e) {
			System.err.println(e);
		}
	}

}
