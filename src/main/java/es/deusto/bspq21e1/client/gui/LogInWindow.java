package es.deusto.bspq21e1.client.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

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
	private static Logger logger = Logger.getLogger(LogInWindow.class.getName());
	private Controller controller;
	
	private JFrame frmLogIn = new JFrame();
	
	private JLabel lblTitle, lblEmail, lblPassword;
	private JTextField txtFieldEmail;
	private JPasswordField passwordField;
	
	private JButton btnBack, btnLogin;
	

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
		frmLogIn.setBounds(100, 100, 450, 250);
		frmLogIn.setLocationRelativeTo(null);
		frmLogIn.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmLogIn.getContentPane().setLayout(null);
		frmLogIn.setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/resources/images/AirBV.png"));
		
		lblTitle = new JLabel(controller.getResourcebundle().getString("login_window_msg"));
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTitle.setBounds(42, 31, 365, 19);
		frmLogIn.getContentPane().add(lblTitle);
		
		//Email
		lblEmail = new JLabel(controller.getResourcebundle().getString("email_msg"));
		lblEmail.setBounds(40, 81, 121, 19);
		frmLogIn.getContentPane().add(lblEmail);
		
		txtFieldEmail = new JTextField();
		txtFieldEmail.setBounds(200, 79, 205, 24);
		frmLogIn.getContentPane().add(txtFieldEmail);
		txtFieldEmail.setColumns(10);
		
		//Password
		lblPassword = new JLabel(controller.getResourcebundle().getString("password_msg"));
		lblPassword.setBounds(40, 120, 78, 13);
		frmLogIn.getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(202, 115, 205, 24);
		frmLogIn.getContentPane().add(passwordField);
		
		//BUTTON: Back
		btnBack = new JButton(controller.getResourcebundle().getString("back_button_msg"));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goToInitialWindow();
			}
		});
		btnBack.addMouseListener(new MouseAdapter() {
        	public void mouseEntered(MouseEvent e) {
        		btnBack.setBounds(28, 170, 175, 29);
        		btnBack.setBackground(Color.RED);
        		btnBack.updateUI();
		    }
		    public void mouseExited(MouseEvent e) {
		    	btnBack.setBounds(40, 170, 150, 25);
		    	btnBack.setBackground(btnLogin.getBackground());
		    	btnBack.updateUI();
		    }
		});
		btnBack.setBounds(40, 170, 150, 25);
		frmLogIn.getContentPane().add(btnBack);
		
		//BUTTON: Log In
		btnLogin = new JButton(controller.getResourcebundle().getString("login_msg"));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserData userData = controller.loginUser( txtFieldEmail.getText(), new String(passwordField.getPassword()) );
				if (userData != null)  {
					frmLogIn.dispose();
					new MainWindow(controller, userData);
					logger.debug("Login correct");
				} else {
					txtFieldEmail.setText(controller.getResourcebundle().getString("wrong_credentials_msg"));
					txtFieldEmail.updateUI();
					passwordField.updateUI();
					logger.debug("Login wrong");
				}	
			}
		});
		btnLogin.addMouseListener(new MouseAdapter() {
        	public void mouseEntered(MouseEvent e) {
        		btnLogin.setBounds(243, 170, 175, 29);
        		btnLogin.updateUI();
		    }
		    public void mouseExited(MouseEvent e) {
		    	btnLogin.setBounds(255, 170, 150, 25);
		    	btnLogin.updateUI();
		    }
		});
		btnLogin.setBounds(255, 170, 150, 25);
		frmLogIn.getContentPane().add(btnLogin);
	
		logger.info("LogInWindow well initialized");
	}
	
    /**
     * Closes this window and opens the Initial window.
     */
    private void goToInitialWindow() {
        new InitialWindow(controller);
        frmLogIn.dispose();
    }
}
