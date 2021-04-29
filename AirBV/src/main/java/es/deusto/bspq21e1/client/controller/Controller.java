package es.deusto.bspq21e1.client.controller;

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

import es.deusto.bspq21e1.serialization.ReservationData;
import es.deusto.bspq21e1.serialization.UserData;
import es.deusto.bspq21e1.serialization.VanData;

public class Controller {

    private Client client;
    private WebTarget webTarget;
    private static Logger logger = Logger.getLogger(Controller.class.getName());
    private ResourceBundle resourceBundle;
    
    public Controller(String hostname, String port) {
    	client = ClientBuilder.newClient();
    	webTarget = client.target(String.format("http://%s:%s/rest", hostname, port));
    	resourceBundle = ResourceBundle.getBundle("SystemMessages", Locale.getDefault());
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
			logger.debug("Error connecting with the server. Code: " + response.getStatus());
			logger.debug("Error: " + response.toString());
		}
		return new ArrayList<VanData>();
    }
    
    public void eraseUser(String dni) {
    	WebTarget eraseUserWebTarget = webTarget.path("AirBV/deleteUser/" + dni);
    	Invocation.Builder invocationBuilder = eraseUserWebTarget.request(MediaType.APPLICATION_JSON);
    	
    	Response response = invocationBuilder.delete();
    	if (response.getStatus() != Status.OK.getStatusCode()) {
    		logger.debug("Error connecting with the server. Code: " + response.getStatus());
			logger.debug("Error: " + response.toString());
		}
    }
    
    public void eraseVan(String licensePlate) {
    	WebTarget eraseVanWebTarget = webTarget.path("AirBV/deleteVan/" + licensePlate);
    	Invocation.Builder invocationBuilder = eraseVanWebTarget.request(MediaType.APPLICATION_JSON);
    	
    	Response response = invocationBuilder.delete();
    	if (response.getStatus() != Status.OK.getStatusCode()) {
    		logger.debug("Error connecting with the server. Code: " + response.getStatus());
			logger.debug("Error: " + response.toString());
		}
    }
    
    public void cancelReservation(String code) {
    	WebTarget cancelReservationWebTarget = webTarget.path("AirBV/cancelReservation/" + code); 
		Invocation.Builder invocationBuilder = cancelReservationWebTarget.request(MediaType.APPLICATION_JSON);
		
		Response response = invocationBuilder.delete();
		if (response.getStatus() != Status.OK.getStatusCode()) {
			logger.debug("Error connecting with the server. Code: " + response.getStatus());
			logger.debug("Error: " + response.toString());
		} else {
			logger.debug("Reservation correctly cancelled");
		}
    }
    
    public void registerUsers(String dni, String name, String email, String password) {
    	WebTarget registerUserWebTarget = webTarget.path("AirBV/registerUser"); 
		Invocation.Builder invocationBuilder = registerUserWebTarget.request(MediaType.APPLICATION_JSON);
		
    	UserData userData = new UserData();
    	userData.setDni(dni);
    	userData.setName(name);
    	userData.setEmail(email);
    	userData.setPassword(password);
		Response response = invocationBuilder.post(Entity.entity(userData, MediaType.APPLICATION_JSON));
		if (response.getStatus() != Status.OK.getStatusCode()) {
			logger.debug("Error connecting with the server. Code: " + response.getStatus());
			logger.debug("Error: " + response.toString());
		} else {
			logger.debug("User correctly registered");
		}
    }
    
    public UserData loginUser(String email, String password) {
    	WebTarget loginWebTarget = webTarget.path("AirBV/loginUser/" + email + "/" + password);
    	
    	GenericType<UserData> genericType = new GenericType<UserData>() {};
    	UserData userData = loginWebTarget.request(MediaType.APPLICATION_JSON).get(genericType);
    	
    	Invocation.Builder invocationBuilder = loginWebTarget.request(MediaType.APPLICATION_JSON);
    	Response response = invocationBuilder.get();
    	if (response.getStatus() == Status.OK.getStatusCode()) {
    		return userData;
    	} else {
    		logger.debug("Error connecting with the server. Code: " + response.getStatus());
			logger.debug("Error: " + response.toString());
    	}
    	return null;
    }
    
    public void registerVan(VanData vanData) {
    	logger.debug("$ DEBUGGING:\n" +
				"\tPrinting VanData and User from Controller in Client side:\n"+
				"\tVan: " + vanData +
				"\n\tUser: " + vanData.getUser() +
				"\n=======================\n");
    	
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
		van.setPricePerDay(vanData.getPricePerDay());
		van.setShower(vanData.hasShower());
		van.setReviews(vanData.getReviews());
		van.setUser(vanData.getUser());
		
		logger.debug("$ DEBUGGING\n" +
				"\tPrinting New VanData and User from Controller in Client side:\n"+
				"\tVan: " + van +
				"\n\tUser: " + van.getUser() +
				"\n=======================\n");
		
		Response response = invocationBuilder.post(Entity.entity(van, MediaType.APPLICATION_JSON));
		if (response.getStatus() != Status.OK.getStatusCode()) {
			logger.debug("Error connecting with the server. Code: " + response.getStatus());
			logger.debug("Error: " + response.toString());
		} else {
			logger.debug("Van correctly registered");
		}
    }
    
    public void registerReservation(Date bookingDate, int duration, VanData vanData, UserData vanRenter) {
    	WebTarget registerReservationWebTarget = webTarget.path("AirBV/registerReservation"); 
		Invocation.Builder invocationBuilder = registerReservationWebTarget.request(MediaType.APPLICATION_JSON);
		
    	ReservationData reservationData = new ReservationData();
    	reservationData.setBookingDate(bookingDate);
    	reservationData.setDuration(duration);
    	reservationData.setVan(vanData.getLicensePlate());
    	reservationData.setVanRenter(vanRenter.getDni());
		Response response = invocationBuilder.post(Entity.entity(reservationData, MediaType.APPLICATION_JSON));
		if (response.getStatus() != Status.OK.getStatusCode()) {
			logger.debug("Error connecting with the server. Code: " + response.getStatus());
			logger.debug("Error: " + response.toString());
		} else {
			logger.debug("Reservation correctly registered");
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
		} else {
			logger.debug("Error connecting with the server. Code: " + response.getStatus());
			logger.debug("Error: " + response.toString());
		}
		
		return new ArrayList<ReservationData>();
	}
	
	/**
	 * Returns the ResourceBundle
	 * @return ResourceBundle
	 */
	public ResourceBundle getResourcebundle() {
		return resourceBundle;
	}
	
}