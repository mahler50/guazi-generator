package com.whn.cli.pattern;

public class Client {
    public static void main(String[] args) {
        // 创建接收者对象
        Device tv = new Device("TV");
        Device stereo = new Device("Stereo");

        // 创建具体指令对象
        TurnOnCommand turnOn = new TurnOnCommand(tv);
        TurnOffCommand turnOff = new TurnOffCommand(stereo);

        // 创建调用者
        RemoteController remoteController = new RemoteController();

        // 执行命令
        remoteController.setCommand(turnOn);
        remoteController.pressButton();

        remoteController.setCommand(turnOff);
        remoteController.pressButton();
    }
}
