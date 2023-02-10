package com.ejdoc.metainfo.seralize.model;

import java.io.Serializable;
import java.net.URL;
import java.util.List;

public class JavaClassMeta  implements Serializable {

    private String projectName;

    private String moduleName;

    private String packageName;

    private String className;

    private String fullClassName;


    private String classNamePrefix;

    private String value;


    private URL url;

    private String relativePath;

    private JavaPackageMeta javaPackageMeta;

    private List<String> imports;


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

    private Boolean voidClass;
    private JavaClassMeta declaringClass;

    private JavaTypeMeta superClass;

    private JavaClassMeta superJavaClass;

    private List<String> modifiers;

    private List<JavaMethodMeta> methods;

    private List<JavaFieldMeta> fields;

    private List<JavaFieldMeta> enumConstants;

    private List<JavaClassMeta> interfaces;

    private List<JavaClassMeta> nestedClasses;

    private  List<JavaConstructorMeta> constructors;

//    private JavaSourceMeta javaSourceMeta;




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

    public JavaClassMeta getDeclaringClass() {
        return declaringClass;
    }

    public void setDeclaringClass(JavaClassMeta declaringClass) {
        this.declaringClass = declaringClass;
    }

    public JavaTypeMeta getSuperClass() {
        return superClass;
    }

    public void setSuperClass(JavaTypeMeta superClass) {
        this.superClass = superClass;
    }

    public JavaClassMeta getSuperJavaClass() {
        return superJavaClass;
    }

    public void setSuperJavaClass(JavaClassMeta superJavaClass) {
        this.superJavaClass = superJavaClass;
    }

    public List<JavaConstructorMeta> getConstructors() {
        return constructors;
    }

    public void setConstructors(List<JavaConstructorMeta> constructors) {
        this.constructors = constructors;
    }

    public List<JavaFieldMeta> getEnumConstants() {
        return enumConstants;
    }

    public void setEnumConstants(List<JavaFieldMeta> enumConstants) {
        this.enumConstants = enumConstants;
    }

    public List<JavaClassMeta> getNestedClasses() {
        return nestedClasses;
    }

    public void setNestedClasses(List<JavaClassMeta> nestedClasses) {
        this.nestedClasses = nestedClasses;
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

    public JavaPackageMeta getJavaPackageMeta() {
        return javaPackageMeta;
    }

    public void setJavaPackageMeta(JavaPackageMeta javaPackageMeta) {
        this.javaPackageMeta = javaPackageMeta;
    }

    public List<String> getImports() {
        return imports;
    }

    public void setImports(List<String> imports) {
        this.imports = imports;
    }

    public String getRelativePath() {
        return relativePath;
    }

    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }
}
