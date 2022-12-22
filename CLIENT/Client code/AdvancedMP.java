package lib.audio;

import lib.thread.*;

import javazoom.jl.decoder.*;
import javazoom.jl.player.*;
import java.io.*;

import javazoom.jl.player.advanced.*;

public class AdvancedMP{
	DataInputStream dis;
    ObjectInputStream ois;

    AdvancedPlayer player;

    int size;
    int buffer;

    long duree;
    long pause;

    int pausedOnFrame = 0;

    public Thread playThread;

    boolean isplayed = true;
    boolean boutonPlay = true;

    public DataInputStream getDis(){
    	return this.dis;
    }
    public void setDis(DataInputStream d){
    	this.dis = d;
    }

    public ObjectInputStream getOis(){
        return this.ois;
    }
    public void setOis(ObjectInputStream o){
        this.ois = o;
    }

    public AdvancedPlayer getPlayer(){
    	return this.player;
    }
    public void setPlayer(AdvancedPlayer p){
    	this.player = p;
    }

    public long getDuree(){
    	return this.duree;
    }

    public boolean getIsPlayed(){
    	return this.isplayed;
    }
    public void setIsPlayed(boolean b){
    	this.isplayed = b;
    }

	public AdvancedMP(DataInputStream dis){
		this.setDis(dis);

		try{
          	this.setPlayer(new AdvancedPlayer(this.getDis()));
          	this.getPlayer().setPlayBackListener(new PlaybackListener(){
                @Override
                public void playbackFinished(PlaybackEvent event){
                    pausedOnFrame = event.getFrame();
                }
            });
        }catch(Exception e){
        	e.printStackTrace();
        }

		this.playThread = new Thread(runnablePlay);
	}

    public AdvancedMP(ObjectInputStream ois){
        this.setOis(ois);

        try{
            this.setPlayer(new AdvancedPlayer(this.getOis()));
            this.getPlayer().setPlayBackListener(new PlaybackListener(){
                @Override
                public void playbackFinished(PlaybackEvent event){
                    pausedOnFrame = event.getFrame();
                }
            });
        }catch(Exception e){
            e.printStackTrace();
        }

        this.playThread = new Thread(runnablePlay);
    }

    public AdvancedMP(DataInputStream dis,int s,int b){
        this.setDis(dis);
        this.size = s;
        this.buffer = b;

        try{
            this.setPlayer(new AdvancedPlayer(this.getDis()));
        }catch(Exception e){
            e.printStackTrace();
        }

        this.playThread = new Thread(runnablePlay);
    }

	public void PlayMusic() throws Exception{
		System.out.println("	Music Started");
		this.setIsPlayed(true);
		this.playThread.start();
	}

	public void StopMusic() throws Exception{
		this.setIsPlayed(false);
		//this.getPlayer().stop();
		//this.playThread = null;
		System.out.println("	Music Stoped");
	}

	Runnable runnablePlay = new Runnable() {
        @Override
        public void run() {
        	try{
        		System.out.println("playing ...");
                getPlayer().play();
            }catch(Exception e){
            	e.printStackTrace();
            }
        }
    };

}