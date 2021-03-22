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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
public class AuthorDaoJdbc implements AuthorDao {

    private final NamedParameterJdbcOperations jdbc;

    public AuthorDaoJdbc(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public int count() {
        SqlParameterSource paramSource = new EmptySqlParameterSource();
        return jdbc.queryForObject("select count(*) from authors", paramSource, Integer.class);
    }

    @Override
    public Long insert(String firstName, String lastName) {

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("firstName", firstName);
        params.addValue("lastName", lastName);
        KeyHolder kh = new GeneratedKeyHolder();
        jdbc.update("insert into authors (`firstName`,`lastName`) values (:firstName, :lastName)", params, kh);
        return kh.getKey().longValue();
    }

    @Override
    public Author getById(long id) {

        Map<String, Object> params = Collections.singletonMap("id", id);
        return jdbc.queryForObject("select id, firstname, lastname from authors where id = :id", params, new AuthorMapper());
    }

    @Override
    public List<Author> getAll() {
        return jdbc.query("select id, firstname, lastname from authors", new AuthorMapper());
    }

    public static class AuthorMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            return new Author(id, firstName, lastName);
        }
    }
}
