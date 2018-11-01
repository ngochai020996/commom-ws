package com.axonactive.common.ws.rs;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import java.text.SimpleDateFormat;
import java.util.TimeZone;


/**
 * To serialize and deserialize date object from/to json
 * @author hung
 *
 */
@Provider
public class ObjectMapperContextResolver implements ContextResolver<ObjectMapper> {

    private static final String DEFAULT_ISO8601_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSX";

    private final ObjectMapper objectMapper;

    public ObjectMapperContextResolver() {
        final SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_ISO8601_FORMAT);
        objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
        objectMapper.setDateFormat(sdf);
        objectMapper.setTimeZone(TimeZone.getTimeZone("UTC"));
        objectMapper.findAndRegisterModules();
    }

    @Override
    public ObjectMapper getContext(Class<?> type) {
        return objectMapper;
    }
}