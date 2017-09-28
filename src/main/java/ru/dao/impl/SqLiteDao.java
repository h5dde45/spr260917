package ru.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import ru.dao.interfaces.MP3Dao;
import ru.dao.objects.Author;
import ru.dao.objects.MP3;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Component
public class SqLiteDao implements MP3Dao {

    private SimpleJdbcInsert insertMP3;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate npJdbcTemplate;
    private DataSource dataSource;
    private static final String mp3Table = "mp3";
    private static final String mp3View = "mp3_view";

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        npJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        insertMP3 = new SimpleJdbcInsert(dataSource).withTableName("mp3").usingColumns("name", "author");
        this.dataSource = dataSource;
    }


//    @Override
//    @Transactional(propagation = Propagation.REQUIRED)
    public int insertMP3(MP3 mp3) {
        System.out.println(TransactionSynchronizationManager.isActualTransactionActive());
        String sql = "INSERT INTO " + mp3Table + " (author_id,name) VALUES(:idAuthor,:mp3Name)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        int idAuthor = insertAuthor(mp3.getAuthor());
        params = new MapSqlParameterSource();
        params.addValue("idAuthor", idAuthor);
        params.addValue("mp3Name", mp3.getName());

        return npJdbcTemplate.update(sql, params);
    }

//    @Override
//    @Transactional(propagation = Propagation.MANDATORY)
    public int insertAuthor(Author author) {
        System.out.println(TransactionSynchronizationManager.isActualTransactionActive());
        String sql = "INSERT INTO author (name) VALUES(:name)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", author.getName());
        npJdbcTemplate.update(sql, params, keyHolder);

        return keyHolder.getKey().intValue();

    }

    @Override
    public int insertSimple(MP3 mp3) {

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", mp3.getName());
        params.addValue("author", mp3.getAuthor());

        return insertMP3.execute(params);
    }

    @Override
    public int insertBatchList(List<MP3> mp3List) {
        String sql = "INSERT INTO mp3 (name, author) VALUES(:name,:author)";
        SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(mp3List.toArray());
        int[] updateCounts = npJdbcTemplate.batchUpdate(sql, batch);
        return updateCounts.length;
    }

    @Override
    public int insertList(List<MP3> mp3List) {
        int i = 0;
        for (MP3 mp3 : mp3List) {
            insertMP3(mp3);
            i++;
        }
        return i;
    }

    @Override
    public Map<String, Integer> getStat() {
        String sql = "select author_name, count(*) as count from " + mp3View + " group by author_name";

        return npJdbcTemplate.query(sql, new ResultSetExtractor<Map<String, Integer>>() {
            @Override
            public Map<String, Integer> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                Map<String, Integer> map = new TreeMap<String, Integer>();
                while (resultSet.next()) {
                    String author = resultSet.getString("author_name");
                    int count = resultSet.getInt("count");
                    map.put(author, count);
                }
                return map;
            }
        });
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM mp3 WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void delete(MP3 mp3) {
        delete(mp3.getId());
    }

    @Override
    public MP3 getMP3ById(int id) {
        String sql = "SELECT * FROM " + mp3View + " WHERE mp3_id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return npJdbcTemplate.queryForObject(sql, params, new MP3RowMapper());
    }

    @Override
    public List<MP3> getMP3ListByName(String name) {
        String sql = "SELECT * FROM " + mp3View + " WHERE UPPER(mp3_name) LIKE :name ";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", "%" + name.toUpperCase() + "%");

        return npJdbcTemplate.query(sql, params, new MP3RowMapper());
    }

    @Override
    public List<MP3> getMP3ListByAuthor(String author) {
        String sql = "SELECT * FROM " + mp3View + " WHERE UPPER(author_name) LIKE :author ";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("author", "%" + author.toUpperCase() + "%");

        return npJdbcTemplate.query(sql, params, new MP3RowMapper());
    }

    @Override
    public int getMP3Count() {
        String sql = "SELECT count(*) FROM " + mp3View;
        return npJdbcTemplate.getJdbcOperations().queryForObject(sql, Integer.class);
    }

    private static final class MP3RowMapper implements RowMapper<MP3> {
        @Override
        public MP3 mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            Author author = new Author();
            author.setId(resultSet.getInt("author_id"));
            author.setName(resultSet.getString("author_name"));

            MP3 mp3 = new MP3();
            mp3.setId(resultSet.getInt("mp3_id"));
            mp3.setName(resultSet.getString("mp3_name"));
            mp3.setAuthor(author);
            return mp3;
        }
    }
}
