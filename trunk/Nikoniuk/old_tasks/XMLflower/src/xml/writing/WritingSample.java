package xml.writing;

import xml.entity.generated.Flowers;

public class WritingSample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Flowers students = ModelGenerator.getFlowers();

		Dom4jWritingSample.writeToXml(students);
		DomWritingSample.writeToXml(ModelGenerator.getFlowerNodes());
		JdomWritingSample.writeToXml(students);
		StaxWritingSample.writeToXml(students);
		XstreamWritingSample.writeToXml(students);
	}

}
