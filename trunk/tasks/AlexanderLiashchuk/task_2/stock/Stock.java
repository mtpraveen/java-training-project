package stock;

import java.util.Set;
import java.util.TreeSet;

import org.omg.CosNaming.NamingContextPackage.NotFound;

import equipment.Battens;
import equipment.Equipment;
import equipment.Hauberk;
import equipment.Helmet;
import equipment.Weapon;

/**
 * Class storing all available for knight equipment
 * 
 * @author Alexander
 * @throws NotFound 
 */
public class Stock {
	private Set<Battens> battens;
	private Set<Hauberk> hauberk;
	private Set<Helmet> helmet;
	private Set<Weapon> weapon;

	public Stock() {
		this.battens = new TreeSet<Battens>();
		this.hauberk = new TreeSet<Hauberk>();
		this.helmet = new TreeSet<Helmet>();
		this.weapon = new TreeSet<Weapon>();
	}

	public Equipment search(Set<? extends Equipment> equipment, double costMin,
			double costMax) throws NotFound {
		for (Equipment o : equipment) {
			if (o.getCost() > costMin && o.getCost() < costMax)
				return o;
		}
		throw new NotFound("Equipment  with such parameters not found", null,
				null);
	}

	public Set<Battens> getBattens() {
		return battens;
	}

	public void setBattens(Set<Battens> battens) {
		this.battens = battens;
	}

	public void addBattens(Battens battens) {
		this.battens.add(battens);
	}

	public Set<Hauberk> getHauberk() {
		return hauberk;
	}

	public void setHauberk(Set<Hauberk> hauberk) {
		this.hauberk = hauberk;
	}

	public void addHauberk(Hauberk hauberk) {
		this.hauberk.add(hauberk);
	}

	public Set<Helmet> getHelmet() {
		return helmet;
	}

	public void setHelmet(Set<Helmet> helmet) {
		this.helmet = helmet;
	}

	public void addHelmet(Helmet helmet) {
		this.helmet.add(helmet);
	}

	public Set<Weapon> getWeapon() {
		return weapon;
	}

	public void setWeapon(Set<Weapon> weapon) {
		this.weapon = weapon;
	}

	public void addWeapon(Weapon weapon) {
		this.weapon.add(weapon);
	}
}
