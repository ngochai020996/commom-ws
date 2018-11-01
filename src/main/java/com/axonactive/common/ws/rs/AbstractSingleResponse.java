package com.axonactive.common.ws.rs;

import java.io.Serializable;

public abstract class AbstractSingleResponse<T> implements Serializable {

    private T data;

    private ResponseMessage message;

    public AbstractSingleResponse() {
    }

    public AbstractSingleResponse(T data, ResponseMessage message) {
        this.data = data;
        this.message = message;

    }

    public AbstractSingleResponse(T data) {
        this.data = data;
        this.message = ResponseMessage.buildSuccessfulMessage();
    }

    public T getData() {
        return data;
    }

    public ResponseMessage getMessage() {
        return message;
    }

}
