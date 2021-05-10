package es.deusto.bspq21e1.client.controller;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
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

	@Before
	public void setUp() throws Exception {
		logger.info("Before tests code execution begins");
		controller = new Controller("127.0.0.1", "8080");
		
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
		
        try {
			date = new SimpleDateFormat("dd-MM-yyyy").parse("30-09-2021");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
        
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
    	
    	logger.info("Before tests code execution ends properly");
	}
	
	@After
	public void tearDown() {
		logger.info("After tests code execution begins");
		assertTrue(controller.cancelReservation(date.getTime()+van.getLicensePlate()));
    	assertFalse(controller.cancelReservation(date.getTime()+"ABC"));
    	
    	assertTrue(controller.eraseVan(van2.getLicensePlate()));
    	assertFalse(controller.eraseVan("8754ACB"));
    	
    	assertTrue(controller.eraseUser("00000001A"));
		assertTrue(controller.eraseUser("00000002A"));
		assertEquals(0, controller.searchVans("Bilbao", "10-10-2021", "20-10-2021").size());

		logger.info("After tests code execution begins properly");
	}
	
	@Test
	public void loginUserTest() {
    	UserData us = controller.loginUser(userEmail2, userPass);
		assertEquals(userDni2, us.getDni());
		
		us = controller.loginUser(userEmail, "321");
		assertNull(us);
		
		logger.info("Test of loginUser done");
    }
	
	@Test
	public void getMyVansTest() {
		assertEquals(1, controller.getMyVans(userDni).size());
		assertEquals(0, controller.getMyVans("9516203").size());
		logger.info("Test of getMyVans done");
	}
	
	@Test
	public void searchVansTest() {
		assertEquals(2, controller.searchVans("Bilbao", "10-10-2021", "20-10-2021").size());
		assertEquals(0, controller.searchVans("Madrid", "10-10-2021", "20-10-2021").size());
		logger.info("Test of searchVans done");
	}
	
	@Test
	public void getMyReservations() {		
		assertEquals(1, controller.getMyReservations(u).size());
		assertEquals(0, controller.getMyReservations(new UserData()).size());
		logger.info("Test of getMyReservations done");
	}

}
