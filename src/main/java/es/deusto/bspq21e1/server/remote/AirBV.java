package es.deusto.bspq21e1.server.remote;

import java.util.ArrayList;

import es.deusto.bspq21e1.serialization.ReservationData;
import es.deusto.bspq21e1.serialization.UserData;
import es.deusto.bspq21e1.serialization.VanData;
import es.deusto.bspq21e1.server.data.Reservation;
import es.deusto.bspq21e1.server.data.User;
import es.deusto.bspq21e1.server.data.Van;
import es.deusto.bspq21e1.server.dto.Assembler;
import es.deusto.bspq21e1.server.AirBVService;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

/**
 * This is the remote façade pattern class.
 * This class will be in contact with the client side of the application.
 * Managing all of the GET and POST requests, and making use of the
 * ApplicationService pattern.
 * 
 * {@Path} in the class definition is used to define the HTTP path, as well
 * as all of the {@Path} seen bellow in the corresponding methods. 
 * 
 * @author Group 3
 *
 */
@Path("/AirBV")
@Produces(MediaType.APPLICATION_JSON)
public class AirBV {

	private AirBVService airbvService = new AirBVService();
	private Logger logger = Logger.getLogger(AirBV.class.getName());

	/**
	 * Variables needed for the POST/GET/DELETE methods paths.
	 * Should correspond to those of the client side methods.
	 */
	private static final String registerUser = "/registerUser";
	private static final String loginUser = "/loginUser/{email}/{password}";
	private static final String deleteUser = "/deleteUser/{dni}";
	private static final String registerVan = "/registerVan/{kitchen}/{shower}";
	private static final String deleteVan = "/deleteVan/{licensePlate}";
	private static final String getMyReservations = "/getMyReservations/{dni}";
	private static final String getVans = "/getVans/{location}/{pickUpDate}/{returnDate}";
	private static final String registerReservation = "/registerReservation";
	private static final String cancelReservation = "/cancelReservation/{code}";
	private static final String getMyVans = "/getMyVans/{dni}";
	private static final String registerUsersList = "/registerUsersList";
	private static final String registerVansList = "/registerVansList";
	private static final String registerReservationsList = "/registerReservationsList";

	/**
	 * Empty constructor for the class.
	 */
    public AirBV() { }
    
    /**
     * Gets the data from the user from the client, transforms it into a User and sends it to the Application Service to register it.
     * @param userData Data from the new user.
     * @return Response depending on the result of the execution.
     */
    @POST
    @Path(registerUser)
    public Response registerUser(UserData userData) {
    	logger.info("Register new User request from client received.");
        if(airbvService.registerUser( userData.getDni(), userData.getName(), userData.getEmail(), userData.getPassword() )) {
        	return Response.ok().build();
        }
        return Response.status(400).build();
    }
    
    /**
     * Gets the ID number from the user the client wants to delete and sends it to the Application Service for that.
     * @param dni ID number of the user client wants to delete.
     * @return Response depending on the result of the execution.
     */
    @DELETE
    @Path(deleteUser)
    public Response deleteUser(@PathParam("dni") String dni) {
    	logger.info("Delete a user request from client received.");
        airbvService.deleteUser( dni );
        return Response.status(Response.Status.OK).build();
    }

    /**
     * Transforms VanData into Van and uses a method from the Application Service to store a Van.
     * @param vanData Data of the van client wants to register.
     * @return Response depending on the result of the execution.
     */
    @POST
    @Path(registerVan)
    public Response registerVan(VanData vanData, @PathParam("kitchen") boolean kitchen, @PathParam("shower") boolean shower) {
    	logger.info("Register new Van request from client received.");
    	vanData.setKitchen(kitchen);
    	vanData.setShower(shower);
    	logger.debug("$ DEBUGGING\n" +
				"\tPrinting VanData and User from AirBV in Server side:\n"+
				"\tVan: " + vanData +
				"\n\tUser: " + vanData.getUser() +
				"\n=======================\n");
    	Assembler as = new Assembler();
    	
    	Van van = as.disassembleVan(vanData);
    	if(airbvService.registerVan(van)) {
    		return Response.ok().build();
    	}
    	return Response.status(400).build();
    }
    
    /**
     * Gets the license plate of the van client wants to erase and sends it to the Application Service for that.
     * @param licensePlate License plate of the van client wants to delete.
     * @return Response depending on the result of the execution.
     */
    @DELETE
    @Path(deleteVan)
    public Response deleteVan(@PathParam("licensePlate") String licensePlate) {
    	logger.info("Delete van request from client received.");
    	if(airbvService.deleteVan(licensePlate)) {
    		return Response.status(Response.Status.OK).build();
    	}
    	return Response.status(400).build();
    }

    /**
     * Gets the code of the reservation client wants to delete and sends it to the Application Service for that.
     * @param code Code of the reservation client wants to erase.
     * @return Response depending on the result of the execution.
     */
    @DELETE
    @Path(cancelReservation)
    public Response cancelReservation(@PathParam("code") String code) {
    	logger.info("Cancel a Reservation request from client received.");
        if(airbvService.cancelReservation(code)) {
        	return Response.status(Response.Status.OK).build();
        }
        return Response.status(400).build();        
    }
    
    /**
     * Gets the data of a new reservation client wants to register and sends it to the Application Service for that.
     * @param rD Gets the data of a new reservation client wants to register.
     * @return Response depending on the result of the execution.
     */
    @POST
    @Path(registerReservation)
    public Response registerReservation(ReservationData rD) {
    	logger.info("Register new Reservation request from client received.");
    	if (airbvService.registerReservation(rD.getBookingDate(), 
    			rD.getDuration(),
    			rD.getVan(),
    			rD.getVanRenter()
    			)) {
    		return Response.ok().build();
    	}
    	return Response.status(400).build();
    	
    }

