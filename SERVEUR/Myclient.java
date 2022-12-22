package lib.mysocket; 

import lib.data.*;
import lib.audio.*;

import gui.multimedia.*;
import gui.Menu;
import gui.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.io.*;
import java.nio.*;
import java.net.*;
import java.util.*;
import java.util.stream.*;

import javazoom.jl.player.*;
import javazoom.jl.player.advanced.*;

public class Myclient extends Socket{
	String nom;
	String myhost;
	int myport;

	MusicPlayer musicplayer;
	AdvancedMP ampplayer = null;

	private DataOutputStream dos = null;
	private DataInputStream dis = null;
	private ObjectOutputStream oos = null;
	private ObjectInputStream ois = null;

	public String getNom(){
		return this.nom;
	}
	public void setNom(String n){
		this.nom = n;
	}

	public MusicPlayer getMusicplayer(){
    	return this.musicplayer;
    }
    public void setMusicplayer(MusicPlayer mp){
    	this.musicplayer = mp;
    }

	public AdvancedMP getAmpplayer(){
    	return this.ampplayer;
    }
    public void setAmpplayer(AdvancedMP p){
    	this.ampplayer = p;
    }

	public DataOutputStream getDos(){
		return this.dos;
	}
	public void setDos(DataOutputStream d){
		this.dos = d;
	}

	public DataInputStream getDis(){
		return this.dis;
	}
	public void setDis(DataInputStream d){
		this.dis = d;
	}

	public ObjectOutputStream getOos(){
		return this.oos;
	}
	public void setOos(ObjectOutputStream d){
		this.oos = d;
	}

	public ObjectInputStream getOis(){
		return this.ois;
	}
	public void setOis(ObjectInputStream d){
		this.ois = d;
	}
    
	public Myclient(){

	}
	public Myclient(String n,String host, int port) throws IOException{
		super(host,port);
		this.nom = n;
		this.myhost = host;
		this.myport = port;

		this.setOos(new ObjectOutputStream(this.getOutputStream()));
		this.setOis(new ObjectInputStream(this.getInputStream()));
	}

	public void showAction(FirstPacket pack,int number){
		System.out.println("Client "+pack.getClient()+" requette numero "+number);
		System.out.println("	a choisit : "+pack.getMenu());
	}

	public void showAction2(SecondPacket pack,int number){
		System.out.println("Client "+pack.getClient()+" requette numero "+number);
		System.out.println("	a choisit : "+pack.getNomfichier()+" du menu "+pack.getMenu());
	}

	public void SendFirstPacket(ObjectOutputStream oos,FirstPacket firstpack) throws Exception{
		oos.writeObject(firstpack);				// Ecrire qlq chose au serveur (requette)
		oos.flush();
	}

	public void SendSecondPacket(ObjectOutputStream oos,SecondPacket secondpack) throws Exception{
		oos.writeObject(secondpack);				// Ecrire qlq chose au serveur (requette)
		oos.flush();
	}

	public void Deconnexion() throws Exception{
		if(this.getOos() == null){
			this.setOos(new ObjectOutputStream(this.getOutputStream()));
		}

		FirstPacket firstpacket = new FirstPacket(this.getNom(),true);
		this.SendFirstPacket(getOos(),firstpacket);
		System.out.println(" Action: "+this.getNom()+" s'est deconnecte");
	}

