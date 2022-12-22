package gui;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.awt.Graphics.*;

public class GIFdisplayer extends JLabel{
	int wi;
	int he;
	String relativepath;

	public GIFdisplayer(){

	}

	public GIFdisplayer(String rp,int w,int h){
		this.wi = w;
		this.he = h;
		this.relativepath = rp;

		this.setLayout(null);
		this.setBounds(5,5,this.wi,this.he);
	}

	public static Image getScaledImage(Image srcImg,int w,int h){
		BufferedImage resizedImg = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = resizedImg.createGraphics();

		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(srcImg,0,0,w,h,null);
		g2.dispose();

		return resizedImg;
	}

	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		
		ImageIcon img2 = new ImageIcon(this.relativepath);
		Image tempImg2 = this.getScaledImage(img2.getImage(),this.wi,this.he);
		ImageIcon newImg2 = new ImageIcon(tempImg2);
		this.setIcon(newImg2);

		this.repaint();
	}
}