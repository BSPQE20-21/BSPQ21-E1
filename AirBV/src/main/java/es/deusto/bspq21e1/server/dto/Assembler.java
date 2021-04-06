package es.deusto.bspq21e1.server.dto;

import es.deusto.bspq21e1.server.data.Reservation;
import es.deusto.bspq21e1.server.data.Review;
import es.deusto.bspq21e1.server.data.User;
import es.deusto.bspq21e1.server.data.Van;

public class Assembler {
    
    /*public UserDTO assembleUser(User u) {

    }*/

	/*public ReservationDTO assembleReservation(Reservation r) {

    }*/

    public VanDTO assembleVan(Van v) {
    	VanDTO vanDTO = new VanDTO(v.getBrand(), v.getModel(), v.getLocation(), v.hasKitchen(), v.hasShower(), v.isOffRoad(), v.getCapacity(), v.getPricePerDay());
    	return vanDTO;	
    }

    /*public ReviewDTO assembleReview(Review re) {
        
    }

    public User disassembleUser(UserDTO u) {

    }

    public Reservation disassembleReservation(ReservationDTO r) {

    }

    public Van disassembleVan(VanDTO v) {

    }

    public Review disassembleReview(ReviewDTO re) {
        
    }*/
}
