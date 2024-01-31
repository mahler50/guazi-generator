package com.whn.generator;

import com.whn.model.MainTemplateConfig;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

/**
 * 核心生成器
 */
public class MainGenerator {
    /**
     *
     * @param model 数据模型
     * @throws IOException
     * @throws TemplateException
     */
    public static void doGenerate(Object model) throws IOException, TemplateException {
        String inputRootPath = "D:\\JAVA\\workspace\\guazi-generator\\guazi-generator-demo-projects\\acm-template-pro";
        String outputRootPath = "D:\\JAVA\\workspace\\guazi-generator";

        String inputPath;
        String outputPath;

        inputPath = new File(inputRootPath, "src/com/whn/acm/MainTemplate.java.ftl").getAbsolutePath();
        outputPath = new File(outputRootPath, "src/com/whn/acm/MainTemplate.java").getAbsolutePath();
        DynamicGenerator.doGenerate(inputPath, outputPath, model);

        inputPath = new File(inputRootPath, ".gitignore").getAbsolutePath();
        outputPath = new File(outputRootPath, ".gitignore").getAbsolutePath();
        StaticGenerator.copyFilesByHutool(inputPath, outputPath);

        inputPath = new File(inputRootPath, "README.md").getAbsolutePath();
        outputPath = new File(outputRootPath, "README.md").getAbsolutePath();
        StaticGenerator.copyFilesByHutool(inputPath, outputPath);
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
