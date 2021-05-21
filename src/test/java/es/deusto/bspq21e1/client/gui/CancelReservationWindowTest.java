package es.deusto.bspq21e1.client.gui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.awt.Font;
import java.awt.HeadlessException;
import java.text.ParseException;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.ListSelectionModel;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import es.deusto.bspq21e1.client.controller.Controller;
import es.deusto.bspq21e1.client.gui.MainWindow;
import es.deusto.bspq21e1.serialization.ReservationData;
import es.deusto.bspq21e1.serialization.UserData;
import es.deusto.bspq21e1.serialization.VanData;
import junit.framework.JUnit4TestAdapter;

public class CancelReservationWindowTest {

	static Logger logger = Logger.getLogger(CancelReservationWindowTest.class.getName());
	
	Controller controller;
	UserData user;
	JFrame frmMain;
	
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(CancelReservationWindowTest.class);
	}
	
	@Before
	public void setUp() throws ParseException {
		try {
			logger.info("Before tests code execution begins");
			
			controller = new Controller("127.0.0.1", "8080", "");
			user = new UserData("00001111A", "Carlos", "carlos@gmail.com", "admin");
			frmMain = new MainWindow(controller, user, false);
			
			logger.info("Before tests code execution ends properly");
		} catch(HeadlessException e) {
			logger.error("You are in ubuntu, it's not posible to do window's test");
		}
	}
	
	@Test
	public void constructorTest() {
		try {
			CancelReservationWindow w = new CancelReservationWindow(controller, user, frmMain, false);
			
			assertEquals(w.getClass(), CancelReservationWindow.class);
			assertTrue(w.getFrmCancelReservation().getTitle().length() > 0);
			assertTrue(!(w.getFrmCancelReservation().isResizable()));
			assertFalse(w.getFrmCancelReservation().isVisible());
			
			logger.info("CancelReservationWindow's constructor tested");
			
			assertTrue(w.getFrmCancelReservation().getBounds().getWidth() > 700);
			assertTrue(w.getFrmCancelReservation().getBounds().getHeight() > 300);
			assertEquals(w.getFrmCancelReservation().getDefaultCloseOperation(), JFrame.DISPOSE_ON_CLOSE);
			assertNull(w.getFrmCancelReservation().getContentPane().getLayout());
			assertNotNull(w.getFrmCancelReservation().getIconImage());
			
			assertTrue(w.getLblTitle().getText().length() > 0);
			assertEquals(w.getLblTitle().getFont(), new Font("Tahoma", Font.BOLD, 18));
			assertTrue(w.getLblTitle().getBounds().getWidth() > 275);
			assertTrue(w.getLblTitle().getBounds().getHeight() > 24);
			
			assertFalse(w.getTableModel().isCellEditable(0, 0));
			
			assertTrue(w.getScrollReservations().getBounds().getWidth() > 685);
			assertTrue(w.getScrollReservations().getBounds().getHeight() > 180);
			
			assertTrue(w.getBtnBack().getText().length() > 0);
			assertTrue(w.getBtnBack().getActionListeners().length > 0);
			assertTrue(w.getBtnBack().getMouseListeners().length > 0);
			assertTrue(w.getBtnBack().getBounds().getWidth() > 95);
			assertTrue(w.getBtnBack().getBounds().getHeight() > 20);
			
			assertTrue(w.getBtnCancel().getText().length() > 0);
			assertTrue(w.getBtnCancel().getActionListeners().length > 0);
			assertTrue(w.getBtnCancel().getMouseListeners().length > 0);
			assertTrue(w.getBtnCancel().getBounds().getWidth() > 95);
			assertTrue(w.getBtnCancel().getBounds().getHeight() > 20);
			
			assertTrue(w.getReservations().size() == 0);
			
			assertTrue(w.getJtReservationsTable().getBounds().getWidth() > 685);
			assertFalse(w.getJtReservationsTable().getTableHeader().getResizingAllowed());
			assertFalse(w.getJtReservationsTable().getTableHeader().getReorderingAllowed());
			
			logger.info("Initialize method tested");
			
			assertFalse(w.getBtnCancel().isEnabled());
			assertTrue(w.getTableModel().getRowCount() == 0);
			
			logger.info("Update lists methods tested");
		} catch(HeadlessException e) {
			logger.error("You are in ubuntu, it's not posible to do window's test");
		}
	}
	
	@Test
	public void updateListTest() {
		VanData van = new VanData("1234PML", "Furgo", "Fur", "Bilbao", 5, true, false, true, 65, user.getDni());
		controller.registerUsers(user.getDni(), user.getName(), user.getEmail(), user.getPassword());
		controller.registerVan(van);
		ReservationData r = new ReservationData(new Date(), 8, van.getLicensePlate(), user.getDni());
		controller.registerReservation(new Date(), 8, van, user);
		
		CancelReservationWindow w = new CancelReservationWindow(controller, user, frmMain, false);
		
		controller.eraseUser(user.getDni());
		controller.cancelReservation(null);
	}

}
