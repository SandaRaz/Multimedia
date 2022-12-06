package lib.data;

import java.io.*;

public class FirstPacket implements Serializable{
	String client;
	String menu;
	boolean deconnect = false;

	public String getClient(){
		return this.client;
	}
	public void setClient(String a){
		this.client = a;
	}
	public String getMenu(){
		return this.menu;
	}
	public void setMenu(String a){
		this.menu = a;
	}
	

	public boolean getDeconnect(){
		return this.deconnect;
	}
	public void setDeconnect(boolean tf){
		this.deconnect = tf;
	}

	public FirstPacket(){

	}

	public FirstPacket(String cl,boolean dec){
		this.client = cl;
		this.deconnect = dec;
	}

	public FirstPacket(String cl,String mn){
		this.client = cl;
		this.menu = mn;
	}
}