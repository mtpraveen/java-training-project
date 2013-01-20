/**
 * 
 */
package com.travel.web.jwebunit;

import static net.sourceforge.jwebunit.junit.JWebUnit.*;

import javax.swing.text.StyledEditorKit.ItalicAction;

import net.sourceforge.jwebunit.api.IElement;
import net.sourceforge.jwebunit.html.Table;

import org.junit.Before;
import org.junit.Test;
/**
 * @author dima
 *
 */
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
        beginAt("/language-en");
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
        submit();
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
