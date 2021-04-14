package es.deusto.bspq21e1.client.controller;

import java.util.ArrayList;
import java.util.Date;

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

import es.deusto.bspq21e1.client.gui.InitialWindow;
import es.deusto.bspq21e1.serialization.ReservationData;
import es.deusto.bspq21e1.serialization.UserData;
import es.deusto.bspq21e1.serialization.VanData;

public class Controller {

    private Client client;
    private WebTarget webTarget;
    private static Logger logger = Logger.getLogger(Controller.class.getName());
    
    public Controller(String hostname, String port) {
    	client = ClientBuilder.newClient();
    	webTarget = client.target(String.format("http://%s:%s/rest", hostname, port));
    }
    
    public ArrayList<VanData> searchVans(String location) {
    	WebTarget vansWebTarget = webTarget.path("AirBV/getVans/" + location);
    	
    	GenericType<ArrayList<VanData>> genericType = new GenericType<ArrayList<VanData>>() {};
		ArrayList<VanData> list = vansWebTarget.request(MediaType.APPLICATION_JSON).get(genericType);
		
		Invocation.Builder invocationBuilder =  vansWebTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		if(response.getStatus() == Status.OK.getStatusCode()) {
			return list;
		}else {
			System.out.println("Error connecting with the server. Code: " + response.getStatus());
			System.out.println("Error: " + response.toString());
		}
		return new ArrayList<VanData>();
    }
    
    public void registerUsers(String dni, String name, String email) {
    	WebTarget registerUserWebTarget = webTarget.path("AirBV/registerUser"); 
		Invocation.Builder invocationBuilder = registerUserWebTarget.request(MediaType.APPLICATION_JSON);
		
    	UserData userData = new UserData();
    	userData.setDni(dni);
    	userData.setName(name);
    	userData.setEmail(email);
		Response response = invocationBuilder.post(Entity.entity(userData, MediaType.APPLICATION_JSON));
		if (response.getStatus() != Status.OK.getStatusCode()) {
			System.out.println("Error connecting with the server. Code: " + response.getStatus());
			System.out.println("Error: " + response.toString());
		} else {
			System.out.println("User correctly registered");
		}
    }
    
    public void registerVan(VanData vanData) {
    	WebTarget registerVanWebTarget = webTarget.path("AirBV/registerVan"); 
		Invocation.Builder invocationBuilder = registerVanWebTarget.request(MediaType.APPLICATION_JSON);
		
		VanData van = new VanData();
		van.setBrand(vanData.getBrand());
		van.setCapacity(vanData.getCapacity());
		van.setKitchen(vanData.hasKitchen());
		van.setLicensePlate(vanData.getLicensePlate());
		van.setLocation(vanData.getLocation());
		van.setModel(vanData.getModel());
		van.setOffRoad(vanData.isOffRoad());
		van.setOwner(vanData.getOwner());
		van.setPricePerDay(vanData.getPricePerDay());
		van.setShower(vanData.hasShower());
		Response response = invocationBuilder.post(Entity.entity(van, MediaType.APPLICATION_JSON));
		if (response.getStatus() != Status.OK.getStatusCode()) {
			System.out.println("Error connecting with the server. Code: " + response.getStatus());
		} else {
			System.out.println("Van correctly registered");
		}
    }
    
    public boolean cancelReservation(String code) {
    	WebTarget cancelReservationWebTarget = webTarget.path("AirBV/cancelReservation"); 
		Invocation.Builder invocationBuilder = cancelReservationWebTarget.request(MediaType.APPLICATION_JSON);
		
		Response response = invocationBuilder.post(Entity.entity(code, MediaType.APPLICATION_JSON));
		if (response.getStatus() != Status.OK.getStatusCode()) {
			System.out.println("Error connecting with the server. Code: " + response.getStatus());
			return false;
		} else {
			System.out.println("Reservation correctly canceled");
			return true;
		}
    }
    
    public void registerReservation(Date bookingDate, int duration, VanData vanData, UserData vanRenter) {
    	WebTarget registerReservationWebTarget = webTarget.path("AirBV/registerReservation"); 
		Invocation.Builder invocationBuilder = registerReservationWebTarget.request(MediaType.APPLICATION_JSON);
		
    	ReservationData reservationData = new ReservationData();
    	reservationData.setBookingDate(bookingDate);
    	reservationData.setDuration(duration);
    	reservationData.setVan(vanData);
    	reservationData.setVanRenter(vanRenter);
		Response response = invocationBuilder.post(Entity.entity(reservationData, MediaType.APPLICATION_JSON));
		if (response.getStatus() != Status.OK.getStatusCode()) {
			System.out.println("Error connecting with the server. Code: " + response.getStatus());
		} else {
			System.out.println("Reservation correctly registered");
		}
    }

	public ArrayList<ReservationData> getMyReservations(UserData user) {		
		WebTarget reservationsWebTarget = webTarget.path("AirBV/getMyReservations/" + user.getDni());
		
		GenericType<ArrayList<ReservationData>> genericType = new GenericType<ArrayList<ReservationData>>() {};
		ArrayList<ReservationData> list = reservationsWebTarget.request(MediaType.APPLICATION_JSON).get(genericType);
		
		Invocation.Builder invocationBuilder =  reservationsWebTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		if(response.getStatus() == Status.OK.getStatusCode()) {
			return list;
		}
		
		return new ArrayList<ReservationData>();
	}
	
	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Use: java Client.Client [host] [port]");
			System.exit(0);
		}

        Controller c = new Controller(args[0], args[1]);
    }
}