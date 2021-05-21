package es.deusto.bspq21e1.client.gui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.awt.HeadlessException;

import javax.swing.JFrame;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import es.deusto.bspq21e1.client.controller.Controller;
import junit.framework.JUnit4TestAdapter;

public class LogInWindowTest {

	static Logger logger = Logger.getLogger(LogInWindowTest.class.getName());
	
	Controller controller;
	JFrame frmLogIn;
	
	LogInWindow w;
	
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(LogInWindowTest.class);
	}
	
	@Before
	public void setUp() {
		logger.info("Before tests code execution begins");
		
		controller = new Controller("127.0.0.1", "8080", "");
		
		logger.info("Before tests code execution ends properly");
	}
	
	@Test
	public void constructorTest() {
		try {
			w = new LogInWindow(controller, false);
			
			assertEquals(w.getClass(), LogInWindow.class);
			assertTrue(w.getFrmLogIn().getTitle().length() > 0);
			assertFalse(w.getFrmLogIn().isVisible());
			
			logger.info("CharacteristicsWindow's constructor tested");
			
			assertFalse(w.getFrmLogIn().isResizable());
			assertTrue(w.getFrmLogIn().getBounds().getWidth() > 440);
			assertTrue(w.getFrmLogIn().getBounds().getHeight() > 240);
			assertEquals(w.getFrmLogIn().getDefaultCloseOperation(), JFrame.DISPOSE_ON_CLOSE);
			assertNull(w.getFrmLogIn().getContentPane().getLayout());
			assertNotNull(w.getFrmLogIn().getIconImage());
			
			assertTrue(w.getLblEmail().getText().length() > 0);
			assertTrue(w.getLblEmail().getBounds().getWidth() > 118);
			assertTrue(w.getLblEmail().getBounds().getHeight() > 17);
			
			assertTrue(w.getTxtFieldEmail().getBounds().getWidth() > 200);
			assertTrue(w.getTxtFieldEmail().getBounds().getHeight() > 20);
			assertTrue(w.getTxtFieldEmail().getColumns() == 10);
			
			assertTrue(w.getLblPassword().getText().length() > 0);
			assertTrue(w.getLblPassword().getBounds().getWidth() > 75);
			assertTrue(w.getLblPassword().getBounds().getHeight() > 10);
			
			assertTrue(w.getPasswordField().getBounds().getWidth() > 200);
			assertTrue(w.getPasswordField().getBounds().getHeight() > 22);
			
			assertTrue(w.getBtnBack().getText().length() > 0);
			assertTrue(w.getBtnBack().getActionListeners().length > 0);
			assertTrue(w.getBtnBack().getMouseListeners().length > 0);
			assertTrue(w.getBtnBack().getBounds().getWidth() > 145);
			assertTrue(w.getBtnBack().getBounds().getHeight() > 22);
			
			assertTrue(w.getBtnLogin().getText().length() > 0);
			assertTrue(w.getBtnLogin().getActionListeners().length > 0);
			assertTrue(w.getBtnLogin().getMouseListeners().length > 0);
			assertTrue(w.getBtnLogin().getBounds().getWidth() > 145);
			assertTrue(w.getBtnLogin().getBounds().getHeight() > 22);
			
			logger.info("Initialize method tested");
		} catch(HeadlessException e) {
			logger.debug("You are in ubuntu, it's not posible to do window's test");
		}
	}
	
}