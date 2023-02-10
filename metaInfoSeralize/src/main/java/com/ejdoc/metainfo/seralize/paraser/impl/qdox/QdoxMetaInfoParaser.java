package com.ejdoc.metainfo.seralize.paraser.impl.qdox;

import cn.hutool.core.collection.CollectionUtil;
import com.ejdoc.metainfo.seralize.dto.MetaFileInfoDto;
import com.ejdoc.metainfo.seralize.dto.MetaJavaClassInfoDto;
import com.ejdoc.metainfo.seralize.model.*;
import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.model.*;
import com.ejdoc.metainfo.seralize.paraser.impl.AbstractMetaInfoParaser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class QdoxMetaInfoParaser extends AbstractMetaInfoParaser {





    @Override
    public List<JavaModuleMeta> parseAllJavaModuletMeta() {
        List<JavaModuleMeta> javaModuleMetas = new ArrayList<>();
//        ClassLibraryBuilder classLibraryBuilder = new OrderedClassLibraryBuilder();
//        classLibraryBuilder.appendClassLoader(ClassLoaderUtil.getClassLoader());
//        JavaProjectBuilder builder = new JavaProjectBuilder( classLibraryBuilder );
        JavaProjectBuilder builder = new JavaProjectBuilder();
        builder.setEncoding( "UTF-8" );
        List<MetaFileInfoDto> metaFileInfos = metaFileRead.readAllMetaFile();
        try {

            for (MetaFileInfoDto metaFileInfo : metaFileInfos) {
                builder.addSource( metaFileInfo.getMetaFile() );
            }

            Collection<JavaClass> javaClasses = builder.getClasses();
            if ( javaClasses != null )
            {
                List<MetaJavaClassInfoDto> metaJavaClassInfoDtos = merageJavaClassAndMetaFileInfo(javaClasses,metaFileInfos);
                List<JavaClassMeta> javaClassMetaList = new ArrayList<>();
                for ( MetaJavaClassInfoDto metaJavaClassInfoDto : metaJavaClassInfoDtos )
                {
                    processJavaClassMeta( metaJavaClassInfoDto ,javaClassMetaList);
                }

                javaModuleMetas =  groupJavaClassMetaByModule(javaClassMetaList);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return javaModuleMetas;
    }

    private List<MetaJavaClassInfoDto> merageJavaClassAndMetaFileInfo(Collection<JavaClass> javaClasses, List<MetaFileInfoDto> metaFileInfos) {
        List<MetaJavaClassInfoDto> metaJavaClassInfoDtos = new ArrayList<>();
        if(CollectionUtil.isNotEmpty(javaClasses) && CollectionUtil.isNotEmpty(metaFileInfos)){
            MetaJavaClassInfoDto metaJavaClassInfoDto = null;
            for (JavaClass javaClass : javaClasses) {
                JavaSource source = javaClass.getSource();
                if(source != null){
                    String path = source.getURL().getPath();
                    for (MetaFileInfoDto metaFileInfo : metaFileInfos) {
                        if(path.equals(metaFileInfo.getMetaFilePath())){
                            metaJavaClassInfoDto = new MetaJavaClassInfoDto();
                            metaJavaClassInfoDto.setJavaClass(javaClass);
                            metaJavaClassInfoDto.setMetaFileInfoDto(metaFileInfo);
                            metaJavaClassInfoDtos.add(metaJavaClassInfoDto);
                            break;
                        }
                    }
                }
            }
        }
        return metaJavaClassInfoDtos;
    }

    private List<JavaModuleMeta> groupJavaClassMetaByModule(Collection<JavaClassMeta> javaClassMetaList) {

        List<JavaModuleMeta> result = new ArrayList<>();

        if(CollectionUtil.isNotEmpty(javaClassMetaList)){
//            Map<String, List<JavaClassMeta>> groupResultMap = new HashMap<>();
//            for (JavaClassMeta javaClassMeta : javaClassMetaList) {
//                List<JavaClassMeta> javaClassMetaList1 = groupResultMap.get(javaClassMeta.getModuleName());
//            }
            Map<String, List<JavaClassMeta>> groupResultMap = javaClassMetaList.stream().collect(Collectors.groupingBy(JavaClassMeta::getModuleName));
            groupResultMap.forEach((groupName,javaClassMetaGroupList) ->{
                JavaModuleMeta javaModuleMeta = new JavaModuleMeta();
                javaModuleMeta.setName(groupName);
                javaModuleMeta.setJavaClassMetas(javaClassMetaGroupList);
                result.add(javaModuleMeta);
            });

        }
        return result;
    }


    private void processJavaClassMeta( MetaJavaClassInfoDto metaJavaClassInfoDto ,List<JavaClassMeta> javaClassMetaList)
            throws IOException
    {

        JavaClass javaClass = metaJavaClassInfoDto.getJavaClass();

        MetaFileInfoDto metaFileInfoDto = metaJavaClassInfoDto.getMetaFileInfoDto();

        // Skipping inner classes
        if ( javaClass.isInner() )
        {
            return;
        }

        JavaClassMeta javaClassMeta = new JavaClassMeta();

        paraseJavaSouce(javaClassMeta,javaClass);

        paraseClassMetaProp(javaClassMeta,metaFileInfoDto,javaClass);

        paraseDeclaringClass(javaClassMeta,javaClass);

        paraseSuperClass(javaClassMeta,javaClass);
        paraseSuperJavaClass(javaClassMeta,javaClass);
        paraseInterfaces(javaClassMeta,javaClass);
        parasePackage(javaClassMeta,javaClass);
        paraseMethods(javaClassMeta,javaClass);
        paraseConstructors(javaClassMeta,javaClass);
        paraseFields(javaClassMeta,javaClass);
        paraseEnumConstants(javaClassMeta,javaClass);
        paraseNestedClasses(javaClassMeta,javaClass);
        paraseBeanProperties(javaClassMeta,javaClass);
        paraseModifiers(javaClassMeta,javaClass);
        paraseInitializers(javaClassMeta,javaClass);

        javaClassMetaList.add(javaClassMeta);
    }

    private void paraseInitializers(JavaClassMeta javaClassMeta, JavaClass javaClass) {
        List<JavaInitializer> initializers = javaClass.getInitializers();
    }

    private void paraseBeanProperties(JavaClassMeta javaClassMeta, JavaClass javaClass) {
        List<BeanProperty> beanProperties = javaClass.getBeanProperties();
    }

    private void paraseNestedClasses(JavaClassMeta javaClassMeta, JavaClass javaClass) {
        List<JavaClass> nestedClasses = javaClass.getNestedClasses();
        if(CollectionUtil.isNotEmpty(nestedClasses)){

            List<JavaClassMeta> nestedClassesMetaList = nestedClasses.stream()
                    .map(javaClassInner -> convertByJavaClass(javaClassInner))
                    .collect(Collectors.toList());
            javaClassMeta.setNestedClasses(nestedClassesMetaList);
        }
    }

    private void paraseEnumConstants(JavaClassMeta javaClassMeta, JavaClass javaClass) {
        List<JavaField> fields = javaClass.getEnumConstants();
        if(CollectionUtil.isNotEmpty(fields)){
            List<JavaFieldMeta> javaFieldMetas = new ArrayList<>();
            for (JavaField field : fields) {
                javaFieldMetas.add(convertByJavaField(field));

            }
            javaClassMeta.setFields(javaFieldMetas);
        }
    }

    private JavaFieldMeta convertByJavaField(JavaField javaField){
        JavaFieldMeta javaFieldMeta = new JavaFieldMeta();
        javaFieldMeta.setType(convertByJavaClass(javaField.getType()));
        javaFieldMeta.setEnumConstantClass(convertByJavaClass(javaField.getEnumConstantClass()));
        javaFieldMeta.setEnumConstant(javaField.isEnumConstant());
        javaFieldMeta.setJavaModelMeta(convertByJavaModel(javaField));
        javaFieldMeta.setInitializationExpression(javaField.getInitializationExpression());
        return javaFieldMeta;
    }

    private void paraseConstructors(JavaClassMeta javaClassMeta, JavaClass javaClass) {
        List<JavaConstructor> constructors = javaClass.getConstructors();
        if(CollectionUtil.isNotEmpty(constructors)){
            List<JavaConstructorMeta> javaConstructorMetaList  = new ArrayList<>();
            JavaConstructorMeta javaConstructorMeta = null;
            for (JavaConstructor constructor : constructors) {
                javaConstructorMeta = new JavaConstructorMeta();

                javaConstructorMeta.setJavaModelMeta(convertByJavaModel(constructor));
                javaConstructorMeta.setJavaMemberMeta(convertByJavaMember(constructor));
                javaConstructorMeta.setJavaExecutableMeta(convertByJavaExecutable(constructor));

                javaConstructorMetaList.add(javaConstructorMeta);
            }
            javaClassMeta.setConstructors(javaConstructorMetaList);
        }
    }

    private void parasePackage(JavaClassMeta javaClassMeta, JavaClass javaClass) {
        JavaPackage aPackage = javaClass.getPackage();
    }

    private void paraseInterfaces(JavaClassMeta javaClassMeta, JavaClass javaClass) {
        List<JavaClass> interfaces = javaClass.getInterfaces();
        if(CollectionUtil.isNotEmpty(interfaces)){
            List<JavaClassMeta> javaClassMetaList = new ArrayList<>();
            for (JavaClass anInterface : interfaces) {
                javaClassMetaList.add(convertByJavaClass(anInterface));
            }
            javaClassMeta.setInterfaces(javaClassMetaList);
        }
    }

    private void paraseSuperJavaClass(JavaClassMeta javaClassMeta, JavaClass javaClass) {
        JavaClass superJavaClass = javaClass.getSuperJavaClass();
        if(superJavaClass != null){
            javaClassMeta.setSuperJavaClass(convertByJavaClass(superJavaClass));
        }
    }

    private void paraseSuperClass(JavaClassMeta javaClassMeta, JavaClass javaClass) {
        JavaType superClass = javaClass.getSuperClass();
        if(superClass != null){
            javaClassMeta.setSuperClass(convertByJavaType(superClass));
        }

    }

    private void paraseModifiers(JavaClassMeta javaClassMeta, JavaClass javaClass) {
        javaClassMeta.setModifiers(javaClass.getModifiers());
    }

    private void paraseFields(JavaClassMeta javaClassMeta, JavaClass javaClass) {
        List<JavaField> fields = javaClass.getFields();
        if(CollectionUtil.isNotEmpty(fields)){
            List<JavaFieldMeta> javaFieldMetas = new ArrayList<>();
            for (JavaField field : fields) {
                javaFieldMetas.add(convertByJavaField(field));

            }
            javaClassMeta.setFields(javaFieldMetas);
        }
    }

    private JavaModelMeta convertByJavaModel(JavaModel javaModel){
        JavaModelMeta javaModelMeta = new JavaModelMeta();
        javaModelMeta.setLineNumber(javaModel.getLineNumber());
        javaModelMeta.setCodeBlock(javaModel.getCodeBlock());
        return javaModelMeta;
    }
    private void paraseMethods(JavaClassMeta javaClassMeta, JavaClass javaClass) {
        List<JavaMethodMeta> javaMethodMetas = new ArrayList<>();
        List<JavaMethod> methods = javaClass.getMethods();
        if(CollectionUtil.isNotEmpty(methods)){
            JavaMethodMeta javaMethodMeta = null;
            for (JavaMethod method : methods) {
                javaMethodMeta = new JavaMethodMeta();
                javaMethodMeta.setName(method.getName());
                javaMethodMeta.setCallSignature(method.getCallSignature());
                JavaModelMeta javaModelMeta = new JavaModelMeta();
                javaModelMeta.setLineNumber(method.getLineNumber());
                javaModelMeta.setCodeBlock(method.getCodeBlock());

                List<JavaDocletTagMeta> docletTagMetas = new ArrayList<>();
                List<DocletTag> methodTags = method.getTags();
                if(CollectionUtil.isNotEmpty(methodTags)){
                    JavaDocletTagMeta javaDocletTagMeta = null;
                    for (DocletTag methodTag : methodTags) {
                        javaDocletTagMeta = new JavaDocletTagMeta();
                        javaDocletTagMeta.setName(methodTag.getName());
                        javaDocletTagMeta.setValue(methodTag.getValue());
                        javaDocletTagMeta.setParameters(methodTag.getParameters());
                        javaDocletTagMeta.setLineNumber(methodTag.getLineNumber());
                        javaDocletTagMeta.setNamedParameterMap(methodTag.getNamedParameterMap());
                        docletTagMetas.add(javaDocletTagMeta);
                    }
                }
                javaModelMeta.setComment(method.getComment());
                javaModelMeta.setTags(docletTagMetas);


                List<JavaAnnotationMeta> annotationMetas = new ArrayList<>();
                List<JavaAnnotation> annotations = method.getAnnotations();
                if(CollectionUtil.isNotEmpty(annotations)){
                    JavaAnnotationMeta javaAnnotationMeta = null;
                    for (JavaAnnotation annotation : annotations) {
                        javaAnnotationMeta = new JavaAnnotationMeta();
                        javaAnnotationMeta.setCodeBlock(annotation.getCodeBlock());
                        javaAnnotationMeta.setLineNumber(annotation.getLineNumber());

                        javaAnnotationMeta.setNamedParameterMap(annotation.getNamedParameterMap());
                        JavaClass type = annotation.getType();
                        if(type != null){
                            javaAnnotationMeta.setType(convertByJavaClass(type));
                        }

                        annotationMetas.add(javaAnnotationMeta);

                    }
                }
                javaModelMeta.setAnnotations(annotationMetas);
                javaMethodMeta.setJavaModelMeta(javaModelMeta);

                javaMethodMeta.setPropertyName(method.getPropertyName());


                JavaType propertyType = method.getPropertyType();
                if(propertyType != null){
                    javaMethodMeta.setPropertyType(convertByJavaType(propertyType));
                }

                JavaType returnType = method.getReturnType();
                if(returnType != null){
                    javaMethodMeta.setReturnType(convertByJavaType(returnType));
                }

                JavaClass returns = method.getReturns();
                if(returns != null){
                    javaMethodMeta.setReturns(convertByJavaClass(returns));
                }

//                JavaExecutable javaExecutable = (JavaExecutable)method;
                JavaExecutableMeta javaExecutableMeta = convertByJavaExecutable(method);

                javaMethodMeta.setJavaExecutableMeta(javaExecutableMeta);

                javaMethodMeta.setJavaMemberMeta(convertByJavaMember(method));

                javaMethodMetas.add(javaMethodMeta);
            }
        }
        javaClassMeta.setMethods(javaMethodMetas);
    }

    private JavaExecutableMeta convertByJavaExecutable(JavaExecutable method) {
        JavaExecutableMeta javaExecutableMeta = new JavaExecutableMeta();

        JavaClass declaringClass = method.getDeclaringClass();
        if(declaringClass != null){
            javaExecutableMeta.setDeclaringClass(convertByJavaClass(declaringClass));
        }

        List<JavaClass> exceptions = method.getExceptions();
        if(CollectionUtil.isNotEmpty(exceptions)){
            List<JavaClassMeta> exceptionClassMetaList = new ArrayList<>();
            for (JavaClass exception : exceptions) {
                exceptionClassMetaList.add(convertByJavaClass(exception));

            }
            javaExecutableMeta.setExceptions(exceptionClassMetaList);
        }

        List<JavaType> exceptionTypes = method.getExceptionTypes();
        if(CollectionUtil.isNotEmpty(exceptionTypes)){
            List<JavaTypeMeta> javaTypeMetaList = new ArrayList<>();
            for (JavaType exceptionType : exceptionTypes) {
                javaTypeMetaList.add(convertByJavaType(exceptionType));
            }
            javaExecutableMeta.setExceptionTypes(javaTypeMetaList);
        }

        List<JavaParameter> parameters = method.getParameters();
        if(CollectionUtil.isNotEmpty(parameters)){
            List<JavaParameterMeta> javaParameterMetas = new ArrayList<>();
            JavaParameterMeta javaParameterMeta = null;
            try{
                for (JavaParameter parameter : parameters) {
                    javaParameterMeta = new JavaParameterMeta();
                    javaParameterMeta.setCanonicalName(parameter.getCanonicalName());
                    javaParameterMeta.setName(parameter.getName());
                    javaParameterMeta.setValue(parameter.getValue());
                    javaParameterMeta.setFullyQualifiedName(parameter.getFullyQualifiedName());
                    javaParameterMeta.setResolvedFullyQualifiedName(parameter.getResolvedFullyQualifiedName());
                    javaParameterMeta.setResolvedGenericValue(parameter.getResolvedGenericValue());
                    javaParameterMeta.setVarArgs(parameter.isVarArgs());

                    JavaType type = parameter.getType();
                    if(type != null){
                        javaParameterMeta.setType(convertByJavaType(type));
                    }
                    JavaClass parameterJavaClass = parameter.getJavaClass();
                    if(parameterJavaClass != null){
                        javaParameterMeta.setJavaClass(convertByJavaClass(parameterJavaClass));
                    }

                    JavaExecutable executable = parameter.getExecutable();
                    if(executable != null){

                    }

                    JavaClass declaringClass1 = parameter.getDeclaringClass();
                    if(declaringClass1 != null){
                        javaParameterMeta.setDeclaringClass(convertByJavaClass(declaringClass1));
                    }


                    javaParameterMetas.add(javaParameterMeta);
                }
            }catch (Exception e){
                System.out.println(method.getCodeBlock());
//                e.printStackTrace();
//                throw new RuntimeException(e);
            }


            javaExecutableMeta.setParameters(javaParameterMetas);
        }

        List<JavaType> parameterTypes = method.getParameterTypes();
        if(CollectionUtil.isNotEmpty(parameterTypes)){
            List<JavaTypeMeta> javaTypeMetaList = new ArrayList<>();
            for (JavaType parameterType : parameterTypes) {
                javaTypeMetaList.add(convertByJavaType(parameterType));
            }
            javaExecutableMeta.setParameterTypes(javaTypeMetaList);
        }

        javaExecutableMeta.setSourceCode(method.getSourceCode());
        return javaExecutableMeta;
    }

    private JavaMemberMeta convertByJavaMember(JavaMember javaMember){
        JavaMemberMeta javaMemberMeta = new JavaMemberMeta();

        javaMemberMeta.setModifiers(javaMember.getModifiers());
        if(javaMember.isAbstract()){
            javaMemberMeta.setAbstracts(javaMember.isAbstract());
        }
        if(javaMember.isFinal()){
            javaMemberMeta.setFinals(javaMember.isFinal());
        }
        if(javaMember.isNative()){
            javaMemberMeta.setNatives(javaMember.isNative());
        }
        if(javaMember.isPrivate()){
            javaMemberMeta.setPrivates(javaMember.isPrivate());
        }
        if(javaMember.isProtected()){
            javaMemberMeta.setProtecteds(javaMember.isProtected());
        }
        if(javaMember.isPublic()){
            javaMemberMeta.setPublics(javaMember.isPublic());
        }
        if(javaMember.isStatic()){
            javaMemberMeta.setStatics(javaMember.isStatic());
        }
        if(javaMember.isStrictfp()){
            javaMemberMeta.setStrictfps(javaMember.isStrictfp());
        }
        if(javaMember.isSynchronized()){
            javaMemberMeta.setSynchronizeds(javaMember.isSynchronized());
        }
        if(javaMember.isTransient()){
            javaMemberMeta.setTransients(javaMember.isTransient());
        }
        if(javaMember.isVolatile()){
            javaMemberMeta.setVolatiles(javaMember.isVolatile());
        }

        return javaMemberMeta;
    }
    private JavaTypeMeta convertByJavaType(JavaType javaType){
        if(javaType == null){
            return null;
        }
        JavaTypeMeta javaTypeMeta = new JavaTypeMeta();
        javaTypeMeta.setBinaryName(javaType.getBinaryName());
        javaTypeMeta.setCanonicalName(javaType.getCanonicalName());
        javaTypeMeta.setFullyQualifiedName(javaType.getFullyQualifiedName());
        javaTypeMeta.setGenericString(javaType.toGenericString());
        javaTypeMeta.setGenericValue(javaType.getGenericValue());
        javaTypeMeta.setValue(javaType.getValue());
        javaTypeMeta.setGenericCanonicalName(javaType.getGenericCanonicalName());
        return javaTypeMeta;
    }

    private JavaClassMeta convertByJavaClass(JavaClass javaClass){
        if(javaClass == null){
            return null;
        }
        JavaClassMeta javaClassMeta = new JavaClassMeta();
        javaClassMeta.setClassName(javaClass.getSimpleName());
        javaClassMeta.setFullClassName(javaClass.getName());
        return javaClassMeta;
    }


    private void paraseDeclaringClass(JavaClassMeta javaClassMeta, JavaClass javaClass) {
        javaClassMeta.setDeclaringClass(convertByJavaClass(javaClass.getDeclaringClass()));

    }

    private void paraseClassMetaProp(JavaClassMeta javaClassMeta, MetaFileInfoDto metaFileInfoDto, JavaClass javaClass) {
        javaClassMeta.setClassName(javaClass.getSimpleName());
        javaClassMeta.setFullClassName(javaClass.getName());
        javaClassMeta.setValue(javaClass.getValue());

        if(javaClass.isAbstract()){
            javaClassMeta.setAbstractClass(javaClass.isAbstract());
        }
        if(javaClass.isEnum()){
            javaClassMeta.setEnumClass(javaClass.isEnum());
        }
        if(javaClass.isFinal()){
            javaClassMeta.setFinalClass(javaClass.isFinal());
        }
        if(javaClass.isInterface()){
            javaClassMeta.setInterfaceClass(javaClass.isInterface());
        }
        if(javaClass.isPrivate()){
            javaClassMeta.setPrimitiveClass(javaClass.isPrivate());
        }
        if(javaClass.isPrivate()){
            javaClassMeta.setPrivateClass(javaClass.isPrivate());
        }
        if(javaClass.isProtected()){
            javaClassMeta.setProtectedClass(javaClass.isProtected());
        }
        if(javaClass.isPublic()){
            javaClassMeta.setPublicClass(javaClass.isPublic());
        }
        if(javaClass.isAnnotation()){
            javaClassMeta.setAnnotationClass(javaClass.isAnnotation());
        }
        if(javaClass.isStatic()){
            javaClassMeta.setStaticClass(javaClass.isStatic());
        }
        if(javaClass.isVoid()){
            javaClassMeta.setVoidClass(javaClass.isVoid());
        }

        javaClassMeta.setModuleName(metaFileInfoDto.getModuleName());
        javaClassMeta.setProjectName(metaFileInfoDto.getProjectName());
    }

    private void paraseJavaSouce(JavaClassMeta javaClassMeta, JavaClass javaClass) {
        JavaSource source = javaClass.getSource();

//        JavaSourceMeta javaSourceMeta = new JavaSourceMeta();

//        JavaModelMeta sourceJavaModelMeta = new JavaModelMeta();
//        sourceJavaModelMeta.setCodeBlock(source.getCodeBlock());
//        javaSourceMeta.setJavaModelMeta(sourceJavaModelMeta);



        javaClassMeta.setImports(source.getImports());
        javaClassMeta.setUrl(source.getURL());
        javaClassMeta.setClassNamePrefix(source.getClassNamePrefix());
        javaClassMeta.setPackageName(source.getPackageName());



        JavaPackage currentJavaPackage = source.getPackage();

        JavaPackageMeta javaPackageMeta = new JavaPackageMeta();
        javaPackageMeta.setName(currentJavaPackage.getName());
        JavaModelMeta javaModelMeta = new JavaModelMeta();
        javaModelMeta.setCodeBlock(currentJavaPackage.getCodeBlock());
        javaModelMeta.setLineNumber(currentJavaPackage.getLineNumber());
        javaPackageMeta.setJavaModelMeta(javaModelMeta);


        JavaPackageMeta parentJavaPackageMeta = new JavaPackageMeta();
        JavaPackage parentPackage = currentJavaPackage.getParentPackage();
        if(parentPackage != null){
            parentJavaPackageMeta.setName(parentPackage.getName());
            JavaModelMeta parentJavaModelMeta = new JavaModelMeta();
            parentJavaModelMeta.setCodeBlock(parentPackage.getCodeBlock());
            parentJavaModelMeta.setLineNumber(parentPackage.getLineNumber());
            parentJavaPackageMeta.setJavaModelMeta(parentJavaModelMeta);

            javaPackageMeta.setParentPackage(parentJavaPackageMeta);
        }


//        List<JavaPackageMeta> subPackageList = new ArrayList<>();


        javaClassMeta.setJavaPackageMeta(javaPackageMeta);

//        javaClassMeta.setJavaSourceMeta(javaSourceMeta);

    }



}