	public void OnclickMenu(Menu menu,MouseEvent e) throws Exception{
		if(this.getOos() == null){
			//this.setOos(new ObjectOutputStream(this.getOutputStream()));
		
		}
		if(this.getOis() == null){
			//this.setOis(new ObjectInputStream(this.getInputStream()));
		}

/// ------------ choisir PICTURE --------------

		if(e.getSource() == menu.getPicture()){
			menu.getContentpane().removeAll();
			menu.getContentpane().setContenu(new Picture(menu.getContentpane()));
			menu.getContentpane().add(menu.getContentpane().getContenu(),BorderLayout.CENTER);	

			menu.getContentpane().revalidate();
			menu.getContentpane().repaint();

		//------------ Sending First Packet ----------------

			FirstPacket firstpacket = new FirstPacket(this.getNom(),"Picture");
			this.SendFirstPacket(getOos(),firstpacket);
			System.out.println(" Action: "+this.getNom()+" a envoyer un FirstPacket");

		//------------ Receiving First Packet --------------

			System.out.println(" Recevoir la reponse");
			FichierPack reponse = (FichierPack) this.getOis().readObject();
				System.out.println("	Reponse recu par "+reponse.getClient());

				ArrayList<Fichier> pictures = new ArrayList<Fichier>();
				for(Fichier f : reponse.getFichier()){
					f.setContentpane(menu.getContentpane());
					f.setType("Picture");
					pictures.add(f);
				}
				Picture picsmenu = new Picture(pictures,menu.getContentpane());

			menu.getContentpane().removeAll();
			menu.getContentpane().setContenu(picsmenu);
			menu.getContentpane().add(menu.getContentpane().getContenu(),BorderLayout.CENTER);

			menu.getContentpane().revalidate();
			menu.getContentpane().repaint();
		}

/// ------------ choisir MUSIC --------------

		if(e.getSource() == menu.getMusic()){

			menu.getContentpane().removeAll();
			menu.getContentpane().setContenu(new Music(menu.getContentpane()));
			menu.getContentpane().add(menu.getContentpane().getContenu(),BorderLayout.CENTER);	

			menu.getContentpane().revalidate();
			menu.getContentpane().repaint();

		//------------ Sending First Packet ----------------

			FirstPacket firstpacket = new FirstPacket(this.getNom(),"Music");
			this.SendFirstPacket(getOos(),firstpacket);
			System.out.println(" Action: "+this.getNom()+" a envoyer un FirstPacket");

		//------------ Receiving First Packet --------------

			System.out.println(" Recevoir la reponse");
			MusicVideoPack reponse = (MusicVideoPack) this.getOis().readObject();
				System.out.println("	Reponse recu par "+reponse.getClient());

				ArrayList<ArtistTitle> artisttitle = new ArrayList<ArtistTitle>();
				for(ArtistTitle at : reponse.getArtisttitle()){
					System.out.println(" _ "+at.getArtist());
					ArrayList<Fichier> finalisation = new ArrayList<Fichier>();
					for(Fichier f : at.getTitres()){
						f.setContentpane(menu.getContentpane());
						f.setType("Music");
						finalisation.add(f);
					}
					at.setTitres(finalisation);
					artisttitle.add(at);
				}
				Music musicmenu = new Music(artisttitle,menu.getContentpane());

			menu.getContentpane().removeAll();
			menu.getContentpane().setContenu(musicmenu);
			menu.getContentpane().add(menu.getContentpane().getContenu(),BorderLayout.CENTER);

			menu.getContentpane().revalidate();
			menu.getContentpane().repaint();

		//---------------------------------------------------

		}
	
/// ------------ choisir VIDEO --------------

		if(e.getSource() == menu.getVideo()){
			menu.getContentpane().removeAll();
			menu.getContentpane().setContenu(new Video(menu.getContentpane()));
			menu.getContentpane().add(menu.getContentpane().getContenu(),BorderLayout.CENTER);	

			menu.getContentpane().revalidate();
			menu.getContentpane().repaint();

		//------------ Sending First Packet ----------------

			FirstPacket firstpacket = new FirstPacket(this.getNom(),"Video");
			this.SendFirstPacket(getOos(),firstpacket);
			System.out.println(" Action: "+this.getNom()+" a envoyer un FirstPacket");

		//------------ Receiving First Packet --------------

			System.out.println(" Recevoir la reponse");
			FichierPack reponse = (FichierPack) this.getOis().readObject();
				System.out.println("	Reponse recu par "+reponse.getClient());

				ArrayList<Fichier> videos = new ArrayList<Fichier>();
				for(Fichier f : reponse.getFichier()){
					f.setContentpane(menu.getContentpane());
					f.setType("Video");
					videos.add(f);
				}
				Video videomenu = new Video(videos,menu.getContentpane());

			menu.getContentpane().removeAll();
			menu.getContentpane().setContenu(videomenu);
			menu.getContentpane().add(menu.getContentpane().getContenu(),BorderLayout.CENTER);

			menu.getContentpane().revalidate();
			menu.getContentpane().repaint();
		}
	}

	public void QuitterMenu() throws Exception{
		SecondPacket secondpacket = new SecondPacket(this.getNom(),true);
		this.SendSecondPacket(getOos(),secondpacket);
		System.out.println(" Action: "+this.getNom()+" est revenue dans le menu principal");
	}

	public void OnclickPicture(Fichier fichier) throws Exception{
		// --------------- Sending Second Packet ----------------

			SecondPacket secondpacket = new SecondPacket(this.getNom(),fichier.getNom().getText(),fichier.getType());
			System.out.println("  Fichier : "+fichier.getNom().getText()+" Type: " + fichier.getType());

			this.SendSecondPacket(getOos(),secondpacket);
			System.out.println(" \n Action: "+this.getNom()+" a envoyer un SecondPacket");

		//------------ Receiving Second Packet --------------

			System.out.println(" Recevoir la reponse");
			BytePacket reponse = (BytePacket) this.getOis().readObject();
			System.out.println("	Reponse recu par "+reponse.getClient()+" taille: "+reponse.getBuffer().length);

			ImageIcon image = new ImageIcon(reponse.getBuffer());

			Picture pic = new Picture();
			JPanel tempJP = fichier.getContentpane().getContenu();
			if(tempJP instanceof Picture){
				pic = (Picture) tempJP;
			}
			pic.getDisplayphoto().setContentpane(fichier.getContentpane());
			pic.getDisplayphoto().displayIn(image);

			System.out.println("Recycling byte");
			ByteBuffer bb = ByteBuffer.wrap(reponse.getBuffer());
			bb.clear();

			//fichier.getContentpane().removeAll();
			//fichier.getContentpane().setContenu(pic);
			//fichier.getContentpane().add(fichier.getContentpane().getContenu(),BorderLayout.CENTER);

			fichier.getContentpane().revalidate();
			fichier.getContentpane().repaint();
	}

// --------------------	MANAGE MUSIC -------------------
	public void stoperLaMusic(){
		if(this.getMusicplayer() != null){
			System.out.println(">>		misy hira mandeha");
			this.getMusicplayer().StopMusic();	
		}
	}

