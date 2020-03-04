package com.tutus;

import com.tutus.versioncontrol.cache.CacheVersionTask;
import com.tutus.versioncontrol.client.ClientVersionTask;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class Controller {

    CacheVersionTask cacheVersionTask = new CacheVersionTask();
    ClientVersionTask clientVersionTask = new ClientVersionTask();

    @FXML
    private ProgressBar progressMain;

    @FXML
    private void launchButton(ActionEvent event) {
        try {
            progressMain.setProgress(0);
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
    private void websiteButton(ActionEvent event){
        try {
            Desktop.getDesktop().browse(new URL("https://runelife.net/").toURI());
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @FXML
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
