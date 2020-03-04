package com.tutus;

import com.tutus.audio.AudioManager;
import com.tutus.versioncontrol.cache.CacheVersionTask;
import com.tutus.versioncontrol.client.ClientVersionTask;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class Controller {

    CacheVersionTask cacheVersionTask = new CacheVersionTask();
    ClientVersionTask clientVersionTask = new ClientVersionTask();
    AudioManager audioManager = new AudioManager();

    @FXML
    private void launchButton(ActionEvent event) {
        try {
            cacheVersionTask.checkCacheVersionTask();
            clientVersionTask.checkClientVersionTask();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void discordButton(ActionEvent event) {
        try {
            Desktop.getDesktop().browse(new URL("https://discord.gg/vkVVcHZ").toURI());
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void musicButton(ActionEvent event) {
        audioManager.playMusic();
    }

    public void forceButton(ActionEvent actionEvent) {
        try {
            Configuration.forceUpdate = true;
            cacheVersionTask.checkCacheVersionTask();
            clientVersionTask.checkClientVersionTask();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
