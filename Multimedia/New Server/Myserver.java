package lib.mysocket;

import lib.thread.*;
import lib.data.*;
import lib.inc.*;

import gui.*;
import gui.multimedia.*;

import java.net.*;
import java.io.*;
import java.util.ArrayList;

import com.mpatric.mp3agic.*;
import org.apache.tika.Tika.*;

public class Myserver extends ServerSocket{
	public Myserver() throws Exception{

	}
	public Myserver(int myport) throws IOException{
		super(myport);
	}

	public Myserver(int myport,int maxclient) throws IOException{
		super(myport,maxclient);
	}

	public Myserver(int myport,int maxclient,InetAddress ip) throws IOException{
		super(myport,maxclient,ip);
	}

	public void Recevoir_un_Client(int number) throws Exception{
		Socket clientvenue = this.accept();	
		System.out.println("Requete numero "+number+",miarahaba ny client IP:"+clientvenue.getInetAddress());

		ClientProcess thread = new ClientProcess(this,clientvenue,number);
		thread.start();
	}


// --------------------------- Some necessary Function ----------------------------
	public FichierPack ListerImage(String nomclient,String mainfolder){
		ArrayList<Fichier> retour = new ArrayList<Fichier>();

		String mainpath = mainfolder+"/Picture";
		File folder = new File(mainpath);
		File[] listimage = folder.listFiles();

		for(File f : listimage){
			retour.add(new Fichier(f.getName()));
		}

		FichierPack fp = new FichierPack(nomclient,retour);
		return fp;
	}

	public MusicVideoPack ListerMusic(String nomclient,String mainfolder){
		ArrayList<ArtistTitle> retour = new ArrayList<ArtistTitle>();

		String mainpath = mainfolder+"/Music";
		File folder = new File(mainpath);
		File[] listartist = folder.listFiles();

		for(File f : listartist){
			if(f.isDirectory()){
				//System.out.println(f.getName());
				ArrayList<Fichier> titres = new ArrayList<Fichier>();

				File artist = new File(mainpath+"/"+f.getName());
				File[] listsong = artist.listFiles();

				for(File f2 : listsong){
					//System.out.println("	"+f2.getName());
										 //titresong , nomartist
					titres.add(new Fichier(f2.getName(),f.getName()));
				}
														//nomartist , sestitres
				ArtistTitle artistTitle = new ArtistTitle(f.getName(),titres);
				retour.add(artistTitle);
			}
		}

		MusicVideoPack mvp = new MusicVideoPack(nomclient,retour);
		return mvp;
	}


// --------------------------------------------------------------------------------

	/*

	public void Envoyer_Vide(ObjectOutputStream oos)throws Exception{
		Response resp = null;
		oos.writeObject(resp);
		oos.flush();
	}

	public void Envoyer_File_Response(DataOutputStream dos,ObjectOutputStream oos,Response resp)throws Exception{
		oos.writeObject(resp);
		oos.flush();
		
		String path = resp.getSong().getPath();	//alaina ny chemin misy anle fichier ato @ server
		this.sendFile_for_Client(dos,path);
	}

	private void sendFile_for_Client(DataOutputStream dos,String path) throws Exception{
		int bytes = 0;
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);


		// send file size
		dos.writeLong(file.length());
		System.out.println("Taille ficher: "+file.length());

		//break file into chunks
		byte[] buffer = new byte[4*1024];
		while((bytes = fis.read(buffer)) != -1){
			dos.write(buffer,0,bytes);
			dos.flush();
		}
		fis.close();
	} 

	*/
}