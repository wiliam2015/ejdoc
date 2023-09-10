package com.ejdoc.metainfo.seralize.parser.impl.javaparser;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.ejdoc.metainfo.seralize.dto.MetaFileInfoDto;
import com.ejdoc.metainfo.seralize.model.JavaClassMeta;
import com.ejdoc.metainfo.seralize.model.JavaModuleMeta;
import com.ejdoc.metainfo.seralize.parser.impl.AbstractMetaInfoParser;
import com.ejdoc.metainfo.seralize.parser.impl.javaparser.member.*;
import com.ejdoc.metainfo.seralize.parser.impl.javaparser.type.*;
import com.ejdoc.metainfo.seralize.resource.MetaFileRead;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.TypeDeclaration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.util.*;

public class JavaParserMetaInfoParser extends AbstractMetaInfoParser {
    private static final Logger log = LoggerFactory.getLogger(JavaParserMetaInfoParser.class);

//    private Javaparser
    private JavaParser javaParser;

    private List<JavaParserTypeDeclarationParse> javaParserTypeDeclarationParseList;

    public JavaParserMetaInfoParser() {
        super();

        this.javaParser = JavaParserCreateFactory.createJavaParser(metaFileRead);

        this.javaParserTypeDeclarationParseList = initJavaParserTypeDeclarationParseList();
    }

    public JavaParserMetaInfoParser(String configFilePath) {
        super(configFilePath);

        this.javaParser = JavaParserCreateFactory.createJavaParser(metaFileRead);

        this.javaParserTypeDeclarationParseList = initJavaParserTypeDeclarationParseList();
    }

    public JavaParserMetaInfoParser(MetaFileRead metaFileRead) {
        super(metaFileRead);

        this.javaParser = JavaParserCreateFactory.createJavaParser(metaFileRead);

        this.javaParserTypeDeclarationParseList = initJavaParserTypeDeclarationParseList();
    }

    public JavaParserMetaInfoParser(MetaFileRead metaFileRead,List<JavaParserTypeDeclarationParse> javaParserTypeDeclarationParseList) {
        super(metaFileRead);

        this.javaParser = JavaParserCreateFactory.createJavaParser(metaFileRead);

        this.javaParserTypeDeclarationParseList = javaParserTypeDeclarationParseList;
    }


    private List<JavaParserTypeDeclarationParse> initJavaParserTypeDeclarationParseList() {
        List<JavaParserMemberParse> javaParserMemberParseList = ListUtil.of(new FieldMemberParse(),new InnerClassMemberParse(),new NestedClassMemberParse(),new MethodMemberParse(),new ConstructorMemberParse(),new AnnotationMemberParse(),new EnumMemberParse());
        return ListUtil.of(new PackageInfoTypeDeclarationParse(javaParserMemberParseList),new ClassTypeDeclarationParse(javaParserMemberParseList),new EnumTypeDeclarationParse(javaParserMemberParseList),new AnnotationTypeDeclarationParse(javaParserMemberParseList),new NestedClassMemberExtractParse(javaParserMemberParseList));
    }


    @Override
    public List<JavaModuleMeta> parseAllJavaModuletMeta() {
        List<JavaModuleMeta> javaModuleMetas = new ArrayList<>();
        JavaParserMetaContext javaParserMetaContext = new JavaParserMetaContext();
        List<MetaFileInfoDto> metaFileInfos = metaFileRead.readAllMetaFile();
        javaParserMetaContext.addEnvProp(metaFileRead.getMetaEnvironment().getAllProp());
        try {
            List<JavaClassMeta> javaClassMetaList = new ArrayList<>();

            preParsingJavaClass(javaParserMetaContext, metaFileInfos, javaClassMetaList);

            postParsingJavaClass(javaParserMetaContext, javaClassMetaList);

            javaModuleMetas =  groupJavaClassMetaByModule(javaClassMetaList);

        } catch (Exception e) {
            log.error("parseAllJavaModuletMeta error",e);
            throw new RuntimeException(e);
        }

        return javaModuleMetas;

    }

    /**
     * 子类重载使用
     * @param javaParserMetaContext
     * @param javaClassMetaList
     */
    protected void postParsingJavaClass(JavaParserMetaContext javaParserMetaContext, List<JavaClassMeta> javaClassMetaList) {

    }


    /**
     * 前置解析java类信息，直接依赖获取到的文件信息即可获取
     * @param javaParserMetaContext
     * @param metaFileInfos
     * @param javaClassMetaList
     * @throws FileNotFoundException
     */
    private void preParsingJavaClass(JavaParserMetaContext javaParserMetaContext, List<MetaFileInfoDto> metaFileInfos, List<JavaClassMeta> javaClassMetaList) throws FileNotFoundException {
        for (MetaFileInfoDto metaFileInfo : metaFileInfos) {

            processJavaClassMeta( metaFileInfo, javaClassMetaList, javaParserMetaContext);

        }
    }

