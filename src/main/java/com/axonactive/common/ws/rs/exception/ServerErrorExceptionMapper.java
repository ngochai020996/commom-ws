/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.axonactive.common.ws.rs.exception;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author nvmuon
 */
@Provider
public class ServerErrorExceptionMapper implements ExceptionMapper<ServerErrorException> {
  
    private static final Logger LOGGER = LoggerFactory.getLogger(ServerErrorExceptionMapper.class);
    
    @Context
    private HttpHeaders headers;
  
    @Override
    public Response toResponse(ServerErrorException e) {
        LOGGER.error("#### server error exception: " + e.getMessage(), e);
        return Response.serverError()
                .type(MediaTypeHelper.getMediaType(headers.getAcceptableMediaTypes()))
                .entity(e.buildResponseMessage())
                .build();
    }
}
