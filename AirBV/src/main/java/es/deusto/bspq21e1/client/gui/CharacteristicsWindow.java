package es.deusto.bspq21e1.client.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import es.deusto.bspq21e1.client.controller.Controller;
import es.deusto.bspq21e1.serialization.ReviewData;
import es.deusto.bspq21e1.serialization.UserData;
import es.deusto.bspq21e1.serialization.VanData;

import javax.swing.JButton;
import javax.swing.SwingConstants;

public class CharacteristicsWindow extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private Controller controller;
	private VanData van;
	private JFrame searchFrame;
	
	private JFrame frmCharacteristics = new JFrame();
	
	
	public CharacteristicsWindow(Controller controller, VanData van, JFrame searchFrame) {
		this.controller = controller;
		if(van == null || van.getBrand() == null) {
			frmCharacteristics.dispose();
			searchFrame.setVisible(true);
		}
		this.van = van;
		this.searchFrame = searchFrame;
		initialize();
		frmCharacteristics.setTitle(controller.getResourcebundle().getString("characterictics_window_tittle_msg"));
		frmCharacteristics.setResizable(false);
		frmCharacteristics.setVisible(true);

	}
	
	private void initialize() {
		frmCharacteristics.setBounds(100, 100, 300, 355);
		frmCharacteristics.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCharacteristics.getContentPane().setLayout(null);
		
		JLabel lblCharacteristicsTitle = new JLabel(controller.getResourcebundle().getString("characteristics_msg"));
		lblCharacteristicsTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblCharacteristicsTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCharacteristicsTitle.setBounds(0, 22, 294, 19);
		frmCharacteristics.getContentPane().add(lblCharacteristicsTitle);
		
		JButton btnBack = new JButton(controller.getResourcebundle().getString("back_button_msg"));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchFrame.setVisible(true);
				frmCharacteristics.dispose();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.ITALIC, 11));
		btnBack.setBounds(101, 277, 89, 23);
		frmCharacteristics.getContentPane().add(btnBack);
		
		JLabel lblLicensePlate = new JLabel(controller.getResourcebundle().getString("license_plate_msg"));
		lblLicensePlate.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLicensePlate.setBounds(30, 52, 89, 14);
		frmCharacteristics.getContentPane().add(lblLicensePlate);
		
		JLabel lblLicensePlateA = new JLabel(van.getLicensePlate());
		lblLicensePlateA.setBounds(175, 52, 89, 14);
		frmCharacteristics.getContentPane().add(lblLicensePlateA);
		
		JLabel lblBrand = new JLabel(controller.getResourcebundle().getString("brand_msg"));
		lblBrand.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblBrand.setBounds(30, 77, 89, 14);
		frmCharacteristics.getContentPane().add(lblBrand);
		
		JLabel lblBrandA = new JLabel(van.getBrand());
		lblBrandA.setBounds(175, 77, 89, 14);
		frmCharacteristics.getContentPane().add(lblBrandA);
		
		JLabel lblModel = new JLabel(controller.getResourcebundle().getString("model_msg"));
		lblModel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblModel.setBounds(30, 102, 89, 14);
		frmCharacteristics.getContentPane().add(lblModel);
		
		JLabel lblModelA = new JLabel(van.getModel());
		lblModelA.setBounds(175, 102, 89, 14);
		frmCharacteristics.getContentPane().add(lblModelA);
		
		JLabel lblLocation = new JLabel(controller.getResourcebundle().getString("location_msg"));
		lblLocation.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLocation.setBounds(30, 127, 89, 14);
		frmCharacteristics.getContentPane().add(lblLocation);
		
		JLabel lblLocationA = new JLabel(van.getLocation());
		lblLocationA.setBounds(175, 127, 89, 14);
		frmCharacteristics.getContentPane().add(lblLocationA);
		
		JLabel lblCapacity = new JLabel(controller.getResourcebundle().getString("capacity_msg"));
		lblCapacity.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCapacity.setBounds(30, 152, 89, 14);
		frmCharacteristics.getContentPane().add(lblCapacity);
		
		JLabel lblCapacityA = new JLabel("");
		if(van.getCapacity()>1) {
			lblCapacityA.setText(van.getCapacity() + " " + controller.getResourcebundle().getString("people_msg"));
		} else {
			lblCapacityA.setText(van.getCapacity() + " " + controller.getResourcebundle().getString("person_msg"));
		}
		lblCapacityA.setBounds(175, 152, 89, 14);
		frmCharacteristics.getContentPane().add(lblCapacityA);
		
		JLabel lblKitchen = new JLabel(controller.getResourcebundle().getString("kitchen_question__mark_msg"));
		lblKitchen.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblKitchen.setBounds(30, 177, 89, 14);
		frmCharacteristics.getContentPane().add(lblKitchen);
		
		JLabel lblKitchenA = new JLabel("");
		if(van.hasKitchen()) {
			lblKitchenA.setText(controller.getResourcebundle().getString("yes_msg"));
		} else {
			lblKitchenA.setText(controller.getResourcebundle().getString("no_msg"));
		}
		lblKitchenA.setBounds(175, 177, 89, 14);
		frmCharacteristics.getContentPane().add(lblKitchenA);
		
		JLabel lblShower = new JLabel(controller.getResourcebundle().getString("shower_question_mark_msg"));
		lblShower.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblShower.setBounds(30, 202, 89, 14);
		frmCharacteristics.getContentPane().add(lblShower);
		
		JLabel lblShowerA = new JLabel("");
		if(van.hasShower()) {
			lblShowerA.setText(controller.getResourcebundle().getString("yes_msg"));
		} else {
			lblShowerA.setText(controller.getResourcebundle().getString("no_msg"));
		}
		lblShowerA.setBounds(175, 202, 89, 14);
		frmCharacteristics.getContentPane().add(lblShowerA);
		
		JLabel lblOffRoad = new JLabel(controller.getResourcebundle().getString("off_road_question_mark_msg"));
		lblOffRoad.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblOffRoad.setBounds(30, 227, 89, 14);
		frmCharacteristics.getContentPane().add(lblOffRoad);
		
		JLabel lblOffRoadA = new JLabel("");
		if(van.isOffRoad()) {
			lblOffRoadA.setText(controller.getResourcebundle().getString("yes_msg"));
		} else {
			lblOffRoadA.setText(controller.getResourcebundle().getString("no_msg"));
		}
		lblOffRoadA.setBounds(175, 222, 89, 14);
		frmCharacteristics.getContentPane().add(lblOffRoadA);
		
		JLabel lblPrice = new JLabel(controller.getResourcebundle().getString("price_per_day_msg"));
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPrice.setBounds(30, 252, 89, 14);
		frmCharacteristics.getContentPane().add(lblPrice);
		
		JLabel lblPriceA = new JLabel(van.getPricePerDay() + " " + controller.getResourcebundle().getString("money_symbol"));
		lblPriceA.setBounds(175, 252, 89, 14);
		frmCharacteristics.getContentPane().add(lblPriceA);
		
	}

}
