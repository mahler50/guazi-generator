package com.whn.cli.pattern;

/**
 * 远程操控类
 */
public class RemoteController {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();
    }
}
