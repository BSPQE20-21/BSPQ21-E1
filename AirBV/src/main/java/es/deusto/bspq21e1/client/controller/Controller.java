package es.deusto.bspq21e1.client.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import es.deusto.bspq21e1.client.gui.InitialWindow;
import es.deusto.bspq21e1.client.remote.ServiceLocator;
import es.deusto.bspq21e1.server.data.User;
import es.deusto.bspq21e1.server.data.Van;
import es.deusto.bspq21e1.server.dto.VanDTO;

public class Controller {

    private ServiceLocator sl;

    public Controller(String args[]) {
        sl = new ServiceLocator();
        sl.setService(args);
        new InitialWindow(this);
    }

    // The methods will go here
    
    public ArrayList<VanDTO> searchVans(String location, String pickUpDate, String returnDate) {
    	try{
    		ArrayList<VanDTO> vans =  sl.getAirBVService().searchVans(location, pickUpDate, returnDate);
    		System.out.println("Controller "+vans);
    		return vans;
    	} catch(Exception e){
    		System.out.println("$ Error searching vans: " + e.getMessage());
    	}
    	return null;
    }
    
    public void registerUsers(String dni, String name, String email) {
        try {
			sl.getAirBVService().registerUser(dni, name, email);
		} catch (Exception e) {
    		System.out.println("$ Error registering user: " + e.getMessage());
		}
    }
    
    public void registerVan(VanDTO vanDTO) {
    	try {
    		sl.getAirBVService().registerVan(vanDTO);
    	}catch (Exception e) {
    		System.err.println("# Error registering van: " + e.getMessage());
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
    
    public void registerReservation(Date bookingDate, int duration, Van van, User vanRenter) {
    	try {
			sl.getAirBVService().registerReservation(bookingDate, duration, van, vanRenter);
		} catch (Exception e) {
    		System.out.println("$ Error registering reservation: " + e.getMessage());
		}
    }
    
    public static void main(String[] args) {
        new Controller(args);
    }
}