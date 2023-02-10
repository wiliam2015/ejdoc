package com.ejdoc.doc.generate.env;

import java.util.Map;

public interface DocOutEnvironment {

    String getDocOutRootPath();

    String getProp(String propKey);

    Map<String,String> getAllProp();
}
