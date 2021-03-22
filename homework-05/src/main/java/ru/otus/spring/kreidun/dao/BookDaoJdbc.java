package ru.otus.spring.kreidun.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.EmptySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring.kreidun.domain.Author;
import ru.otus.spring.kreidun.domain.Book;
import ru.otus.spring.kreidun.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
public class BookDaoJdbc implements BookDao {

    private final NamedParameterJdbcOperations jdbc;

    public BookDaoJdbc(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public int count() {
        SqlParameterSource paramSource = new EmptySqlParameterSource();
        return jdbc.queryForObject("select count(*) from books", paramSource, Integer.class);
    }

    @Override
    public long insert(String bookTitle, long authorId, long genreId) {

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("title", bookTitle);
        params.addValue("genreid", genreId);
        params.addValue("authorid", authorId);
        KeyHolder kh = new GeneratedKeyHolder();
        jdbc.update("insert into books (`title`,genreid ,authorid) values (:title, :genreid, :authorid)", params, kh);
        return kh.getKey().longValue();
    }

    @Override
    public void update(long id, String bookTitle, long authorId, long genreId) {
        jdbc.update("update books set `title` = :bookTitle, authorid = :authorId, genreid = :genreId where id = :id",
                Map.of("id", id, "bookTitle", bookTitle, "authorId", authorId, "genreId", genreId));
    }

    @Override
    public void deleteById(long id) {

        jdbc.update("delete from books where id = :id", Map.of("id", id));
    }

    @Override
    public Book getById(long id) {

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        return jdbc.queryForObject(" select b.id, b.title, b.authorid, " +
                "a.firstname, a.lastname, b.genreid, j.name as genrename " +
                "from books b " +
                "inner join authors a on a.id = b.authorid " +
                "inner join genres j on j.id = b.genreid " +
                "where b.id = :id ", params, new BookMapper());
    }

    @Override
    public List<Book> getAll() {

        List<Book> listBook = jdbc.query(" select b.id, b.title, b.authorid, " +
                "a.firstname, a.lastname, b.genreid, j.name as genrename " +
                "from books b " +
                "inner join authors a on a.id = b.authorid " +
                "inner join genres j on j.id = b.genreid "
                , new BookMapper());
        return listBook;
    }

    public static class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String bookTitle = resultSet.getString("title");
            Long genreId = resultSet.getLong("genreId");
            String genreName = resultSet.getString("genrename");
            Long authorId = resultSet.getLong("authorId");
            String firstname = resultSet.getString("firstname");
            String lastname = resultSet.getString("lastname");
            Author author = new Author(authorId, firstname, lastname);
            Genre genre = new Genre(genreId, genreName);
            return new Book(id, bookTitle, author, genre);
        }
    }
}
