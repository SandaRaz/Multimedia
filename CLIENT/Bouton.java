package gui;

import javax.swing.*;
import java.awt.*;

public class Bouton extends JButton{
	Color color1;
	Color color2;
	public Bouton(String nom,Color c1,Color c2){
		super(nom);
		this.color1 = c1;
		this.color2 = c2;

		this.setContentAreaFilled(false);
		this.setFocusPainted(false);
		this.setFocusable(false);
		this.setFont(new Font("Century Gothic",0,14));
	}

	@Override
	protected void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setPaint(new GradientPaint(
				new Point(0, 0),
				color1,
				new Point(0, getHeight()),
				color2));
		g2.fillRect(0, 0, getWidth(), getHeight());
		g2.dispose();

		super.paintComponent(g);
	}

}