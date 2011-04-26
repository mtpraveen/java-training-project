package xml.binding;

import java.util.Arrays;
import java.util.List;

import xml.entity.generated.Flowers.Flower;
import xml.entity.generated.Flowers.Flower.GrowingTips;
import xml.entity.generated.Flowers.Flower.VisualParameters;


public class ModelGenerator {
	private static List<Flower> flowers;
	static {
		Flower rose = new Flower();
		rose.setName("rose");
		rose.setMultiplying("seeds");
		rose.setSoil("podzol");
		
		GrowingTips growingTips = new GrowingTips();
		growingTips.setLighting("photophilous");
		growingTips.setTemperature(20);
		growingTips.setWatering(1000);
		
		VisualParameters visualParameters = new VisualParameters();
		visualParameters.setStemColour("green");
		visualParameters.setLeafsColour("green");
		visualParameters.setAveSize(0.5);
		
		rose.setGrowingTips(growingTips);
		rose.setVisualParameters(visualParameters);
			
		flowers = Arrays.asList(rose, rose, rose);
	}

	public static List<Flower> generateFlowers() {
		return flowers;
	}
}
