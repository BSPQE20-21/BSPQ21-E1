package es.deusto.bspq21e1.server.data;

public class Review {
    
    private int number;
    private int stars;
    private String comment;

    public Review(int stars, String comment) {
        this.number = (int)Math.random()*10000;
        this.stars = stars;
        this.comment = comment;
    }

    // Getters and setters
}
