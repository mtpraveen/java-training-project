/**
 * 
 */
package edu.XML.dom;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import edu.XML.Less.Candy;
import edu.XML.Less.Ingredients;
import edu.XML.Less.TypeCandies;
import edu.XML.Less.ValueCandies;

/**
 * @author Администратор
 * 
 */
public class DOM {
	public static void parse(File xmlFile) {
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			Document document = builder.parse(xmlFile);

			Element root = document.getDocumentElement();
			if (root.hasAttributes()) {
				System.out.println("Attributes:");
				NamedNodeMap attrs = root.getAttributes();
				for (int i = 0; i < attrs.getLength(); i++) {
					System.out.println(attrs.item(i));
				}
			}
			printElements(root);
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

	private static void printElements(Element root) {
		HashMap<ValueCandies, Integer> valueHashMap = null;	
		Candy candy = null;
		boolean isCandy = root.getNodeName().equalsIgnoreCase("candy");
		if (isCandy) 
			candy = new Candy();
		if (root.hasChildNodes()) {
			NodeList candyNodes = root.getChildNodes();
			for (int i = 0; i < candyNodes.getLength(); i++) {
				Node candyNode = candyNodes.item(i);
				String CandyNodeName = candyNode.getNodeName();
				if (candyNode.getNodeType() == Node.ELEMENT_NODE) 
				{
					String text = candyNode.getTextContent();
					if (isCandy) {
					//	System.out.println("Found element:" + CandyNodeName + " "
					//			+ text);
						if (CandyNodeName.equalsIgnoreCase("name"))
							candy.setName(text);
						if (CandyNodeName.equalsIgnoreCase("energy"))
							candy.setEnergy(Integer.parseInt(text));
						if (CandyNodeName.equalsIgnoreCase("production"))
							candy.setProduction(text);
						if (CandyNodeName.equalsIgnoreCase("type")) {
							if (text.equalsIgnoreCase("CARAMEL"))
								candy.setType(TypeCandies.CARAMEL);
							if (text.equalsIgnoreCase("IRIS"))
								candy.setType(TypeCandies.IRIS);
							if (text.equalsIgnoreCase("CHOCOLATE_WHITH_STUFFING"))
								candy.setType(TypeCandies.CHOCOLATE_WHITH_STUFFING);
							if (text.equalsIgnoreCase("CHOCOLATE_WHITHOUT_STUFFING"))
								candy.setType(TypeCandies.CHOCOLATE_WHITHOUT_STUFFING);
						}
						if (CandyNodeName.equalsIgnoreCase("value")) {
												valueHashMap = new HashMap<ValueCandies, Integer>();
							NodeList valueNodes = candyNode.getChildNodes();
							for (int j = 0; j < valueNodes.getLength(); j++) {
								Node valueNode = valueNodes.item(j);
							//	System.out.println("Find element value:"+valueNode.getNodeName());
							String valueNameNode = valueNode.getNodeName();
							text = valueNode.getTextContent();
							if (valueNameNode.equalsIgnoreCase("PROTEINS"))
									valueHashMap.put(ValueCandies.PROTEINS,
											Integer.parseInt(text));
								if (valueNameNode.equalsIgnoreCase("FATS"))
									valueHashMap.put(ValueCandies.FATS,
											Integer.parseInt(text));
								if (valueNameNode.equalsIgnoreCase("CARBOHYDRATES"))
									valueHashMap.put(ValueCandies.CARBOHYDRATES,
											Integer.parseInt(text));
							}
							candy.setValue(valueHashMap);
						}
						if (CandyNodeName.equalsIgnoreCase("ingredients")) {
							Ingredients ingredient = new Ingredients();
							NodeList ingrNodes = candyNode.getChildNodes();
							for (int j = 0; j < ingrNodes.getLength(); j++) {
								Node ingrNode = ingrNodes.item(j);
								String ingrNameNode = ingrNode.getNodeName();
								text = ingrNode.getTextContent();
								if (ingrNameNode.equalsIgnoreCase("water"))
									if (text.equalsIgnoreCase("yes"))
										ingredient.setWater(true);
									else
										ingredient.setWater(false);
								if (ingrNameNode.equalsIgnoreCase("sugar"))
									ingredient.setSugar(Integer.parseInt(text));
								if (ingrNameNode.equalsIgnoreCase("fructose"))
									ingredient.setFructose(Integer.parseInt(text));
								if (ingrNameNode.equalsIgnoreCase("vanillin"))
									ingredient.setVanillin(Integer.parseInt(text));
								if (ingrNameNode.equalsIgnoreCase("chocolateType"))
									ingredient.setChocolateType(text);
							}
							candy.setIngredients(ingredient);
						}
					}
					printElements((Element) candyNode);
				}
			}
		}

		if (isCandy)
		
			System.out.println(candy);
		
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		parse(new File("candy.xml"));
		// TODO Auto-generated method stub

	}

}
