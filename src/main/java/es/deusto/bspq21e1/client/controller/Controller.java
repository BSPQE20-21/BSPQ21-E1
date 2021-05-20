package es.deusto.bspq21e1.client.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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

import es.deusto.bspq21e1.serialization.ReservationData;
import es.deusto.bspq21e1.serialization.UserData;
import es.deusto.bspq21e1.serialization.VanData;

public class Controller {

    private Client client;
    private WebTarget webTarget;
    private static Logger logger = Logger.getLogger(Controller.class.getName());
    private ResourceBundle resourceBundle;
    private Locale currentLocale;
    
    private String demoDataPath;
    
    public Controller(String hostname, String port, String demoDataPath) {
    	client = ClientBuilder.newClient();
    	webTarget = client.target(String.format("http://%s:%s/rest", hostname, port));
    	resourceBundle = ResourceBundle.getBundle("SystemMessages", Locale.getDefault());
    	this.demoDataPath = demoDataPath;
    }
    
    public ArrayList<VanData> searchVans(String location, String pickUpDate, String returnDate) {
    	WebTarget vansWebTarget = webTarget.path("AirBV/getVans/" + location + "/" + pickUpDate + "/" + returnDate);
    	
    	GenericType<ArrayList<VanData>> genericType = new GenericType<ArrayList<VanData>>() {};
		ArrayList<VanData> list = vansWebTarget.request(MediaType.APPLICATION_JSON).get(genericType);
		
		Invocation.Builder invocationBuilder =  vansWebTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		if(response.getStatus() == Status.OK.getStatusCode()) {
			logger.info("Vans received to the controller.");
			return list;
		}else {
			logger.error("Error connecting with the server. Code: " + response.getStatus());
			logger.error("Error: " + response.toString());
		}
		return new ArrayList<VanData>();
    }
    
    public boolean eraseUser(String dni) {
    	WebTarget eraseUserWebTarget = webTarget.path("AirBV/deleteUser/" + dni);
    	Invocation.Builder invocationBuilder = eraseUserWebTarget.request(MediaType.APPLICATION_JSON);
    	
    	Response response = invocationBuilder.delete();
    	if (response.getStatus() != Status.OK.getStatusCode()) {
    		logger.error("Error connecting with the server. Code: " + response.getStatus());
			logger.error("Error: " + response.toString());
			return false;
		}
    	logger.info("User erased (message from controller)");
    	return true;
    }
    
    public boolean eraseVan(String licensePlate) {
    	WebTarget eraseVanWebTarget = webTarget.path("AirBV/deleteVan/" + licensePlate);
    	Invocation.Builder invocationBuilder = eraseVanWebTarget.request(MediaType.APPLICATION_JSON);
    	
    	Response response = invocationBuilder.delete();
    	if (response.getStatus() != Status.OK.getStatusCode()) {
    		logger.error("Error connecting with the server. Code: " + response.getStatus());
			logger.error("Error: " + response.toString());
			return false;
		}
    	logger.info("Van erased (message from controller)");
    	return true;
    }
    
    public boolean cancelReservation(String code) {
    	WebTarget cancelReservationWebTarget = webTarget.path("AirBV/cancelReservation/" + code); 
		Invocation.Builder invocationBuilder = cancelReservationWebTarget.request(MediaType.APPLICATION_JSON);
		
		Response response = invocationBuilder.delete();
		if (response.getStatus() != Status.OK.getStatusCode()) {
			logger.error("Error connecting with the server. Code: " + response.getStatus());
			logger.error("Error: " + response.toString());
			return false;
		} else {
			logger.info("Reservation correctly cancelled");
			return true;
		}
    }
    
    public boolean registerUsers(String dni, String name, String email, String password) {
    	WebTarget registerUserWebTarget = webTarget.path("AirBV/registerUser"); 
		Invocation.Builder invocationBuilder = registerUserWebTarget.request(MediaType.APPLICATION_JSON);
		
    	UserData userData = new UserData();
    	userData.setDni(dni);
    	userData.setName(name);
    	userData.setEmail(email);
    	userData.setPassword(password);
		Response response = invocationBuilder.post(Entity.entity(userData, MediaType.APPLICATION_JSON));
		if (response.getStatus() != Status.OK.getStatusCode()) {
			logger.error("Error connecting with the server. Code: " + response.getStatus());
			logger.error("Error: " + response.toString());
			return false;
		} else {
			logger.info("User correctly registered");
			return true;
		}
    }
    
