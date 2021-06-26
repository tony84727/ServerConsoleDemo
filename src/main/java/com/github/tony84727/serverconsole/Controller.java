package com.github.tony84727.serverconsole;

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

    private ServerProcess server;
    private TextAreaPrinter printer;

    public Controller() {
        this.server = new ServerProcess();
    }

    public void launch() {
        this.launchCommand.setEditable(false);
        this.launchButton.setDisable(true);
        final String command = this.launchCommand.getText();
        this.server.setLaunchCommand(command);
        try {
            this.server.launch();
            this.printer = new TextAreaPrinter(this.message::appendText, this.server.getInputStream());
            this.printer.start();
        } catch (IOException e) {
            this.message.appendText(String.format("Fail to launch: %s, %s\n", command, e));
            this.reset();
        }
    }

    private void reset() {
        this.launchCommand.setEditable(true);
        this.launchButton.setDisable(false);
    }
}
