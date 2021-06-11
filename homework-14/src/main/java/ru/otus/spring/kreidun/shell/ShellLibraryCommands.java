package ru.otus.spring.kreidun.shell;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.kreidun.models.AuthorForWrite;
import ru.otus.spring.kreidun.models.BookForWrite;
import ru.otus.spring.kreidun.models.CommentForWrite;
import ru.otus.spring.kreidun.models.GenreForWrite;
import ru.otus.spring.kreidun.repositories.AuthorRepository;
import ru.otus.spring.kreidun.repositories.BookRepository;
import ru.otus.spring.kreidun.repositories.CommentRepository;
import ru.otus.spring.kreidun.repositories.GenreRepository;

import java.util.Date;
import java.util.List;

import static ru.otus.spring.kreidun.config.JobConfig.IMPORT_LIBRARY_JOB_NAME;


@ShellComponent
@RequiredArgsConstructor
public class ShellLibraryCommands {

    private final Job importLibraryJob;
    private final JobLauncher jobLauncher;
    private final JobExplorer jobExplorer;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;

    @SneakyThrows
    @ShellMethod(value = "runjob", key = "rj")
    public void startMigrationJobWithJobLauncher() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {

        JobParametersBuilder builder = new JobParametersBuilder();
        builder.addDate("date", new Date());
        JobExecution execution = jobLauncher.run(importLibraryJob,
                builder.toJobParameters());
        System.out.println(execution);
        List<AuthorForWrite> lista = authorRepository.findAll();
        lista.forEach(a -> System.out.println(a.toString()));
        List<GenreForWrite> listg = genreRepository.findAll();
        listg.forEach(a -> System.out.println(a.toString()));
        List<BookForWrite> listb = bookRepository.findAll();
        listb.forEach(a -> System.out.println(a.toString()));
        List<CommentForWrite> listc = commentRepository.findAll();
        listc.forEach(a -> System.out.println(a.toString()));
    }

    @ShellMethod(value = "showInfo", key = "i")
    public void showInfo() {
        System.out.println(jobExplorer.getJobNames());
        System.out.println(jobExplorer.getLastJobInstance(IMPORT_LIBRARY_JOB_NAME));
    }

}
