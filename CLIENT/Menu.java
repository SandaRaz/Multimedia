package gui;

import gui.multimedia.*;
import lib.inc.*;
import lib.mysocket.*;

import java.io.*;
import java.net.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Menu extends JPanel implements MouseListener{
	Contentpane contentpane;
	Accueil accueil;

	JPanel picture;
	JPanel music;
	JPanel video;

	public Contentpane getContentpane(){
		return this.contentpane;
	}
	public void setContentpane(Contentpane cont){
		this.contentpane = cont;
	}

	public Accueil getAccueil(){
		return this.accueil;
	}
	public void setAccueil(Accueil a){
		this.accueil = a;
	}

	public JPanel getPicture(){
		return this.picture;
	}
	public void setPicture(JPanel jp){
		this.picture = jp;
	}

	public JPanel getMusic(){
		return this.music;
	}
	public void setMusic(JPanel jp){
		this.music = jp;
	}

	public JPanel getVideo(){
		return this.video;
	}
	public void setVideo(JPanel jp){
		this.video = jp;
	}

	public Menu(){

	}
	public Menu(Contentpane cont,Accueil acc){
		this.setContentpane(cont);
		this.setAccueil(acc);

		this.setBackground(Color.white);
		this.setLayout(new GridLayout(1,3,20,0));
		this.setPreferredSize(new Dimension(760,220));
		
		this.setPicture(new JPanel());
			this.getPicture().setLayout(null);
			this.getPicture().add(new GIFdisplayer("./Themes/pictures/camera.gif",225,210));
		
		this.setMusic(new JPanel());
			this.getMusic().setLayout(null);
			this.getMusic().add(new GIFdisplayer("./Themes/musics/sound.gif",225,210));
		this.setVideo(new JPanel());		
			this.getVideo().setLayout(null);
			this.getVideo().add(new GIFdisplayer("./Themes/video/video.gif",225,210));
		this.add(this.getPicture());
		this.add(this.getMusic());
		this.add(this.getVideo());

		this.getPicture().addMouseListener(this);
		this.getMusic().addMouseListener(this);
		this.getVideo().addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e){
		Myclient client = this.getContentpane().getFenetre().getClient();
		try{
			client.OnclickMenu(this,e);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e){
		if(e.getSource() == this.getPicture()){
			this.getPicture().setBackground(Color.gray);
			this.getPicture().removeAll();
			this.getPicture().add(Fonction.Ajouter_image("./Themes/pictures/camera.png",225,210));
			this.getAccueil().getMenutitle().setText("<html><body><font color='gray'>Pics streaming</body></html>");

			this.getContentpane().revalidate();
			this.getContentpane().repaint();
		}
		if(e.getSource() == this.getMusic()){
			this.getMusic().setBackground(Color.gray);
			this.getMusic().removeAll();
			this.getMusic().add(Fonction.Ajouter_image("./Themes/musics/sound.png",225,210));
			this.getAccueil().getMenutitle().setText("<html><body><font color='gray'>Music streaming</body></html>");

			this.getContentpane().revalidate();
			this.getContentpane().repaint();
		}
		if(e.getSource() == this.getVideo()){
			this.getVideo().setBackground(Color.gray);
			this.getVideo().removeAll();
			this.getVideo().add(Fonction.Ajouter_image("./Themes/video/video.png",225,210));
			this.getAccueil().getMenutitle().setText("<html><body><font color='gray'>Video streaming</body></html>");

			this.getContentpane().revalidate();
			this.getContentpane().repaint();
		}
	}
	@Override
	public void mouseExited(MouseEvent e){
		if(e.getSource() == this.getPicture()){
			this.getPicture().setBackground(new Color(238,238,238));
			this.getPicture().removeAll();
			this.getPicture().add(new GIFdisplayer("./Themes/pictures/camera.gif",225,210));

			this.getAccueil().getMenutitle().setText("");

			this.getContentpane().revalidate();
			this.getContentpane().repaint();
		}
		if(e.getSource() == this.getMusic()){
			this.getMusic().setBackground(new Color(238,238,238));
			this.getMusic().removeAll();
			this.getMusic().add(new GIFdisplayer("./Themes/musics/sound.gif",225,210));

			this.getAccueil().getMenutitle().setText("");

			this.getContentpane().revalidate();
			this.getContentpane().repaint();
		}
		if(e.getSource() == this.getVideo()){
			this.getVideo().setBackground(new Color(238,238,238));
			this.getVideo().removeAll();
			this.getVideo().add(new GIFdisplayer("./Themes/video/video.gif",225,210));

			this.getAccueil().getMenutitle().setText("");

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