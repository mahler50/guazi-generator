package com.whn.maker.generator;

import com.whn.maker.generator.main.GenerateTemplate;

public class MainGenerator extends GenerateTemplate {
    @Override
    protected String buildDist(String outputPath, String sourceCopyDestPath, String jarPath, String shellOutputFilePath) {
        String distPath = super.buildDist(outputPath, sourceCopyDestPath, jarPath, shellOutputFilePath);
        return distPath;
    }
}
