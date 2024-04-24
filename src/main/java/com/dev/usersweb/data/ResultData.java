package com.dev.usersweb.data;

public class ResultData {

    private String message;
    private boolean status;

    public ResultData() {
    }

    public ResultData(String message, boolean status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
