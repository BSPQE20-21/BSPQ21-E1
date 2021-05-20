package es.deusto.bspq21e1.client.gui;

import java.awt.Font;
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

public class MainWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(MainWindow.class.getName());
	private Controller controller;
	private boolean visible;
	private UserData user;
	
	private JFrame frmMain = new JFrame();
	
	private JButton btnRegisterVan, btnSearchVan, btnCancelRes, btnRemoveVan,
	btnRemoveAccount, btnlogOut;
	private JLabel lblTitle;
	
	public MainWindow(Controller controller, UserData user, boolean visible) {
		this.controller = controller;
		this.visible = visible;
		this.user = user;
		initialize();
		frmMain.setResizable(false);
		frmMain.setVisible(visible);
	}
	
	private void initialize() {
		frmMain.setBounds(300, 100, 260, 410);
		frmMain.setLocationRelativeTo(null);
		frmMain.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmMain.getContentPane().setLayout(null);
		frmMain.setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/resources/images/AirBV.png"));
		
		lblTitle = new JLabel(controller.getResourcebundle().getString("main_title_msg"));
		lblTitle.setBounds(40, 20, 300, 30);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
		frmMain.getContentPane().add(lblTitle);
		
		btnSearchVan = new JButton(controller.getResourcebundle().getString("search_btn_msg"));
		btnSearchVan.setBounds(35, 70, 175, 25);
		btnSearchVan.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frmMain.setVisible(false);
				new SearchWindow(controller, user, frmMain, visible);
			}
		});
		btnSearchVan.addMouseListener(new MouseAdapter() {
        	public void mouseEntered(MouseEvent e) {
        		btnSearchVan.setBounds(23, 70, 200, 29);
        		btnSearchVan.updateUI();
		    }
		    public void mouseExited(MouseEvent e) {
		    	btnSearchVan.setBounds(35, 70, 175, 25);
		    	btnSearchVan.updateUI();
		    }
		});
		frmMain.getContentPane().add(btnSearchVan);
		
		btnRegisterVan = new JButton(controller.getResourcebundle().getString("register_btn_msg"));
		btnRegisterVan.setBounds(35, 120, 175, 25);
		btnRegisterVan.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frmMain.setVisible(false);
				new RegisterVanWindow(controller, user, frmMain, visible);
			}
		});
		btnRegisterVan.addMouseListener(new MouseAdapter() {
        	public void mouseEntered(MouseEvent e) {
        		btnRegisterVan.setBounds(23, 120, 200, 29);
        		btnRegisterVan.updateUI();
		    }
		    public void mouseExited(MouseEvent e) {
		    	btnRegisterVan.setBounds(35, 120, 175, 25);
		    	btnRegisterVan.updateUI();
		    }
		});
		frmMain.getContentPane().add(btnRegisterVan);
		
		btnCancelRes = new JButton(controller.getResourcebundle().getString("cancel_btn_msg"));
		btnCancelRes.setBounds(35, 170, 175, 25);
		btnCancelRes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frmMain.setVisible(false);
				new CancelReservationWindow(controller, user, frmMain, visible);
			}
		});
		btnCancelRes.addMouseListener(new MouseAdapter() {
        	public void mouseEntered(MouseEvent e) {
        		btnCancelRes.setBounds(23, 170, 200, 29);
        		btnCancelRes.updateUI();
		    }
		    public void mouseExited(MouseEvent e) {
		    	btnCancelRes.setBounds(35, 170, 175, 25);
		    	btnCancelRes.updateUI();
		    }
		});
		frmMain.getContentPane().add(btnCancelRes);
		
		btnRemoveVan = new JButton(controller.getResourcebundle().getString("remove_van_btn_msg"));
		btnRemoveVan.setBounds(35, 220, 175, 25);
		btnRemoveVan.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frmMain.setVisible(false);
				new RemoveVanWindow(controller, user, frmMain, visible);
			}
		});
		btnRemoveVan.addMouseListener(new MouseAdapter() {
        	public void mouseEntered(MouseEvent e) {
        		btnRemoveVan.setBounds(23, 220, 200, 29);
        		btnRemoveVan.updateUI();
		    }
		    public void mouseExited(MouseEvent e) {
		    	btnRemoveVan.setBounds(35, 220, 175, 25);
		    	btnRemoveVan.updateUI();
		    }
		});
		frmMain.getContentPane().add(btnRemoveVan);
		
		btnRemoveAccount = new JButton(controller.getResourcebundle().getString("remove_user_btn_msg"));
		btnRemoveAccount.setBounds(35, 270, 175, 25);
		btnRemoveAccount.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frmMain.setVisible(false);
				new RemoveAccountWindow(controller, user, frmMain, visible);
			}
		});
		btnRemoveAccount.addMouseListener(new MouseAdapter() {
        	public void mouseEntered(MouseEvent e) {
        		btnRemoveAccount.setBounds(23, 270, 200, 29);
        		btnRemoveAccount.updateUI();
		    }
		    public void mouseExited(MouseEvent e) {
		    	btnRemoveAccount.setBounds(35, 270, 175, 25);
		    	btnRemoveAccount.updateUI();
		    }
		});
		frmMain.getContentPane().add(btnRemoveAccount);
		
		btnlogOut = new JButton(controller.getResourcebundle().getString("logout_btn_msg"));
		btnlogOut.setBounds(35, 325, 175, 25);
		btnlogOut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new InitialWindow(controller, visible);
				frmMain.dispose();
			}
		});
		btnlogOut.addMouseListener(new MouseAdapter() {
        	public void mouseEntered(MouseEvent e) {
        		btnlogOut.setBounds(23, 325, 200, 29);
        		btnlogOut.setBackground(java.awt.Color.RED);
        		btnlogOut.updateUI();
		    }
		    public void mouseExited(MouseEvent e) {
		    	btnlogOut.setBounds(35, 325, 175, 25);
		    	btnlogOut.setBackground(btnCancelRes.getBackground());
		    	btnlogOut.updateUI();
		    }
		});
		frmMain.getContentPane().add(btnlogOut);
		
		logger.info("MainWindow well initialized");
	}

	public boolean isVisible() {
		return visible;
	}

	public UserData getUser() {
		return user;
	}

	public JFrame getFrmMain() {
		return frmMain;
	}

	public JButton getBtnRegisterVan() {
		return btnRegisterVan;
	}

	public JButton getBtnSearchVan() {
		return btnSearchVan;
	}

	public JButton getBtnCancelRes() {
		return btnCancelRes;
	}

	public JButton getBtnRemoveVan() {
		return btnRemoveVan;
	}

	public JButton getBtnRemoveAccount() {
		return btnRemoveAccount;
	}

	public JButton getBtnlogOut() {
		return btnlogOut;
	}

	public JLabel getLblTitle() {
		return lblTitle;
	}
	
}