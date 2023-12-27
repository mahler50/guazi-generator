package com.whn.cli.command;

import cn.hutool.core.io.FileUtil;
import picocli.CommandLine.Command;

import java.io.File;
import java.util.List;

@Command(name = "list", description = "查看文件列表", mixinStandardHelpOptions = true)
public class ListCommand implements Runnable{
    @Override
    public void run() {
        String projectPath = System.getProperty("user.dir");
        // 整个项目的根路径
        File parentFile = new File(projectPath).getParentFile();
        // 输入路径
        File inputPath = new File(parentFile, "guazi-generator-demo-projects/acm-template");
        List<File> files = FileUtil.loopFiles(inputPath);
        for (File file :
                files) {
            System.out.println(file);
        }
    }
}
