package es.deusto.bspq21e1.client.gui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JFrame;

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
			
			assertTrue(w.getFrmMain().getBounds().getWidth() > 250);
			assertTrue(w.getFrmMain().getBounds().getHeight() > 400);
			assertEquals(w.getFrmMain().getDefaultCloseOperation(), JFrame.DISPOSE_ON_CLOSE);
			assertNull(w.getFrmMain().getContentPane().getLayout());
			assertNotNull(w.getFrmMain().getIconImage());
			
			assertTrue(w.getLblTitle().getText().length() > 0);
			assertTrue(w.getLblTitle().getBounds().getWidth() > 295);
			assertTrue(w.getLblTitle().getBounds().getHeight() > 25);
			assertEquals(w.getLblTitle().getFont(), new Font("Tahoma", Font.BOLD, 18));
			
			assertTrue(w.getBtnSearchVan().getText().length() > 0);
			assertTrue(w.getBtnSearchVan().getBounds().getWidth() > 170);
			assertTrue(w.getBtnSearchVan().getBounds().getHeight() > 22);
			assertTrue(w.getBtnSearchVan().getActionListeners().length > 0);
			assertTrue(w.getBtnSearchVan().getMouseListeners().length > 0);
			
			assertTrue(w.getBtnRegisterVan().getText().length() > 0);
			assertTrue(w.getBtnRegisterVan().getBounds().getWidth() > 170);
			assertTrue(w.getBtnRegisterVan().getBounds().getHeight() > 22);
			assertTrue(w.getBtnRegisterVan().getActionListeners().length > 0);
			assertTrue(w.getBtnRegisterVan().getMouseListeners().length > 0);
			
			assertTrue(w.getBtnCancelRes().getText().length() > 0);
			assertTrue(w.getBtnCancelRes().getBounds().getWidth() > 170);
			assertTrue(w.getBtnCancelRes().getBounds().getHeight() > 22);
			assertTrue(w.getBtnCancelRes().getActionListeners().length > 0);
			assertTrue(w.getBtnCancelRes().getMouseListeners().length > 0);
			
			assertTrue(w.getBtnRemoveVan().getText().length() > 0);
			assertTrue(w.getBtnRemoveVan().getBounds().getWidth() > 170);
			assertTrue(w.getBtnRemoveVan().getBounds().getHeight() > 22);
			assertTrue(w.getBtnRemoveVan().getActionListeners().length > 0);
			assertTrue(w.getBtnRemoveVan().getMouseListeners().length > 0);
			
			assertTrue(w.getBtnRemoveAccount().getText().length() > 0);
			assertTrue(w.getBtnRemoveAccount().getBounds().getWidth() > 170);
			assertTrue(w.getBtnRemoveAccount().getBounds().getHeight() > 22);
			assertTrue(w.getBtnRemoveAccount().getActionListeners().length > 0);
			assertTrue(w.getBtnRemoveAccount().getMouseListeners().length > 0);
			
			assertTrue(w.getBtnlogOut().getText().length() > 0);
			assertTrue(w.getBtnlogOut().getBounds().getWidth() > 170);
			assertTrue(w.getBtnlogOut().getBounds().getHeight() > 22);
			assertTrue(w.getBtnlogOut().getActionListeners().length > 0);
			assertTrue(w.getBtnlogOut().getMouseListeners().length > 0);
			
			logger.info("Initialize method tested");
 			
		} catch(HeadlessException e) {
			logger.error("You are in ubuntu, it's not posible to do window's test");
		}
	}
}
