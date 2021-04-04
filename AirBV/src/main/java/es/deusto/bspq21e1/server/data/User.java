package es.deusto.bspq21e1.server.data;

public class User {
    
    private String dni;
    private String name;
    private String email;
    private int stars;

    public User(String dni, String name, String email) {
        this.dni = dni;
        this.name = name;
        this.email = email;
        this.stars = 0;
    }

    // Getters and setters
}
