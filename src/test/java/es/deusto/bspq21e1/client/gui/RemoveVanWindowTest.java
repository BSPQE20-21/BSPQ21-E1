package es.deusto.bspq21e1.client.gui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.awt.Font;
import java.awt.HeadlessException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.ListSelectionModel;

import org.apache.log4j.Logger;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.junit.Before;
import org.junit.Test;

import es.deusto.bspq21e1.client.controller.Controller;
import es.deusto.bspq21e1.serialization.UserData;
import es.deusto.bspq21e1.serialization.VanData;
import junit.framework.JUnit4TestAdapter;

public class RemoveVanWindowTest {

	static Logger logger = Logger.getLogger(RemoveVanWindowTest.class.getName());
	
	Controller controller;
	UserData user;
	JFrame frmMain;
	
	RemoveVanWindow w;
	
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(RemoveVanWindowTest.class);
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
	@PerfTest(invocations = 5, threads = 10)
	@Required(max = 1200, average = 250)
	public void constructorTest() {
		try {
			w = new RemoveVanWindow(controller, user, frmMain, false);
			
			assertEquals(w.getClass(), RemoveVanWindow.class);
			assertTrue(w.getFrmRemoveVan().getTitle().length() > 0);
			assertFalse(w.getFrmRemoveVan().isResizable());
			assertFalse(w.getFrmRemoveVan().isVisible());
			
			logger.info("RemoveVanWindow's constructor tested");
			
			assertTrue(w.getFrmRemoveVan().getBounds().getWidth() > 710);
			assertTrue(w.getFrmRemoveVan().getBounds().getHeight() > 490);
			assertEquals(w.getFrmRemoveVan().getDefaultCloseOperation(), JFrame.DISPOSE_ON_CLOSE);
			assertNull(w.getFrmRemoveVan().getContentPane().getLayout());
			assertNotNull(w.getFrmRemoveVan().getIconImage());
			
			assertNotNull(w.getJtVansTable().getModel());
			assertEquals(w.getJtVansTable().getSelectionModel().getSelectionMode(), ListSelectionModel.SINGLE_SELECTION);
			assertFalse(w.getJtVansTable().getTableHeader().getResizingAllowed());
			assertFalse(w.getJtVansTable().getTableHeader().getReorderingAllowed());
			
			assertTrue(w.getScrollVans().getBounds().getWidth() > 625);
			assertTrue(w.getScrollVans().getBounds().getHeight() > 315);
			
			assertTrue(w.getBtnBack().getText().length() > 0);
			assertTrue(w.getBtnBack().getBounds().getWidth() > 95);
			assertTrue(w.getBtnBack().getBounds().getHeight() > 23);
			assertTrue(w.getBtnBack().getActionListeners().length > 0);
			assertTrue(w.getBtnBack().getMouseListeners().length > 0);
			
			assertTrue(w.getBtnRemove().getText().length() > 0);
			assertTrue(w.getBtnRemove().getBounds().getWidth() > 95);
			assertTrue(w.getBtnRemove().getBounds().getHeight() > 23);
			assertTrue(w.getBtnRemove().getActionListeners().length > 0);
			assertTrue(w.getBtnRemove().getMouseListeners().length > 0);
			
			assertTrue(w.getLblText().getText().length() > 0);
			assertEquals(w.getLblText().getFont(), new Font("Tahoma", Font.BOLD, 18));
			assertTrue(w.getLblText().getBounds().getWidth() > 575);
			assertTrue(w.getLblText().getBounds().getHeight() > 45);
			
			logger.info("Initialize method tested");
			
		} catch(HeadlessException e) {
			logger.error("You are in ubuntu, it's not posible to do window's test");
		}
	}
	
	@Test
	@PerfTest(invocations = 5, threads = 10)
	@Required(max = 1200, average = 250)
	public void updateListTest() {
		try {
			w = new RemoveVanWindow(controller, user, frmMain, false);
			
			VanData van = new VanData("1234LMK", "Furgo", "Fur", "Bilbao", 3, true, false, true, 45, user.getDni());
			ArrayList<VanData> vans = new ArrayList<VanData>();
			vans.add(van);
			w.setVans(vans);
			assertEquals(1, w.getVans().size());
			w.updateList();
			assertEquals(1, w.getTableModel().getRowCount());
			assertTrue(w.getBtnRemove().isEnabled());
			w.setVans(new ArrayList<VanData>());
			logger.info("updateList method tested");
		} catch (HeadlessException e) {
			logger.error("You are in ubuntu, it's not posible to do window's test");
		}
	}
	
}
