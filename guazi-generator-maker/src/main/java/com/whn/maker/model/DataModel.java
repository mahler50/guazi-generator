package com.whn.maker.model;

import lombok.Data;

/**
 * 动态模板配置类
 */
@Data
public class DataModel {


    /**
     * 核心模板
     */
    public MainTemplate mainTemplate = new MainTemplate();

    /**
     * 用于生成核心模板文件
     */
    @Data
    public static class MainTemplate {
        /**
         * 作者注释
         */
        public String author = "whn";

        /**
         * 输出信息
         */
        public String outputText = "输出结果=";
    }

    /**
     * 是否生成循环
     */
    public boolean loop;

}
