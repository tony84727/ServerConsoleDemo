package com.github.tony84727.serverconsole;

import javafx.scene.control.TextArea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.function.Consumer;

public class TextAreaPrinter {
    private final InputStream input;
    private final Consumer<String> consumer;
    private boolean running = true;

    public TextAreaPrinter(Consumer<String> consumer, InputStream input) {
        this.input = input;
        this.consumer= consumer;
    }

    public void start() {
        new Thread(() -> {
            final InputStreamReader inputStreamReader = new InputStreamReader(this.input);
            final BufferedReader reader = new BufferedReader(inputStreamReader);
            try {
                for (String content = reader.readLine(); running && content != null; content = reader.readLine()) {
                    this.consumer.accept(content);
                }
            } catch (IOException e) {
                this.consumer.accept("Server output forwarding crashed!\n");
            }
        }).start();
    }
    public void stop() {
        this.running = false;
    }
}
