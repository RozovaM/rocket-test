package core.modules.database.services;

import core.modules.library.models.Verbose;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Map;

public class Database {

    private DatabaseAssert dbAssert;

    private JdbcTemplate jdbcTemplate;

    private LightQueryBuilder lightQueryBuilder;

    private Verbose verbose;

    public Database(DatabaseAssert dbAssert, JdbcTemplate jdbcTemplate, LightQueryBuilder lightQueryBuilder, Verbose verbose) {
        this.dbAssert = dbAssert;
        this.jdbcTemplate = jdbcTemplate;
        this.lightQueryBuilder = lightQueryBuilder;
        this.verbose = verbose;
    }

    public Database haveInTable(String table, Map data){

        String sql = lightQueryBuilder.buildInsert(table, data);
        jdbcTemplate.update(sql);
        verbose.testInfo("Create precondition in DB", sql);
        return this;
    }

    public DatabaseAssert assertion() {
        return dbAssert;
    }
}
