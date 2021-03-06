package es.deusto.bspq21e1.client.gui;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.apache.log4j.Logger;

import es.deusto.bspq21e1.client.controller.Controller;
import es.deusto.bspq21e1.serialization.UserData;

public class RemoveAccountWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(RemoveAccountWindow.class.getName());
	private Controller controller;
	private boolean visible;
	private UserData userData;
	private JFrame frmMain;
	
	private JFrame frmRemoveAccount = new JFrame();
	private JButton btnYes, btnNo;
	private JLabel lblText;
	
	public RemoveAccountWindow(Controller controller, UserData userData, JFrame frmMain, boolean visible) {
		this.controller = controller;
		this.userData = userData;
		this.visible = visible;
		this.frmMain = frmMain;
		initialize();
		frmRemoveAccount.setTitle(controller.getResourcebundle().getString("remove_user_btn_msg"));
		frmRemoveAccount.setResizable(false);
		frmRemoveAccount.setVisible(visible);
	}
	
	private void initialize() {
		frmRemoveAccount.setBounds(400, 200, 340, 130);
		frmRemoveAccount.setLocationRelativeTo(null);
		frmRemoveAccount.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmRemoveAccount.getContentPane().setLayout(null);
		frmRemoveAccount.setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/resources/images/AirBV.png"));
		
		btnNo = new JButton(controller.getResourcebundle().getString("no_msg"));
		btnNo.setBounds(25, 50, 100, 25);
		btnNo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frmMain.setVisible(visible);
				frmRemoveAccount.dispose();
			}
		});
		btnNo.addMouseListener(new MouseAdapter() {
        	public void mouseEntered(MouseEvent e) {
        		btnNo.setBounds(20, 50, 110, 26);
        		btnNo.updateUI();
		    }
		    public void mouseExited(MouseEvent e) {
		    	btnNo.setBounds(25, 50, 100, 25);
		    	btnNo.updateUI();
		    }
		});
		frmRemoveAccount.getContentPane().add(btnNo);
		
		btnYes = new JButton(controller.getResourcebundle().getString("yes_msg"));
		btnYes.setBounds(200, 50, 100, 25);
		btnYes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.eraseUser( userData.getDni() );
				new InitialWindow(controller, visible);
				frmRemoveAccount.dispose();
			}
		});
		btnYes.addMouseListener(new MouseAdapter() {
        	public void mouseEntered(MouseEvent e) {
        		btnYes.setBounds(195, 50, 110, 26);
        		btnYes.setBackground(Color.RED);
        		btnYes.updateUI();
		    }
		    public void mouseExited(MouseEvent e) {
		    	btnYes.setBounds(200, 50, 100, 25);
		    	btnYes.setBackground(btnNo.getBackground());
		    	btnYes.updateUI();
		    }
		});
		frmRemoveAccount.getContentPane().add(btnYes);
		
		lblText = new JLabel(controller.getResourcebundle().getString("ensure_remove_msg"));
		lblText.setBounds(25, 5, 350, 35);
		frmRemoveAccount.getContentPane().add(lblText);
		
		logger.info("RemoveAccountWindow well initialized");
	}

	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		RemoveAccountWindow.logger = logger;
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

	public UserData getUserData() {
		return userData;
	}

	public void setUserData(UserData userData) {
		this.userData = userData;
	}

	public JFrame getFrmMain() {
		return frmMain;
	}

	public void setFrmMain(JFrame frmMain) {
		this.frmMain = frmMain;
	}

	public JFrame getFrmRemoveAccount() {
		return frmRemoveAccount;
	}

	public void setFrmRemoveAccount(JFrame frmRemoveAccount) {
		this.frmRemoveAccount = frmRemoveAccount;
	}

	public JButton getBtnYes() {
		return btnYes;
	}

	public void setBtnYes(JButton btnYes) {
		this.btnYes = btnYes;
	}

	public JButton getBtnNo() {
		return btnNo;
	}

	public void setBtnNo(JButton btnNo) {
		this.btnNo = btnNo;
	}

	public JLabel getLblText() {
		return lblText;
	}

	public void setLblText(JLabel lblText) {
		this.lblText = lblText;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
