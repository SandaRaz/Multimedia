package lib.audio;

import lib.thread.*;

import javazoom.jl.decoder.*;
import javazoom.jl.player.*;
import java.io.*;

import javazoom.jl.player.advanced.*;

public class MyAdvancedPlayer extends AdvancedPlayer{
    int size;
    int buffer;

    

	public MyAdvancedPlayer(DataInputStream dis) throws Exception{
        super(dis);
	}

    @Override
    public boolean play(int start,int end) throws JavaLayerException{
        boolean ret = true;
        int offset = start;
        while(offset-- > 0 && ret) ret = this.skipFrame();
        return play(end - start);
    }

}