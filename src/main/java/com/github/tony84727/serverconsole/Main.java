package com.github.tony84727.serverconsole;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        URL sceneFile = getClass().getClassLoader().getResource("console.fxml");
        FXMLLoader loader = new FXMLLoader(sceneFile);
        Parent root = loader.load();
        Controller controller = loader.getController();
        Scene scene = new Scene(root, 640, 480);
        stage.setTitle("Server Console");
        stage.setScene(scene);
        stage.setOnHidden(e -> {
            // shutdown the child process
            controller.shutdown();
            Platform.exit();
        });
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}