package ru.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import ru.dao.interfaces.MP3Dao;
import ru.dao.objects.MP3;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class SqLiteDao implements MP3Dao {
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate npJdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        npJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }


    @Override
    public void insert(MP3 mp3) {
        String sql = "insert into mp3(name,author) values(?,?)";
        jdbcTemplate.update(sql, new Object[]{mp3.getName(), mp3.getAuthor()});
    }

    @Override
    public void insert(List<MP3> mp3List) {
        for (MP3 mp3 : mp3List) {
            insert(mp3);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "delete from mp3 where id=?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void delete(MP3 mp3) {
        delete(mp3.getId());
    }

    @Override
    public MP3 getMP3ById(int id) {
        String sql = "select * from mp3 where id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return npJdbcTemplate.queryForObject(sql, params, new MP3RowMapper());
    }

    @Override
    public List<MP3> getMP3ListByName(String name) {
        String sql = "select * from mp3 where UPPER(name) LIKE :name ";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", "%" + name.toUpperCase() + "%");

        return npJdbcTemplate.query(sql, params, new MP3RowMapper());
    }

    @Override
    public List<MP3> getMP3ListByAuthor(String author) {
        String sql = "select * from mp3 where UPPER(author) LIKE :author ";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("author", "%" + author.toUpperCase() + "%");

        return npJdbcTemplate.query(sql, params, new MP3RowMapper());
    }

    @Override
    public int getMP3Count() {
        String sql = "select count(*) from mp3";
        return npJdbcTemplate.getJdbcOperations().queryForObject(sql, Integer.class);
    }

    private static final class MP3RowMapper implements RowMapper<MP3> {
        @Override
        public MP3 mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            MP3 mp3 = new MP3();
            mp3.setId(resultSet.getInt("id"));
            mp3.setName(resultSet.getString("name"));
            mp3.setAuthor(resultSet.getString("author"));
            return mp3;
        }
    }
}
