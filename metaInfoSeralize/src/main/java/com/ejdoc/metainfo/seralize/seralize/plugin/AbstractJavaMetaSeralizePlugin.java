package com.ejdoc.metainfo.seralize.seralize.plugin;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.ejdoc.metainfo.seralize.index.JavaMetaFileInfo;
import com.ejdoc.metainfo.seralize.model.JavaClassMeta;
import com.ejdoc.metainfo.seralize.seralize.config.SeralizeConfig;
import com.ejdoc.metainfo.seralize.util.MetaPathUtil;

import java.util.List;
import java.util.Map;

public abstract class AbstractJavaMetaSeralizePlugin {

    protected  void parseBaseClassDependPath(SeralizeConfig seralizeConfig, List<JavaClassMeta> javaClassMetaList, String absolutePath, Map<String, JavaMetaFileInfo> dependPathMap) {

        if(CollectionUtil.isNotEmpty(javaClassMetaList)){
            for (JavaClassMeta javaClassMeta : javaClassMetaList) {
                String fullClassName = javaClassMeta.getFullClassName();
                JavaMetaFileInfo javaMetaFileInfo = dependPathMap.get(fullClassName);
                setRelativePath(seralizeConfig,javaMetaFileInfo, absolutePath,javaClassMeta);
            }
        }
    }

    /**
     * 设置依赖的路径，相对路径或绝对路径
     * @param seralizeConfig 序列化配置信息
     * @param dependJavaMetaFileInfo 元文件依赖的文件信息
     * @param absolutePath 元文件的绝对路径
     * @param dependClassMeta 元文件依赖的类信息 与dependJavaMetaFileInfo是同一个
     */
    protected  void setRelativePath(SeralizeConfig seralizeConfig,JavaMetaFileInfo dependJavaMetaFileInfo, String absolutePath, JavaClassMeta dependClassMeta) {

        if(dependJavaMetaFileInfo == null){
            return;
        }

        String seralizeOutPath = dependJavaMetaFileInfo.getOutFileBasePath();

        String dependAbsolutePath = dependJavaMetaFileInfo.getJsonFilePath();

        if(seralizeConfig.isUseAbsPath()){
            String relativePath = dependAbsolutePath.replace(seralizeOutPath,"");
            relativePath = relativePath.replace(".json","");
            dependClassMeta.setDependencyRelativePath(relativePath);
            return;
        }

        String relativePath = MetaPathUtil.calRelativePath(dependAbsolutePath, absolutePath);
        //依赖信息是自己
        if(StrUtil.equals(dependAbsolutePath,absolutePath)){
            relativePath = dependClassMeta.getClassName();
        }
        if(StrUtil.isNotBlank(relativePath)){
            relativePath = relativePath.replace(".json","");
            dependClassMeta.setDependencyRelativePath(relativePath);
        }
    }
}
