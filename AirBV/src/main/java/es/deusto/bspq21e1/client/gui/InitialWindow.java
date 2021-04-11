package es.deusto.bspq21e1.client.gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import es.deusto.bspq21e1.client.controller.Controller;

/**
 * Window class for the initial point of the program, where users can register or log in.
 * @author SPQ Group 1
 * @version 1.0
 */
public class InitialWindow extends JFrame {

    private static final long serialVersionUID = 1L;
    private Controller controller;

    private JFrame frame = new JFrame();
    private JPanel panel;
    private JButton btnRegister, btnLogin, btnExit;

    /**
	 * Creates the initial window of the program.
     * @param controller Controller used for handling all requests in the system.  
	 */
    public InitialWindow(Controller controller) {
        this.controller = controller;
        frame.setVisible(true);
        initialize();
    }

    /**
	 * Initializes all the elements the window needs to show to the user and their functionality.
	 */
    private void initialize() {
        frame.setBounds(100, 100, 600, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

        panel = new JPanel();
        panel.setBounds(0, 0, 600, 300);
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        btnRegister = new JButton("Register");
        btnRegister.setBounds(225, 50, 150, 25);
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goToRegisterUser();
            }
        } );
        panel.add(btnRegister);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(225, 125, 150, 25);
        panel.add(btnLogin);

        btnExit = new JButton("Exit");
        btnExit.setBounds(225, 200, 150, 25);
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        } );
        panel.add(btnExit);

    }

    /**
     * Closes this window and opens the RegisterUser window.
     */
    private void goToRegisterUser() {
        this.dispose();
        new RegisterUser(controller);
    }
}