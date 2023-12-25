package com.whn.generator;

import com.whn.model.MainTemplateConfig;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 动态文件生成类
 */
public class DynamicGenerator {
    public static void main(String[] args) throws IOException, TemplateException {
        // 设置模版的路径
        String projectPath = System.getProperty("user.dir") + File.separator + "guazi-generator-basic";
        String inputPath = projectPath + File.separator + "src/main/resources/templates/MainTemplate.java.ftl";
        String outputPath = projectPath + File.separator + "MainTemplate.java";
        // 创建数据模型
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("whn");
        mainTemplateConfig.setLoop(true);
        mainTemplateConfig.setOutputText("求和结果为：");
        doGenerate(inputPath, outputPath, mainTemplateConfig);
    }
    public static  void doGenerate(String inputPath, String outputPath, Object model) throws IOException, TemplateException{
        // new 一个Configuration对象，制定版本号
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);
        // 设置模版的路径
        File templateDir = new File(inputPath).getParentFile();
        configuration.setDirectoryForTemplateLoading(templateDir);
        // 设置模版文件使用的字符集
        configuration.setDefaultEncoding("utf-8");
        configuration.setNumberFormat("0.######");

        // 创建模板对象，加载指定模板
        String templateName = new File(inputPath).getName();
        Template template = configuration.getTemplate(templateName);

        // 生成
        Writer out = new FileWriter(outputPath);
        template.process(model, out);

        out.close();
    }
}
