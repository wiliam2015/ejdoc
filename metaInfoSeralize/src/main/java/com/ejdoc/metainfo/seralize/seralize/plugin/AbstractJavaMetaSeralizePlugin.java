package com.ejdoc.metainfo.seralize.seralize.plugin;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.ejdoc.metainfo.seralize.model.JavaClassMeta;
import com.ejdoc.metainfo.seralize.seralize.config.SeralizeConfig;
import com.ejdoc.metainfo.seralize.seralize.plugin.dto.JavaMetaSeralizePluginData;
import com.ejdoc.metainfo.seralize.seralize.plugin.dto.JavaMetaServalizePluginContextDto;
import com.ejdoc.metainfo.seralize.util.MetaPathUtil;

import java.util.List;
import java.util.Map;

public abstract class AbstractJavaMetaSeralizePlugin {

    protected  void parseBaseClassDependPath(JavaMetaServalizePluginContextDto javaMetaServalizePluginContextDto, List<JavaClassMeta> javaClassMetaList, String absolutePath, Map<String, JavaMetaSeralizePluginData> dependPathMap) {

        if(CollectionUtil.isNotEmpty(javaClassMetaList)){
            for (JavaClassMeta javaClassMeta : javaClassMetaList) {
                setRelativePath(javaMetaServalizePluginContextDto,dependPathMap, absolutePath,javaClassMeta);
            }
        }
    }

    /**
     * 设置相对路径
     * @param dependPathMap
     * @param absolutePath
     * @param classMeta
     */
    protected  void setRelativePath(JavaMetaServalizePluginContextDto javaMetaServalizePluginContextDto,Map<String, JavaMetaSeralizePluginData> dependPathMap, String absolutePath, JavaClassMeta classMeta) {
        String fullClassName = classMeta.getFullClassName();
        String seralizeOutPath = javaMetaServalizePluginContextDto.getSeralizeOutPath();
        SeralizeConfig seralizeConfig = javaMetaServalizePluginContextDto.getSeralizeConfig();
        if(dependPathMap.containsKey(fullClassName)){
            JavaMetaSeralizePluginData javaParserDependPath = dependPathMap.get(fullClassName);
            String dependAbsolutePath = javaParserDependPath.getJsonFilePath();

            if(seralizeConfig.isUseAbsPath()){
                String relativePath = dependAbsolutePath.replace(seralizeOutPath,"");
                relativePath = relativePath.replace(".json","");
                classMeta.setDependencyRelativePath(relativePath);
                return;
            }



            String relativePath = MetaPathUtil.calRelativePath(dependAbsolutePath, absolutePath);
            //依赖信息是自己
            if(StrUtil.equals(dependAbsolutePath,absolutePath)){
                relativePath = classMeta.getClassName();
            }
            if(StrUtil.isNotBlank(relativePath)){
                relativePath = relativePath.replace(".json","");
                classMeta.setDependencyRelativePath(relativePath);
            }
        }
    }
}