    /**
     * 因为解析的是注释文件，在java文件中，有注释的地方是
     * 1.文件头；2.成员变量；3.成员变量实体；4.方法；5.方法入参；6.方法入参实体类
     * @param metaFileInfo
     * @param javaClassMetaList
     * @param javaParserMetaContext
     * @throws FileNotFoundException
     */
    private void processJavaClassMeta(MetaFileInfoDto metaFileInfo, List<JavaClassMeta> javaClassMetaList, JavaParserMetaContext javaParserMetaContext) throws FileNotFoundException {

        Optional<CompilationUnit> result = this.javaParser.parse(metaFileInfo.getMetaFile()).getResult();

        //抽象语法树的根节点
        CompilationUnit rootAst = result.get();

        //解析具体的类信息
        parseJavaClassInfo(metaFileInfo, javaClassMetaList, javaParserMetaContext, rootAst);


    }

    private List<JavaClassMeta> parseJavaClassInfo(MetaFileInfoDto metaFileInfo, List<JavaClassMeta> javaClassMetaList, JavaParserMetaContext javaParserMetaContext, CompilationUnit rootAst) {
        NodeList<TypeDeclaration<?>> classTypeDataList = rootAst.getTypes();
        if(CollectionUtil.isNotEmpty(javaParserTypeDeclarationParseList)){
            for (JavaParserTypeDeclarationParse javaParserTypeDeclarationParse : javaParserTypeDeclarationParseList) {
                if(CollectionUtil.isNotEmpty(classTypeDataList)){
                    for (TypeDeclaration<?> typeDeclaration : classTypeDataList) {
                        if(javaParserTypeDeclarationParse.accept(typeDeclaration,metaFileInfo)){
                            List<JavaClassMeta> javaClassMetas = javaParserTypeDeclarationParse.parseTypeToJavaClassMeta(metaFileInfo, rootAst, typeDeclaration,javaParserMetaContext);
                            javaClassMetaList.addAll(javaClassMetas);

                        }
                    }
                }else{
                    if(javaParserTypeDeclarationParse.accept(null,metaFileInfo)){
                        List<JavaClassMeta> javaClassMetas = javaParserTypeDeclarationParse.parseTypeToJavaClassMeta(metaFileInfo, rootAst, null,javaParserMetaContext);
                        javaClassMetaList.addAll(javaClassMetas);
                    }
                }

            }
        }else {
            log.warn("not get JavaParserTypeDeclarationParse data");
        }



        return javaClassMetaList;
    }






    private List<JavaModuleMeta> groupJavaClassMetaByModule(Collection<JavaClassMeta> javaClassMetaList) {

        List<JavaModuleMeta> result = new ArrayList<>();

        if(CollectionUtil.isNotEmpty(javaClassMetaList)){
            Map<JavaModuleMeta, List<JavaClassMeta>> groupResultMap = groupByModelueName(javaClassMetaList);

            groupResultMap.forEach((javaModuleMetaKey,javaClassMetaGroupList) ->{
                JavaModuleMeta javaModuleMeta = new JavaModuleMeta();
                javaModuleMeta.setName(javaModuleMetaKey.getName());
                javaModuleMeta.setDesc(javaModuleMetaKey.getDesc());
                javaModuleMeta.setJavaClassMetas(javaClassMetaGroupList);
                result.add(javaModuleMeta);
            });

        }
        return result;
    }

    private  Map<JavaModuleMeta, List<JavaClassMeta>> groupByModelueName(Collection<JavaClassMeta> javaClassMetaList) {
        //            Map<String, List<JavaClassMeta>> groupResultMap = javaClassMetaList.stream().collect(Collectors.groupingBy(JavaClassMeta::getModuleName));
        Map<JavaModuleMeta, List<JavaClassMeta>> groupResultMap = new HashMap<>();
        for (JavaClassMeta javaClassMeta : javaClassMetaList) {
            String moduleName = javaClassMeta.getModuleName();
            String moduleDesc = javaClassMeta.getModuleDesc();
            JavaModuleMeta javaModuleMeta = new JavaModuleMeta(moduleName,moduleDesc);
            if(StrUtil.isNotBlank(moduleName)){
                if(groupResultMap.containsKey(javaModuleMeta)){
                    groupResultMap.get(javaModuleMeta).add(javaClassMeta);
                }else {
                    List<JavaClassMeta> groupJavaClassMetaList =new ArrayList<>();
                    groupJavaClassMetaList.add(javaClassMeta);
                    groupResultMap.put(javaModuleMeta,groupJavaClassMetaList);
                }

            }else{
                log.warn("Not obtained module name javaClassMeta:{}",JSONUtil.toJsonStr(javaClassMeta));
            }

        }
        return groupResultMap;
    }


    public List<JavaParserTypeDeclarationParse> getJavaParserTypeDeclarationParseList() {
        return javaParserTypeDeclarationParseList;
    }

    public void setJavaParserTypeDeclarationParseList(List<JavaParserTypeDeclarationParse> javaParserTypeDeclarationParseList) {
        this.javaParserTypeDeclarationParseList = javaParserTypeDeclarationParseList;
    }

}
