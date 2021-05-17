package es.deusto.bspq21e1.client.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import es.deusto.bspq21e1.client.controller.Controller;
import es.deusto.bspq21e1.serialization.VanData;

import javax.swing.JButton;

import org.apache.log4j.Logger;

public class CharacteristicsWindow extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(CharacteristicsWindow.class.getName());
	private Controller controller;
	private VanData van;
	private JFrame searchFrame;
	
	private JFrame frmCharacteristics = new JFrame();
	
	private JLabel lblCharacteristicsTitle, lblLicensePlate, lblLicensePlateA, lblBrand, lblBrandA, lblModel, lblModelA, 
	lblLocation, lblLocationA, lblCapacity, lblCapacityA, lblKitchen, lblKitchenA, lblShower, lblShowerA, lblOffRoad,
	lblOffRoadA, lblPrice, lblPriceA;
	private JButton btnBack;
	
	
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
		frmCharacteristics.setBounds(100, 100, 290, 355);
		frmCharacteristics.setLocationRelativeTo(null);
		frmCharacteristics.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCharacteristics.getContentPane().setLayout(null);
		frmCharacteristics.setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/resources/images/AirBV.png"));
		
		lblCharacteristicsTitle = new JLabel(controller.getResourcebundle().getString("characteristics_msg"));
		lblCharacteristicsTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCharacteristicsTitle.setBounds(80, 15, 300, 20);
		frmCharacteristics.getContentPane().add(lblCharacteristicsTitle);
		
		btnBack = new JButton(controller.getResourcebundle().getString("back_button_msg"));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchFrame.setVisible(true);
				frmCharacteristics.dispose();
			}
		});
		btnBack.addMouseListener(new MouseAdapter() {
        	public void mouseEntered(MouseEvent e) {
        		btnBack.setBounds(80, 280, 110, 26);
        		btnBack.updateUI();
		    }
		    public void mouseExited(MouseEvent e) {
		    	btnBack.setBounds(85, 280, 100, 25);
		    	btnBack.updateUI();
		    }
		});
		btnBack.setBounds(85, 280, 100, 25);
		frmCharacteristics.getContentPane().add(btnBack);
		
		lblLicensePlate = new JLabel(controller.getResourcebundle().getString("license_plate_msg"));
		lblLicensePlate.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLicensePlate.setBounds(50, 52, 100, 14);
		frmCharacteristics.getContentPane().add(lblLicensePlate);
		
		lblLicensePlateA = new JLabel(van.getLicensePlate());
		lblLicensePlateA.setBounds(180, 52, 100, 14);
		frmCharacteristics.getContentPane().add(lblLicensePlateA);
		
		lblBrand = new JLabel(controller.getResourcebundle().getString("brand_msg"));
		lblBrand.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblBrand.setBounds(50, 77, 100, 14);
		frmCharacteristics.getContentPane().add(lblBrand);
		
		lblBrandA = new JLabel(van.getBrand());
		lblBrandA.setBounds(180, 77, 100, 14);
		frmCharacteristics.getContentPane().add(lblBrandA);
		
		lblModel = new JLabel(controller.getResourcebundle().getString("model_msg"));
		lblModel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblModel.setBounds(50, 102, 100, 14);
		frmCharacteristics.getContentPane().add(lblModel);
		
		lblModelA = new JLabel(van.getModel());
		lblModelA.setBounds(180, 102, 100, 14);
		frmCharacteristics.getContentPane().add(lblModelA);
		
		lblLocation = new JLabel(controller.getResourcebundle().getString("location_msg"));
		lblLocation.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLocation.setBounds(50, 127, 100, 14);
		frmCharacteristics.getContentPane().add(lblLocation);
		
		lblLocationA = new JLabel(van.getLocation());
		lblLocationA.setBounds(180, 127, 100, 14);
		frmCharacteristics.getContentPane().add(lblLocationA);
		
		lblCapacity = new JLabel(controller.getResourcebundle().getString("capacity_msg"));
		lblCapacity.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCapacity.setBounds(50, 152, 100, 14);
		frmCharacteristics.getContentPane().add(lblCapacity);
		
		lblCapacityA = new JLabel("");
		if(van.getCapacity()>1) {
			lblCapacityA.setText(van.getCapacity() + " " + controller.getResourcebundle().getString("people_msg"));
		} else {
			lblCapacityA.setText(van.getCapacity() + " " + controller.getResourcebundle().getString("person_msg"));
		}
		lblCapacityA.setBounds(180, 152, 89, 14);
		frmCharacteristics.getContentPane().add(lblCapacityA);
		
		lblKitchen = new JLabel(controller.getResourcebundle().getString("kitchen_question__mark_msg"));
		lblKitchen.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblKitchen.setBounds(50, 177, 100, 14);
		frmCharacteristics.getContentPane().add(lblKitchen);
		
		lblKitchenA = new JLabel("");
		if(van.hasKitchen()) {
			lblKitchenA.setText(controller.getResourcebundle().getString("yes_msg"));
		} else {
			lblKitchenA.setText(controller.getResourcebundle().getString("no_msg"));
		}
		lblKitchenA.setBounds(180, 177, 89, 14);
		frmCharacteristics.getContentPane().add(lblKitchenA);
		
		lblShower = new JLabel(controller.getResourcebundle().getString("shower_question_mark_msg"));
		lblShower.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblShower.setBounds(50, 202, 100, 14);
		frmCharacteristics.getContentPane().add(lblShower);
		
		lblShowerA = new JLabel("");
		if(van.hasShower()) {
			lblShowerA.setText(controller.getResourcebundle().getString("yes_msg"));
		} else {
			lblShowerA.setText(controller.getResourcebundle().getString("no_msg"));
		}
		lblShowerA.setBounds(180, 202, 89, 14);
		frmCharacteristics.getContentPane().add(lblShowerA);
		
		lblOffRoad = new JLabel(controller.getResourcebundle().getString("off_road_question_mark_msg"));
		lblOffRoad.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblOffRoad.setBounds(50, 227, 100, 14);
		frmCharacteristics.getContentPane().add(lblOffRoad);
		
		lblOffRoadA = new JLabel("");
		if(van.isOffRoad()) {
			lblOffRoadA.setText(controller.getResourcebundle().getString("yes_msg"));
		} else {
			lblOffRoadA.setText(controller.getResourcebundle().getString("no_msg"));
		}
		lblOffRoadA.setBounds(180, 227, 89, 14);
		frmCharacteristics.getContentPane().add(lblOffRoadA);
		
		lblPrice = new JLabel(controller.getResourcebundle().getString("price_per_day_msg"));
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPrice.setBounds(50, 252, 100, 14);
		frmCharacteristics.getContentPane().add(lblPrice);
		
		lblPriceA = new JLabel(van.getPricePerDay() + " " + controller.getResourcebundle().getString("money_symbol"));
		lblPriceA.setBounds(180, 252, 110, 14);
		frmCharacteristics.getContentPane().add(lblPriceA);
		
		logger.info("CharacteristicsWindow well initialized");
	}

	public JFrame getFrmCharacteristics() {
		return frmCharacteristics;
	}

	public JLabel getLblCharacteristicsTitle() {
		return lblCharacteristicsTitle;
	}

	public JLabel getLblLicensePlate() {
		return lblLicensePlate;
	}

	public JLabel getLblLicensePlateA() {
		return lblLicensePlateA;
	}

	public JLabel getLblBrand() {
		return lblBrand;
	}

	public JLabel getLblBrandA() {
		return lblBrandA;
	}

	public JLabel getLblModel() {
		return lblModel;
	}

	public JLabel getLblModelA() {
		return lblModelA;
	}

	public JLabel getLblLocation() {
		return lblLocation;
	}

	public JLabel getLblLocationA() {
		return lblLocationA;
	}

	public JLabel getLblCapacity() {
		return lblCapacity;
	}

	public JLabel getLblCapacityA() {
		return lblCapacityA;
	}

	public JLabel getLblKitchen() {
		return lblKitchen;
	}

	public JLabel getLblKitchenA() {
		return lblKitchenA;
	}

	public JLabel getLblShower() {
		return lblShower;
	}

	public JLabel getLblShowerA() {
		return lblShowerA;
	}

	public JLabel getLblOffRoad() {
		return lblOffRoad;
	}

	public JLabel getLblOffRoadA() {
		return lblOffRoadA;
	}

	public JLabel getLblPrice() {
		return lblPrice;
	}

	public JLabel getLblPriceA() {
		return lblPriceA;
	}

	public JButton getBtnBack() {
		return btnBack;
	}

}
