package es.deusto.bspq21e1.server.remote;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;

import es.deusto.bspq21e1.serialization.ReservationData;
import es.deusto.bspq21e1.serialization.UserData;
import es.deusto.bspq21e1.serialization.VanData;
import es.deusto.bspq21e1.server.data.Reservation;
import es.deusto.bspq21e1.server.data.User;
import es.deusto.bspq21e1.server.data.Van;
import es.deusto.bspq21e1.server.dto.Assembler;
import es.deusto.bspq21e1.server.AirBVService;

import es.deusto.bspq21e1.serialization.*;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

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
 * @author Iñigo (adaptation from RMI to RESTful API).
 *
 */

@Path("/AirBV")    //ABITRARILY DEFINED.
@Produces(MediaType.APPLICATION_JSON)
public class AirBV {

	private AirBVService airbvService = new AirBVService();
	private Logger logger = Logger.getLogger(AirBV.class.getName());

	/**
	 * Variables needed for the post and get methods paths.
	 * Should correspond to those of the client side methods.
	 */
	
	private static final String registerUser = "/registerUser";
	private static final String loginUser = "/loginUser";
	private static final String deleteUser = "/deleteUser/{dni}";
	private static final String registerVan = "/registerVan";
	private static final String deleteVan = "/deleteVan/{licensePlate}";
	private static final String getMyReservations = "/getMyReservations/{dni}";
	private static final String getVans = "/getVans/{location}";
	private static final String registerReservation = "/registerReservation";
	private static final String cancelReservation = "/cancelReservation";

    public AirBV() { }
    
    // Here will go the methods
    @POST
    @Path(registerUser)
    public Response registerUser(UserData userData) {
    	logger.debug("Register new User request from client received.");
        airbvService.registerUser( userData.getDni(), userData.getName(), userData.getEmail(), userData.getPassword() );
        return Response.ok().build();
    }
    
    @DELETE
    @Path(deleteUser)
    public Response deleteUser(@PathParam("dni") String dni) {
    	logger.debug("Delete a user request from client received.");
        airbvService.deleteUser( dni );
        return Response.status(Response.Status.OK).build();
    }

    /**
     * Transforms VanData into Van and uses a method from the Application Service to store a Van
     * @param vanData
     */
    @POST
    @Path(registerVan)
    public Response registerVan(VanData vanData) {
    	logger.debug("Register new Van request from client received.");
    	Assembler as = new Assembler();
    	
    	Van van = as.disassembleVan(vanData);
    	airbvService.registerVan(van);
    	
    	return Response.ok().build();
    }
    
    @DELETE
    @Path(deleteVan)
    public Response deleteVan(@PathParam("licensePlate") String licensePlate) {
    	logger.debug("Delete van request from client received.");
    	airbvService.deleteVan(licensePlate);
    	return Response.status(Response.Status.OK).build();
    }

    @POST
    @Path(cancelReservation)
    public Response cancelReservation(String code) {
    	logger.debug("Cancel a Reservation request from client received.");
        airbvService.cancelReservation(code);
        return Response.ok().build();        
    }
    
    @POST
    @Path(registerReservation)
    public Response registerReservation(ReservationData rD) {
    	logger.debug("Register new Reservation request from client received.");
    	Assembler as = new Assembler();
    	
    	airbvService.registerReservation(as.disassembleReservation(rD).getBookingDate(), 
    			as.disassembleReservation(rD).getDuration(),
    			as.disassembleReservation(rD).getVan(),
    			as.disassembleReservation(rD).getVanRenter()
    			);
    	
    	return Response.ok().build();
    }

    @GET
    @Path(getVans)
	public ArrayList<VanData> searchVans( @PathParam("location") String location) {	
    	logger.debug("Search with location: "+location+" request from client received.");
    	ArrayList<VanData> vansData = new ArrayList<VanData>();
		Assembler as = new Assembler();
		for (Van van : airbvService.searchVans(location)) {
			vansData.add(as.assembleVan(van));
		}
		return vansData;
	}
    
    @GET
    @Path(loginUser)
    public UserData login(@PathParam("email") String email, @PathParam("password") String password) { 
    	logger.debug("User's login with credentials request received from client.");
    	User u = airbvService.login(email, password);
    	UserData userData = null;
    	if (u != null) {
    		Assembler as = new Assembler();
        	userData = as.assembleUser( u );
    	}
    	return userData;
    }

	@GET
	@Path(getMyReservations)
	public ArrayList<ReservationData> getUserReservations( @PathParam("dni") String dni ) {
		logger.debug("Get user's reservations request from client received.");
		Assembler as = new Assembler();
		ArrayList<ReservationData> resData = new ArrayList<ReservationData>();
		for (Reservation reservation : airbvService.getUserReservations(dni)) {
			resData.add(as.assembleReservation(reservation));
		}
		
		return resData;
	}

}