    /**
     * Gets the location and dates which client has put as parameters and sends it to the Application Service to return the vans that
     * meet those requirements.
     * @param location Location of the van.
     * @param pickUpDate Date of pickup of the van.
     * @param returnDate Date of return of the van.
     * @return List of vans which meet the requirements.
     */
    @GET
    @Path(getVans)
	public ArrayList<VanData> searchVans( @PathParam("location") String location, @PathParam("pickUpDate") String pickUpDate, @PathParam("returnDate") String returnDate) {	
    	logger.info("Search with location: "+location+" from " + pickUpDate + " to " + returnDate + " request from client received.");
    	ArrayList<VanData> vansData = new ArrayList<VanData>();
		Assembler as = new Assembler();
		for (Van van : airbvService.searchVans(location, pickUpDate, returnDate)) {
			vansData.add(as.assembleVan(van));
		}
		return vansData;
	}
    
    /**
     * Gets the email and password of a user and checks if they are good credentials sending them to the Application Service.
     * @param email Email of the user.
     * @param password Password of the user.
     * @return Response depending on the result of the execution.
     */
    @GET
    @Path(loginUser)
    public Response login(@PathParam("email") String email, @PathParam("password") String password) { 
    	logger.info("User's login with credentials request received from client.");
    	User u = airbvService.login(email, password);
    	UserData userData = null;
    	if (u != null) {
    		Assembler as = new Assembler();
        	userData = as.assembleUser( u );
    	}
    	return Response.ok(userData).build();
    }

    /**
     * Gets the ID number of a user to get all their reservations using the Application Service.
     * @param dni ID number of the user.
     * @return List of reservations of the user.
     */
	@GET
	@Path(getMyReservations)
	public ArrayList<ReservationData> getUserReservations( @PathParam("dni") String dni ) {
		logger.info("Get user's reservations request from client received.");
		Assembler as = new Assembler();
		ArrayList<ReservationData> resData = new ArrayList<ReservationData>();
		for (Reservation reservation : airbvService.getUserReservations(dni)) {
			resData.add(as.assembleReservation(reservation));
		}
		
		return resData;
	}

	/**
	 * Gets the ID number of a user to get all their vans using the Application Service.
	 * @param dni ID number of the user.
	 * @return List of vans of the user.
	 */
	@GET
	@Path(getMyVans)
	public ArrayList<VanData> getUserVans( @PathParam("dni") String dni ) {
		logger.info("Get user's vans request from client received.");
		Assembler as = new Assembler();
		ArrayList<VanData> vanData = new ArrayList<VanData>();
		for (Van van : airbvService.getUserVans(dni)) {
			vanData.add(as.assembleVan(van));
		}
		return vanData;
	}
	
	/**
	 * Gets the data from a list of users to register them in the system using the Application Service.
	 * @param usersData List of data from the users.
	 * @return Response depending on the result of the execution.
	 */
	@POST
    @Path(registerUsersList)
    public Response registerUsersList(ArrayList<UserData> usersData) {
		logger.info("Register new users from list received");
		ArrayList<User> users = new ArrayList<User>();
		Assembler as = new Assembler();
		for (UserData u : usersData) {
			users.add( as.disassembleUser(u) );
		}
    	if ( airbvService.registerUsersList(users) ) {
    		logger.debug("registerUsersList methods in airBV seems to work");
    		return Response.ok().build();
    	}
    	logger.debug("airbvService doesn't return a true boolean. Check for errors and if users are stored");
    	return Response.status(400).build();
    }
    
	/**
	 * Gets the data from a list of vans to register them in the system using the Application Service.
	 * @param vansData List of data from the vans.
	 * @return Response depending on the result of the execution.
	 */
    @POST
    @Path(registerVansList)
    public Response registerVansList(ArrayList<VanData> vansData) {
    	logger.debug("Register new vans from list received. vansData size: " + vansData.size() + " + first: " + vansData.get(0).getLicensePlate());
    	ArrayList<Van> vans = new ArrayList<Van>();
    	Assembler as = new Assembler();
    	for (VanData v : vansData) {
    		vans.add( as.disassembleVan(v) );
    		logger.debug("Van diassembled and added to new array -> " + vans.get( vansData.indexOf(v) ).getLicensePlate());
    	}
    	if ( airbvService.registerVansList(vans) ) {
    		logger.debug("registerVansList methods in airBV seems to work");
    		return Response.ok().build();
    	}
    	logger.debug("airbvService doesn't return a true boolean. Check for errors and if vans are stored");
    	return Response.status(400).build();
    }
    
    /**
	 * Gets the data from a list of reservations to register them in the system using the Application Service.
	 * @param reservationsData List of data from the reservations.
	 * @return Response depending on the result of the execution.
	 */
    @POST
    @Path(registerReservationsList)
    public Response registerReservationsList(ArrayList<ReservationData> reservationsData) {
    	logger.info("Register new reservations from list received");
    	ArrayList<Reservation> reservations = new ArrayList<Reservation>();
    	Assembler as = new Assembler();
    	for (ReservationData r : reservationsData) {
    		reservations.add( as.disassembleReservation(r) );
    	}
    	if ( airbvService.registerReservationsList(reservations) ) {
    		return Response.ok().build();
    	}
    	return Response.status(400).build();
    }
}
