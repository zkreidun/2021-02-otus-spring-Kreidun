package ru.otus.spring.kreidun.service;

import java.util.List;

public interface ParserCSV {
    List<String[]> parsingFile(List<String> fileCSV);
}
