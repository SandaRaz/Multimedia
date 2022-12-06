package lib.main;

import lib.mysocket.*;
import java.net.*;

public class StartServer{
	public static void main(String[] args) {
		try{ 
			Myserver server = new Myserver(6969,10);
			System.out.println("Server Port: "+server.getLocalPort());
			System.out.println("ia "+server.getInetAddress());

			int number = 0;
			System.out.println("Server Started ...");
			while(true){
				number++;
				server.Recevoir_un_Client(number);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}