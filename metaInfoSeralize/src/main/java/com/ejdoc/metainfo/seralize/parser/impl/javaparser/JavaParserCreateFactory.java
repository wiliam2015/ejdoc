package com.ejdoc.metainfo.seralize.parser.impl.javaparser;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ListUtil;
import com.ejdoc.metainfo.seralize.dto.MetaFileInfoDto;
import com.ejdoc.metainfo.seralize.parser.impl.javaparser.member.*;
import com.ejdoc.metainfo.seralize.parser.impl.javaparser.type.ClassTypeDeclarationParse;
import com.ejdoc.metainfo.seralize.parser.impl.javaparser.type.EnumTypeDeclarationParse;
import com.ejdoc.metainfo.seralize.resource.MetaFileRead;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ParserConfiguration;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JavaParserCreateFactory {
    /**
     * 文件目录缓存
     */
    private static Set<String> srcDirCache = new HashSet<>();

    private static JavaParser javaParser;
    private static ClassTypeDeclarationParse classTypeDeclarationParse;
    private static EnumTypeDeclarationParse enumTypeDeclarationParse;

    private JavaParserCreateFactory(){}


    public static JavaParser createJavaParser(MetaFileRead metaFileRead){
        List<MetaFileInfoDto> metaFileInfos = metaFileRead.readAllMetaFile();

        String projectSourceDir = metaFileRead.getMetaEnvironment().getProjectSourceDir();
        CombinedTypeSolver combinedTypeSolverNewCode = new CombinedTypeSolver();
        combinedTypeSolverNewCode.add(new ReflectionTypeSolver());
        ParserConfiguration parserConfiguration = new ParserConfiguration();
        parserConfiguration.setLanguageLevel(ParserConfiguration.LanguageLevel.RAW);
//        parserConfiguration.setStoreTokens(false);
//        parserConfiguration.setAttributeComments(false);
//        parserConfiguration.setLexicalPreservationEnabled(false);
        if(CollectionUtil.isNotEmpty(metaFileInfos)){
            for (MetaFileInfoDto metaFileInfo : metaFileInfos) {
                String parent = metaFileInfo.getMetaFile().getParent();

                int src = parent.indexOf(projectSourceDir);
                if(src != -1){
                    String sourceDir = parent.substring(0,src+projectSourceDir.length());
                    if(!srcDirCache.contains(sourceDir)){
                        combinedTypeSolverNewCode.add(new JavaParserTypeSolver(sourceDir, parserConfiguration));
                        srcDirCache.add(sourceDir);
                    }
                }
            }
        }
        return createParserWithResolver(combinedTypeSolverNewCode);
    }

    private static JavaParser createParserWithResolver(TypeSolver typeSolver) {
        if(javaParser == null){
            javaParser =new JavaParser(new ParserConfiguration().setSymbolResolver(new JavaSymbolSolver(typeSolver)));
        }
        return javaParser;
    }

    public static ClassTypeDeclarationParse createDefaultClassTypeDeclarationParse(){
        if(classTypeDeclarationParse == null){
            List<JavaParserMemberParse> javaParserMemberParseList = ListUtil.of(new FieldMemberParse(),new InnerClassMemberParse(),new MethodMemberParse(),new ConstructorMemberParse(),new AnnotationMemberParse(),new EnumMemberParse());
            classTypeDeclarationParse = new ClassTypeDeclarationParse(javaParserMemberParseList);
        }
        return classTypeDeclarationParse;

    }

    public static EnumTypeDeclarationParse createDefaultEnumTypeDeclarationParse(){
        if(enumTypeDeclarationParse == null){
            List<JavaParserMemberParse> javaParserMemberParseList = ListUtil.of(new FieldMemberParse(),new InnerClassMemberParse(),new MethodMemberParse(),new ConstructorMemberParse(),new AnnotationMemberParse(),new EnumMemberParse());
            enumTypeDeclarationParse = new EnumTypeDeclarationParse(javaParserMemberParseList);
        }
        return enumTypeDeclarationParse;
    }
}
