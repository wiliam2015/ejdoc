package com.ejdoc.doc.generate.enums;

/**
 * mock内容类型
 */
public enum MockContentTypeEnum {
    NONE("none","none","无"),
    DATE("date","date","随机日期"),
    DATETIME("datetime","datetime","随机时间"),
    TIMESTAMP("timestamp","timestamp","随机时间戳"),
    TIMEZONE("timezone","timezone","随机时区"),
    PASSWORD("password","password","随机密码长度8位"),
    ADDRESS("address","address","生成1个随机中国大陆详细地址"),
    PROVINCE("province","province","随机获取省份"),
    CITY("city","city","随机获取城市(省份+城市，以逗号为分隔符)"),
    ZIPCODE("zipCode","zipCode","随机获取邮编"),

    PLATE_NUMBER("plateNumber","PlateNumber","随机中国大陆车牌号"),
    NICK_NAME("nickName","nickName","生成1个随机英文网络昵称，最大长度为8个字符"),
    CHINESE_NICK_NAME("chineseNickName","chineseNickName","生成1个随机汉字网络昵称，最大长度为8个汉字"),
    CHINESE_NAME("chineseName","chineseName","生成1个中文名字"),
    ENGLISH_NAME("englishName","englishName","生成1个中文名字"),
    MOBILE_MODEL("mobileModel","mobileModel","随机手机型号"),
    NUMBER("number","number","随机数字，支持随机位数"),
    CHINESE("chinese","chinese","随机汉字，支持随机位数"),
    CHINESE_IDIOM("chineseIdiom","chineseIdiom","随机成语，支持随机位数"),
    ORDER_ID("orderId","orderId","随机订单号11位数字"),
    CHINESE_MOBILE("chineseMobile","chineseMobile","中国大陆手机号"),
    EMAIL("email","email","随机生成邮箱"),
    DOMAIN("domain","domain","随机生成域名"),
    IPV4("ipv4","ipv4","随机生成IPv4地址"),
    IPV6("ipv6","ipv6","随机生成IPv6地址"),
    URL("url","url","随机生成url"),
    app_name("appName","appName","随机生成app名称"),
    COMPANY_NAME("companyName","companyName","随机公司名称"),
    COMPANY_DEPARTMENT("companyDepartment","companyDepartment","随机公司部门名称"),
    CHINESE_SENTENCE("chineseSentence","chineseSentence","随机中文短句"),
    ENGLISH_TEXT("englishText","englishText","随机英文文本"),
    ECONOMIC_CATEGORY("economicCategory","economicCategory","随机行业分类"),
    SOCIAL_CREDIT_CODE("socialCreditCode","socialCreditCode","随机社会信用代码"),
    ;

    private String code;
    private String name;
    private String desc;

    MockContentTypeEnum(String code,String name,String desc){
        this.code = code;
        this.name = name;
        this.desc = desc;
    }


    public static MockContentTypeEnum convertToEnumByCode(String code){
        MockContentTypeEnum[] values = MockContentTypeEnum.values();
        for (MockContentTypeEnum value : values) {
            if(value.getCode().equals(code)){
                return value;
            }
        }
        return NONE;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
