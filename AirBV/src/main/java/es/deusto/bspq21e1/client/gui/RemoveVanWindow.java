package es.deusto.bspq21e1.client.gui;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

import es.deusto.bspq21e1.client.controller.Controller;
import es.deusto.bspq21e1.serialization.UserData;
import es.deusto.bspq21e1.serialization.VanData;

public class RemoveVanWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private Controller controller;
	private UserData user;
	
	private JFrame frmRemoveVan = new JFrame();
	
	private JButton btnRemove, btnBack;
	private JList<String> jlVansList = new JList<String>();
	private JScrollPane scrollVans;
	private ArrayList<VanData> vans = new ArrayList<VanData>();
	
	public RemoveVanWindow(Controller controller, UserData user) {
		this.controller = controller;
		this.user = user;
		frmRemoveVan.setTitle("Remove Van");
		frmRemoveVan.setResizable(false);
		frmRemoveVan.setVisible(true);
		initialize();
	}
	
	private void initialize() {
		frmRemoveVan.setBounds(100, 100, 670, 360);
		frmRemoveVan.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmRemoveVan.getContentPane().setLayout(null);
		
		// Initialize buttons and panel for Jlist
	}
}
