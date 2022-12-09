package lib.data;

import java.io.*;
import java.util.ArrayList;

public class Metadata implements Serializable{
	byte[] cover;
	ArrayList<String> idvtag;

	public byte[] getCover(){
		return this.cover;
	}
	public void setCover(byte[] c){
		this.cover = c;
	}

	public ArrayList<String> getIdvtag(){
		return this.idvtag;
	}
	public void setIdvtag(ArrayList<String> tag){
		this.idvtag = tag;
	}
// -------------------------------

	public Metadata(){

	}

	public Metadata(byte[] c,ArrayList<String> tag){
		this.setCover(c);
		this.setIdvtag(tag);
	}
}