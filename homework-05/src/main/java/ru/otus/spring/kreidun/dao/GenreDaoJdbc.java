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
import ru.otus.spring.kreidun.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
public class GenreDaoJdbc implements GenreDao {

    private final NamedParameterJdbcOperations jdbc;

    public GenreDaoJdbc(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public int count() {
        SqlParameterSource paramSource = new EmptySqlParameterSource();
        return jdbc.queryForObject("select count(*) from genres", paramSource, Integer.class);
    }

    @Override
    public long insert(String genreName) {

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", genreName);
        KeyHolder kh = new GeneratedKeyHolder();
        jdbc.update("insert into genres (`name`) values (:name)", params, kh);
        return kh.getKey().longValue();
    }

    @Override
    public Genre getById(long id) {

        return jdbc.queryForObject("select * from genres where id = :id",  Map.of("id", id), new GenreMapper());
    }

    @Override
    public List<Genre> getAll() {
        return jdbc.query("select * from genres", new GenreMapper());
    }

    public static class GenreMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            return new Genre(id, name);
        }
    }
}
