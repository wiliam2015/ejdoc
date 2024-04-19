package com.ejdoc.metainfo.seralize.resource.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.ejdoc.metainfo.seralize.dto.MetaFileInfoDto;
import com.ejdoc.metainfo.seralize.dto.ModuleInfoDto;
import com.ejdoc.metainfo.seralize.enums.EnvPropEnum;
import com.ejdoc.metainfo.seralize.env.MetaEnvironment;
import com.ejdoc.metainfo.seralize.env.impl.DefaultMetaEnvironment;
import com.ejdoc.metainfo.seralize.resource.MetaFileRead;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class DefaultMetaFileRead implements MetaFileRead {
    private static final Logger log = LoggerFactory.getLogger(DefaultMetaFileRead.class);
    private MetaEnvironment metaEnvironment;

    private List<MetaFileInfoDto> defaultMetaFiles;

    public DefaultMetaFileRead(){
        this(new DefaultMetaEnvironment());
    };

    public DefaultMetaFileRead(String configFilePath){
        this(new DefaultMetaEnvironment(configFilePath));
    };

    public DefaultMetaFileRead(MetaEnvironment metaEnvironment){
        Assert.notNull(metaEnvironment, "MetaEnvironment can not be null !");
        this.metaEnvironment = metaEnvironment;
    }

    private Map<String,List<File>> projectFileCacheMap = new HashMap<>();

    public List<MetaFileInfoDto> readAllMetaFile() {
        this.defaultMetaFiles = readAllFile();
        List<String> sourceDirs = this.metaEnvironment.getSourceDirs();
        List<String> sourceJarFiles = this.metaEnvironment.getSourceJarFiles();
        List<String> sourceFiles = this.metaEnvironment.getSourceFiles();
        List<MetaFileInfoDto> sourceJarFileList = readCustomerSourceJarFile(sourceJarFiles);
        List<MetaFileInfoDto> sourceDirFileList = readCustomerSourceDir(sourceDirs);
        List<MetaFileInfoDto> sourceFileDataList = readCustomerSourceFileData(sourceFiles);
        this.defaultMetaFiles.addAll(sourceJarFileList);
        this.defaultMetaFiles.addAll(sourceDirFileList);
        this.defaultMetaFiles.addAll(sourceFileDataList);
        return this.defaultMetaFiles;
    }

    @Override
    public List<File> readModuleProjectMetaFile() {
        List<File> files = new ArrayList<>();
        List<String> subProjectRootPath = metaEnvironment.getSubProjectRootPath();
        if(CollectionUtil.isNotEmpty(subProjectRootPath)){
            for (String subProjectRootPathStr : subProjectRootPath) {
                files.add(new File(subProjectRootPathStr+"/projectMeta.yml"));
            }
        }
        return files;
    }

    @Override
    public List<File> readModuleProjectMetaFileByModuleName(String moduleName) {
        List<File> files = new ArrayList<>();
        List<ModuleInfoDto> subProjectInfo = this.metaEnvironment.getSubProjectInfo();
        if(CollectionUtil.isNotEmpty(subProjectInfo)){
            for (ModuleInfoDto moduleInfoDto : subProjectInfo) {
                if(moduleName.equals(moduleInfoDto.getModuleName())){
                    files.add(new File(moduleInfoDto.getModulePath()+"/projectMeta.yml"));
                    break;
                }
            }
        }
        return files;
    }

    @Override
    public File readProjectMetaFile() {
        String projectRootPath = metaEnvironment.getProjectRootPath();
        return new File(projectRootPath+"/projectMeta.yml");
    }

    @Override
    public List<MetaFileInfoDto> readModuleMetaFileByName(String moduleName) {
        List<MetaFileInfoDto> moduleFileList = new ArrayList<>();
        if(CollectionUtil.isNotEmpty(this.defaultMetaFiles)){
            for (MetaFileInfoDto defaultMetaFile : this.defaultMetaFiles) {
                if(moduleName.equals(defaultMetaFile.getModuleName())){
                    moduleFileList.add(defaultMetaFile);
                }
            }
        }
        return moduleFileList;
    }

    @Override
    public Map<String,List<MetaFileInfoDto>> readModuleMetaFile() {
        Map<String,List<MetaFileInfoDto>> allModuleMetaFile = new HashMap<>();

        if(CollectionUtil.isNotEmpty(this.defaultMetaFiles)){
            for (MetaFileInfoDto defaultMetaFile : this.defaultMetaFiles) {
                List<MetaFileInfoDto> files = allModuleMetaFile.get(defaultMetaFile.getModuleName());
                if(CollectionUtil.isNotEmpty(files)){
                    files.add(defaultMetaFile);
                }else{
                    files = new ArrayList<>();
                    files.add(defaultMetaFile);
                    allModuleMetaFile.put(defaultMetaFile.getModuleName(),files);
                }
            }
        }
        return allModuleMetaFile;
    }

    @Override
    public List<MetaFileInfoDto> readProjectFileByPath(String path) {
        if(CollectionUtil.isNotEmpty(this.defaultMetaFiles)){
            for (MetaFileInfoDto defaultMetaFile : this.defaultMetaFiles) {
                if(defaultMetaFile.getMetaFile() != null){

                }
//
            }
        }
        return null;
    }

    @Override
    public List<MetaFileInfoDto> readProjectFileByClasspath(String classpath) {
        return null;
    }


    private List<MetaFileInfoDto>  readCustomerSourceDir(List<String> sourceDirList){
        String projectRootPath = this.metaEnvironment.getProjectRootPath();
        File projectRootFile = new File(projectRootPath);
        List<MetaFileInfoDto> resultFile = new ArrayList<>();
        if(CollectionUtil.isNotEmpty(sourceDirList)){
            for (String sourcePath : sourceDirList) {
                List<File> files = FileUtil.loopFiles(sourcePath,subFile -> FileTypeUtil.getType(subFile).equals("java"));
                if(CollectionUtil.isNotEmpty(files)){
                    MetaFileInfoDto defaultMetaFile = null;
                    for (File file : files) {
                        defaultMetaFile = new MetaFileInfoDto();

                        defaultMetaFile.setMetaFile(file);
                        defaultMetaFile.setMetaFilePath(file.getPath());
                        defaultMetaFile.setMetaFileName(file.getName());

                        defaultMetaFile.setProjectName(projectRootFile.getName());
                        defaultMetaFile.setProjectPath(projectRootPath);

                        defaultMetaFile.setModuleName(projectRootFile.getName());
                        defaultMetaFile.setModulePath(projectRootPath);

                        resultFile.add(defaultMetaFile);
                    }
                }
            }
        }
        return resultFile;
    }

    private List<MetaFileInfoDto>  readCustomerSourceJarFile(List<String> sourceJarParthList){
        String projectRootPath = this.metaEnvironment.getProjectRootPath();
        File projectRootFile = new File(projectRootPath);
        List<MetaFileInfoDto> resultFile = new ArrayList<>();
        if(CollectionUtil.isNotEmpty(sourceJarParthList)){
            for (String sourceJarPath : sourceJarParthList) {
                try {
                    JarFile jarFile = new JarFile(sourceJarPath);
                    Enumeration<JarEntry> entries = jarFile.entries();
                    MetaFileInfoDto defaultMetaFile = null;
                    while (entries.hasMoreElements()){
                        defaultMetaFile = new MetaFileInfoDto();
                        JarEntry jarEntry = entries.nextElement();
                        InputStream inputStream = jarFile.getInputStream(jarEntry);
                        String data = IoUtil.readUtf8(inputStream);

                        defaultMetaFile.setMetFileData(data);
                        defaultMetaFile.setMetaFileName(jarEntry.getName());

                        defaultMetaFile.setProjectName(projectRootFile.getName());
                        defaultMetaFile.setProjectPath(projectRootPath);

                        defaultMetaFile.setModuleName(projectRootFile.getName());
                        defaultMetaFile.setModulePath(projectRootPath);

                        resultFile.add(defaultMetaFile);
                    }
                } catch (IOException e) {
                    log.error("readCustomerSourceJarFile error",e);
                    throw new RuntimeException(e);
                }
            }
        }
        return resultFile;
    }

    private List<MetaFileInfoDto>  readCustomerSourceFileData(List<String> sourceFileDataPaths){
        String projectRootPath = this.metaEnvironment.getProjectRootPath();
        File projectRootFile = new File(projectRootPath);
        List<MetaFileInfoDto> resultFile = new ArrayList<>();
        if(CollectionUtil.isNotEmpty(sourceFileDataPaths)){
            for (String sourceFileDataPath : sourceFileDataPaths) {
                File file = new File(sourceFileDataPath);
                MetaFileInfoDto defaultMetaFile = null;
                defaultMetaFile.setMetaFile(file);
                defaultMetaFile.setMetaFilePath(file.getPath());
                defaultMetaFile.setMetaFileName(file.getName());

                defaultMetaFile.setProjectName(projectRootFile.getName());
                defaultMetaFile.setProjectPath(projectRootPath);

                defaultMetaFile.setModuleName(projectRootFile.getName());
                defaultMetaFile.setModulePath(projectRootPath);

                resultFile.add(defaultMetaFile);
            }
        }
        return resultFile;
    }

    private List<MetaFileInfoDto>  readAllFile(){
        List<MetaFileInfoDto> resultFile = new ArrayList<>();
        String projectRootPath = this.metaEnvironment.getProjectRootPath();
        Map<String, String> loadModuleNameMap = getLoadModuleNameMap();
        File projectRootFile = new File(projectRootPath);
        String sourceDir = this.metaEnvironment.getProjectSourceDir();
        if(this.metaEnvironment.isIncludeSubProject()){
            List<ModuleInfoDto> subProjectInfo = this.metaEnvironment.getSubProjectInfo();
            if(CollectionUtil.isNotEmpty(subProjectInfo)){
                MetaFileInfoDto defaultMetaFile = null;
                for (ModuleInfoDto moduleInfoDto : subProjectInfo) {
                    String loopPath = moduleInfoDto.getModulePath()+ sourceDir;
                    String moduleName = moduleInfoDto.getModuleName();
                    if(CollectionUtil.isNotEmpty(loadModuleNameMap) && !loadModuleNameMap.containsKey(moduleName)){
                        continue;
                    }

                    List<File> files = FileUtil.loopFiles(loopPath,subFile -> FileTypeUtil.getType(subFile).equals("java"));
                    if(CollectionUtil.isNotEmpty(files)){
                        for (File file : files) {
                            defaultMetaFile = new MetaFileInfoDto();
                            defaultMetaFile.setMetaFile(file);
                            defaultMetaFile.setMetaFilePath(file.getPath());
                            defaultMetaFile.setMetaFileName(file.getName());

                            defaultMetaFile.setProjectName(projectRootFile.getName());
                            defaultMetaFile.setProjectPath(projectRootPath);


                            defaultMetaFile.setModuleName(moduleName);
                            defaultMetaFile.setModulePath(moduleInfoDto.getModulePath());
                            defaultMetaFile.setModuleDesc(moduleInfoDto.getModuleDesc());

                            resultFile.add(defaultMetaFile);
                        }
                    }

                }
            }

        }else{
            List<File> files = FileUtil.loopFiles(projectRootPath,subFile -> FileTypeUtil.getType(subFile).equals("java"));
            if(CollectionUtil.isNotEmpty(files)){
                MetaFileInfoDto defaultMetaFile = null;
                for (File file : files) {
                    defaultMetaFile = new MetaFileInfoDto();

                    defaultMetaFile.setMetaFile(file);
                    defaultMetaFile.setMetaFilePath(file.getPath());
                    defaultMetaFile.setMetaFileName(file.getName());

                    defaultMetaFile.setProjectName(projectRootFile.getName());
                    defaultMetaFile.setProjectPath(projectRootPath);

                    defaultMetaFile.setModuleName(projectRootFile.getName());
                    defaultMetaFile.setModulePath(projectRootPath);

                    resultFile.add(defaultMetaFile);
                }
            }
        }
        return resultFile;
    }

    private Map<String, String> getLoadModuleNameMap() {
        Map<String,String> loadModuleNameMap = new HashMap<>();
        String loadModuleName = this.metaEnvironment.getProp(EnvPropEnum.load_module_name.getCode(), "");
        if(StrUtil.isNotBlank(loadModuleName)){
            String[] moduleNames = loadModuleName.split(",");
            for (String moduleName : moduleNames) {
                loadModuleNameMap.put(moduleName,moduleName);
            }
        }
        return loadModuleNameMap;
    }

    @Override
    public MetaEnvironment getMetaEnvironment() {
        return metaEnvironment;
    }

    @Override
    public String getProp(String propKey) {
        return metaEnvironment.getProp(propKey);
    }

    @Override
    public String getProp(String propKey, String defaultVal) {
        return metaEnvironment.getProp(propKey,defaultVal);
    }

    public void setMetaEnvironment(MetaEnvironment metaEnvironment) {
        this.metaEnvironment = metaEnvironment;
    }

}

