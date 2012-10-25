package task_2_Alexander_Liashchuk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Knight {

	private Hauberk hauberk;
	private Battens battens;
	private Helmet helmet;
	
	public void Sort(String field){
		Map<Integer, String> list[];//=new HashMap<Integer, String>();
		int i=0, j=0, minValue=2147483647;
		String minName = new String("Helmet");
		switch (field) {
			case "Cost":
				list[1].put(this.helmet.getCost(),"Helmet");
				list[2].put(this.hauberk.getCost(), "Hauberk");
				list[3].put(this.battens.getCost(), "Battens");
				for(; i<9; i++) {
					for(; i<9; i++) {
						if(list[j].;						
					}					
				}
	
		}
	}
		
	
	public int getCost() {
		int cost = 0;
		cost+=this.helmet.getCost();
		cost+=this.battens.getCost();
		cost+=this.hauberk.getCost();
		return cost;		
	}

	public Helmet getHelmet() {
		return helmet;
	}

	public void setHelmet(Helmet helmet) {
		this.helmet = helmet;
	}

	public Hauberk getHauberk() {
		return hauberk;
	}

	public void setHauberk(Hauberk hauberk) {
		this.hauberk = hauberk;
	}

	public Battens getBattens() {
		return battens;
	}

	public void setBattens(Battens battens) {
		this.battens = battens;
	}

	
	
	public static void main(String[] args) {
		Knight knight = new Knight();
		knight.helmet = new Helmet(200, 30, 1.2, "Leather", 62);
		knight.hauberk = new Hauberk(600, 50, 3.0, "Steel", 100, 10);
		knight.battens = new Battens(1200, 80, 12.0, "Steel", 100, 80, 80);
		System.out.println(knight.getCost());
		System.out.println("Hi!");
	}
}
