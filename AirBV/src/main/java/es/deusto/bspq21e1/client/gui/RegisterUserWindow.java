package es.deusto.bspq21e1.client.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTextField;

import es.deusto.bspq21e1.client.controller.Controller;
import es.deusto.bspq21e1.serialization.UserData;
import es.deusto.bspq21e1.serialization.VanData;

import javax.swing.JButton;
import javax.swing.SwingConstants;

public class RegisterUserWindow extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private Controller controller;
	
	private JFrame frmRegisterYourself = new JFrame();

	private JTextField txtIdNumber;
	private JTextField txtName;
	private JTextField txtEmail;
	private JPasswordField passwordField;
	
	
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
		frmRegisterYourself.setBounds(100, 100, 450, 350);
		frmRegisterYourself.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmRegisterYourself.getContentPane().setLayout(null);
		
		JLabel lblRegisterTitle = new JLabel(controller.getResourcebundle().getString("register_as_new_user_msg"));
		lblRegisterTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegisterTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRegisterTitle.setBounds(56, 20, 294, 19);
		frmRegisterYourself.getContentPane().add(lblRegisterTitle);
		
		//JLabels
		JLabel lblId = new JLabel(controller.getResourcebundle().getString("id_number_msg"));
		lblId.setBounds(56, 63, 73, 19);
		frmRegisterYourself.getContentPane().add(lblId);
		
		JLabel lblName = new JLabel(controller.getResourcebundle().getString("name_msg"));
		lblName.setBounds(56, 103, 52, 14);
		frmRegisterYourself.getContentPane().add(lblName);
		
		JLabel lblEmail = new JLabel(controller.getResourcebundle().getString("email_msg"));
		lblEmail.setBounds(56, 141, 52, 14);
		frmRegisterYourself.getContentPane().add(lblEmail);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(56, 178, 52, 14);
		frmRegisterYourself.getContentPane().add(lblPassword);
		
		
		//txtFields
		txtIdNumber = new JTextField();
		txtIdNumber.setBounds(164, 63, 126, 20);
		frmRegisterYourself.getContentPane().add(txtIdNumber);
		txtIdNumber.setColumns(10);
		
		txtName = new JTextField();
		txtName.setBounds(164, 101, 126, 20);
		frmRegisterYourself.getContentPane().add(txtName);
		txtName.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(164, 139, 126, 20);
		frmRegisterYourself.getContentPane().add(txtEmail);
		txtEmail.setColumns(10);
		
		//passwordFields
		passwordField = new JPasswordField();
		passwordField.setBounds(164, 176, 126, 19);
		frmRegisterYourself.getContentPane().add(passwordField);
		
		//Register Button
		JButton btnRegister = new JButton(controller.getResourcebundle().getString("register_msg"));
		btnRegister.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				
				if(txtIdNumber.getText() != "" && txtName.getText() != "" && txtEmail.getText() != "") {
					controller.registerUsers(txtIdNumber.getText(), txtName.getText(), txtEmail.getText(), new String( passwordField.getPassword() ) );
				}
				frmRegisterYourself.dispose();
				
				UserData userData = new UserData(txtIdNumber.getText(), txtName.getText(), txtEmail.getText(), new String( passwordField.getPassword() ) );
				
				
				new MainWindow(controller, userData);
				
			}
		});
		btnRegister.setBounds(104, 144, 86, 28);
		frmRegisterYourself.getContentPane().add(btnRegister);
		
	}
	
}
