package tests;

import static org.junit.Assert.*;
import knight.Knight;

import org.junit.BeforeClass;
import org.junit.Test;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import stock.Stock;

import equipment.Battens;
import equipment.Hauberk;
import equipment.Helmet;
import equipment.Weapon;

public class KnightTest {
	private static Knight knight_1 = new Knight();
	private static Knight knight_2 = new Knight();
	private static Battens battens = new Battens(1200, 80, 12.0, "Steel", 100, 80, 80);
	private static Hauberk hauberk = new Hauberk(500, 50, 3.0, "Steel", 100, 10);
	private static Helmet helmet = new Helmet(200, 30, 1.2, "Leather", 62);
	private static Weapon weapon = new Weapon(1500, 80, 1.2, "Damask Steel", "Raven Sword");
	private static Stock stock = new Stock();
	
	@BeforeClass
	public static void init() {
		knight_1.setBattens(battens);
		knight_1.setHauberk(hauberk);
		knight_1.setHelmet(helmet);
		knight_1.setWeapon(weapon);
		knight_1.setCashAvailable(600);
		stock.addBattens(battens);
		stock.addHauberk(hauberk);
		stock.addHelmet(helmet);
		stock.addWeapon(weapon);
	}
	
	@Test
	public void testException() {
		try {
			stock.search(stock.getBattens(), 0, 1);
		}
		catch(NotFound e) {
			assertEquals(1, 1);
		}		
	}
	
	@Test
	public void testSelfEquip() {
		knight_2.setCashAvailable(4000);
		knight_2.selfEquip(stock);
		assertEquals(true, knight_1.equals(knight_2));
	}
	
	@Test
	public void testHashCode() {
		assertEquals(true, knight_1.hashCode()==knight_2.hashCode());
	}
	
	@Test
	public void testGetCost() {
		assertEquals(3400, knight_1.getCost());
		assertEquals(3400, knight_2.getCost());
	}

}