	public void pauserLaMusic() throws Exception{
		if(this.getMusicplayer() != null){
			System.out.println(">>		misy hira mandeha");
			if(this.getMusicplayer().isPlayed()){
				this.getMusicplayer().PauseMusic();
			}
		}
	}

	public void playLaMusic(){
		if(this.getMusicplayer() != null){
			System.out.println(">>		misy hira mandeha");
			this.getMusicplayer().ResumeMusic();
		}else{
			System.out.println("musicplayer null");
		}
	}

	public void decreaseVolume(){
		float current = this.getMusicplayer().getVolumeValue();
		current -= 10;
		if(current <= -80){
			current = -80;
		}
		this.getMusicplayer().setVolumeValue(current);
		this.getMusicplayer().setVolume();
	}

	public void increaseVolume(){
		float current = this.getMusicplayer().getVolumeValue();
		current += 10;
		if(current >= 6){
			current = 6;
		}
		this.getMusicplayer().setVolumeValue(current);
		this.getMusicplayer().setVolume();
	}

	public void resendingPacket(Myclient client) throws Exception{	// recommencer a envoyer un first packet a cause d'une socket is closed
		FirstPacket firstpacket = new FirstPacket(client.getNom(),"Music");
		client.SendFirstPacket(getOos(),firstpacket);

		MusicVideoPack reponse = (MusicVideoPack) client.getOis().readObject();
		reponse = null;
	}

	public void receiveFile(DataInputStream dis) throws Exception{	// recevoir la music a jouer
		this.stoperLaMusic();

		int size = (int) dis.readLong();
		byte[] buffer = new byte[4*1024];

		this.setMusicplayer(new MusicPlayer(dis));
		this.getMusicplayer().PlayMusic();
		this.getMusicplayer().setVolume(-80f);

	}

	public void OnclickSong(Myclient client,Fichier fichier) throws Exception{
		System.out.println("----------------------- Commencons ----------------------");
			
			this.stoperLaMusic();
		// --------------- Sending Second Packet ----------------
														//nom client   // nom artist         // titre chanson            // type du menu
			SecondPacket secondpacket = new SecondPacket(client.getNom(),fichier.getNomartist(),fichier.getNom().getText(),fichier.getType());
			secondpacket.setNomfichier(fichier.getNom().getText());
			System.out.println("  Fichier : "+fichier.getNomartist()+"  "+fichier.getNom().getText()+" Type: " + fichier.getType());

			try{
				client.SendSecondPacket(client.getOos(),secondpacket);
			}catch(SocketException e){
				if(client.isClosed()){
					client = new Myclient(client.getNom(),client.myhost,client.myport);

					//------------ RESENDING First Packet ----------------
					try{
						client.resendingPacket(client);
					}catch(SocketException e2){
						if(client.isClosed()){
							client.resendingPacket(client);
						}
						//e2.printStackTrace();
					}
					//---------------------------------------------------
					client.SendSecondPacket(client.getOos(),secondpacket);
				}
			}
			
			System.out.println(" \n Action: "+client.getNom()+" a envoyer un SecondPacket");

		// ----------- Receiving Metadata  --------------

			System.out.println(" Recevoir la reponse");

			Metadata metadata = (Metadata) client.getOis().readObject();

			ImageIcon image = new ImageIcon(metadata.getCover());

			Music music = new Music();
			JPanel tempJP = fichier.getContentpane().getContenu();
			if(tempJP instanceof Music){
				music = (Music) tempJP;
			}
			music.getDisplaycover().setContentpane(fichier.getContentpane());
			music.getDisplaycover().displayIn(image);

			music.getOutils().removeAll();
			music.setInfomusic(new InfoMusic(fichier.getContentpane(),music,metadata));
			music.getOutils().add(music.getInfomusic());

			System.out.println("Recycling byte");
			ByteBuffer bb = ByteBuffer.wrap(metadata.getCover());
			bb.clear();

			fichier.getContentpane().revalidate();
			fichier.getContentpane().repaint();

		// -----------------------------------------------
		// -------------------- play ---------------------
			this.setDis(new DataInputStream(client.getInputStream()));
			this.receiveFile(this.getDis());
		// -----------------------------------------------
       		System.out.println("\n \n	File readed");

			
	}

}