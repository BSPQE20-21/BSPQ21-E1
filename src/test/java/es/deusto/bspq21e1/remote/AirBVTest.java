package es.deusto.bspq21e1.remote;

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
	ArrayList<VanData> vans = new ArrayList<VanData>();
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
    
	@Before
	public void setUp() throws Exception {
		// Initialization of variables
		client = ClientBuilder.newClient();
    	webTarget = client.target(String.format("http://%s:%s/rest", "127.0.0.1", "8080"));
    	resourceBundle = ResourceBundle.getBundle("SystemMessages", Locale.getDefault());
    	airBV = new AirBV();
    	
    	userDni = "12345689ABC";
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
    	
    	// Register user test
    	assertEquals(Status.OK.getStatusCode(), airBV.registerUser(user).getStatus());
    	assertEquals(400, airBV.registerUser(user).getStatus());
    	
    	//Register van test
    	assertEquals(Status.OK.getStatusCode(), airBV.registerVan(van, true, false).getStatus());
    	assertEquals(400, airBV.registerVan(van, true, false).getStatus());
    	
    	//Register reservation test
//    	assertEquals(Status.OK.getStatusCode(), airBV.registerReservation(r).getStatus());
	}

	@After
	public void tearDown() throws Exception {
		// Delete reservation
//		assertEquals(Status.OK.getStatusCode(), airBV.cancelReservation(resCode).getStatus());
				
		// Delete van test
		assertEquals(Status.OK.getStatusCode(), airBV.deleteVan(licensePlate).getStatus());
		assertEquals(400, airBV.deleteVan(licensePlate).getStatus());
		
		// Delete user test
		assertEquals(Status.OK.getStatusCode(), airBV.deleteUser(userDni).getStatus());
		
	}

    @Test
	public void searchVans() {	
    	assertEquals(1, airBV.searchVans(location, d1, d2).size());
    	assertEquals(licensePlate, airBV.searchVans(location, d1, d2).get(0).getLicensePlate());
	}
    
	@Test
    public void loginTest() { 
		assertEquals(Status.OK.getStatusCode(), airBV.login(userEmail, userPass).getStatus());
    }

	public void getUserReservations() {
		
	}

	public void getUserVans() {
		assertEquals(licensePlate, airBV.getUserVans(userDni).get(0).getLicensePlate());
	}
	
    public void registerUsersList() {
		
    }
    
    public void registerVansList() {
    	
    }
    
    public void registerReservationsList() {
    	
    }

}
