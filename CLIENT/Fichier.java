package gui;

import lib.mysocket.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;

public class Fichier extends JLabel implements MouseListener,Serializable{
	Contentpane contentpane;

	JLabel nom;

	String nomartist;
	ArtistTitle artisttitle;

	String type;

	public Contentpane getContentpane(){
		return this.contentpane;
	}
	public void setContentpane(Contentpane cont){
		this.contentpane = cont;
	}
	
	public JLabel getNom(){
		return this.nom;
	}
	public void setNom(JLabel nm){
		this.nom = nm;
	}

	public String getNomartist(){
		return this.nomartist;
	}
	public void setNomartist(String nm){
		this.nomartist = nm;
	}

	public ArtistTitle getArtisttitle(){
		return this.artisttitle;
	}
	public void setArtisttitle(ArtistTitle at){
		this.artisttitle = at;
	}

	public String getType(){
		return this.type;
	}
	public void setType(String tp){
		this.type = tp;
	}

	public Fichier(String nm){	// si les fichiers sont tous grouper dans un seule dossier
		this.setLayout(null);
		this.setOpaque(true);
		this.setBackground(Color.white);
		//this.setBounds(5,5,380,40);
		this.setPreferredSize(new Dimension(380,40));

		JLabel nom = new JLabel();
		nom.setBounds(10,0,385,40);
		nom.setFont(new Font("Verdana",0,14));
		nom.setHorizontalAlignment(SwingConstants.LEFT);
		nom.setText(nm);

		this.setNom(nom);

		this.add(this.getNom());

		this.getNom().addMouseListener(this);
		this.addMouseListener(this);
	}

	public Fichier(String nm,String nartist){	// si les fichiers sont repartis dans un dossier portant le nom d'un artiste
		this.setNomartist(nartist);

		this.setLayout(null);
		this.setOpaque(true);
		this.setBackground(Color.white);
		//this.setBounds(5,5,380,40);
		this.setPreferredSize(new Dimension(380,40));

		JLabel nom = new JLabel();
		nom.setBounds(10,0,385,40);
		nom.setFont(new Font("Verdana",0,14));
		nom.setHorizontalAlignment(SwingConstants.LEFT);
		nom.setText(nm);

		this.setNom(nom);

		this.add(this.getNom());

		this.getNom().addMouseListener(this);
		this.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e){
		Myclient client = this.getContentpane().getFenetre().getClient();
		try{
			if(this.getType().equalsIgnoreCase("Picture")){
				client.OnclickPicture(this);
			}else if(this.getType().equalsIgnoreCase("Music")){
				client.OnclickSong(client,this);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	/*	
		if(this.getContentpane() != null){
			System.out.println("Type: "+this.getType());
			System.out.println(this.getContentpane().getFenetre().getClient()+" "+this.getContentpane().getFenetre().getAdresseip());
		}
	*/

	}
	@Override
	public void mouseEntered(MouseEvent e){
		
	}
	@Override
	public void mouseExited(MouseEvent e){
		
	}
	@Override
	public void mousePressed(MouseEvent e){
		this.setBackground(new Color(186,180,168));
		this.getNom().setForeground(Color.white);
	}
	@Override
	public void mouseReleased(MouseEvent e){
		this.setBackground(Color.white);
		this.getNom().setForeground(Color.black);
	} 
}