package com.whn.maker.template.enums;

import cn.hutool.core.util.ObjectUtil;
import lombok.Getter;

@Getter
public enum FileFilterRuleEnum {

    CONTAINS("包含", "contains"),
    PREFIX("前缀匹配", "prefix"),
    SUFFIX("后缀匹配", "suffix"),
    REGEX("正则", "regex"),
    EQUALS("相等", "equals");

    private String text;

    private String value;

    FileFilterRuleEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    /**
     * 根据 value 获取枚举
     * @param value
     * @return
     */
    public static FileFilterRuleEnum getEnumByValue(String value) {
        if (ObjectUtil.isEmpty(value)) {
            return null;
        }
        for (FileFilterRuleEnum anEnum :
                FileFilterRuleEnum.values()) {
            if (anEnum.value.equals(value)) {
                return anEnum;
            }
        }
        return null;
    }
}
