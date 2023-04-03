package com.ejdoc.metainfo.seralize.parser.impl.javaparser.type;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.ejdoc.metainfo.seralize.dto.MetaFileInfoDto;
import com.ejdoc.metainfo.seralize.model.JavaClassMeta;
import com.ejdoc.metainfo.seralize.model.JavaModelMeta;
import com.ejdoc.metainfo.seralize.parser.impl.javaparser.BaseJavaParse;
import com.ejdoc.metainfo.seralize.parser.impl.javaparser.JavaParserMetaContext;
import com.ejdoc.metainfo.seralize.parser.impl.javaparser.JavaParserMetaInfoParser;
import com.ejdoc.metainfo.seralize.parser.impl.javaparser.UnSolvedSymbolTool;
import com.ejdoc.metainfo.seralize.parser.impl.javaparser.member.JavaParserMemberParse;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.nodeTypes.NodeWithName;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.type.TypeParameter;
import com.github.javaparser.javadoc.Javadoc;
import com.github.javaparser.resolution.UnsolvedSymbolException;
import com.github.javaparser.resolution.types.ResolvedType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class AbstractJavaParserTypeDeclarationParse extends BaseJavaParse implements JavaParserTypeDeclarationParse {
    private static final Logger log = LoggerFactory.getLogger(JavaParserMetaInfoParser.class);

    private List<JavaParserMemberParse> javaParserMemberParseList;

    public AbstractJavaParserTypeDeclarationParse(List<JavaParserMemberParse> javaParserMemberParseList){
        this.javaParserMemberParseList = javaParserMemberParseList;
    }
    @Override
    public JavaClassMeta parseTypeToJavaClassMeta(MetaFileInfoDto metaFileInfo, CompilationUnit rootAst, TypeDeclaration<?> typeDeclaration, JavaParserMetaContext javaParserMetaContext) {
        JavaClassMeta javaClassMeta = new JavaClassMeta();
        parseClassMetaProp(javaClassMeta, metaFileInfo, typeDeclaration,javaParserMetaContext);
        parseJavaSouce(javaClassMeta, rootAst,javaParserMetaContext);
        parseDocAndAnnotation(javaClassMeta, typeDeclaration, rootAst,javaParserMetaContext);
        parseNestedJavaClass(javaClassMeta, typeDeclaration, rootAst,javaParserMetaContext);
        parseModifiers(javaClassMeta, typeDeclaration,javaParserMetaContext);
        parseMembers(javaClassMeta, metaFileInfo, typeDeclaration,javaParserMetaContext);
        parseChildTypeToJavaClassMeta(metaFileInfo,javaClassMeta,rootAst,typeDeclaration,javaParserMetaContext);
        parseClassDeclaration(javaClassMeta,javaParserMetaContext);
        return javaClassMeta;
    }

    private void parseChildTypeToJavaClassMeta(MetaFileInfoDto metaFileInfo, JavaClassMeta javaClassMeta, CompilationUnit rootAst, TypeDeclaration<?> typeDeclaration,JavaParserMetaContext javaParserMetaContext) {
        if(accept(typeDeclaration,metaFileInfo)){
            doParseChildTypeToJavaClassMeta(metaFileInfo,javaClassMeta,rootAst,typeDeclaration,javaParserMetaContext);
        }
    }

    protected  void parseMembers(JavaClassMeta javaClassMeta, MetaFileInfoDto metaFileInfo, TypeDeclaration<?> typeDeclaration,JavaParserMetaContext javaParserMetaContext){
        List<JavaParserMemberParse> memberParseList = getJavaParserMemberParseList();
        if(CollectionUtil.isNotEmpty(memberParseList)){
            for (JavaParserMemberParse javaParserMemberParse : memberParseList) {
                javaParserMemberParse.parseMemberToJavaClassMeta(javaClassMeta,metaFileInfo,typeDeclaration,javaParserMetaContext);
            }
        }
    }



    protected abstract void doParseChildTypeToJavaClassMeta(MetaFileInfoDto metaFileInfo, JavaClassMeta javaClassMeta, CompilationUnit rootAst, TypeDeclaration<?> typeDeclaration,JavaParserMetaContext javaParserMetaContext);

    /**
     * 解析类声明结构
     * @param javaClassMeta
     */
    protected void parseClassDeclaration(JavaClassMeta javaClassMeta,JavaParserMetaContext javaParserMetaContext) {
        javaClassMeta.setDeclarationStructure(javaClassMeta.parseDeclarationStructure());
    }


    protected void parseModifiers(JavaClassMeta javaClassMeta, TypeDeclaration<?> typeDeclaration,JavaParserMetaContext javaParserMetaContext) {
        NodeList<Modifier> modifiers = ObjectUtil.isNull(typeDeclaration) ?null:typeDeclaration.getModifiers();
        if(CollectionUtil.isNotEmpty(modifiers)){
            List<String> collect = modifiers.stream().map(modifier -> modifier.getKeyword().asString()).collect(Collectors.toList());
            javaClassMeta.setModifiers(collect);
        }

    }
    /**
     *  嵌套类解析
     * @param javaClassMeta
     * @param typeDeclaration
     * @param rootAst
     */
    protected void parseNestedJavaClass(JavaClassMeta javaClassMeta, TypeDeclaration<?> typeDeclaration, CompilationUnit rootAst,JavaParserMetaContext javaParserMetaContext) {
        if(ObjectUtil.isNotNull(typeDeclaration)){

        }
    }

    protected void parseSuperJavaClass(JavaClassMeta javaClassMeta, TypeDeclaration<?> typeDeclaration) {

        if(typeDeclaration.isClassOrInterfaceDeclaration()){
            NodeList<ClassOrInterfaceType> extendedTypes = ((ClassOrInterfaceDeclaration)typeDeclaration).getExtendedTypes();

            if(CollectionUtil.isNotEmpty(extendedTypes)){
                List<JavaClassMeta> superClasses = new ArrayList<>();
                for (ClassOrInterfaceType extendedType : extendedTypes) {
                    superClasses.add(convertClassOrInterfaceTypeToSimpleClassMeta(extendedType));
                }
                javaClassMeta.setSuperClasses(superClasses);
            }
        }
    }

    protected JavaClassMeta convertClassOrInterfaceTypeToSimpleClassMeta(ClassOrInterfaceType implementedType) {
        if(implementedType == null){
            return null;
        }
        JavaClassMeta javaClassMeta = new JavaClassMeta();
        Optional<NodeList<Type>> typeArguments = implementedType.getTypeArguments();
        if(typeArguments.isPresent()){
            List<String> collect = typeArguments.get().stream().map(Type::asString).collect(Collectors.toList());
            javaClassMeta.setTypeParameters(collect);
        }
        javaClassMeta.setClassName(implementedType.getNameAsString());
        javaClassMeta.setFullClassName(implementedType.getNameAsString());
        ResolvedType resolve = getRefClassResolvedType(implementedType);
        setFullClassNameFromResolvedType(javaClassMeta,resolve);
        return javaClassMeta;
    }




    protected  ResolvedType getRefClassResolvedType(ClassOrInterfaceType implementedType) {
        try {
            ResolvedType resolve = implementedType.resolve();
            return resolve;
        }catch (UnsolvedSymbolException ue){
            log.debug("getRefClassResolvedType error",ue);
            UnSolvedSymbolTool.addUnSolveTOCache(ue.getMessage());
            return null;
        } catch (Exception e) {
            log.error("getRefClassResolvedType error",e);
            return null;
        }
    }

    protected void parseJavaSouce(JavaClassMeta javaClassMeta, CompilationUnit rootAst,JavaParserMetaContext javaParserMetaContext) {
        Optional<PackageDeclaration> packageDeclarationOptional = rootAst.getPackageDeclaration();
        if(packageDeclarationOptional.isPresent()){
            PackageDeclaration packageDeclaration = rootAst.getPackageDeclaration().get();

            CompilationUnit node = rootAst;
            List<String> importList = node.getImports().stream().map(NodeWithName::getNameAsString).collect(Collectors.toList());
            javaClassMeta.setImports(importList);

//            try {
//                URL url = node.getStorage().get().getPath().toUri().toURL();
//                String path = url.getPath();
//                javaClassMeta.setUrl(url);
//                javaClassMeta.setAbsolutePath(node.getStorage().get().getPath().toFile().getAbsolutePath());
//                javaClassMeta.setRelativePath(path.substring(path.indexOf("src")));
//            } catch (MalformedURLException e) {
//            }

            javaClassMeta.setClassNamePrefix(packageDeclaration.getNameAsString()+".");
            javaClassMeta.setPackageName(packageDeclaration.getNameAsString());
        }
    }
    protected void parseDocAndAnnotation(JavaClassMeta javaClassMeta, TypeDeclaration<?> typeDeclaration, CompilationUnit rootAst,JavaParserMetaContext javaParserMetaContext) {
        if(typeDeclaration != null){
            Optional<Javadoc> javadoc = typeDeclaration.getJavadoc();
            NodeList<AnnotationExpr> annotations = typeDeclaration.getAnnotations();
            JavaModelMeta javaModelMeta = new JavaModelMeta();
            createJavaDocTag(javadoc,javaModelMeta);
            setAnnotations(annotations, javaModelMeta);
            javaClassMeta.setJavaModelMeta(javaModelMeta);
        }else{
            if(ObjectUtil.isNotNull(rootAst)){
                Optional<Comment> comment = rootAst.getComment();
                if(comment.isPresent()){
                    JavaModelMeta javaModelMeta = new JavaModelMeta();
                    javaModelMeta.setComment(comment.get().getContent());
                    javaClassMeta.setJavaModelMeta(javaModelMeta);
                }
            }
        }
    }



    /**
     * 解析class基本信息
     *
     * @param javaClassMeta
     * @param metaFileInfoDto
     * @param typeDeclaration
     */
    protected void parseClassMetaProp(JavaClassMeta javaClassMeta, MetaFileInfoDto metaFileInfoDto,TypeDeclaration<?> typeDeclaration,JavaParserMetaContext javaParserMetaContext) {
        javaClassMeta.setModuleName(metaFileInfoDto.getModuleName());
        javaClassMeta.setModuleDesc(metaFileInfoDto.getModuleDesc());
        javaClassMeta.setProjectName(metaFileInfoDto.getProjectName());
        if(typeDeclaration != null){
            SimpleName simpleName = typeDeclaration.getName();
            javaClassMeta.setClassName(simpleName.getIdentifier());
            javaClassMeta.setFullClassName(typeDeclaration.getFullyQualifiedName().get());
            javaClassMeta.setValue(simpleName.getIdentifier());


            if(typeDeclaration.isPrivate()){
                javaClassMeta.setPrimitiveClass(typeDeclaration.isPrivate());
            }
            if(typeDeclaration.isPrivate()){
                javaClassMeta.setPrivateClass(typeDeclaration.isPrivate());
            }
            if(typeDeclaration.isProtected()){
                javaClassMeta.setProtectedClass(typeDeclaration.isProtected());
            }
            if(typeDeclaration.isPublic()){
                javaClassMeta.setPublicClass(typeDeclaration.isPublic());
            }
            if(typeDeclaration.isAnnotationDeclaration()){
                javaClassMeta.setAnnotationClass(typeDeclaration.isAnnotationDeclaration());
            }
            if(typeDeclaration.isEnumDeclaration()){
                javaClassMeta.setEnumClass(typeDeclaration.isEnumDeclaration());
            }
            if(typeDeclaration.isStatic()){
                javaClassMeta.setStaticClass(typeDeclaration.isStatic());
            }
            if(typeDeclaration.isEmpty()){
                javaClassMeta.setVoidClass(typeDeclaration.isEmpty());
            }

            if(typeDeclaration.isClassOrInterfaceDeclaration()){
                ClassOrInterfaceDeclaration javaClass = (ClassOrInterfaceDeclaration)typeDeclaration;
                NodeList<TypeParameter> typeParameters = javaClass.getTypeParameters();
                if(CollectionUtil.isNotEmpty(typeParameters)){
                    List<String> typeParameterList = new ArrayList<>();
                    for (TypeParameter typeParameter : typeParameters) {
                        typeParameterList.add(typeParameter.getNameAsString());
                    }
                    javaClassMeta.setTypeParameters(typeParameterList);
                }

                if(javaClass.isAbstract()){
                    javaClassMeta.setAbstractClass(javaClass.isAbstract());
                }
                if(javaClass.isFinal()){
                    javaClassMeta.setFinalClass(javaClass.isFinal());
                }
                if(javaClass.isInterface()){
                    javaClassMeta.setInterfaceClass(javaClass.isInterface());
                }
                if(javaClass.isInnerClass()){
                    javaClassMeta.setInnerClass(javaClass.isInnerClass());
                }
            }
        }
    }

    public List<JavaParserMemberParse> getJavaParserMemberParseList() {
        return javaParserMemberParseList;
    }

    public void setJavaParserMemberParseList(List<JavaParserMemberParse> javaParserMemberParseList) {
        this.javaParserMemberParseList = javaParserMemberParseList;
    }
}
