package com.ejdoc.doc.generate.out.apidoc.mockdata;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.apifan.common.random.RandomSource;
import com.ejdoc.doc.generate.enums.MockContentTypeEnum;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 自动生成真实的mock数据
 */
public class AutoGenerateRealMockData {

    private String mockContent;

    private Set<String> matchExpressions;



    public AutoGenerateRealMockData( String mockContent){
        this.mockContent = mockContent;
       if(StrUtil.isNotBlank(mockContent)){
           mockContent = StrUtil.trim(mockContent);
           Pattern pattern =Pattern.compile("\\([a-zA-Z0-9\\s]+\\)");
           Matcher matcher = pattern.matcher(mockContent);
           matchExpressions = new HashSet<>();

           while (matcher.find()){
               matchExpressions.add(matcher.group());
           }

       }
    }

    public Object mockRealData(){
        String mockContentResult =this.mockContent;
        if(CollectionUtil.isNotEmpty(matchExpressions)){
            Map<String,Object> matchExpressionMap = new HashMap<>();
            for (String matchExpression : matchExpressions) {
                Object mockContentInner = "";
                String matchExpressionKey = matchExpression;
                matchExpression = matchExpression.replace("(","").replace(")","");
                if(StrUtil.isNotBlank(matchExpression)){
                    String mockContentType = matchExpression;
                    String mockContentParam ="";
                    int firstBlankPos = matchExpression.indexOf(" ");
                    if(firstBlankPos > 0){
                        mockContentType = matchExpression.substring(0,firstBlankPos);
                        mockContentParam = StrUtil.trim(matchExpression.substring(firstBlankPos));
                    }

                    if(StrUtil.isNotBlank(mockContentType)){
                        MockContentTypeEnum mockContentTypeEnum = MockContentTypeEnum.convertToEnumByCode(mockContentType);
                        if(mockContentTypeEnum == MockContentTypeEnum.NONE){
                            mockContentInner = "";
                        }else{
                            mockContentInner = doMockReadData(mockContentTypeEnum,mockContentParam);
                        }

                        matchExpressionMap.put(matchExpressionKey,mockContentInner);
                    }
                }
            }
            if(CollectionUtil.isNotEmpty(matchExpressionMap)){
                for (Map.Entry<String, Object> stringStringEntry : matchExpressionMap.entrySet()) {
                    String key = stringStringEntry.getKey();
                    Object value = stringStringEntry.getValue();
                    mockContentResult =mockContentResult.trim().replace(key,value.toString());
                }
            }
        }


        return mockContentResult;
    }

