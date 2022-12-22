package lib.inc;

import gui.GIFdisplayer;

import java.io.*;
import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.Graphics.*;

public class Fonction{

	public Fonction(){

	}

	public static JLabel Ajouter_image(String relativepath,int w,int h){
		JLabel fond = new JLabel();
		fond.setBounds(5,5,w,h);
		ImageIcon img2 = new ImageIcon(relativepath);
		Image tempImg2 = getScaledImage(img2.getImage(),w,h);
		ImageIcon newImg2 = new ImageIcon(tempImg2);
		fond.setIcon(newImg2);

		return fond;
	}

	public static Image getScaledImage(Image srcImg,int w,int h){
		BufferedImage resizedImg = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = resizedImg.createGraphics();

		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(srcImg,0,0,w,h,null);
		g2.dispose();

		return resizedImg;
	}

	public static void FileWrite(String textes,File file,boolean append){
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(file,append))){
			bw.write(textes);
			bw.newLine();

			bw.close();
		}
		catch(IOException ex){
			ex.printStackTrace();
		}
	}

	public static ArrayList<String> ReadFile(File file){
		ArrayList<String> listes = new ArrayList<String>();
		
		try{
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			
			String line;
			while((line = br.readLine()) != null){
				listes.add(line);
			}
		}catch(Exception e){
			e.printStackTrace();
		}

		return listes;
	}
}