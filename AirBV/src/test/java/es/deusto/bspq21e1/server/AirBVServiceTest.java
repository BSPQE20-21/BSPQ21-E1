package es.deusto.bspq21e1.server;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.deusto.bspq21e1.client.controller.Controller;
import es.deusto.bspq21e1.client.controller.ControllerTest;
import es.deusto.bspq21e1.serialization.UserData;
import es.deusto.bspq21e1.serialization.VanData;
import es.deusto.bspq21e1.server.dao.DBManager;
import es.deusto.bspq21e1.server.data.Reservation;
import es.deusto.bspq21e1.server.data.User;
import es.deusto.bspq21e1.server.data.Van;

public class AirBVServiceTest {
	
	static Logger logger = Logger.getLogger(ControllerTest.class.getName());
	
	AirBVService service;
	String userDni;
	String userDni2;
	String userName;
	String userEmail;
	String userEmail2;
	String userPass;
	User u;
	Van van;
	Van van2;
	Date date = null;

	@Before
	public void setUp() throws Exception {
		logger.info("Before tests code execution begins");
		service = new AirBVService();
		
		userDni = "00000001A";
		userDni2 = "00000002A";
		userName = "Rita";
		userEmail = "rita@gmail.com";
		userEmail2 = "rita2@gmail.com";
		userPass = "123";
		u = new User();
		u.setDni(userDni2);
		van = new Van("1234ABC", "Ford", "Focus", "Bilbao", true, true, true, 3, 50, userDni);
		van2 = new Van("1235ABC", "Ford", "Focus", "Bilbao",  true, true, true, 3, 50, userDni2);
		
        try {
			date = new SimpleDateFormat("dd/MM/yyyy").parse("30/09/2021");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
        
        assertTrue(service.registerUser(userDni, userName, userEmail, userPass));
        assertTrue(service.registerUser(userDni2, userName, userEmail2, userPass));
        
        assertTrue(service.registerVan(van));
        assertTrue(service.registerVan(van2));
    	
    	van.setUser("7777");
    	assertFalse(service.registerVan(van));
    	van.setUser("userDni");
    	
    	assertTrue(service.registerReservation(date, 3, van.getLicensePlate(), u.getDni()));
    	assertFalse(service.registerReservation(date, 3, van.getLicensePlate(), new User().getDni()));
    	assertFalse(service.registerReservation(date, 3, "0000CCC", u.getDni()));
    	
    	logger.info("Before tests code execution ends properly");
	}

	@After
	public void tearDown() throws Exception {
		logger.info("After tests code execution begins");
		assertTrue(service.cancelReservation(date.getTime()+van.getLicensePlate()));
    	assertFalse(service.cancelReservation(date.getTime()+"ABC"));
    	
    	assertTrue(service.deleteVan(van2.getLicensePlate()));
    	assertFalse(service.deleteVan("8754ACB"));
    	
    	assertTrue(service.deleteUser("00000001A"));
		assertTrue(service.deleteUser("00000002A"));
		assertEquals(0, service.searchVans("Bilbao").size());
		
		logger.info("After tests code execution begins properly");
	}

	@Test
	public void searchVansTest() {
		assertEquals(2, service.searchVans("Bilbao").size());
		assertEquals(0, service.searchVans("Madrid").size());
		logger.info("Test of searchVans done");
	}
	
	@Test
	public void loginTest() {
		User us = service.login(userEmail2, userPass);
		assertEquals(userDni2, us.getDni());
		
		us = service.login(userEmail, "321");
		assertNull(us);
		logger.info("Test of login done");
	}

	@Test
	public void getUserReservationsTest() {
		assertEquals(1, service.getUserReservations(u.getDni()).size());
		assertEquals(0, service.getUserReservations(new UserData().getDni()).size());
		logger.info("Test of getUserReservations done");
	}
	
	@Test
	public void getUserVans() {
		assertEquals(1, service.getUserVans(userDni).size());
		assertEquals(0, service.getUserVans("9516203").size());
		logger.info("Test of getUserVans done");
	}

}
