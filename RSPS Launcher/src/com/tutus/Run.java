package com.tutus;

import com.tutus.download.cache.CacheDownloader;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;


public class Run extends Application implements EventHandler<ActionEvent> {

    Button launchButton;

    public static void main(String[] args) {
        System.out.println("Launched the program");
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Tutus Launcher");
        launchButton = new Button();
        launchButton.setText("Download File Test");
        launchButton.setOnAction(this);

        StackPane layout = new StackPane();
        layout.getChildren().add(launchButton);

        Scene mainScene = new Scene(layout, 300, 250);
        primaryStage.setScene(mainScene);
        primaryStage.show();

    }

    @Override
    public void handle(ActionEvent event) {
        if(event.getSource() == launchButton){
            System.out.println("YEEET");
            try {
                CacheDownloader.downloadCache();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
