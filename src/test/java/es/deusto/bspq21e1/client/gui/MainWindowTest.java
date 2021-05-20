package es.deusto.bspq21e1.client.gui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import java.awt.HeadlessException;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import es.deusto.bspq21e1.client.controller.Controller;
import es.deusto.bspq21e1.serialization.UserData;
import junit.framework.JUnit4TestAdapter;

public class MainWindowTest {

	static Logger logger = Logger.getLogger(MainWindowTest.class.getName());
	
	Controller controller;
	UserData user;
	
	MainWindow w;
	
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(MainWindowTest.class);
	}
	
	@Before
	public void setUp() {
		try {
			logger.info("Before tests code execution begins");
			
			controller = new Controller("127.0.0.1", "8080", "");
			user = new UserData("00001111A", "Carlos", "carlos@gmail.com", "admin");
			
			logger.info("Before tests code execution ends properly");
		} catch (HeadlessException e) {
			logger.error("You are in ubuntu, it's not posible to do window's test");
		}
	}
	
	@Test
	public void constructorTest() {
		try {
			w = new MainWindow(controller, user, false);
			
			assertEquals(w.getClass(), MainWindow.class);
			assertFalse(w.getFrmMain().isResizable());
			assertFalse(w.getFrmMain().isVisible());
			
			logger.info("MainWindow's constructor tested");
			
			
			
		} catch(HeadlessException e) {
			logger.error("You are in ubuntu, it's not posible to do window's test");
		}
	}
}
