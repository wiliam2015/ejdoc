package com.ejdoc.metainfo.seralize.model;

import java.util.List;

public class JavaExecutableMeta {

    private JavaClassMeta declaringClass;

    private List<JavaClassMeta> exceptions;

    private List<JavaTypeMeta> exceptionTypes;


    List<JavaParameterMeta> parameters;

    List<JavaTypeMeta> parameterTypes;

    private String sourceCode;

    private Boolean varArgs;

    private String callSignature;

    public JavaClassMeta getDeclaringClass() {
        return declaringClass;
    }

    public void setDeclaringClass(JavaClassMeta declaringClass) {
        this.declaringClass = declaringClass;
    }

    public List<JavaClassMeta> getExceptions() {
        return exceptions;
    }

    public void setExceptions(List<JavaClassMeta> exceptions) {
        this.exceptions = exceptions;
    }

    public List<JavaTypeMeta> getExceptionTypes() {
        return exceptionTypes;
    }

    public void setExceptionTypes(List<JavaTypeMeta> exceptionTypes) {
        this.exceptionTypes = exceptionTypes;
    }

    public List<JavaParameterMeta> getParameters() {
        return parameters;
    }

    public void setParameters(List<JavaParameterMeta> parameters) {
        this.parameters = parameters;
    }

    public List<JavaTypeMeta> getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(List<JavaTypeMeta> parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public Boolean getVarArgs() {
        return varArgs;
    }

    public void setVarArgs(Boolean varArgs) {
        this.varArgs = varArgs;
    }

    public String getCallSignature() {
        return callSignature;
    }

    public void setCallSignature(String callSignature) {
        this.callSignature = callSignature;
    }
}
