package com.ejdoc.doc.generate.comment.md.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.ejdoc.doc.generate.comment.CommentSerialize;
import com.ejdoc.doc.generate.comment.dto.CommentLinkDto;
import com.ejdoc.doc.generate.comment.dto.CommentSerializeRootDocDto;
import com.ejdoc.doc.generate.util.beetl.function.MemberRenderUtil;
import com.ejdoc.metainfo.seralize.enums.JavaDocCommentTypeEnum;
import com.ejdoc.metainfo.seralize.index.MetaIndexContext;
import com.ejdoc.metainfo.seralize.index.TreeIndexClassMeta;
import com.ejdoc.metainfo.seralize.model.JavaClassMeta;
import com.ejdoc.metainfo.seralize.model.JavaFieldMeta;
import com.ejdoc.metainfo.seralize.model.JavaMethodMeta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @link 是 Javadoc 中用于创建超链接的标签之一。它可以用于将文档中的某个位置链接到其他类、方法、字段等的位置。
 *
 * @link 的语法如下：
 *
 * {@link package.class#member label}
 *
 * 其中，package.class#member 是要链接的类、方法或字段的完全限定名称，label 是链接的文本标签。
 *
 * @link 可以链接到以下内容：
 *
 * 1.类：可以链接到一个类，例如：{@link java.util.ArrayList}
 * 2.方法：可以链接到一个方法，例如：{@link java.util.List#add(Object)}
 * 3.字段：可以链接到一个字段，例如：{@link java.util.List#size}
 * 4.包：可以链接到一个包，例如：{@link java.util}
 * 5.超类：可以链接到一个类的超类，例如：{@link java.util.ArrayList#add(Object) super.add()}
 * 6.实现的接口：可以链接到一个类实现的接口，例如：{@link java.util.List#add(Object) List.add()}
 * 7.异常：可以链接到一个异常，例如：{@link java.io.IOException}
 * @link 还可以用于链接到当前类或当前方法中的成员。例如，要链接到当前类中的一个字段，可以使用以下语法：
 *
 * {@link #fieldName}
 *
 * 这将创建一个超链接，链接到当前类中名为 fieldName 的字段。
 *
 * 在使用 @link 时，可以提供一个 label 参数，用于指定链接的文本标签。例如：
 *
 * {@link java.util.List#add(Object) List.add()}
 *
 * 这将创建一个超链接，链接到 java.util.List 接口的 add 方法，并使用 "List.add()" 作为链接的文本标签。
 *
 * 总之，@link 可以方便地创建 Javadoc 中的超链接，使得文档更加易读和易用。
 *
 *
 *
 *
 * @link标签是Java文档注释中的一个标记，用于在文档中引用其他类、方法、字段等的链接。它通常用于创建超链接，使读者能够方便地跳转到被引用的元素的文档。
 *
 * 使用@link标签的语法是{@link package.class#member label}，其中package是包名，class是类名，member是类的成员（方法、字段等），label是可选的链接标签。
 *
 * @link标签的作用有以下几个方面：
 *
 * 创建超链接：通过在文档中使用@link标签，可以将被引用的元素链接到其文档页面，使读者能够方便地查看相关信息。
 *
 * 提供类型安全的引用：@link标签可以在编译时检查被引用的元素是否存在，并在文档生成过程中生成正确的链接。
 *
 * 支持跨类、跨包引用：@link标签可以引用其他类、方法、字段等，无论它们是否在同一个包中。
 *
 * 提供可读性：通过使用@link标签，可以在文档中直接显示被引用元素的名称，而不是只显示文本。
 *
 * 总的来说，@link标签在Java文档注释中起到了方便读者查看相关信息、提供类型安全引用和增强文档可读性的作用。它是Java文档注释中常用的一个标签，对于编写清晰、易读的文档非常有帮助。
 */
public class CommentSerializeLinkImpl implements CommentSerialize {
    private static final Logger log = LoggerFactory.getLogger(CommentSerializeLinkImpl.class);
    private MemberRenderUtil memberRenderUtil = new MemberRenderUtil();
    @Override
    public String acceptType() {
        return JavaDocCommentTypeEnum.LINK.getName();
    }
    @Override
    public boolean accept(String type) {
        return JavaDocCommentTypeEnum.LINK.getName().equals(type);
    }

    /**
     * {@link java.util.List#add(Object) List.add()}
     * @param content
     * @param serializeRootDocDto
     * @return
     */
    @Override
    public String toSerialize(String content, CommentSerializeRootDocDto serializeRootDocDto) {
        if(StrUtil.isBlank(content)){
            return "";
        }
        content=StrUtil.trim(content);
        CommentLinkDto commentLinkDto = parseCommentLinkDto(content);
        processCommentLinkDto(commentLinkDto,serializeRootDocDto);
        content = parseLinkContent(commentLinkDto,content);
        return content;
    }

    /**
     * 加工link标签信息
     * @param commentLinkDto link标签类
     * @param serializeRootDocDto 文档根信息
     */
    private void processCommentLinkDto(CommentLinkDto commentLinkDto, CommentSerializeRootDocDto serializeRootDocDto) {

        List<String> imports = new ArrayList<>();

        JavaClassMeta classMeta = MetaIndexContext.getClassMetaByFullName(serializeRootDocDto.getFullClassName());
        if(classMeta != null){
            commentLinkDto.setLinkJavaClassMeta(classMeta);
            List<JavaClassMeta> classMetaByPackage = MetaIndexContext.getClassMetaByPackage(classMeta.getPackageName());
            if(CollectionUtil.isNotEmpty(classMetaByPackage)){
                for (JavaClassMeta javaClassMeta : classMetaByPackage) {
                    imports.add(javaClassMeta.getFullClassName());
                }
            }
            List<String> importsClass = classMeta.getImports();
            //导入子包引入
            for (String importInfo : importsClass) {
                if(!importInfo.startsWith("java")){
                    if(importInfo.contains("*")){
                        String packageNameImport = importInfo.substring(0,importInfo.indexOf("*")-1);
                        List<JavaClassMeta> packageClassList = MetaIndexContext.getClassMetaByPackage(packageNameImport);
                        if(CollectionUtil.isNotEmpty(packageClassList)){
                            for (JavaClassMeta javaClassMeta : packageClassList) {
                                imports.add(javaClassMeta.getFullClassName());
                            }
                        }
                    }else{
                        imports.add(importInfo);
                    }
                }
            }

            imports.addAll(importsClass);
        }

        commentLinkDto.setImports(imports);

        if(commentLinkDto.isClassLink()){
            if(commentLinkDto.isFullClassPathLink() && !commentLinkDto.getFullName().startsWith("java")){
                JavaClassMeta refClassMeta = MetaIndexContext.getClassMetaByFullName(commentLinkDto.getFullName());
                commentLinkDto.setLinkRefJavaClassMeta(refClassMeta);
            }else if(classMeta != null && !commentLinkDto.getFullName().startsWith("java")){
                List<JavaClassMeta> currentPackage = MetaIndexContext.getClassMetaByPackage(classMeta.getPackageName());
                if(CollectionUtil.isNotEmpty(currentPackage)){
                    for (JavaClassMeta javaClassMeta : currentPackage) {
                        if(commentLinkDto.getFullName().equals(javaClassMeta.getValue())){
                            commentLinkDto.setLinkRefJavaClassMeta(javaClassMeta);
                        }
                    }
                }
            }
        }
    }

    private String parseLinkContent(CommentLinkDto commentLinkDto, String content) {

        String uniqueName ="";
        if(commentLinkDto.isOnlyClassLink()){
            if(commentLinkDto.getLinkRefJavaClassMeta() != null){
                uniqueName = getLinkContentPreName(commentLinkDto.getLinkRefJavaClassMeta());
                setLableName(commentLinkDto,commentLinkDto.getLinkRefJavaClassMeta());
            }
        }else if(commentLinkDto.isClassLink()){
            if(commentLinkDto.getLinkRefJavaClassMeta() != null){
                String preName = getLinkContentPreName(commentLinkDto.getLinkRefJavaClassMeta());
                String idLink = "?id=";
                if(commentLinkDto.isMethodLink()){
                    idLink +=commentLinkDto.getMethodName();
                    if(CollectionUtil.isNotEmpty(commentLinkDto.getParams())){
                        idLink+= memberRenderUtil.catUniqueMethodParamName(commentLinkDto.getParams());
                    }
                }else if(commentLinkDto.isFieldLink()){
                    idLink+=commentLinkDto.getFieldName();
                }
                uniqueName = preName+idLink.toLowerCase();
                setLableName(commentLinkDto,commentLinkDto.getLinkRefJavaClassMeta());
            }
        }else if(commentLinkDto.isMethodLink()){
            JavaClassMeta linkJavaClassMeta = commentLinkDto.getLinkJavaClassMeta();
            if(linkJavaClassMeta != null){
                String preName = getLinkContentPreName(linkJavaClassMeta);
                String idLink = "?id=";
                String methodUniqueId = commentLinkDto.getMethodName();
                if(CollectionUtil.isNotEmpty(commentLinkDto.getParams())){
                    methodUniqueId += memberRenderUtil.catUniqueMethodParamName(commentLinkDto.getParams());
                }
//                setLableName(commentLinkDto,linkJavaClassMeta);
                Map<String, String> methodMetaIndex = linkJavaClassMeta.getMethodMetaIndex();
                if(methodMetaIndex != null && methodMetaIndex.containsKey(methodUniqueId.toLowerCase())){
                }else{
                    List<JavaClassMeta> allSupperClassAndInterface = getAllSupperClassInterface(linkJavaClassMeta);
                    if(CollectionUtil.isNotEmpty(allSupperClassAndInterface)){
                        for (JavaClassMeta supperClass : allSupperClassAndInterface) {
                            JavaClassMeta supperClassFullInfo = MetaIndexContext.getClassMetaByFullName(supperClass.getFullClassName());
                            if(supperClassFullInfo != null){
                                Map<String, String> supperMethodMetaIndex = supperClassFullInfo.getMethodMetaIndex();
                                if(supperMethodMetaIndex != null && supperMethodMetaIndex.containsKey(methodUniqueId.toLowerCase())){
                                    preName = StrUtil.join("/", supperClassFullInfo.getModuleName(), supperClassFullInfo.getFullClassName().replace(".","/"));
//                                    setLableName(commentLinkDto,supperClassFullInfo);
                                    if(!commentLinkDto.isUseCustomLableName()){
                                        commentLinkDto.setLabelName(supperClassFullInfo.getClassName()+"."+commentLinkDto.getLabelName());
                                    }

                                }
                            }
                        }
                    }
                }
                idLink+=methodUniqueId;
                uniqueName = preName+idLink.toLowerCase();
            }
        }else if(commentLinkDto.isFieldLink()){
            JavaClassMeta linkJavaClassMeta = commentLinkDto.getLinkJavaClassMeta();
            if(linkJavaClassMeta != null){
                String preName = getLinkContentPreName(linkJavaClassMeta);
                String idLink = "?id=";
                String fieldUniqueId = commentLinkDto.getFieldName();
                Map<String, String> fieldMetaIndex = linkJavaClassMeta.getFieldMetaIndex();
//                setLableName(commentLinkDto,linkJavaClassMeta);
                if(fieldMetaIndex != null && fieldMetaIndex.containsKey(fieldUniqueId)){
                }else{
                    List<JavaClassMeta> allSupperClassAndInterface = getAllSupperClassInterface(linkJavaClassMeta);
                    if(CollectionUtil.isNotEmpty(allSupperClassAndInterface)){
                        for (JavaClassMeta supperClass : allSupperClassAndInterface) {
                            JavaClassMeta supperClassFullInfo = MetaIndexContext.getClassMetaByFullName(supperClass.getFullClassName());
                            if(supperClassFullInfo != null){
                                Map<String, String> supperMethodMetaIndex = supperClassFullInfo.getFieldMetaIndex();
                                if(supperMethodMetaIndex != null && supperMethodMetaIndex.containsKey(fieldUniqueId.toLowerCase())){
//                                    preName = StrUtil.join("/", supperClassFullInfo.getModuleName(), supperClassFullInfo.getFullClassName().replace(".","/"));
                                    preName = getLinkContentPreName(supperClassFullInfo);
//                                    setLableName(commentLinkDto,supperClassFullInfo);
                                    if(!commentLinkDto.isUseCustomLableName()){
                                        commentLinkDto.setLabelName(supperClassFullInfo.getClassName()+"."+commentLinkDto.getLabelName());
                                    }
                                }
                                break;
                            }
                        }
                    }
                }
                idLink+=fieldUniqueId;
                uniqueName = preName+idLink.toLowerCase();
            }
        }
        if(StrUtil.isNotBlank(uniqueName)){
            content = memberRenderUtil.createALinkHrefIdHtml(commentLinkDto.getLabelName(),"",uniqueName,null);
        }
        return content;
    }

    /**
     * 设置显示名称
     * @param commentLinkDto
     * @param linkRefJavaClassMeta
     */
    private void setLableName(CommentLinkDto commentLinkDto, JavaClassMeta linkRefJavaClassMeta) {
        if(!commentLinkDto.isUseCustomLableName()){
            String fullName = "";
            if(linkRefJavaClassMeta.getNestedClass() != null && linkRefJavaClassMeta.getNestedClass()){
                fullName = linkRefJavaClassMeta.getNestedClassName();
                commentLinkDto.setLabelName(fullName+"."+commentLinkDto.getLabelName());
            }
        }
    }

    /**
     * 获取link标签的前置内容
     * @param linkJavaClassMeta java类
     * @return
     */
    private  String getLinkContentPreName(JavaClassMeta linkJavaClassMeta) {
        String preName = StrUtil.join("/", linkJavaClassMeta.getModuleName(), linkJavaClassMeta.getFullClassName().replace(".","/"));
        if(linkJavaClassMeta.getNestedClass() != null && linkJavaClassMeta.getNestedClass()){
            preName = StrUtil.join("/", linkJavaClassMeta.getModuleName(), linkJavaClassMeta.getPackageName().replace(".","/"), linkJavaClassMeta.getClassName());
        }
        return preName;
    }

    /**
     * 获取所有的父类和接口类
     * @param linkJavaClassMeta
     * @return
     */
    private  List<JavaClassMeta> getAllSupperClassInterface(JavaClassMeta linkJavaClassMeta) {
        TreeIndexClassMeta treeIndexClass = MetaIndexContext.getTreeIndexClassMetaByFullName(linkJavaClassMeta.getFullClassName());
        List<JavaClassMeta> allSupperClassAndInterface = new ArrayList<>();
        if(treeIndexClass != null && CollectionUtil.isNotEmpty(treeIndexClass.getAllSupperClasses())){
            allSupperClassAndInterface.addAll(treeIndexClass.getAllSupperClasses());
        }
        if(treeIndexClass != null && CollectionUtil.isNotEmpty(treeIndexClass.getAllInterfaceClasses())){
            allSupperClassAndInterface.addAll(treeIndexClass.getAllInterfaceClasses());
        }
        return allSupperClassAndInterface;
    }

    /**
     * 解析link标签语法树
     * @param content
     * @return
     */
    private CommentLinkDto parseCommentLinkDto(String content) {
        CommentLinkDto commentLinkDto = new CommentLinkDto();
        String fullName="";
        String packageName="";
        String memberName="";
        String labelName=content;
        String methodName="";
        String fieldName="";
        List<String> params = new ArrayList<>();
        if(content.startsWith("#")){
            memberName = content.substring(1);
            labelName = memberName;
        }else if(content.contains("#")){
            String[] split = content.split("#");
            fullName =split[0];
            memberName =split[1];
            commentLinkDto.setClassLink(true);
            if(fullName.contains(".")){
                commentLinkDto.setFullClassPathLink(true);
            }
            labelName = fullName+"."+memberName;
        }else{
            fullName = content;
            commentLinkDto.setClassLink(true);
            commentLinkDto.setOnlyClassLink(true);
            if(fullName.contains(".")){
                commentLinkDto.setFullClassPathLink(true);
            }
        }

        if(StrUtil.isNotBlank(memberName)){
            if(memberName.contains("(")){
                commentLinkDto.setMethodLink(true);
                int start = memberName.indexOf("(");
                int end = memberName.indexOf(")");
                String paramStr = null;
                try {
                    paramStr = memberName.substring(start+1, end).trim();
                } catch (StringIndexOutOfBoundsException e) {
                    log.error("CommentSerializeLinkImpl index out of bounds content:{}",content,e);
                }
                if(StrUtil.isNotBlank(paramStr)){
                    String[] paramArr = paramStr.split(",");
                    if(paramArr != null && paramArr.length > 0){
                        for (String param : paramArr) {
                            params.add(param.trim());
                        }
                    }
                }
                methodName = memberName.substring(0,memberName.indexOf("("));
                if(end+1 < memberName.length()){
                    labelName = memberName.substring(end+1).trim();
                    commentLinkDto.setUseCustomLableName(true);
                }

            }else{
                String[] split = memberName.split("\\s+");
                fieldName = split[0];
                if(split.length > 1){
                    labelName = split[1];
                    commentLinkDto.setUseCustomLableName(true);
                }
                commentLinkDto.setFieldLink(true);
            }
        }

        commentLinkDto.setFullName(fullName);
        commentLinkDto.setPackageName(packageName);
        commentLinkDto.setMemberName(memberName);
        commentLinkDto.setLabelName(labelName);
        commentLinkDto.setMethodName(methodName);
        commentLinkDto.setParams(params);
        commentLinkDto.setFieldName(fieldName);
        return commentLinkDto;
    }


}
