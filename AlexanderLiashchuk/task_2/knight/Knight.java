package knight;

import org.omg.CosNaming.NamingContextPackage.NotFound;

import stock.Stock;
import armor.Battens;
import armor.Hauberk;
import armor.Helmet;
import weapon.Weapon;

/**
 * Class descripting knight itself.
 * Knight can carry and buy armor and weapon.
 * @author Alexander
 */

public class Knight {
	private Hauberk hauberk;
	private Battens battens;
	private Helmet helmet;
	private Weapon weapon;
	private int cashAvailable;
	
	public int getCashAvailable() {
		return cashAvailable;
	}

	public void setCashAvailable(int cashAvailable) {
		this.cashAvailable = cashAvailable;
	}

	public int getCost() {
		int cost = 0;
		cost+=this.helmet.getCost();
		cost+=this.battens.getCost();
		cost+=this.hauberk.getCost();
		cost+=this.weapon.getCost();
		return cost;		
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
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
	
	/**
	 * This function allows to equip the instance of the Knight class
	 * automatically. Cost of every thing is 
	 * @param stock
	 */
	public void selfEquip(Stock stock) {
		// Trying to get equipped with battens.
		try {
			this.setBattens(stock.searchBattens((this.cashAvailable*0.10), (this.cashAvailable*0.21)));
		} catch (NotFound e) {
			try {
				this.setBattens(stock.searchBattens((this.cashAvailable*0.07), (this.cashAvailable*0.27)));
			}
			catch(NotFound f) {
				f.printStackTrace();
			}
		}
		this.cashAvailable-=this.battens.getCost();
		
		// Trying to get equipped with hauberk.
		try {
			this.setHauberk(stock.searchHauberk((this.cashAvailable*0.15), (this.cashAvailable*0.33)));
		} catch (NotFound e) {
			try {
				this.setHauberk(stock.searchHauberk((this.cashAvailable*0.1), (this.cashAvailable*0.40)));
			}
			catch(NotFound f) {
				f.printStackTrace();
			}
		}
		this.cashAvailable-=this.battens.getCost();
		
		// Trying to get equipped with helmet.
		try {
			this.setHelmet(stock.searchHelmet((this.cashAvailable*0.15), (this.cashAvailable*0.33)));
		} catch (NotFound e) {
			try {
				this.setHelmet(stock.searchHelmet((this.cashAvailable*0.5), (this.cashAvailable*0.45)));
			}
			catch(NotFound f) {
				f.printStackTrace();
			}
		}
		this.cashAvailable-=this.battens.getCost();
		
		// Trying to get equipped with weapon.
		try {
			this.setWeapon(stock.searchWeapon((this.cashAvailable*0.40), this.cashAvailable));
		} catch (NotFound e) {
			e.printStackTrace();
		}
		this.cashAvailable-=this.battens.getCost();
	}
	
	public static void main(String[] args) {
		Knight knight = new Knight();
		knight.helmet = new Helmet(200, 30, 1.2, "Leather", 62);
		knight.hauberk = new Hauberk(600, 50, 3.0, "Steel", 100, 10);
		knight.battens = new Battens(1200, 80, 12.0, "Steel", 100, 80, 80);
		knight.weapon = new Weapon(1500, 80, 1.2, "Damask Steel", "Raven Sword");
		System.out.println(knight.getCost());
	}
}
