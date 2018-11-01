package com.axonactive.common.ws.rs.exception;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.ejb.EJBException;
import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.axonactive.common.ws.rs.ResponseMessage;

@Provider
public class EJBExceptionMapper implements ExceptionMapper<EJBException> {

    @Override
    public Response toResponse(EJBException ex) {
        Throwable cause = ex.getCause();
        if (cause instanceof NotFoundException) {
        	NotFoundException actual = (NotFoundException) cause;
        	ResponseMessage message = ResponseMessage.buildErrorMessage(actual.getErrorCode(), actual.getMessage());
            return Response.status(Response.Status.NOT_FOUND).
            		entity(message).
                    build();
        }
        
        if (cause instanceof EntityNotFoundException) {
        	EntityNotFoundException actual = (EntityNotFoundException) cause;
        	ResponseMessage message = ResponseMessage.buildErrorMessage(actual.getMessage());
            return Response.status(Response.Status.NOT_FOUND).
            		entity(message).
                    build();
        }
        
        if (cause instanceof ConstraintViolationException) {
            ConstraintViolationException violationException = (ConstraintViolationException) cause;
            ResponseMessage message = ResponseMessage.
                    buildErrorMessage(extractErrorMessage(violationException.getConstraintViolations()));
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(message)
                    .build();
        }

        return Response.serverError().
                header("cause", ex.toString()).build();
    }
    
    private String extractErrorMessage(Set<ConstraintViolation<?>> violations) {
        List<String> messages = violations.stream()
        		.map(ConstraintViolation::getMessage)
        		.collect(Collectors.toList());
        
        return messages.toString();
    }
}
