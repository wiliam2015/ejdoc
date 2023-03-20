package com.ejdoc.doc.generate.out.javadoc;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ejdoc.metainfo.seralize.model.JavaClassMeta;
import com.ejdoc.metainfo.seralize.seralize.JavaMetaSeralizePlugin;
import com.ejdoc.metainfo.seralize.seralize.plugin.AbstractJavaMetaSeralizePlugin;
import com.ejdoc.metainfo.seralize.seralize.plugin.dto.JavaMetaSeralizePluginData;
import com.ejdoc.metainfo.seralize.seralize.plugin.dto.JavaMetaServalizePluginContextDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JavaDocDeepDependParsePlugin extends AbstractJavaMetaSeralizePlugin implements JavaMetaSeralizePlugin {



    @Override
    public void exePostJavaMetaSeralize(JavaMetaServalizePluginContextDto javaMetaServalizePluginContextDto) {
        List<JavaMetaSeralizePluginData> allJavaMetaSeralizeClassList = javaMetaServalizePluginContextDto.getAllJavaMetaSeralizeClassList();
        if(CollectionUtil.isNotEmpty(allJavaMetaSeralizeClassList)){
            Map<String, JavaDocClassMeta> allFileIndexDoc = new HashMap<>();
            List<JavaDocClassMeta> allJavaClassDocList = new ArrayList<>();

            for (JavaMetaSeralizePluginData javaMetaSeralizePluginData : allJavaMetaSeralizeClassList) {
                JavaDocClassMeta javaDocClassMeta = new JavaDocClassMeta();
                JavaClassMeta javaClassMeta = JSONUtil.toBean(javaMetaSeralizePluginData.getJsonObject(),JavaClassMeta.class);
                javaDocClassMeta.setJavaClassMeta(javaClassMeta);
                javaDocClassMeta.setJavaClassMetaJsonObject(javaMetaSeralizePluginData.getJsonObject());
                javaDocClassMeta.setJavaMetaSeralizePluginData(javaMetaSeralizePluginData);
                javaDocClassMeta.setJsonFilePath(javaMetaSeralizePluginData.getJsonFilePath());
                allJavaClassDocList.add(javaDocClassMeta);
                allFileIndexDoc.put(javaClassMeta.getFullClassName(),javaDocClassMeta);
            }

            for (JavaDocClassMeta javaClassMeta : allJavaClassDocList) {
                //解析所有父类，接口，所有实现子类和接口
                parseJavaDocClassMetaInfo(javaClassMeta,javaClassMeta,javaClassMeta.getJavaMetaSeralizePluginData(),allFileIndexDoc);
            }

            for (JavaDocClassMeta javaClassMeta : allJavaClassDocList) {
                //解析所有父类，接口，所有实现子类和接口的相对路径
                parseAllDependClassRelativePath(javaMetaServalizePluginContextDto,javaClassMeta,javaClassMeta.getJavaMetaSeralizePluginData());
            }
        }
    }

    /**
     * 解析所有依赖类的相对路径
     * @param javaMetaServalizePluginContextDto
     * @param javaClassMeta
     */
    private void parseAllDependClassRelativePath(JavaMetaServalizePluginContextDto javaMetaServalizePluginContextDto,JavaDocClassMeta javaClassMeta,JavaMetaSeralizePluginData javaMetaSeralizePluginData) {
        String jsonFilePath = javaClassMeta.getJsonFilePath();
        Map<String, JavaMetaSeralizePluginData> dependPathMap = javaMetaServalizePluginContextDto.getMetaSeralizeFileIndex();
        if(CollectionUtil.isNotEmpty(javaClassMeta.getAllInterfaceClasses())){
            parseBaseClassDependPath(javaMetaServalizePluginContextDto,javaClassMeta.getAllInterfaceClasses(),jsonFilePath,dependPathMap);
        }
        if(CollectionUtil.isNotEmpty(javaClassMeta.getAllSubInterfaceClasses())){
            parseBaseClassDependPath(javaMetaServalizePluginContextDto,javaClassMeta.getAllSubInterfaceClasses(),jsonFilePath,dependPathMap);
        }
        if(CollectionUtil.isNotEmpty(javaClassMeta.getAllSubClasses())){
            parseBaseClassDependPath(javaMetaServalizePluginContextDto,javaClassMeta.getAllSubClasses(),jsonFilePath,dependPathMap);
        }
        if(CollectionUtil.isNotEmpty(javaClassMeta.getAllSupperClasses())){
            parseBaseClassDependPath(javaMetaServalizePluginContextDto,javaClassMeta.getAllSupperClasses(),jsonFilePath,dependPathMap);
        }

        addAllDeepDependMetaToJsonProp(javaClassMeta, javaMetaSeralizePluginData);
    }

    /**
     * 重新设置json元数据属性
     * @param javaMetaSeralizePluginData
     * @param allInterfaceClasses
     * @param javaClassMeta
     */
    private void reSetJsonMetaFileProp(JavaMetaSeralizePluginData javaMetaSeralizePluginData, String allInterfaceClasses, List<JavaClassMeta> javaClassMeta) {
        if(CollectionUtil.isNotEmpty(javaClassMeta)){
            JSONObject jsonObject = javaMetaSeralizePluginData.getJsonObject();
            jsonObject.putOpt(allInterfaceClasses,JSONUtil.parseArray(javaClassMeta));
        }
    }


    /**
     * 装填JavaDocClassMeta子类中的信息
     * 如果当前类是接口，那么只需要获取父类即可，接口可以继承接口。
     * 如果当前类是具体类，查找逻辑
     *   1.当前类实现的接口以及所有父类实现的接口，及其子类
     *   2.当前类的所有父类及其子类
     * @param javaClassMeta 当前类
     * @param supperJavaClassMeta  父类,第一次时传入的是当前类，父类是自己
     * @param allFileIndex
     */
    private void parseJavaDocClassMetaInfo(JavaDocClassMeta javaClassMeta,JavaDocClassMeta supperJavaClassMeta,JavaMetaSeralizePluginData javaMetaSeralizePluginData,Map<String, JavaDocClassMeta> allFileIndex) {
        boolean currentClassIsInterface = BooleanUtil.isTrue(javaClassMeta.getJavaClassMeta().getInterfaceClass());

        //当前类是接口，那么只需要获取父类即可，接口可以继承接口。
        if(currentClassIsInterface){
            addAllInterfaceAndSubClassForInterfaceClass(javaClassMeta,supperJavaClassMeta.getJavaClassMeta(),allFileIndex);
            return;
        }

        //需要判断当前类的继承类，继承类的父类和继承类实现的接口。
        addAllSuperClassAndSubClass(javaClassMeta,supperJavaClassMeta,allFileIndex);

        //当前类实现的接口以及父类实现的接口
        addAllInterfaceClassAndSubClass(javaClassMeta,supperJavaClassMeta,allFileIndex);

        addAllDeepDependMetaToJsonProp(javaClassMeta, javaMetaSeralizePluginData);

    }

    /**
     * 将所有深度依赖属性添加到json属性上
     * @param javaClassMeta
     * @param javaMetaSeralizePluginData
     */
    private void addAllDeepDependMetaToJsonProp(JavaDocClassMeta javaClassMeta, JavaMetaSeralizePluginData javaMetaSeralizePluginData) {
        reSetJsonMetaFileProp(javaMetaSeralizePluginData, "allInterfaceClasses", javaClassMeta.getAllInterfaceClasses());
        reSetJsonMetaFileProp(javaMetaSeralizePluginData, "allSubInterfaceClasses", javaClassMeta.getAllSubInterfaceClasses());
        reSetJsonMetaFileProp(javaMetaSeralizePluginData, "allSubClasses", javaClassMeta.getAllSubClasses());
        reSetJsonMetaFileProp(javaMetaSeralizePluginData, "allSupperClasses", javaClassMeta.getAllSupperClasses());
    }

    /**
     * 当前类实现的接口以及父类实现的接口
     * @param javaClassMeta
     * @param supperJavaClassMeta
     */
    private void addAllInterfaceClassAndSubClass(JavaDocClassMeta javaClassMeta, JavaDocClassMeta supperJavaClassMeta,Map<String, JavaDocClassMeta> allFileIndex) {
        //当前类实现的接口
        addAllInterfaceAndSubClassForRealClass(javaClassMeta,supperJavaClassMeta.getJavaClassMeta(),allFileIndex);
        //继承类实现的接口
        addAllInterfaceAndSubClassFromAllSupperClass(javaClassMeta,supperJavaClassMeta,allFileIndex);

    }

    /**
     * 增加将当前类的所有父类的接口
     * @param javaClassMeta
     * @param supperJavaClassMeta
     */
    private void addAllInterfaceAndSubClassFromAllSupperClass(JavaDocClassMeta javaClassMeta, JavaDocClassMeta supperJavaClassMeta,Map<String, JavaDocClassMeta> allFileIndex) {
        //继承类实现的接口
        if(supperJavaClassMeta.getJavaClassMeta() != null && CollectionUtil.isNotEmpty(supperJavaClassMeta.getJavaClassMeta().getSuperClasses())){
            for (JavaClassMeta superClass : supperJavaClassMeta.getJavaClassMeta().getSuperClasses()) {
                addAllInterfaceAndSubClassForRealClass(javaClassMeta,superClass,allFileIndex);

                String fullClassName = superClass.getFullClassName();
                JavaDocClassMeta supperJavaDocClassMeta = allFileIndex.get(fullClassName);
                //递归调用父类
                if(!isJdkClass(fullClassName)){
                    if(ObjectUtil.isNotNull(supperJavaDocClassMeta) && ObjectUtil.isNotNull(supperJavaDocClassMeta.getJavaClassMeta()) && CollectionUtil.isNotEmpty(supperJavaDocClassMeta.getJavaClassMeta().getSuperClasses())){
                        addAllInterfaceAndSubClassFromAllSupperClass(javaClassMeta,supperJavaDocClassMeta,allFileIndex);
                    }
                }
            }
        }
    }

    /**
     * 需要判断当前类的继承类，继承类的父类和继承类实现的接口。
     * @param javaClassMeta
     * @param supperJavaClassMeta
     */
    private void addAllSuperClassAndSubClass(JavaDocClassMeta javaClassMeta, JavaDocClassMeta supperJavaClassMeta,Map<String, JavaDocClassMeta> allFileIndexMap) {
        if(supperJavaClassMeta.getJavaClassMeta() != null && CollectionUtil.isNotEmpty(supperJavaClassMeta.getJavaClassMeta().getSuperClasses())){
            for (JavaClassMeta superClass : supperJavaClassMeta.getJavaClassMeta().getSuperClasses()) {
                //增加父类信息
                String fullClassName = superClass.getFullClassName();
                javaClassMeta.addAllSupperClasses(createSimpleClass(superClass));

                JavaDocClassMeta supperJavaDocClassMeta = allFileIndexMap.get(fullClassName);
                //增加子类
                addAllSubClass(javaClassMeta,supperJavaClassMeta.getJavaClassMeta(), supperJavaDocClassMeta);

                //递归调用父类
                if(!isJdkClass(fullClassName)){
                    if(ObjectUtil.isNotNull(supperJavaDocClassMeta) && ObjectUtil.isNotNull(supperJavaDocClassMeta.getJavaClassMeta()) && CollectionUtil.isNotEmpty(supperJavaDocClassMeta.getJavaClassMeta().getSuperClasses())){
                        addAllSuperClassAndSubClass(javaClassMeta,supperJavaDocClassMeta,allFileIndexMap);
                    }
                }
            }
        }
    }

    /**
     * 当前类是具体实现类，增加所有接口信息和子类信息
     * @param javaClassMeta
     * @param supperJavaClassMeta
     */
    private void addAllInterfaceAndSubClassForRealClass(JavaDocClassMeta javaClassMeta, JavaClassMeta supperJavaClassMeta,Map<String, JavaDocClassMeta> allFileIndex) {
        if(CollectionUtil.isNotEmpty(supperJavaClassMeta.getInterfaces())){
            for (JavaClassMeta anInterface : supperJavaClassMeta.getInterfaces()) {
                //接口信息
                String fullClassName = anInterface.getFullClassName();
                javaClassMeta.addAllInterfaceClasses(createSimpleClass(anInterface));
                JavaDocClassMeta interfaceClassMeta =allFileIndex.get(fullClassName);

                //增加子类
                addAllSubClass(javaClassMeta,supperJavaClassMeta, interfaceClassMeta);

                //递归调用父接口
                if(!isJdkClass(fullClassName)){
                    if(interfaceClassMeta != null && interfaceClassMeta.getJavaClassMeta() != null && CollectionUtil.isNotEmpty(interfaceClassMeta.getJavaClassMeta().getSuperClasses())){
                        addAllInterfaceAndSubClassForInterfaceClass(javaClassMeta,interfaceClassMeta.getJavaClassMeta(),allFileIndex);
                    }
                }

            }
        }
    }

    /**
     * 当前类是接口类，增加所有接口信息和子类信息
     * @param javaClassMeta
     * @param supperJavaClassMeta
     */
    private void addAllInterfaceAndSubClassForInterfaceClass(JavaDocClassMeta javaClassMeta, JavaClassMeta supperJavaClassMeta,Map<String, JavaDocClassMeta> allFileIndex) {
        if(CollectionUtil.isNotEmpty(supperJavaClassMeta.getSuperClasses())){
            for (JavaClassMeta anInterface : supperJavaClassMeta.getSuperClasses()) {
                //接口信息
                String fullClassName = anInterface.getFullClassName();
                javaClassMeta.addAllInterfaceClasses(createSimpleClass(anInterface));
                JavaDocClassMeta interfaceClassMeta = allFileIndex.get(fullClassName);

                //增加子类
                addAllSubClass(javaClassMeta,supperJavaClassMeta, interfaceClassMeta);

                //递归调用父接口
                if(!isJdkClass(fullClassName)){
                    if(interfaceClassMeta != null && interfaceClassMeta.getJavaClassMeta() != null  && CollectionUtil.isNotEmpty(interfaceClassMeta.getJavaClassMeta().getSuperClasses())){
                        addAllInterfaceAndSubClassForInterfaceClass(javaClassMeta,interfaceClassMeta.getJavaClassMeta(),allFileIndex);
                    }
                }

            }
        }
    }

    /**
     * 增加子类
     * @param javaClassMeta 原始类
     * @param superJavaClassMeta  原始类或直接父类
     * @param supperSuperJavaClassMeta 父类，必须是从getAllFileIndex获取的类，否则会导致信息不全
     */
    private void addAllSubClass(JavaDocClassMeta javaClassMeta,JavaClassMeta superJavaClassMeta, JavaDocClassMeta supperSuperJavaClassMeta) {
        if(ObjectUtil.isNotNull(supperSuperJavaClassMeta)){
            boolean isClass = !BooleanUtil.isTrue(superJavaClassMeta.getInterfaceClass());
            if(isClass){
                supperSuperJavaClassMeta.addAllSubClasses(createSimpleClass(superJavaClassMeta));
            }else {
                supperSuperJavaClassMeta.addAllSubInterfaceClasses(createSimpleClass(superJavaClassMeta));
            }

            //不相等，将最起始类也加到子类里
            if(javaClassMeta.getJavaClassMeta() != null && !StrUtil.equals(javaClassMeta.getJavaClassMeta().getFullClassName(),superJavaClassMeta.getFullClassName())){
                boolean metaIsClass = !BooleanUtil.isTrue(javaClassMeta.getJavaClassMeta().getInterfaceClass());
                if(metaIsClass){
                    supperSuperJavaClassMeta.addAllSubClasses(createSimpleClass(javaClassMeta.getJavaClassMeta()));
                }else{
                    supperSuperJavaClassMeta.addAllSubInterfaceClasses(createSimpleClass(javaClassMeta.getJavaClassMeta()));
                }

            }

        }
    }


    private  JavaClassMeta createSimpleClass(JavaClassMeta javaClassMeta1) {
        JavaClassMeta supperClassAdd = new JavaClassMeta();
        supperClassAdd.setClassName(javaClassMeta1.getClassName());
        supperClassAdd.setFullClassName(javaClassMeta1.getFullClassName());
        supperClassAdd.setDependencyRelativePath(javaClassMeta1.getDependencyRelativePath());
        supperClassAdd.setDependencyAbsolutePath(javaClassMeta1.getDependencyAbsolutePath());
        supperClassAdd.setTypeParameters(javaClassMeta1.getTypeParameters());
        supperClassAdd.setTypeArguments(javaClassMeta1.getTypeArguments());
        return supperClassAdd;
    }


    private boolean isJdkClass(String fullClassName) {
        if (StrUtil.isBlank(fullClassName)) {
            return false;
        } else {
            return fullClassName.startsWith("java.") || fullClassName.startsWith("javax.");
        }
    }
}
