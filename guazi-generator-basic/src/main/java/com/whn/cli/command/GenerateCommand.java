package com.whn.cli.command;

import cn.hutool.core.bean.BeanUtil;
import com.whn.generator.MainGenerator;
import com.whn.model.MainTemplateConfig;
import lombok.Data;
import picocli.CommandLine.Option;
import picocli.CommandLine.Command;

import java.util.concurrent.Callable;

@Command(name = "generate", description = "生成代码", mixinStandardHelpOptions = true)
@Data
public class GenerateCommand implements Callable<Integer> {
    /**
     * 作者注释
     */
    @Option(names = {"-a", "--author"}, description = "作者注释", arity = "0..1", interactive = true, echo = true)
    private String author;

    /**
     * 输出信息
     */
    @Option(names = {"-o", "--outputText"}, description = "输出文本", arity = "0..1", interactive = true, echo = true)
    private String outputText;

    /**
     * 是否生成循环
     */
    @Option(names = {"-l", "--loop"}, description = "是否循环", arity = "0..1", interactive = true, echo = true)
    private boolean loop;


    @Override
    public Integer call() throws Exception {
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        BeanUtil.copyProperties(this, mainTemplateConfig);
        System.out.println("配置信息" + mainTemplateConfig);
        MainGenerator.doGenerate(mainTemplateConfig);
        return 0;
    }
}
