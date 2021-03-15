package ru.otus.spring.kreidun.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.kreidun.configuration.ConfigLocale;

@Service
public class MessageServiceImpl implements MessageService  {

    private MessageSource messageSource ;
    private final ConfigLocale configLocale;

    public MessageServiceImpl(MessageSource messageSource , ConfigLocale configLocale)
    {
        this.messageSource = messageSource;
        this.configLocale = configLocale;
    };

    @Override
    public String getMessage(String keyString)
    {
        String message = messageSource.getMessage(keyString,null, configLocale.getLocale());
        return message;
    }

    @Override
    public String getMessageFormat(String keyString, Object[] objectsFormat)
    {
        String message = messageSource.getMessage(keyString, objectsFormat, configLocale.getLocale());
        return message;
    }
}
