package epam.course.csv;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import epam.course.domain.Car;
import epam.course.domain.Order;

public class CsvWriting {

	public static void writeCarsToCsv(List<Car> cars, String fileName) {

		File f = new File(fileName);
		try {
			FileWriter fw = new FileWriter(fileName);
			for (Car c : cars) {
				fw.write(Integer.toString(c.getId()));
				fw.write(";");
				fw.write(c.getBrand());
				fw.write(";");
				fw.write(c.getModel());
				fw.write(";");
				fw.write(Integer.toString(c.getYear()));
				fw.write(";");
				fw.write(Integer.toString(c.getMoneyForDay()));
				fw.write("\n");
			}
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void writeOrdersToCsv(List<Order> order, String fileName) {

		File f = new File(fileName);
		try {
			FileWriter fw = new FileWriter(fileName);
			for (Order o : order) {
				fw.write(Integer.toString(o.getNumber()));
				fw.write(";");
				fw.write(o.getClient().getName());
				fw.write(";");
				fw.write(o.getClient().getSurname());
				fw.write(";");
				fw.write(o.getClient().getPaspNumber());
				fw.write(";");
				fw.write(o.getCar().getBrand());
				fw.write(";");
				fw.write(o.getCar().getModel());
				fw.write(";");
				fw.write(Integer.toString(o.getCar().getYear()));
				fw.write(";");
				fw.write(Integer.toString(o.getCar().getMoneyForDay()));
				fw.write(";");
				
				
				String dateFormat ="dd.MM.yyyy";
				SimpleDateFormat sdf=new SimpleDateFormat(dateFormat);
				fw.write(sdf.format(o.getBeginOrder()));
				fw.write(";");
				fw.write(sdf.format(o.getEndOrder()));
				
				fw.write("\n");
			}
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
