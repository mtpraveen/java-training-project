/**
 * 
 */
package com.travel.web.jwebunit;

import static net.sourceforge.jwebunit.junit.JWebUnit.assertElementPresent;
import static net.sourceforge.jwebunit.junit.JWebUnit.assertLinkPresent;
import static net.sourceforge.jwebunit.junit.JWebUnit.assertTableRowCountEquals;
import static net.sourceforge.jwebunit.junit.JWebUnit.assertTitleEquals;
import static net.sourceforge.jwebunit.junit.JWebUnit.beginAt;
import static net.sourceforge.jwebunit.junit.JWebUnit.clickLink;
import static net.sourceforge.jwebunit.junit.JWebUnit.getTable;
import static net.sourceforge.jwebunit.junit.JWebUnit.setBaseUrl;
import static net.sourceforge.jwebunit.junit.JWebUnit.setTextField;
import static net.sourceforge.jwebunit.junit.JWebUnit.submit;
import net.sourceforge.jwebunit.html.Table;

import org.junit.Before;
import org.junit.Test;

import com.sun.corba.se.spi.legacy.connection.GetEndPointInfoAgainException;
/**
 * @author dima
 *
 */
//Because this test is very slow it is temporary disabled 
public class TravelWebUnitTest
{


    @Before
    public void prepare() {
        setBaseUrl("http://localhost:8080/travel4");
    }
    @Test
    public void testLogin() {
    	//recreating database
        beginAt("/install");
        //
        beginAt("/language/en");
        beginAt("/index");
        assertTitleEquals("Hyper travel");
        clickLink("login");
        setTextField("login", "admin");
        setTextField("password", "1");
        submit();
        assertLinkPresent("logout");
        //go to discounts
        clickLink("discounts");
        assertElementPresent("discountstable");
        Table table = getTable("discountstable");
        int rowsCount = table.getRowCount();
        clickLink("createNewItemLink");
        //creating new discount
        setTextField("threshold", "7000");
        setTextField("percent","22");
        submit();
        //check that added
        clickLink("discounts");
        table = getTable("discountstable");
        assertTableRowCountEquals("discountstable", rowsCount+1);
    }
}
