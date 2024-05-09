package com.ejdoc.doc.generate.util;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ClassLoaderUtil;
import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.reflect.generics.reflectiveObjects.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.*;

public class JdkClassUtil {

    private static final Logger log = LoggerFactory.getLogger(JdkClassUtil.class);

    /**
     * 获取公共方法
     * @param fullJdkClassName
     * @return
     */
    public static List<Method> getAllPublicMethod(String fullJdkClassName){
        List<Method> allMethods = new ArrayList<>();
        Class<?> aClass = null;
        try {
            aClass = ClassLoaderUtil.loadClass(fullJdkClassName);
        } catch (Exception e) {
            log.debug(" JdkClassUtil  getAllPublicMethod loadClass fullJdkClassName:{} error",fullJdkClassName);
        }
        if(aClass != null){
            Method[] methods = aClass.getMethods();
            if(ArrayUtil.isNotEmpty(methods)){
                allMethods.addAll(Arrays.asList(methods));
            }
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

    public static List<String> getClassNamesByPackage(String packageName) {
        List<String> classNameList = new ArrayList<>();
        try {
            String packagePath = packageName.replace(".","/");
            Enumeration<URL> resources = ClassLoader.getSystemClassLoader().getResources("/"+packagePath);
            List<File> classFile = new ArrayList<>();
            while (resources.hasMoreElements()){
                URL url = resources.nextElement();
                classFile.add(new File(url.getFile()));
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return classNameList;
    }


    public static void main(String[] args) {
//        List<Method> javaObjectMethods = JdkClassUtil.getAllPublicMethod("java.lang.Object");
//        for (Method method : javaObjectMethods) {
//            System.out.println(getMethodFullName(method));
//        }
//        System.out.println("----------");
//        System.out.println("----------");
//        Class<?> aClass = ClassLoaderUtil.loadClass("java.util.Map");
//        Class<?>[] declaredClasses = aClass.getDeclaredClasses();
//        if(ArrayUtil.isNotEmpty(declaredClasses)){
//            for (Class<?> declaredClass : declaredClasses) {
//                System.out.println(declaredClass.getName());
//            }
//        }
//        List<Method> allPublicMethod = JdkClassUtil.getAllPublicMethod("java.util.Map");
//        for (Method method : allPublicMethod) {
//            System.out.println(getMethodFullName(method));
//
//        }
    }

    /**
     * 是否是jdk内部类
     * @param fullClassName
     * @return
     */
    public static boolean isJdkClass(String fullClassName){
        if(StrUtil.isBlank(fullClassName)){
            return false;
        }
        fullClassName = fullClassName.trim();

        return fullClassName.startsWith("java.") || fullClassName.startsWith("javax.");
    }
}
