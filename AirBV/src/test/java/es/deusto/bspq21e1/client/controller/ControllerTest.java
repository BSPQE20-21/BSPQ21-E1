package es.deusto.bspq21e1.client.controller;

import static org.junit.Assert.*;

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
import org.junit.Before;
import org.junit.Test;

import es.deusto.bspq21e1.serialization.ReservationData;
import es.deusto.bspq21e1.serialization.UserData;
import es.deusto.bspq21e1.serialization.VanData;

public class ControllerTest {
	
	static Logger logger = Logger.getLogger(ControllerTest.class.getName());

	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void serachVansTest() {
		// TODO arrayList<Vandata> (string location)
	}
	
	public void eraseUserTest() {
		// TODO void  (string dni)
	}

    public void eraseVanTest(String licensePlate) {
    	//TODO void (string licensePlate)
    }
    
    public void cancelReservationTest(String code) {
    	// TODO void (string code)
    }
    
    public void registerUsers(String dni, String name, String email, String password) {
    	
    }
    
    public UserData loginUser(String email, String password) {
    	
    }
    
    public void registerVan(VanData vanData) {
    	
    }
    
    public void registerReservation(Date bookingDate, int duration, VanData vanData, UserData vanRenter) {
    	
    }

	public ArrayList<ReservationData> getMyReservations(UserData user) {		
		
	}
	
	public ArrayList<VanData> getMyVans(String dni) {
		
	}

}