    public UserData loginUser(String email, String password) {
    	WebTarget loginWebTarget = webTarget.path("AirBV/loginUser/" + email + "/" + password);
    	
    	GenericType<UserData> genericType = new GenericType<UserData>() {};
    	UserData userData = loginWebTarget.request(MediaType.APPLICATION_JSON).get(genericType);
    	
    	Invocation.Builder invocationBuilder = loginWebTarget.request(MediaType.APPLICATION_JSON);
    	Response response = invocationBuilder.get();
    	if (response.getStatus() == Status.OK.getStatusCode()) {
    		logger.info("User has logged in");
    		return userData;
    	} else {
    		logger.error("Error connecting with the server. Code: " + response.getStatus());
			logger.error("Error: " + response.toString());
    	}
    	return null;
    }
    
    public boolean registerVan(VanData vanData) {
    	logger.debug("$ DEBUGGING:\n" +
				"\tPrinting VanData and User from Controller in Client side:\n"+
				"\tVan: " + vanData +
				"\n\tUser: " + vanData.getUser() +
				"\n=======================\n");
    	
    	WebTarget registerVanWebTarget = webTarget.path("AirBV/registerVan/"+vanData.isKitchen()+"/"+vanData.isShower()); 
		Invocation.Builder invocationBuilder = registerVanWebTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(vanData, MediaType.APPLICATION_JSON));
		if (response.getStatus() != Status.OK.getStatusCode()) {
			logger.error("Error connecting with the server. Code: " + response.getStatus());
			logger.error("Error: " + response.toString());
			return false;
		} else {
			logger.info("Van correctly registered");
			return true;
		}
    }
    
    public boolean registerReservation(Date bookingDate, int duration, VanData vanData, UserData vanRenter) {
    	WebTarget registerReservationWebTarget = webTarget.path("AirBV/registerReservation"); 
		Invocation.Builder invocationBuilder = registerReservationWebTarget.request(MediaType.APPLICATION_JSON);
		
    	ReservationData reservationData = new ReservationData();
    	reservationData.setBookingDate(bookingDate);
    	reservationData.setDuration(duration);
    	reservationData.setVan(vanData.getLicensePlate());
    	reservationData.setVanRenter(vanRenter.getDni());
		Response response = invocationBuilder.post(Entity.entity(reservationData, MediaType.APPLICATION_JSON));
		if (response.getStatus() != Status.OK.getStatusCode()) {
			logger.error("Error connecting with the server. Code: " + response.getStatus());
			logger.error("Error: " + response.toString());
			return false;
		} else {
			logger.info("Reservation correctly registered");
			return true;
		}
    }

	public ArrayList<ReservationData> getMyReservations(UserData user) {		
		WebTarget reservationsWebTarget = webTarget.path("AirBV/getMyReservations/" + user.getDni());
		
		GenericType<ArrayList<ReservationData>> genericType = new GenericType<ArrayList<ReservationData>>() {};
		ArrayList<ReservationData> list = reservationsWebTarget.request(MediaType.APPLICATION_JSON).get(genericType);
		
		Invocation.Builder invocationBuilder =  reservationsWebTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		if(response.getStatus() == Status.OK.getStatusCode()) {
			logger.info("Reservations well retrieved");
			return list;
		} else {
			logger.error("Error connecting with the server. Code: " + response.getStatus());
			logger.error("Error: " + response.toString());
		}
		
		return new ArrayList<ReservationData>();
	}
	
	public ArrayList<VanData> getMyVans(String dni) {
		WebTarget vansWebTarget = webTarget.path("AirBV/getMyVans/" + dni);
		
		GenericType<ArrayList<VanData>> genericType = new GenericType<ArrayList<VanData>>() {};
		ArrayList<VanData> list = vansWebTarget.request(MediaType.APPLICATION_JSON).get(genericType);
		
		Invocation.Builder invocationBuilder = vansWebTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		if (response.getStatus() == Status.OK.getStatusCode()) {
			logger.info("Van well retrieved");
			return list;
		} else {
			logger.error("Error connecting with the server. Code: " + response.getStatus());
			logger.error("Error: " + response.toString());
		}
		return null;
	}
	
	public boolean registerUsersList(ArrayList<UserData> users) {
		WebTarget usersWebTarget = webTarget.path("AirBV/registerUsersList");
		
		Invocation.Builder invocationBuilder = usersWebTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(users, MediaType.APPLICATION_JSON));
		if (response.getStatus() != Status.OK.getStatusCode()) {
			logger.error("Error connecting with the server. Code: " + response.getStatus());
			logger.error("Error: " + response.toString());
			return false;
		} else {
			logger.info("Mock users correctly registered. Message from Controller.");
			return true;
		}
	}
	
	public boolean registerVansList(ArrayList<VanData> vans) {
		WebTarget vansWebTarget = webTarget.path("AirBV/registerVansList");
		for (VanData v : vans) {
			logger.debug("Van " + vans.indexOf( v ) + "   Kitchen: " + v.isKitchen() + "   Shower: " + v.isShower() + "   Off-road: " + v.isOffRoad());
		}
		Invocation.Builder invocationBuilder = vansWebTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(vans, MediaType.APPLICATION_JSON));
		if (response.getStatus() != Status.OK.getStatusCode()) {
			logger.error("Error connecting with the server. Code: " + response.getStatus());
			logger.error("Error: " + response.toString());
			return false;
		} else {
			logger.info("Mock vans correctly registered. Message from Controller.");
			return true;
		}
	}
	
	public boolean registerReservationsList(ArrayList<ReservationData> reservations) {
		WebTarget reservationsWebTarget = webTarget.path("AirBV/registerReservationsList");
		
		Invocation.Builder invocationBuilder = reservationsWebTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(reservations, MediaType.APPLICATION_JSON));
		if (response.getStatus() != Status.OK.getStatusCode()) {
			logger.error("Error connecting with the server. Code: " + response.getStatus());
			logger.error("Error: " + response.toString());
			return false;
		} else {
			logger.info("Mock reservations correctly registered. Message from Controller.");
			return true;
		}
	}
	
	/**
	 * Reads the csv with the demo data and creates the different lists for dividing users, vans and reservations.
	 * Then, it calls three different methods for storing those objects in the system DB.
	 */
	public void initializeDemoData() {
		ArrayList<UserData> users = new ArrayList<UserData>();
		ArrayList<VanData> vans = new ArrayList<VanData>();
		ArrayList<ReservationData> reservations = new ArrayList<ReservationData>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(demoDataPath));
			String line = br.readLine();
			while (line != null) {
				String[] fields = line.split(",");
				if (fields.length == 4) { // User
					users.add( new UserData(fields[0], fields[1], fields[2], fields[3]) );
					logger.debug("User created from csv. Password: " + fields[3]);
				} else if (fields.length == 5) { // Reservation
					Date d = null;
					try {
						d = new SimpleDateFormat("dd-MM-yyyy").parse(fields[0]);
						logger.debug("Date from reservation well parsed");
					} catch (ParseException e) {
						e.printStackTrace();
						logger.error("Error parsing pickup date");
					}
					reservations.add( new ReservationData(d, Integer.parseInt(fields[1]), fields[2], fields[3]) );
					logger.debug("Reservation created from csv");
				} else { // Van -> DEBUG HERE
					VanData v = new VanData();
					v.setLicensePlate( fields[0] ); logger.debug("License plate from van stored");
					v.setBrand( fields[1] ); logger.debug("Brand from van stored");
					v.setModel( fields[2] ); logger.debug("Model from van stored");
					v.setLocation( fields[3] ); logger.debug("Location from van stored");
					logger.debug("Capacity: " + fields[4]);
					v.setCapacity( Integer.parseInt( fields[4] ) ); logger.debug("Capacity from van stored");
					logger.debug("Kitchen: " + fields[5]);
					v.setKitchen( Boolean.parseBoolean( fields[5] ) ); logger.debug("Kitchen from van stored");
					logger.debug("Shower: " + fields[6]);
					v.setShower( Boolean.parseBoolean( fields[6] ) ); logger.debug("Shower from van stored");
					logger.debug("Off-road: " + fields[7]);
					v.setOffRoad( Boolean.parseBoolean( fields[7] ) ); logger.debug("Off-road from van stored");
					logger.debug("Price: " + fields[8]);
					v.setPricePerDay( Double.parseDouble( fields[8] ) ); logger.debug("Price from van stored");
					logger.debug("User's dni: " + fields[9]);
					v.setUser( fields[9] ); logger.debug("User's dni from van stored");
					logger.debug("Van created from csv");
					vans.add(v);
				}
				line = br.readLine();
			}
			registerUsersList(users);
			logger.debug("registerUsersList method in Controller executed");
			registerVansList(vans);
			logger.debug("registerVansList method in Controller executed");
			registerReservationsList(reservations);
			logger.debug("registerReservationsList method in Controller executed");
		} catch (Exception e) {
			logger.error("It wasn't possible to read the demo data");
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					logger.error("It wasn't possible to close the buffered reader");
				}
			}
		}
	}
	
	/**
	 * Returns the ResourceBundle
	 * @return ResourceBundle
	 */
	public ResourceBundle getResourcebundle() {
		return resourceBundle;
	}
	
	/**
	 * Sets the currentLocale
	 * @param locale
	 */
	public void setLocale(String locale){
		if(locale.equals("es")){
			currentLocale = new Locale("es");
		}else if(locale.equals("eu")){
			currentLocale = new Locale("eu");
		}else if(locale.equals("en")){
			currentLocale = new Locale("en");
		}
		resourceBundle = ResourceBundle.getBundle("SystemMessages", currentLocale);
	}

	public String getDemoDataPath() {
		return demoDataPath;
	}
	
}