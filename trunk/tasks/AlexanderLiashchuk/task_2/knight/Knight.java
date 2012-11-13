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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((battens == null) ? 0 : battens.hashCode());
		result = prime * result + cashAvailable;
		result = prime * result + ((hauberk == null) ? 0 : hauberk.hashCode());
		result = prime * result + ((helmet == null) ? 0 : helmet.hashCode());
		result = prime * result + ((weapon == null) ? 0 : weapon.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Knight other = (Knight) obj;
		if (battens == null) {
			if (other.battens != null)
				return false;
		} else if (!battens.equals(other.battens))
			return false;
		if (cashAvailable != other.cashAvailable)
			return false;
		if (hauberk == null) {
			if (other.hauberk != null)
				return false;
		} else if (!hauberk.equals(other.hauberk))
			return false;
		if (helmet == null) {
			if (other.helmet != null)
				return false;
		} else if (!helmet.equals(other.helmet))
			return false;
		if (weapon == null) {
			if (other.weapon != null)
				return false;
		} else if (!weapon.equals(other.weapon))
			return false;
		return true;
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
			double battensLowerValueBoundary = availableCash * 0.25;
			double battensHigherValueBoundary = availableCash * 0.35;
			double hauberkLowerValueBoundary = availableCash * 0.05;			
			double hauberkHidherValueBoundary = availableCash * 0.15;
			double helmetHigherValueBoundary = availableCash * 0.1;
			double weaponLowerValueBoundary = availableCash * 0.3;
			double weaponHigherValueBoundary = availableCash * 0.4;
			this.setBattens((Battens) stock.search(stock.getBattens(),
					battensLowerValueBoundary,
					battensHigherValueBoundary));
			this.setHauberk((Hauberk) stock.search(stock.getHauberk(),
					hauberkLowerValueBoundary,
					hauberkHidherValueBoundary));
			this.setHelmet((Helmet) stock.search(stock.getHelmet(), 0,
					helmetHigherValueBoundary));
			this.setWeapon((Weapon) stock.search(stock.getWeapon(),
					weaponLowerValueBoundary,
					weaponHigherValueBoundary));
			this.setCashAvailable((availableCash-this.getCost()));
		} catch (NotFound e) {
			e.printStackTrace();
		}
	}
}
