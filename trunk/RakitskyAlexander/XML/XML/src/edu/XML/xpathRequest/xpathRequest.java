/**
 * 
 */
package edu.XML.xpathRequest;

import java.io.File;
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

/**
 * @author Администратор
 *
 */
public class xpathRequest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		transform(new File("candy.xml"));

	}
	private static void transform(File file) {
		TransformerFactory factory = TransformerFactory.newInstance();

		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			Document document = builder.parse(file);
			DOMSource domSource = new DOMSource(document);
			String[] stylesheets = { 
//					"identityTransform.xslt",
//					"removeDuplicates.xslt",
//					 "filter.xslt",
//					 "sort.xslt",
					"htmlTransform.xslt",
//					 "indent.xslt",
					};
			for (String stylesheet : stylesheets) {
				System.out.println("\nTransformation for stylesheet: "
						+ stylesheet);
				StreamSource xsltSource = new StreamSource(new File(stylesheet));
				StreamResult streamResult = new StreamResult(System.out);
				Transformer transformer = factory.newTransformer(xsltSource);
				transformer.transform(domSource, streamResult);
				// another way to create a transformer is to use a template
				// Templates templates=factory.newTemplates(xsltSource);
				// Transformer transformer=templates.newTransformer();

			}
		} catch (TransformerConfigurationException e) {
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
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
