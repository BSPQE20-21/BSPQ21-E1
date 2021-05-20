package es.deusto.bspq21e1.server.remote;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
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

import es.deusto.bspq21e1.client.controller.Controller;
import es.deusto.bspq21e1.serialization.ReservationData;
import es.deusto.bspq21e1.serialization.UserData;
import es.deusto.bspq21e1.serialization.VanData;
import es.deusto.bspq21e1.server.AirBVService;
import es.deusto.bspq21e1.server.data.Reservation;
import es.deusto.bspq21e1.server.data.User;
import es.deusto.bspq21e1.server.data.Van;
import es.deusto.bspq21e1.server.dto.Assembler;
import es.deusto.bspq21e1.server.remote.AirBV;

public class AirBVTest {

	private Client client;
    private WebTarget webTarget;
    private static Logger logger = Logger.getLogger(Controller.class.getName());
    private ResourceBundle resourceBundle;
    private AirBV airBV;
	
    UserData user;
	String userDni;
	String userName;
	String userEmail;
	String userPass;
	
	VanData van;
	String licensePlate;
	String location;
	
	String resCode;	
	String d1;
	Date date1 = null;
	String d2;
	Date date2 = null;
	String d3;
	String d4;
	int duration;
	ReservationData r;
	
	ArrayList<UserData> users = new ArrayList<UserData>();
	ArrayList<VanData> vans = new ArrayList<VanData>();
	ArrayList<ReservationData> reservations = new ArrayList<ReservationData>(); 
    
	@Before
	public void setUp() throws Exception {
		// Initialization of variables
		client = ClientBuilder.newClient();
    	webTarget = client.target(String.format("http://%s:%s/rest", "127.0.0.1", "8080"));
    	resourceBundle = ResourceBundle.getBundle("SystemMessages", Locale.getDefault());
    	airBV = new AirBV();
    	
    	userDni = "12345689A";
    	userName = "Pepa";
    	userEmail = "pepa@gmail.com";
    	userPass = "123";
    	user = new UserData(userDni, userName, userEmail, userPass);
    	
    	licensePlate = "1234";
    	location = "Bilbao";
    	van = new VanData(licensePlate, "Ford", "Focus", location, 5, true, false, true, 45, userDni);
    	
    	
    	resCode = "pl213";
    	duration = 3;
    	d1 = "30-09-2021";
    	d2 = "11-10-2021";
    	d3 = "18-10-2021";
    	d4 = "19-10-2021";
    	try {
			date1 = new SimpleDateFormat("dd-MM-yyyy").parse(d1);
			date2 = new SimpleDateFormat("dd-MM-yyyy").parse(d2);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
    	
    	r = new ReservationData(date1, duration, licensePlate, userDni);
    	
    	users.add(new UserData("14725836D", "Fede", "fede@gmail.com", "123"));
    	users.add(new UserData("14725836E", "Rober", "rober@gmail.com", "123"));
    	users.add(new UserData("14725836F", "Rita", "rita@gmail.com", "123"));
    	
    	vans.add(new VanData("7894ABC", "Fiat", "555", "Madrid", 5, false, true, false, 32, userDni));
    	vans.add(new VanData("3020ADD", "Furgo", "Fur", "Bilbao", 5, false, true, false, 32, "14725836D"));
    	vans.add(new VanData("8495GTY", "Cara", "van", "Madrid", 5, false, true, false, 32, "14725836E"));
    	
    	reservations.add(new ReservationData(date2, 5, "3020ADD", "14725836F"));
    	reservations.add(new ReservationData(date1, 5, "7894ABC", "14725836F"));
    	reservations.add(new ReservationData(date1, 2, "3020ADD", "14725836F"));
    	
    	// Register user test
    	airBV.registerUser(user);
    	assertEquals(400, airBV.registerUser(user).getStatus());
    	
    	//Register van test
    	airBV.registerVan(van, true, false);
    	assertEquals(400, airBV.registerVan(van, true, false).getStatus());
    	
    	//Register reservation test
    	airBV.registerReservation(r);
    	
    	//Register user list test
    	airBV.registerUsersList(users);
    	assertEquals(400, airBV.registerUsersList(users).getStatus());
    	
    	//Register van list test
    	airBV.registerVansList(vans);
    	assertEquals(400, airBV.registerVansList(vans).getStatus());
    	
    	//Register reservation list test
    	airBV.registerReservationsList(reservations);
    	assertEquals(400, airBV.registerReservationsList(reservations).getStatus());
	}

	@After
	public void tearDown() throws Exception {
		// Delete reservation
		airBV.cancelReservation(resCode);
				
		// Delete van test
		airBV.deleteVan(licensePlate);
		
		// Delete user test
		airBV.deleteUser(userDni);
		airBV.deleteUser("14725836D");
		airBV.deleteUser("14725836E");
		airBV.deleteUser("14725836F");
		
	}

    @Test
	public void searchVans() {	
    	assertEquals(1, airBV.searchVans(location, d2, d2).size());
    	assertEquals(2, airBV.searchVans("Madrid", d2, d2).size());
    	assertEquals(0, airBV.searchVans(location, d1, d2).size());
    	assertEquals(1, airBV.searchVans("Madrid", d1, d2).size());
	}
    
	@Test
    public void loginTest() { 
		assertEquals(Status.OK.getStatusCode(), airBV.login(userEmail, userPass).getStatus());
		assertEquals(Status.OK.getStatusCode(), airBV.login("14725836D", userPass).getStatus());
		assertEquals(Status.OK.getStatusCode(), airBV.login("14725836E", userPass).getStatus());
		assertEquals(Status.OK.getStatusCode(), airBV.login("14725836F", userPass).getStatus());
    }

	@Test
	public void getUserReservations() {
		assertEquals(1, airBV.getUserReservations(userDni).size());
		assertEquals(3, airBV.getUserReservations("14725836F").size());
		assertEquals(0, airBV.getUserReservations("14725836D").size());
		assertEquals(0, airBV.getUserReservations("14725836E").size());
	}

	@Test
	public void getUserVans() {
		assertEquals(2, airBV.getUserVans(userDni).size());
		assertEquals(1, airBV.getUserVans("14725836D").size());
		assertEquals(1, airBV.getUserVans("14725836E").size());
		assertEquals(0, airBV.getUserVans("14725836F").size());
	}

}