    private Object doMockReadData( MockContentTypeEnum mockContentTypeEnum,String mockContentParam) {
        Object mockContent ="";
        switch (mockContentTypeEnum){
            case DATE:
                mockContent = RandomSource.dateTimeSource().randomDate(DateUtil.thisYear(), "yyyy-MM-dd");
                break;
            case DATETIME:
                mockContent = RandomSource.dateTimeSource().randomPastDate(LocalDate.now(), "yyyy-MM-dd HH:mm:ss");
                break;
            case TIMESTAMP:
                mockContent = String.valueOf(RandomSource.dateTimeSource().randomTimestamp(LocalDate.now()));
                break;
            case PASSWORD:
                //生成1个随机强密码，长度为16，无特殊字符
                int length = NumberUtil.isInteger(mockContentParam) ? Integer.parseInt(mockContentParam):16;
                mockContent = RandomSource.personInfoSource().randomStrongPassword(length, false);
                break;
            case TIMEZONE:
                //生成1个随机时区名称
                mockContent = RandomSource.dateTimeSource().randomTimezoneName();
                break;
            case CITY:
                //随机获取城市(省份+城市，以逗号为分隔符)
                mockContent = RandomSource.areaSource().randomCity(",");
                break;
            case ADDRESS:
                //生成1个随机中国大陆详细地址
                mockContent = RandomSource.areaSource().randomAddress();
                break;
            case ZIPCODE:
                //随机获取邮编
                mockContent = RandomSource.areaSource().randomZipCode();
                break;
            case PROVINCE:
                //随机获取省份
                mockContent =RandomSource.areaSource().randomProvince();
                break;
            case URL:
                //生成1个随机静态url，自定义后缀
                String suffix =StrUtil.isNotBlank(mockContentParam) ? mockContentParam.trim() : "";
                mockContent = RandomSource.internetSource().randomStaticUrl(suffix);
                break;
            case IPV4:
                //生成1个随机公网IPv4地址
                mockContent =  RandomSource.internetSource().randomPublicIpv4();
                break;
            case IPV6:
                //生成1个随机公网IPv6地址
                mockContent =  RandomSource.internetSource().randomIpV6();
                break;
            case EMAIL:
                //生成1个随机邮箱地址，后缀随机，邮箱用户名最大长度为10
                int emailLength = NumberUtil.isInteger(mockContentParam) ? Integer.parseInt(mockContentParam):10;
                mockContent =  RandomSource.internetSource().randomEmail(emailLength);
                break;
            case DOMAIN:
                //生成1个随机域名，域名最大长度为16
                int domainLength = NumberUtil.isInteger(mockContentParam) ? Integer.parseInt(mockContentParam):16;
                mockContent = RandomSource.internetSource().randomDomain(domainLength);
                break;
            case NUMBER:
                Long min = 100L;
                Long max = 100000L;
                if(StrUtil.isNotBlank(mockContentParam)){
                    String[] params = mockContentParam.split(" ");
                    if(params.length ==2 ){
                        if(NumberUtil.isLong(params[0])){
                            min = Long.parseLong(params[0]);
                        }
                        if(NumberUtil.isLong(params[1])){
                            max = Long.parseLong(params[1]);
                        }
                    }
                }
                mockContent = String.valueOf(RandomSource.numberSource().randomLong(min, max));
                break;
            case CHINESE:
                //生成随机汉字
                int worldLength = NumberUtil.isInteger(mockContentParam) ? Integer.parseInt(mockContentParam):10;
                mockContent = RandomSource.languageSource().randomChinese(worldLength);
                break;
            case app_name:
                //生成1个随机App名称
                mockContent =  RandomSource.internetSource().randomAppName();
                break;
            case ORDER_ID:
                String innerMockContentParam ="10000000000 60000000000";
                mockContent =   doMockReadData(MockContentTypeEnum.NUMBER,innerMockContentParam);
                break;
            case NICK_NAME:
                //生成1个随机英文网络昵称
                int nickLength = NumberUtil.isInteger(mockContentParam) ? Integer.parseInt(mockContentParam):8;
                mockContent =   RandomSource.personInfoSource().randomNickName(nickLength);
                break;
            case CHINESE_NAME:
                mockContent =   RandomSource.personInfoSource().randomChineseName();
                break;
            case COMPANY_NAME:
                //随机生成1个公司名称，默认地区前缀为北京
                String prefix =StrUtil.isNotBlank(mockContentParam) ? mockContentParam.trim() : "北京";
                mockContent =  RandomSource.otherSource().randomCompanyName(prefix);
                break;
            case ENGLISH_NAME:
                //生成1个随机英文人名
                mockContent =  RandomSource.personInfoSource().randomEnglishName();
                break;
            case ENGLISH_TEXT:
                //随机生成1条英文文本，默认包含10个单词
                int englistTextLength = NumberUtil.isInteger(mockContentParam) ? Integer.parseInt(mockContentParam):10;
                mockContent =  RandomSource.languageSource().randomEnglishText(englistTextLength);
                break;
            case MOBILE_MODEL:
                mockContent =  RandomSource.otherSource().randomMobileModel();
                break;
            case PLATE_NUMBER:
                //生成1个随机中国大陆车牌号(新能源车型)
                mockContent =  RandomSource.otherSource().randomPlateNumber();
                break;
            case CHINESE_IDIOM:
                mockContent =  RandomSource.languageSource().randomChineseIdiom();
                break;
            case CHINESE_MOBILE:
                //生成1个随机中国大陆手机号
                mockContent =  RandomSource.personInfoSource().randomChineseMobile();
                break;
            case CHINESE_SENTENCE:
                //随机生成1条中文短句
                mockContent =  RandomSource.languageSource().randomChineseSentence();
                break;
            case CHINESE_NICK_NAME:
                //生成1个随机中文网络昵称
                int chineseNickLength = NumberUtil.isInteger(mockContentParam) ? Integer.parseInt(mockContentParam):8;
                mockContent =   RandomSource.personInfoSource().randomChineseNickName(chineseNickLength);
                break;
            case ECONOMIC_CATEGORY:
                mockContent =  RandomSource.otherSource().randomEconomicCategory().getName();
                break;
            case COMPANY_DEPARTMENT:
                //随机生成1个公司部门名称
                mockContent =  RandomSource.otherSource().randomCompanyDepartment();
                break;
            case SOCIAL_CREDIT_CODE:
                mockContent =  RandomSource.otherSource().randomSocialCreditCode();
                break;
        }
        return mockContent;
    }
}
