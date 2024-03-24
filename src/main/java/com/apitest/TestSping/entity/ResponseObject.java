package com.apitest.TestSping.entity;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
// class để định hình thông báo cho clients biết
public class ResponseObject {
    private String status;
    private String message;
    private Object data;

    public ResponseObject(String status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
