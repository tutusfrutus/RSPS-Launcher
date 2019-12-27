package com.tutus.audio;

import com.tutus.Configuration;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class AudioManager {

    String audioFile = "Resources/Audio/launcherAudio.mp3";
    Media music = new Media(new File(audioFile).toURI().toString());
    MediaPlayer mediaPlayer = new MediaPlayer(music);

    public void PlayMusic(){
        if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            mediaPlayer.pause();
        } else {
            mediaPlayer.play();
        }
    }
}
