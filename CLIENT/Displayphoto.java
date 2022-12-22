package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import javax.swing.border.Border;

public class Displayphoto extends JPanel{
	Contentpane contentpane;
	JLabel label;
	ImageIcon currentImage;

	int defaultWidth = 360;
	int defaultHeight = 360;

	int imageWidth = 360;
	int imageHeight = 360;

	public Contentpane getContentpane(){
		return this.contentpane;
	}
	public void setContentpane(Contentpane cont){
		this.contentpane = cont;
	}

	public JLabel getLabel(){
		return this.label;
	}
	public void setLabel(JLabel jl){
		this.label = jl;
	}

	public ImageIcon getCurrentImage(){
		return this.currentImage;
	}
	public void setCurrentImage(ImageIcon ci){
		this.currentImage = ci;
	}

	public int getImageWidth(){
		return this.imageWidth;
	}
	public void setImageWidth(int w){
		this.imageWidth = w;
	}

	public int getImageHeight(){
		return this.imageHeight;
	}
	public void setImageHeight(int h){
		this.imageHeight = h;
	}

	public Displayphoto(){
		this.setLayout(null);

		Border bord = BorderFactory.createLineBorder(Color.gray,4);

		this.setPreferredSize(new Dimension(this.defaultWidth,this.defaultHeight));
		this.setBorder(bord);
	}

	public Displayphoto(Contentpane cp){
		this.setContentpane(cp);

		this.setLayout(null);

		Border bord = BorderFactory.createLineBorder(Color.gray,4);

		this.setPreferredSize(new Dimension(this.defaultWidth,this.defaultHeight));
		this.setBorder(bord);
	}
	
	public Displayphoto(ImageIcon img,Contentpane cp){
		this.setContentpane(cp);
		this.setCurrentImage(img);

		this.setLayout(null);

		Border bord = BorderFactory.createLineBorder(Color.gray,4);

		this.setPreferredSize(new Dimension(this.defaultWidth,this.defaultHeight));
		this.setBorder(bord);

		JLabel lab = new JLabel();
		lab.setBounds(0,0,this.defaultWidth,this.defaultHeight);
		this.Ajouter_imageIcon(lab,this.getCurrentImage(),this.imageWidth,this.imageHeight);
		this.setLabel(lab);

		this.add(this.getLabel());
	}


// --------------------------- FONCTION --------------------------------------

	public void displayIn(ImageIcon img){
		JLabel lab = new JLabel();
		lab.setBounds(0,0,this.defaultWidth,this.defaultHeight);
		this.setLabel(lab);

		this.setCurrentImage(img);
		this.Ajouter_imageIcon(this.getLabel(),this.getCurrentImage(),this.imageWidth,this.imageHeight);

		this.removeAll();
		this.add(this.getLabel());

		this.revalidate();
		this.repaint();
		this.getContentpane().revalidate();
		this.getContentpane().repaint();
	}

	public void Ajouter_imageIcon(JLabel mylabel,ImageIcon img,int w,int h){
		Image tempImg = getScaledImage(img.getImage(),w,h);
		ImageIcon newImg = new ImageIcon(tempImg);
		mylabel.setIcon(newImg);
	}

	public JLabel Ajouter_image(String relativepath,int w,int h){
		JLabel fond = new JLabel();
		fond.setBounds(5,5,w,h);
		ImageIcon img2 = new ImageIcon(relativepath);
		Image tempImg2 = getScaledImage(img2.getImage(),w,h);
		ImageIcon newImg2 = new ImageIcon(tempImg2);
		fond.setIcon(newImg2);

		return fond;
	}

	public Image getScaledImage(Image srcImg,int w,int h){
		BufferedImage resizedImg = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = resizedImg.createGraphics();

		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(srcImg,0,0,w,h,null);
		g2.dispose();

		return resizedImg;
	}
}