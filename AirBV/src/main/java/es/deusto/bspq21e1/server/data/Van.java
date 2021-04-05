package es.deusto.bspq21e1.server.data;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.Join;
import javax.jdo.annotations.Persistent;

public class Van {
    
    private String licensePlate;
    private String brand;
    private String model;
    private String status;
    private String location;

    private boolean kitchen;
    private boolean shower;
    private boolean offRoad;
    private int capacity;
    private double pricePerDay;

    private User owner;
    @Join
    private List<Review> reviews;

    public Van(String licensePlate, String brand, String model, String location, boolean kitchen, boolean shower, boolean offRoad,
    int capacity, double pricePerDay, User owner) {
        this.licensePlate = licensePlate;
        this.brand = brand;
        this.model = model;
        this.status = "Open";
        this.location = location;
        this.kitchen = kitchen;
        this.shower = shower;
        this.offRoad = offRoad;
        this.capacity = capacity;
        this.pricePerDay = pricePerDay;
        this.owner = owner;
        this.reviews = new ArrayList<Review>();
    }

    // Getters and setters
}
