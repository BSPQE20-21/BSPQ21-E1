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
import es.deusto.bspq21e1.serialization.UserData;
import junit.framework.JUnit4TestAdapter;

public class SearchWindowTest {

static Logger logger = Logger.getLogger(SearchWindowTest.class.getName());
	
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
			SearchWindow w = new SearchWindow(controller, user, frmMain, false);
			
			assertEquals(w.getClass(), SearchWindow.class);
			assertTrue(w.getFrmSearchVans().getTitle().length() > 0);
			assertEquals(w.getFrmSearchVans().getTitle(), controller.getResourcebundle().getString("search_window_tittle_msg"));
			assertFalse(w.getFrmSearchVans().isResizable());
			assertFalse(w.getFrmSearchVans().isVisible());
			
			logger.info("SearchnWindow's constructor tested");
			
			assertTrue(w.getFrmSearchVans().getBounds().getWidth() > 665);
			assertTrue(w.getFrmSearchVans().getBounds().getWidth() < 670);
			assertTrue(w.getFrmSearchVans().getBounds().getHeight() > 395);
			assertTrue(w.getFrmSearchVans().getBounds().getHeight() < 405);
			assertEquals(w.getFrmSearchVans().getDefaultCloseOperation(), JFrame.EXIT_ON_CLOSE);
			assertNull(w.getFrmSearchVans().getContentPane().getLayout());
			assertNotNull(w.getFrmSearchVans().getIconImage());

			assertTrue(w.getSearchPanel().getWidth() > 660);
			assertTrue(w.getSearchPanel().getHeight() > 162);
			
			assertTrue(w.getLblLocation().getText().length() > 0);
			assertTrue(w.getLblLocation().getBounds().getWidth() > 95);
			assertTrue(w.getLblLocation().getBounds().getHeight() > 13);
			
			assertTrue(w.getLblPickUp().getText().length() > 0);
			assertTrue(w.getLblPickUp().getBounds().getWidth() > 123);
			assertTrue(w.getLblPickUp().getBounds().getHeight() > 13);
			
			assertTrue(w.getLblReturn().getText().length() > 0);
			assertTrue(w.getLblReturn().getBounds().getWidth() > 130);
			assertTrue(w.getLblReturn().getBounds().getHeight() > 13);
			
			assertFalse(w.getTxtLocation().getText().length() > 0);
			assertTrue(w.getTxtLocation().getBounds().getWidth() > 138);
			assertTrue(w.getTxtLocation().getBounds().getHeight() > 19);
			assertEquals(w.getTxtLocation().getColumns(), 10);
			
			assertFalse(w.getTxtPickUp().getText().length() > 0);
			assertTrue(w.getTxtPickUp().getBounds().getWidth() > 130);
			assertTrue(w.getTxtPickUp().getBounds().getHeight() > 19);
			assertEquals(w.getTxtPickUp().getColumns(), 10);
			
			assertFalse(w.getTxtReturn().getText().length() > 0);
			assertTrue(w.getTxtReturn().getBounds().getWidth() > 130);
			assertTrue(w.getTxtReturn().getBounds().getHeight() > 19);
			assertEquals(w.getTxtReturn().getColumns(), 10);
			
			assertTrue(w.getBtnSearch().getText().length() > 0);
			assertEquals(w.getBtnSearch().getText(), controller.getResourcebundle().getString("search_button_msg"));
			assertEquals(w.getBtnSearch().getFont(), new Font("Tahoma", Font.ITALIC, 11));
			assertTrue(w.getBtnSearch().getActionListeners().length > 0);
			assertTrue(w.getBtnSearch().getMouseListeners().length > 0);
			assertTrue(w.getBtnSearch().getBounds().getWidth() > 85);
			assertTrue(w.getBtnSearch().getBounds().getHeight() > 19);
			
			assertTrue(w.getLblSearchTitle().getText().length() > 0);
			assertEquals(w.getLblSearchTitle().getText(), controller.getResourcebundle().getString("lbl_search_title_msg"));
			assertTrue(w.getLblSearchTitle().getBounds().getWidth() > 315);
			assertTrue(w.getLblSearchTitle().getBounds().getWidth() < 325);
			assertTrue(w.getLblSearchTitle().getBounds().getHeight() > 33);
			assertTrue(w.getLblSearchTitle().getBounds().getHeight() < 36);
			
