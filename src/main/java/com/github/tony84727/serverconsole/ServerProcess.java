package com.github.tony84727.serverconsole;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.CompletableFuture;

public class ServerProcess {
    private String[] launchCommand;
    private Process process;

    public ServerProcess() {
    }

    public void setLaunchCommand(String[] launchCommand) {
        this.launchCommand = launchCommand;
    }

    public CompletableFuture<Boolean> launch() throws IOException {
        if (this.process != null && this.process.isAlive()) {
            throw new IllegalStateException("a server process already started");
        }
        this.process = new ProcessBuilder(launchCommand)
                .start();
        return this.process.onExit().handleAsync((p, throwable) -> throwable == null && p.exitValue() == 0);
    }

    public boolean isRunning() {
        return this.process != null && this.process.isAlive();
    }

    public void stop() {
        if (this.process == null) {
            throw new IllegalStateException("there's no server process to stop");
        }
        this.process.destroy();
    }

    public InputStream getInputStream() {
        this.ensureHasProcess();
        return this.process.getInputStream();
    }

    public OutputStream getOutputStream() {
        this.ensureHasProcess();
        return this.process.getOutputStream();
    }

    private void ensureHasProcess() {
        if (this.process == null) {
            throw new IllegalStateException("there's no server process");
        }
    }
}
