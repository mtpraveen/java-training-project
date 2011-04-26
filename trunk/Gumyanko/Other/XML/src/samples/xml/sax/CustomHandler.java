package samples.xml.sax;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import samples.xml.model.device;

public class CustomHandler extends DefaultHandler {

	
	ArrayList<device> devices = new	ArrayList<device>();		
	device curr =null;
	
	public ArrayList<device> getDevices(){
		return devices;
	}
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		super.characters(ch, start, length);
		System.out.print(new String(ch, start, length)); 
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		System.out.println("Start of element: "+qName);
		if (qName.equals("Device")){
			curr = new device();
			curr.setCritical(attributes.getValue(0));
			curr.setName(attributes.getValue(1));
			curr.setOrigin(attributes.getValue(2));
			curr.setPrice(attributes.getValue(3));
			System.out.println(curr);
		}
		
		for (int i=0;i<attributes.getLength();i++){
			System.out.println(attributes.getLocalName(i)+"="+attributes.getValue(i));
		}
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		System.out.println("\nEnd of element: "+qName);
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
	 */
//	@Override
//	public void characters(char[] ch, int start, int length)
//			throws SAXException {
//		String text = new String(ch, start,length);
//		System.out.println("Element text: "+text);
//	}

}