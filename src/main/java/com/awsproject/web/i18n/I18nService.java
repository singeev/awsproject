package com.awsproject.web.i18n;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * Created by singeev on 20/11/2017.
 */

@Service
public class I18nService {

    @Autowired
    private MessageSource messageSource;

    /**
     * Returns a message for the given message ID and the default locale in the session context
     * @param messageId The key to the message resource file
     */
    public String getMessage(String messageId) {
        Locale locale = LocaleContextHolder.getLocale();
        return getMessage(messageId, locale);
    }

    /**
     * Returns a message for the given message ID and locale
     * @param messageId The key to the message resource file
     * @param locale The locale
     */
    private String getMessage(String messageId, Locale locale) {
        return messageSource.getMessage(messageId, null, locale);
    }
}
