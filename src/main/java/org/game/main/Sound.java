package org.game.main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

public class Sound {

    // Field of clips
    private Clip[] clips = new Clip[30];
    private FloatControl fc;
    public int volumeScale = 3;
    private float volume;

    public Sound() {
        // Load all sound while initializing
        loadSound();
        checkVolume();
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

    public void play(int index) {
        if (index >= 0 && index < clips.length && clips[index] != null) {
            if (clips[index].isRunning()) {
                clips[index].stop();
            }
            clips[index].setFramePosition(0);
            clips[index].start();
        }
    }

    public void loop(int index) {
        if (index >= 0 && index < clips.length && clips[index] != null) {
            clips[index].loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void stop(int index) {
        if (index >= 0 && index < clips.length && clips[index] != null) {
            clips[index].stop();
        }
    }

    public void checkVolume() {
        switch (volumeScale) {
            case 0: volume = -80f; break;
            case 1: volume = -20f; break;
            case 2: volume = -12f; break;
            case 3: volume = -5f; break;
            case 4: volume = 1f; break;
            case 5: volume = 6f; break;
        }
        if (fc != null) {
            fc.setValue(volume);
        }
    }

    public void loadSound() {
        // Load all sound while initializing
        for (int i = 0; i < clips.length; i++) {
            try {
                URL url = getClass().getResource("/sound/" + getSoundFileName(i));
                if (url != null) {
                    AudioInputStream ais = AudioSystem.getAudioInputStream(url);
                    clips[i] = AudioSystem.getClip();
                    clips[i].open(ais);
                    // Set initial volume control for the first clip
                    if (fc == null) {
                        fc = (FloatControl) clips[i].getControl(FloatControl.Type.MASTER_GAIN);
                    }
                }
            } catch (Exception e) {
                System.err.println("Error loading sound: " + e);
            }
        }
    }

}
