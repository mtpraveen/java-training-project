package knight;

import org.omg.CosNaming.NamingContextPackage.NotFound;

import stock.Stock;
import equipment.Battens;
import equipment.Hauberk;
import equipment.Helmet;
import equipment.Weapon;

/**
 * Class descripting knight itself. Knight can carry and buy armor and weapon.
 * 
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
		cost += this.helmet.getCost();
		cost += this.battens.getCost();
		cost += this.hauberk.getCost();
		cost += this.weapon.getCost();
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
	 * automatically. Cost of every thing is determined automatically and based
	 * on constants.
	 * 
	 * @param stock
	 */
	public void selfEquip(Stock stock) {
		try {
			int availableCash = this.getCashAvailable();
			double battensLowerValueBoundary = availableCash * 0.3;
			double battensHigherValueBoundary = availableCash * 0.4;
			double hauberkLowerValueBoundary = availableCash * 0.1;			
			double hauberkHidherValueBoundary = availableCash * 0.2;
			double helmetLowerValueBoundary = availableCash * 0.1;
			double weaponLowerValueBoundary = availableCash * 0.4;
			double weaponHigherValueBoundary = availableCash * 0.5;
			this.setBattens((Battens) stock.search(stock.getBattens(),
					battensLowerValueBoundary,
					battensHigherValueBoundary));
			this.setHauberk((Hauberk) stock.search(stock.getHauberk(),
					hauberkLowerValueBoundary,
					hauberkHidherValueBoundary));
			this.setHelmet((Helmet) stock.search(stock.getHelmet(), 0,
					helmetLowerValueBoundary));
			this.setWeapon((Weapon) stock.search(stock.getWeapon(),
					weaponLowerValueBoundary,
					weaponHigherValueBoundary));
		} catch (NotFound e) {
			e.printStackTrace();
		}
	}
}
