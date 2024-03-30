package com.whn.web;

import com.whn.web.common.ErrorCode;
import com.whn.web.exception.BusinessException;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

public class MyTest {
    @Test
    public void testProcess() {
        File scriptFile = new File("D:\\JAVA\\workspace\\guazi-generator\\guazi-generator-web-backend\\.temp\\use\\9\\dist\\acm-template-pro-generator\\generator.bat");
        String dataModelFilePath = "D:\\JAVA\\workspace\\guazi-generator\\guazi-generator-web-backend\\.temp\\use\\9\\dataModel.json";

        try {
            Set<PosixFilePermission> permissions = PosixFilePermissions.fromString("rwxrwxrwx");
            Files.setPosixFilePermissions(scriptFile.toPath(), permissions);
        } catch (Exception e) {

        }
        File scriptDir = scriptFile.getParentFile();
        String scriptAbsolutePath = scriptFile.getAbsolutePath().replace("\\", "/");
        String[] commands = new String[] {scriptAbsolutePath, "json-generate", "--file=" + dataModelFilePath};

        ProcessBuilder processBuilder = new ProcessBuilder(commands);
        processBuilder.directory(scriptDir);

        try {
            Process process = processBuilder.start();

            // 读取命令行输出
            InputStream inputStream = process.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }

            // 等待命令完成
            int exitCode = process.waitFor();
            System.out.println("命令执行结束， 退出码：" + exitCode);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "执行生成器脚本错误");
        }
    }
}
