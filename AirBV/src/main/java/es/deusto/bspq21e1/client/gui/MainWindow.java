package es.deusto.bspq21e1.client.gui;

import javax.swing.JButton;
import javax.swing.JFrame;

import es.deusto.bspq21e1.client.controller.Controller;
import es.deusto.bspq21e1.serialization.UserData;

public class MainWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private Controller controller;
	private UserData user;
	
	private JFrame frmMain = new JFrame();
	
	private JButton btnRegisterVan, btnSearchVan, btnCancelRes, btnRemoveVan,
	btnRemoveAccount, btnlogOut;
	
	public MainWindow(Controller controller, UserData user) {
		this.controller = controller;
		this.user = user;
		initialize();
		frmMain.setTitle("Main Window");
		frmMain.setResizable(false);
		frmMain.setVisible(true);
	}
	
	public MainWindow() {
		initialize();
		frmMain.setTitle("Main Window");
		frmMain.setResizable(false);
		frmMain.setVisible(true);
	}
	
	private void initialize() { // Falta ajustar posiciones y tamaños, y hacer los metodos que lleven a las otras ventanas
		frmMain.setBounds(300, 100, 400, 400);
		frmMain.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmMain.getContentPane().setLayout(null);
		
		btnSearchVan = new JButton("Search vans");
		btnSearchVan.setBounds(110, 25, 175, 25);
		frmMain.getContentPane().add(btnSearchVan);
		
		btnRegisterVan = new JButton("Register van");
		btnRegisterVan.setBounds(110, 75, 175, 25);
		frmMain.getContentPane().add(btnRegisterVan);
		
		btnCancelRes = new JButton("Cancel reservation");
		btnCancelRes.setBounds(110, 125, 175, 25);
		frmMain.getContentPane().add(btnCancelRes);
		
		btnRemoveVan = new JButton("Remove van");
		btnRemoveVan.setBounds(110, 175, 175, 25);
		frmMain.getContentPane().add(btnRemoveVan);
		
		btnRemoveAccount = new JButton("Remove account");
		btnRemoveAccount.setBounds(110, 225, 175, 25);
		frmMain.getContentPane().add(btnRemoveAccount);
		
		btnlogOut = new JButton("Log out");
		btnlogOut.setBackground(java.awt.Color.RED);
		btnlogOut.setBounds(110, 325, 175, 25);
		frmMain.getContentPane().add(btnlogOut);
	}
	
}