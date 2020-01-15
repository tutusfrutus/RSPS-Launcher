package com.tutus.audio;

import com.tutus.Configuration;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.net.URL;

public class AudioManager {

    private Media launcherMusic = new Media((Configuration.launcherAudio).toString());
    MediaPlayer mediaPlayer = new MediaPlayer(launcherMusic);

    public void playMusic(){
        if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            mediaPlayer.pause();
        } else {
            mediaPlayer.play();
            mediaPlayer.setVolume(Configuration.audioVolume);
        }
    }
}
