package lib.data;

import java.io.*;
import java.net.*;

public class BytePacket implements Serializable{
	String client;
	byte[] buffer;

	public String getClient(){
		return this.client;
	}
	public void setClient(String a){
		this.client = a;
	}

	public byte[] getBuffer(){
		return this.buffer;
	}
	public void setBuffer(byte[] b){
		this.buffer = b;
	}

	public BytePacket(){
		
	}
	public BytePacket(String cl,byte[] b){
		this.setClient(cl);
		this.setBuffer(b);
	}
}