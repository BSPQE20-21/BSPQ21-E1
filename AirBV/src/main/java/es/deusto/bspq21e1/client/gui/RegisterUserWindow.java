package es.deusto.bspq21e1.client.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
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
import java.awt.BorderLayout;

public class RegisterUserWindow extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private Controller controller;
	
	private JFrame frmRegisterYourself = new JFrame();

	private JTextField txtIdNumber;
	private JTextField txtName;
	private JTextField txtEmail;
	
	
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
		frmRegisterYourself.setBounds(100, 100, 300, 225);
		frmRegisterYourself.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmRegisterYourself.getContentPane().setLayout(null);
		
		JLabel lblRegisterTitle = new JLabel(controller.getResourcebundle().getString("register_as_new_user_msg"));
		lblRegisterTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegisterTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRegisterTitle.setBounds(0, 22, 294, 19);
		frmRegisterYourself.getContentPane().add(lblRegisterTitle);
		
		//JLabels
		JLabel lblId = new JLabel(controller.getResourcebundle().getString("id_number_msg"));
		lblId.setBounds(56, 63, 73, 19);
		frmRegisterYourself.getContentPane().add(lblId);
		
		JLabel lblName = new JLabel(controller.getResourcebundle().getString("name_msg"));
		lblName.setBounds(56, 90, 52, 14);
		frmRegisterYourself.getContentPane().add(lblName);
		
		JLabel lblEmail = new JLabel(controller.getResourcebundle().getString("email_msg"));
		lblEmail.setBounds(56, 116, 52, 14);
		frmRegisterYourself.getContentPane().add(lblEmail);
		
		//txtFields
		txtIdNumber = new JTextField();
		txtIdNumber.setBounds(164, 62, 86, 20);
		frmRegisterYourself.getContentPane().add(txtIdNumber);
		txtIdNumber.setColumns(10);
		
		txtName = new JTextField();
		txtName.setBounds(164, 87, 86, 20);
		frmRegisterYourself.getContentPane().add(txtName);
		txtName.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(164, 113, 86, 20);
		frmRegisterYourself.getContentPane().add(txtEmail);
		txtEmail.setColumns(10);
		
		//Register Button
		JButton btnRegister = new JButton(controller.getResourcebundle().getString("register_msg"));
		btnRegister.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				
				if(txtIdNumber.getText() != "" && txtName.getText() != "" && txtEmail.getText() != "") {
					controller.registerUsers(txtIdNumber.getText(), txtName.getText(), txtEmail.getText());
				}
				frmRegisterYourself.dispose();
				
				UserData userData = new UserData(txtIdNumber.getText(), txtName.getText(), txtEmail.getText(), new ArrayList<VanData>());
				
				
				new MainWindow(controller, userData);
//				new RegisterVanWindow(controller, userData);
//				new SearchWindow(controller, userData);
//				new CancelReservationWindow(controller, userData);
				
			}
		});
		btnRegister.setBounds(104, 144, 86, 28);
		frmRegisterYourself.getContentPane().add(btnRegister);
		
	}
	
}
