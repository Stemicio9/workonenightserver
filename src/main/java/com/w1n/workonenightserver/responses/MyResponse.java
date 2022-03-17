package com.w1n.workonenightserver.responses;

public class MyResponse<T> {

    T data;
    int code;
    String message;
    String exception;

    public MyResponse(){}

    public MyResponse(T data, int code, String message, String exception){
        this.data = data;
        this.code = code;
        this.message = message;
        this.exception = exception;
    }


    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public String getException() {
        return exception;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setException(String exception) {
        this.exception = exception;
    }
}
