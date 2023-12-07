package com.ejdoc.doc.generate.util;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ClassLoaderUtil;
import sun.reflect.generics.reflectiveObjects.*;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class JdkClassUtil {

    /**
     * 获取公共方法
     * @param fullJdkClassName
     * @return
     */
    public static List<Method> getAllPublicMethod(String fullJdkClassName){
        List<Method> allMethods = new ArrayList<>();
        Class<?> aClass = ClassLoaderUtil.loadClass(fullJdkClassName);
        Method[] methods = aClass.getMethods();
        if(ArrayUtil.isNotEmpty(methods)){
            allMethods.addAll(Arrays.asList(methods));
        }
        return allMethods;
    }

    public static String getMethodFullName(Method method){
        StringBuilder result = new StringBuilder();
        result.append(method.getName());
        result.append("-");
        Parameter[] parameters = method.getParameters();
        if(ArrayUtil.isNotEmpty(parameters)){
            for (Parameter parameter : parameters) {
                Type parameterizedType = parameter.getParameterizedType();
                Class<? extends Type> typeClass = parameterizedType.getClass();
                if(typeClass.getPackage().getName().startsWith("sun.reflect.generics.reflectiveObjects")){
                    if(parameterizedType instanceof TypeVariableImpl){
                        TypeVariableImpl typeVariable = (TypeVariableImpl)parameterizedType;
                        result.append(typeVariable.getName());
                        result.append("-");
                    }else if(parameterizedType instanceof ParameterizedTypeImpl){
                        ParameterizedTypeImpl variable = (ParameterizedTypeImpl)parameterizedType;
                        result.append(variable.getRawType().getName());
                        result.append("-");
                    }else if(parameterizedType instanceof GenericArrayTypeImpl){
                        GenericArrayTypeImpl variable = (GenericArrayTypeImpl)parameterizedType;
                    }else if(parameterizedType instanceof LazyReflectiveObjectGenerator){
                        LazyReflectiveObjectGenerator variable = (LazyReflectiveObjectGenerator)parameterizedType;
                    }else if(parameterizedType instanceof NotImplementedException){
                        NotImplementedException variable = (NotImplementedException)parameterizedType;
                    }
                }else{
                    result.append(parameterizedType.getTypeName());
                    result.append("-");
                }
            }
        }else{
            result.append("-");
        }
        return result.toString();
    }


    public static void main(String[] args) {
        List<Method> javaObjectMethods = JdkClassUtil.getAllPublicMethod("java.lang.Object");
        for (Method method : javaObjectMethods) {
            System.out.println(getMethodFullName(method));
        }
        System.out.println("----------");
        System.out.println("----------");
        Class<?> aClass = ClassLoaderUtil.loadClass("java.util.Map");
        Class<?>[] declaredClasses = aClass.getDeclaredClasses();
        if(ArrayUtil.isNotEmpty(declaredClasses)){
            for (Class<?> declaredClass : declaredClasses) {
                System.out.println(declaredClass.getName());
            }
        }
        List<Method> allPublicMethod = JdkClassUtil.getAllPublicMethod("java.util.Map");
        for (Method method : allPublicMethod) {
            System.out.println(getMethodFullName(method));

        }
    }
}
