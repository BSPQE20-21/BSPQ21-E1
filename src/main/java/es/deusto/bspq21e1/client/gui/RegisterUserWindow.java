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
	private boolean visible;
	
	private JFrame frmRegisterYourself = new JFrame();

	private JTextField txtIdNumber, txtName, txtEmail;
	private JPasswordField passwordField;
	private JButton btnBack, btnRegister;
	private JLabel lblRegisterTitle;
	private JLabel lblId;
	private JLabel lblName;
	private JLabel lblEmail;
	private JLabel lblPassword;
	
	public RegisterUserWindow(Controller controller, boolean visible) {
		this.controller = controller;
		this.visible = visible;
		initialize();
		frmRegisterYourself.setTitle(controller.getResourcebundle().getString("register_yourself_msg"));
		frmRegisterYourself.setResizable(false);
		frmRegisterYourself.setVisible(visible);

	}
	
	public RegisterUserWindow(boolean visible) {
		this.controller = null;
		this.visible = visible;
		initialize();
		frmRegisterYourself.setTitle(controller.getResourcebundle().getString("register_yourself_msg"));
		frmRegisterYourself.setResizable(false);
		frmRegisterYourself.setVisible(visible);

	}
	
	private void initialize() {
		frmRegisterYourself.setBounds(100, 100, 385, 315);
		frmRegisterYourself.setLocationRelativeTo(null);
		frmRegisterYourself.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmRegisterYourself.getContentPane().setLayout(null);
		frmRegisterYourself.setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/resources/images/AirBV.png"));
		
		//JLabels
		lblRegisterTitle = new JLabel(controller.getResourcebundle().getString("register_as_new_user_msg"));
		lblRegisterTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRegisterTitle.setBounds(49, 20, 294, 19);
		frmRegisterYourself.getContentPane().add(lblRegisterTitle);
		
		lblId = new JLabel(controller.getResourcebundle().getString("id_number_msg"));
		lblId.setBounds(40, 63, 130, 19);
		frmRegisterYourself.getContentPane().add(lblId);
		
		lblName = new JLabel(controller.getResourcebundle().getString("name_msg"));
		lblName.setBounds(40, 103, 130, 14);
		frmRegisterYourself.getContentPane().add(lblName);
		
		lblEmail = new JLabel(controller.getResourcebundle().getString("email_msg"));
		lblEmail.setBounds(40, 141, 130, 14);
		frmRegisterYourself.getContentPane().add(lblEmail);
		
		lblPassword = new JLabel(controller.getResourcebundle().getString("password_msg"));
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
		
		//Back Button
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
				
				
				new MainWindow(controller, userData, visible);
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
        new InitialWindow(controller, visible);
        frmRegisterYourself.dispose();
    }

	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		RegisterUserWindow.logger = logger;
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public JFrame getFrmRegisterYourself() {
		return frmRegisterYourself;
	}

	public void setFrmRegisterYourself(JFrame frmRegisterYourself) {
		this.frmRegisterYourself = frmRegisterYourself;
	}

	public JTextField getTxtIdNumber() {
		return txtIdNumber;
	}

	public void setTxtIdNumber(JTextField txtIdNumber) {
		this.txtIdNumber = txtIdNumber;
	}

	public JTextField getTxtName() {
		return txtName;
	}

	public void setTxtName(JTextField txtName) {
		this.txtName = txtName;
	}

	public JTextField getTxtEmail() {
		return txtEmail;
	}

	public void setTxtEmail(JTextField txtEmail) {
		this.txtEmail = txtEmail;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public void setPasswordField(JPasswordField passwordField) {
		this.passwordField = passwordField;
	}

	public JButton getBtnBack() {
		return btnBack;
	}

	public void setBtnBack(JButton btnBack) {
		this.btnBack = btnBack;
	}

	public JButton getBtnRegister() {
		return btnRegister;
	}

	public void setBtnRegister(JButton btnRegister) {
		this.btnRegister = btnRegister;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JLabel getLblRegisterTitle() {
		return lblRegisterTitle;
	}

	public void setLblRegisterTitle(JLabel lblRegisterTitle) {
		this.lblRegisterTitle = lblRegisterTitle;
	}

	public JLabel getLblId() {
		return lblId;
	}

	public void setLblId(JLabel lblId) {
		this.lblId = lblId;
	}

	public JLabel getLblName() {
		return lblName;
	}

	public void setLblName(JLabel lblName) {
		this.lblName = lblName;
	}

	public JLabel getLblEmail() {
		return lblEmail;
	}

	public void setLblEmail(JLabel lblEmail) {
		this.lblEmail = lblEmail;
	}

	public JLabel getLblPassword() {
		return lblPassword;
	}

	public void setLblPassword(JLabel lblPassword) {
		this.lblPassword = lblPassword;
	}
	
}
