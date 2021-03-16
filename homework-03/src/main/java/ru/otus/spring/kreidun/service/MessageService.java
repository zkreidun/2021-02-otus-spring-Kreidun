package ru.otus.spring.kreidun.service;

public interface MessageService {

    String getMessage(String keyString);

    String getMessageFormat(String keyString, Object[] objectsFormat);
}
