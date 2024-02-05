package com.whn.maker.generator;

import com.whn.maker.generator.main.GenerateTemplate;

public class MainGenerator extends GenerateTemplate {
    @Override
    protected void buildDist(String outputPath, String sourceCopyDestPath, String jarPath, String shellOutputFilePath) {
        System.out.println("Dont need dist version");
    }
}
