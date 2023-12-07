package com.ejdoc.doc.generate.template.markdown;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ejdoc.doc.generate.model.DocOutFileInfo;
import com.ejdoc.doc.generate.out.config.DocGenerateConfig;
import com.ejdoc.metainfo.seralize.seralize.config.SeralizeConfig;
import org.beetl.core.GroupTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JavaDocMarkdownDocOutTemplate extends MarkdownDocOutTemplate{
    private static final Logger log = LoggerFactory.getLogger(JavaDocMarkdownDocOutTemplate.class);

    public JavaDocMarkdownDocOutTemplate() {
        super();
    }

    public JavaDocMarkdownDocOutTemplate(DocGenerateConfig docGenerateConfig) {
        super(docGenerateConfig);
    }

    public JavaDocMarkdownDocOutTemplate(GroupTemplate groupTemplate, DocGenerateConfig docGenerateConfig) {
        super(groupTemplate, docGenerateConfig);
    }

    private String ALL_SUPPER_CLASSES_KEY = "allSupperClasses";
    private String ALL_INTERFACE_CLASSES_KEY = "allInterfaceClasses";
//    private String ALL_SUB_CLASSES_KEY = "allSubClasses";
//    private String ALL_SUB_INTERFACE_CLASSES_KEY = "allSubInterfaceClasses";

    @Override
    protected Map loadCustomProp(DocOutFileInfo docOutFileInfo, JSONObject jsonObject) {
        Map<String,Object> resultProp = new HashMap<>();
        try {
            String basePath = docOutFileInfo.getJsonFile().getParent();
            SeralizeConfig seralizeConfig = docOutFileInfo.getSeralizeConfig();
            if(seralizeConfig.isUseAbsPath()){
                basePath  = docOutFileInfo.getJsonFileRootPath();
            }
            JSONArray allSuperClassJsonArray = jsonObject.getJSONArray(ALL_SUPPER_CLASSES_KEY);
            JSONArray allInterfaceClassJsonArray = jsonObject.getJSONArray(ALL_INTERFACE_CLASSES_KEY);
//            JSONArray allSubClassJsonArray = jsonObject.getJSONArray(ALL_SUB_CLASSES_KEY);
//            JSONArray allSubInterfaceClassJsonArray = jsonObject.getJSONArray(ALL_SUB_INTERFACE_CLASSES_KEY);
            addAllSuperClassDetailProp(resultProp, allSuperClassJsonArray,basePath);
            addAllInterfaceClassDetailProp(resultProp, allInterfaceClassJsonArray,basePath);
//            addAllSubClassDetailProp(resultProp, allSubClassJsonArray,basePath);
//            addAllSubInterfaceClassDetailProp(resultProp, allSubInterfaceClassJsonArray,basePath);
        } catch (Exception e) {
            log.error("JavaDocMarkdownDocOutTemplate loadCustomProp exception,docOutFileInfo:{}",docOutFileInfo.getFullFilePath(),e);
        }
        return resultProp;
    }

//    private void addAllSubInterfaceClassDetailProp(Map<String, Object> resultProp, JSONArray allSubInterfaceClassJsonArray, String basePath) {
//        List<JSONObject> jsonObjectList = baseAddClassProp(allSubInterfaceClassJsonArray,basePath);
//        if(jsonObjectList != null && jsonObjectList.size() > 0){
//            resultProp.put("allSubInterfaceClassDetailProp",jsonObjectList);
//        }
//    }

//    private void addAllSubClassDetailProp(Map<String, Object> resultProp, JSONArray allSubClassJsonArray, String basePath) {
//        List<JSONObject> jsonObjectList = baseAddClassProp(allSubClassJsonArray,basePath);
//        if(jsonObjectList != null && jsonObjectList.size() > 0){
//            resultProp.put("allSubClassDetailProp",jsonObjectList);
//        }
//    }

    private void addAllInterfaceClassDetailProp(Map<String, Object> resultProp, JSONArray allInterfaceClassJsonArray, String basePath) {
        List<JSONObject> jsonObjectList = baseAddClassProp(allInterfaceClassJsonArray,basePath);
        if(jsonObjectList != null && jsonObjectList.size() > 0){
            resultProp.put("allInterfaceClassDetailProp",jsonObjectList);
        }
    }

    private void addAllSuperClassDetailProp(Map<String, Object> resultProp, JSONArray allSuperClassJsonArray, String basePath) {
        List<JSONObject> jsonObjectList = baseAddClassProp(allSuperClassJsonArray, basePath);
        if(jsonObjectList != null && jsonObjectList.size() > 0){
            resultProp.put("allSupperClassDetailProp",jsonObjectList);
        }
    }

    private List<JSONObject> baseAddClassProp(JSONArray allClassJsonArray,String basePath) {
        if(allClassJsonArray != null && allClassJsonArray.size() > 0){
            List<JSONObject> jsonObjectList = new ArrayList<>();
            for (Object obj : allClassJsonArray) {
                JSONObject classJsonObj = (JSONObject)obj;
                Boolean jdkClass = classJsonObj.getBool("jdkClass",false);
                if(jdkClass){
                    JSONObject copyJsonObj = new JSONObject();
                    copyJsonObj.putOpt("jdkClass",true);
                    copyJsonObj.putOpt("className",classJsonObj.get("className"));
                    copyJsonObj.putOpt("fullClassName",classJsonObj.get("fullClassName"));
                    copyJsonObj.putOpt("classNamePrefix",classJsonObj.get("classNamePrefix"));
                    jsonObjectList.add(copyJsonObj);
                }else{
                    String dependencyRelativePath = classJsonObj.getStr("dependencyRelativePath");
                    if(StrUtil.isNotBlank(dependencyRelativePath)){
                        Path filePath = Paths.get(basePath,dependencyRelativePath+".json");
                        JSONObject dependencyRelativeObj = JSONUtil.readJSONObject(filePath.toFile(), CharsetUtil.CHARSET_UTF_8);
                        if(dependencyRelativeObj.containsKey("methods")){
                            JSONObject copyJsonObj = new JSONObject();
                            copyJsonObj.putOpt("jdkClass",false);
                            copyJsonObj.putOpt("methods",dependencyRelativeObj.get("methods"));
                            copyJsonObj.putOpt("className",dependencyRelativeObj.get("className"));
                            copyJsonObj.putOpt("classNamePrefix",dependencyRelativeObj.get("classNamePrefix"));
                            copyJsonObj.putOpt("fullClassName",dependencyRelativeObj.get("fullClassName"));
                            copyJsonObj.putOpt("dependencyRelativePath",dependencyRelativePath);
                            jsonObjectList.add(copyJsonObj);
                        }
                    }
                }

            }

            return jsonObjectList;
        }
        return null;
    }
}
