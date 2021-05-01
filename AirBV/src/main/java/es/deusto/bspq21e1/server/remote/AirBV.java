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
	 * Variables needed for the post and get methods paths.
	 * Should correspond to those of the client side methods.
	 */
	
	private static final String registerUser = "/registerUser";
	private static final String loginUser = "/loginUser/{email}/{password}";
	private static final String deleteUser = "/deleteUser/{dni}";
	private static final String registerVan = "/registerVan";
	private static final String deleteVan = "/deleteVan/{licensePlate}";
	private static final String getMyReservations = "/getMyReservations/{dni}";
	private static final String getVans = "/getVans/{location}";
	private static final String registerReservation = "/registerReservation";
	private static final String cancelReservation = "/cancelReservation/{code}";
	private static final String getMyVans = "/getMyVans/{dni}";

    public AirBV() { }
    
    @POST
    @Path(registerUser)
    public Response registerUser(UserData userData) {
    	logger.info("Register new User request from client received.");
        if(airbvService.registerUser( userData.getDni(), userData.getName(), userData.getEmail(), userData.getPassword() )) {
        	return Response.ok().build();
        }
        return Response.status(400).build();
    }
    
    @DELETE
    @Path(deleteUser)
    public Response deleteUser(@PathParam("dni") String dni) {
    	logger.info("Delete a user request from client received.");
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
    	logger.info("Register new Van request from client received.");
    	Assembler as = new Assembler();
    	
    	Van van = as.disassembleVan(vanData);
    	if(airbvService.registerVan(van)) {
    		return Response.ok().build();
    	}
    	return Response.status(400).build();
    }
    
    @DELETE
    @Path(deleteVan)
    public Response deleteVan(@PathParam("licensePlate") String licensePlate) {
    	logger.info("Delete van request from client received.");
    	if(airbvService.deleteVan(licensePlate)) {
    		return Response.status(Response.Status.OK).build();
    	}
    	return Response.status(400).build();
    }

    @DELETE
    @Path(cancelReservation)
    public Response cancelReservation(@PathParam("code") String code) {
    	logger.info("Cancel a Reservation request from client received.");
        if(airbvService.cancelReservation(code)) {
        	return Response.status(Response.Status.OK).build();
        }
        return Response.status(400).build();        
    }
    
    @POST
    @Path(registerReservation)
    public Response registerReservation(ReservationData rD) {
    	logger.info("Register new Reservation request from client received.");
    	Assembler as = new Assembler();
    	
    	if(airbvService.registerReservation(as.disassembleReservation(rD).getBookingDate(), 
    			as.disassembleReservation(rD).getDuration(),
    			as.disassembleReservation(rD).getVan(),
    			as.disassembleReservation(rD).getVanRenter()
    			)) {
    		return Response.ok().build();
    	}
    	return Response.status(400).build();
    	
    }

    @GET
    @Path(getVans)
	public ArrayList<VanData> searchVans( @PathParam("location") String location) {	
    	logger.info("Search with location: "+location+" request from client received.");
    	ArrayList<VanData> vansData = new ArrayList<VanData>();
		Assembler as = new Assembler();
		for (Van van : airbvService.searchVans(location)) {
			vansData.add(as.assembleVan(van));
		}
		return vansData;
	}
    
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
}
