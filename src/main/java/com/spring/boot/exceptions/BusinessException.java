package com.spring.boot.exceptions;

public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -6003868869041167435L;

    private int errorCode;
    private String errorMsg;
    private Throwable t;

    public BusinessException(int errorCode) {
        this.errorCode = errorCode;
    }

    public BusinessException(int errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public BusinessException(int errorCode, Throwable t) {
        this.errorCode = errorCode;
        this.t = t;
    }

    public BusinessException(int errorCode, String errorMsg, Throwable t) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.t = t;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Throwable getT() {
        return t;
    }

    public void setT(Throwable t) {
        this.t = t;
    }

}
