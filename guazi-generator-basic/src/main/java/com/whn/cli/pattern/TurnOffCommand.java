package com.whn.cli.pattern;

/**
 * TurnOff指令类
 */
public class TurnOffCommand implements Command{
    private Device device;

    public TurnOffCommand(Device device) {
        this.device = device;
    }
    @Override
    public void execute() {
        device.turnOff();
    }
}
