package com.tutus.audio;

import com.tutus.Configuration;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class AudioManager {

    public static void DownloadMusic(){

    }

    public static void PlayMusic(){
        String audioFile = Configuration.AUDIO_SAVE_DIR + "/" + Configuration.AUDIO_SAVE_NAME;
        Media music = new Media(new File(audioFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(music);
        mediaPlayer.play();
    }
}
