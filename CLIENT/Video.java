package gui.multimedia;

import gui.*;
import lib.mysocket.*;

import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import java.awt.event.*;

public class Video extends JPanel implements MouseListener{
	Contentpane contentpane;
	Bouton quitter;
	JPanel listevideo;
	Displayphoto displayphoto;
	ArrayList<Fichier> fichier;

	public ArrayList<Fichier> getFichier(){
		return this.fichier;
	}
	public void setFichier(ArrayList<Fichier> h){
		this.fichier = h;
	}

	public Contentpane getContentpane(){
		return this.contentpane;
	}
	public void setContentpane(Contentpane cont){
		this.contentpane = cont;
	}

	public Bouton getQuitter(){
		return this.quitter;
	}
	public void setQuitter(Bouton c){
		this.quitter = c;
	}

	public JPanel getListevideo(){
		return this.listevideo;
	}
	public void setListevideo(JPanel lv){
		this.listevideo = lv;
	}

	public Displayphoto getDisplayphoto(){
		return this.displayphoto;
	}
	public void setDisplayphoto(Displayphoto dp){
		this.displayphoto = dp;
	}

	public Video(){

	}

	public Video(Contentpane cp){
		this.setContentpane(cp);

		this.setBackground(Color.white);
		this.setLayout(new BorderLayout(0,40));

	// -------------- NAVIGATION BAR ---------------
		JPanel navbar = new JPanel();
		navbar.setBackground(new Color(238,238,238));
		navbar.setPreferredSize(new Dimension(760,40));
			JPanel navbout = new JPanel();
			navbout.setPreferredSize(new Dimension(735,30));
			navbout.setBackground(Color.white);
			navbout.setBackground(new Color(238,238,238));
			navbout.setLayout(null);

				this.setQuitter(new Bouton("Quitter",new Color(245,240,236),new Color(186,180,168)));
				this.getQuitter().setBounds(0,0,135,30);
				this.getQuitter().addMouseListener(this);

			navbout.add(this.getQuitter());
		navbar.add(navbout);
	// ---------------------------------------------

	// --------------- CONTENT MENU -----------------

		Border bord = BorderFactory.createLineBorder(Color.gray,4);

		JPanel center = new JPanel();
		center.setBackground(Color.white);
		center.setPreferredSize(new Dimension(760,455));
		center.setLayout(null);
		//center.setLayout(new BorderLayout(20,0));

		JPanel list = new JPanel();
		list.setBounds(10,0,395,360);
		list.setBackground(Color.red);
		list.setLayout(new BorderLayout(0,10));
			JPanel souslist = new JPanel();
			souslist.setLayout(null);
			int yf = 5;
			int hsl = 0;
			souslist.setPreferredSize(new Dimension(390,hsl));
			for(int i=0;i<20;i++){
				Fichier f = new Fichier("sary numero "+i);
				f.setBounds(5,yf,380,40);
				yf += 50; 
				hsl += 50;
				souslist.setPreferredSize(new Dimension(390,hsl));
				//souslist.add(f);
			}
		
		JTextArea jta = new JTextArea(20,30);
		JScrollPane jsp = new JScrollPane(souslist);
		jsp.setHorizontalScrollBarPolicy(jsp.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.getVerticalScrollBar().setPreferredSize(new Dimension(10,360));
		jsp.getVerticalScrollBar().setUnitIncrement(5);

		list.add(jsp,BorderLayout.CENTER);

		this.setListevideo(list);
		this.setDisplayphoto(new Displayphoto());

		center.add(this.getListevideo());
		center.add(this.getDisplayphoto());
	// -----------------------------------------------

	// ---------------- BARRE D'OUTILS --------------- 
		JPanel outils = new JPanel();
		//outils.setBackground(Color.white);
		outils.setPreferredSize(new Dimension(760,80));
		outils.setLayout(null);
	// -----------------------------------------------
		this.add(navbar,BorderLayout.NORTH);
		this.add(center,BorderLayout.CENTER);
		this.add(outils,BorderLayout.SOUTH);
	}


	public Video(ArrayList<Fichier> fich,Contentpane cp){
		this.setContentpane(cp);
		this.setFichier(fich);

		this.setBackground(Color.white);
		this.setLayout(new BorderLayout(0,40));

	// -------------- NAVIGATION BAR ---------------

		JPanel navbar = new JPanel();
		navbar.setBackground(new Color(238,238,238));
		navbar.setPreferredSize(new Dimension(760,40));
			JPanel navbout = new JPanel();
			navbout.setPreferredSize(new Dimension(735,30));
			navbout.setBackground(Color.white);
			navbout.setBackground(new Color(238,238,238));
			navbout.setLayout(null);

				this.setQuitter(new Bouton("Quitter",new Color(245,240,236),new Color(186,180,168)));
				this.getQuitter().setBounds(0,0,135,30);
				this.getQuitter().addMouseListener(this);

			navbout.add(this.getQuitter());
		navbar.add(navbout);
	// ---------------------------------------------

	// --------------- CONTENT MENU -----------------

		Border bord = BorderFactory.createLineBorder(Color.gray,4);

		JPanel center = new JPanel();
		center.setBackground(Color.white);
		center.setPreferredSize(new Dimension(760,455));
		center.setLayout(null);
		//center.setLayout(new BorderLayout(20,0));

		JPanel list = new JPanel();
		list.setBounds(10,0,395,360);
		list.setBackground(Color.red);
		list.setLayout(new BorderLayout(0,10));
			JPanel souslist = new JPanel();
			souslist.setLayout(null);
			int yf = 5;
			int hsl = 0;
			souslist.setPreferredSize(new Dimension(390,hsl));

			for(Fichier f : this.getFichier()){
				f.setBounds(5,yf,380,40);
				yf += 50; 
				hsl += 50;
				souslist.setPreferredSize(new Dimension(390,hsl));
				souslist.add(f);
			}
		
		JTextArea jta = new JTextArea(20,30);
		JScrollPane jsp = new JScrollPane(souslist);
		jsp.setHorizontalScrollBarPolicy(jsp.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.getVerticalScrollBar().setPreferredSize(new Dimension(10,360));
		jsp.getVerticalScrollBar().setUnitIncrement(5);

		list.add(jsp,BorderLayout.CENTER);

		JPanel disp = new JPanel();
		disp.setBounds(415,0,360,360);
		disp.setBorder(bord);
		disp.setLayout(null);

		this.setListevideo(list);
		if(this.getDisplayphoto() == null){
			this.setDisplayphoto(new Displayphoto());
		}
		this.getDisplayphoto().setBounds(415,0,360,360);

		center.add(this.getListevideo());
		center.add(this.getDisplayphoto());
	// -----------------------------------------------

	// ---------------- BARRE D'OUTILS --------------- 
		JPanel outils = new JPanel();
		//outils.setBackground(Color.white);
		outils.setPreferredSize(new Dimension(760,80));
		outils.setLayout(null);

	// -----------------------------------------------
		this.add(navbar,BorderLayout.NORTH);
		this.add(center,BorderLayout.CENTER);
		this.add(outils,BorderLayout.SOUTH);

	}



// ------------------------------ OVERRIDING ------------------------------------

	@Override
	public void mouseClicked(MouseEvent e){
		/*
		Picture pic = new Picture();
		JPanel tempJP = this.getContentpane().getContenu();
		if(tempJP instanceof Picture){
			System.out.println("Contenu instanceof Picture");
			pic = (Picture) tempJP;
		}
		pic.setBackground(Color.blue);
		this.getContentpane().revalidate();
		this.getContentpane().repaint();

		*/

		Myclient client = this.getContentpane().getFenetre().getClient();
		if(e.getSource() == this.getQuitter()){
			try{
				//this.getDisplayphoto().setCurrentImage(null);
				client.QuitterMenu();
			}catch(Exception ex){
				ex.printStackTrace();
			}

			this.getContentpane().removeAll();
			this.getContentpane().setContenu(new Accueil(this.getContentpane()));
			this.getContentpane().add(this.getContentpane().getContenu(),BorderLayout.CENTER);	

			this.getContentpane().revalidate();
			this.getContentpane().repaint();
		}
	}
	@Override
	public void mouseEntered(MouseEvent e){
		
	}
	@Override
	public void mouseExited(MouseEvent e){
		
	}
	@Override
	public void mousePressed(MouseEvent e){
		

		this.getContentpane().revalidate();
		this.getContentpane().repaint();
	}
	@Override
	public void mouseReleased(MouseEvent e){

		this.getContentpane().revalidate();
		this.getContentpane().repaint();
	}
}