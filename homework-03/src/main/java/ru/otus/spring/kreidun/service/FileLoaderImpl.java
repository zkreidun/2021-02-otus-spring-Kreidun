package ru.otus.spring.kreidun.service;

import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Scanner;

@Service
public class FileLoaderImpl implements FileLoader{

    @Override
    public Scanner loadFile(String fileName) {

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
        Scanner scanner = new Scanner(inputStream);

        return scanner;
    }
}
