/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.axonactive.common.ws.rs.locale;

import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;

/**
 * @author muonnv
 ** Checks whether the {@code Accept-Language} HTTP header exists and creates a {@link ThreadLocal} to store the
 ** corresponding Locale.
 */
@Provider
@PreMatching
public class AcceptLanguageRequestFilter implements ContainerRequestFilter {

    @Context
    private HttpHeaders headers;
    
    @Override
    public void filter(ContainerRequestContext crc) throws IOException {
        if (!headers.getAcceptableLanguages().isEmpty()) {
            LocaleThreadLocal.set(headers.getAcceptableLanguages().get(0));
        }
    }
    
}
