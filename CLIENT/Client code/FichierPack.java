package lib.data;

import gui.*;

import java.util.ArrayList;
import java.io.*;

public class FichierPack implements Serializable{
	String client;
	ArrayList<Fichier> fichier;

	public String getClient(){
		return this.client;
	}
	public void setClient(String a){
		this.client = a;
	}
	public ArrayList<Fichier> getFichier(){
		return this.fichier;
	}
	public void setFichier(ArrayList<Fichier> at){
		this.fichier = at;
	}

	public FichierPack(){

	}

	public FichierPack(String cl,ArrayList<Fichier> at){
		this.setClient(cl);
		this.setFichier(at);
	}
}