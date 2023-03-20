package com.ejdoc.metainfo.seralize.seralize.plugin;

import cn.hutool.core.collection.CollectionUtil;
import com.ejdoc.metainfo.seralize.seralize.JavaMetaSeralizePlugin;
import com.ejdoc.metainfo.seralize.seralize.config.SeralizeConfig;
import com.ejdoc.metainfo.seralize.seralize.plugin.dto.DependPathPluginDto;
import com.ejdoc.metainfo.seralize.seralize.plugin.dto.JavaClassMetaOverrideCommentDto;
import com.ejdoc.metainfo.seralize.seralize.plugin.dto.JavaMetaSeralizePluginData;
import com.ejdoc.metainfo.seralize.seralize.plugin.dto.JavaMetaServalizePluginContextDto;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * 重写方法Comment读取设置插件
 * 当前子类如果是重写方法，并且没有注释会读取父类注释信息并添加到子类中
 */
public class JavaMetaOverrideCommentPlugin extends AbstractJavaMetaSeralizePlugin implements JavaMetaSeralizePlugin {

    @Override
    public void exePostJavaMetaSeralize(JavaMetaServalizePluginContextDto javaMetaServalizePluginContextDto) {
        SeralizeConfig seralizeConfig = javaMetaServalizePluginContextDto.getSeralizeConfig();
        List<JavaMetaSeralizePluginData> allJavaMetaSeralizeClassList = javaMetaServalizePluginContextDto.getAllJavaMetaSeralizeClassList();
        Map<String, JavaMetaSeralizePluginData> metaSeralizeFileIndex = javaMetaServalizePluginContextDto.getMetaSeralizeFileIndex();
    }

    public void doPostParseMetaFile(String outFilePath, List<JavaClassMetaOverrideCommentDto> allJavaClassList, List<File> jsonFiles, Map<String, JavaClassMetaOverrideCommentDto> allFileIndex, Map<String, DependPathPluginDto> dependPathMap, SeralizeConfig seralizeConfig) {
        if(CollectionUtil.isNotEmpty(allJavaClassList)){
            for (JavaClassMetaOverrideCommentDto javaClassMetaOverrideCommentDto : allJavaClassList) {
                //解析所有重写方法的注释
                parseOverrideMethodCommentMetaInfo(javaClassMetaOverrideCommentDto,javaClassMetaOverrideCommentDto,allFileIndex);
            }
        }
    }

    private void parseOverrideMethodCommentMetaInfo(JavaClassMetaOverrideCommentDto javaClassMetaOverrideCommentDto, JavaClassMetaOverrideCommentDto javaClassMetaOverrideCommentDto1, Map<String, JavaClassMetaOverrideCommentDto> allFileIndex) {

    }
}
