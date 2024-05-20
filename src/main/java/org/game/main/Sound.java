package org.game.main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {

    // Field of clips
    private Clip[] clips = new Clip[30];

    public Sound() {
        // Load all sound while initializing
        for (int i = 0; i < clips.length; i++) {
            try {
                URL url = getClass().getResource("/sound/" + getSoundFileName(i));
                if (url != null) {
                    AudioInputStream ais = AudioSystem.getAudioInputStream(url);
                    clips[i] = AudioSystem.getClip();
                    clips[i].open(ais);
                }
            } catch (Exception e) {
                System.err.println("Error loading sound: " + e);
            }
        }
    }

    private String getSoundFileName(int index) {
        switch (index) {
            case 0: return "BlueBoyAdventure.wav";
            case 1: return "coin.wav";
            case 2: return "powerup.wav";
            case 3: return "unlock.wav";
            case 4: return "fanfare.wav";
            case 5: return "hitmonster.wav";
            case 6: return "receivedamage.wav";
            case 7: return "levelup.wav";
            case 8: return "cursor.wav";
            case 9: return "cuttree.wav";
            default: return null;
        }
    }

    public void play(int index){
        if (index >= 0 && index < clips.length && clips[index] != null) {
            if (clips[index].isRunning()) {
                clips[index].stop();
            }
            clips[index].setFramePosition(0);
            clips[index].start();
        }
    }

    public void loop(int index){
        if (index >= 0 && index < clips.length && clips[index] != null) {
            clips[index].loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void stop(int index){
        if (index >= 0 && index < clips.length && clips[index] != null) {
            clips[index].stop();
        }
    }
}

