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

public class RemoveAccountWindowTest {

	static Logger logger = Logger.getLogger(RemoveAccountWindowTest.class.getName());
	
	Controller controller;
	UserData user;
	JFrame frmMain;
	
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(RemoveAccountWindowTest.class);
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
			RemoveAccountWindow w = new RemoveAccountWindow(controller, user, frmMain, false);
			
			assertEquals(w.getClass(), RemoveAccountWindow.class);
			assertTrue(w.getFrmRemoveAccount().getTitle().length() > 0);
			assertEquals(w.getFrmRemoveAccount().getTitle(), controller.getResourcebundle().getString("remove_user_btn_msg"));
			assertFalse(w.getFrmRemoveAccount().isResizable());
			assertFalse(w.getFrmRemoveAccount().isVisible());
			
			logger.info("RemoveAccountWindow's constructor tested");
			
			assertTrue(w.getFrmRemoveAccount().getBounds().getWidth() > 330);
			assertTrue(w.getFrmRemoveAccount().getBounds().getHeight() > 120);
			assertEquals(w.getFrmRemoveAccount().getDefaultCloseOperation(), JFrame.EXIT_ON_CLOSE);
			assertNull(w.getFrmRemoveAccount().getContentPane().getLayout());
			assertNotNull(w.getFrmRemoveAccount().getIconImage());
			
			assertTrue(w.getBtnYes().getText().length() > 0);
			assertTrue(w.getBtnYes().getActionListeners().length > 0);
			assertTrue(w.getBtnYes().getMouseListeners().length > 0);
			assertTrue(w.getBtnYes().getBounds().getWidth() > 95);
			assertTrue(w.getBtnYes().getBounds().getHeight() > 20);
			
			assertTrue(w.getBtnNo().getText().length() > 0);
			assertTrue(w.getBtnNo().getActionListeners().length > 0);
			assertTrue(w.getBtnNo().getMouseListeners().length > 0);
			assertTrue(w.getBtnNo().getBounds().getWidth() > 95);
			assertTrue(w.getBtnNo().getBounds().getHeight() > 20);
			
			assertTrue(w.getLblText().getText().length() > 0);
			assertFalse(w.getLblText().getMouseListeners().length > 0);
			assertTrue(w.getLblText().getBounds().getWidth() > 340);
			assertTrue(w.getLblText().getBounds().getHeight() > 30);
			assertEquals(w.getLblText().getText().length(), controller.getResourcebundle().getString("ensure_remove_msg"));
			
		} catch(HeadlessException e) {
			System.out.println("You are in ubuntu, it's not posible to do window's test");
		}
	}
	
}
