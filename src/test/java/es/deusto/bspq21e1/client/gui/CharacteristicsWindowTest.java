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
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.junit.Before;
import org.junit.Test;

import es.deusto.bspq21e1.client.controller.Controller;
import es.deusto.bspq21e1.client.gui.MainWindow;
import es.deusto.bspq21e1.client.gui.SearchWindow;
import es.deusto.bspq21e1.serialization.UserData;
import es.deusto.bspq21e1.serialization.VanData;
import junit.framework.JUnit4TestAdapter;

public class CharacteristicsWindowTest {

	static Logger logger = Logger.getLogger(CharacteristicsWindowTest.class.getName());
	
	Controller controller;
	VanData van;
	JFrame frmSearch;
	
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(CharacteristicsWindowTest.class);
	}
	
	@Before
	public void setUp() {
		try {
			logger.info("Before tests code execution begins");
			
			controller = new Controller("127.0.0.1", "8080", "");
			van = new VanData("997755KMA", "Ford", "Focus", "Bilbao", 4, true, true, false, 50, "11223344D");
			UserData u = new UserData("11335566A", "Juan", "juan@gmail.com", "1234");
			frmSearch = new SearchWindow(controller, u, new MainWindow(controller, u, false), false);
			
			logger.info("Before tests code execution ends properly");
		} catch (HeadlessException e) {
			logger.error("You are in ubuntu, it's not posible to do window's test");
		}
	}
	
	@Test
	@PerfTest(invocations = 5, threads = 10)
	@Required(max = 1200, average = 250)
	public void constructorTest() {
		try {
			CharacteristicsWindow w = new CharacteristicsWindow(controller, van, frmSearch, false);
			
			assertEquals(w.getClass(), CharacteristicsWindow.class);
			assertTrue(w.getFrmCharacteristics().getTitle().length() > 0);
			assertFalse(w.getFrmCharacteristics().isResizable());
			assertFalse(w.getFrmCharacteristics().isVisible());
			
			logger.info("CharacteristicsWindow's constructor tested");
			
			assertTrue(w.getFrmCharacteristics().getBounds().getWidth() > 280);
			assertTrue(w.getFrmCharacteristics().getBounds().getHeight() > 350);
			assertEquals(w.getFrmCharacteristics().getDefaultCloseOperation(), JFrame.EXIT_ON_CLOSE);
			assertNull(w.getFrmCharacteristics().getContentPane().getLayout());
			assertNotNull(w.getFrmCharacteristics().getIconImage());
			
			assertTrue(w.getLblCharacteristicsTitle().getText().length() > 0);
			assertEquals(w.getLblCharacteristicsTitle().getFont(), new Font("Tahoma", Font.BOLD, 15));
			assertTrue(w.getLblCharacteristicsTitle().getBounds().getWidth() > 290);
			assertTrue(w.getLblCharacteristicsTitle().getBounds().getHeight() > 15);
			
			assertTrue(w.getBtnBack().getText().length() > 0);
			assertTrue(w.getBtnBack().getActionListeners().length > 0);
			assertTrue(w.getBtnBack().getMouseListeners().length > 0);
			assertTrue(w.getBtnBack().getBounds().getWidth() > 90);
			assertTrue(w.getBtnBack().getBounds().getHeight() > 20);
			
			assertTrue(w.getLblLicensePlate().getText().length() > 0);
			assertEquals(w.getLblLicensePlate().getFont(), new Font("Tahoma", Font.BOLD, 11));
			assertTrue(w.getLblLicensePlate().getBounds().getWidth() > 95);
			assertTrue(w.getLblLicensePlate().getBounds().getHeight() > 12);
			
			assertTrue(w.getLblLicensePlateA().getText().length() > 0);
			assertTrue(w.getLblLicensePlateA().getBounds().getWidth() > 95);
			assertTrue(w.getLblLicensePlateA().getBounds().getHeight() > 12);
			
			assertTrue(w.getLblBrand().getText().length() > 0);
			assertEquals(w.getLblBrand().getFont(), new Font("Tahoma", Font.BOLD, 11));
			assertTrue(w.getLblBrand().getBounds().getWidth() > 95);
			assertTrue(w.getLblBrand().getBounds().getHeight() > 12);
			
			assertTrue(w.getLblBrandA().getText().length() > 0);
			assertTrue(w.getLblBrandA().getBounds().getWidth() > 95);
			assertTrue(w.getLblBrandA().getBounds().getHeight() > 12);
			
			assertTrue(w.getLblModel().getText().length() > 0);
			assertEquals(w.getLblModel().getFont(), new Font("Tahoma", Font.BOLD, 11));
			assertTrue(w.getLblModel().getBounds().getWidth() > 95);
			assertTrue(w.getLblModel().getBounds().getHeight() > 12);
			
			assertTrue(w.getLblModelA().getText().length() > 0);
			assertTrue(w.getLblModelA().getBounds().getWidth() > 95);
			assertTrue(w.getLblModelA().getBounds().getHeight() > 12);
			
			assertTrue(w.getLblLocation().getText().length() > 0);
			assertEquals(w.getLblLocation().getFont(), new Font("Tahoma", Font.BOLD, 11));
			assertTrue(w.getLblLocation().getBounds().getWidth() > 95);
			assertTrue(w.getLblLocation().getBounds().getHeight() > 12);
			
			assertTrue(w.getLblLocationA().getText().length() > 0);
			assertTrue(w.getLblLocationA().getBounds().getWidth() > 95);
			assertTrue(w.getLblLocationA().getBounds().getHeight() > 12);
			
			assertTrue(w.getLblCapacity().getText().length() > 0);
			assertEquals(w.getLblCapacity().getFont(), new Font("Tahoma", Font.BOLD, 11));
			assertTrue(w.getLblCapacity().getBounds().getWidth() > 95);
			assertTrue(w.getLblCapacity().getBounds().getHeight() > 12);
			
			assertTrue(w.getLblCapacityA().getText().length() > 0);
			assertTrue(w.getLblCapacityA().getBounds().getWidth() > 85);
			assertTrue(w.getLblCapacityA().getBounds().getHeight() > 12);
			
			assertTrue(w.getLblKitchen().getText().length() > 0);
			assertEquals(w.getLblKitchen().getFont(), new Font("Tahoma", Font.BOLD, 11));
			assertTrue(w.getLblKitchen().getBounds().getWidth() > 95);
			assertTrue(w.getLblKitchen().getBounds().getHeight() > 12);
			
			assertTrue(w.getLblKitchenA().getText().length() > 0);
			assertTrue(w.getLblKitchenA().getBounds().getWidth() > 85);
			assertTrue(w.getLblKitchenA().getBounds().getHeight() > 12);
			
			assertTrue(w.getLblShower().getText().length() > 0);
			assertEquals(w.getLblShower().getFont(), new Font("Tahoma", Font.BOLD, 11));
			assertTrue(w.getLblShower().getBounds().getWidth() > 95);
			assertTrue(w.getLblShower().getBounds().getHeight() > 12);
			
			assertTrue(w.getLblShowerA().getText().length() > 0);
			assertTrue(w.getLblShowerA().getBounds().getWidth() > 85);
			assertTrue(w.getLblShowerA().getBounds().getHeight() > 12);
			
			assertTrue(w.getLblOffRoad().getText().length() > 0);
			assertEquals(w.getLblOffRoad().getFont(), new Font("Tahoma", Font.BOLD, 11));
			assertTrue(w.getLblOffRoad().getBounds().getWidth() > 95);
			assertTrue(w.getLblOffRoad().getBounds().getHeight() > 12);
			
			assertTrue(w.getLblOffRoadA().getText().length() > 0);
			assertTrue(w.getLblOffRoadA().getBounds().getWidth() > 85);
			assertTrue(w.getLblOffRoadA().getBounds().getHeight() > 12);
			
			assertTrue(w.getLblPrice().getText().length() > 0);
			assertEquals(w.getLblPrice().getFont(), new Font("Tahoma", Font.BOLD, 11));
			assertTrue(w.getLblPrice().getBounds().getWidth() > 95);
			assertTrue(w.getLblPrice().getBounds().getHeight() > 12);
			
			assertTrue(w.getLblPriceA().getText().length() > 0);
			assertTrue(w.getLblPriceA().getBounds().getWidth() > 105);
			assertTrue(w.getLblPriceA().getBounds().getHeight() > 12);
			
			logger.info("Initialize method tested");
		} catch(HeadlessException e) {
			logger.error("You are in ubuntu, it's not posible to do window's test");
		}
	}
	
}
