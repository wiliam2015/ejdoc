package com.ejdoc.doc.generate.exception;

public class JavaDocSerializeException extends RuntimeException{
    public static final String LOAD_FILE_ERROR = "LOAD_FILE_ERROR";
    private String errCode;

    private String errMsg;

    public JavaDocSerializeException(String errCode, String errMsg) {
        super(errMsg);
        this.errCode = errCode;
    }

    public JavaDocSerializeException(String errCode, Throwable cause) {
        super(cause);
        this.errCode = errCode;
    }

    public JavaDocSerializeException(String errCode, String errMsg, Throwable cause) {
        super(errMsg, cause);
        this.errCode = errCode;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
