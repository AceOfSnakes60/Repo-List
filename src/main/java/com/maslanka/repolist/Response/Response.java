package com.maslanka.repolist.Response;

public class Response<T> {
    private int status;
    private String message;
    private T data;

    // Constructor for success responses with data
    public Response(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    // Constructor for responses without data (e.g., error messages)
    public Response(int status, String message) {
        this.status = status;
        this.message = message;
        this.data = null;
    }

    // Getters and Setters
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
