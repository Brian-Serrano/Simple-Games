package Util;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Audio {
    private Clip clip;
    private long clipTime;

    public Audio(String filename) {
        try {
            File audioFile = new File(filename);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clipTime = 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void play() {
        clip.setMicrosecondPosition(clipTime);
        clip.start();
    }
    
    public void playInfinitely() {
    	clip.setMicrosecondPosition(clipTime);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void pause() {
        clipTime = clip.getMicrosecondPosition();
        clip.stop();
    }

    public void setTime(long microseconds) {
        clipTime = microseconds;
    }
}