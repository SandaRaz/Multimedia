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
}