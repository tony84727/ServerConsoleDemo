package com.github.tony84727.serverconsole;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        URL sceneFile = getClass().getClassLoader().getResource("console.fxml");
        Parent root = FXMLLoader.load(Objects.requireNonNull(sceneFile));
        Scene scene = new Scene(root, 640, 480);
        stage.setTitle("Server Console");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}