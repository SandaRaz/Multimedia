package lib.audio;

import javazoom.jl.decoder.*;
import javazoom.jl.player.*;
import java.io.*;

import javazoom.jl.player.advanced.*;

public class AdvancedMP{
	DataInputStream dis;

    AdvancedPlayer player;
    long duree;
    long pause;

    public Thread playThread;
    public Thread resumeThread;

    boolean isplayed;

    public DataInputStream getDis(){
    	return this.dis;
    }
    public void setDis(DataInputStream d){
    	this.dis = d;
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

	public void StopMusic(){
		System.out.println("	Music Stoped");
		//this.getPlayer().stop();
		this.setIsPlayed(false);
		this.playThread = new Thread(runnablePlay);
		//this.playThread = null;
	}

	Runnable runnablePlay = new Runnable() {
        @Override
        public void run() {
        	try{
              	while(getIsPlayed() == true){
              		if(getDis().available() != 0){
		                System.out.println("playing");
		                getPlayer().play();
		            } else if(getDis().available() <= 0){
		                StopMusic();
		            }
              	}
            }catch(Exception e){
            	e.printStackTrace();
            }
        }
    };
}