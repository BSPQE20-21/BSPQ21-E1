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

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;



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

	private AirBVService airbvService;

	/**
	 * Variables needed for the post and get methods paths.
	 * Should correspond to those of the client side methods.
	 */
	
	private static final String registerUser = "/registerUser";
	private static final String registerVan = "/registerVan";
	private static final String getMyReservations = "/getMyReservations";
	private static final String getVans = "/getVans";
	private static final String registerReservation = "/registerReservation";
	private static final String cancelReservation = "/cancelReservation";
	
    public AirBV(AirBVService airbvService){
        super();
        this.airbvService = airbvService;
    }    

    
    
    // Here will go the methods
    @POST
    @Path(registerUser)
    public Response registerUser(UserData user) {
        airbvService.registerUser( user.getDni(), user.getName(), user.getEmail() );
        
        return Response.ok().build();
    }
    
    /**
     * Transforms VanData into Van and uses a method from the Application Service to store a Van
     * @param vanData
     */
    @POST
    @Path(registerVan)
    public Response registerVan(VanData vanData) {
    	Assembler as = new Assembler();
    	
    	Van van = as.disassembleVan(vanData);
    	airbvService.registerVan(van);
    	
    	return Response.ok().build();
    }

    @POST
    @Path(cancelReservation)
    public Response cancelReservation(String code) {
        airbvService.cancelReservation(code);
        return Response.ok().build();        
    }
    
    @POST
    @Path(registerReservation)
    public Response registerReservation(ReservationData rD) {
        
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
	public ArrayList<VanData> searchVans( @QueryParam("location") String location) {	
		ArrayList<VanData> vansData = new ArrayList<VanData>();
		Assembler as = new Assembler();
		for (Van van : airbvService.searchVans(location)) {
			vansData.add(as.assembleVan(van));
		}
		
		return vansData;
	}

	@GET
	@Path(getMyReservations)
	public ArrayList<ReservationData> getUserReservations( @QueryParam("dni") String dni ) {
		Assembler as = new Assembler();
		ArrayList<ReservationData> resData = new ArrayList<ReservationData>();
		for (Reservation reservation : airbvService.getUserReservations(dni)) {
			resData.add(as.assembleReservation(reservation));
		}
		
		return resData;
	}

}
