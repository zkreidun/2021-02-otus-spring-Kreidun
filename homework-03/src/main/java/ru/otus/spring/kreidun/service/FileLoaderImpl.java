package ru.otus.spring.kreidun.service;

import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class FileLoaderImpl implements FileLoader{

    @Override
    public List<String> loadFile(String fileName) {

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
        Scanner scanner = new Scanner(inputStream);
        List<String> strings = new ArrayList<>();

        while (scanner.hasNext()) {
            strings.add(scanner.nextLine());
        }

        return strings;
    }
}
