package es.deusto.bspq21e1.server.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;

import es.deusto.bspq21e1.serialization.ReservationData;
import es.deusto.bspq21e1.serialization.UserData;
import es.deusto.bspq21e1.serialization.VanData;
import es.deusto.bspq21e1.server.data.Reservation;
import es.deusto.bspq21e1.server.data.User;
import es.deusto.bspq21e1.server.data.Van;
import es.deusto.bspq21e1.server.dto.Assembler;
import es.deusto.bspq21e1.server.AirBVService;

// This is the REMOTE FACADE
public class AirBV extends UnicastRemoteObject implements IAirBV {
    
    private static final long serialVersionUID = 1L;
    private AirBVService airbvService;

    public AirBV(AirBVService airbvService) throws RemoteException{
        super();
        this.airbvService = airbvService;
    }

    // Here will go the methods
    public void registerUser(String dni, String name, String email) {
        airbvService.registerUser(dni, name, email);
    }
    
    /**
     * Transforms VanData into Van and uses a method from the Application Service to store a Van
     * @param vanData
     */
    public void registerVan(VanData vanData) {
    	Assembler as = new Assembler();
    	
    	Van van = as.disassembleVan(vanData);
    	airbvService.registerVan(van);
    }

    public boolean cancelReservation(String code) {
        return airbvService.cancelReservation(code);
    }
    
    public void registerReservation(Date bookingDate, int duration, VanData vanData, UserData vanRenter) {
    	Assembler as = new Assembler();
    	Van van = as.disassembleVan(vanData);
    	User user = as.disassembleUser(vanRenter);
    	airbvService.registerReservation(bookingDate, duration, van, user);
    }

	@Override
	public ArrayList<VanData> searchVans(String location) {
		
		ArrayList<Van> vans = airbvService.searchVans(location);
		ArrayList<VanData> vansData = new ArrayList<VanData>();
		Assembler as = new Assembler();
		for (Van van : vans) {
			vansData.add(as.assembleVan(van));
		}
		return vansData;
	}

	@Override
	public ArrayList<ReservationData> getUserReservations(UserData userData) {
		Assembler as = new Assembler();
		User user = as.disassembleUser(userData);
		ArrayList<Reservation> res = airbvService.getUserReservations(user);
		ArrayList<ReservationData> resData = new ArrayList<ReservationData>();
		for (Reservation reservation : res) {
			resData.add(as.assembleReservation(reservation));
		}
		return resData;
	}

}
