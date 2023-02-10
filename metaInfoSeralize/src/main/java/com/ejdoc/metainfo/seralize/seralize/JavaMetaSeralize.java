package com.ejdoc.metainfo.seralize.seralize;

import com.ejdoc.metainfo.seralize.seralize.config.SeralizeConfig;
/**
 * Java元数据序列化接口<br>
 * 提供默认的java序列化方法和自定义配置能力的序列化输出方法
 *
 * @author wiliam.hu
 * @since 0.1.0
 */
public interface JavaMetaSeralize {

    /**
     * 默认的java序列化方法，使用默认配置
     */
    void exeJavaMetaSeralize();

    /**
     * java序列化方法,传入自定义配置
     * @param seralizeConfig 自定义配置
     */
    String exeJavaMetaSeralize(SeralizeConfig seralizeConfig);
}
