/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.axonactive.common.ws.rs.locale;

import java.util.Locale;
import javax.validation.MessageInterpolator;

/**
 *
 * @author nvmuon
 */
public class LocaleSpecificMessageInterpolator implements MessageInterpolator {

    private final MessageInterpolator defaultInterpolator;

    public LocaleSpecificMessageInterpolator(MessageInterpolator defaultInterpolator) {
        this.defaultInterpolator = defaultInterpolator;
    }
    
    @Override
    public String interpolate(String string, Context cntxt) {
        return this.defaultInterpolator.interpolate(string, cntxt, LocaleThreadLocal.get());
    }

    @Override
    public String interpolate(String string, Context cntxt, Locale locale) {
        return this.defaultInterpolator.interpolate(string, cntxt, LocaleThreadLocal.get());
    }
    
}
