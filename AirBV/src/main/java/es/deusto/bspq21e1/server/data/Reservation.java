package es.deusto.bspq21e1.server.data;

import java.util.Calendar;
import java.util.Random;

public class Reservation {
    
    private String code;
    private Calendar bookingDate;
    private int duration;
    
    private Van van;
    private User vanRenter;

    public Reservation(int duration) {
        Random rnd = new Random();
        String c = "";
        for (int i = 0; i < 10; i++) {
            c += (char)(rnd.nextInt(91) + 65);
        }
        this.code = c;
        // Date?
        this.duration = duration;
        this.van = van;
        this.vanRenter = vanRenter;
    }

    // Getters and setters
}
