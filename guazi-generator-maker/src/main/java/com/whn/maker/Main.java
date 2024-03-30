package com.whn.maker;

import com.whn.maker.generator.MainGenerator;
import com.whn.maker.generator.main.ZipGenerator;
import freemarker.template.TemplateException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws TemplateException, IOException, InterruptedException {
//        MainGenerator mainGenerator = new MainGenerator();
        ZipGenerator zipGenerator = new ZipGenerator();
        zipGenerator.doGenerate();
    }
}
