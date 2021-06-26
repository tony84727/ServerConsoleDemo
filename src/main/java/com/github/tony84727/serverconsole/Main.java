package com.github.tony84727.serverconsole;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        String javafxVersion = System.getProperty("javafx.version");
        String javaVersion = System.getProperty("java.version");
        Label l = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        TextArea message = new TextArea();
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