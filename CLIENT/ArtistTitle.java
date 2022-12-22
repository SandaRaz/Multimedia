package gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.io.Serializable;

public class ArtistTitle extends JPanel implements Serializable{
	String artist;
	ArrayList<Fichier> titres;

	int wi;
	int he;

	public String getArtist(){
		return this.artist;
	}
	public void setArtist(String nm){
		this.artist = nm;
	}

	public ArrayList<Fichier> getTitres(){
		return this.titres;
	}
	public void setTitres(ArrayList<Fichier> t){
		this.titres = t;
	}

	public int getWi(){
		return this.wi;
	}
	public void setWi(int w){
		this.wi = w;
	}

	public int getHe(){
		return this.he;
	}
	public void setHe(int h){
		this.he = h;
	}

	public ArtistTitle(){

	}

	public ArtistTitle(String ar,ArrayList<Fichier> t){
		this.setArtist(ar);
		this.setTitres(t);

		this.setBackground(new Color(238,238,238));
		this.setLayout(new BorderLayout(5,10));
			JLabel mpihira = new JLabel();
			mpihira.setPreferredSize(new Dimension(390,20));
			mpihira.setOpaque(true);
			mpihira.setBackground(new Color(186,180,168));
			mpihira.setFont(new Font("Verdana",Font.BOLD,15));
			mpihira.setHorizontalAlignment(SwingConstants.CENTER);
			mpihira.setText(this.getArtist());
		this.add(mpihira,BorderLayout.NORTH);
			JPanel south = new JPanel();
			south.setLayout(null);
			int posYfichier = 5;
			int height = 0;
			south.setPreferredSize(new Dimension(390,height));
			for(Fichier f : this.getTitres()){
				f.setBounds(5,posYfichier,380,40);
				posYfichier += 50; 
				height += 50;
				south.setPreferredSize(new Dimension(390,height));
				south.add(f);
			}
			this.setWi(390);
			this.setHe(height + 30); // (>>> 30 = mpihira height(20) + vgap(10))
		this.setPreferredSize(new Dimension(this.getWi(),this.getHe()));
		this.add(south,BorderLayout.CENTER);
	}
} 