package es.deusto.bspq21e1.client.controller;

import java.util.ArrayList;
import java.util.List;

import es.deusto.bspq21e1.client.gui.InitialWindow;
import es.deusto.bspq21e1.client.remote.ServiceLocator;
import es.deusto.bspq21e1.serialization.VanData;

public class Controller {

    private ServiceLocator sl;

    public Controller(String args[]) {
        sl = new ServiceLocator();
        sl.setService(args);
        new InitialWindow(this);
    }

    // The methods will go here
    
    public ArrayList<VanData> searchVans(String location, String pickUpDate, String returnDate) {
    	try{
    		ArrayList<VanData> vans =  sl.getAirBVService().searchVans(location, pickUpDate, returnDate);
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
    
    public void registerVan(VanData vanData) {
    	try {
    		sl.getAirBVService().registerVan(vanData);
    	}catch (Exception e) {
    		System.err.println("# Error registering van: " + e.getMessage());
    	}
    }
    
    public static void main(String[] args) {
        new Controller(args);
    }
}