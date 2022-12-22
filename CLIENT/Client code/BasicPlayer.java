package lib.audio;

import javax.sound.sampled.Control;
import javax.sound.sampled.*;
import java.lang.reflect.Field;

import javazoom.jl.decoder.*;
import javazoom.jl.player.*;
import javazoom.jlgui.basicplayer.*;
import javazoom.jl.player.advanced.*;
import java.io.*;

public class BasicPlayer{
	File song;

	DataInputStream dataInputStream;

    Player player;
    float volumeValue = -37f;

    static AudioFormat format;
    static float rate = 44100.0f;
    static DataLine.Info dataLineInfo;
    static SourceDataLine sourceDataLine;

    long duree;
    long pause;

    AudioDevice device;
    FloatControl volControl;

    public Thread playThread;
    public Thread resumeThread;

    boolean isplayed;

    public File getSong(){
    	return this.song;
    }
    public void setSong(File sng){
    	this.song = sng;
    }

    public DataInputStream getDataInputStream(){
        return this.dataInputStream;
    }
    public void setDataInputStream(DataInputStream dis){
        this.dataInputStream = dis;
    }

    public Player getPlayer(){
    	return this.player;
    }
    public void setPlayer(Player p){
    	this.player = p;
    }

    public float getVolumeValue(){
        return this.volumeValue;
    }
    public void setVolumeValue(float v){
        this.volumeValue = v;
    }

    public long getDuree(){
    	return this.duree;
    }
    public void setDuree(long d){
    	this.duree = d;
    }

    public boolean isPlayed(){
    	return this.isplayed;
    }
    public void setIsPlayed(boolean b){
    	this.isplayed = b;
    }

    public BasicPlayer(DataInputStream dis) throws Exception{
    	this.dataInputStream = dis;

        JavaSoundAudioDevice a = new JavaSoundAudioDevice();
        this.device = FactoryRegistry.systemRegistry().createAudioDevice();

    	this.setPlayer(new Player(this.dataInputStream,this.device));
    	this.setDuree(this.dataInputStream.available());

    	this.playThread = new Thread(runnablePlay);
        this.resumeThread = new Thread(runnableResume);
    }


	public void PlayMusic() throws Exception{
		System.out.println("	Music Started");
		this.playThread.start();

		this.setIsPlayed(true);
	}

	public void PauseMusic() throws Exception{
		System.out.println("	Music Paused");
		this.pause = this.dataInputStream.available();
        this.getPlayer().close();

        this.setIsPlayed(false);
	}

	public void ResumeMusic(){
		System.out.println("	Music Resumed");
		this.resumeThread.start();

		this.setIsPlayed(true);
	}

	public void StopMusic(){
		System.out.println("	Music Stoped");
		if(this.getPlayer() != null){
            this.getPlayer().close();
        }
        
		this.setIsPlayed(false);
		this.playThread = new Thread(runnablePlay);
		this.resumeThread = new Thread(runnableResume);
	}

    public void setVolume(){
        if(this.volControl == null){
            Class<JavaSoundAudioDevice> clazz = JavaSoundAudioDevice.class;
            Field[] fields = clazz.getDeclaredFields();
            try{
                format = new AudioFormat(rate, 16, 2, true, false);
                dataLineInfo = new DataLine.Info(SourceDataLine.class, format);
                sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
                sourceDataLine.open(format);
                sourceDataLine.start();
                this.volControl = (FloatControl) sourceDataLine.getControl(FloatControl.Type.MASTER_GAIN);
                /*
                for(Field field : fields){
                    if(field.getName().contains("source")){
                        field.setAccessible(true);
                        source = (SourceDataLine) field.get(this.device);
                        System.out.println("field name >>> "+field.getName());
                        System.out.println("objet >>> "+field.get(this.device));
                        System.out.println("device >>> "+this.device);
                        field.setAccessible(false);
                        this.volControl = (FloatControl) source.getControl(FloatControl.Type.MASTER_GAIN);
                    }
                }
                */
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        if(this.volControl != null){
            float newGain = Math.min(Math.max(this.getVolumeValue(),this.volControl.getMinimum()) , this.volControl.getMaximum());
            System.out.println(this.volControl.getValue()+" to "+newGain);

            this.volControl.setValue(newGain);
        }
    }

     public void setVolume(float gain){
        if(this.volControl == null){
            Class<JavaSoundAudioDevice> clazz = JavaSoundAudioDevice.class;
            Field[] fields = clazz.getDeclaredFields();
            try{
                format = new AudioFormat(rate, 16, 2, true, false);
                dataLineInfo = new DataLine.Info(SourceDataLine.class, format);
                sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
                sourceDataLine.open(format);
                sourceDataLine.start();
                this.volControl = (FloatControl) sourceDataLine.getControl(FloatControl.Type.MASTER_GAIN);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        if(this.volControl != null){
            float newGain = Math.min(Math.max(gain,this.volControl.getMinimum()) , this.volControl.getMaximum());
            System.out.println(this.volControl.getValue()+" to "+newGain);

            this.volControl.setValue(newGain);
        }
    }

	Runnable runnablePlay = new Runnable() {
        @Override
        public void run() {
            try{
              	setPlayer(new Player(dataInputStream));
            	setDuree(dataInputStream.available());
              	getPlayer().play();
            }catch(Exception e){
            	e.printStackTrace();
            }
        }
    };
 
    Runnable runnableResume = new Runnable() {
        @Override
        public void run() {
            try {
            	setPlayer(new Player(dataInputStream));

                dataInputStream.skip(duree - pause);
                getPlayer().play();
            }catch(Exception e) {
                e.printStackTrace();
            } 
        }
    };
}