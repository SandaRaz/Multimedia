package lib.inc;

import java.io.*;
import java.util.stream.*;
import java.util.*;
import java.text.SimpleDateFormat;

import com.mpatric.mp3agic.*;
import org.apache.tika.*; 
import javazoom.jl.decoder.*;
import javazoom.jl.player.*;

public class Function{
	public Function(){

	}

	public String generateName(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy HH:mm:ss");
		Date date = new Date();
		String temp = sdf.format(date);
		String[] tab = temp.split("[- :]");

		String nom = "";
		for(String s : tab){
			nom += s;
		}
		return nom;
	}

	public String getVraiDuree(long duree){
		SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm:ss");

		long mm = (duree % 3600) / 60;
		long ss = duree % 60;
		String mmss = String.format("%02d:%02d",mm,ss);
		
		return mmss;
	}

	public ArrayList<String> retrieveMetaData(String localname,File song) throws Exception{
		ArrayList<String> info = new ArrayList<String>();

		Mp3File mp3 = new Mp3File(song.getPath());
		if(mp3.hasId3v2Tag()){
			ID3v2 id3v2Tag = mp3.getId3v2Tag();

			if(id3v2Tag.getTitle() == null || id3v2Tag.getTitle() == ""){
				info.add(localname);
			}else{
				info.add(id3v2Tag.getTitle());
			}

			if(id3v2Tag.getArtist() == null || id3v2Tag.getArtist() == ""){
				info.add("Artiste inconnue");
			}else{
				info.add(id3v2Tag.getArtist());
			}

			if(id3v2Tag.getAlbum() == null || id3v2Tag.getAlbum() == ""){
				info.add("Album inconnue");
			}else{
				info.add(id3v2Tag.getAlbum());
			}

			info.add(getVraiDuree(mp3.getLengthInSeconds()));

			if(id3v2Tag.getYear() == null || id3v2Tag.getYear() == ""){
				info.add("");
			}else{
				info.add(id3v2Tag.getYear());
			}	
			
		}else if(!mp3.hasId3v2Tag()){
			info.add(localname);
			info.add("Artiste inconnue");
			info.add("Album inconnue");
			info.add(getVraiDuree(mp3.getLengthInSeconds()));
			info.add("");
		}

		return info;
	}

	public byte[] streamToBytes(String path){
		InputStream inputstream = null;
		try{
			inputstream = new FileInputStream(path);
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		File tmp = new File(path);
		long taillefichier = tmp.length();

		byte[] buffer = new byte[8*1024];
		int bytesRead;
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		try{
			while(taillefichier > 0 && (bytesRead = inputstream.read(buffer)) != 1){
				output.write(buffer, 0, bytesRead);
				taillefichier -= bytesRead;
			}
		}catch(IOException e){
			e.printStackTrace();
		}

		byte[] bytes = output.toByteArray();
		System.out.println(bytes.length);

		return bytes;
	}

	public File findTheMusic(String path,String artist,String title){
		File thesong = null;

		//String path = "./AllMusics/";

		ArrayList<File> listArtist = this.ListArtist(path);

		int i = this.getSimilarIndex(listArtist,artist);
		if(i != 404){
			File art = listArtist.get(i);
			String pathArtist = art.toString();

			ArrayList<File> listSong = this.ListSong(pathArtist);
			
			int j = this.getSimilarIndex(listSong,title);
			if(j != 404){
				thesong = listSong.get(j);
			}else{
				System.out.println("Aucun resultat");
			}
		}else{
			System.out.println("Aucun resultat");
		}

		return thesong;
	}

	public ArrayList<File> ListArtist(String path){
		File dir = new File(path);
		File[] files = dir.listFiles();

		ArrayList<File> list = new ArrayList<File>();
		for(File f : files){
			if(f.isDirectory()) list.add(f);
		}
		return list;
	}

	public ArrayList<File> ListSong(String path){
		File dir = new File(path);
		File[] files = dir.listFiles();
		Tika tika = new Tika();

		ArrayList<File> list = new ArrayList<File>();
		for(File f : files){
			String filepath = f.toString();
			String type = tika.detect(filepath);
			if((type.toLowerCase().contains("audio/mpeg"))){
				list.add(f);
			}
		}
		return list;
	}

	public double similarityPlus(String s1,String s2){ // retourn pourcentage de similarite
		double totale = 0;
		int lmin = 0;

		double equiv = 0;
		String[] split1 = s1.split(" ");
		String[] split2 = s2.split(" ");
		int ls1 = split1.length;
		int ls2 = split2.length;
		if(ls1 > ls2){
			totale=ls1;
			lmin = ls2;
		}else if(ls1 == ls2){
			totale = ls1;
			lmin = ls1;	
		}else{
			totale=ls2;
			lmin = ls1;
		}

		for(int i=0;i<split1.length;i++){
			int br = 0;
			for(int j=0;j<split2.length;j++){
				if(split1[i].toLowerCase().contains(split2[j].toLowerCase()) || split2[j].toLowerCase().contains(split1[i].toLowerCase())){
					if(split1[i] != "" && split2[j] != ""){
						equiv++;
					}
					if((equiv == totale)){
						//System.out.println(split1[i]+" equiv "+split2[j]);
						br = 1;
						break;
					}
				}
			}
			if(br == 1){
				break;
			}
		}
		//System.out.println("equiv : "+equiv+" totale: "+totale);
		return (equiv/totale)*100;
	}

	public int getSimilarIndex(ArrayList<File> listes,String mot){		// if return 404 : mp3 not found
		
		double[] valeur = new double[listes.size()]; 
		int ind = 0;
		for(File f : listes){
			String nomficher = f.toString();
			valeur[ind] = this.similarityPlus(nomficher,mot);  // entrer la pourcentage de similarite entre la requette et le syntaxe
			ind++;
		}

		OptionalDouble optvalmax = Arrays.stream(valeur).max(); // maka ny maximum de valeur anaty tableau
		double valmax = optvalmax.orElse(-1);					// convertir un OptionalDouble en double

		int similar = 404; // not found
		if(valmax >= 10 && valmax <= 100){
			for(int i=0;i<valeur.length;i++){
				if(valmax == valeur[i]){
					similar = i;
				}
			}
		}
		//System.out.println("valmax "+valmax);
		return similar;
	}
}