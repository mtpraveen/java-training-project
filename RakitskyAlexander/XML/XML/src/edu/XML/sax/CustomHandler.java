package edu.XML.sax;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import edu.XML.Less.Candy;

public class CustomHandler extends DefaultHandler {
	private List<Candy> candies=new ArrayList<Candy>();
	private int x;
	private String element;
	private Candy candy;
	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
	//	if 
		System.out.println("Start of element: "+qName);
		element=qName;
		if (qName.equalsIgnoreCase("candy"))
		candy=new Candy();
			//	System.out.println("Trali-vali"+attributes.ge);	
		for (int i=0;i<attributes.getLength();i++){//!!!!!
			System.out.println(attributes.getLocalName(i)+"="+attributes.getValue(i));//!!!!
		}
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		System.out.println("End of element: "+qName);
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
	 */
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		String text = new String(ch, start,length);
		System.out.println("Element text: "+text);
		if (element.equalsIgnoreCase("name")){
			System.out.println(" this is name element text: "+text);
		}
		
	}

}
