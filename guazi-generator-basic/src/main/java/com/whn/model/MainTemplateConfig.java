package com.whn.model;

import lombok.Data;

/**
 * 动态模板配置类
 */
@Data
public class MainTemplateConfig {

    /**
     * 作者注释
     */
    private String author = "whn";

    /**
     * 输出信息
     */
    private String outputText = "输出结果=";

    /**
     * 是否生成循环
     */
    private boolean loop;

}
