package es.deusto.bspq21e1.server.data;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class User {
    @PrimaryKey
    private String dni;
    private String name;
    private String email;
    private String password;
    private List<Integer> stars;
    
    public User() {
    	
    }

	public User(String dni, String name, String email, String password) {
        this.dni = dni;
        this.name = name;
        this.email = email;
        this.password = password;
        this.stars = new ArrayList<Integer>();
    }

    /**
     * Calculates the average of the ratings given to a user
     * @return average number of stars
     */
    public int getStarsAverage(){
        int sum = 0;
        int average = 0;
        if(stars.size() > 0) {
        	for(int i = 0; i < stars.size(); i++){
                sum+=stars.get(i);
            }
            average = sum / stars.size();
        }
        return average; 
    }
    // Getters and setters

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
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Integer> getStars() {
		return stars;
	}

	public void setStars(List<Integer> stars) {
		this.stars = stars;
	}
	
	public void addStar(int star) {
		this.stars.add(star);
	}

	@Override
	public boolean equals(Object obj) {
		return dni.equals(((User) obj).getDni());
	}

}
