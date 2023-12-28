package com.ejdoc.metainfo.seralize.util;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.text.StrSplitter;
import cn.hutool.core.util.StrUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EjdocStrUtil {

    /**
     * 获取第一句注释
     * @param commentStr  注释内容
     * @return 第一句注释
     */
    public static String getFirstComment(String commentStr){
        return extractFirstSentenceByJsonp(commentStr);
    }

    private  static  String extractFirstSentenceByToken(String commentStr) {
        StringBuilder  result = new StringBuilder();
        StringTokenizer stringTokenizer = new StringTokenizer(commentStr,"\n。.");
        while (stringTokenizer.hasMoreTokens()) {
            //分割得到的字符串
            String firstStr = stringTokenizer.nextToken();
//            if(startHtml){
//                result.append("<");
//            }
            if(firstStr.endsWith(".")){
                result.append(firstStr);
            }else{
                result.append(firstStr).append(".");;
            }
            break;
        }
        return result.toString();
    }

    private static String extractFirstSentenceByRegex(String text) {
        // 使用正则表达式匹配句子
        String sentenceRegex = "([^.。!?]+[.。!?])";
        Pattern pattern = Pattern.compile(sentenceRegex);
        Matcher matcher = pattern.matcher(text);

        // 提取第一句话
        if (matcher.find()) {
            return matcher.group(1).trim();
        } else {
            return ""; // 如果没有匹配到句子，返回空字符串或者其他默认值
        }
    }


    private static String extractFirstSentenceByJsonp(String htmlText) {
        Document doc = Jsoup.parseBodyFragment(htmlText);
        Elements elements = doc.select("body");

        StringBuilder contentBuild = new StringBuilder();
        for (Element element : elements) {
            List<Node> nodes = element.childNodes();
            if(CollectionUtil.isNotEmpty(nodes)){
                for (Node node : nodes) {
                    if(node instanceof TextNode){
                        TextNode textNode = (TextNode)node;
                        String wholeText = textNode.getWholeText();
                        int wrap = StrUtil.indexOf(wholeText, '\n');
                        int point = StrUtil.indexOf(wholeText, '.');
                        int chinaPoint = StrUtil.indexOf(wholeText, '。');
                        if(wrap > -1){
                            contentBuild.append(StrUtil.subBefore(wholeText,'\n',false));
                            break;
                        }else if(point > -1){
                            contentBuild.append(StrUtil.subBefore(wholeText,'.',false));
                            break;
                        }else  if(chinaPoint > -1){
                            contentBuild.append(StrUtil.subBefore(wholeText,'。',false));
                            break;
                        }else{
                            contentBuild.append(wholeText);
                        }
                    }else if(node instanceof Element){
                        Element elementNode = (Element)node;
                        String text = elementNode.text();
                        int wrap = StrUtil.indexOf(text, '\n');
                        int point = StrUtil.indexOf(text, '.');
                        int chinaPoint = StrUtil.indexOf(text, '。');
                        contentBuild.append(elementNode.outerHtml());
                        if(point > -1 || chinaPoint > -1 || wrap > -1){
                            break;
                        }
                    }
                }
                if(contentBuild.length() > 0){
                    contentBuild.append(".");
                }
            }
        }

        return contentBuild.toString();  // 如果没有匹配到句子，返回空字符串或者其他默认值
    }
//    public static void main(String[] args) {
//        String content = "简单<a href=\"#hutool-core/cn/hutool/core/map/multi/Table.Cell\">Table.Cell</a>实现,我们可以提取重要信息.要实现这样的功能可以替换.";
//
////        String content ="<a href=\"#hutool-paraser/cn/hutool/cache/Hierarchical\">Hierarchical</a>选择器，用于根据一定的规则从两个<a href=\"#hutool-paraser/cn/hutool/cache/Hierarchical\">Hierarchical</a>实现类中选择并返回一个最合适的对象\n";
//        String firstComment = getFirstComment(content);
//        System.out.println(firstComment);
//    }
}
