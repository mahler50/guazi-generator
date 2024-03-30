package com.whn.web.model.dto.generator;

import com.whn.maker.meta.Meta;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 使用生成器请求
 */
@Data
public class GeneratorUseRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 数据模型
     */
    Map<String, Object> dataModel;

    private static final long serialVersionUID = 1L;
}