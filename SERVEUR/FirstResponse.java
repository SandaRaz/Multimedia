package lib.data;

import java.io.File;
import java.io.Serializable;

public class FirstResponse implements Serializable{
	String client;
	String writeIn;
	String localtitle;
	File song;

	public String getClient(){
		return this.client;
	}
	public void setClient(String c){
		this.client = c;
	}

	public String getWriteIn(){
		return this.writeIn;
	}
	public void setWriteIn(String s){
		this.writeIn = s;
	}

	public String getLocaltitle(){
		return this.localtitle;
	}
	public void setLocaltitle(String s){
		this.localtitle = s;
	}

	public File getSong(){
		return this.song;
	}
	public void setSong(File s){
		this.song = s;
	}

	public FirstResponse(){

	}
	
	public FirstResponse(String c,String wi,String lt,File s){
		this.client = c;
		this.writeIn = wi;
		this.localtitle = lt;
		this.song = s;
	}
}