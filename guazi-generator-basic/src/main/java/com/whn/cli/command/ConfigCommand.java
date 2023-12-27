package com.whn.cli.command;

import cn.hutool.core.util.ReflectUtil;
import com.whn.model.MainTemplateConfig;
import picocli.CommandLine.Command;

import java.lang.reflect.Field;

@Command(name = "config", description = "查看参数信息", mixinStandardHelpOptions = true)
public class ConfigCommand implements Runnable{
    @Override
    public void run() {
        // 实现config命令逻辑
        System.out.println("查看参数信息");
        Field[] fields = ReflectUtil.getFields(MainTemplateConfig.class);
        for (Field field:
             fields) {
            System.out.println("字段名称：" + field.getName());
            System.out.println("字段类型：" + field.getType());
            System.out.println("---");
        }
    }
}
