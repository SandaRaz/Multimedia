package gui.multimedia;

import gui.*;
import lib.audio.*;
import lib.mysocket.*;

import java.util.ArrayList;
import java.lang.reflect.*;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import java.awt.event.*;

public class Music extends JPanel implements MouseListener{
	Contentpane contentpane;

	Bouton quitter;

	ArrayList<ArtistTitle> artisttitle;
	ArrayList<Fichier> hira;

	JPanel listemusic;
	Displayphoto displaycover;

	JPanel outils;
	InfoMusic infomusic;

	MusicPlayer mplayer;

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

	public ArrayList<ArtistTitle> getArtisttitle(){
		return this.artisttitle;
	}
	public void setArtisttitle(ArrayList<ArtistTitle> t){
		this.artisttitle = t;
	}

	public ArrayList<Fichier> getHira(){
		return this.hira;
	}
	public void setHira(ArrayList<Fichier> h){
		this.hira = h;
	}

	public JPanel getListemusic(){
		return this.listemusic;
	}
	public void setListemusic(JPanel lp){
		this.listemusic = lp;
	}

	public Displayphoto getDisplaycover(){
		return this.displaycover;
	}
	public void setDisplaycover(Displayphoto dc){
		this.displaycover = dc;
	}

	public JPanel getOutils(){
		return this.outils;
	}
	public void setOutils(JPanel ou){
		this.outils = ou;
	}
	public InfoMusic getInfomusic(){
		return this.infomusic;
	}
	public void setInfomusic(InfoMusic im){
		this.infomusic = im;
	}

	public Music(){

	}

	public Music(Contentpane cp){
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

	// --------------- Liste fichier ---------------
		JPanel list = new JPanel();
		list.setBounds(10,0,395,360);
		list.setBackground(Color.red);
		list.setLayout(new BorderLayout(0,10));
			JPanel souslist = new JPanel();
		
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

		this.setListemusic(list);
		if(this.getDisplaycover() == null){
			this.setDisplaycover(new Displayphoto());
		}
		this.getDisplaycover().setBounds(415,0,360,360);

		center.add(this.getListemusic());
		center.add(this.getDisplaycover());
	// -----------------------------------------------

	// ---------------- BARRE D'OUTILS --------------- 
		this.setOutils(new JPanel());
		this.getOutils().setBackground(new Color(238,238,238));
		this.getOutils().setPreferredSize(new Dimension(760,80));
		this.getOutils().setLayout(null);
	// -----------------------------------------------
		this.add(navbar,BorderLayout.NORTH);
		this.add(center,BorderLayout.CENTER);
		this.add(this.getOutils(),BorderLayout.SOUTH);
	}

	public Music(ArrayList<ArtistTitle> artt,Contentpane cp){
		this.setContentpane(cp);
		this.setArtisttitle(artt);

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

	// --------------- Liste fichier ---------------
		JPanel list = new JPanel();
		list.setBounds(10,0,395,360);
		list.setBackground(Color.red);
		list.setLayout(new BorderLayout(0,10));
			JPanel souslist = new JPanel();
			souslist.setLayout(null);
			int yf = 5;
			int hsl = 0;
			souslist.setPreferredSize(new Dimension(390,hsl));

			for(ArtistTitle at : this.getArtisttitle()){
				at.setBounds(0,yf,at.getWi(),at.getHe());
				yf += at.getHe() + 10; 
				hsl += at.getHe() + 10;
				souslist.setPreferredSize(new Dimension(390,hsl));
				souslist.add(at);
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

		this.setListemusic(list);
		this.setListemusic(list);
		if(this.getDisplaycover() == null){
			this.setDisplaycover(new Displayphoto());
		}
		this.getDisplaycover().setBounds(415,0,360,360);

		center.add(this.getListemusic());
		center.add(this.getDisplaycover());
	// -----------------------------------------------

	// ---------------- BARRE D'OUTILS --------------- 

		this.setOutils(new JPanel());
		this.getOutils().setBackground(new Color(238,238,238));
		this.getOutils().setPreferredSize(new Dimension(760,80));
		this.getOutils().setLayout(null);

		if(this.getInfomusic() == null){
			this.setInfomusic(new InfoMusic());
		}

		this.getOutils().add(this.getInfomusic());
	// --------------------- ADD ---------------------

		this.add(navbar,BorderLayout.NORTH);
		this.add(center,BorderLayout.CENTER);
		this.add(this.getOutils(),BorderLayout.SOUTH);

	// ---------------- LISTENER ---------------------

		this.addMouseListener(this);
	}

// ------------------------------- LISTENER -----------------------------------

	@Override
	public void mouseClicked(MouseEvent e){
		Myclient client = this.getContentpane().getFenetre().getClient();

		if(e.getSource() == this.getQuitter()){
			client.stoperLaMusic();
			
			if(client.isClosed()){
				System.out.println("isClosed isClosed isClosed");
				try{
					client = new Myclient(this.getContentpane().getFenetre().getClientname(),this.getContentpane().getFenetre().getAdresseip(),this.getContentpane().getFenetre().getPort());
					this.getContentpane().getFenetre().setClient(client);
					client.resendingPacket(client);

					System.out.println("	MCREER CLIENT VAOVAO");
				}catch(Exception ex){
					ex.printStackTrace();
				}
				
			}
			try{
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
		Myclient client = this.getContentpane().getFenetre().getClient();

		if(e.getSource() == this.getInfomusic().getStop()){
			System.out.println("stop");
			client.stoperLaMusic();
			
			if(client.isClosed()){
				System.out.println("isClosed isClosed isClosed");
				try{
					client = new Myclient(this.getContentpane().getFenetre().getClientname(),this.getContentpane().getFenetre().getAdresseip(),this.getContentpane().getFenetre().getPort());
					this.getContentpane().getFenetre().setClient(client);
					client.resendingPacket(client);

					System.out.println("	MCREER CLIENT VAOVAO");
				}catch(Exception ex){
					ex.printStackTrace();
				}
				
			}
			this.getInfomusic().getStop().setFont(new Font("Century Gothic",Font.BOLD,15));
		}

		if(e.getSource() == this.getInfomusic().getVolplus()){
			System.out.println("Volume +");
			client.increaseVolume();
			this.getInfomusic().getVolplus().setFont(new Font("Century Gothic",Font.BOLD,15));
		}

		if(e.getSource() == this.getInfomusic().getVolmoin()){
			System.out.println("Volume -");
			client.decreaseVolume();
			this.getInfomusic().getVolmoin().setFont(new Font("Century Gothic",Font.BOLD,15));
		}
	}
	@Override
	public void mouseReleased(MouseEvent e){
		if(e.getSource() == this.getInfomusic().getStop()){
			System.out.println("stop");
			this.getInfomusic().getStop().setFont(new Font("Century Gothic",0,14));
		}
		if(e.getSource() == this.getInfomusic().getVolplus()){
			System.out.println("Volume +");
			this.getInfomusic().getVolplus().setFont(new Font("Century Gothic",0,14));
		}

		if(e.getSource() == this.getInfomusic().getVolmoin()){
			System.out.println("Volume -");
			this.getInfomusic().getVolmoin().setFont(new Font("Century Gothic",0,14));
		}
	}
}