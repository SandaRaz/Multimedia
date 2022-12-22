package lib.thread;

import lib.audio.*;

import javazoom.jl.decoder.*;
import javazoom.jl.player.*;
import java.io.*;

import javazoom.jl.player.advanced.*;

public class Lecture extends Thread{
	MusicPlayer musicplayer;

	public MusicPlayer getMusicplayer(){
		return this.musicplayer;
	}
	public void setMusicplayer(MusicPlayer mp){
		this.musicplayer = mp;
	}

	public Lecture(){

	}

	public Lecture(MusicPlayer mp){
		this.setMusicplayer(mp);
	}

	@Override
	public void run(){
		try{
          	this.getMusicplayer().setPlayer(new Player(this.getMusicplayer().getDataInputStream()));
        	this.getMusicplayer().setDuree(this.getMusicplayer().getDataInputStream().available());
          	this.getMusicplayer().getPlayer().play();
        }catch(Exception e){
        	e.printStackTrace();
        }
	}
			
}