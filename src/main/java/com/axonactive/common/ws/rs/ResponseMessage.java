package com.axonactive.common.ws.rs;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor @AllArgsConstructor
public @Data class ResponseMessage implements Serializable {

    public enum Serverity {
        INFO,
        WARNING,
        ERROR
    }

    private boolean successful;
    private String code;
    private String message;
    private Serverity severity;
    private String detailedMessage;

    public static ResponseMessage buildSuccessfulMessage() {
        ResponseMessage message = new ResponseMessage();
        message.setSuccessful(true);
        message.setSeverity(Serverity.INFO);
        return message;
    }

    public static ResponseMessage buildErrorMessage(String code, String message, String detailedMessage) {

        return new ResponseMessage(false, code, message, Serverity.ERROR, detailedMessage);
    }
    
    public static ResponseMessage buildErrorMessage(String message) {

        return new ResponseMessage(false, null, message, Serverity.ERROR, null);
    }

    public static ResponseMessage buildErrorMessage(String code, String message) {

        return new ResponseMessage(false, code, message, Serverity.ERROR, null);
    }

    
}
