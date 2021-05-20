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
import es.deusto.bspq21e1.serialization.VanData;
import junit.framework.JUnit4TestAdapter;

import org.apache.log4j.Logger;

public class RegisterVanWindowTest {

	static Logger logger = Logger.getLogger(RegisterVanWindowTest.class.getName());
	
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
			RegisterVanWindow w = new RegisterVanWindow(controller, user, frmMain, false);
			
			assertEquals(w.getClass(), RegisterVanWindow.class);
			assertTrue(w.getFrmRegistrationOfVans().getTitle().length() > 0);
			assertTrue(!(w.getFrmRegistrationOfVans().isResizable()));
			assertFalse(w.getFrmRegistrationOfVans().isVisible());
			
			logger.info("RegisterVanWindow's constructor tested");
			
			assertTrue(w.getFrmRegistrationOfVans().getBounds().getWidth() > 300);
			assertTrue(w.getFrmRegistrationOfVans().getBounds().getHeight() > 500);
			assertEquals(w.getFrmRegistrationOfVans().getDefaultCloseOperation(), JFrame.DISPOSE_ON_CLOSE);
			assertNull(w.getFrmRegistrationOfVans().getContentPane().getLayout());
			assertNotNull(w.getFrmRegistrationOfVans().getIconImage());
			
			assertTrue(w.getLblTitle().getText().length() > 0);
			assertEquals(w.getLblTitle().getFont(), new Font("Tahoma", Font.BOLD, 15));
			assertTrue(w.getLblTitle().getBounds().getWidth() > 190);
			assertTrue(w.getLblTitle().getBounds().getHeight() > 19);
			
			assertTrue(w.getBtnCancel().getText().length() > 0);
			assertTrue(w.getBtnCancel().getActionListeners().length > 0);
			assertTrue(w.getBtnCancel().getMouseListeners().length > 0);
			assertTrue(w.getBtnCancel().getBounds().getWidth() > 95);
			assertTrue(w.getBtnCancel().getBounds().getHeight() > 20);
			
			assertTrue(w.getBtnRegisterVan().getText().length() > 0);
			assertTrue(w.getBtnRegisterVan().getActionListeners().length > 0);
			assertTrue(w.getBtnRegisterVan().getMouseListeners().length > 0);
			assertTrue(w.getBtnRegisterVan().getBounds().getWidth() > 150);
			assertTrue(w.getBtnRegisterVan().getBounds().getHeight() > 20);
			
			assertTrue(w.getLblLicensePlate().getText().length() > 0);
			assertFalse(w.getLblLicensePlate().getMouseListeners().length > 0);
			assertTrue(w.getLblLicensePlate().getBounds().getWidth() > 120);
			assertTrue(w.getLblLicensePlate().getBounds().getHeight() > 15);
			
			assertTrue(w.getLblBrand().getText().length() > 0);
			assertFalse(w.getLblBrand().getMouseListeners().length > 0);
			assertTrue(w.getLblBrand().getBounds().getWidth() > 120);
			assertTrue(w.getLblBrand().getBounds().getHeight() > 10);

			assertTrue(w.getLblMoldel().getText().length() > 0);
			assertFalse(w.getLblMoldel().getMouseListeners().length > 0);
			assertTrue(w.getLblMoldel().getBounds().getWidth() > 120);
			assertTrue(w.getLblMoldel().getBounds().getHeight() > 10);
			
			assertTrue(w.getLblLocation().getText().length() > 0);
			assertFalse(w.getLblLocation().getMouseListeners().length > 0);
			assertTrue(w.getLblLocation().getBounds().getWidth() > 120);
			assertTrue(w.getLblLocation().getBounds().getHeight() > 10);
			
			assertTrue(w.getLblLocation().getText().length() > 0);
			assertFalse(w.getLblLocation().getMouseListeners().length > 0);
			assertTrue(w.getLblLocation().getBounds().getWidth() > 120);
			assertTrue(w.getLblLocation().getBounds().getHeight() > 10);
			
			assertTrue(w.getLblCapacity().getText().length() > 0);
			assertFalse(w.getLblCapacity().getMouseListeners().length > 0);
			assertTrue(w.getLblCapacity().getBounds().getWidth() > 120);
			assertTrue(w.getLblCapacity().getBounds().getHeight() > 10);
		
			assertTrue(w.getSpinnerCapacity().getBounds().getWidth() > 50);
			assertTrue(w.getSpinnerCapacity().getBounds().getHeight() > 15);
			
			logger.info("Initialize method tested");
			
		} catch(HeadlessException e) {
			logger.error("You are in ubuntu, it's not posible to do window's test");
		}
	}
}

