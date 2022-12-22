package gui;

import lib.inc.*;
import lib.mysocket.*;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Fenetre extends JFrame{
	Contentpane contentpane;
	Myclient client;

	String clientname;
	String adresseip;
	int port;

	public Contentpane getContentpane(){
		return this.contentpane;
	}
	public void setContentpane(Contentpane cont){
		this.contentpane = cont;
	}

	public Myclient getClient(){
		return this.client;
	}
	public void setClient(Myclient cl){
		this.client = cl;
	}

	public String getClientname(){
		return this.clientname;
	} 
	public void setClientname(String cn){
		this.clientname = cn;
	}

	public String getAdresseip(){
		return this.adresseip;
	} 
	public void setAdresseip(String aip){
		this.adresseip = aip;
	}

	public int getPort(){
		return this.port;
	} 
	public void setPort(int p){
		this.port = p;
	}

	public void setDefaultNAP(){
		File infos = new File("./save/infos.snd");
		if(!infos.exists()){
			try{
				infos.createNewFile();
				String pardefaut = "Nouveau Client,localhost,6969;";

				Fonction.FileWrite(pardefaut,infos,false);
			}catch(Exception e){
				e.printStackTrace();
			}	
		}else{
			String ligne = Fonction.ReadFile(infos).get(0);
			String[] tab = ligne.split("[,;]");

			for(String s : tab){
				System.out.println(s);
			}
			this.setClientname(tab[0]);
			this.setAdresseip(tab[1]);
			this.setPort(Integer.parseInt(tab[2]));
		}
	}

	public Fenetre(){
		this.setDefaultNAP();

		try{
			Myclient newclient = new Myclient(this.getClientname(),this.getAdresseip(),this.getPort());
			this.setClient(newclient);
			System.out.println("Client "+this.getClient().getNom()+" , "+this.getClient().getInetAddress());

		}catch(Exception e){
			e.printStackTrace();
			String except = e.getMessage();
			if(except.toLowerCase().contains("refused")){
				except = "Serveur indisponible \n Aucune requette ne pourra etre execute, revenez plus tard \n";
			}
			JOptionPane.showMessageDialog(new JFrame(),except,"Erreur",JOptionPane.ERROR_MESSAGE);
		}

		this.setTitle("Multimedia Streaming Player V2.1 - Sanda");
		this.setSize(800,600);
		this.setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.setLocationRelativeTo(null);
		

		this.setContentPane(new Contentpane(this));

		this.setVisible(true);

		this.addWindowListener(new FenetreListener(this));
	}
}