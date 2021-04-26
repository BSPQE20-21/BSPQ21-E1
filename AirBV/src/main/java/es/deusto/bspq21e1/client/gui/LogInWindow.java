package es.deusto.bspq21e1.client.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import es.deusto.bspq21e1.client.controller.Controller;

public class LogInWindow extends JFrame{

	private static final long serialVersionUID = 1L;
	private Controller controller;
	
	private JFrame frmLogIn = new JFrame();
	
	private JLabel lblTitle;
	
	private JLabel lblEmail;
	private JTextField txtFieldEmail;
	
	private JLabel lblPassword;
	private JTextField txtFieldPassword;
	
	private JButton btnMenu;
	

	/**
	 * Create the application.
	 */
	public LogInWindow(Controller controller) {
		getContentPane().setLayout(null);
		initialize();
		frmLogIn.setTitle(controller.getResourcebundle().getString("login_msg"));
		frmLogIn.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogIn.setResizable(false);
		frmLogIn.setBounds(100, 100, 450, 350);
		frmLogIn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogIn.getContentPane().setLayout(null);
		
		lblTitle = new JLabel(controller.getResourcebundle().getString("login_window_msg"));
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTitle.setBounds(40, 31, 365, 19);
		frmLogIn.getContentPane().add(lblTitle);
		
		//Email
		lblEmail = new JLabel(controller.getResourcebundle().getString("email_msg"));
		lblEmail.setBounds(40, 81, 121, 19);
		frmLogIn.getContentPane().add(lblEmail);
		
		txtFieldEmail = new JTextField();
		txtFieldEmail.setBounds(200, 81, 205, 19);
		frmLogIn.getContentPane().add(txtFieldEmail);
		txtFieldEmail.setColumns(10);
		
		//Password
		lblPassword = new JLabel(controller.getResourcebundle().getString("password_msg"));
		lblPassword.setBounds(40, 120, 78, 13);
		frmLogIn.getContentPane().add(lblPassword);
		
		txtFieldPassword = new JTextField(); //TODO
		txtFieldPassword.setBounds(200, 117, 205, 19);
		frmLogIn.getContentPane().add(txtFieldPassword);
		txtFieldPassword.setColumns(10);


		//BUTTON: Log In
		JButton btnRegisterVan = new JButton(controller.getResourcebundle().getString("login_msg"));
		btnRegisterVan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				//CONTROLLER //TODO
					// Do verification of credentials
//				new MainWindow(controller, userData);
			}
		});
		btnRegisterVan.setBounds(152, 170, 147, 23);
		frmLogIn.getContentPane().add(btnRegisterVan);
		
		//BUTTON: Menu
		btnMenu = new JButton(controller.getResourcebundle().getString("menu_msg"));
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				goToInitialWindow();
		
			}
		});
		btnMenu.setBounds(29, 264, 89, 23);
		frmLogIn.getContentPane().add(btnMenu);
	
		
	}
	
    /**
     * Closes this window and opens the Initial window.
     */
    private void goToInitialWindow() {
        new InitialWindow(controller);
        frmLogIn.dispose();
    }
	

}