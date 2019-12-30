package com.tutus;

import com.tutus.versioncontrol.CacheVersionTask;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.concurrent.ExecutorService;


public class Run extends Application implements EventHandler<ActionEvent> {

    Button launchButton;
    CacheVersionTask cacheVersionTask = new CacheVersionTask();

    public static void main(String[] args) {
        System.out.println("Launched the program");
        //Plays the Music if it's set to true
        if (Configuration.enableAudio == true){
           // AudioManager.PlayMusic();
        }
        launch(args);

    } //YouTube tutorial, cant take credits.

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Tutus Launcher");
        launchButton = new Button();
        launchButton.setText("Download File Test");
        launchButton.setOnAction(this);

        StackPane layout = new StackPane();
        layout.getChildren().add(launchButton);

        Scene mainScene = new Scene(layout, Configuration.appWidth, Configuration.appHeight);
        primaryStage.setScene(mainScene);
        primaryStage.show();

    }

    @Override
    public void handle(ActionEvent event) {
        if(event.getSource() == launchButton){
            System.out.println("Button Pressed");
            try {
                //CacheDownloader.downloadCache();
                //CacheVersion.run();
                //Platform.runLater(cacheVer);
                //ClientDownloader.downloadClient();
                cacheVersionTask.checkCacheVersionTask();
            } //catch (IOException e) {
            catch (Exception e) {
                e.printStackTrace();
            }
            //    e.printStackTrace();
            //}
        }

    }

}
