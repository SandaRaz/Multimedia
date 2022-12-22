package gui;

import javax.swing.*;
import java.awt.*;

public class Contentpane extends JPanel{
	Fenetre fenetre;
	JPanel contenu;

	public Fenetre getFenetre(){
		return this.fenetre;
	}
	public void setFenetre(Fenetre f){
		this.fenetre = f;
	} 
	public JPanel getContenu(){
		return this.contenu;
	}
	public void setContenu(JPanel cont){
		this.contenu = cont;
	}

	/*
		Fenetre size : 800 x 600
		W total 800: w 20 c 760  e 20
		menu (center) 760 : - gap 20 x 2 => 720
			taille d'une boite dans menu 720/3 = 240
		
		H total 600: n 20 c 560 s 20
		menu (center) 560 : - gap 20 x 1 => 540
			taille d'ine boite de menu 540/2 = 270 
	*/

	public Contentpane(){
		this.setContenu(new Accueil(this));
		this.setBackground(Color.white);

		this.setLayout(new BorderLayout(0,0));
		this.add(this.getContenu(),BorderLayout.CENTER);
	}
	public Contentpane(Fenetre fen){
		this.setFenetre(fen);
		this.setContenu(new Accueil(this));
		this.setBackground(Color.white);

		this.setLayout(new BorderLayout(0,0));
		this.add(this.getContenu(),BorderLayout.CENTER);
	}
	public Contentpane(JPanel cont,Fenetre fen){
		this.setFenetre(fen);
		this.setContenu(cont);
		this.setBackground(Color.white);

		this.setLayout(new BorderLayout(0,0));
		this.add(this.getContenu(),BorderLayout.CENTER);
	}
}