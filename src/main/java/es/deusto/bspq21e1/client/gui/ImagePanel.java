package es.deusto.bspq21e1.client.gui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Image background = null;
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, null);
	}
	
	public void setImage(String image) {
		background = new ImageIcon(image).getImage();
//		background = new ImageIcon(getClass().getResource(image)).getImage();
	}
	
	@Override
	public String toString() {
		return this.getName();
	}
	
}
