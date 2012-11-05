package com.epam.tests;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.incomes.IncomeFromWork;
import com.epam.incomes.NetIncome;
import com.epam.incomes.TaxableIncome;
import com.epam.taxes.TaxesHandler;

public class testsForTaxesProcessing {
	private static IncomeFromWork mainWork;
	private static IncomeFromWork additionalWork;
	private static TaxableIncome royalty;
	private static TaxableIncome dispositionOfProperty;
	private static NetIncome moneyTransferFromLondon;
	private static NetIncome allowanceForChildren;
	private static NetIncome materialAid;
	private static TaxesHandler taxesHandler; 
	
	@Before
	public void setUpBeforeClass() throws Exception {
		mainWork = new IncomeFromWork(1000, 12);
		additionalWork = new IncomeFromWork(400, 9);
		royalty = new TaxableIncome(2000, 3);
		dispositionOfProperty = new TaxableIncome(4000, 2);
		moneyTransferFromLondon = new NetIncome(400);
		allowanceForChildren = new NetIncome(200);
		materialAid = new NetIncome(150);
		taxesHandler = new TaxesHandler();
	}

	@Test
	public void testOfObjectsDeclaration() {
		Assert.assertEquals(1000 * 12 * 0.88, mainWork.getIncome());
		Assert.assertEquals(400*12*0.09, additionalWork.getTaxPayments());
		Assert.assertEquals(2000.0, royalty.getProfit());
		Assert.assertEquals(2.0, dispositionOfProperty.getTax());
		Assert.assertEquals(400.0, moneyTransferFromLondon.getIncome());
		Assert.assertEquals(200.0, allowanceForChildren.getProfit());
		materialAid.setProfit(300);
		Assert.assertEquals(300.0, materialAid.getIncome());
	}
	
	@Test
	public void testOfTaxesProcessing() {
		taxesHandler.addTaxe(mainWork.getTaxPayments());
		taxesHandler.addTaxe(additionalWork.getTaxPayments());
		taxesHandler.addTaxe(royalty.getTaxPayments());
		taxesHandler.addTaxe(dispositionOfProperty.getTaxPayments());
		Assert.assertEquals(1000 * 12 * 0.12, taxesHandler.getTaxe(0));
		Assert.assertEquals(400 * 12 * 0.09, taxesHandler.getTaxe(1));
		Assert.assertEquals(2000 * 0.03, taxesHandler.getTaxe(2));
		Assert.assertEquals(4000 * 0.02, taxesHandler.getTaxe(3));
	}

}
