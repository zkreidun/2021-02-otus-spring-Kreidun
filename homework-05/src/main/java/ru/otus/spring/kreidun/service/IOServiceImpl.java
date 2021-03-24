package ru.otus.spring.kreidun.service;

import org.springframework.stereotype.Service;

@Service
public class IOServiceImpl implements IOService {

    @Override
    public void printString(String s) {
        System.out.println(s);
    }
}
