/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.axonactive.common.ws.rs.exception;


import com.axonactive.common.ws.rs.ResponseMessage;

import lombok.Getter;

/**
 *
 * @author nvmuon
 */
public class ServerErrorException extends RuntimeException {
	private @Getter String errorCode;

    public ServerErrorException(String message) {
        super(message);
    }
    
    public ServerErrorException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
    
    public ResponseMessage buildResponseMessage() {
        return ResponseMessage.buildErrorMessage(this.errorCode, getMessage());
    }
}
