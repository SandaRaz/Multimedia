package lib.mysocket;

import lib.thread.*;
import lib.data.*;
import lib.inc.*;

import gui.*;
import gui.multimedia.*;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

import com.mpatric.mp3agic.*;

public class Myserver extends ServerSocket{
	Function myfunction = new Function();

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

		ArtistTitle woArtistDir = null;
		ArrayList<Fichier> woArtistTitre = new ArrayList<Fichier>();

		for(File f : listartist){
			if(f.isDirectory()){
				//System.out.println(f.getName());
				ArrayList<Fichier> titres = new ArrayList<Fichier>();

				File artist = new File(mainpath+"/"+f.getName());
				File[] listsong = artist.listFiles();

				for(File f2 : listsong){
					if(f2.getName().contains(".mp3") || f2.getName().contains(".m4a")){
						//System.out.println("	"+f2.getName());
											 //titresong , nomartist
						titres.add(new Fichier(f2.getName(),f.getName()));
					}
				}
														//nomartist , sestitres
				ArtistTitle artistTitle = new ArtistTitle(f.getName(),titres);
				retour.add(artistTitle);

			}else if(f.isFile()){
				if(f.getName().contains(".mp3") || f.getName().contains(".m4a")){
					woArtistTitre.add(new Fichier(f.getName(),"woArtistDir"));
				}
			}
		}

		if(woArtistTitre.size() > 0){
			woArtistDir = new ArtistTitle("Autres",woArtistTitre);
			retour.add(woArtistDir);
		}


		MusicVideoPack mvp = new MusicVideoPack(nomclient,retour);
		return mvp;
	}

	public String getVraiDuree(long duree){
		SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm:ss");

		long mm = (duree % 3600) / 60;
		long ss = duree % 60;
		String mmss = String.format("%02d:%02d",mm,ss);
		
		return mmss;
	}

	public Metadata retrieveMetaData(String localname,File song) throws Exception{

		byte[] cover = null;
		ArrayList<String> tags = new ArrayList<String>();
		Mp3File mp3 = new Mp3File(song.getPath());
		if(mp3.hasId3v2Tag()){
			ID3v2 id3v2Tag = mp3.getId3v2Tag();

			if(id3v2Tag.getTitle() == null || id3v2Tag.getTitle() == ""){
				tags.add(localname);
			}else{
				tags.add(id3v2Tag.getTitle());
			}

			if(id3v2Tag.getArtist() == null || id3v2Tag.getArtist() == ""){
				tags.add("Artiste inconnue");
			}else{
				tags.add(id3v2Tag.getArtist());
			}

			if(id3v2Tag.getAlbum() == null || id3v2Tag.getAlbum() == ""){
				tags.add("Album inconnue");
			}else{
				tags.add(id3v2Tag.getAlbum());
			}

			tags.add(this.getVraiDuree(mp3.getLengthInSeconds()));

			if(id3v2Tag.getYear() == null || id3v2Tag.getYear() == ""){
				tags.add("");
			}else{
				tags.add(id3v2Tag.getYear());
			}

			cover =  id3v2Tag.getAlbumImage();
		}else if(!mp3.hasId3v2Tag()){
			tags.add(localname);
			tags.add("Artiste inconnue");
			tags.add("Album inconnue");
			tags.add(this.getVraiDuree(mp3.getLengthInSeconds()));
			tags.add("");
		}

		if(cover == null){
			String path = "./Rsc/menu2.jpg";
			cover = this.myfunction.streamToBytes(path);;
		}

		Metadata md = new Metadata(cover,tags);
		return md;
	}

	public void sendFile(DataOutputStream dos,String path) throws Exception{
		int bytes = 0;
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);


		// send file size
		dos.writeLong(file.length());
		dos.flush();
		System.out.println("Taille ficher: "+file.length());

		//break file into chunks
		byte[] buffer = new byte[4*1024];

		while((bytes = fis.read(buffer)) != -1){
			dos.write(buffer,0,bytes);
			dos.flush();
		}
		fis.close();
	}

	public FichierPack ListerVideo(String nomclient,String mainfolder){
		ArrayList<Fichier> retour = new ArrayList<Fichier>();

		String mainpath = mainfolder+"/Video";
		File folder = new File(mainpath);
		File[] listvideo = folder.listFiles();

		for(File f : listvideo){
			retour.add(new Fichier(f.getName()));
		}

		FichierPack fp = new FichierPack(nomclient,retour);
		return fp;
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