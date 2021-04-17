package es.deusto.bspq21e1.client.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import es.deusto.bspq21e1.serialization.ReviewData;
import es.deusto.bspq21e1.serialization.UserData;
import es.deusto.bspq21e1.serialization.VanData;

import javax.swing.JButton;
import javax.swing.SwingConstants;

public class CharacteristicsWindow extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private VanData van;
	private JFrame searchFrame;
	
	private JFrame frmCharacteristics = new JFrame();
	
	
	public CharacteristicsWindow(VanData van, JFrame searchFrame) {
		if(van == null || van.getBrand() == null) {
			frmCharacteristics.dispose();
			searchFrame.setVisible(true);
		}
		this.van = van;
		this.searchFrame = searchFrame;
		initialize();
		frmCharacteristics.setTitle("Characteristics of the van");
		frmCharacteristics.setResizable(false);
		frmCharacteristics.setVisible(true);

	}
	
	private void initialize() {
		frmCharacteristics.setBounds(100, 100, 300, 355);
		frmCharacteristics.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmCharacteristics.getContentPane().setLayout(null);
		
		JLabel lblCharacteristicsTitle = new JLabel("Characteristics");
		lblCharacteristicsTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblCharacteristicsTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCharacteristicsTitle.setBounds(0, 22, 294, 19);
		frmCharacteristics.getContentPane().add(lblCharacteristicsTitle);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchFrame.setVisible(true);
				frmCharacteristics.dispose();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.ITALIC, 11));
		btnBack.setBounds(102, 292, 89, 23);
		frmCharacteristics.getContentPane().add(btnBack);
		
		JLabel lblLicensePlate = new JLabel("License plate:");
		lblLicensePlate.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLicensePlate.setBounds(30, 52, 89, 14);
		frmCharacteristics.getContentPane().add(lblLicensePlate);
		
		JLabel lblLicensePlateA = new JLabel(van.getLicensePlate());
		lblLicensePlateA.setBounds(175, 52, 89, 14);
		frmCharacteristics.getContentPane().add(lblLicensePlateA);
		
		JLabel lblBrand = new JLabel("Brand:");
		lblBrand.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblBrand.setBounds(30, 77, 89, 14);
		frmCharacteristics.getContentPane().add(lblBrand);
		
		JLabel lblBrandA = new JLabel(van.getBrand());
		lblBrandA.setBounds(175, 77, 89, 14);
		frmCharacteristics.getContentPane().add(lblBrandA);
		
		JLabel lblModel = new JLabel("Model:");
		lblModel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblModel.setBounds(30, 102, 89, 14);
		frmCharacteristics.getContentPane().add(lblModel);
		
		JLabel lblModelA = new JLabel(van.getModel());
		lblModelA.setBounds(175, 102, 89, 14);
		frmCharacteristics.getContentPane().add(lblModelA);
		
		JLabel lblLocation = new JLabel("Location:");
		lblLocation.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLocation.setBounds(30, 127, 89, 14);
		frmCharacteristics.getContentPane().add(lblLocation);
		
		JLabel lblLocationA = new JLabel(van.getLocation());
		lblLocationA.setBounds(175, 127, 89, 14);
		frmCharacteristics.getContentPane().add(lblLocationA);
		
		JLabel lblCapacity = new JLabel("Capacity:");
		lblCapacity.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCapacity.setBounds(30, 152, 89, 14);
		frmCharacteristics.getContentPane().add(lblCapacity);
		
		JLabel lblCapacityA = new JLabel("");
		if(van.getCapacity()>1) {
			lblCapacityA.setText(van.getCapacity() + " people");
		} else {
			lblCapacityA.setText(van.getCapacity() + " person");
		}
		lblCapacityA.setBounds(175, 152, 89, 14);
		frmCharacteristics.getContentPane().add(lblCapacityA);
		
		JLabel lblKitchen = new JLabel("Kitchen?");
		lblKitchen.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblKitchen.setBounds(30, 177, 89, 14);
		frmCharacteristics.getContentPane().add(lblKitchen);
		
		JLabel lblKitchenA = new JLabel("");
		if(van.hasKitchen()) {
			lblKitchenA.setText("YES");
		} else {
			lblKitchenA.setText("NO");
		}
		lblKitchenA.setBounds(175, 177, 89, 14);
		frmCharacteristics.getContentPane().add(lblKitchenA);
		
		JLabel lblShower = new JLabel("Shower?");
		lblShower.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblShower.setBounds(30, 202, 89, 14);
		frmCharacteristics.getContentPane().add(lblShower);
		
		JLabel lblShowerA = new JLabel("");
		if(van.hasShower()) {
			lblShowerA.setText("YES");
		} else {
			lblShowerA.setText("NO");
		}
		lblShowerA.setBounds(175, 202, 89, 14);
		frmCharacteristics.getContentPane().add(lblShowerA);
		
		JLabel lblOffRoad = new JLabel("Off road?");
		lblOffRoad.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblOffRoad.setBounds(30, 227, 89, 14);
		frmCharacteristics.getContentPane().add(lblOffRoad);
		
		JLabel lblOffRoadA = new JLabel("");
		if(van.isOffRoad()) {
			lblOffRoadA.setText("YES");
		} else {
			lblOffRoadA.setText("NO");
		}
		lblOffRoadA.setBounds(175, 222, 89, 14);
		frmCharacteristics.getContentPane().add(lblOffRoadA);
		
		JLabel lblPrice = new JLabel("Price per day:");
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPrice.setBounds(30, 252, 89, 14);
		frmCharacteristics.getContentPane().add(lblPrice);
		
		JLabel lblPriceA = new JLabel(van.getPricePerDay() + "€");
		lblPriceA.setBounds(175, 252, 89, 14);
		frmCharacteristics.getContentPane().add(lblPriceA);
		
	}

}
