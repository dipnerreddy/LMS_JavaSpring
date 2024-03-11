package com.dipner.software.beans;

public class Response<Users> {

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean success) {
        Success = success;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Users getData() {
        return data;
    }

    public void setData(Users data) {
        this.data = data;
    }

    public Response(String status, String message, Users data, boolean Success) {
        this.status = status;
        this.message = message;
        this.Success = Success;
        this.data = data;
    }

    private String status;
    private String message;
    private boolean Success;
    private Users data;

}