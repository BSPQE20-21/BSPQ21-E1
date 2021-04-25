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
	
	private void initialize() { // Falta ajustar posiciones y tamaños, y hacer los metodos que lleven a las otras ventanas
		frmMain.setBounds(100, 100, 500, 500);
		frmMain.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmMain.getContentPane().setLayout(null);
		
		btnSearchVan = new JButton("Search vans");
		btnSearchVan.setBounds(100, 100, 125, 25);
		frmMain.getContentPane().add(btnSearchVan);
		
		btnRegisterVan = new JButton("Register van");
		btnRegisterVan.setBounds(300, 100, 125, 25);
		frmMain.getContentPane().add(btnRegisterVan);
		
		btnCancelRes = new JButton("Cancel reservation");
		btnCancelRes.setBounds(100, 250, 125, 25);
		frmMain.getContentPane().add(btnCancelRes);
		
		btnRemoveVan = new JButton("Remove van");
		btnRemoveVan.setBounds(300, 250, 125, 25);
		frmMain.getContentPane().add(btnRemoveVan);
		
		btnRemoveAccount = new JButton("Remove account");
		btnRemoveAccount.setBounds(100, 400, 125, 25);
		frmMain.getContentPane().add(btnRemoveAccount);
		
		btnlogOut = new JButton("Log out");
		btnlogOut.setBackground(java.awt.Color.RED);
		btnlogOut.setBounds(300, 400, 125, 25);
		frmMain.getContentPane().add(btnlogOut);
	}
	
}