package es.deusto.bspq21e1.client.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import es.deusto.bspq21e1.client.controller.Controller;
import es.deusto.bspq21e1.serialization.UserData;
import javax.swing.JPasswordField;

/**
 * Window for the user to log in to the application
 * @author BSPQ21-E1
 *
 */
public class LogInWindow extends JFrame{

	private static final long serialVersionUID = 1L;
	private Controller controller;
	
	private JFrame frmLogIn = new JFrame();
	
	private JLabel lblTitle;
	
	private JLabel lblEmail;
	private JTextField txtFieldEmail;
	
	private JLabel lblPassword;
	private JPasswordField passwordField;
	
	private JButton btnBack;
	

	/**
	 * Create the application.
	 */
	public LogInWindow(Controller controller) {
		this.controller = controller;
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
		frmLogIn.setLocationRelativeTo(null);
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
		
		passwordField = new JPasswordField();
		passwordField.setBounds(200, 117, 205, 19);
		frmLogIn.getContentPane().add(passwordField);


		//BUTTON: Log In
		JButton btnRegisterVan = new JButton(controller.getResourcebundle().getString("login_msg"));
		btnRegisterVan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
				UserData userData = controller.loginUser( txtFieldEmail.getText(), new String(passwordField.getPassword()) );
				if (userData != null)  {
					frmLogIn.dispose();
					new MainWindow(controller, userData);
				} else {
					txtFieldEmail.setText("Wrong credentials");
					txtFieldEmail.updateUI();
					passwordField.updateUI();
				}
					
			}
		});
		btnRegisterVan.setBounds(152, 170, 147, 23);
		frmLogIn.getContentPane().add(btnRegisterVan);
		
		//BUTTON: Back
		btnBack = new JButton(controller.getResourcebundle().getString("back_button_msg"));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				goToInitialWindow();
		
			}
		});
		btnBack.setBounds(29, 264, 89, 23);
		frmLogIn.getContentPane().add(btnBack);
	
	
	}
	
    /**
     * Closes this window and opens the Initial window.
     */
    private void goToInitialWindow() {
        new InitialWindow(controller);
        frmLogIn.dispose();
    }
}
