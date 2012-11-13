package runner;

import knight.Knight;
import stock.Stock;
import equipment.Battens;
import equipment.Hauberk;
import equipment.Helmet;
import equipment.Weapon;

public class Runner {
	public static void main(String[] args) {
		Knight knight1 = new Knight();
		knight1.setHelmet(new Helmet(200, 30, 1.2, "Leather", 62));
		knight1.setHauberk(new Hauberk(500, 50, 3.0, "Steel", 100, 10));
		knight1.setBattens(new Battens(1200, 80, 12.0, "Steel", 100, 80, 80));
		knight1.setWeapon(new Weapon(1500, 80, 1.2, "Damask Steel",
				"Raven Sword"));
		System.out.println(knight1.getCost());
		Stock stock = new Stock();
		stock.addBattens(new Battens(1200, 80, 12.0, "Steel", 100, 80, 80));
		stock.addHauberk(new Hauberk(500, 50, 3.0, "Steel", 100, 10));
		stock.addHelmet(new Helmet(200, 30, 1.2, "Leather", 62));
		stock.addWeapon(new Weapon(1500, 80, 1.2, "Damask Steel", "Raven Sword"));
		Knight knight2 = new Knight();
		knight2.setCashAvailable(4000);
		knight2.selfEquip(stock);

		System.out.println(knight2.getCost());
	}
}
