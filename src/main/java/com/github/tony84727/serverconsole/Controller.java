package com.github.tony84727.serverconsole;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class Controller {
    @FXML
    private TextField launchCommand;

    @FXML
    private TextArea message;

    @FXML
    private Button launchButton;

    private final ServerProcess server;

    public Controller() {
        this.server = new ServerProcess();
    }

    public void action() {
        if (server.isRunning()) {
            this.stop();
            return;
        }
        this.start();
    }

    private void stop() {
        this.server.stop();
        this.reset();
    }

    private void start() {
        this.setToRunningMode();
        final String command = this.launchCommand.getText();
        this.server.setLaunchCommand(command.split(" "));
        try {
            this.server.launch().whenComplete((success, e) -> Platform.runLater(this::reset));
            final TextAreaPrinter printer = new TextAreaPrinter((message) -> Platform.runLater(() -> this.message.appendText(message)), this.server.getInputStream());
            printer.start();
        } catch (IOException e) {
            this.message.appendText(String.format("Fail to launch: %s, %s\n", command, e));
            this.reset();
        }
    }

    private void setToRunningMode() {
        this.launchCommand.setEditable(false);
        this.launchButton.setDisable(true);
    }

    private void reset() {
        this.launchCommand.setEditable(true);
        this.launchButton.setDisable(false);
    }
}
