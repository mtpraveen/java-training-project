package samples.xml.sax;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import samples.xml.model.device;

public class SAXExample {

	public static void parse(File xmlFile) {
			
		try {
			CustomHandler sh=new CustomHandler();
			SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
			parser.parse(xmlFile, sh);
			System.out.println(sh.getDevices());
			
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
	 * @param args
	 */
	public static void main(String[] args) {
		parse(new File("Comp.xml"));
		
	}

}
