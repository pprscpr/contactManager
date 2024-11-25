package com.pprscpr.contactmanager.dto.response;

public class ApiResponse<T> {

    private String message;
    private String status;
    private T data = null;

    public ApiResponse(String status, String message, T data) {
            this.message = message;
            this.status = status;
            this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
