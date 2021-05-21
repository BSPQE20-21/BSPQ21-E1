package es.deusto.bspq21e1.client.controller;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.deusto.bspq21e1.serialization.ReservationData;
import es.deusto.bspq21e1.serialization.UserData;
import es.deusto.bspq21e1.serialization.VanData;

public class ControllerTest {
	
	static Logger logger = Logger.getLogger(ControllerTest.class.getName());
	
	Controller controller;
	String userDni;
	String userDni2;
	String userName;
	String userEmail;
	String userEmail2;
	String userPass;
	UserData u;
	VanData van;
	VanData van2;
	Date date = null;
	Date date1 = null;
	Date date2 = null;
	
	ArrayList<UserData> users = new ArrayList<UserData>();
	ArrayList<VanData> vans = new ArrayList<VanData>();
	ArrayList<ReservationData> reservations = new ArrayList<ReservationData>(); 

	@Before
	public void setUp() throws Exception {
		logger.info("Before tests code execution begins");
		controller = new Controller("127.0.0.1", "8080", "C:\\Users\\amaia\\Documents\\GitHub\\BSPQ21-E1\\src\\main\\resources\\demoData.csv");
		
		userDni = "00000001A";
		userDni2 = "00000002A";
		userName = "Rita";
		userEmail = "rita@gmail.com";
		userEmail2 = "rita2@gmail.com";
		userPass = "123";
		u = new UserData();
		u.setDni(userDni2);
		van = new VanData("1234ABC", "Ford", "Focus", "Bilbao", 3, true, true, true, 50, userDni);
		van2 = new VanData("1235ABC", "Ford", "Focus", "Bilbao", 3, true, true, true, 50, userDni2);
		
		String d1 = "30-09-2021";
    	String d2 = "11-10-2021";
        try {
			date = new SimpleDateFormat("dd-MM-yyyy").parse("30-09-2021");
			date1 = new SimpleDateFormat("dd-MM-yyyy").parse(d1);
			date2 = new SimpleDateFormat("dd-MM-yyyy").parse(d2);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
        
        users.add(new UserData("14725836D", "Fede", "fede@gmail.com", "123"));
    	users.add(new UserData("14725836E", "Rober", "rober@gmail.com", "123"));
    	users.add(new UserData("14725836F", "Rita", "rita@gmail.com", "123"));
    	
    	vans.add(new VanData("7894ABC", "Fiat", "555", "Madrid", 5, false, true, false, 32, userDni));
    	vans.add(new VanData("3020ADD", "Furgo", "Fur", "Bilbao", 5, false, true, false, 32, "14725836D"));
    	vans.add(new VanData("8495GTY", "Cara", "van", "Madrid", 5, false, true, false, 32, "14725836E"));
    	
    	reservations.add(new ReservationData(date2, 5, "3020ADD", "14725836F"));
    	reservations.add(new ReservationData(date1, 5, "7894ABC", "14725836F"));
    	reservations.add(new ReservationData(date1, 2, "3020ADD", "14725836F"));
        
        assertTrue(controller.registerUsers(userDni, userName, userEmail, userPass));
        assertTrue(controller.registerUsers(userDni2, userName, userEmail2, userPass));
        
        assertTrue(controller.registerVan(van));
        assertTrue(controller.registerVan(van2));
    	
    	van.setUser("7777");
    	assertFalse(controller.registerVan(van));
    	van.setUser("userDni");
    	
    	assertTrue(controller.registerReservation(date, 3, van, u));
    	assertFalse(controller.registerReservation(date, 3, van, new UserData()));
    	assertFalse(controller.registerReservation(date, 3, new VanData(), u));
    	
    	controller.registerUsersList(users);
    	controller.registerVansList(vans);
    	controller.registerReservationsList(reservations);
    	
    	controller.initializeDemoData();
    	controller.eraseUser("11111111A");
		controller.eraseUser("22222222B");
		controller.eraseUser("33333333C");
		controller.eraseUser("44444444D");
		controller.eraseUser("55555555E");
    	
    	logger.info("Before tests code execution ends properly");
	}
	
	@After
	public void tearDown() {
		logger.info("After tests code execution begins");
		assertTrue(controller.cancelReservation(date.getTime()+van.getLicensePlate()));
    	assertFalse(controller.cancelReservation(date.getTime()+"ABC"));
    	
    	assertTrue(controller.eraseVan(van2.getLicensePlate()));
    	assertFalse(controller.eraseVan("8754ACB"));
    	
    	assertTrue(controller.eraseUser(userDni));
		assertTrue(controller.eraseUser(userDni2));
		assertEquals(1, controller.searchVans("Bilbao", "10-10-2021", "20-10-2021").size());
		
		controller.eraseUser("14725836D");
		controller.eraseUser("14725836E");
		controller.eraseUser("14725836F");

		logger.info("After tests code execution begins properly");
	}
	
	@Test
	@PerfTest(invocations = 5, threads = 10)
	@Required(max = 1200, average = 250)
	public void loginUserTest() {
    	UserData us = controller.loginUser(userEmail2, userPass);
		assertEquals(userDni2, us.getDni());
		
		us = controller.loginUser(userEmail, "321");
		assertNull(us);
		
		logger.info("Test of loginUser done");
    }
	
	@Test
	@PerfTest(invocations = 5, threads = 10)
	@Required(max = 1200, average = 250)
	public void getMyVansTest() {
		assertEquals(2, controller.getMyVans(userDni).size());
		assertEquals(0, controller.getMyVans("9516203").size());
		logger.info("Test of getMyVans done");
	}
	
	@Test
	@PerfTest(invocations = 5, threads = 10)
	@Required(max = 1200, average = 250)
	public void searchVansTest() {
		assertEquals(1, controller.searchVans("Bilbao", "30-09-2021", "20-10-2021").size());
		assertEquals(0, controller.searchVans("Leon", "10-10-2021", "20-10-2021").size());
		logger.info("Test of searchVans done");
	}
	
	@Test
	@PerfTest(invocations = 5, threads = 10)
	@Required(max = 1200, average = 250)
	public void getMyReservations() {		
		assertEquals(1, controller.getMyReservations(u).size());
		assertEquals(0, controller.getMyReservations(new UserData()).size());
		logger.info("Test of getMyReservations done");
	}
	
	@Test
	@PerfTest(invocations = 5, threads = 10)
	@Required(max = 1200, average = 250)
	public void setLocaleTest() {		
		controller.setLocale("es");
		assertEquals(ResourceBundle.getBundle("SystemMessages", new Locale("es")).getBaseBundleName(), controller.getResourcebundle().getBaseBundleName());
		controller.setLocale("eu");
		assertEquals(ResourceBundle.getBundle("SystemMessages", new Locale("eu")).getBaseBundleName(), controller.getResourcebundle().getBaseBundleName());
		controller.setLocale("en");
		assertEquals(ResourceBundle.getBundle("SystemMessages", new Locale("en")).getBaseBundleName(), controller.getResourcebundle().getBaseBundleName());
	}

}
