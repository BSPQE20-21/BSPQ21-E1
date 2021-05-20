package es.deusto.bspq21e1.client.gui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.HeadlessException;

import javax.swing.JFrame;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import es.deusto.bspq21e1.client.controller.Controller;
import es.deusto.bspq21e1.serialization.UserData;
import junit.framework.JUnit4TestAdapter;

public class SearchWindowTest {

static Logger logger = Logger.getLogger(SearchWindowTest.class.getName());
	
	Controller controller;
	UserData user;
	JFrame frmMain;
	
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(RegisterVanWindowTest.class);
	}
	
	@Before
	public void setUp() {
		try {
			logger.info("Before tests code execution begins");
			
			controller = new Controller("127.0.0.1", "8080", "");
			user = new UserData("00001111A", "Carlos", "carlos@gmail.com", "admin");
			frmMain = new MainWindow(controller, user, false);
			
			logger.info("Before tests code execution ends properly");
		} catch(HeadlessException e) {
			
		}
	}
	
	@Test
	public void constructorTest() {
		try {
			SearchWindow w = new SearchWindow(controller, user, frmMain, false);
			
			assertEquals(w.getClass(), SearchWindow.class);
			assertTrue(w.getFrmSearchVans().getTitle().length() > 0);
			assertFalse(w.getFrmSearchVans().isResizable());
			assertFalse(w.getFrmSearchVans().isVisible());
			
			logger.info("RemoveVanWindow's constructor tested");
			

			
		} catch(HeadlessException e) {
			System.out.println("You are in ubuntu, it's not posible to do window's test");
		}
	}
	
	
}
