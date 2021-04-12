package es.deusto.bspq21e1.client.controller;

import java.util.ArrayList;
import java.util.Date;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import es.deusto.bspq21e1.client.gui.InitialWindow;
import es.deusto.bspq21e1.serialization.ReservationData;
import es.deusto.bspq21e1.serialization.UserData;
import es.deusto.bspq21e1.serialization.VanData;

public class Controller {

    private Client client;
    private WebTarget webTarget;

    public Controller(String args[]) {
    	client = ClientBuilder.newClient();
    	//TODO comprobar el path
    	webTarget = client.target(String.format("http://%s:%s/airbv", args[0], args[1]));
    	
        new InitialWindow(this);
    }

    // The methods will go here
    
    public ArrayList<VanData> searchVans(String location) {
    	try{
    		ArrayList<VanData> vans =  sl.getAirBVService().searchVans(location);
    		System.out.println("Controller "+vans);
    		return vans;
    	} catch(Exception e){
    		System.out.println("$ Error searching vans: " + e.getMessage());
    	}
    	return null;
    }
    
    public void registerUsers(String dni, String name, String email) {
    	//TODO añadir el path correcto
    	WebTarget registerUserWebTarget = webTarget.path("/registerUser"); 
		Invocation.Builder invocationBuilder = registerUserWebTarget.request(MediaType.APPLICATION_JSON);
		
    	UserData userData = new UserData(dni, name, email);
		Response response = invocationBuilder.post(Entity.entity(userData, MediaType.APPLICATION_JSON));
		if (response.getStatus() != Status.OK.getStatusCode()) {
			System.out.println("Error connecting with the server. Code: " + response.getStatus());
		} else {
			System.out.println("User correctly registered");
		}
    }
    
    public void registerVan(VanData vanData) {
    	//TODO añadir el path correcto
    	WebTarget registerVanWebTarget = webTarget.path("/path"); 
		Invocation.Builder invocationBuilder = registerVanWebTarget.request(MediaType.APPLICATION_JSON);
		
		Response response = invocationBuilder.post(Entity.entity(vanData, MediaType.APPLICATION_JSON));
		if (response.getStatus() != Status.OK.getStatusCode()) {
			System.out.println("Error connecting with the server. Code: " + response.getStatus());
		} else {
			System.out.println("User correctly registered");
		}
    }
    
    public boolean cancelReservation(String code) {
    	try {
			sl.getAirBVService().cancelReservation(code);
			return true;
		} catch (Exception e) {
    		System.out.println("$ Error cancelling reservation: " + e.getMessage());
    		return false;
		}
    }
    
    public void registerReservation(Date bookingDate, int duration, VanData vanData, UserData vanRenter) {
    	//TODO añadir el path correcto
    	WebTarget registerReservationWebTarget = webTarget.path("/path"); 
		Invocation.Builder invocationBuilder = registerReservationWebTarget.request(MediaType.APPLICATION_JSON);
		
    	ReservationData reservationData = new ReservationData(bookingDate, duration, vanData, vanRenter);
		Response response = invocationBuilder.post(Entity.entity(reservationData, MediaType.APPLICATION_JSON));
		if (response.getStatus() != Status.OK.getStatusCode()) {
			System.out.println("Error connecting with the server. Code: " + response.getStatus());
		} else {
			System.out.println("User correctly registered");
		}
    }

	public ArrayList<ReservationData> getMyReservations(UserData user) {
		try {
			return sl.getAirBVService().getUserReservations(user);
		} catch (Exception e) {
    		System.out.println("$ Error registering reservation: " + e.getMessage());
		}
		return null;
	}
	
	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Use: java Client.Client [host] [port]");
			System.exit(0);
		}
		
        new Controller(args);
    }
}