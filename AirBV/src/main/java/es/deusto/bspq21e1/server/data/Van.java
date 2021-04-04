package es.deusto.bspq21e1.server.data;

import java.util.ArrayList;
import java.util.List;

public class Van {
    
    private String licensePlate;
    private String brand;
    private String model;
    private String status;

    private boolean kitchen;
    private boolean shower;
    private boolean offRoad;
    private int capacity;

    private User owner;
    private List<Review> reviews;

    public Van(String licensePlate, String brand, String model, boolean kitchen, boolean shower, boolean offRoad,
    int capacity, User owner) {
        this.licensePlate = licensePlate;
        this.brand = brand;
        this.model = model;
        this.status = "Open";
        this.kitchen = kitchen;
        this.shower = shower;
        this.offRoad = offRoad;
        this.capacity = capacity;
        this.owner = owner;
        this.reviews = new ArrayList<Review>();
    }

    // Getters and setters
}
