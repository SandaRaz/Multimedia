package lib.data;

import gui.*;

import java.util.ArrayList;
import java.io.*;

public class MusicVideoPack implements Serializable{
	String client;
	ArrayList<ArtistTitle> artisttitle;

	public String getClient(){
		return this.client;
	}
	public void setClient(String a){
		this.client = a;
	}
	public ArrayList<ArtistTitle> getArtisttitle(){
		return this.artisttitle;
	}
	public void setArtisttitle(ArrayList<ArtistTitle> at){
		this.artisttitle = at;
	}

	public MusicVideoPack(){

	}

	public MusicVideoPack(String cl,ArrayList<ArtistTitle> at){
		this.setClient(cl);
		this.setArtisttitle(at);
	}
}