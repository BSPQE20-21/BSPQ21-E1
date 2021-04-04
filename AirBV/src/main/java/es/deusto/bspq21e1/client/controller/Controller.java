package es.deusto.bspq21e1.client.controller;

import es.deusto.bspq21e1.client.gui.Window;
import es.deusto.bspq21e1.client.remote.ServiceLocator;

public class Controller {

    private ServiceLocator sl;

    public Controller(String args[]) {
        sl = new ServiceLocator();
        sl.setService(args);
        new Window(this);
    }

    // The methods will go here


    public static void main(String[] args) {
        new Controller(args);
    }
}