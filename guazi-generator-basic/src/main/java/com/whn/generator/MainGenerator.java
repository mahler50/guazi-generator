package com.whn.generator;

import com.whn.model.MainTemplateConfig;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

/**
 * 核心生成器
 */
public class MainGenerator {
    public static void doGenerate(Object model) throws IOException, TemplateException {
        String projectPath = System.getProperty("user.dir");
        // 输入路径
        String inputPath = projectPath + File.separator +"guazi-generator-demo-projects" + File.separator + "acm-template";
        String outputPath = projectPath;
        // 生成静态文件
        StaticGenerator.copyFilesByHutool(inputPath, outputPath);
        // 生成动态文件
        String dynamicInputFilePath = projectPath + File.separator + "guazi-generator-basic"+ File.separator + "src/main/resources/templates/MainTemplate.java.ftl";
        String dynamicOutputFilePath = projectPath + File.separator + "acm-template/src/com/whn/acm/MainTemplate.java";
        DynamicGenerator.doGenerate(dynamicInputFilePath, dynamicOutputFilePath, model);
    }

    public static void main(String[] args) throws TemplateException, IOException {
        // 创建数据模型
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("whn");
        mainTemplateConfig.setLoop(true);
        mainTemplateConfig.setOutputText("求和结果为：");
        doGenerate(mainTemplateConfig);
    }
}
