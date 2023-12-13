package com.ejdoc.metainfo.seralize.model;

import cn.hutool.core.util.StrUtil;

import java.io.Serializable;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class JavaClassMeta  implements Serializable {

    private String projectName;

    private String moduleName;

    private String moduleDesc;

    private String packageName;
    private String packageDesc;

    private String className;

    /**
     * 类描述
     */
    private String classDesc;

    private String fullClassName;

    /**
     * 数组类全名称
     */
    private String arrayFullClassName;

    /**嵌套类全名称*/
    private String nestedClassFullClassName;

    /**嵌套类*/
    private String nestedClassName;

    /**类型参数类名*/
    private String typeArgExtendClassName;

    /**类型参数全路径*/
    private String typeArgExtendFullClassName;
    /**
     * 类型注解参数
     */
    private List<String> typeParameters;

    /**
     * 类型参数
     */
    private List<JavaClassMeta> typeArguments;

    private String classNamePrefix;

    private String value;

    private String declarationStructure;

    private URL url;

    private String relativePath;

    private String absolutePath;

    private String dependencyRelativePath;

    private String dependencyAbsolutePath;

    private List<JavaClassImportMeta> imports;

    /**
     * 注释注解等
     */
    private JavaModelMeta javaModelMeta;



    private Boolean abstractClass;

    private Boolean enumClass;

    private Boolean finalClass;

    private Boolean interfaceClass;

    private Boolean primitiveClass;

    private Boolean privateClass;

    private Boolean protectedClass;

    private Boolean publicClass;

    private Boolean annotationClass;

    private Boolean staticClass;
    private Boolean innerClass;
    private Boolean nestedClass;

    private Boolean voidClass;

    private Boolean jdkClass;
    /**类型参数*/
    private Boolean typeParameter;
    /**通配符类型，类似结构：Class<? extends Aspect> */
    private Boolean wildcardType;

    private List<JavaClassMeta> superClasses;

    private List<String> modifiers;

    private List<JavaMethodMeta> methods;

    private List<JavaFieldMeta> fields;

    private List<JavaAnnotationMeta> annotationFields;

    /**
     * 枚举信息
     */
    private List<JavaClassMeta> enumConstants;

    private List<JavaClassMeta> interfaces;

    /**
     * 即内部类
     */
    private List<JavaClassMeta> innerClasses;

    /**嵌套类*/
    private List<JavaClassMeta> nestedClasses;

    private  List<JavaConstructorMeta> constructors;
    /**
     * 字段内部索引
     */
    private Map<String,String> fieldMetaIndex;
    /**
     * 方法内部索引
     */
    private Map<String,String> methodMetaIndex;
    /**
     * 构造函数内部索引
     */
    private Map<String,String> constructorMetaIndex;

    public JavaClassMeta(){}

    public JavaClassMeta(String className,String fullClassName){
        this.className = className;
        this.fullClassName = fullClassName;
    }
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getFullClassName() {
        return fullClassName;
    }

    public void setFullClassName(String fullClassName) {
        if(StrUtil.contains(fullClassName,"$")){
            fullClassName= fullClassName.replace("$",".");
        }
        this.fullClassName = fullClassName;
    }

    public JavaModelMeta getJavaModelMeta() {
        return javaModelMeta;
    }

    public void setJavaModelMeta(JavaModelMeta javaModelMeta) {
        this.javaModelMeta = javaModelMeta;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<JavaClassMeta> getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(List<JavaClassMeta> interfaces) {
        this.interfaces = interfaces;
    }

    public List<String> getModifiers() {
        return modifiers;
    }

    public void setModifiers(List<String> modifiers) {
        this.modifiers = modifiers;
    }

    public List<JavaMethodMeta> getMethods() {
        return methods;
    }

    public void setMethods(List<JavaMethodMeta> methods) {
        this.methods = methods;
    }

    public List<JavaFieldMeta> getFields() {
        return fields;
    }

    public void setFields(List<JavaFieldMeta> fields) {
        this.fields = fields;
    }

    public List<String> getTypeParameters() {
        return typeParameters;
    }


    public void setTypeParameters(List<String> typeParameters) {
        this.typeParameters = typeParameters;
    }

    public List<JavaClassMeta> getSuperClasses() {
        return superClasses;
    }

    public void setSuperClasses(List<JavaClassMeta> superClasses) {
        this.superClasses = superClasses;
    }

    public List<JavaConstructorMeta> getConstructors() {
        return constructors;
    }

    public void setConstructors(List<JavaConstructorMeta> constructors) {
        this.constructors = constructors;
    }

    public List<JavaClassMeta> getEnumConstants() {
        return enumConstants;
    }

    public void setEnumConstants(List<JavaClassMeta> enumConstants) {
        this.enumConstants = enumConstants;
    }

    public List<JavaClassMeta> getInnerClasses() {
        return innerClasses;
    }

    public void setInnerClasses(List<JavaClassMeta> innerClasses) {
        this.innerClasses = innerClasses;
    }

    public Boolean getAbstractClass() {
        return abstractClass;
    }

    public void setAbstractClass(Boolean abstractClass) {
        this.abstractClass = abstractClass;
    }

    public Boolean getEnumClass() {
        return enumClass;
    }

    public void setEnumClass(Boolean enumClass) {
        this.enumClass = enumClass;
    }

    public Boolean getFinalClass() {
        return finalClass;
    }

    public void setFinalClass(Boolean finalClass) {
        this.finalClass = finalClass;
    }

    public Boolean getInterfaceClass() {
        return interfaceClass;
    }

    public void setInterfaceClass(Boolean interfaceClass) {
        this.interfaceClass = interfaceClass;
    }

    public Boolean getPrimitiveClass() {
        return primitiveClass;
    }

    public void setPrimitiveClass(Boolean primitiveClass) {
        this.primitiveClass = primitiveClass;
    }

    public Boolean getPrivateClass() {
        return privateClass;
    }

    public void setPrivateClass(Boolean privateClass) {
        this.privateClass = privateClass;
    }

    public Boolean getProtectedClass() {
        return protectedClass;
    }

    public void setProtectedClass(Boolean protectedClass) {
        this.protectedClass = protectedClass;
    }

    public Boolean getPublicClass() {
        return publicClass;
    }

    public void setPublicClass(Boolean publicClass) {
        this.publicClass = publicClass;
    }

    public Boolean getAnnotationClass() {
        return annotationClass;
    }

    public void setAnnotationClass(Boolean annotationClass) {
        this.annotationClass = annotationClass;
    }

    public Boolean getStaticClass() {
        return staticClass;
    }

    public void setStaticClass(Boolean staticClass) {
        this.staticClass = staticClass;
    }

    public Boolean getVoidClass() {
        return voidClass;
    }

    public void setVoidClass(Boolean voidClass) {
        this.voidClass = voidClass;
    }

    public String getClassNamePrefix() {
        return classNamePrefix;
    }

    public void setClassNamePrefix(String classNamePrefix) {
        this.classNamePrefix = classNamePrefix;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public List<JavaClassImportMeta> getImports() {
        return imports;
    }

    public void setImports(List<JavaClassImportMeta> imports) {
        this.imports = imports;
    }

    public String getRelativePath() {
        return relativePath;
    }

    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }

    public String getDeclarationStructure() {
        return declarationStructure;
    }

    public void setDeclarationStructure(String declarationStructure) {
        this.declarationStructure = declarationStructure;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    public String getDependencyRelativePath() {
        return dependencyRelativePath;
    }

    public void setDependencyRelativePath(String dependencyRelativePath) {
        this.dependencyRelativePath = dependencyRelativePath;
    }

    public String getDependencyAbsolutePath() {
        return dependencyAbsolutePath;
    }

    public void setDependencyAbsolutePath(String dependencyAbsolutePath) {
        this.dependencyAbsolutePath = dependencyAbsolutePath;
    }

    public List<JavaClassMeta> getTypeArguments() {
        return typeArguments;
    }

    public void setTypeArguments(List<JavaClassMeta> typeArguments) {
        this.typeArguments = typeArguments;
    }

    public String getArrayFullClassName() {
        return arrayFullClassName;
    }

    public void setArrayFullClassName(String arrayFullClassName) {
        this.arrayFullClassName = arrayFullClassName;
    }

    public Boolean getInnerClass() {
        return innerClass;
    }


    public List<JavaAnnotationMeta> getAnnotationFields() {
        return annotationFields;
    }

    public void setAnnotationFields(List<JavaAnnotationMeta> annotationFields) {
        this.annotationFields = annotationFields;
    }

    public void setInnerClass(Boolean innerClass) {
        this.innerClass = innerClass;
    }

    public String getModuleDesc() {
        return moduleDesc;
    }

    public void setModuleDesc(String moduleDesc) {
        this.moduleDesc = moduleDesc;
    }


    public String getPackageDesc() {
        return packageDesc;
    }

    public void setPackageDesc(String packageDesc) {
        this.packageDesc = packageDesc;
    }

    public String getClassDesc() {
        return classDesc;
    }

    public void setClassDesc(String classDesc) {
        this.classDesc = classDesc;
    }

    public Map<String, String> getFieldMetaIndex() {
        return fieldMetaIndex;
    }

    public void setFieldMetaIndex(Map<String, String> fieldMetaIndex) {
        this.fieldMetaIndex = fieldMetaIndex;
    }

    public Map<String, String> getMethodMetaIndex() {
        return methodMetaIndex;
    }

    public void setMethodMetaIndex(Map<String, String> methodMetaIndex) {
        this.methodMetaIndex = methodMetaIndex;
    }

    public Map<String, String> getConstructorMetaIndex() {
        return constructorMetaIndex;
    }

    public void setConstructorMetaIndex(Map<String, String> constructorMetaIndex) {
        this.constructorMetaIndex = constructorMetaIndex;
    }

    public Boolean getNestedClass() {
        return nestedClass;
    }

    public void setNestedClass(Boolean nestedClass) {
        this.nestedClass = nestedClass;
    }

    public List<JavaClassMeta> getNestedClasses() {
        return nestedClasses;
    }

    public void setNestedClasses(List<JavaClassMeta> nestedClasses) {
        this.nestedClasses = nestedClasses;
    }

    public String getNestedClassFullClassName() {
        return nestedClassFullClassName;
    }

    public void setNestedClassFullClassName(String nestedClassFullClassName) {
        this.nestedClassFullClassName = nestedClassFullClassName;
    }

    public String getNestedClassName() {
        return nestedClassName;
    }

    public void setNestedClassName(String nestedClassName) {
        this.nestedClassName = nestedClassName;
    }

    public Boolean getJdkClass() {
        return jdkClass;
    }

    public void setJdkClass(Boolean jdkClass) {
        this.jdkClass = jdkClass;
    }

    public Boolean getTypeParameter() {
        return typeParameter;
    }

    public void setTypeParameter(Boolean typeParameter) {
        this.typeParameter = typeParameter;
    }

    public Boolean getWildcardType() {
        return wildcardType;
    }

    public void setWildcardType(Boolean wildcardType) {
        this.wildcardType = wildcardType;
    }

    public String getTypeArgExtendClassName() {
        return typeArgExtendClassName;
    }

    public void setTypeArgExtendClassName(String typeArgExtendClassName) {
        this.typeArgExtendClassName = typeArgExtendClassName;
    }

    public String getTypeArgExtendFullClassName() {
        return typeArgExtendFullClassName;
    }

    public void setTypeArgExtendFullClassName(String typeArgExtendFullClassName) {
        this.typeArgExtendFullClassName = typeArgExtendFullClassName;
    }

    public String parseDeclarationStructure(){
        StringBuilder sb = new StringBuilder();
        if(this.publicClass != null && this.publicClass){
            sb.append("public ");
        }else if(this.protectedClass != null && this.protectedClass){
            sb.append("protected ");
        }else if(this.privateClass != null && this.privateClass){
            sb.append("private ");
        }
        if(this.staticClass != null && this.staticClass){
            sb.append("static ");
        }
        if(this.finalClass != null && this.finalClass){
            sb.append("final ");
        }
        if(this.abstractClass != null && this.abstractClass){
            sb.append("abstract class ");
        }else if(this.interfaceClass != null && this.interfaceClass){
            sb.append("interface ");
        }else{
            sb.append("class ");
        }
        sb.append(this.className);
        if(this.typeParameters != null && this.typeParameters.size() > 0){
            sb.append("<");
            for (int i = 0; i < this.typeParameters.size(); i++) {
                String typeParameter = typeParameters.get(i);
                sb.append(typeParameter);
                if(this.typeParameters.size() != (i+1)){
                    sb.append(",");
                }
            }
            sb.append(">");
        }

        if(this.superClasses != null && this.superClasses.size() > 0){
            sb.append(" extends ");
            for (int i = 0; i < this.superClasses.size(); i++) {
                JavaClassMeta javaClassMeta = this.superClasses.get(i);
                sb.append(javaClassMeta.getFullClassName());
                if(this.superClasses.size() != (i+1)){
                    sb.append(",");
                }
            }
        }

        if(this.interfaces != null && this.interfaces.size() > 0){
            sb.append(" implements ");
            for (int i = 0; i < this.interfaces.size(); i++) {
                JavaClassMeta javaClassMeta = this.interfaces.get(i);
                sb.append(javaClassMeta.getFullClassName());
                if(this.interfaces.size() != (i+1)){
                    sb.append(",");
                }
            }

        }
        return sb.toString();
    }
}
