package com.whn.generator;

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
public class StaticGenerator {


    /**
     * 通过Hutool进行文件拷贝
     * @param inputPath 输入文件路径
     * @param outputPath 输出文件路径
     */
    public static void copyFilesByHutool(String inputPath, String outputPath) {
        FileUtil.copy(inputPath, outputPath, false);
    }

    /**
     * 递归实现文件拷贝
     * @param inputPath 输入文件路径
     * @param outputPath 输出文件路径
     */
    public static void copyFileByRecursive(String inputPath, String outputPath) {
        File inputFile = new File(inputPath);
        File outputFile = new File(outputPath);
        try {
            copyFileByRecursive(inputFile, outputFile);
        } catch (Exception e) {
            System.out.println("文件复制失败");
            e.printStackTrace();
        }
    }

    /**
     * 创建目录，遍历文件夹，辅助到目标路径
     * @param inputFile 输入文件
     * @param outputFile 输出文件
     * @throws IOException
     */
    private static void copyFileByRecursive(File inputFile, File outputFile) throws IOException {
        // 区分是文件还是目录
        if (inputFile.isDirectory()) {
            System.out.println(inputFile.getName());
            File destOutPutFile = new File(outputFile, inputFile.getName());
            // 如果是目录，先创建目标目录
            if (!destOutPutFile.exists()) {
                destOutPutFile.mkdir();
            }
            // 获取目录下的所有文件和子目录
            File[] files = inputFile.listFiles();
            // 无子文件，直接结束
            if (ArrayUtil.isEmpty(files)) {
                return;
            }
            for (File file :
                    files){
                copyFileByRecursive(file, destOutPutFile);
            }
        } else {
            // 是文件，直接复制到目标目录下
            Path destPath = outputFile.toPath().resolve(inputFile.getName());
            Files.copy(inputFile.toPath(), destPath, StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
