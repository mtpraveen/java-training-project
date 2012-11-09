package stock;

import java.util.Set;
import java.util.TreeSet;

import org.omg.CosNaming.NamingContextPackage.NotFound;

import weapon.Weapon;
import armor.Battens;
import armor.Hauberk;
import armor.Helmet;

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

	public Battens searchBattens(double costMin, double costMax)
			throws NotFound {
		try {
			for (Battens o : this.battens) {
				if (o.getCost() > costMin && o.getCost() < costMax)
					return o;
			}
			throw new Exception("Battens with such parameters not found");
		} catch (Exception e) {
			e.printStackTrace();
			Battens[] arr = (Battens[]) this.battens.toArray();
			return arr[0];
		}
	}

	public Hauberk searchHauberk(double costMin, double costMax)
			throws NotFound {
		try {
			for (Hauberk o : this.hauberk) {
				if (o.getCost() > costMin && o.getCost() < costMax)
					return o;
			}
			throw new NotFound("Hauberk with such parameters not found", null, null);
		}
		catch(NotFound e) {
			throw e;
		}
		
	}

	public Helmet searchHelmet(double costMin, double costMax) throws NotFound {
		try {
			for (Helmet o : this.helmet) {
				if (o.getCost() > costMin && o.getCost() < costMax)
					return o;
			}
			throw new NotFound("Helmet with such parameters not found", null, null);
		} 
		catch(NotFound e) {
			throw e;
		}
	}

	public Weapon searchWeapon(double costMin, double costMax) throws NotFound {
		try {
			for (Weapon o : this.weapon) {
				if (o.getCost() > costMin && o.getCost() < costMax)
					return o;
			}
			throw new NotFound("Weapon with such parameters not found", null, null);
		} 
		catch(NotFound e) {
			throw e;
		}
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
