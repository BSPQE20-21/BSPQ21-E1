package es.deusto.bspq21e1.remote;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
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
	
    UserData user;
	String userDni;
	String userName;
	String userEmail;
	String userPass;
	VanData van;
	String licensePlate;
	String resCode;	
	String location;
	Date date1 = null;
	Date date2 = null;
	int duration;
    
	@Before
	public void setUp() throws Exception {
		client = ClientBuilder.newClient();
    	webTarget = client.target(String.format("http://%s:%s/rest", "127.0.0.1", "8080"));
    	resourceBundle = ResourceBundle.getBundle("SystemMessages", Locale.getDefault());
	
    	userDni = "12345689ABC";
    	userName = "Pepa";
    	userEmail = "pepa@gmail.com";
    	userPass = "123";
    	user = new UserData(userDni, userName, userEmail, userPass);
    	
    	licensePlate = "1234";
    	van = new VanData(licensePlate, "Ford", "Focus", location, 5, true, false, true, 45, userDni);
    	
    	resCode = "pl213";
    	location = "Bilbao";
    	duration = 3;
    	try {
			date1 = new SimpleDateFormat("dd-MM-yyyy").parse("30-09-2021");
			date2 = new SimpleDateFormat("dd-MM-yyyy").parse("11-10-2021");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
	}

	@After
	public void tearDown() throws Exception {
		
	}
	
	public void registerUser() {
		WebTarget registerUserWebTarget = webTarget.path("AirBV/registerUser"); 
		Invocation.Builder invocationBuilder = registerUserWebTarget.request(MediaType.APPLICATION_JSON);
		
    	UserData userData = new UserData();
    	userData.setDni(userDni);
    	userData.setName(userName);
    	userData.setEmail(userEmail);
    	userData.setPassword(userPass);
		Response response = invocationBuilder.post(Entity.entity(userData, MediaType.APPLICATION_JSON));

		assertEquals(Status.OK.getStatusCode(), response.getStatus());
    }
    
    public void deleteUser() {
    	
    }

    public void registerVan() {
    	
    }
    
    public void deleteVan() {
    	
    }

    public void cancelReservation() {
    	     
    }
    
    public void registerReservation() {
    	
    	
    }

	public void searchVans() {	
    	
	}
    
    public void login() { 
    	
    }

	public void getUserReservations() {
		
	}

	public void getUserVans() {
		
	}
	
    public void registerUsersList() {
		
    }
    
    public void registerVansList() {
    	
    }
    
    public void registerReservationsList() {
    	
    }

}