			assertTrue(w.getLblNDateExample().getText().length() > 0);
			assertEquals(w.getLblNDateExample().getForeground(), Color.BLUE);
			assertTrue(w.getLblNDateExample().getBounds().getWidth() > 90);
			assertTrue(w.getLblNDateExample().getBounds().getHeight() > 12);
			
			assertTrue(w.getVisualizePanel().getWidth() > 660);
			assertTrue(w.getVisualizePanel().getHeight() > 197);
			
			assertTrue(w.getLblResultsTitle().getText().length() > 0);
			assertEquals(w.getLblResultsTitle().getText(), controller.getResourcebundle().getString("lbl_results_title_msg"));
			assertEquals(w.getLblResultsTitle().getFont(), new Font("Tahoma", Font.BOLD, 15));
			assertTrue(w.getLblResultsTitle().getBounds().getWidth() > 146);
			assertTrue(w.getLblResultsTitle().getBounds().getWidth() < 150);
			assertTrue(w.getLblResultsTitle().getBounds().getHeight() > 28);
			assertTrue(w.getLblResultsTitle().getBounds().getHeight() < 30);
			
			assertFalse(w.getTableModel().isCellEditable(0, 0));
			assertEquals(w.getTableModel().getColumnCount(), 5);
			assertTrue(w.getTableModel().getRowCount() == 0);
			
			assertTrue(w.getJtVansTable().getBounds().getWidth() > -559);
			assertFalse(w.getJtVansTable().getTableHeader().getResizingAllowed());
			assertFalse(w.getJtVansTable().getTableHeader().getReorderingAllowed());
			
			assertTrue(w.getScrollVans().getBounds().getWidth() > 630);
			assertTrue(w.getScrollVans().getBounds().getHeight() > 82);
			
			assertTrue(w.getBtnBook().getText().length() > 0);
			assertEquals(w.getBtnBook().getText(), controller.getResourcebundle().getString("book_button_msg"));
			assertEquals(w.getBtnBook().getFont(), new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
			assertTrue(w.getBtnBook().getActionListeners().length > 0);
			assertTrue(w.getBtnBook().getMouseListeners().length > 0);
			assertTrue(w.getBtnBook().getBounds().getWidth() > 98);
			assertTrue(w.getBtnBook().getBounds().getHeight() > 29);
			
			assertTrue(w.getBtnCharacteristics().getText().length() > 0);
			assertEquals(w.getBtnCharacteristics().getText(), controller.getResourcebundle().getString("characteristics_button_msg"));
			assertEquals(w.getBtnCharacteristics().getFont(), new Font("Tahoma", Font.ITALIC, 11));
			assertTrue(w.getBtnCharacteristics().getActionListeners().length > 0);
			assertTrue(w.getBtnCharacteristics().getMouseListeners().length > 0);
			assertTrue(w.getBtnCharacteristics().getBounds().getWidth() > 128);
			assertTrue(w.getBtnCharacteristics().getBounds().getHeight() > 19);
			
			assertTrue(w.getBtnBackButton().getText().length() > 0);
			assertEquals(w.getBtnBackButton().getText(), controller.getResourcebundle().getString("back_button_msg"));
			assertEquals(w.getBtnBackButton().getFont(), new Font("Tahoma", Font.ITALIC, 11));
			assertTrue(w.getBtnBackButton().getActionListeners().length > 0);
			assertTrue(w.getBtnBackButton().getMouseListeners().length > 0);
			assertTrue(w.getBtnBackButton().getBounds().getWidth() > 79);
			assertTrue(w.getBtnBackButton().getBounds().getHeight() > 19);
			
			assertTrue(w.getSeparator().getBounds().getWidth() > 660);
			assertTrue(w.getSeparator().getBounds().getHeight() > 159);
			
			assertFalse(w.getBtnBook().isEnabled());
			assertFalse(w.getBtnCharacteristics().isEnabled());
			
			
		} catch(HeadlessException e) {
			System.out.println("You are in ubuntu, it's not posible to do window's test");
		}
	}
	
	
}
