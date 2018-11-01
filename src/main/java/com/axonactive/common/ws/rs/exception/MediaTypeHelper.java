/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.axonactive.common.ws.rs.exception;

import java.util.List;
import java.util.Optional;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author nvmuon
 */
public class MediaTypeHelper {

    private MediaTypeHelper() {
    }

    public static MediaType getMediaType(List<MediaType> accept) {
        MediaType type = Optional.ofNullable(accept)
                .map(thiz -> thiz.isEmpty() ? MediaType.APPLICATION_JSON_TYPE : thiz.get(0))
                .get();

        if (MediaType.APPLICATION_XML_TYPE.getType().equalsIgnoreCase(type.getType())
                && MediaType.APPLICATION_XML_TYPE.getSubtype().equalsIgnoreCase(type.getSubtype())) {
            return MediaType.APPLICATION_XML_TYPE;
        }
        if (MediaType.APPLICATION_JSON_TYPE.getType().equalsIgnoreCase(type.getType())
                && MediaType.APPLICATION_JSON_TYPE.getSubtype().equalsIgnoreCase(type.getSubtype())) {
            return MediaType.APPLICATION_JSON_TYPE;
        }
        return MediaType.APPLICATION_JSON_TYPE;
    }
}
