package com.whn.maker.generator.file;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ArrayUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * 静态文件生成类
 */
public class StaticFileGenerator {


    /**
     * 通过Hutool进行文件拷贝
     * @param inputPath 输入文件路径
     * @param outputPath 输出文件路径
     */
    public static void copyFilesByHutool(String inputPath, String outputPath) {
        FileUtil.copy(inputPath, outputPath, false);
    }
}