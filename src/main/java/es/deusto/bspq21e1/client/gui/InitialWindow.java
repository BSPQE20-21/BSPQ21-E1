package es.deusto.bspq21e1.client.gui;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;

import es.deusto.bspq21e1.client.controller.Controller;
import javax.swing.JLabel;

/**
 * Window class for the initial point of the program, where users can register or log in.
 * @author SPQ Group 1
 * @version 1.0
 */
public class InitialWindow extends JFrame {

    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(InitialWindow.class.getName());
    private Controller controller;

    private JFrame frmAirbv = new JFrame();
    private JLabel lblHead;
    private JPanel panel;
    private JButton btnRegister, btnLogin, btnExit;
    
//    private ImagePanel imagePanel;
//    private String pathToImage = "initialWindow.png";

    /**
	 * Creates the initial window of the program.
     * @param controller Controller used for handling all requests in the system.  
	 */
    public InitialWindow(Controller controller) {
        this.controller = controller;
        frmAirbv.setTitle("AirB&V");
        frmAirbv.setVisible(true);
        initialize();
    }

    /**
	 * Initializes all the elements the window needs to show to the user and their functionality.
	 */
    private void initialize() {
        frmAirbv.setBounds(100, 100, 255, 320);
        frmAirbv.setLocationRelativeTo(null);
        frmAirbv.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAirbv.getContentPane().setLayout(null);
		
//		imagePanel.setImage(pathToImage);
//		frmAirbv.setContentPane(imagePanel);
		
        lblHead = new JLabel(controller.getResourcebundle().getString("initial_head_msg"));
        lblHead.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblHead.setBounds(23, 25, 202, 25);
        frmAirbv.getContentPane().add(lblHead);

        panel = new JPanel();
        panel.setBounds(20, 50, 202, 210);
        frmAirbv.getContentPane().add(panel);
        panel.setLayout(null);

        btnRegister = new JButton(controller.getResourcebundle().getString("register_msg"));
        btnRegister.setBounds(25, 36, 150, 25);
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	new RegisterUserWindow(controller);
                frmAirbv.dispose();
            }
        } );
        panel.add(btnRegister);
        btnRegister.updateUI();

        btnLogin = new JButton(controller.getResourcebundle().getString("login_msg"));
        btnLogin.setBounds(25, 85, 150, 25);
        btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new LogInWindow(controller);
				frmAirbv.dispose();
			}
		});
        panel.add(btnLogin);
        btnLogin.updateUI();

        btnExit = new JButton(controller.getResourcebundle().getString("exit_msg"));
        btnExit.setBounds(25, 180, 150, 25);
        btnExit.setBackground(Color.RED);
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	frmAirbv.dispose();
            }
        } );
        panel.add(btnExit);
        btnExit.updateUI();

        logger.info("InitialWindow well initialized");
    }
    
//    class ImagePanel extends JPanel {
//    	
//		private static final long serialVersionUID = 1L;
//		private Image fondo = null;
//		
//		@Override
//		protected void paintComponent(Graphics g) {
//			super.paintComponent(g);
//			g.drawImage(fondo, 0, 0, null);
//		}
//		
//		public void setImage(String image) {
//			fondo = new ImageIcon(getClass().getResource(image)).getImage();
//		}
//		
//		@Override
//		public String toString() {
//			return this.getName();
//		}
//    }
}