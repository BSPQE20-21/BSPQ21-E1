package es.deusto.bspq21e1.client.gui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JFrame;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import es.deusto.bspq21e1.client.controller.Controller;
import junit.framework.JUnit4TestAdapter;

public class InitialWindowTest {

	static Logger logger = Logger.getLogger(InitialWindowTest.class.getName());
	
	Controller controller;
	
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(InitialWindowTest.class);
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
			InitialWindow w = new InitialWindow(controller, false);
			
			assertTrue(w.getFrmAirbv().getTitle().length() > 0);
			assertFalse(w.getFrmAirbv().isVisible());
			
			logger.info("InitialWindow's constructor tested");
			
			assertTrue(w.getFrmAirbv().getBounds().getWidth() > 350);
			assertTrue(w.getFrmAirbv().getBounds().getHeight() > 345);
			assertEquals(w.getFrmAirbv().getDefaultCloseOperation(), JFrame.DISPOSE_ON_CLOSE);
			assertNull(w.getFrmAirbv().getContentPane().getLayout());
			assertNotNull(w.getFrmAirbv().getIconImage());
			
			assertTrue(w.getLblHead().getText().length() > 0);
			assertEquals(w.getLblHead().getFont(), new Font("Tahoma", Font.BOLD, 18));
			assertTrue(w.getLblHead().getBounds().getWidth() > 200);
			assertTrue(w.getLblHead().getBounds().getHeight() > 23);
			
			assertTrue(w.getPanel().getBounds().getWidth() > 200);
			assertTrue(w.getPanel().getBounds().getHeight() > 190);
			assertNull(w.getPanel().getLayout());
			
			assertTrue(w.getBtnRegister().getText().length() > 0);
			assertTrue(w.getBtnRegister().getBounds().getWidth() > 145);
			assertTrue(w.getBtnRegister().getBounds().getHeight() > 23);
			assertTrue(w.getBtnRegister().getActionListeners().length > 0);
			assertTrue(w.getBtnRegister().getMouseListeners().length > 0);
			
			assertTrue(w.getBtnLogin().getText().length() > 0);
			assertTrue(w.getBtnLogin().getBounds().getWidth() > 145);
			assertTrue(w.getBtnLogin().getBounds().getHeight() > 23);
			assertTrue(w.getBtnLogin().getActionListeners().length > 0);
			assertTrue(w.getBtnLogin().getMouseListeners().length > 0);
			
			assertTrue(w.getBtnExit().getText().length() > 0);
			assertTrue(w.getBtnExit().getBounds().getWidth() > 145);
			assertTrue(w.getBtnExit().getBounds().getHeight() > 23);
			assertTrue(w.getBtnExit().getActionListeners().length > 0);
			assertTrue(w.getBtnExit().getMouseListeners().length > 0);
			
			assertTrue(w.getLblLogoImg().getBounds().getWidth() > 40);
			assertTrue(w.getLblLogoImg().getBounds().getHeight() > 23);
			assertNotNull(w.getLblLogoImg().getIcon());
			
			assertTrue(w.getLblSpanishImg().getBounds().getWidth() > 42);
			assertTrue(w.getLblSpanishImg().getBounds().getHeight() > 23);
			assertNotNull(w.getLblSpanishImg().getIcon());
			assertTrue(w.getLblSpanishImg().getMouseListeners().length > 0);
			
			assertTrue(w.getLblEnglishImg().getBounds().getWidth() > 42);
			assertTrue(w.getLblEnglishImg().getBounds().getHeight() > 23);
			assertNotNull(w.getLblEnglishImg().getIcon());
			assertTrue(w.getLblEnglishImg().getMouseListeners().length > 0);
			
			assertTrue(w.getLblBasqueImg().getBounds().getWidth() > 42);
			assertTrue(w.getLblBasqueImg().getBounds().getHeight() > 23);
			assertNotNull(w.getLblBasqueImg().getIcon());
			assertTrue(w.getLblBasqueImg().getMouseListeners().length > 0);
			
			assertTrue(w.getLblSpanishButton().getText().length() > 0);
			assertTrue(w.getLblSpanishButton().getBounds().getWidth() > 50);
			assertTrue(w.getLblSpanishButton().getBounds().getHeight() > 12);
			assertTrue(w.getLblSpanishButton().getMouseListeners().length > 0);
			assertEquals(w.getLblSpanishButton().getForeground(), Color.BLUE);
			
			assertTrue(w.getLblEnglishButton().getText().length() > 0);
			assertTrue(w.getLblEnglishButton().getBounds().getWidth() > 50);
			assertTrue(w.getLblEnglishButton().getBounds().getHeight() > 12);
			assertTrue(w.getLblEnglishButton().getMouseListeners().length > 0);
			assertEquals(w.getLblEnglishButton().getForeground(), Color.BLUE);
			
			assertTrue(w.getLblBasqueButton().getText().length() > 0);
			assertTrue(w.getLblBasqueButton().getBounds().getWidth() > 50);
			assertTrue(w.getLblBasqueButton().getBounds().getHeight() > 12);
			assertTrue(w.getLblBasqueButton().getMouseListeners().length > 0);
			assertEquals(w.getLblBasqueButton().getForeground(), Color.BLUE);
			
			logger.info("Initialize method tested");
		} catch(HeadlessException e) {
			System.out.println("You are in ubuntu, it's not posible to do window's test");
		}
	}
}
