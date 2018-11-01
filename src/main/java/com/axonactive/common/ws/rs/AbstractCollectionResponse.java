package com.axonactive.common.ws.rs;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractCollectionResponse<T> implements Iterable<T>, Serializable {

    private List<T> data;

    private ResponseMessage message;

    public AbstractCollectionResponse() {

    }

    public AbstractCollectionResponse(List<T> data) {
        this.data = data;
        this.message = ResponseMessage.buildSuccessfulMessage();
    }

    public AbstractCollectionResponse(List<T> data, ResponseMessage message) {
        this.data = data;
        this.message = message;
    }

    public List<T> getData() {
        return data;
    }

    public ResponseMessage getMessage() {
        return message;
    }

    @Override
    public  Iterator<T> iterator() {
        return this.data.iterator();
    }
}
