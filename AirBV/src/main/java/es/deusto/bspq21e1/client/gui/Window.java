package es.deusto.bspq21e1.client.gui;

import javax.swing.JFrame;

import es.deusto.bspq21e1.client.controller.Controller;

public class Window extends JFrame {

    private static final long serialVersionUID = 1L;
    private Controller controller;

    // Rest of elements will go here

    public Window(Controller controller) {
        this.controller = controller;
        setupWindow();
        initComponents();
        this.setVisible( true );
    }

    private void setupWindow() {
        
    }

    private void initComponents() {

    }
}