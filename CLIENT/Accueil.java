package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Accueil extends JPanel implements MouseListener{
	Contentpane contentpane;
	Menu menu;
	JLabel menutitle;
	JPanel infos;

	Bouton config;
	public JLabel infosconfig;

	public Contentpane getContentpane(){
		return this.contentpane;
	}
	public void setContentpane(Contentpane cont){
		this.contentpane = cont;
	}

	public Menu getMenu(){
		return this.menu;
	}
	public void setMenu(Menu m){
		this.menu = m;
	}

	public JLabel getMenutitle(){
		return this.menutitle;
	}
	public void setMenutitle(JLabel m){
		this.menutitle = m;
	}

	public JPanel getInfos(){
		return this.infos;
	}
	public void setInfos(JPanel m){
		this.infos = m;
	}

	public Bouton getConfig(){
		return this.config;
	}
	public void setConfig(Bouton c){
		this.config = c;
	}

	public Accueil(){

	}
	
	public Accueil(Contentpane cont){
		this.setContentpane(cont);

		this.setBackground(Color.white);
		this.setLayout(new BorderLayout(0,20));

		/*
			Fenetre size : 800 x 600
			W total 800: w 20 c 760  e 20
			menu (center) 760 : - gap 20 x 2 => 720
				taille d'une boite dans menu 720/3 = 240
			
			H total 600: n 20 c 560 s 20
			menu (center) 560 : - gap 20 x 1 => 540
				taille d'ine boite de menu 540/2 = 270 
		*/

	// ----------------- MENU picture,music and video -------------

		JPanel center = new JPanel();
		center.setPreferredSize(new Dimension(760,560));
		center.setBackground(Color.white);
		center.setLayout(new BorderLayout(0,20));
			this.setMenu(new Menu(this.getContentpane(),this));

			this.setMenutitle(new JLabel());
			this.getMenutitle().setFont(new Font("Century Gothic",Font.ITALIC,34));
			this.getMenutitle().setHorizontalAlignment(SwingConstants.CENTER);

			this.setInfos(new JPanel());
			this.getInfos().setPreferredSize(new Dimension(760,220));
			this.getInfos().setLayout(new BorderLayout(10,10));
			
	// -------------------------------------------------------------

	// ---------- CHAMP information et configuration ---------

		JPanel features = new JPanel();
		features.setPreferredSize(new Dimension(760,180));
		//features.setBackground(Color.blue);
		//String text = "<html><body> <p>aaaaaaaaaa</p> <p>bbbbbbbb</p>  <p>ccccccccc</p> </body></html>";
		
		// --------- BOUTON et Informations du bouton ---------

		JPanel bottom = new JPanel();
		bottom.setPreferredSize(new Dimension(760,40));
			JPanel champbout = new JPanel();
			champbout.setPreferredSize(new Dimension(735,30));
			champbout.setBackground(new Color(238,238,238));
			champbout.setLayout(null);

				this.setConfig(new Bouton("Config",new Color(245,240,236),new Color(186,180,168)));
				this.getConfig().setBounds(0,0,135,30);
				this.getConfig().addMouseListener(this);

				JLabel configInfos = new JLabel();
				configInfos.setFont(new Font("Century Gothic",0,14));
				configInfos.setBounds(150,0,580,30);
				this.infosconfig = configInfos;

			champbout.add(this.getConfig());
			champbout.add(this.infosconfig);

		bottom.add(champbout);

		// ----------------------------------------------------

		this.getInfos().add(features,BorderLayout.NORTH);
		this.getInfos().add(bottom,BorderLayout.SOUTH);

	// -------------------------------------------------------

		center.add(this.getMenu(),BorderLayout.NORTH);
		center.add(this.getMenutitle(),BorderLayout.CENTER);
		center.add(this.getInfos(),BorderLayout.SOUTH);

		JLabel jln = new JLabel();
		jln.setPreferredSize(new Dimension(800,0));
		this.add(jln,BorderLayout.NORTH);

		JLabel jls = new JLabel();
		jls.setPreferredSize(new Dimension(800,0));
		this.add(jls,BorderLayout.SOUTH);

		JLabel jlw = new JLabel();
		jlw.setPreferredSize(new Dimension(20,760));
		this.add(jlw,BorderLayout.WEST);	

		JLabel jle = new JLabel();
		jle.setPreferredSize(new Dimension(20,760));
		this.add(jle,BorderLayout.EAST);

		this.add(center,BorderLayout.CENTER);
	}

// ---------------------------------- LISTENER -------------------------------------

	@Override
	public void mouseClicked(MouseEvent e){
		if(e.getSource() == this.getConfig()){
			new Configuration(this.getContentpane());
		}
	}
	@Override
	public void mouseEntered(MouseEvent e){
		if(e.getSource() == this.getConfig()){
			this.infosconfig.setText("<html><body> <p>Ajouter ou changer votre <b>nom</b>, changer le <b>port</b> de votre serveur </p> <p>ou modifier l' <b>adresse IP</b> de votre serveur</p></body></html>");

			this.getContentpane().revalidate();
			this.getContentpane().repaint();
		}
	}
	@Override
	public void mouseExited(MouseEvent e){
		if(e.getSource() == this.getConfig()){
			this.infosconfig.setText("");

			this.getContentpane().revalidate();
			this.getContentpane().repaint();
		}
	}
	@Override
	public void mousePressed(MouseEvent e){
		
	}
	@Override
	public void mouseReleased(MouseEvent e){
		
	} 
}