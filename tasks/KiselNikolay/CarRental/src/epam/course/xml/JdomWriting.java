package epam.course.xml;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

import epam.course.domain.Car;
import epam.course.domain.Order;

public class JdomWriting {
	
	public static void writeCarsToXml(List<Car> cars, String fileName) {
		Element root = new Element("cars");
		for (Car car : cars) {
			Element carElement=new Element("car");
			Element idElement=new Element("id");
			idElement.addContent(Integer.toString(car.getId()));
			
			Element brandElement=new Element("brand");
			brandElement.addContent(car.getBrand());
			
			Element modelElement=new Element("model");
			modelElement.addContent(car.getModel());
			
			Element yearElement=new Element("year");
			yearElement.addContent(Integer.toString(car.getYear()));
			
			Element moneyElement=new Element("money");
			moneyElement.addContent(Integer.toString(car.getMoneyForDay()));
			
			carElement.addContent(idElement);
			carElement.addContent(brandElement);
			carElement.addContent(modelElement);
			carElement.addContent(yearElement);
			carElement.addContent(moneyElement);
			
			root.addContent(carElement);
		}
		Document document = new Document(root);
		XMLOutputter out = new XMLOutputter();
		try {
			//String fileName = "jdom-output.xml";
			FileOutputStream fos = new FileOutputStream(fileName);
			out.output(document, fos);
			
			System.out.println("Data is written to "+fileName);
			fos.close();
		} catch (Exception e) {
			System.out.println("Exception while outputting JDOM:");
			e.printStackTrace();
		}
	}
	
	public static void writeOrdersToXml(List<Order> orders, String fileName) {
		Element root = new Element("order");
		for (Order order : orders) {
			Element orderElement=new Element("order");
			Element idOrder=new Element("id");
			idOrder.addContent(Integer.toString(order.getNumber()));
			
			Element clientElement=new Element("client");
			Element clientNameElement=new Element("name");
			clientNameElement.addContent(order.getClient().getName());
			Element clientSurnameElement=new Element("surname");
			clientSurnameElement.addContent(order.getClient().getSurname());
			Element clientPaspNumber=new Element("number_passport");
			clientPaspNumber.addContent(order.getClient().getPaspNumber());
			clientElement.addContent(clientNameElement);
			clientElement.addContent(clientSurnameElement);
			clientElement.addContent(clientPaspNumber);
			
			Element carElement=new Element("car");
			Element idElement=new Element("id");
			idElement.addContent(Integer.toString(order.getCar().getId()));
			Element brandElement=new Element("brand");
			brandElement.addContent(order.getCar().getBrand());
			Element modelElement=new Element("model");
			modelElement.addContent(order.getCar().getModel());
			Element yearElement=new Element("year");
			yearElement.addContent(Integer.toString(order.getCar().getYear()));
			Element moneyElement=new Element("money");
			moneyElement.addContent(Integer.toString(order.getCar().getMoneyForDay()));
			
			carElement.addContent(idElement);
			carElement.addContent(brandElement);
			carElement.addContent(modelElement);
			carElement.addContent(yearElement);
			carElement.addContent(moneyElement);
			
			String dateFormat ="dd.MM.yyyy";
			SimpleDateFormat sdf=new SimpleDateFormat(dateFormat);
			
			Element beginDateElement=new Element("begin_date");
			beginDateElement.addContent(sdf.format(order.getBeginOrder()));
			Element endDateElement=new Element("end_date");
			endDateElement.addContent(sdf.format(order.getEndOrder()));
			
			orderElement.addContent(idOrder);
			orderElement.addContent(clientElement);
			orderElement.addContent(carElement);
			orderElement.addContent(beginDateElement);
			orderElement.addContent(endDateElement);
			
			root.addContent(orderElement);
		}
		Document document = new Document(root);
		XMLOutputter out = new XMLOutputter();
		try {
			//String fileName = "jdom-output.xml";
			FileOutputStream fos = new FileOutputStream(fileName);
			out.output(document, fos);
			
			System.out.println("Data is written to "+fileName);
			fos.close();
		} catch (Exception e) {
			System.out.println("Exception while outputting JDOM:");
			e.printStackTrace();
		}
	}
}
