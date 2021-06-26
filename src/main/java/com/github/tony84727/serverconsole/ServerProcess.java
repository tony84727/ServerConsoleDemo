package com.github.tony84727.serverconsole;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ServerProcess {
    private String launchCommand;
    private Process process;

    public ServerProcess() {
    }

    public void setLaunchCommand(String launchCommand) {
        this.launchCommand = launchCommand;
    }

    public void launch() throws IOException {
        if (this.process != null) {
            throw new IllegalStateException("a server process already started");
        }
        this.process = new ProcessBuilder(launchCommand).start();
    }

    public void stop() {
        if (this.process == null) {
            throw new IllegalStateException("there's no server process to stop");
        }
        this.process.destroy();
    }

    public InputStream getInputStream() {
        return this.process.getInputStream();
    }

    public OutputStream getOutputStream() {
        return this.process.getOutputStream();
    }
}
