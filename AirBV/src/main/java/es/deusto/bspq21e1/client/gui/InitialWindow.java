package es.deusto.bspq21e1.client.gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import es.deusto.bspq21e1.client.controller.Controller;

public class InitialWindow extends JFrame {

    private static final long serialVersionUID = 1L;
    private Controller controller;

    private JFrame frame;
    private JPanel panel;
    private JButton btnRegister, btnLogin, btnExit;

    /**
	 * Create the application.
	 */
    public InitialWindow(Controller controller) {
        this.controller = controller;
        frame.setVisible(true);
        initialize();
    }

    public InitialWindow() {
        frame.setVisible(true);
        initialize();
    }

    /**
	 * Initialize the contents of the frame.
	 */
    private void initialize() {
        frame = new JFrame();
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

    private void goToRegisterUser() {
        this.dispose();
        RegisterUser ru = new RegisterUser(controller);
    }
}