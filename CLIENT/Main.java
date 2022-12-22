package lib.main;

import gui.*;
import lib.inc.*;

public class Main{
	public static void main(String[] args) {
		Bienvenue bv = new Bienvenue();
		try{
			Thread.sleep(2000);
		}catch(Exception  e){
			e.printStackTrace();
		}
		bv.dispose();
		Fenetre fenetre = new Fenetre();
	}
}