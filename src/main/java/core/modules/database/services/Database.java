package core.modules.database.services;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Map;

public class Database {

    private DatabaseAssert dbAssert;

    private JdbcTemplate jdbcTemplate;

    private LightQueryBuilder lightQueryBuilder;

    public Database(DatabaseAssert dbAssert, JdbcTemplate jdbcTemplate, LightQueryBuilder lightQueryBuilder) {
        this.dbAssert = dbAssert;
        this.jdbcTemplate = jdbcTemplate;
        this.lightQueryBuilder = lightQueryBuilder;
    }

    public Database haveInTable(String table, Map data){
        jdbcTemplate.update(lightQueryBuilder.buildInsert(table, data));
        return this;
    }

    public DatabaseAssert assertion() {
        return dbAssert;
    }
}
