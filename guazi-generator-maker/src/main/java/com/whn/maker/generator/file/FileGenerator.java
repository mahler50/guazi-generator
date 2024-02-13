package com.whn.maker.generator.file;

import com.whn.maker.model.DataModel;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

/**
 * 核心生成器
 */
public class FileGenerator {
    public static void doGenerate(Object model) throws IOException, TemplateException {
        String projectPath = System.getProperty("user.dir");
        // 项目根路径
        File parentFile = new File(projectPath).getParentFile();
        // 输入路径
        String inputPath = new File(parentFile, "guazi-generator-demo-projects/acm-template").getAbsolutePath();
        String outputPath = projectPath;
        // 生成静态文件
        StaticFileGenerator.copyFilesByHutool(inputPath, outputPath);
        // 生成动态文件
        String dynamicInputFilePath = projectPath + File.separator + "src/main/resources/templates/MainTemplate.java.ftl";
        String dynamicOutputFilePath = outputPath + File.separator + "acm-template/src/com/whn/acm/MainTemplate.java";
        DynamicFileGenerator.doGenerate(dynamicInputFilePath, dynamicOutputFilePath, model);
    }

}
