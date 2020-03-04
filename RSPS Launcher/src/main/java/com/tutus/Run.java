package com.tutus;

import com.tutus.audio.AudioManager;
import com.tutus.versioncontrol.cache.CacheVersionTask;
import com.tutus.versioncontrol.client.ClientVersionTask;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;

import java.net.URL;


public class Run extends Application {

    AudioManager audioManager = new AudioManager();

    public static void main(String[] args) {
        System.out.println("Launched the program");
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Loads the FXML File
        AnchorPane root = FXMLLoader.load(getClass().getResource("/FXML/AppDesign.fxml"));
        //Creates the Scene
        Scene mainScene = new Scene(root, Configuration.appWidth, Configuration.appHeight);
        //Loads the CSS file
        mainScene.getStylesheets().add(getClass().getResource("/CSS/AppStyling.css").toString());
        //Sets the Scene
        primaryStage.setScene(mainScene);
        //Sets title name
        primaryStage.setTitle(Configuration.LAUNCHER_TITLE);
        //Sets launcher icon
        primaryStage.getIcons().add(new Image(Configuration.iconImage.toString()));
        //Prevents resizing
        primaryStage.setResizable(false);
        //Displays the stage
        primaryStage.show();

        if (Configuration.enableAudio == true) {
            audioManager.playMusic();
        }
    }
}
