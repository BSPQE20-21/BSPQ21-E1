package es.deusto.bspq21e1.client.gui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.awt.Font;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.ListSelectionModel;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import es.deusto.bspq21e1.client.controller.Controller;
import es.deusto.bspq21e1.client.gui.MainWindow;
import es.deusto.bspq21e1.serialization.UserData;
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
		logger.info("Before tests code execution begins");
		
		controller = new Controller("127.0.0.1", "8080");
		user = new UserData("00001111A", "Carlos", "carlos@gmail.com", "admin");
		frmMain = new MainWindow(controller, user);
		
		logger.info("Before tests code execution ends properly");
	}
	
	@Test
	public void constructorTest() {
		CancelReservationWindow w = new CancelReservationWindow(controller, user, frmMain);
		
		assertEquals(w.getClass(), CancelReservationWindow.class);
		assertTrue(w.getFrmCancelReservation().getTitle().length() > 0);
		assertTrue(!(w.getFrmCancelReservation().isResizable()));
		assertTrue(w.getFrmCancelReservation().isVisible());
		
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
		
		assertTrue(w.getJtReservationsTable().getBounds().getWidth() > 685);
		assertTrue(w.getJtReservationsTable().getBounds().getHeight() > 160);
		assertEquals(w.getJtReservationsTable().getSelectionModel(), ListSelectionModel.SINGLE_SELECTION);
		assertFalse(w.getJtReservationsTable().getTableHeader().getResizingAllowed());
		assertFalse(w.getJtReservationsTable().getTableHeader().getReorderingAllowed());
		
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
		
		logger.info("Initialize method tested");
		
		assertFalse(w.getBtnCancel().isEnabled());
		assertTrue(w.getTableModel().getRowCount() == 0);
		
		logger.info("Update lists methods tested");
	}

}