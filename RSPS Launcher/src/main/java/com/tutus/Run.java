package com.tutus;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Run extends Application {

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
    }
}
