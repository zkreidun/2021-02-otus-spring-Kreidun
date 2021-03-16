package ru.otus.spring.kreidun.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParserCSVImpl implements ParserCSV{

    @Override
    public List<String[]> parsingFile(List<String> fileCSV) {

        List<String[]> strings = new ArrayList<>();

        for (String string: fileCSV) {
            strings.add(string.split(","));
        }

        return strings;
    }
}
