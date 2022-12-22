package gui;

import gui.multimedia.*;
import lib.data.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import javax.swing.border.Border;

public class InfoMusic extends JPanel{
	Contentpane contentpane;
	Music musicMenu;
	Metadata metadata;

	Bouton volplus;
	Bouton volmoin;
	Bouton stop;

	public Contentpane getContentpane(){
		return this.contentpane;
	}
	public void setContentpane(Contentpane cont){
		this.contentpane = cont;
	}

	public Music getMusicMenu(){
		return this.musicMenu;
	}
	public void setMusicMenu(Music mm){
		this.musicMenu = mm;
	}

	public Metadata getMetadata(){
		return this.metadata;
	}
	public void setMetadata(Metadata m){
		this.metadata = m;
	}

	public Bouton getVolplus(){
		return this.volplus;
	}
	public void setVolplus(Bouton v){
		this.volplus = v;
	}

	public Bouton getVolmoin(){
		return this.volmoin;
	}
	public void setVolmoin(Bouton v){
		this.volmoin = v;
	}

	public Bouton getStop(){
		return this.stop;
	}
	public void setStop(Bouton st){
		this.stop = st;
	}

	public InfoMusic(){
		this.setLayout(null);
		this.setBackground(Color.white);
		//this.setPreferredSize(new Dimension(740,60));
		this.setBounds(11,10,760,60);
	}

	public InfoMusic(Contentpane cp,Music mm,Metadata meta){
		this.setContentpane(cp);
		this.setMusicMenu(mm);
		this.setMetadata(meta);

		this.setBackground(Color.white);
		//this.setPreferredSize(new Dimension(740,60));
		this.setBounds(11,10,760,60);
		this.setLayout(null);

		Font font1 = new Font("Century Gothic",0,13);
		Font font2 = new Font("Century Gothic",Font.BOLD,13);

		// -------------- Label ------------------
		JLabel titre = new JLabel("Titre:");
		titre.setFont(font1);
		titre.setHorizontalAlignment(SwingConstants.RIGHT);
		titre.setBounds(0,5,100,25);

		JLabel metatitre = new JLabel(this.getMetadata().getIdvtag().get(0));
		metatitre.setFont(font2);
		metatitre.setHorizontalAlignment(SwingConstants.LEFT);
		metatitre.setBounds(110,5,170,25);


		JLabel artiste = new JLabel("Artiste:");
		artiste.setFont(font1);
		artiste.setHorizontalAlignment(SwingConstants.RIGHT);
		artiste.setBounds(0,30,100,25);

		JLabel metaartiste = new JLabel(this.getMetadata().getIdvtag().get(1));
		metaartiste.setFont(font2);
		//metaartiste.setOpaque(true);
		//metaartiste.setBackground(Color.red);
		metaartiste.setHorizontalAlignment(SwingConstants.LEFT);
		metaartiste.setBounds(110,30,170,25);


		JLabel album = new JLabel("Album:");
		album.setFont(font1);
		album.setHorizontalAlignment(SwingConstants.RIGHT);
		album.setBounds(280,5,70,25);

		JLabel metaalbum = new JLabel(this.getMetadata().getIdvtag().get(2));
		metaalbum.setFont(font2);
		metaalbum.setHorizontalAlignment(SwingConstants.LEFT);
		metaalbum.setBounds(360,5,170,25);

		JLabel duree = new JLabel("Duree:");
		duree.setFont(font1);
		duree.setHorizontalAlignment(SwingConstants.RIGHT);
		duree.setBounds(280,30,70,25);

		JLabel metaduree = new JLabel(this.getMetadata().getIdvtag().get(3));
		metaduree.setFont(font2);
		metaduree.setHorizontalAlignment(SwingConstants.LEFT);
		metaduree.setBounds(360,30,170,25);

		// --------- BARRE D'OUTILS --------
		this.setVolmoin(new Bouton("vol -",new Color(245,240,236),new Color(186,180,168)));
			this.getVolmoin().setBounds(530,20,70,20);
			this.getVolmoin().setFont(new Font("Century Gothic",0,14));
			this.getVolmoin().addMouseListener(this.getMusicMenu());

		this.setVolplus(new Bouton("vol +",new Color(245,240,236),new Color(186,180,168)));
			this.getVolplus().setBounds(605,20,70,20);
			this.getVolplus().setFont(new Font("Century Gothic",0,14));
			this.getVolplus().addMouseListener(this.getMusicMenu());

		this.setStop(new Bouton("stop",new Color(245,240,236),new Color(186,180,168)));
			this.getStop().setBounds(680,20,70,20);
			this.getStop().setFont(new Font("Century Gothic",0,14));
			this.getStop().addMouseListener(this.getMusicMenu());


		// ---------------------------------------
		this.add(titre);
		this.add(metatitre);
		this.add(artiste);
		this.add(metaartiste);
		this.add(album);
		this.add(metaalbum);
		this.add(duree);
		this.add(metaduree);

		this.add(this.getVolmoin());
		this.add(this.getVolplus());
		this.add(this.getStop());

		//Border bord = BorderFactory.createLineBorder(Color.gray,4);

		
	}
}