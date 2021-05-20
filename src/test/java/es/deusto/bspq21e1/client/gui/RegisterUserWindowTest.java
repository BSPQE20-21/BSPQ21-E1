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

public class RegisterUserWindowTest {

	static Logger logger = Logger.getLogger(RegisterUserWindowTest.class.getName());
	
	Controller controller;
	JFrame frmLogIn;
	
	RegisterUserWindow w;
	
	@Before
	public void setUp() {
		logger.info("Before tests code execution begins");
		
		controller = new Controller("127.0.0.1", "8080", "");
		
		logger.info("Before tests code execution ends properly");
	}
	
	@Test
	public void constructorTest() {
		try {
			
			w = new RegisterUserWindow(controller, false);
			
			assertEquals(w.getClass(), RegisterUserWindow.class);
			assertTrue(w.getFrmRegisterYourself().getTitle().length() > 0);
			assertFalse(w.getFrmRegisterYourself().isVisible());
			
			logger.info("RegisterUserWindow's constructor tested");
			
			assertFalse(w.getFrmRegisterYourself().isResizable());
			assertTrue(w.getFrmRegisterYourself().getBounds().getWidth() > 375);
			assertTrue(w.getFrmRegisterYourself().getBounds().getHeight() > 310);
			assertEquals(w.getFrmRegisterYourself().getDefaultCloseOperation(), JFrame.DISPOSE_ON_CLOSE);
			assertNull(w.getFrmRegisterYourself().getContentPane().getLayout());
			assertNotNull(w.getFrmRegisterYourself().getIconImage());
			
			assertEquals(w.getLblRegisterTitle().getText(), controller.getResourcebundle().getString("register_as_new_user_msg"));
			assertEquals(w.getLblRegisterTitle().getFont(), new Font("Tahoma", Font.BOLD, 15));
			assertTrue(w.getLblRegisterTitle().getText().length() > 0);
			assertTrue(w.getLblRegisterTitle().getWidth() > 290);
			assertTrue(w.getLblRegisterTitle().getBounds().getHeight() > 17);
			
			assertTrue(w.getLblId().getText().length() > 0);
			assertTrue(w.getLblId().getWidth() > 125);
			assertTrue(w.getLblId().getBounds().getHeight() > 17);
			
			assertTrue(w.getLblName().getText().length() > 0);
			assertTrue(w.getLblName().getWidth() > 290);
			assertTrue(w.getLblName().getBounds().getHeight() > 13);
			
			assertTrue(w.getLblEmail().getText().length() > 0);
			assertTrue(w.getLblEmail().getWidth() > 290);
			assertTrue(w.getLblEmail().getBounds().getHeight() > 13);
			
			assertTrue(w.getLblPassword().getText().length() > 0);
			assertTrue(w.getLblPassword().getWidth() > 290);
			assertTrue(w.getLblPassword().getBounds().getHeight() > 13);
		
			assertTrue(w.getBtnBack().getText().length() > 0);
			assertTrue(w.getBtnBack().getActionListeners().length > 0);
			assertTrue(w.getBtnBack().getMouseListeners().length > 0);
			assertTrue(w.getBtnBack().getBounds().getWidth() > 120);
			assertTrue(w.getBtnBack().getBounds().getHeight() > 22);
			
			assertTrue(w.getBtnRegister().getText().length() > 0);
			assertTrue(w.getBtnRegister().getActionListeners().length > 0);
			assertTrue(w.getBtnRegister().getMouseListeners().length > 0);
			assertTrue(w.getBtnRegister().getBounds().getWidth() > 120);
			assertTrue(w.getBtnRegister().getBounds().getHeight() > 22);
			
			
		} catch(HeadlessException e) {
			logger.debug("You are in ubuntu, it's not posible to do window's test");
		}
	}
}
