package es.deusto.bspq21e1.serialization;

import java.util.ArrayList;
import java.util.List;

import es.deusto.bspq21e1.server.data.Van;

/**
 * Class for the representation of the User object which is going to be sended to clients.
 * @author SPQ Group 1
 * @version 1.0
 */
public class UserData {
    
	private String dni;
    private String name;
    private String email;
    private List<Integer> stars;
    
    private List<VanData> vans;
    
    public UserData() {
    	
    }

	/**
	 * Creates the object that has the data from User.
	 * @param dni DNI of the user.
	 * @param name Name of the user.
	 * @param email Email of the user.
	 */
    public UserData(String dni, String name, String email, List<VanData> vans) {
    	this.dni = dni;
        this.name = name;
        this.email = email;
        this.stars = new ArrayList<Integer>();
        this.vans = vans;
    }
    
    public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Integer> getStars() {
		return stars;
	}

	public void setStars(List<Integer> stars) {
		this.stars = stars;
	}
	
	public List<VanData> getVans() {
		return vans;
	}

	public void setVans(List<VanData> vans) {
		this.vans = vans;
	}
	
	public void addVan(VanData van) {
		if(this.vans == null) {
			this.vans = new ArrayList<VanData>();
		}
		this.vans.add(van);
	}
	
	public void removeVan(VanData van) {
		this.vans.remove(this.vans.indexOf(van));
	}

	@Override
	public String toString() {
		return "UserData [dni=" + dni + ", name=" + name + ", email=" + email + ", stars=" + stars + "]";
	}
	
}
