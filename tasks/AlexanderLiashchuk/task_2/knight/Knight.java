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
			this.setBattens(stock.searchBattens((this.cashAvailable*0.30), (this.cashAvailable*0.40)));
		} catch (NotFound e) {
			try {
				this.setBattens(stock.searchBattens((this.cashAvailable*0.20), (this.cashAvailable*0.45)));
			}
			catch(NotFound f) {
				f.printStackTrace();
			}
		}
		this.cashAvailable-=this.battens.getCost();
		
		// Trying to get equipped with hauberk.
		try {
			this.setHauberk(stock.searchHauberk((this.cashAvailable*0.20), (this.cashAvailable*0.30)));
		} catch (NotFound e) {
			try {
				this.setHauberk(stock.searchHauberk((this.cashAvailable*0.03), (this.cashAvailable*0.20)));
			}
			catch(NotFound f) {
				f.printStackTrace();
			}
		}
		this.cashAvailable-=this.hauberk.getCost();
		
		// Trying to get equipped with helmet.
		try {
			this.setHelmet(stock.searchHelmet((this.cashAvailable*0.03), (this.cashAvailable*0.10)));
		} catch (NotFound e) {
			try {
				this.setHelmet(stock.searchHelmet((this.cashAvailable*0.01), (this.cashAvailable*0.15)));
			}
			catch(NotFound f) {
				f.printStackTrace();
			}
		}
		this.cashAvailable-=this.helmet.getCost();
		
		// Trying to get equipped with weapon.
		try {
			this.setWeapon(stock.searchWeapon((this.cashAvailable*0.25), this.cashAvailable));
		} catch (NotFound e) {
			e.printStackTrace();
		}
		this.cashAvailable-=this.weapon.getCost();
	}
	
	public static void main(String[] args) {
		Knight knight1 = new Knight();
		knight1.helmet = new Helmet(200, 30, 1.2, "Leather", 62);
		knight1.hauberk = new Hauberk(600, 50, 3.0, "Steel", 100, 10);
		knight1.battens = new Battens(1200, 80, 12.0, "Steel", 100, 80, 80);
		knight1.weapon = new Weapon(1500, 80, 1.2, "Damask Steel", "Raven Sword");
		System.out.println(knight1.getCost());
		Stock stock = new Stock();
		stock.addBattens(new Battens(1200, 80, 12.0, "Steel", 100, 80, 80));
		stock.addHauberk(new Hauberk(600, 50, 3.0, "Steel", 100, 10));
		stock.addHelmet(new Helmet(200, 30, 1.2, "Leather", 62));
		stock.addWeapon(new Weapon(1500, 80, 1.2, "Damask Steel", "Raven Sword"));
		Knight knight2 = new Knight();
		knight2.setCashAvailable(3600);
		knight2.selfEquip(stock);
		System.out.println(knight2.getCost());
	}
}
