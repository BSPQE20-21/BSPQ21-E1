package es.deusto.bspq21e1.client.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;

import es.deusto.bspq21e1.client.controller.Controller;
import es.deusto.bspq21e1.serialization.UserData;

import javax.swing.JButton;

import org.apache.log4j.Logger;

public class RegisterUserWindow extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(RegisterUserWindow.class.getName());
	private Controller controller;
	
	private JFrame frmRegisterYourself = new JFrame();

	private JTextField txtIdNumber, txtName, txtEmail;
	private JPasswordField passwordField;
	private JButton btnBack, btnRegister;
	
	public RegisterUserWindow(Controller controller) {
		this.controller = controller;
		initialize();
		frmRegisterYourself.setTitle(controller.getResourcebundle().getString("register_yourself_msg"));
		frmRegisterYourself.setResizable(false);
		frmRegisterYourself.setVisible(true);

	}
	
	public RegisterUserWindow() {
		this.controller = null;
		initialize();
		frmRegisterYourself.setTitle(controller.getResourcebundle().getString("register_yourself_msg"));
		frmRegisterYourself.setResizable(false);
		frmRegisterYourself.setVisible(true);

	}
	
	private void initialize() {
		frmRegisterYourself.setBounds(100, 100, 385, 315);
		frmRegisterYourself.setLocationRelativeTo(null);
		frmRegisterYourself.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmRegisterYourself.getContentPane().setLayout(null);
		frmRegisterYourself.setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/resources/images/AirBV.png"));
		
		JLabel lblRegisterTitle = new JLabel(controller.getResourcebundle().getString("register_as_new_user_msg"));
		lblRegisterTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRegisterTitle.setBounds(49, 20, 294, 19);
		frmRegisterYourself.getContentPane().add(lblRegisterTitle);
		
		//JLabels
		JLabel lblId = new JLabel(controller.getResourcebundle().getString("id_number_msg"));
		lblId.setBounds(40, 63, 130, 19);
		frmRegisterYourself.getContentPane().add(lblId);
		
		JLabel lblName = new JLabel(controller.getResourcebundle().getString("name_msg"));
		lblName.setBounds(40, 103, 130, 14);
		frmRegisterYourself.getContentPane().add(lblName);
		
		JLabel lblEmail = new JLabel(controller.getResourcebundle().getString("email_msg"));
		lblEmail.setBounds(40, 141, 130, 14);
		frmRegisterYourself.getContentPane().add(lblEmail);
		
		JLabel lblPassword = new JLabel(controller.getResourcebundle().getString("password_msg"));
		lblPassword.setBounds(40, 178, 130, 14);
		frmRegisterYourself.getContentPane().add(lblPassword);
		
		
		//txtFields
		txtIdNumber = new JTextField();
		txtIdNumber.setBounds(184, 63, 140, 20);
		frmRegisterYourself.getContentPane().add(txtIdNumber);
		txtIdNumber.setColumns(10);
		
		txtName = new JTextField();
		txtName.setBounds(184, 101, 140, 20);
		frmRegisterYourself.getContentPane().add(txtName);
		txtName.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(184, 139, 140, 20);
		frmRegisterYourself.getContentPane().add(txtEmail);
		txtEmail.setColumns(10);
		
		//passwordFields
		passwordField = new JPasswordField();
		passwordField.setBounds(184, 176, 140, 19);
		frmRegisterYourself.getContentPane().add(passwordField);
		
		
		btnBack = new JButton(controller.getResourcebundle().getString("back_button_msg"));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				goToInitialWindow();
			}
		});
		btnBack.addMouseListener(new MouseAdapter() {
        	public void mouseEntered(MouseEvent e) {
        		btnBack.setBounds(28, 229, 150, 29);
        		btnBack.setBackground(Color.RED);
        		btnBack.updateUI();
		    }
		    public void mouseExited(MouseEvent e) {
		    	btnBack.setBounds(40, 229, 125, 25);
		    	btnBack.setBackground(btnRegister.getBackground());
		    	btnBack.updateUI();
		    }
		});
		btnBack.setBounds(40, 229, 125, 25);
		frmRegisterYourself.getContentPane().add(btnBack);
		
		//Register Button
		btnRegister = new JButton(controller.getResourcebundle().getString("register_msg"));
		btnRegister.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				
				if(txtIdNumber.getText() != "" && txtName.getText() != "" && txtEmail.getText() != "") {
					controller.registerUsers(txtIdNumber.getText(), txtName.getText(), txtEmail.getText(), new String( passwordField.getPassword() ) );
				}
				frmRegisterYourself.dispose();
				
				UserData userData = new UserData(txtIdNumber.getText(), txtName.getText(), txtEmail.getText(), new String( passwordField.getPassword() ) );
				
				
				new MainWindow(controller, userData);
				logger.debug("User registered");
			}
		});
		btnRegister.addMouseListener(new MouseAdapter() {
        	public void mouseEntered(MouseEvent e) {
        		btnRegister.setBounds(183, 229, 150, 29);
        		btnRegister.updateUI();
		    }
		    public void mouseExited(MouseEvent e) {
		    	btnRegister.setBounds(195, 229, 125, 25);
		    	btnRegister.updateUI();
		    }
		});
		btnRegister.setBounds(195, 229, 125, 25);
		frmRegisterYourself.getContentPane().add(btnRegister);
		
		logger.info("RegisterUserWindow well initialized");
	}
	
    /**
     * Closes this window and opens the Initial window.
     */
    private void goToInitialWindow() {
        new InitialWindow(controller);
        frmRegisterYourself.dispose();
    }
	
}
