package es.deusto.bspq21e1.client.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import es.deusto.bspq21e1.client.controller.Controller;
import es.deusto.bspq21e1.serialization.UserData;
import es.deusto.bspq21e1.serialization.VanData;

public class RemoveVanWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private Controller controller;
	private UserData user;
	private JFrame frmMain;
	
	private JFrame frmRemoveVan = new JFrame();
	
	private JLabel lblText;
	private JButton btnRemove, btnBack;
	private JList<String> jlVansList;
	private JScrollPane scrollVans;
	private ArrayList<VanData> vans = new ArrayList<VanData>();
	private DefaultListModel<String> vansList = new DefaultListModel<String>();
	
	public RemoveVanWindow(Controller controller, UserData user, JFrame frmMain) {
		this.controller = controller;
		this.user = user;
		this.frmMain = frmMain;
		frmRemoveVan.setTitle("Remove Van");
		frmRemoveVan.setResizable(false);
		frmRemoveVan.setVisible(true);
		initialize();
	}
	
	public RemoveVanWindow() {
		frmRemoveVan.setTitle("Remove Van");
		frmRemoveVan.setResizable(false);
		frmRemoveVan.setVisible(true);
		initialize();
	}
	
	private void initialize() {
		frmRemoveVan.setBounds(50, 50, 470, 500);
		frmRemoveVan.setLocationRelativeTo(null);
		frmRemoveVan.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRemoveVan.getContentPane().setLayout(null);
		
		jlVansList = new JList<String>();
		jlVansList.setModel(vansList);
		jlVansList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		scrollVans = new JScrollPane();
		scrollVans.setBounds(25, 80, 405, 320);
		scrollVans.setViewportView(jlVansList);
		frmRemoveVan.getContentPane().add(scrollVans);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(25, 420, 100, 25);
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frmMain.setVisible(true);
				frmRemoveVan.dispose();
			}
		});
		frmRemoveVan.getContentPane().add(btnBack);
		btnBack.updateUI();
		
		btnRemove = new JButton("Remove");
		btnRemove.setBounds(330, 420, 100, 25);
		btnRemove.setBackground(java.awt.Color.RED);
		btnRemove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VanData van = vans.get(jlVansList.getSelectedIndex());
				// HACER AQUÍ EL CÓDIGO PARA ELIMINAR LA VAN
				
				// HACER AQUÍ EL CÓDIGO PARA ELIMINAR LA VAN
				frmMain.setVisible(true);
				frmRemoveVan.dispose();
			}
		});
		frmRemoveVan.getContentPane().add(btnRemove);
		btnRemove.updateUI();
		
		lblText = new JLabel("Choose the van to remove from this list:");
		lblText.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblText.setBounds(43, 10, 580, 50);
		frmRemoveVan.getContentPane().add(lblText);
		lblText.updateUI();
		
	}
}
