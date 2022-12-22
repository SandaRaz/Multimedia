package lib.data;

import java.io.*;

public class SecondPacket implements Serializable{
	String client;
	String nomficher;
	String menu;

	String artist;
	String titre;

	boolean deconnect = false;

	public String getClient(){
		return this.client;
	}
	public void setClient(String a){
		this.client = a;
	}
	public String getNomfichier(){
		return this.nomficher;
	}
	public void setNomfichier(String a){
		this.nomficher = a;
	}

	public String getMenu(){
		return this.menu;
	}
	public void setMenu(String a){
		this.menu = a;
	}

// -------------------------------

	public String getArtist(){
		return this.artist;
	}
	public void setArtist(String a){
		this.artist = a;
	}
	public String getTitre(){
		return this.titre;
	}
	public void setTitre(String a){
		this.titre = a;
	}
	

	public boolean getDeconnect(){
		return this.deconnect;
	}
	public void setDeconnect(boolean tf){
		this.deconnect = tf;
	}

	public SecondPacket(){

	}

	public SecondPacket(String cl,boolean dec){
		this.client = cl;
		this.deconnect = dec;
	}

	public SecondPacket(String cl,String nf,String mn){
		this.client = cl;
		this.nomficher = nf;
		this.menu = mn;
	}

	public SecondPacket(String cl,String art,String tr,String mn){
		this.client = cl;
		this.artist = art;
		this.titre = tr;
		this.menu = mn;
	}
}