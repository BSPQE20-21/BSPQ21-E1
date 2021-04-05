package es.deusto.bspq21e1.server.data;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.Join;

@PersistenceCapable
public class User {
    
    private String dni;
    private String name;
    private String email;
    private List<Integer> stars;

    @Join
    @Persistent(mappedBy = "owner", dependentElement = "true")
    private List<Van> vans;

    public User(String dni, String name, String email) {
        this.dni = dni;
        this.name = name;
        this.email = email;
        this.stars = new ArrayList<Integer>();
        this.vans =  new ArrayList<Van>();
    }

    /**
     * Calculates the average of the ratings given to a user
     * @return average number of stars
     */
    public int getStartsAverage(){
        int sum = 0;
        for(int i = 0; i < stars.size(); i++){
            sum+=stars.get(i);
        }

        int average = sum / stars.size();

        return average; 
    }
    // Getters and setters
}
