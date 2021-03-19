package ru.otus.spring.kreidun.dao;

import org.springframework.stereotype.Repository;
import ru.otus.spring.kreidun.configuration.ConfigFileName;
import ru.otus.spring.kreidun.configuration.ConfigLocale;
import ru.otus.spring.kreidun.domain.Question;
import ru.otus.spring.kreidun.service.FileLoaderImpl;
import ru.otus.spring.kreidun.service.ParserCSV;

import java.util.ArrayList;
import java.util.List;

@Repository
public class QuestionDaoCsv implements QuestionDao{

    private List<Question> questions;
    private final ConfigLocale configLocale;
    private final ConfigFileName configFileName;
    private final FileLoaderImpl fileLoader;
    private final ParserCSV parserCSV;

    public  QuestionDaoCsv(ConfigLocale configLocale,
                           ConfigFileName configFileName,
                           FileLoaderImpl fileLoader,
                           ParserCSV parserCSV)
    {
        this.configLocale = configLocale;
        this.configFileName = configFileName;
        this.fileLoader = fileLoader;
        this.parserCSV = parserCSV;
    }

    @Override
    public void loadQuestions() {

        questions = new ArrayList<>();

        String fileName = String.format(this.configFileName.getPattern(), this.configLocale.getLocale().toString());

        questions = parserCSV.parsingFile(fileLoader.loadFile(fileName));
    }

    @Override
    public List<Question> getQuestions() {

        return questions;
    }
}
